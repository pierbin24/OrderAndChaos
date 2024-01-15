package OrderAndChaos;

import java.util.ArrayList;
import java.util.List;

public class Game {

  //false - chaos / true - order
  private static final int SIZE = 6;
  private static char[][] board = new char[SIZE][SIZE];
  private static int freeSpace = 36;
  public List<Integer> winningStreak = new ArrayList<>();


  public static void updateBoard(int i, int j, char c){
    board[i][j] = c;
  }

  public static void uploadBoard(char[][] testBoard){
    board = testBoard;
  }

  public int getFreeSpace(){
    return freeSpace;
  }

  public static void setFreeSpace(int value){
    freeSpace = value;
  }



  //check if the are five straight symbols for row, col and diag
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

  // check every row for 5 straight symbols
  public boolean checkRow(){

    for (int i = 0; i < SIZE; i++){
      int straightSymbolCounter = 1;
      for (int j = 1; j < SIZE; j++) {
        if ((board[i][j] == board[i][j - 1]) && (board[i][j] != ' ')) {
          straightSymbolCounter++;
          winningStreak.add(i);
          winningStreak.add(j-1);
          if (straightSymbolCounter == 5) {
            winningStreak.add(i);
            winningStreak.add(j);
            return true;
          }
        } else {
          winningStreak.clear();
          straightSymbolCounter = 1;
        }
      }
    }
    return false;
  }

  //check every col for 5 straight symbols
  public boolean checkCol(){

    for (int j = 0; j < SIZE; j++){
      int straightSymbolCounter = 1;
      for (int i = 1; i < SIZE; i++) {
        if ((board[i][j] == board[i-1][j]) && (board[i][j] != ' ')) {
          straightSymbolCounter++;
          winningStreak.add(i-1);
          winningStreak.add(j);
          if (straightSymbolCounter == 5) {
            winningStreak.add(i);
            winningStreak.add(j);
            return true;
          }
        } else {
          winningStreak.clear();
          straightSymbolCounter = 1;
        }
      }
    }
    return false;

  }

  //to check che diagonal use two methods, the first for the for the main diagonal and the two secondary diagonals
  //that check from up left to down right, the second one for the same but opposite diagonals
  private boolean checkDiag() {

    if(checkDiag1(1,1)){
      return true;
    }
    if(checkDiag1(1,2)){
      return true;
    }
    if(checkDiag1(2,1)){
      return true;
    }
    if(checkDiag2(1,4)){
      return true;
    }
    if(checkDiag2(1,3)){
      return true;
    }
    if(checkDiag2(2,4)){
      return true;
    }

      return false;
    }


  public boolean checkDiag1(int indexR, int indexC){

      int straightSymbolCounter = 1;
      for (int i = indexR, j = indexC; i < SIZE && j < SIZE; i++, j++) {
          if (board[i][j] == ' ') {
            straightSymbolCounter = 1;
          } else {
            if (board[i][j] == board[i - 1][j - 1]) {
              straightSymbolCounter++;
              winningStreak.add(i-1);
              winningStreak.add(j-1);
              if (straightSymbolCounter == 5) {
                winningStreak.add(i);
                winningStreak.add(j);
                return true;
              }
            } else {
              winningStreak.clear();
              straightSymbolCounter = 1;
            }
          }
      }
    return false;
    }

  public boolean checkDiag2(int indexR, int indexC){

    int straightSymbolCounter = 1;
    for (int i = indexR, j = indexC; i < SIZE && j >= 0; i++, j--) {
      if (board[i][j] == ' ') {
        straightSymbolCounter = 1;
      } else {
        if (board[i][j] == board[i - 1][j + 1]) {
          straightSymbolCounter++;
          winningStreak.add(i-1);
          winningStreak.add(j+1);
          if (straightSymbolCounter == 5) {
            winningStreak.add(i);
            winningStreak.add(j);
            return true;
          }
        } else {
          winningStreak.clear();
          straightSymbolCounter = 1;
        }
      }
    }
    return false;
  }

}