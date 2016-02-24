import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test der Klasse Position.
 *
 * @author  David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class PositionTest 
{
    /**
     * Konstruktor für die Test-Klasse PositionTest
     */
    public PositionTest()
    {
    }

    /**
     *  Setzt das Testgerüst für den Test.
     *
     * Wird vor jeder Testfall-Methode aufgerufen.
     */
    @Before
    protected void setUp()
    {
    }

    /**
     * Gibt das Testgerüst wieder frei.
     *
     * Wird nach jeder Testfall-Methode aufgerufen.
     */
    @After
    protected void tearDown()
    {
    }

    /**
     * Test der Methode schritteZu der Klasse Position.
     */
    @Test
    public void testSchritteZu()
    {
        boolean ok = true;
        int startX = 10, startY = 10;
        Position startPosition = new Position(startX, startY);

        // Berechne die Entfernung von der Startposition zu 
        // angrenzenden Positionen. Die Entfernung sollte
        // immer gleich dem abstand sein.
        int abstand = 5;
        assertEquals(startPosition.schritteZu(
            new Position(startX, startY + abstand)), abstand);
        assertEquals(startPosition.schritteZu(
            new Position(startX + abstand, startY)), abstand);
        assertEquals(startPosition.schritteZu(
            new Position(startX + 1, startY + abstand)), abstand);
        assertEquals(startPosition.schritteZu(
            new Position(startX + abstand, startY + 1)), abstand);
        assertEquals(startPosition.schritteZu(
            new Position(startX + abstand, startY + abstand)), abstand);
        assertEquals(startPosition.schritteZu(
            new Position(startX + abstand - 1, startY + abstand)), abstand);
        assertEquals(startPosition.schritteZu(
            new Position(startX + abstand, startY + abstand - 1)), abstand);
    }
    
    /**
     * Tests der Methode naechstePosition der Klasse Position.
     */
    @Test
    public void testeAngrenzendePositionen()
    {
        int startX = 10, startY = 10;
        Position startPosition = new Position(startX, startY);
        
        // Teste angrenzende Positionen.
        // (x, y) Abstände für jede Richtung von (startX, startY).
        int[][] abstaende = {
            { 0, 1, 0, 1, -1, 0, -1, 1, -1},
            { 0, 0, 1, 1, 0, -1, -1, -1, 1},
        };

        for(int i = 0; i < abstaende[0].length; i++) {
            Position ziel = new Position(startX + abstaende[0][i],
                                       startY + abstaende[1][i]);
            Position naechstePosition = startPosition.naechstePosition(ziel);
            assertEquals(naechstePosition.equals(ziel), true);
        }
    }
    
    @Test
    public void testeNichtAngrenzendePositionen()
    {
        int startX = 10, startY = 10;
        Position startPosition = new Position(startX, startY);
        // (x, y) Abstände für jede Richtung von (startX, startY).
        int[][] abstaende = {
            { 0, 1, 0, 1, -1, 0, -1, 1, -1},
            { 0, 0, 1, 1, 0, -1, -1, -1, 1},
        };
        // Test mit Zielen, die nicht angrenzend sind.
        // Verschiedene Werte für xDist und yDist für vielfältigere Test.
        int xDist = 7;
        int yDist = 3;
        for(int i = 0; i < abstaende[0].length; i++) {
            Position ziel = new Position(startX + xDist * abstaende[0][i],
                                       startY + yDist * abstaende[1][i]);
            Position erwarteteNaechstePosition =
                        new Position(startX + abstaende[0][i],
                                     startY + abstaende[1][i]);
            Position nextPosition = startPosition.naechstePosition(ziel);            
            assertEquals(erwarteteNaechstePosition.equals(nextPosition), true);
        }
    }
}
