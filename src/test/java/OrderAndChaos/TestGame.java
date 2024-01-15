package OrderAndChaos;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestGame {

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
  void testCheckRow() {
    Game.uploadBoard(testBoards.get(0));
    assertTrue(game.checkRow());
  }

  @Test
  void testCheckCol() {
    Game.uploadBoard(testBoards.get(1));
    assertTrue(game.checkCol());
  }


  @ParameterizedTest
  @CsvSource({
      "1,1,2",
      "1,2,3",
      "2,1,4"
  })
  void testDiag1(int i, int j, int b) {
    Game.uploadBoard(testBoards.get(b));
    assertTrue(game.checkDiag1(i,j));
  }


  @ParameterizedTest
  @CsvSource({
      "1,4,5",
      "1,3,6",
      "2,4,7"
  })
  void testDiag2(int i, int j, int b) {
    Game.uploadBoard(testBoards.get(b));
    assertTrue(game.checkDiag2(i,j));
  }


  @Test
  void testOrderWin() {
    for (char[][] testBoard : testBoards) {
      Game.uploadBoard(testBoard);
      assertTrue(game.checkWin());
    }
  }


  @Test
  void testChaosWin(){

    Game.uploadBoard(new char[][]{
        {'X', 'X', 'O', 'O', 'X', 'X'},
        {'O', 'O', 'X', 'X', 'O', 'O'},
        {'X', 'X', 'O', 'O', 'X', 'X'},
        {'O', 'O', 'X', 'X', 'O', 'O'},
        {'X', 'X', 'O', 'O', 'X', 'X'},
        {'O', 'O', 'X', 'X', 'O', 'O'},
    });
    assertFalse(game.checkWin());
  }


  @ParameterizedTest
  @ValueSource(strings =
      {"0,2,1,2,2,2,3,2,4,2,5",
      "1,0,1,1,1,2,1,3,1,4,1",
      "2,0,0,1,1,2,2,3,3,4,4",})
  void testWinningStreak(String streakCSV){

    String[] streak = streakCSV.split(",");
    ArrayList<Integer> expectedStreak = new ArrayList<>();
    Game.uploadBoard(testBoards.get(Integer.parseInt(streak[0])));
    for(int i = 1 ; i < streak.length; i++){
      expectedStreak.add(Integer.valueOf(streak[i]));
    }
      game.checkWin();
      assertEquals(expectedStreak, Game.getStreak());
  }
}