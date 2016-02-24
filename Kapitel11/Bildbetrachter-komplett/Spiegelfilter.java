import java.awt.Color;

/**
 * Ein Bildfilter zum horizontalen Spiegeln eines Bildes
 * (Spiegeln an der vertikalen Achse). 
 * 
 * @author Michael Kölling und David J Barnes 
 * @version 1.0
 */
public class Spiegelfilter extends Filter
{
    /**
     * Konstruktor für Objekte der Klasse Spiegelfilter
     * @param name der Name des Filters.
     */
    public Spiegelfilter(String name)
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
        int hoehe = bild.getHeight();
        int breite = bild.getWidth();
        for(int y = 0; y < hoehe; y++) {
            for(int x = 0; x < breite/2; x++) {
                Color links = bild.gibPunktfarbe(x, y);
                bild.setzePunktfarbe(x, y, bild.gibPunktfarbe(breite-1-x, y));
                bild.setzePunktfarbe(breite-1-x, y, links);
            }
        }
    }
}
