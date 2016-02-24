import java.awt.Color;

/**
 * Ein dreistufiger (Schwarz-Grau-Weiß) Schwellwertfilter.
 * 
 * @author Michael Kölling und David J Barnes 
 * @version 1.0
 */
public class Schwellwertfilter extends Filter
{
    /**
     * Konstruktor für Objekte der Klasse Schwellwertfilter
     * @param name der Name des Filters.
     */
    public Schwellwertfilter(String name)
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
                Color farbe = bild.gibPunktfarbe(x, y);
                int helligkeit = (farbe.getRed() + farbe.getBlue() + farbe.getGreen()) / 3;
                if(helligkeit <= 85) {
                    bild.setzePunktfarbe(x, y, Color.BLACK);
                }
                else if(helligkeit <= 170) {
                    bild.setzePunktfarbe(x, y, Color.GRAY);
                }
                else {
                    bild.setzePunktfarbe(x, y, Color.WHITE);
                }
            }
        }
    }
}
