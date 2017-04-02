import java.util.ArrayList;

/**
 * Speichert Details �ber Vereinsmitgliedschaften.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Verein
{
    // Definieren Sie alle notwendigen Datenfelder hier...
    private ArrayList<Mitgliedschaft> mitgliedschaften;
    
    /**
     * Konstruktor f�r Objekte der Klasse Verein
     */
    public Verein()
    {
        // Initialisieren Sie die Datenfelder hier...
        mitgliedschaften = new ArrayList<Mitgliedschaft>();
    }

    /**
     * F�ge ein neues Mitglied in die Mitgliederliste ein.
     * @param mitglied Infos �ber das einzuf�gende Mitglied.
     */
    public void beitreten(Mitgliedschaft mitglied)
    {
        mitgliedschaften.add(mitglied);
    }

    /**
     * @return die Anzahl der Mitglieder (Mitgliedschaft-Objekte)
     *         in diesem Verein.
     */
    public int anzahlMitglieder()
    {
        return mitgliedschaften.size();
    }
}
