/**
 * Ein Bildfilter zum leichten Aufhellen eines Bildes.
 * 
 * @author Michael Kölling und David J Barnes 
 * @version 1.0
 */
public class Aufhellfilter extends Filter
{
    /**
     * Konstruktor für Objekte der Klasse Aufhellfilter
     * @param name der Name des Filters.
     */
    public Aufhellfilter(String name)
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

        // Auf alle Bildpunkte die Operation "brighter" der
        // Klasse Color anwenden.
        for(int y = 0; y < hoehe; y++) {
            for(int x = 0; x < breite; x++) {
                bild.setzePunktfarbe(x, y, bild.gibPunktfarbe(x, y).brighter());
            }
        }
    }

}
