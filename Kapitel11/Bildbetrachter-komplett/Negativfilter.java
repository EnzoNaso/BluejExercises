import java.awt.Color;

/**
 * Ein Bildfilter zum Invertieren der Farben.
 * 
 * @author Michael Kölling und David J Barnes 
 * @version 1.0
 */
public class Negativfilter extends Filter
{
    /**
     * Konstruktor für Objekte der Klasse Negativfilter
     * @param name der Name des Filters.
     */
    public Negativfilter(String name)
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
            for(int x = 0; x < breite; x++) {
                Color punkt = bild.gibPunktfarbe(x, y);
                bild.setzePunktfarbe(x, y, new Color(255 - punkt.getRed(),
                                               255 - punkt.getGreen(),
                                               255 - punkt.getBlue()));
            }
        }
    }
}
