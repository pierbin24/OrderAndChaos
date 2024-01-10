package OrderAndChaos;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckWin {

  Game game;
  char[][] board;
  private static ArrayList<char[][]> testBoards = new ArrayList<>();


  @BeforeAll
  static void setUpBeforeAll(){

    char[][] testBoard = new char[6][6];
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
    game.board = testBoards.get(0);
    assertTrue(game.checkRow());
  }


  @Test
  public void testCheckCol() {
    game.board = testBoards.get(1);
    assertTrue(game.checkCol());
  }


  @ParameterizedTest
  @CsvSource({
      "1,1,2",
      "1,2,3",
      "2,1,4"
  })
  public void testDiag1(int i, int j, int b) {
    game.board = testBoards.get(b);
    assertTrue(game.checkDiag1(i,j));
  }


  @ParameterizedTest
  @CsvSource({
      "1,4,5",
      "1,3,6",
      "2,4,7"
  })
  public void testDiag2(int i, int j, int b) {
    game.board = testBoards.get(b);
    assertTrue(game.checkDiag2(i,j));
  }


  @ParameterizedTest
  @MethodSource("provideWinningTestCases")
  public void testOrderWin(char[][] board) {
    game.board = board;
    assertTrue(game.checkWin());
  }

  private static Stream<Arguments> provideWinningTestCases() {
    Stream.Builder<Arguments> builder = Stream.builder();

    for (int i = 0; i < 8; i++) {
      builder.add(Arguments.of((Object) testBoards.get(i)));
    }

    return builder.build();
  }


  @Test
  public void testChaosWin(){
    game.board = new char[][]{
        {'X', 'X', 'O', 'O', 'X', 'X'},
        {'O', 'O', 'X', 'X', 'O', 'O'},
        {'X', 'X', 'O', 'O', 'X', 'X'},
        {'O', 'O', 'X', 'X', 'O', 'O'},
        {'X', 'X', 'O', 'O', 'X', 'X'},
        {'O', 'O', 'X', 'X', 'O', 'O'},
    };
    assertFalse(game.checkWin());
  }


  @ParameterizedTest
  @CsvSource({
      "0,2,1,2,2,2,3,2,4,2,5",
      "1,1,0,1,1,1,2,1,3,1,4",
      "2,0,0,1,1,2,2,3,3,4,4",
  })
  public void testWinningStreak(){

  }


}