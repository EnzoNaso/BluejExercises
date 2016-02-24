import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

/**
 * Farbbild ist eine Klasse, die Farbbilder mit einer bequemen
 * Schnittstelle definiert.
 * 
 * @author  Michael Kölling und David J. Barnes
 * @version 1.1
 */
public class Farbbild extends BufferedImage
{
    /**
     * Erzeuge ein Farbbild als Kopie von einem BufferedImage.
     * @param image das zu kopierende BufferedImage.
     */
    public Farbbild(BufferedImage image)
    {
         super(image.getColorModel(), image.copyData(null), 
               image.isAlphaPremultiplied(), null);
    }

    /**
     * Erzeuge ein Farbbild mit der angegebenen Größe mit
     * undefiniertem Inhalt.
     * @param breite die Breite des Bildes.
     * @param hoehe die Hoehe des Bildes.
     */
    public Farbbild(int breite, int hoehe)
    {
        super(breite, hoehe, TYPE_INT_RGB);
    }

    /**
     * Setze den angegebenen Bildpunkt dieses Bildes auf die
     * angegebene Farbe.
     * @param x die x-Koordinate des Bildpunktes.
     * @param y die y-Koordinate des Bildpunktes.
     * @param col die Farbe des Bildpunktes.
     */
    public void setzePunktfarbe(int x, int y, Color col)
    {
        int punktfarbe = col.getRGB();
        setRGB(x, y, punktfarbe);
    }
    
    /**
     * Liefere die Farbe des angegebenen Bildpunktes.
     * @param x die x-Koordinate des Bildpunktes.
     * @param y die y-Koordinate des Bildpunktes.
     * @return die Farbe des Bildpunktes an der angegebenen Position.
     */
    public Color gibPunktfarbe(int x, int y)
    {
        int punktfarbe = getRGB(x, y);
        return new Color(punktfarbe);
    }

    /**
     * Dunkle diese Bild etwas ab.
     */
    public void abdunkeln()
    {
        int hoehe = getHeight();
        int breite = getWidth();
        
        // Auf alle Bildpunkte die Operation "darker" der
        // Klasse Color anwenden.
        for(int y = 0; y < hoehe; y++) {
            for(int x = 0; x < breite; x++) {
                setzePunktfarbe(x, y, gibPunktfarbe(x, y).darker());
            }
        }
    }

    /**
     * Helle diese Bild etwas auf.
     */
    public void aufhellen()
    {
        int hoehe = getHeight();
        int breite = getWidth();

        // Auf alle Bildpunkte die Operation "brighter" der
        // Klasse Color anwenden.
        for(int y = 0; y < hoehe; y++) {
            for(int x = 0; x < breite; x++) {
                setzePunktfarbe(x, y, gibPunktfarbe(x, y).brighter());
            }
        }
    }
    
    /**
     * Wende eine dreistufige Schwellwertoperation an. Heißt: Zeichne
     * das Bild erneut mit lediglich drei Farbwerten: weiß, grau und schwarz.
     */
    public void schwellwerteAnwenden()
    {
        int hoehe = getHeight();
        int breite = getWidth();
        for(int y = 0; y < hoehe; y++) {
            for(int x = 0; x < breite; x++) {
                Color farbe = gibPunktfarbe(x, y);
                int helligkeit = (farbe.getRed() + farbe.getBlue() + farbe.getGreen()) / 3;
                if(helligkeit <= 85) {
                    setzePunktfarbe(x, y, Color.BLACK);
                }
                else if(helligkeit <= 170) {
                    setzePunktfarbe(x, y, Color.GRAY);
                }
                else {
                    setzePunktfarbe(x, y, Color.WHITE);
                }
            }
        }
    }

}
