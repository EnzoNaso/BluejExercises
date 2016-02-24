import java.util.ArrayList;

/**
 * Diese Klasse speichert Informationen über eine Einsendung für den Newsfeed eines sozialen Netzwerks.
 * Einsendungen können gespeichert und angezeigt werden. 
 * Diese Klasse dient als Superklasse für stärker spezialisierte Arten von Einsendungen.
 * 
 * @author Michael Kölling und David J. Barnes
 * @version 0.3
 */
public class Einsendung 
{
    private String benutzername;  // Benutzername des Senders
    private long zeitstempel;
    private int gefielWieOft;
    private ArrayList<String> kommentare;

    /**
     * Konstruktor für Objekte der Klasse Einsendung.
     * 
     * @param autor    Der Benutzername des Einsenders.
     */
    public Einsendung(String autor)
    {
        benutzername = autor;
        zeitstempel = System.currentTimeMillis();
        gefielWieOft = 0;
        kommentare = new ArrayList<String>();
    }

    /**
     * Halte fest, dass die Einsendung von einem Benutzer mit 'gefällt' bewertet wurde.
     */
    public void gefaellt()
    {
        gefielWieOft++;
    }

    /**
     * Halte fest, dass ein Benutzer seine 'gefällt'-Bewertung zurückgezogen hat.
     */
    public void gefaelltNicht()
    {
        if (gefielWieOft > 0) {
            gefielWieOft--;
        }
    }

    /**
     * Füge der Einsendung einen Kommentar hinzu.
     * 
     * @param text  Der neu hinzuzufügende Kommentar.
     */
    public void erfasseKommentar(String text)
    {
        kommentare.add(text);
    }

    /**
     * Liefere die Zeit, zu der die Einsendung erstellt wurde.
     * 
     * @return die Zeit, zu der die Einsendung erstellt wurde (als Systemzeit-Wert)..
     */
    public long gibZeitstempel()
    {
        return this.zeitstempel;
    }
    
    
    
    /**
     * Erzeuge einen String, der einen in der Vergangenheit liegenden Zeitpunkt im Vergleich
     * zur aktuellen Zeit beschreibt, also beispielsweise "vor 30 Sekunden" oder "vor 7 Minuten".
     * Derzeit werden nur Sekunden und Minuten für den String verwendet.
     * 
     * @param zeit  der umzuwandelnde Zeitwert (in System-Millisekunden)
     * @return      eine relative Zeitbeschreibung für den gegebenen Zeitwert
     */
    private String zeitString(long zeit)
    {
        long aktuell = System.currentTimeMillis();
        long vergangeneMillis = aktuell - zeit;      // vergangene Zeit in Millisekunden
        long sekunden = vergangeneMillis/1000;
        long minuten = sekunden/60;
        if(minuten > 0) {
            return "vor " + minuten + " Minuten";
        }
        else {
            return "vor " + sekunden + " Sekunden";
        }
    }
    public String getBenutzername(){
        return this.benutzername;
    }
}
