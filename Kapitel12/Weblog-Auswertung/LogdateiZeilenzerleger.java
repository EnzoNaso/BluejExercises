import java.util.Scanner;

/**
 * Aufgabe dieser Klasse ist es, die Logdatei eines Webservers in 
 * ihre einzelnen Felder zu zerlegen.
 * Derzeit wird davon ausgegangen, dass die Logdatei nur einfache ganze Zahlenwerte und
 * Zeitangaben enthält.
 * 
 * @author David J. Barnes und Michael Kölling.
 * @version 31.07.2011
 */
public class LogdateiZeilenzerleger
{
    /**
     * Erzeuge einen LogLineAnalyzer
     */
    public LogdateiZeilenzerleger()
    {
    }

    /**
     * Zerlege eine Zeile der Logdatei. Lege die enthaltenen Zahlenwerte
     * in einem Array ab. Die Anzahl Elemente (Token) in einer Zeile muss
     * ausreichen, um das Array zu füllen.
     *
     * @param logZeile die zu zerlegende Zeile.
     * @param datenZeile wo sollen die Werte gespeichert werden.
     */
    public void zerlegen(String logZeile, int[] datenZeile)
    {
        try {
            // die logZeile nach ganzen Zahlen durchsuchen.
            Scanner zerleger = new Scanner(logZeile);
            for(int i = 0; i < datenZeile.length; i++) {
                datenZeile[i] = zerleger.nextInt();
            }
        }
        catch(java.util.NoSuchElementException e) {
            System.out.println("Zu wenige Elemente in Logdatei-Zeile: " + logZeile);
            throw e;
        }
    }
}
