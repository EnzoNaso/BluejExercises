/**
 * Speichere die Details von Musiktiteln (Tracks),
 * wie z.B. den Interpreten, den Titel und den Dateinamen.
 * Verwende die FELDER-Klassenvariable für die Namen der
 * verfügbaren Attribute.
 * 
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class Track
{
    // Der Interpret.
    private String interpret;
    // Der Titel des Tracks.
    private String titel;
    // Wo der Track gespeichert ist.
    private String dateiname;

    // Namen der verfügbaren Felder.
    public static final String[] FELDER = {
        "Interpret",
        "Titel",
        "Dateiname",
    };
    
    /**
     * Konstruktor für Objekte der Klasse Track.
     * @param interpret der Interpret des Titels.
     * @param titel der Titel des Tracks.
     * @param dateiname die Track-Datei. 
     */
    public Track(String interpret, String titel, String dateiname)
    {
        setzeDetails(interpret, titel, dateiname);
    }
    
    /**
     * Konstruktor für Objekte der Klasse Track.
     * Geht davon aus, dass der Dateiname nicht dekodiert werden kann, um
     * den tatsächlichen Interpreten und Titel zu ermitteln.
     * @param dateiname die Track-Datei. 
     */
    public Track(String dateiname)
    {
        setzeDetails("unbekannt", "unbekannt", dateiname);
    }
    
    /**
     * Liefere den Interpreten.
     * @return der Interpret.
     */
    public String gibInterpret()
    {
        return interpret;
    }
    
    /**
     * Liefere den Titel.
     * @return der Titel.
     */
    public String gibTitel()
    {
        return titel;
    }
    
    /**
     * Liefere den Dateinamen.
     * @return der Dateiname.
     */
    public String gibDateiname()
    {
        return dateiname;
    }

    /**
     * Liefere den Wert des benannten Feldes.
     * Das Fefld sollte ein Element vonTrack.FELDER sein.
     * @param feld welches Feld soll zurückgeliefert werden.
     */
    public String gibFeld(String feld) 
    {
        if (feld.equals("Interpret")) {
            return interpret;
        }
        else if (feld.equals("Titel")) {
            return titel;
        }
        else if (feld.equals("Dateiname")) {
            return dateiname;
        }
        else {
            throw new IllegalArgumentException("Unbekannter Feldname: " + feld);
        }
    }
    
    /**
     * Liefere die Werte der Felder.
     * @return die Felder.
     */
    public String[] gibFelder()
    {
        String[] felder = new String[FELDER.length];
        for(int i = 0; i < FELDER.length; i++) {
            felder[i] = gibFeld(FELDER[i]);
        }
        return felder;
    }
    
     
    /**
     * Liefer Details über den Track: Interpret, Titel und Dateiname.
     * @return die Track-Details.
     */
    public String gibDetails()
    {
        return interpret + ": " + titel + "  (Datei: " + dateiname + ")";
    }
    
    /**
     * Lege die Details des Tracks fest.
     * @param interpret der Interpret des Titels.
     * @param titel der Titel des Tracks.
     * @param dateiname die Track-Datei. 
     */
    private void setzeDetails(String interpret, String titel, String dateiname)
    {
        this.interpret = interpret;
        this.titel = titel;
        this.dateiname = dateiname;
    }
    
}
