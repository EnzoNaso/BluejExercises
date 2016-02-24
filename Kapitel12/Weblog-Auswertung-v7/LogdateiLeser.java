import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Eine Klasse zum Lesen von Informationen aus einer
 * Datei, in der Webseitenzugriffe protokolliert
 * sind (eine 'Logdatei').
 * Es wird angenommen, dass ein Eintrag in der Logdatei
 * Datums- und Zeitinformationen in folgendem Format
 * enthält:
 * 
 *   Jahr Monat Tag Stunde Minute
 * 
 * Einträge in der Logdatei sind chronologisch
 * aufsteigend sortiert.
 * 
 * @author David J. Barnes und Michael Kölling.
 * @version 31.07.2011
 */
public class LogdateiLeser implements Iterator<Logeintrag>
{
    // das Format eines Eintrags in der Logdatei
    private String format;
    // ein "Behälter" für die gelesenen Logeintrag-Objekte.
    private ArrayList<Logeintrag> eintraege;
    // ein Iterator über Einträge
    private Iterator<Logeintrag> dataIterator;
    
    /**
     * Erzeuge einen LogdateiLeser, der Einträge aus einer 
     * Logdatei mit dem voreingestellten Namen "weblog.txt"
     * liest.
     */
    public LogdateiLeser() throws IOException
    {
        this("weblog.txt");
    }
    
    /**
     * Erzeuge einen LogdateiLeser, der Einträge aus einer
     * Datei mit dem angegebenen Namen liest.
     * @param dateiname der Name der Logdatei
     */
    public LogdateiLeser(String dateiname) throws IOException
    {
        // das Format der Daten
        format = "Jahr Monat(1-12) Tag Stunde Minute";       
        // ein "Behälter" für die Einträge
        eintraege = new ArrayList<Logeintrag>();
        
        // versuchen, den kompletten Inhalt der Datei einzulesen
        boolean datenGelesen;
        try{
            File d = new File(dateiname);
            Scanner logdatei = new Scanner(d);
            // lesen der Zeilen bis zum Ende der Datei
            while(logdatei.hasNextLine()) {
                String logzeile = logdatei.nextLine();
                // Zerlegen der Zeile und einfügen in die Eintragsliste
                Logeintrag eintrag = new Logeintrag(logzeile);
                eintraege.add(eintrag);
            }
            logdatei.close();
            datenGelesen = true;
        }
        catch(FileNotFoundException e) {
            System.out.println("Datei kann nicht gefunden werden: " + dateiname);
            // Die Exception an den Aufrufer weitergeben
            throw e;
        }
        // Sortiere die Einträge in aufsteigender Folge an.
        Collections.sort(eintraege);
        zuruecksetzen();
    }
    
    /**
     * Hat dieser Leser weitere Einträge zu liefern?
     * @return true falls weitere Daten geliefert werden können,
     *               'false' sonst.
     */
    public boolean hasNext()
    {
        return dataIterator.hasNext();
    }
    
    /**
     * Liefere den nächsten Eintrag aus der Logdatei
     * in Form eines Logeintrag-Objektes.
     * 
     * @return ein Exemplar von Logeintrag, das die Daten
     *          der nächsten Zeile enthält.
     */
    public Logeintrag next()
    {
        return dataIterator.next();
    }
    
    /**
     * Lösche einen Eintrag.
     * Diese Operation ist nicht erlaubt.
     */
    public void remove()
    {
        System.err.println("Das Loeschen von Eintraegen ist nicht erlaubt.");
    }
    
    /**
     * @return einen String, der das Format der Daten in der Protokolldatei erklärt.
     */
    public String gibFormat()
    {
        return format;
    }
    
    /**
     * Setze den logischen Lesezeiger wieder an den Anfang
     * der Daten dieses LogdateiLesers. Die Daten können auf
     * diese Weise mehrfach ausgelesen werden.
     */
    public void zuruecksetzen()
    {
        dataIterator = eintraege.iterator();
    }
    
    /**
     * Gib die Daten dieses Lesers auf die Konsole aus.
     */
    public void datenAusgeben()
    {
        for (Logeintrag eintrag : eintraege) {
            System.out.println(eintrag);
        }
    }
}
