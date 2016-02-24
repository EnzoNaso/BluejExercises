/**
 * Ein Bildfilter zum leichten Abdunkeln eines Bildes.
 * 
 * @author Michael Kölling und David J Barnes 
 * @version 1.0
 */
public class Abdunkelfilter extends Filter
{
    /**
     * Konstruktor für Objekte der Klasse Abdunkelfilter
     * @param name der Name des Filters.
     */
    public Abdunkelfilter(String name)
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
        
        // Auf alle Bildpunkte die Operation "darker" der
        // Klasse Color anwenden.
        for(int y = 0; y < hoehe; y++) {
            for(int x = 0; x < breite; x++) {
                bild.setzePunktfarbe(x, y, bild.gibPunktfarbe(x, y).darker());
            }
        }
    }
}
