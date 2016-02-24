import java.util.ArrayList;

/**
 * Diese Klasse speichert Informationen über eine Einsendung innerhalb eines sozialen
 * Netzwerks. Der Hauptteil der Einsendung besteht aus einer (möglicherweise 
 * mehrzeiligen) Textnachricht. Weitere Daten, wie Autor und Datum, werden ebenfalls 
 * gespeichert.
 * 
 * @autor Michael Kölling und David J. Barnes
 * @version 0.1
 */
public class NachrichtenEinsendung 
{
    private String benutzername;  // Benutzername des Senders
    private String nachricht;     // eine beliebig lange, mehrzeilige Nachricht
    private long zeitstempel;
    private int gefielWieOft;
    private ArrayList<String> kommentare;

    /**
     * Konstruktor für Objekte der Klasse NachrichtenEinsendung.
     * 
     * @param autor    der Benutzername des Einsenders.
     * @param text      der Text dieser Einsendung.
     */
    public NachrichtenEinsendung(String autor, String text)
    {
        benutzername = autor;
        nachricht = text;
        zeitstempel = System.currentTimeMillis();
        gefielWieOft = 0;
        kommentare = new ArrayList<String>();
    }

    /**
     * Halte fest, dass die Nachricht von einem Benutzer mit 'gefällt' bewertet 
     * wurde.
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
     * @param text  der neu hinzuzufügende Kommentar.
     */
    public void erfasseKommentar(String text)
    {
        kommentare.add(text);
    }

    /**
     * Liefere den Text dieser Einsendung.
     * 
     * @return den Text der Einsendung.
     */
    public String gibText()
    {
        return nachricht;
    }

    /**
     * Liefere die Zeit, zu der die Einsendung erstellt wurde.
     * 
     * @return die Zeit, zu der die Einsendung erstellt wurde (als Systemzeit-Wert)..
     */
    public long gibZeitstempel()
    {
        return zeitstempel;
    }

    /**
     * Zeige die Details der Einsendung an.
     * 
     * (Aktuell werden die Daten auf die Konsole ausgegeben, wodurch momentan die 
     * Anzeige im Webbrowser simuliert werden soll.)
     */
    public void anzeigen()
    {
        System.out.println(benutzername);
        System.out.println(nachricht);
        System.out.print(zeitString(zeitstempel));
        
        if(gefielWieOft > 0) {
            System.out.println("  -  " + gefielWieOft + " Person(en) gefaellt dies.");
        }
        else {
            System.out.println();
        }
        
        if(kommentare.isEmpty()) {
            System.out.println("   Keine Kommentare.");
        }
        else {
            System.out.println("   " + kommentare.size() + 
                               " Kommentar(e). Zum Einsehen hier klicken.");
        }
    }
    
    /**
     * Erzeuge einen String, der einen in der Vergangenheit liegenden Zeitpunkt im 
     * Vergleich zur aktuellen Zeit beschreibt, also beispielsweise "vor 30 Sekunden" 
     * oder "vor 7 Minuten". Derzeit werden nur Sekunden und Minuten für den String 
     * verwendet.
     * 
     * @param zeit  der umzuwandelnde Zeitwert (in System-Millisekunden)
     * @return      eine relative Zeitbeschreibung für den gegebenen Zeitwert
     */
    private String zeitString(long zeit)
    {
        long aktuell = System.currentTimeMillis();
        long vergangeneMillis = aktuell - zeit;   // vergangene Zeit in Millisekunden
        long sekunden = vergangeneMillis/1000;
        long minuten = sekunden/60;
        if(minuten > 0) {
            return "vor " + minuten + " Minuten";
        }
        else {
            return "vor " + sekunden + " Sekunden";
        }
    }
}
