import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Die Test-Klasse TaxiTest.
 *
 * @author  (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public class TaxiTest 
{
    private Taxi taxi;
    
    /**
     * Konstruktor für die Test-Klasse TaxiTest
     */
    public TaxiTest()
    {
    }

    /**
     *  Erzeuge ein Taxi.
     *
     * Wird vor jeder Testfall-Methode aufgerufen.
     */
    @Before
    protected void setUp()
    {
        Taxiunternehmen unternehmen = new Taxiunternehmen();
        Position taxiPosition = new Position();
        taxi = new Taxi(unternehmen, taxiPosition);
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
     * Teste die Erzeugung und den Anfangszustand eines Taxis.
     */
    @Test
    public void testErzeugung()
    {
        assertEquals(true, taxi.istFrei());
    }
    
    /**
     * Teste, ob ein Taxi nicht mehr frei ist, nachdem es
     * einen Fahrgast aufgenommen hat.
     */
    @Test
    public void testAbholung()
    {
        Position abholpunkt = new Position();
        Position ziel = new Position();
        Fahrgast fahrgast = new Fahrgast(abholpunkt, ziel);
        taxi.aufnehmen(fahrgast);
        assertEquals(false, taxi.istFrei());
    }
    
    /**
     * Test, ob ein Taxi wieder frei ist, nachdem es einen
     * Fahrgast abgesetzt hat.
     */
    @Test
    public void testAbsetzen()
    {
        testAbholung();
        taxi.fahrgastAbsetzen();
        assertEquals(true, taxi.istFrei());
    }
    
}
