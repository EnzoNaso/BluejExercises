import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Die Testklasse ProduktTest.
 *
 * @author  mik
 * @version 0.1
 */
public class ProduktTest
{
    /**
     * Standardkonstruktor für die Testklasse ProduktTest
     */
    public ProduktTest()
    {
    }

    /**
     * Setzt das Testgerüst fuer den Test.
     *
     * Wird vor jeder Testfallmethode aufgerufen.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Abschluss.
     *
     * Wird  nach jeder Testfallmethode aufgerufen.
     */
    @After
    public void tearDown()
    {
    }

    /**
     * Teste, ob ein Kommentar hinzugefügt werden kann, und ob der Kommentarzähler anschließend korrekt ist.
     */
    @Test
    public void testKommentarHinzufuegen()
    {
        Produkt produkt1 = new Produkt("Java fuer komplette Idioten", 21998);
        assertEquals(true, produkt1.kommentarHinzufuegen("James Duckling", "Geniales Buch. All mein Java-Wissen stammt aus diesem Buch.", 4));
        assertEquals(1, produkt1.gibAnzahlKommentare());
    }

    /**
     * Teste, ob ein Kommentar mit illegaler Bewertung abgelehnt wird.
     */
    @Test
    public void testUngueltigeBewertung()
    {
        Produkt produkt1 = new Produkt("Java fuer komplette Idioten, Band 2", 19900);
        assertEquals(false, produkt1.kommentarHinzufuegen("Joshua Black", "Das Geld nicht wert. Die Schrift ist zu klein.", -5));
    }

    /**
     * Teste, ob ein Produkt korrekt initialisiert wird (Name und Preis).
     */
    @Test
    public void testInit()
    {
        Produkt produkt1 = new Produkt("Testname", 1000);
        assertEquals("Testname", produkt1.gibName());
        assertEquals(1000, produkt1.gibPreis());
    }
}



