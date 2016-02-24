import java.awt.Color;

import java.util.List;
import java.util.ArrayList;

/**
 * Ein Bildfilter zum Abschwächen von scharfen Kanten und Rastereffekten.
 * In etwa wie eine Weichzeichnerlinse.
 * 
 * @author Michael Kölling und David J Barnes 
 * @version 1.0
 */
public class Weichzeichnerfilter extends Filter
{
    private Farbbild original;
    private int breite;
    private int hoehe;
    
    /**
     * Konstruktor für Objekte der Klasse Weichzeichnerfilter
     * @param name der Name des Filters.
     */
    public Weichzeichnerfilter(String name)
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
                bild.setzePunktfarbe(x, y, angleichen(x, y));
            }
        }
    }
    
    /**
     * Liefere die angegeglichene Farbe des angegebenen Bildpunktes.
     * Die "angeglichene" Farbe ist der Farbwert, der sich aus dem 
     * Durchschnitt der Farbwerte dieses Bildpunktes und aller
     * angrenzenden Punkte ergibt.
     * @param xpos die x-Koordinate des Bildpunktes.
     * @param ypos die y-Koordinate des Bildpunktes.
     * @return die angeglichene Farbe.
     */
    private Color angleichen(int xpos, int ypos)
    {
        List<Color> punkte = new ArrayList<Color>(9);
        
        for(int y = ypos-1; y <= ypos+1; y++) {
            for(int x = xpos-1; x <= xpos+1; x++) {
                if( x>=0 && x<breite && y>=0 && y<hoehe )
                    punkte.add(original.gibPunktfarbe(x, y));
            }
        }

        return new Color(rotMittel(punkte), gruenMittel(punkte), blauMittel(punkte));
    }

    /**
     * @param punkte eine Liste von Bildpunktfarben.
     * @return den Durchschnittswert aller Rotwerte aus der gegebenen
     * Liste von Bildpunktfarben.
     */
    private int rotMittel(List<Color> punkte)
    {
        int summe = 0;
        for(Color farbe : punkte) {
            summe += farbe.getRed();
        }
        return summe / punkte.size();
    }

    /**
     * @param punkte eine Liste von Bildpunktfarben.
     * @return den Durchschnittswert aller Grünwerte aus der gegebenen
     * Liste von Bildpunktfarben.
     */
    private int gruenMittel(List<Color> punkte)
    {
        int summe = 0;
        for(Color farbe : punkte) {
            summe += farbe.getGreen();
        }
        return summe / punkte.size();
    }

    /**
     * @param punkte eine Liste von Bildpunktfarben.
     * @return den Durchschnittswert aller Blauwerte aus der gegebenen
     * Liste von Bildpunktfarben.
     */
    private int blauMittel(List<Color> punkte)
    {
        int summe = 0;
        for(Color farbe : punkte) {
            summe += farbe.getBlue();
        }
        return summe / punkte.size();
    }
}
