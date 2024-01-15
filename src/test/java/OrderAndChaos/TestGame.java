package OrderAndChaos;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestGame {

  Game game;
  char[][] board;
  private static final ArrayList<char[][]> testBoards = new ArrayList<>();


  @BeforeAll
  static void setUpBeforeAll(){

    char[][] testBoard;
    //row
    testBoard = new char[][]{
        {' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' '},
        {' ', 'X', 'X', 'X', 'X', 'X'},
        {' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' '}
    };
    testBoards.add(testBoard);

    //col
    testBoard = new char[][]{
        {' ', 'O', ' ', ' ', ' ', ' '},
        {' ', 'O', ' ', ' ', ' ', ' '},
        {' ', 'O', ' ', ' ', ' ', ' '},
        {' ', 'O', ' ', ' ', ' ', ' '},
        {' ', 'O', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' '}
    };
    testBoards.add(testBoard);

    //diag1
    testBoard = new char[][]{
        {'X', ' ', ' ', ' ', ' ', ' '},
        {' ', 'X', ' ', ' ', ' ', ' '},
        {' ', ' ', 'X', ' ', ' ', ' '},
        {' ', ' ', ' ', 'X', ' ', ' '},
        {' ', ' ', ' ', ' ', 'X', ' '},
        {' ', ' ', ' ', ' ', ' ', ' '}
    };
    testBoards.add(testBoard);

    //diag1
    testBoard = new char[][]{
        {' ', 'O', ' ', ' ', ' ', ' '},
        {' ', ' ', 'O', ' ', ' ', ' '},
        {' ', ' ', ' ', 'O', ' ', ' '},
        {' ', ' ', ' ', ' ', 'O', ' '},
        {' ', ' ', ' ', ' ', ' ', 'O'},
        {' ', ' ', ' ', ' ', ' ', ' '}
    };
    testBoards.add(testBoard);

    //diag1
    testBoard = new char[][]{
        {' ', ' ', ' ', ' ', ' ', ' '},
        {'O', ' ', ' ', ' ', ' ', ' '},
        {' ', 'O', ' ', ' ', ' ', ' '},
        {' ', ' ', 'O', ' ', ' ', ' '},
        {' ', ' ', ' ', 'O', ' ', ' '},
        {' ', ' ', ' ', ' ', 'O', ' '}
    };
    testBoards.add(testBoard);

    //diag2
    testBoard = new char[][]{
        {' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', 'X', ' '},
        {' ', ' ', ' ', 'X', ' ', ' '},
        {' ', ' ', 'X', ' ', ' ', ' '},
        {' ', 'X', ' ', ' ', ' ', ' '},
        {'X', ' ', ' ', ' ', ' ', ' '}
    };
    testBoards.add(testBoard);

    //diag2
    testBoard = new char[][]{
        {' ', ' ', ' ', ' ', 'O', ' '},
        {' ', ' ', ' ', 'O', ' ', ' '},
        {' ', ' ', 'O', ' ', ' ', ' '},
        {' ', 'O', ' ', ' ', ' ', ' '},
        {'O', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' '}
    };
    testBoards.add(testBoard);

    //diag2
    testBoard = new char[][]{
        {' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', 'O'},
        {' ', ' ', ' ', ' ', 'O', ' '},
        {' ', ' ', ' ', 'O', ' ', ' '},
        {' ', ' ', 'O', ' ', ' ', ' '},
        {' ', 'O', ' ', ' ', ' ', ' '}
    };
    testBoards.add(testBoard);

  }

  @BeforeEach
  void setUp() {
    game = new Game();
    board = new char[6][6];
  }

  @Test
  public void testCheckRow() {
    game.uploadBoard(testBoards.get(0));
    //game.board = testBoards.get(0);
    assertTrue(game.checkRow());
  }

  @Test
  public void testCheckCol() {
    game.uploadBoard(testBoards.get(1));
    //game.board = testBoards.get(1);
    assertTrue(game.checkCol());
  }


  @ParameterizedTest
  @CsvSource({
      "1,1,2",
      "1,2,3",
      "2,1,4"
  })
  public void testDiag1(int i, int j, int b) {
    game.uploadBoard(testBoards.get(b));
    //game.board = testBoards.get(b);
    assertTrue(game.checkDiag1(i,j));
  }


  @ParameterizedTest
  @CsvSource({
      "1,4,5",
      "1,3,6",
      "2,4,7"
  })
  public void testDiag2(int i, int j, int b) {
    game.uploadBoard(testBoards.get(b));
    //game.board = testBoards.get(b);
    assertTrue(game.checkDiag2(i,j));
  }


  @Test
  public void testOrderWin() {
    for (char[][] testBoard : testBoards) {
      game.uploadBoard(testBoard);
      //game.board = testBoard;
      assertTrue(game.checkWin());
    }
  }


  @Test
  public void testChaosWin(){

    game.uploadBoard(new char[][]{
        {'X', 'X', 'O', 'O', 'X', 'X'},
        {'O', 'O', 'X', 'X', 'O', 'O'},
        {'X', 'X', 'O', 'O', 'X', 'X'},
        {'O', 'O', 'X', 'X', 'O', 'O'},
        {'X', 'X', 'O', 'O', 'X', 'X'},
        {'O', 'O', 'X', 'X', 'O', 'O'},
    });
    /*
    game.board = new char[][]{
        {'X', 'X', 'O', 'O', 'X', 'X'},
        {'O', 'O', 'X', 'X', 'O', 'O'},
        {'X', 'X', 'O', 'O', 'X', 'X'},
        {'O', 'O', 'X', 'X', 'O', 'O'},
        {'X', 'X', 'O', 'O', 'X', 'X'},
        {'O', 'O', 'X', 'X', 'O', 'O'},
    };
     */

    assertFalse(game.checkWin());
  }


  @ParameterizedTest
  @ValueSource(strings =
      {"0,2,1,2,2,2,3,2,4,2,5",
      "1,0,1,1,1,2,1,3,1,4,1",
      "2,0,0,1,1,2,2,3,3,4,4",})
  public void testWinningStreak(String streakCSV){

    String[] streak = streakCSV.split(",");
    ArrayList<Integer> expectedStreak = new ArrayList<>();
    game.uploadBoard(testBoards.get(Integer.parseInt(streak[0])));
    //game.board = testBoards.get(Integer.parseInt(streak[0]));
    for(int i = 1 ; i < streak.length; i++){
      expectedStreak.add(Integer.valueOf(streak[i]));
    }
      game.checkWin();
      assertEquals(expectedStreak, game.winningStreak);
  }

}