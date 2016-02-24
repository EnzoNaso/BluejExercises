import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * Ein simples Modell eines Mail-Servers. Der Server kann Nachrichten
 * entgegen nehmen und auf Anfrage an Mail-Klienten weiterleiten.
 *
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class MailServer
{
    // Speicherstruktur für die Nachrichten, die auf dem Server
    // zwischengespeichert werden.
    private List<Nachricht> nachrichten;

    /**
     * Erzeuge einen MailServer.
     */
    public MailServer()
    {
        nachrichten = new ArrayList<Nachricht>();
    }

    /**
     * Liefere die Anzahl der Nachrichten für den angegebenen Benutzer.
     * @param benutzer Benutzer, dessen Nachrichtenanzahl geliefert
     *                  werden soll.
     * @return die Anzahl der Nachrichten für 'benutzer'.
     */
    public int anzahlNachrichtenFuer(String benutzer)
    {
        int anzahl = 0;
        for (Nachricht nachricht: nachrichten) {
            if(nachricht.gibEmpfaenger().equals(benutzer)) {
                anzahl++;
            }
        }
        return anzahl;
    }

    /**
     * Liefere die nächste Nachricht für den angegebenen Benutzer.
     * Liefere null, falls keine Nachrichten vorhanden sind.
     * @param benutzer Benutzer, für den die Nachricht geliefert
     *                  werden soll.
     * @return die nächste Nachricht, falls vorhanden, null sonst.
     */
    public Nachricht gibNaechsteNachrichtFuer(String benutzer)
    {
        Iterator<Nachricht> it = nachrichten.iterator();
        while(it.hasNext()) {
            Nachricht nachricht = it.next();
            if(nachricht.gibEmpfaenger().equals(benutzer)) {
                it.remove();
                return nachricht;
            }
        }
        return null;
    }

    /**
     * Leite die angegebene Nachricht auf Anfrage weiter.
     * @param nachricht die Nachricht, die auf dem Server hinterlegt
     *                   werden soll
     */
    public void leiteWeiter(Nachricht nachricht)
    {
        nachrichten.add(nachricht);
    }
}
