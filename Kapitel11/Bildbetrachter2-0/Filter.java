
/**
 * Filter ist eine abstrakte Superklasse für alle Bildfilter
 * dieser Anwendung. Filter können auf Farbbilder angewendet,
 * indem die Methode "anwenden" aufgerufen wird.
 * 
 * @author Michael Kölling und David J Barnes 
 * @version 1.0
 */
public abstract class Filter
{
    private String name;

    /**
     * Erzeuge einen neuen Filter mit dem angegebenen Namen.
     * @param name der Name des Filters.
     */
    public Filter(String name)
    {
        this.name = name;
    }
    
    /**
     * Liefere den Namen dieses Filters.
     * 
     * @return  den Namen dieses Filters.
     */
    public String gibName()
    {
        return name;
    }
    
    /**
     * Wende diesen Filter auf das gegebene Bild an.
     * 
     * @param  bild  das Bild, das dieser Filter verändern soll.
     */
    public abstract void anwenden(Farbbild bild);
}
