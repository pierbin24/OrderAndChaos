package OrderAndChaos;

import java.util.Random;
import java.util.Scanner;

public class GameTerminal {

  //false - chaos / true - order
  private static boolean player = false;
  public char[][] board = new char[6][6];
  private static int freeSpace = 36;

  public static void main(String... args) {

    GameTerminal game = new GameTerminal();

    //chaos test
    //fillBoard(game.board);
    //game.printBoard(game.board);

    Scanner scanner = new Scanner(System.in);
    System.out.println("Do you know how to play? (y/-)");
    String answer = scanner.nextLine().trim().toLowerCase();

    if (answer.equals("y")) {
      //System.out.println("Who play first? Order or Chaos? (o/c)");
      //turn = game.selectPlayer();
      game.newGame();

    } else {
      System.out.println("""
          Don't worry, it's simple! It's like tic-tac-toe but with a 6x6 board and two player: ORDER and CHAOS.\s
          Both player can write down X or O. Order's goal is to make a 5 straight symbols horizontally, vertically or diagonally.\s
          On the contrary Chaos to win has to avoid at any costs.\s""");
      System.out.println("So, now do you wanna play? (y/n)");
      answer = scanner.nextLine().trim().toLowerCase();
      if(answer.equals("y")){
        game.newGame();
      }else{
        System.out.println("Sad! See you next time, maybe");
      }
    }

    scanner.close();
  }

  //player selection
  /*
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
   */



  public void newGame(){

    System.out.println("Great! Have a good game");

    //board initialization
    initializeBoard(board);
    player = !player;
    //check if order win
    while(!checkWin() && freeSpace > 0){
      if(player){
        System.out.println("It's Order's turn");
      }else{
        System.out.println("It's Chaos' turn");
      }
      Scanner scanner = new Scanner(System.in);
      System.out.println("Make a move!");
      System.out.println("[Row,Col,Type]");
      String input = scanner.nextLine();
      makeTurn(board, input);
    }

    String winner;
    if(player){
      winner = "ORDER";
    }else{
      winner = "CHAOS";
    }

    endGame(winner);

  }

  //board initialization
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

  //turn
  public void makeTurn(char[][] board, String input){

    String[] components = input.split("\\s*,\\s*");
    if (components.length == 3) {
      try {
        int row = Integer.parseInt(components[0]);
        int col = Integer.parseInt(components[1]);
        char type = components[2].toUpperCase().charAt(0);

        //check if the move is valid
        if (isValidMove(board, row, col, type)) {
          board[row][col] = type;
          printBoard(board);
          player = !player;
          freeSpace--;
        } else {
          System.out.println("Invalid input. Be sure to enter correct values.");
        }
      } catch (NumberFormatException e) {
        System.out.println("Error during number conversion.");
      }
    } else {
      System.out.println("""
          Invalid input. Be sure to enter three comma-separated components.\s
          The first two number for row and column (from 0 to 5) and third the symbol.
          Here an example [2,3,x]""");
    }

  }

  //check if the coordinates are ok, the cell is empty and the symbol correct
  private static boolean isValidMove(char[][] board, int row, int col, char type) {

    boolean isValid = row >= 0 && row < 6 && col >= 0 && col < 6 && (type == 'X' || type == 'O');
    if(isValid){
      if(board[row][col] != ' '){
        isValid = false;
      }
    }
    return isValid;

  }

  //check if the are five straight symbols for row, col and diag
  public boolean checkWin(){

    if(checkRow()){
      player = true;
      return true;
    }
    if(checkCol()){
      player = true;
      return true;
    }
    if(checkDiag()){
      player = true;
      return true;
    }

    player = false;
    return false;

  }

  // check every row for 5 straight symbols
  public boolean checkRow(){

    int length = board.length;
    for (int i = 0; i < length; i++){
      int straightSymbolCounter = 1;
      for (int j = 1; j < length; j++) {
        if ((board[i][j] == board[i][j - 1]) && (board[i][j] != ' ')) {
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

  //check every col for 5 straight symbols
  public boolean checkCol(){

    int length = board.length;
    for (int j = 0; j < length; j++){
      int straightSymbolCounter = 1;
      for (int i = 1; i < length; i++) {
        if ((board[i][j] == board[i-1][j]) && (board[i][j] != ' ')) {
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

  //to check che diagonal use two methods, the first for the for the main diagonal and the two secondary diagonals
  //that check from up left to down right, the second one for the same but opposite diagonals
  private boolean checkDiag() {
    int length = board.length;

    if(checkDiag1(1,1,length)){
      return true;
    }
    if(checkDiag1(1,2,length)){
      return true;
    }
    if(checkDiag1(2,1,length)){
      return true;
    }
    if(checkDiag2(1,4,length)){
      return true;
    }
    if(checkDiag2(1,3,length)){
      return true;
    }
    if(checkDiag2(2,4,length)){
      return true;
    }

      return false;
    }


  public boolean checkDiag1(int indexR, int indexC, int length){

      int straightSymbolCounter = 1;
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

  public void endGame(String winner){

    Random random = new Random();
    int numb = random.nextInt(5) + 1;


    switch (numb) {
      case 1 -> System.out.println("Wow, that was truly a wild game, but in the end, " + winner + " emerged victorious!.");
      case 2 -> System.out.println("What an incredible match! Eventually, " + winner + " triumphed in a surprising turn of events.");
      case 3 -> System.out.println("A truly breathtaking game, but in the end, it was " + winner + " who secured the victory.");
      case 4 -> System.out.println("I just witnessed an epic match, and fortunately, " + winner + " managed to come out on top.");
      case 5 -> System.out.println("It was a thrilling game, but " + winner + " showcased their skill and won decisively.");
    }

  }

  private static void fillBoard(char[][] table) {
    char[][] pattern = {
        {'O', 'O', 'X', 'X', 'O', 'O'},
        {'X', 'X', 'O', 'O', 'X', 'X'},
        {'O', 'O', 'X', 'X', 'O', 'O'},
        {'X', 'X', 'O', 'O', 'X', 'X'},
        {'O', 'O', 'X', 'X', 'O', 'O'},
        {'X', 'X', 'O', 'O', 'X', 'X'}
    };

    for (int i = 0; i < table.length; i++) {
      for (int j = 0; j < table[i].length; j++) {
        table[i][j] = pattern[i % pattern.length][j % pattern[i].length];
        freeSpace--;
        System.out.println(freeSpace);
      }
    }
  }

}



