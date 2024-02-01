package OrderAndChaos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI extends JFrame {

  private final JButton[][] buttons;
  private final transient Game game;
  private boolean player = true;
  private boolean gameInProgress = true;

  public GameGUI() {
    setTitle("Order&Chaos - Order's Turn");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setSize(600, 600);
    setLocationRelativeTo(null);

    buttons = new JButton[6][6];
    game = new Game();

    initializeButtons();
    initializeBottomPanel();
  }

  private void initializeButtons() {
    JPanel panel = new JPanel(new GridLayout(6, 6));

    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 6; j++) {
        buttons[i][j] = new JButton();
        buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
        buttons[i][j].addActionListener(new ButtonClickListener(i, j));
        panel.add(buttons[i][j]);
        //Game.updateBoard(i, j, ' ');
        game.updateBoard(i, j, ' ');
      }
    }
    add(panel);
  }

  private class ButtonClickListener implements ActionListener {
    private final int row;
    private final int col;

    public ButtonClickListener(int row, int col) {
      this.row = row;
      this.col = col;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

      if(gameInProgress){

        if (buttons[row][col].getText().equals("")) {
          char symbol = chooseSymbol();

          if(symbol != 'B'){
            buttons[row][col].setText(String.valueOf(symbol));
            buttons[row][col].setOpaque(true);
            if(symbol == 'X'){
              buttons[row][col].setBackground(Color.RED);
            }else{
              buttons[row][col].setBackground(Color.BLUE);
            }
            //Game.updateBoard(row, col, symbol);
            game.updateBoard(row, col, symbol);
            Game.setFreeSpace(game.getFreeSpace() - 1);
            player = !player;

            if(player){
              setTitle("Order's turn");
            }else{
              setTitle("Chaos's turn");

            }

            if (game.checkWin()) {
              gameInProgress = false;
              setTitle("ORDER WIN");
              colorStreak();
              JOptionPane.showMessageDialog(null, "What a game! Order wins!");
            } else if (game.getFreeSpace() == 0) {
              gameInProgress = false;
              setTitle("CHAOS WIN");
              JOptionPane.showMessageDialog(null, "Wow! Chaos wins");
            }
          }
        }else {
          JOptionPane.showMessageDialog(null, "Cell already taken. Choose another one.");
        }
      }
    }

    private void colorStreak(){

      int r;
      int c;

      for(int i = 0 ; i < 10; i = i + 2){
        r = game.getStreak(i);
        c = game.getStreak(i+1);
        buttons[r][c].setBackground(Color.GREEN);
      }
    }
  }


  public char chooseSymbol() {
    String[] options = {"Back", "X", "O"};
    int choice = JOptionPane.showOptionDialog(null, "Choose your symbol:", "Symbol Selection",
        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

    return switch (choice) {
      case 1 -> 'X';
      case 2 -> 'O';
      default -> 'B';
    };
  }

  private void initializeBottomPanel() {
    JPanel bottomPanel = new JPanel();

    JButton instructionsButton = new JButton("How to play");
    instructionsButton.addActionListener(e -> showInstructions());

    JButton newGameButton = new JButton("New Game");
    newGameButton.addActionListener(e -> resetGame());

    bottomPanel.add(instructionsButton);
    bottomPanel.add(newGameButton);

    add(bottomPanel, BorderLayout.SOUTH);
  }

  private void showInstructions() {
    JOptionPane.showMessageDialog(this, """
        It's like tic-tac-toe but with a 6x6 board and two player: ORDER and CHAOS.\s
        Both player can write down X or O. Order's goal is to make a 5 straight symbols horizontally, vertically or diagonally.\s
        On the contrary Chaos to win has to avoid at any costs.""");
  }

  public void resetGame() {
    int confirm = JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to start a new game?",
        "New game",
        JOptionPane.YES_NO_OPTION
    );

    if (confirm == JOptionPane.YES_OPTION) {
      clearBoard();
    }
  }

  public void clearBoard(){
    gameInProgress = true;
    Game.setFreeSpace(36);
    player = true;
    setTitle("Order&Chaos - Order's Turn");
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 6; j++) {
        buttons[i][j].setOpaque(false);
        buttons[i][j].setText("");
        game.updateBoard(i, j, ' ');

        //Game.updateBoard(i, j, ' ');
      }
    }
  }


  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      GameGUI game = new GameGUI();
      game.setVisible(true);
    });
  }

}
