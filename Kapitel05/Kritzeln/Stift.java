import java.awt.Color;
import java.util.Random;

/**
 * Mithilfe eines Stift-Objekt kann in ein Canvas-Leinwand gezeichnet werden.
 * Der Stift verfügt über eine Position, Richtung, Farbe und einen Aufgesetzt/Abgehoben-Zustand.
 * Der Stift kann über die Canvas bewegt werden. Wenn der Stift aufgesetzt ist , zeichnet er 
 * beim Bewegen eine Linie in die Leinwand. (Ist der Stift abgehoben, zeichnet er keine Linie.)
 * 
 * @author Michael Kölling und David J. Barnes
 * @version 31.07.2011
 */
public class Stift
{
    // Konstanten für die zufaelligesGekritzel-Methode
    private static final int GEKRITZEL_GROESSE = 40;
    private static final int GEKRITZEL_ZAEHLER = 30;
    
    private int xPosition;
    private int yPosition;
    private int rotation;
    private Color farbe;
    private boolean stiftAufgesetzt;

    private Canvas canvas;
    private Random zufallsgenerator;

    /**
     * Erzeuge einen neuen Stift mit seiner eigenen Canvas. Der Stift erzeugt sich eine neue Canvas, 
     * in die er zeichnen kann, und startet in einem definierten Zustand (Position: Mitte der Leinwand,
     * Richtung: rechts, Farbe: schwarz, Stift: aufgesetzt).
     */
    public Stift()
    {
        this (280, 220, new Canvas("Meine Canvas", 560, 440));
    }

    /**
     * Erzeuge einen neuen Stift für eine gegebene Canvas. Die Richtung ist anfänglich 0 (nach rechts),
     * die Farbe ist schwarz und der Stift ist aufgesetzt.
     *
     * @param xPos  die anfängliche horizontale Koordinate des Stiftes
     * @param yPos  die anfängliche vertikale Koordinate des Stiftes
     * @param zeichenCanvas  die Canvas, in die gezeichnet werden soll
     */
    public Stift(int xPos, int yPos, Canvas zeichenCanvas)
    {
        xPosition = xPos;
        yPosition = yPos;
        rotation = 0;
        stiftAufgesetzt = true;
        farbe = Color.BLACK;
        canvas = zeichenCanvas;
        zufallsgenerator = new Random();
    }

    /**
     * Bewege den Stift für die angegebene Distanz in die aktuelle Richtung. Wenn, 
     * der Stift aufgesetzt ist, lasse eine Linie zurück.
     * 
     * @param distanz  Die Distanz um die der Stift von der aktuellen Position aus nach vorne bewegt werden soll.
     */
    public void bewegen(int distanz)
    {
        double winkel = Math.toRadians(rotation);
        int neuX = (int) Math.round(xPosition + Math.cos(winkel) * distanz);
        int neuY = (int) Math.round(yPosition + Math.sin(winkel) * distanz);
        
        bewegenNach(neuX, neuY);
    }

    /**
     * Bewege den Stift zu der angegebenen Position. Wenn, 
     * der Stift aufgesetzt ist, lasse eine Linie zurück.
     * 
     * @param x   Die x-Koordinate, zu der der Stift bewegt werden soll.
     * @param y   Die y-Koordinate, zu der der Stift bewegt werden soll.
     */
    public void bewegenNach(int x, int y)
    {
        if (stiftAufgesetzt) {
            canvas.setForegroundColor(farbe);
            canvas.drawLine(xPosition, yPosition, x, y);
        }

        xPosition = x;
        yPosition = y;
    }

    /**
     * Drehe den Stift ausgehend von der aktuellen Drehung um den gewünschten Betrag 
     * (angegeben in Winkelgrad) im Uhrzeigersinn.
     * 
     * @param winkelgrad  der Winkel, um den gedreht werden soll. (360 ergibt einen vollen Kreis.)
     */
    public void drehen(int winkelgrad)
    {
        rotation = rotation + winkelgrad;
    }

    /**
     * Drehe den Stift in die gewünschte Richtung. 0 bedeutet rechts, 90  unten, 180 links, 270 oben.
     * 
     * @param winkel  der Winkel, zu dem der Stift gedreht werden soll.
     */
    public void drehenZu(int winkel)
    {
        rotation = winkel;
    }

    /**
     * Lege die Zeichenfarbe fest.
     * 
     * @param neueFarbe  die Farbe, die für nachfolgende Zeichenoperationen verwendet werden soll.
     */
    public void setzeFarbe(Color neueFarbe)
    {
        farbe = neueFarbe;
    }

    /**
     * Hebe den Stift ab. Nachfolgende Bewegungen werden keine Linie auf dem Canvas hinterlassen.
     */
    public void stiftAbheben()
    {
        stiftAufgesetzt = false;
    }

    /**
     * Setze den Stift auf. Nachfolgende Bewegungen werden eine Linie auf dem Canvas hinterlassen.
     */
    public void stiftAufsetzen()
    {
        stiftAufgesetzt = true;
    }

    /**
     * Kritzel in der aktuellen Farbe auf den Canvas. Größe und Komplexität des produzierten 
     * Gekritzels wird durch die Konstanten GEKRITZEL_GROESSE und GEKRITZEL_ZAEHLER bestimmt.
     */
    public void zufaelligesGekritzel()
    {
        for (int i=0; i<GEKRITZEL_ZAEHLER; i++) {
            bewegen(zufallsgenerator.nextInt(GEKRITZEL_GROESSE));
            drehen(160 + zufallsgenerator.nextInt(40));
        }

    }

}
