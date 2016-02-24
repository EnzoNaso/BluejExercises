import java.util.Calendar;

/**
 * Ein Logeintrag repräsentiert die Daten einer
 * Zeile in der Logdatei eines Webservers.
 * Die einzelnen Werte werden über sondierende
 * Operationen wie gibStunde() und gibMinute()
 * verfügbar gemacht.
 * 
 * @author David J. Barnes und Michael Kölling.
 * @version 31.07.2011
 */
public class Logeintrag implements Comparable<Logeintrag>
{
    // Array, in dem die Werte einer Logzeile gehalten werden
    private int[] logzeilenwerte;
    // das äquivalente Calendar-Objekt für die Zeit der Eintragung
    private Calendar wann;
    // welcher Index liefert welchen Wert aus der Zeile
    private static final int JAHR = 0, MONAT = 1, TAG = 2,
                             STUNDE = 3, MINUTE = 4;
    // Die Anzahl der Felder. Wenn weitere Felder hinzugefügt werden, beispielsweise für
    // Sekunden oder einen Statuscode, muss dieser Wert entsprechend erhöht werden.
    private static final int ANZAHL_FELDER = 5;
                      
    /**
     * Zerteile eine Logzeile so, dass die einzelnen
     * Werte verfügbar werden.
     * @param logzeile eine einzelne Zeile aus der Logdatei.
               Die Zeile sollte folgendes Format haben:
               jahr monat tag stunde minute etc.
     */
    public Logeintrag(String logzeile)
    {
        // das Array für die Werte einer Zeile anlegen
        logzeilenwerte = new int[ANZAHL_FELDER];
        // die Werte aus der Zeile auslesen
        LogdateiZeilenzerleger scanner = new LogdateiZeilenzerleger();
        scanner.zerlegen(logzeile,logzeilenwerte);
        setzeWann();
    }
    
    /**
     * Erzeuge aus den einzelnen Komponenten einen Logeintrag
     * @param jahr das Jahr
     * @param monat der Monat (1-12)
     * @param tag der Tag (1-31)
     * @param stunde die Stunde (0-23)
     * @param minute die Minute (0-59)
     * @return den Stunden-Wert eines Logeintrags.
     */
    public Logeintrag(int jahr, int monat, int tag, int stunde, int minute)
    {
        // das Array für die Werte einer Zeile anlegen
        logzeilenwerte = new int[ANZAHL_FELDER];
        logzeilenwerte[JAHR] = jahr;
        logzeilenwerte[MONAT] = monat;
        logzeilenwerte[TAG] = tag;
        logzeilenwerte[STUNDE] = stunde;
        logzeilenwerte[MINUTE] = minute;
        setzeWann();
    }
    
    /**
     * Liefere die Stunde.
     * @return den Stunden-Wert eines Logeintrags.
     */
    public int gibStunde()
    {
        return logzeilenwerte[STUNDE];
    }

    /**
     * Liefere die Minute.
     * @return den Minuten-Wert eines Logeintrags.
     */
    public int gibMinute()
    {
        return logzeilenwerte[MINUTE];
    }
    
    /**
     * Erzeuge eine Repräsentation als String.
     * Das Ergebnis ist nicht notwendigerweise
     * identisch mit der ursprünglichen Logzeile.
     *
     * @return eine Zeichenkette, die die Werte 
     *          der Logzeile enthält.
     */
    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        for(int wert : logzeilenwerte) {
            // bei einstelligen Werten eine führende
            // Null einfügen
            if(wert < 10) {
                buffer.append('0');
            }
            buffer.append(wert);
            buffer.append(' ');
        }
        // anhängende Leerzeichen abschneiden
        return buffer.toString().trim();
    }
    
    /**
     * Vergleiche die Datums- und Zeitwerte dieses Eintrags
     * mit denen eines anderen.
     * @param andererEintrag der andere Eintrag, mit dem verglichen werden soll.
     * @return Einen negativen Wert, wenn dieser Eintrag einen früheren
     *          Zeitpunkt repräsentiert. Einen positiven Wert, wenn
     *          dieser Eintrag einen späteren Zeitpunkt repräsentiert.
     *          Null, wenn beide Einträge den gleichen Zeitpunkt
     *          repräsentieren.
     */
    public int compareTo(Logeintrag andererEintrag)
    {
        // Verwende die zugehörige Calendar-Vergleichsmethode.
        return wann.compareTo(andererEintrag.gibWann());
    }
    
    /**
     * Liefere das Calendar-Objekt, welches das Ereignis repräsentiert
     * @return Das Calendar-Objekt für dieses Ereignis.
     */
    private Calendar gibWann()
    {
        return wann;
    }

    /**
     * Erzeuge ein zu den Werten passendes Calendar-Objekt.
     */
    private void setzeWann()
    {
        wann = Calendar.getInstance();
        // Wandle die 1-basierten Werte für Monat und Jahr in 0-basierte Werte um.
        wann.set(logzeilenwerte[JAHR],
                 logzeilenwerte[MONAT] - 1, logzeilenwerte[TAG] - 1,
                 logzeilenwerte[STUNDE], logzeilenwerte[MINUTE]);
    }    
}