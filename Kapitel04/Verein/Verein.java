import java.util.ArrayList;
import java.util.Iterator;

/**
 * Speichert Details ï¿½ber Vereinsmitgliedschaften.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Verein
{
    // Definieren Sie alle notwendigen Datenfelder hier...
    private ArrayList<Mitgliedschaft> mitglieder;
    
    /**
     * Konstruktor fï¿½r Objekte der Klasse Verein
     */
    public Verein()
    {
        // Initialisieren Sie die Datenfelder hier...
        mitglieder = new ArrayList();
    }

    /**
     * Fï¿½ge ein neues Mitglied in die Mitgliederliste ein.
     * @param mitglied Infos ï¿½ber das einzufï¿½gende Mitglied.
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
    
    /** 
     * Liefere die Anzahl der Mitglieder, die im angegebenen Monat 
     * Mitglied geworden sind. 
     * @param monat der Monat des Beitritts. 
     * @return die Anzahl der Mitglieder, die im Monat beigetreten sind. 
    */ 
    public int beigetretenInMonat(int monat)
    {
        if (monat < 1 || monat > 12)
        {
            System.out.println("Ein Monat " + monat + " gibt es nicht!");
            return 0;
        }
        
        int count = 0;
        for (Mitgliedschaft m : mitglieder)
        {
            if (m.gibMonat() == monat)
            {
                count++;
            }
        }
        return count;
    }
    
    
    /** 
     * Entferne alle Mitglieder aus der Sammlung des Vereins, die im 
     * angegebenen Monat Mitglied geworden sind, und liefere sie in 
     * einem getrennten Sammlungsobjekt zurück. 
     * @param monat der Monat des Beitritts. 
     * @param jahr das Jahr des Beitritts. 
     * @return die neuen Mitglieder des gegebenen Monats und Jahres. 
    */
    public ArrayList<Mitgliedschaft> entfernen(int monat, int jahr)
    {
        if (monat < 1 || monat > 12)
        {
            System.out.println("Ein Monat " + monat + " gibt es nicht!");
            return new ArrayList<Mitgliedschaft>();
        }
        
        ArrayList<Mitgliedschaft> entferneteMitglieder = new ArrayList();
        
        Iterator<Mitgliedschaft> it = mitglieder.iterator();
        
        while (it.hasNext())
        {
            Mitgliedschaft m = it.next();
            if (m.gibMonat() == monat && m.gibJahr() == jahr)
            {
                entferneteMitglieder.add(m);
                it.remove();
            }
            
        }
        return entferneteMitglieder;
    }
}
