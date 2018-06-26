
/**
 * Beschreiben Sie hier die Klasse Bildschirm.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Bildschirm
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private int xAufloesung;
    private int yAufloesung;

    /**
     * Konstruktor für Objekte der Klasse Bildschirm
     */
    public Bildschirm(int xAufloesung, int yAufloesung)
    {
        this.xAufloesung = xAufloesung;
        this.yAufloesung = yAufloesung;
    }

    /**
     * Ein Beispiel einer Methode - ersetzen Sie diesen Kommentar mit Ihrem eigenen
     * 
     * @param  y    ein Beispielparameter für eine Methode
     * @return        die Summe aus x und y
     */
    public int anzahlBildpunkte()
    {
        return xAufloesung * yAufloesung;
    }
    
    public void loeschen(boolean invertieren) 
    {
        
    }
}
