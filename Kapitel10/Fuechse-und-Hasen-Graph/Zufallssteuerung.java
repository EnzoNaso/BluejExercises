import java.util.Random;

/**
 * Steuerung der zufallsbasierten Elemente der Simulation.
 * 
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class Zufallssteuerung
{
    // Vorgabe für den SEED-Wert, der die Erzeugung der Zufallszahlen steuert
    private static final int SEED = 1111;
    // Ein gemeinsam genutztes Random-Objekt, falls benötigt
    private static final Random rand = new Random(SEED);
    // Bestimmt, ob ein gemeinsam genutzer Zufallsgenerator zur Verfügung gestellt wird.
    private static final boolean nutzeGemeinsam = true;

    /**
     * Konstruktor für Objekte der Klasse Zufallssteuerung
     */
    public Zufallssteuerung()
    {
    }

    /**
     * Liefert einen Zufallsgenerator.
     * @return Ein Random-Objekt.
     */
    public static Random gibZufallsgenerator()
    {
        if(nutzeGemeinsam) {
            return rand;
        }
        else {
            return null; //new Random();
        }
    }
    
    /**
     * Setzt die Zufallssteuerung zurück.
     * Hat keinen Effekt, wenn für die Zufallssteuerung kein 
     * gemeinsam genutzer Zufallsgenerator verwendet wird.
     */
    public static void zuruecksetzen()
    {
        if(nutzeGemeinsam) {
            rand.setSeed(SEED);
        }
    }
}
