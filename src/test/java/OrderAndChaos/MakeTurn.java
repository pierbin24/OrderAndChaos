package OrderAndChaos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MakeTurn {

  @Mock
  private Scanner mockScanner;

  private Game game;

  @BeforeEach
  void setUp() {
    game = new Game();
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

}
