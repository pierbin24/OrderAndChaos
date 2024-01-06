package OrderAndChaos;

import java.util.Scanner;

public class Game {

  public static void main(String... args) {

    Game game = new Game();

    Scanner scanner = new Scanner(System.in);
    System.out.println("Do you wanna play? (y/n)");
    String answer = scanner.nextLine().trim().toLowerCase();


    if (answer.equals("y")) {
      System.out.println("Who play first? Order or Chaos? (o/c)");
      boolean turn = game.selectPlayer();
      game.newGame(turn);

    } else {
      System.out.println("Sad! See you next time.");
    }

    // Chiudi lo scanner per evitare leak di risorse


    scanner.close();
  }

  //true order/false chaos
  public boolean selectPlayer(){

    Scanner scanner = new Scanner(System.in);
    String player = scanner.nextLine().trim().toLowerCase();
    boolean turn = true;
    if(!player.equals("o")){
      if(player.equals("c")){
        turn = false;
      }else{
        System.out.println("There is a typo, answer with a 'o' for Order o 'c' for Chaos");
        selectPlayer();
      }
    }
    System.out.println(turn);
    return turn;
  }


  public void newGame(boolean turn){


    System.out.println("Inizia una nuova partita!");
    char[][] board = new char[6][6];
    initializeBoard(board);
    printBoard(board);
    if(turn){
      System.out.println("It's Order's turn");
    }else{
      System.out.println("It's Chaos' turn");
    }

    Scanner scanner = new Scanner(System.in);
    System.out.println("Make a move! (row, column, type)");
    String input = scanner.nextLine();
    makeTurn(board, input);
  }


  public void initializeBoard(char[][] board) {
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 6; j++) {
        board[i][j] = ' ' ;
      }
    }
  }

  public void printBoard(char[][] board) {
    System.out.println("-------------------------");
    for (int i = 0; i < 6; i++) {
      System.out.print("| ");
      for (int j = 0; j < 6; j++) {
        System.out.print(board[i][j] + " | ");
      }
      System.out.println("\n-------------------------");
    }
  }

  public void makeTurn(char[][] board, String input){

    // Separazione della stringa nelle tre componenti
    String[] components = input.split("\\s*,\\s*");
    // Verifica che ci siano esattamente tre componenti
    if (components.length == 3) {
      try {
        // Estrai i valori dalla stringa
        int row = Integer.parseInt(components[0]);
        int col = Integer.parseInt(components[1]);
        char type = components[2].toUpperCase().charAt(0);

        // Verifica che i valori siano validi
        if (isValidMove(row, col, type)) {
          board[row][col] = type;
          printBoard(board);
        } else {
          System.out.println("Input non valido. Assicurati di inserire valori corretti.");
        }
      } catch (NumberFormatException e) {
        System.out.println("Errore durante la conversione di numeri.");
      }
    } else {
      System.out.println("Input non valido. Assicurati di inserire tre componenti separate da virgola.");
    }

  }

  private static boolean isValidMove(int row, int col, char type) {
    // Aggiungi eventuali regole di validità qui
    // Ad esempio, controlla se row e col sono nell'intervallo desiderato,
    // e se type è "X" o "O"
    return row >= 0 && row < 6 && col >= 0 && col < 6 && (type == 'X' || type == 'O');
  }

}
