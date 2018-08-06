
/**
 * Beschreiben Sie hier die Klasse Monitor.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Monitor
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private Bildschirm bildschirm1;

    /**
     * Konstruktor für Objekte der Klasse Monitor
     */
    public Monitor()
    {
        this.bildschirm1 = new Bildschirm(1024, 1024);
    }

    /**
     * Ein Beispiel einer Methode - ersetzen Sie diesen Kommentar mit Ihrem eigenen
     * 
     * @param  y    ein Beispielparameter für eine Methode
     * @return        die Summe aus x und y
     */
    public void bildloeschen()
    {
        if (this.bildschirm1.anzahlBildpunkte() > 2000000)
        {   
            this.bildschirm1.loeschen(true);
        }
    
    }
}
