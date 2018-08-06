import java.util.ArrayList;

/**
 * Speichert Details über Vereinsmitgliedschaften.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Verein
{
    // Definieren Sie alle notwendigen Datenfelder hier...
    private ArrayList<Mitgliedschaft> mitglieder;
    
    /**
     * Konstruktor für Objekte der Klasse Verein
     */
    public Verein()
    {
        // Initialisieren Sie die Datenfelder hier...
        mitglieder = new ArrayList();
    }

    /**
     * Füge ein neues Mitglied in die Mitgliederliste ein.
     * @param mitglied Infos über das einzufügende Mitglied.
     */
    public void beitreten(Mitgliedschaft mitglied)
    {
        mitglieder.add(mitglied);
    }

    /**
     * @return die Anzahl der Mitglieder (Mitgliedschaft-Objekte)
     *         in diesem Verein.
     */
    public int anzahlMitglieder()
    {
        return mitglieder.size();
    }
}
