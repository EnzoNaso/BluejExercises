import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
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
    public LogdateiLeser()
    {
        this("weblog.txt");
    }
    
    /**
     * Erzeuge einen LogdateiLeser, der Einträge aus einer
     * Datei mit dem angegebenen Namen liest.
     * @param dateiname der Name der Logdatei
     */
    public LogdateiLeser(String dateiname)
    {
        // das Format der Daten
        format = "Jahr Monat(1-12) Tag Stunde Minute";       
        // ein "Behälter" für die Einträge
        eintraege = new ArrayList<Logeintrag>();
        
        // versuchen, den kompletten Inhalt der Datei einzulesen
        boolean datenGelesen;
        try{
            // lokalisieren der Datei in der aktuellen Umgebung
            URL fileURL = getClass().getClassLoader().getResource(dateiname);
            if(fileURL == null) {
                throw new FileNotFoundException(dateiname);
            }
            Scanner logdatei = new Scanner(new File(fileURL.toURI()));
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
            System.out.println("Problem entdeckt: " + e);
            datenGelesen = false;
        }
        catch(URISyntaxException e) {
            System.out.println("Problem entdeckt: " + e);
            datenGelesen = false;
        }
        // Wenn wir die Logdatei nicht lesen konnten: Simulationsdaten erzeugen.
        if(!datenGelesen) {
            System.out.println("Lesen der Datei schlug fehl: " +
                               dateiname);
            System.out.println("Es werden nun Simulationsdaten verwendet.");
            erzeugeSimulationsdaten(eintraege);
        }
        // Sort the entries into ascending order.
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
     * Lösche einen Eintrag
     * Diese Operation ist nicht erlaubt.
     */
    public void remove()
    {
        System.err.println("Das Löschen von Einträgen ist nicht erlaubt.");
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

    /**
     * Liefere einen Satz von Simulationsdaten.
     * Nebenbemerkung: Um die Erzeugung hier zu vereinfachen,
     * werden keine Tage über den 28sten eines Monats hinaus
     * generiert.
     * @param daten 'Behälter', in dem die erzeugten Daten 
     *              abgelegt werden sollen.
     */
    private void erzeugeSimulationsdaten(ArrayList<Logeintrag> daten)
    {
        // Für jede Dateneinheit (Jahr, Monat, Tag, Stunde, Minute)
        // wird hier der Minimalwert angegeben.
        int[] minimum = { 2006, 1, 1, 0, 0, };
        // Für jede Dateneinheit (Jahr, Monat, Tag, Stunde, Minute)
        // wird hier der gültige Wertebereich angegeben (man beachte,
        // dass für die Tage nur Werte bis 28 gültig sind, um die
        // Erzeugung ungültiger Daten zu vermeiden).
        int[] bereich = { 3, 12, 28, 24, 60 };
        // Wähle einen festen Basiswert für die Erzeugung der
        // Zufallszahlen, damit das Ergebnis reproduzierbar ist.
        Random zufall = new Random(12345);
        // Eine simulierte Zeile wird mit einem StringBuffer gebildet.
        StringBuffer zeile = new StringBuffer();
        // Anzahl der simulierten Zeilen
        int anzahlZeilen = 100;
        // die Anzahl der Dateneinheit pro Zeile
        int einheitenProZeile = minimum.length;
        for(int i = 0; i < anzahlZeilen; i++) {
            for(int j = 0; j < einheitenProZeile; j++) {
                int wert = minimum[j]+zufall.nextInt(bereich[j]);
                zeile.append(wert);
                zeile.append(' ');
            }
            // Konvertiere die Zeile in einen Logeintrag.
            Logeintrag entry = new Logeintrag(zeile.toString());
            daten.add(entry);
            zeile.setLength(0);
        }
    }
}
