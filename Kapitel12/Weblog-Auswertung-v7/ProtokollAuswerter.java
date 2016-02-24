import java.io.IOException;

/**
 * Eine Klasse, die das Protokoll eines Webservers
 * in Hinsicht auf Zugriffe pro Stunde auswertet.
 * 
 * @author David J. Barnes und Michael Kölling.
 * @version 31.07.2011
 */
public class ProtokollAuswerter
{
    // Hier werden die Zugriffe für die Stunden gehalten
    private int[] zugriffeInStunde;
    // Verwendung eines LogdateiLesers
    private LogdateiLeser leser;
    // Der Name der Logdatei.
    private String logdatei;

    /**
     * Erzeuge ein Exemplar, das die Zugriffe in den
     * Stunden eines Tages zählt.
     */
    public ProtokollAuswerter()
    { 
        // das Array-Objekt erzeugen, das die Zugriffe
        // in den einzelnen Stunden eines Tages zählt.
        zugriffeInStunde = new int[24];
        leser = null;
        logdatei = null;
    }

    /**
     * Analysiere die in der Logdatei erfassten Zugriffsdaten.
      * @param dateiname der Name der Logdatei.
    */
    public void analysiereStundendaten(String dateiname)
    {
        try {
            // Den Auswerter als Vorbereitung auf die neuen Daten zurücksetzen.
            zuruecksetzen();
            logdatei = dateiname;
            // Erzeuge den Leser und lese die Daten ein.
            leser = new LogdateiLeser(dateiname);            
            while(leser.hasNext()) {
                Logeintrag eintrag = leser.next();
                int stunde = eintrag.gibStunde();
                zugriffeInStunde[stunde]++;
            }
        }
        catch(IOException e) {
            System.out.println("Auswertung nicht moeglich " + dateiname);
            // Den Auswerter zurcksetzen, um keinen undefinierten Teilzustand zu hinterlassen.
            zuruecksetzen();
        }
    }

    /**
     * Gib die Anzahl der Zugriffe in den Stunden eines
     * Tages nach Stunden sortiert auf der Konsole aus.
     * Diese Werte sollten zuerst mit einem Aufruf von 
     * 'analysiereStundendaten' berechnet werden.
     */
    public void stundendatenAusgeben()
    {
        System.out.println("Stunde: Zugriffe");
        for(int stunde = 0; stunde < zugriffeInStunde.length; stunde++) {
            System.out.println(stunde + ": " + zugriffeInStunde[stunde]);
        }
    }
    
    /**
     * Gib die Zeilen der Logdatei auf der Konsole aus.
     */
    public void logdateiAusgeben()
    {
        if(leser != null) {
            System.out.println("Logdatei: " + logdatei);
            leser.datenAusgeben();
        }
        else {
            System.out.println("Aktuell gibt es keine auszugebenden Daten.");
        }
    }
    
    /**
     * Den Auswerter auf den datenlosen Zustand zur�cksetzen.
     */
    private void zuruecksetzen()
    {
        for(int i = 0; i < zugriffeInStunde.length; i++) {
            zugriffeInStunde[i] = 0;
        }
        leser = null;
        logdatei = null;
    }    
}
