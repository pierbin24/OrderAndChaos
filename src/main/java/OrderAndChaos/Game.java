package OrderAndChaos;

import java.util.Scanner;

public class Game {

  private static boolean turn = true;
  public char[][] board = new char[6][6];

  public static void main(String... args) {

    Game game = new Game();

    Scanner scanner = new Scanner(System.in);
    System.out.println("Do you wanna play? (y/n)");
    String answer = scanner.nextLine().trim().toLowerCase();


    if (answer.equals("y")) {
      System.out.println("Who play first? Order or Chaos? (o/c)");
      turn = game.selectPlayer();
      game.newGame();

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
    return turn;
  }


  public void newGame(){

    System.out.println("Inizia una nuova partita!");

    initializeBoard(board);
    //printBoard(board);

    while(!checkWin()){
      System.out.println("sono qui "+ turn);
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
        if (isValidMove(board, row, col, type)) {
          board[row][col] = type;
          printBoard(board);
          System.out.println(turn);
          turn = !turn;
          System.out.println(turn);
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

  private static boolean isValidMove(char[][] board, int row, int col, char type) {
    // Aggiungi eventuali regole di validità qui
    // Ad esempio, controlla se row e col sono nell'intervallo desiderato,
    // e se type è "X" o "O"
    boolean isValid = row >= 0 && row < 6 && col >= 0 && col < 6 && (type == 'X' || type == 'O');
    if(isValid){
      if(board[row][col] != ' '){
        isValid = false;
      }
    }
    return isValid;
  }

  public boolean checkWin(){

    if(checkRow()){
      return true;
    }
    if(checkCol()){
      return true;
    }

    if(checkDiag()){
      return true;
    }

    return false;


  }

  public boolean checkRow(){

    int rows = board.length;
    int cols = board[0].length;
    for (int i = 0; i < rows; i++){
      int straightSymbolCounter = 1;

      // Scorrere ogni colonna (partendo dalla seconda colonna)
      for (int j = 1; j < cols; j++) {
        // escludo il controllo se arrivo alla terza colonna con tre simboli diversi
        if((j >= 4 && straightSymbolCounter < 2)){
          return false;
        }else{
          // Controllare se il simbolo corrente è uguale a quello nella colonna precedente
          if ((board[i][j] == board[i][j - 1]) && (board[i][j] != ' ')) {
            straightSymbolCounter++;
            System.out.println(straightSymbolCounter);

            // Se abbiamo 5 simboli consecutivi, restituire true
            if (straightSymbolCounter == 5) {
              return true;
              //win = true;
            }
          } else {
            // Se i simboli non sono uguali, riavviare il contatore
            straightSymbolCounter = 1;
          }
        }
      }
    }
    return false;
  }

  public boolean checkCol(){

    int rows = board.length;
    int cols = board[0].length;

    for (int j = 0; j < cols; j++){
      int straightSymbolCounter = 1;

      // Scorrere ogni colonna (partendo dalla seconda colonna)
      for (int i = 1; i < rows; i++) {
        // escludo il controllo se arrivo alla terza colonna con tre simboli diversi
        if((i >= 4 && straightSymbolCounter < 2)){
          return false;
        }else{
          // Controllare se il simbolo corrente è uguale a quello nella colonna precedente
          if ((board[i][j] == board[i-1][j]) && (board[i][j] != ' ')) {
            straightSymbolCounter++;
            System.out.println(straightSymbolCounter);

            // Se abbiamo 5 simboli consecutivi, restituire true
            if (straightSymbolCounter == 5) {
              return true;
            }
          } else {
            // Se i simboli non sono uguali, riavviare il contatore
            straightSymbolCounter = 1;
          }
        }
      }
    }
    return false;
  }


  private boolean checkDiag() {
    int rows = board.length;
    int cols = board[0].length;


    if(checkDiag1(1,1,rows)){
      return true;
    }
    if(checkDiag1(1,2,rows)){
      return true;
    }
    if(checkDiag1(2,1,rows)){
      return true;
    }
    if(checkDiag2(1,4,rows)){
      return true;
    }
    if(checkDiag2(1,3,rows)){
      return true;
    }
    if(checkDiag2(2,4,rows)){
      return true;
    }

      // Nessuna diagonale principale con 5 simboli consecutivi trovata
      return false;
    }


    public boolean checkDiag1(int indexR, int indexC, int length){

      int straightSymbolCounter = 1;

      //main diag from left up to down right
      for (int i = indexR, j = indexC; i < length && j < length; i++, j++) {
          if (board[i][j] == ' ') {
            straightSymbolCounter = 1;
          } else {
            if (board[i][j] == board[i - 1][j - 1]) {
              straightSymbolCounter++;
              if (straightSymbolCounter == 5) {
                return true;
              }
            } else {
              straightSymbolCounter = 1;
            }
          }
      }

    return false;
    }

  public boolean checkDiag2(int indexR, int indexC, int length){

    int straightSymbolCounter = 1;

    //main diag from left up to down right
    for (int i = indexR, j = indexC; i < length && j > 0; i++, j--) {
      if (board[i][j] == ' ') {
        straightSymbolCounter = 1;
      } else {
        if (board[i][j] == board[i - 1][j + 1]) {
          straightSymbolCounter++;
          if (straightSymbolCounter == 5) {
            return true;
          }
        } else {
          straightSymbolCounter = 1;
        }
      }
    }

    return false;
  }

}



