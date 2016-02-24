import java.util.ArrayList;

/**
 * Diese Klasse speichert Informationen über eine Einsendung für den Newsfeed eines sozialen Netzwerks.
 * Der Hauptteil der Einsendung besteht aus einer (möglicherweise mehrzeiligen) Textnachricht. 
 * Weitere Daten, wie Autor und Datum, werden ebenfalls gespeichert.
 * 
 * @author Michael Kölling und David J. Barnes
 * @version 0.3
 */
public class NachrichtenEinsendung extends Einsendung
{
    private String nachricht;   // eine beliebig lange, mehrzeilige Nachricht

    /**
     * Konstruktor für Objekte der Klasse NachrichtenEinsendung.
     * 
     * @param autor    Der Benutzername des Einsenders.
     * @param text      Der Text dieser Einsendung.
     */
    public NachrichtenEinsendung(String autor, String text)
    {
        super(autor);
        nachricht = text;
        
    }

    /**
     * Liefere den Text der Einsendung.
     * 
     * @return der Nachrichtentext der Einsendung.
     */
    public String gibText()
    {
        return nachricht;
    }
    
//     /**
//      * Zeige die Details der Einsendung an.
//      * 
//      * (Aktuell werden die Daten auf die Konsole ausgegeben,wodurch momentan die 
//      * Anzeige im Webbrowser simuliert werden soll.)
//      */
//     public void anzeigen()
//     {
//         System.out.println(nachricht);
//     }
/**
     * Zeige die Details der Einsendung an.
     * 
     * (Aktuell werden die Daten auf die Konsole ausgegeben,wodurch momentan die 
     * Anzeige im Webbrowser simuliert werden soll.)
     */
    public void anzeigen()
    {
        
        
        System.out.println(super.getBenutzername());
        System.out.print(super.zeitString(super.gibZeitstempel()));
        
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
}
