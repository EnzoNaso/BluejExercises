import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
    
/**
 * Eine Stadt, in der sich Gegenstände befinden.
 * 
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class Stadt
{
    private List<Object> gegenstaende;
    private int breite;
    private int hoehe;
    
    private static final int STANDARD_BREITE = 35;
    private static final int STANDARD_HOEHE = 35;

    /**
     * Konstruktor für Objekte der Klasse Stadt
     * @param breite die Breite der Stadt.
     * @param hoehe die Höhe der Stadt.
     */
    public Stadt(int breite, int hoehe)
    {
        if(breite < 1) {
            throw new IllegalArgumentException(
                        "Breite muss positiv sein: " +
                        breite);
        }
        if(hoehe < 1) {
            throw new IllegalArgumentException(
                        "Höhe muss positiv sein: " +
                        hoehe);
        }
        this.breite = breite;
        this.hoehe = hoehe;
        gegenstaende = new LinkedList<Object>();
    }
    
    /**
     * Erzeuge eine Stadt mit einer Standardgröße.
     */
    public Stadt()
    {
        this(STANDARD_BREITE, STANDARD_HOEHE);
    }
    
    /**
     * @return einen Iterator über die Gegenstände.
     */
    public Iterator gibGegenstaende()
    {
        return gegenstaende.iterator();
    }

    /**
     * Füge den gegebenen Gegenstand in die Stadt ein.
     * @param gegenstand der einzufügende Gegenstand.
     */
    public void gegenstandEinfuegen(Object gegenstand)
    {
        if(gegenstaende.contains(gegenstand)) {
            throw new IllegalArgumentException(
                gegenstand + " bereits in der Stadt vorhanden.");
        }
        gegenstaende.add(gegenstand);
    }

    /**
     * Entferne den gegebenen Gegenstand aus der Stadt.
     * @param gegenstand der zu entfernende Gegenstand.
     */
    public void gegenstandEntfernen(Object gegenstand)
    {
        if(!gegenstaende.remove(gegenstand)) {
            throw new IllegalArgumentException(
                        gegenstand + " ist nicht in der Stadt.");
        }
    }
        
    /**
     * @return eine String-Darstellung der Stadt.
     */
    public String toString()
    {
        return "Stadt mit Größe " + breite + " x " + hoehe;
    }
    
    /**
     * @return die Breite dieser Stadt.
     */
    public int gibBreite()
    {
        return breite;
    }
    
    /**
     * @return die Höhe dieser Stadt.
     */
    public int gibHoehe()
    {
        return hoehe;
    }
}
