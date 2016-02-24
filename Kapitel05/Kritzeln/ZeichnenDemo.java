import java.awt.Color;
import java.util.Random;

/**
 * Die Klasse ZeichnenDemo bietet einige kurze Demonstrationen, wie mithilfe der
 * Klasse Stift verschiedene Zeichnungen erzeugt werden können.
 *
 * @author Michael Kölling und David J. Barnes
 * @version 31.07.2011
 */

public class ZeichnenDemo
{
    private Canvas meineCanvas;
    private Random zufallsgenerator;

    /**
     * Die Demo vorbereiten. Es wird ein frischer Canvas erzeugt und sichtbar gemacht.
     */
    public ZeichnenDemo()
    {
        meineCanvas = new Canvas("Zeichnen-Demo", 500, 400);
        zufallsgenerator = new Random();
    }

    /**
     * Zeichne ein Quadrat auf den Bildschirm.
     */
    public void zeichneQuadrat()
    {
        Stift stift = new Stift(320, 260, meineCanvas);
        stift.setzeFarbe(Color.BLUE);

        quadrat(stift);
    }

    /**
     * Zeichne ein Rad aus mehreren Quadraten.
     */
    public void zeichneRad()
    {
        Stift stift = new Stift(250, 200, meineCanvas);
        stift.setzeFarbe(Color.RED);

        for (int i=0; i<36; i++) {
            quadrat(stift);
            stift.drehen(10);
        }
    }

    /**
     * Zeichne ein Quadrat in der Farbe des Stifts und an der Position des Stifts.
     */
    private void quadrat(Stift stift)
    {
        for (int i=0; i<4; i++) {
            stift.bewegen(100);
            stift.drehen(90);
        }
    }

    /**
     * Zeichne einige zufällige Schnörkel in zufälligen Farben.
     */
    public void buntesGekritzel()
    {
        Stift stift = new Stift(250, 200, meineCanvas);

        for (int i=0; i<10; i++) {
            // wähle zufällig eine Farbe aus
            int rot = zufallsgenerator.nextInt(256);
            int gruen = zufallsgenerator.nextInt(256);
            int blau = zufallsgenerator.nextInt(256);
            stift.setzeFarbe(new Color(rot, gruen, blau));
            
            stift.zufaelligesGekritzel();
        }
    }
    
    /**
     * Leere die Zeichenfläche.
     */
    public void leeren()
    {
        meineCanvas.erase();
    }
}
