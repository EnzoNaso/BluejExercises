/**
 * Eine Demo der Klassen Verein und Mitgliedschaft.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class VereinDemo
{
    private Verein verein;

    /**
     * Konstruktor für Objekte der Klasse VereinDemo
     */
    public VereinDemo()
    {
        verein = new Verein();
    }

    /**
     * Trage einige Mitglieder in den Verein ein und
     * zeige dann, wie viele es sind.
     * Weitere Beispielaufrufe könnten eingefügt werden, wenn
     * der Verein weitere Funktionalität erhält.
     */
    public void demo()
    {
        verein.beitreten(new Mitgliedschaft("David", 2, 2004));
        verein.beitreten(new Mitgliedschaft("Michael", 1, 2004));
        System.out.println("Der Verein hat " +
                           verein.anzahlMitglieder() +
                           " Mitglieder.");
    }
}
