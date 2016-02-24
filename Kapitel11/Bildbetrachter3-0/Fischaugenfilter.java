
/**
 * Ein Bildfilter, mit dem ein ähnlicher Effekt wie mit einer
 * Fischaugenlinse bei einer Fotokamera erzielt werden kann.
 * (Wirkt besonders gut bei Porträts.)
 * 
 * @author Michael Kölling und David J Barnes 
 * @version 1.0
 */
public class Fischaugenfilter extends Filter
{
    final static int STAERKE = 20;   // Stärke der Verzerrung
    final static double ZWEI_PI = 2 * Math.PI;

    /**
     * Konstruktor für Objekte der Klasse Fischaugenfilter
     * @param name der Name des Filters.
     */
    public Fischaugenfilter(String name)
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
        Farbbild original = new Farbbild(bild);

        int[] xa = berechneXArray(breite);
        int[] ya = berechneYArray(hoehe);
        
        for(int y = 0; y < hoehe; y++) {
            for(int x = 0; x < breite; x++) {
                bild.setzePunktfarbe(x, y, original.gibPunktfarbe(x + xa[x], y + ya[y]));
            }
        }
    }

    /**
     * Berechne und liefere ein Array von horizontalen Abweichungen
     * für jede Punktspalte. Auf jeden Bildpunkt kann dann der entsprechende
     * Wert als horizontale Abweichung angewendet werden.
     */
    private int[] berechneXArray(int breite)
    {
        int[] xArray = new int[breite];
        
        for(int i=0; i < breite; i++) {
            xArray[i] = (int)(Math.sin( ((double)i / breite) * ZWEI_PI) * STAERKE);
        }
        return xArray;
    }

    /**
     * Berechne und liefere ein Array von vertikalen Abweichungen
     * für jede Punktzeile. Auf jeden Bildpunkt kann dann der entsprechende
     * Wert als vertikale Abweichung angewendet werden.
     */
    private int[] berechneYArray(int hoehe)
    {
        int[] yArray = new int[hoehe];
        
        for(int i=0; i < hoehe; i++) {
            yArray[i] = (int)(Math.sin( ((double)i / hoehe) * ZWEI_PI) * STAERKE);
        }
        return yArray;
    }
}
