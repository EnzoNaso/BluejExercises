import java.util.ArrayList;

/**
 * Diese Klasse speichert Informationen über eine Einsendung für den Newsfeed eines sozialen Netzwerks.
 * Der Hauptteil der Einsendung besteht aus einem Foto und einer Überschrift. 
 * Weitere Daten, wie Autor und Datum, werden ebenfalls gespeichert.
 * 
 * @author Michael Kölling und David J. Barnes
 * @version 0.3
 */
public class FotoEinsendung extends Einsendung
{
    private String dateiname;  // der Name der Bilddatei
    private String ueberschrift;   // einzeilige Bildüberschrift

    /**
     * Konstruktor für Objekte der Klasse FotoEinsendung.
     * 
     * @param autor    Der Benutzername des Einsenders.
     * @param dateiname  Der Dateiname des Bildes in dieser Einsendung.
     * @param ueberschrift   Eine Überschrift für das Bild.
     */
    public FotoEinsendung(String autor, String dateiname, String ueberschrift)
    {
        super(autor);
        this.dateiname = dateiname;
        this.ueberschrift = ueberschrift;
    }
    
    /**
     * Liefere den Dateinamen des Bildes aus der Einsendung.
     * 
     * @return den Namen der Bilddatei.
     */
    public String gibBilddateiname()
    {
        return dateiname;
    }

    /**
     * Liefere die Überschrift des Bildes aus der Einsendung.
     * 
     * @return die Überschrift des Bildes.
     */
    public String gibUeberschrift()
    {
        return ueberschrift;
    }
    
    
//     /**
//      * Zeige die Details der Einsendung an.
//      * 
//      * (Aktuell werden die Daten auf die Konsole ausgegeben,wodurch momentan die 
//      * Anzeige im Webbrowser simuliert werden soll.)
//      */
//     public void anzeigen()
//     {
//         System.out.println("  [" + dateiname + "]");
//         System.out.println("  " + ueberschrift);
//     }    
    /**
     * Zeige die Details der Einsendung an.
     * 
     * (Aktuell werden die Daten auf die Konsole ausgegeben,wodurch momentan die 
     * Anzeige im Webbrowser simuliert werden soll.)
     */
    public void anzeigen()
    {
        System.out.println(benutzername);
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
}
