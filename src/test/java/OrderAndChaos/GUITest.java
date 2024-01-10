package OrderAndChaos;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GUITest {

  GameGUI gameGUI;

  @BeforeEach
  void setUp() {
    gameGUI = new GameGUI();
  }

  @Test
  public void testClearBoard(){

    gameGUI.game.board = new char[][]{
        {'X', 'X', 'O', 'O', 'X', 'X'},
        {'O', 'O', 'X', 'X', 'O', 'O'},
        {'X', 'X', 'O', 'O', 'X', 'X'},
        {'O', 'O', 'X', 'X', 'O', 'O'},
        {'X', 'X', 'O', 'O', 'X', 'X'},
        {'O', 'O', 'X', 'X', 'O', 'O'},
    };
    gameGUI.game.freeSpace = 0;

    gameGUI.clearBoard();

    assertEquals(36, gameGUI.game.freeSpace); // Verifica lo spazio libero

    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 6; j++) {
        assertEquals("", gameGUI.buttons[i][j].getText());
        assertEquals(' ', gameGUI.game.board[i][j]);

      }
    }

  }

  /*
    public void clearBoard(){

    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 6; j++) {
        buttons[i][j].setOpaque(false);
        buttons[i][j].setText("");
        game.board[i][j] = ' ';
      }
    }
  }
   */
}