package OrderAndChaos;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

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
        {' ', 'O', ' ', ' ', ' ', ' '}
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

}



//@ExtendWith(MockitoExtension.class)


  //@Mock
  //private Scanner mockScanner;

  /*

    Game game;
  char[][] board;

  @BeforeEach
  void setUp() {
    game = new Game();
    board = new char[6][6];
    game.initializeBoard(board);
  }


  @ParameterizedTest
  @CsvSource({
      "1, 2, X",
      "3, 4, O",
      "5, 5, X",
      // Aggiungi altri casi di test come necessario
  })
  void makeTurn_ValidInput(int row, int col, char type) {
    game.makeTurn(board, String.format("%d,%d,%s", row, col, type));

    assertEquals(type, board[row][col]);
    // Assicurati che il metodo isValidMove sia chiamato con gli argomenti corretti
    // Assicurati che il metodo printBoard sia chiamato con il tuo stato atteso del tavolo
    // Altri controlli che desideri fare
  }

  @ParameterizedTest
  @CsvSource({
      "invalid input",
      "7, 8",
      "X, 2, 3",
      "0, 6, X"
  })
  void makeTurn_InvalidInput(String input) {

    game.makeTurn(board, input);

    // Verifica che la matrice board sia rimasta invariata (nessun aggiornamento)
    char[][] expectedBoard = new char[6][6];
    game.initializeBoard(expectedBoard);
    assertArrayEquals(expectedBoard, board);
  }

   */











  /*

  @Test
  void makeTurn_ValidInput_UpdatesBoard() {
    char[][] board = new char[6][6];
    when(mockScanner.nextLine()).thenReturn("1,2,X");
    game.makeTurn(board, mockScanner, true);
    assertEquals('X', board[1][2]);
    verify(mockScanner, times(1)).nextLine();
  }
  @Test
  void makeTurn_ValidInput_UpdatesBoard() {
    char[][] board = new char[6][6];
    when(mockScanner.nextLine()).thenReturn("1,2,X");
    game.makeTurn(board, mockScanner, true);
    assertEquals('X', board[1][2]);
    verify(mockScanner, times(1)).nextLine();
  }

    @Test
  void makeTurn_InvalidInput_PrintsErrorMessage() {
    char[][] board = new char[6][6];
    when(mockScanner.nextLine()).thenReturn("invalid input");
    game.makeTurn(board, mockScanner, true);
    verify(mockScanner, times(1)).nextLine();
  }

  @Test
  void makeTurn_ExceptionDuringConversion_PrintsErrorMessage() {
    char[][] board = new char[6][6];
    when(mockScanner.nextLine()).thenReturn("1,2,invalid");
    assertThrows(NumberFormatException.class, () -> game.makeTurn(board, mockScanner, true));
    verify(mockScanner, times(1)).nextLine();
  }
   */