import java.awt.Color;

import java.util.List;
import java.util.ArrayList;

/**
 * Ein Bildfilter zum Erkennen und Hervorheben von Kanten,
 * in etwa wie in einer Buntstiftzeichnung.
 * 
 * @author Michael Kölling und David J Barnes 
 * @version 1.0
 */
public class Kantenfilter extends Filter
{
    private static final int TOLERANZ = 20;
    
    private Farbbild original;
    private int breite;
    private int hoehe;

    /**
     * Konstruktor für Objekte der Klasse Kantenfilter
     * @param name der Name des Filters.
     */
    public Kantenfilter(String name)
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
        original = new Farbbild(bild);
        breite = original.getWidth();
        hoehe = original.getHeight();
        
        for(int y = 0; y < hoehe; y++) {
            for(int x = 0; x < breite; x++) {
                bild.setzePunktfarbe(x, y, hervorheben(x, y));
            }
        }
    }

    /**
     * Liefere eine neue Farbe, die sich aus der Hervorhebung
     * der Farbe einer gegebenen Position ergibt. "Hervorhebung" sei
     * hier der Farbwert, der sich aus dem Verhältnis des Farbwertes
     * des gegebenen Punktes und aller angrenzender Farbpunktwerte ergibt
     * - je größer die Helligkeitsunterschiede zwischen den Punkten,
     * desto dunkler ist das Ergebnis.
     * @param xpos Die x-Koordinate des Bildpunktes.
     * @param ypos Die y-Koordinate des Bildpunktes.
     * @return die hervorgehobene Farbe.
     */
    private Color hervorheben(int xpos, int ypos)
    {
        List<Color> punkte = new ArrayList<Color>(9);
        
        for(int y = ypos-1; y <= ypos+1; y++) {
            for(int x = xpos-1; x <= xpos+1; x++) {
                if( x >= 0 && x < breite && y >= 0 && y < hoehe ) {
                    punkte.add(original.gibPunktfarbe(x, y));
                }
            }
        }

        return new Color(255-diffRot(punkte), 255-diffGruen(punkte), 255-diffBlau(punkte));
    }

    /**
     * @param bildpunkte eine Liste von Bildpunkten, die untersucht werden sollen.
     * @return die Differenz zwischen dem hellsten und dem dunkelsten
     * Rotwert in der gegebenen Liste von Bildpunkten.
     */
    private int diffRot(List<Color> bildpunkte)
    {
        int max = 0;
        int min = 255;
        for(Color farbe : bildpunkte) {
            int wert = farbe.getRed();
            if(wert > max) {
                max = wert;
            }
            if(wert < min) {
                min = wert;
            }
        }
        int differenz = max - min - TOLERANZ;
        if(differenz < 0) {
            differenz = 0;
        }
        return differenz;
    }

    /**
     * @param bildpunkte eine Liste von Bildpunkten, die untersucht werden sollen.
     * @return die Differenz zwischen dem hellsten und dem dunkelsten
     * Grünwert in der gegebenen Liste von Bildpunkten.
     */
    private int diffGruen(List<Color> bildpunkte)
    {
        int max = 0;
        int min = 255;
        for(Color farbe : bildpunkte) {
            int wert = farbe.getGreen();
            if(wert > max) {
                max = wert;
            }
            if(wert < min) {
                min = wert;
            }
        }
        int differenz = max - min - TOLERANZ;
        if(differenz < 0) {
            differenz = 0;
        }
        return differenz;
    }

    /**
     * @param bildpunkte eine Liste von Bildpunkten, die untersucht werden sollen.
     * @return die Differenz zwischen dem hellsten und dem dunkelsten
     * Blauwert in der gegebenen Liste von Bildpunkten.
     */
    private int diffBlau(List<Color> bildpunkte)
    {
        int max = 0;
        int min = 255;
        for(Color farbe : bildpunkte) {
            int wert = farbe.getBlue();
            if(wert > max) {
                max = wert;
            }
            if(wert < min) {
                min = wert;
            }
        }
        int differenz = max - min - TOLERANZ;
        if(differenz < 0) {
            differenz = 0;
        }
        return differenz;
    }

}
