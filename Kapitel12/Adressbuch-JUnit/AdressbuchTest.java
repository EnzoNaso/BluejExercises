import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Skizze einer Testklasse für Adressbuch.
 * Es werden weder alle Methoden getestet noch sind die
 * Tests der Methoden, die getestet werden, gründlich.
 *
 * @author  David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class AdressbuchTest 
{
    // Einige Beispielkontakte.
    private Kontakt[] testdaten;
    // Ein Adressbuch mit Testdaten.
    private Adressbuch buch;
    // Eine Kopie eines vorhandenen Kontakts.
    private Kontakt vorhandenerKontakt;
    // Ein weiterer Kontakt.
    private Kontakt weitererKontakt;
    // Eine Abwandlung eines vorhandenen Kontakts.
    private Kontakt abgewandelterKontakt;

    /**
     * Erstellen der Testdaten.
     */
    public AdressbuchTest()
    {
        // Einige Testkontakte zum Speichern im Adressbuch.
        testdaten = new Kontakt[] {
            new Kontakt("david",   "08459 100000", "adresse 1"),
            new Kontakt("michael", "08459 200000", "adresse 2"),
            new Kontakt("john",    "08459 300000", "adresse 3"),
            new Kontakt("heike",   "08459 400000", "adresse 4"),
            new Kontakt("emma",    "08459 500000", "adresse 5"),
            new Kontakt("simone",  "08459 600000", "adresse 6"),
            new Kontakt("chris",   "08459 700000", "adresse 7"),
            new Kontakt("axel",    "08459 800000", "adresse 8"),
        };
    }

    /**
     *  Setzt das Testgerüst fuer den Test.
     *
     * Wird vor jeder Testfall-Methode aufgerufen.
     */
    @Before
    public void setUp()
    {
        buch = new Adressbuch();
        for(Kontakt kontakt : testdaten) {
            buch.neuerKontakt(kontakt);
        }
        // Erzeugen einer Kopie von einem der Kontakte.
        Kontakt first = testdaten[0];
        vorhandenerKontakt = new Kontakt(first.gibName(), first.gibTelefon(),
                                     first.gibAdresse());
        // Erzeugen eines weiteren Kontaktes, der noch nicht im Adressbuch
        // eingetragen ist.
        weitererKontakt = new Kontakt("jan", "08459 900000", "adresse 9");
        // Ändern der Adresse eines existierenden Kontaktes.
        abgewandelterKontakt = new Kontakt(vorhandenerKontakt.gibName(),
                                           vorhandenerKontakt.gibTelefon(),
                                           vorhandenerKontakt.gibAdresse() + "X");

    }

    /**
     * Gibt das Testgerüst wieder frei.
     *
     * Wird nach jeder Testfall-Methode aufgerufen.
     */
    @After
    public void tearDown()
    {
        buch = null;
        vorhandenerKontakt = null;
        weitererKontakt = null;
        abgewandelterKontakt = null;
    }

    /**
     * Teste, ob der Zähler mit der Anzahl der Beispieleinträge
     * übereinstimmt.
     * Entferne dann einen und prüfe auf dekrementierten Zähler.
     * Füge dann einen ein und prüfe auf inkrementierten Zähler.
     */
    @Test
    public void testGibAnzahlEintraege()
    {
        assertEquals(testdaten.length, buch.gibAnzahlEintraege());
        buch.entferneKontakt(vorhandenerKontakt.gibName());
        assertEquals(testdaten.length - 1, buch.gibAnzahlEintraege());
        buch.neuerKontakt(weitererKontakt);
        assertEquals(testdaten.length, buch.gibAnzahlEintraege());
    }

    /**
     * Teste, ob ein existierender Eintrag gefunden wird und ob ein
     * nicht existierender nicht gefunden wird.
     */
    @Test
    public void testGibKontakt()
    {
        assertEquals(vorhandenerKontakt, buch.gibKontakt(vorhandenerKontakt.gibName()));
        assertNull(buch.gibKontakt(weitererKontakt.gibName()));
    }

    /**
     * Teste, ob Name und Telefonnummer eines eingetragenen Kontakts
     * als Schlüssel registriert sind.
     */
    @Test
    public void testSchluesselBekannt()
    {
        assertEquals(true, buch.schluesselBekannt(vorhandenerKontakt.gibName()));
        assertEquals(true, buch.schluesselBekannt(vorhandenerKontakt.gibTelefon()));
    }

    /**
     * Teste, ob ein weiterer Kontakt eingefügt werden kann.
     */
    @Test
    public void testNeuerKontakt()
    {
        assertEquals(false, buch.schluesselBekannt("jan"));
        buch.neuerKontakt(weitererKontakt);
        assertEquals(true, buch.schluesselBekannt("jan"));
        assertEquals(testdaten.length + 1, buch.gibAnzahlEintraege());
    }

    /**
     * Teste, ob wir einen weiteren Kontakt einfügen und wieder
     * entfernen können.
     */
    @Test
    public void testEntferneKontakt()
    {
        buch.neuerKontakt(weitererKontakt);
        assertEquals(true, buch.schluesselBekannt("jan"));
        buch.entferneKontakt("jan");
        assertEquals(false, buch.schluesselBekannt("jan"));
    }

    /**
     * Teste, ob die Details eines eingetragenen Kontakts geändert
     * werden können.
     */
    @Test
    public void testAendereKontakt()
    {
        assertEquals(vorhandenerKontakt, buch.gibKontakt(vorhandenerKontakt.gibName()));
        buch.aendereKontakt(vorhandenerKontakt.gibName(), abgewandelterKontakt);
        assertEquals(abgewandelterKontakt, buch.gibKontakt(abgewandelterKontakt.gibName()));
    }

    /**
     * Teste, ob die Suche nach einem eingetragenen Kontakt erfolgreich
     * ist. Teste dann, ob die Suche nach einem nicht eingetragenen
     * Kontakt fehlschlägt. Füge dann einen neuen Kontakt ein und teste,
     * ob eine Suche diesen findet.
     */
    @Test
    public void testSuche()
    {
        assertEquals(buch.suche(vorhandenerKontakt.gibName()).length, 1);
        assertEquals(buch.suche(weitererKontakt.gibName()).length, 0);
        buch.neuerKontakt(weitererKontakt);
        assertEquals(buch.suche(weitererKontakt.gibName()).length, 1);
    }
    
    /**
     * Löse einen AssertionError aus, indem ein abgewandelter Kontakt
     * eingefügt wird, statt den Kontakt mit aendereKontakt zu ändern.
     */
    @Test
    public void testNeuerKontaktFehler()
    {
        assertEquals(vorhandenerKontakt, buch.gibKontakt(vorhandenerKontakt.gibName()));
        buch.neuerKontakt(abgewandelterKontakt);
        assertEquals(vorhandenerKontakt, buch.gibKontakt(vorhandenerKontakt.gibName()));
        assertEquals(abgewandelterKontakt, buch.gibKontakt(abgewandelterKontakt.gibName()));
    }
}








