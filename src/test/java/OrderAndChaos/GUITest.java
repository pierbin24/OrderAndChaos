package OrderAndChaos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GUITest {


  @Test
  public void testChooseSymbol() {
    // Creazione di un'istanza di GameGUI (assicurati di inizializzare correttamente gameGUI)
    GameGUI gameGUI = new GameGUI();

    // Simulazione dell'input dell'utente (puoi utilizzare una libreria come Mockito per simulare input)
    // In questo esempio, sto supponendo che il simbolo 'X' venga scelto
    char symbol = gameGUI.chooseSymbol();

    // Verifica che il simbolo scelto sia quello atteso (nel caso di questo esempio, 'X')
    assertEquals('X', symbol);
    // Puoi sostituire 'X' con il simbolo effettivamente atteso in base alla tua implementazione
  }

  @Test
  public void testResetGame() {
    // Creazione di un'istanza di GameGUI (assicurati di inizializzare correttamente gameGUI)
    GameGUI gameGUI = new GameGUI();

    // Simulazione di una partita e chiamata a resetGame()
    // In questo esempio, resetto il gioco e verifico che lo stato sia iniziale
    gameGUI.resetGame();

    // Verifica che lo stato del gioco sia quello atteso dopo il reset
    // Ad esempio, puoi verificare che tutti i pulsanti siano vuoti e che il titolo sia corretto
    // Puoi aggiungere ulteriori verifiche in base alla tua implementazione
    // assertTrue(gameGUI.isInitialState()); // Sostituisci con il tuo metodo per verificare lo stato iniziale
  }
}
