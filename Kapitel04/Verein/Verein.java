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
    private ArrayList<Mitgliedschaft> mitglieder;
    
    /**
     * Konstruktor f�r Objekte der Klasse Verein
     */
    public Verein()
    {
        // Initialisieren Sie die Datenfelder hier...
        mitglieder = new ArrayList();
    }

    /**
     * F�ge ein neues Mitglied in die Mitgliederliste ein.
     * @param mitglied Infos �ber das einzuf�gende Mitglied.
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
