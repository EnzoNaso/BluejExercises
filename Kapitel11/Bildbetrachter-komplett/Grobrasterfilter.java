import java.awt.Color;

/**
 * Ein Bildfilter für einen Grobrastereffekt, wie bei einem
 * vergrößerten Digitalbild mit niedriger Auflösung.
 * 
 * @author Michael Kölling und David J Barnes 
 * @version 1.0
 */
public class Grobrasterfilter extends Filter
{
    /**
     * Konstruktor für Objekte der Klasse Grobrasterfilter
     * @param name der Name des Filters.
     */
    public Grobrasterfilter(String name)
    {
        super(name);
    }

    /**
     * Wende diesen Filter auf das gegebene Bild an.
     * 
     * @param  bild  das Bild, das dieser Filter verändern soll.
     */
    public void anwenden(Farbbild bild)
    {
        final int RASTERGROESSE = 5;
        int breite = bild.getWidth();
        int hoehe = bild.getHeight();
        
        for(int y = 0; y < hoehe; y+=RASTERGROESSE) {
            for(int x = 0; x < breite; x+=RASTERGROESSE) {
                Color farbe = bild.gibPunktfarbe(x, y);
                for(int dy = y; dy < y+RASTERGROESSE; dy++) {
                    for(int dx = x; dx < x+RASTERGROESSE; dx++) {
                        if( dx < breite && dy < hoehe )
                            bild.setzePunktfarbe(dx, dy, farbe);
                    }
                }
            }
        }
    }
}
