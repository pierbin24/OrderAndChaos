package OrderAndChaos;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI extends JFrame {

  private JButton[][] buttons;
  private Game game;


  public GameGUI() {
    setTitle("Order&Chaos");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(600, 600);
    setLocationRelativeTo(null);

    buttons = new JButton[6][6];
    //board = new char[6][6];
    game = new Game();

    initializeButtons();
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

    game.printBoard(game.board);
    add(panel);
  }

  private class ButtonClickListener implements ActionListener {
    private int row, col;

    public ButtonClickListener(int row, int col) {
      this.row = row;
      this.col = col;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if (buttons[row][col].getText().equals("")) {
        char symbol = chooseSymbol();

        if(symbol != 'U'){
          buttons[row][col].setText(String.valueOf(symbol));
          game.board[row][col] = symbol;
          //game.printBoard(game.board);
          game.freeSpace--;

          if (game.checkWin()) {
            JOptionPane.showMessageDialog(null, "What a game! Order wins!");
            resetGame();
          } else if (game.freeSpace == 0) {
            JOptionPane.showMessageDialog(null, "Wow! Chaos wins");
            resetGame();
          }
        }
      }else {
        JOptionPane.showMessageDialog(null, "Cell already taken. Choose another one.");
      }


    }
  }


  private char chooseSymbol() {
    String[] options = {"Undo", "X", "O"};
    int choice = JOptionPane.showOptionDialog(null, "Choose your symbol:", "Symbol Selection",
        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

    //return (choice == 0) ? 'X' : 'O';

    return switch (choice) {
      case 1 -> 'X';
      case 2 -> 'O';
      default -> 'U';
    };

  }

  /*
    private boolean checkWin() {



    return false;
  }
   */


  private void resetGame() {
    game.freeSpace = 36;
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 6; j++) {
        buttons[i][j].setText("");
        game.board[i][j] = ' ';
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