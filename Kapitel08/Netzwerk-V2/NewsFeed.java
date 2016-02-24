import java.util.ArrayList;

/**
 * Die Klasse NewsFeed speichert neue Einsendungen für den Newsfeed einer 
 * Sozialen-Netzwerk-Anwendung (wie Facebook oder Google+).
 * 
 * Derzeit wird das Anzeigen der Einsendungen durch die Ausgabe auf die Konsole 
 * simuliert. (Später soll die Anzeige in einem Webbrowser erfolgen.)
 * 
 * Diese Version speichert keine Daten auf Festplatte. Auch gibt es keine 
 * Unterstützung fürdas Durchsuchen oder Anordnen der Einsendungen.
 * 
 * @autor Michael Kölling und David J. Barnes
 * @version 0.2
 */
public class NewsFeed
{
    private ArrayList<Einsendung> einsendungen;

    /**
     * Erzeuge einen leeren Newsfeed.
     */
    public NewsFeed()
    {
        einsendungen = new ArrayList<Einsendung>();
    }

    /**
     * Füge dem Newsfeed eine Einsendung hinzu.
     * 
     * @param Einsendung  die hinzuzufügende Einsendung.
     */
    public void erfasseEinsendung(Einsendung einsendung)
    {
        einsendungen.add(einsendung);
    }

    /**
     * Gebe den Newsfeed aus. Derzeit werden die Details des Newsfeed auf die
     * Konsole ausgegeben. (Noch zu tun: ersetze diesen Code durch die Anzeige im
     * Webbrowser.)
     */
    public void zeigen()
    {
        // alle Einsendungen anzeigen
        for(Einsendung einsendung : einsendungen) {
            einsendung.anzeigen();
            System.out.println();   // leere Zeile zwischen den Einsendungen
        }
    }
}
