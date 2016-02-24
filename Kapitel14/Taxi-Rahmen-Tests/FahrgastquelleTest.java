import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Die Test-Klasse FahrgastquelleTest.
 *
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class FahrgastquelleTest 
{
    private Fahrgastquelle quelle;
    
    /**
     * Konstruktor für die Test-Klasse FahrgastquelleTest
     */
    public FahrgastquelleTest()
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
        Taxiunternehmen unternehmen = new Taxiunternehmen();
        quelle = new Fahrgastquelle(unternehmen);
    }

    /**
     * Gibt das Testgerüst wieder frei.
     *
     * Wird nach jeder Testfall-Methode aufgerufen.
     */
    @After
    protected void tearDown()
    {
        quelle = null;
    }

    /**
     * Test der erfolgreichen Abholung eines Fahrgastes.
     */
    @Test
    public void testAbholung()
    {
        assertEquals(true, quelle.erbitteAbholung());
    }

}
