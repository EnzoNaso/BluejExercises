
/**
 * Write a description of class Bildschirm here.
 * 
 * @author (Enzo) 
 * @version (1.0)
 */
public class Bildschirm
{
    // instance variables
    private int xAufloesung;
    private int yAufloesung;

    /**
     * Constructor for objects of class Bildschirm
     * 
     * @params xAufloesung Bildpunkte auf der X-Achse
     * @params yAufloesung Bildpunkte auf der Y-Achse
     */
    public Bildschirm(int xAufloesung, int yAufloesung)
    {
        // initialise instance variables
        this.xAufloesung = xAufloesung;
        this.yAufloesung = yAufloesung;
        if(anzahlBildpunkte() > 2000000)
        {
            loeschen(true);
        }
    }

    /**
     * Anzahl Bildpunkte
     * 
     */
    public int anzahlBildpunkte()
    {
        return xAufloesung * yAufloesung;
    }

    /**
     * loeschen der Bildpunkte
     * 
     * @params invertieren Bildpunkte invertieren (schwarz/weiss) 
     */
    public void loeschen(boolean invertieren)
    {
        
    }
        
    
}
