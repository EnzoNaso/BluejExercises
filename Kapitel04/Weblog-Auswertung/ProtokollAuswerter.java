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

    /**
     * Erzeuge ein Exemplar, das die Zugriffe in den
     * Stunden eines Tages zählt.
     */
    public ProtokollAuswerter()
    { 
        // das Array-Objekt erzeugen, das die Zugriffe
        // in den einzelnen Stunden eines Tages zählt.
        zugriffeInStunde = new int[24];
        // Den Leser für die Logdatei erzeugen.
        leser = new LogdateiLeser();
    }

    /**
     * Analysiere die in der Logdatei erfassten Zugriffsdaten.
     */
    public void analysiereStundendaten()
    {
        while(leser.hasNext()) {
            Logeintrag eintrag = leser.next();
            int stunde = eintrag.gibStunde();
            zugriffeInStunde[stunde]++;
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
        leser.datenAusgeben();
    }
}
