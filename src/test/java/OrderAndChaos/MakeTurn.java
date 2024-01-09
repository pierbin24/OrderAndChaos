package OrderAndChaos;

//@ExtendWith(MockitoExtension.class)
public class MakeTurn {

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




}






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



