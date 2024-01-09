package OrderAndChaos;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI extends JFrame {

  private JButton[][] buttons;
  private Game game;
  boolean player = true;


  public GameGUI() {
    setTitle("Order&Chaos - Order Turn");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(600, 600);
    setLocationRelativeTo(null);

    buttons = new JButton[6][6];
    //board = new char[6][6];
    game = new Game();

    initializeButtons();
    initializeBottomPanel();  // Nuovo metodo per inizializzare i bottoni "New Game" e "Instructions"

  }

  private void initializeButtons() {
    JPanel panel = new JPanel(new GridLayout(6, 6));

    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 6; j++) {
        buttons[i][j] = new JButton();
        buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
        buttons[i][j].addActionListener(new ButtonClickListener(i, j));
        panel.add(buttons[i][j]);
        game.board[i][j] = ' ' ;
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
      if (buttons[row][col].getText().equals("")) {
        char symbol = chooseSymbol();

        if(symbol != 'B'){
          buttons[row][col].setText(String.valueOf(symbol));
          if(symbol == 'X'){
            buttons[row][col].setOpaque(true);
            buttons[row][col].setBackground(Color.RED);
          }else{
            buttons[row][col].setOpaque(true);
            buttons[row][col].setBackground(Color.BLUE);
          }
          game.board[row][col] = symbol;
          game.freeSpace--;
          player = !player;

          if(player){
            setTitle("Order turn");
          }else{
            setTitle("Chaos turn");

          }


          if (game.checkWin()) {
            setTitle("ORDER WIN");
            colorWinningStreak();
            JOptionPane.showMessageDialog(null, "What a game! Order wins!");
          } else if (game.freeSpace == 0) {
            setTitle("CHAOS WIN");
            JOptionPane.showMessageDialog(null, "Wow! Chaos wins");
          }
        }
      }else {
        JOptionPane.showMessageDialog(null, "Cell already taken. Choose another one.");
      }

    }
  }


  private char chooseSymbol() {
    String[] options = {"Back", "X", "O"};
    int choice = JOptionPane.showOptionDialog(null, "Choose your symbol:", "Symbol Selection",
        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

    //return (choice == 0) ? 'X' : 'O';

    return switch (choice) {
      case 1 -> 'X';
      case 2 -> 'O';
      default -> 'B';
    };

  }

  private void initializeBottomPanel() {
    JPanel bottomPanel = new JPanel();

    JButton instructionsButton = new JButton("How to play");
    instructionsButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        showInstructions();
      }
    });

    JButton newGameButton = new JButton("New Game");
    newGameButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        resetGame();
      }
    });

    bottomPanel.add(instructionsButton);
    bottomPanel.add(newGameButton);


    add(bottomPanel, BorderLayout.SOUTH);
  }

  /*
        System.out.println("""
          Don't worry, it's simple! It's like tic-tac-toe but with a 6x6 board and two player: ORDER and CHAOS.\s
          Both player can write down X or O. Order's goal is to make a 5 straight symbols horizontally, vertically or diagonally.\s
          On the contrary Chaos to win has to avoid at any costs.\s""");
   */

  private void showInstructions() {
    // Aggiungi qui la logica per mostrare le istruzioni (puoi utilizzare un JOptionPane o un altro componente)
    JOptionPane.showMessageDialog(this, "It's like tic-tac-toe but with a 6x6 board and two player: " +
        "ORDER and CHAOS. \nBoth player can write down X or O. Order's goal is to make a 5 straight symbols horizontally," +
        " vertically or diagonally. \nOn the contrary Chaos to win has to avoid at any costs." );
  }


  private void resetGame() {

    int confirm = JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to start a new game?",
        "New game",
        JOptionPane.YES_NO_OPTION
    );

    if (confirm == JOptionPane.YES_OPTION) {
      game.freeSpace = 36;
      setTitle("Order&Chaos - Order Turn");
      for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 6; j++) {
          buttons[i][j].setOpaque(false);
          buttons[i][j].setText("");
          game.board[i][j] = ' ';
        }
      }
    }
  }

  private void colorWinningStreak(){

    int row;
    int col;

    for(int i = 0 ; i < 10; i = i + 2){
      row = game.winningStreak.get(i);
      col = game.winningStreak.get(i+1);
      buttons[row][col].setBackground(Color.GREEN);
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      GameGUI game = new GameGUI();
      game.setVisible(true);
    });
  }
}



/*
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI {

  private static Game game;
  private static JButton[][] gridButtons;
  private static JButton xButton;
  private static JButton oButton;

  public GameGUI() {
    game = new Game();

    JFrame frame = new JFrame("Tic Tac Toe");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(600, 600);

    JPanel mainPanel = new JPanel(new BorderLayout());

    JPanel gridPanel = new JPanel(new GridLayout(6, 6));
    gridButtons = new JButton[6][6];
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 6; j++) {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(50, 50));
        button.addActionListener(new ButtonClickListener(i, j));
        gridButtons[i][j] = button;
        gridPanel.add(button);
      }
    }

    mainPanel.add(gridPanel, BorderLayout.CENTER);

    JPanel symbolPanel = new JPanel();
    xButton = new JButton("X");
    oButton = new JButton("O");
    xButton.addActionListener(new SymbolButtonClickListener('X'));
    oButton.addActionListener(new SymbolButtonClickListener('O'));
    symbolPanel.add(xButton);
    symbolPanel.add(oButton);

    mainPanel.add(symbolPanel, BorderLayout.SOUTH);

    frame.getContentPane().add(mainPanel);

    frame.setVisible(true);
  }

  private class ButtonClickListener implements ActionListener {
    private int row;
    private int col;

    public ButtonClickListener(int row, int col) {
      this.row = row;
      this.col = col;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if (game.isCellFree(row, col)) {
        updateGrid(game.getBoard());
        game.fillCell(row, col, ' ');  // Inizializza con uno spazio vuoto
      }
    }
  }

  private class SymbolButtonClickListener implements ActionListener {
    char symbol;
    public SymbolButtonClickListener(char symbol) {
      this.symbol = symbol;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      game.setSymbol(0, 0, symbol);
    }
  }

  private void updateGrid(char[][] board) {
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 6; j++) {
        char symbol = board[i][j];
        gridButtons[i][j].setText(symbol != ' ' ? String.valueOf(symbol) : "");
        gridButtons[i][j].setEnabled(symbol == ' ');
      }
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new GameGUI();
      }
    });
  }
}

*/