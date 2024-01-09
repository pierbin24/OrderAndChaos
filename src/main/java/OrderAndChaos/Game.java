package OrderAndChaos;

import java.util.ArrayList;

public class Game {

  //false - chaos / true - order
  public char[][] board = new char[6][6];
  public int freeSpace = 36;
  public ArrayList<Integer> winningStreak = new ArrayList<>();

  //check if the are five straight symbols for row, col and diag
  public boolean checkWin(){

    if(checkRow()){
      System.out.println(winningStreak);
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

    int length = board.length;
    for (int i = 0; i < length; i++){
      int straightSymbolCounter = 1;
      for (int j = 1; j < length; j++) {
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

    int length = board.length;
    for (int j = 0; j < length; j++){
      int straightSymbolCounter = 1;
      for (int i = 1; i < length; i++) {
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

  public boolean checkDiag2(int indexR, int indexC, int length){

    int straightSymbolCounter = 1;
    for (int i = indexR, j = indexC; i < length && j >= 0; i++, j--) {
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