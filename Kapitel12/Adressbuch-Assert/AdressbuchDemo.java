/**
 * Eine Demo-Klasse für Adressbuch.
 * Enthält auch einige einfache Testmethoden. Mit ihnen 
 * wird bei einigen Grundoperationen eines Adressbuchs 
 * überprüft, ob Zusicherungsfehler ausgelöst werden.
 *
 * @author  David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class AdressbuchDemo
{
    // Einige Beispielkontakte.
    private Kontakt[] testdaten;
    // Ein Adressbuch mit Testdaten.
    private Adressbuch buch;
    // Eine Kopie eines vorhandenen Kontakts.
    private Kontakt vorhandenerKontakt;
    // Ein weiterer Kontakt.
    private Kontakt weitererKontakt;

    /**
     * Erstellen der Testdaten.
     */
    public AdressbuchDemo()
    {
        // Einige Testdaten zum Speichern im Adressbuch.
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
        buch = new Adressbuch();
        // Erzeugen einer Kopie von einem der Kontakte.
        Kontakt erster = testdaten[0];
        vorhandenerKontakt = new Kontakt(erster.gibName(), erster.gibTelefon(),
                                     erster.gibAdresse());
        // Erzeugen eines weiteren Kontaktes, der noch nicht im Adressbuch
        // eingetragen ist.
        weitererKontakt = new Kontakt("jan", "08459 900000", "adresse 9");
    }
    
    /**
     * Einrichten eines neues Adressbuches mit Beispieldaten.
     */
    public void einrichten()
    {
        buch = new Adressbuch();
        for(Kontakt kontakt : testdaten) {
            buch.neuerKontakt(kontakt);
        }
    }
    
    /**
     * Ein einfacher Test von neuerKontakt um zu prüfen, ob
     * eine Zusicherung fehlschlägt.
     */
    public void testEinfuegen()
    {
        einrichten();
        buch.neuerKontakt(weitererKontakt);
    }
    
    /**
     * Ein einfacher Test von entferneKontakt um zu prüfen,
     * ob eine Zusicherung fehlschlägt.
     */
    public void testEntfernen()
    {
        einrichten();
        buch.entferneKontakt(vorhandenerKontakt.gibName());
    }
    
    /**
     * Ein einfacher Test von aendereKontakt um zu prüfen,
     * ob eine Zusicherung fehlschlägt.
     */
    public void testAendern()
    {
        einrichten();
        Kontakt abgewandelterKontakt = erzeugeAbgewandeltenKontakt();
            
        buch.aendereKontakt(vorhandenerKontakt.gibName(),
                           abgewandelterKontakt);
    }
    
    /**
     * Füge zum zweiten Mail einen Eintrag mit bereits vorhandenem
     * Namen und vorhandener Telefonnummer ein.
     * Dies sollte einen AssertionError auslösen.
     */
    public void testFehlerEinfuegen()
    {
        einrichten();
        buch.neuerKontakt(erzeugeAbgewandeltenKontakt());
    }

    /**
     * @return das Beispieladressbuch.
     */
    public Adressbuch gibAdressbuch()
    {
        return buch;
    }

    /**
     * @return einen Kontakt, der bereits im Adressbuch
     *         eingetragen ist.
     */
    public Kontakt gibVorhandenenKontakt()
    {
        return vorhandenerKontakt;
    }

    /**
     * @return einen neuen Kontakt, der ursprünglich nicht
     *         im Adressbuch eingetragen ist.
     */
    public Kontakt gibWeiterenKontakt()
    {
        return weitererKontakt;
    }
    
    /**
     * Erzeuge einen Kontakt auf Basis eines existierenden Eintrags.
     */
    private Kontakt erzeugeAbgewandeltenKontakt()
    {
        return new Kontakt(vorhandenerKontakt.gibName(),
                           vorhandenerKontakt.gibTelefon(),
                           vorhandenerKontakt.gibAdresse() + "X");
    }
}