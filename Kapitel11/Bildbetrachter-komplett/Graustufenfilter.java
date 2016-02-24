import java.awt.Color;

/**
 * Ein Bildfilter zum Entfernen der Farbe aus einem Bild.
 * 
 * @author Michael Kölling und David J Barnes 
 * @version 1.0
 */
public class Graustufenfilter extends Filter
{
    /**
     * Konstruktor für Objekte der Klasse Graustufenfilter
     * @param name der Name des Filters.
     */
    public Graustufenfilter(String name)
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
                Color punktfarbe = bild.gibPunktfarbe(x, y);
                int mittel = (punktfarbe.getRed() + punktfarbe.getGreen() + punktfarbe.getBlue()) / 3;
                bild.setzePunktfarbe(x, y, new Color(mittel, mittel, mittel));
            }
        }
    }
}
