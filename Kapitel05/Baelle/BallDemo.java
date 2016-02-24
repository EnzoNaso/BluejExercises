import java.awt.*;
import java.awt.geom.*;

/**
 * Die Klasse BallDemo bietet eine kurze Demonstration, wie die
 * Klasse Canvas benutzt werden kann.
 *
 * @author Michael Kölling und David J. Barnes
 * @version 31.07.2011
 */

public class BallDemo   
{
    private Canvas leinwand;

    /**
     * Erzeuge ein Exemplar von BallDemo.
     * Es wird ein frischer Canvas erzeugt und sichtbar gemacht.
     */
    public BallDemo()
    {
        leinwand = new Canvas("Ball Demo", 600, 500);
    }
 
    /**
     * Simuliere zwei springende Bälle.
     */
    public void springenLassen()
    {
        int boden = 400;   // Position der Bodenlinie

        leinwand.setVisible(true);

        // Den Boden zeichnen.
        leinwand.drawLine(50, boden, 550, boden);

        // Die Bälle erzeugen und anzeigen.
        Ball ball = new Ball(50, 50, 16, Color.BLUE, boden, leinwand);
        ball.zeichnen();
        Ball ball2 = new Ball(70, 80, 20, Color.RED, boden, leinwand);
        ball2.zeichnen();

        // Die Bälle springen lassen.
        boolean fertig =  false;
        while(!fertig) {
            leinwand.wait(50);           // kurze Pause
            ball.bewegen();
            ball2.bewegen();
            // Stoppen, wenn die Bälle weit genug gesprungen sind.
            if(ball.gibXPosition() >= 550 || ball2.gibXPosition() >= 550) {
                fertig = true;
            }
        }
    }
}
