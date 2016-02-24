/**
 * Diese Klasse definiert Zähler für die Akteurstypen
 * in einer Simulation.
 * Ein Zähler wird über einen Namen identifiziert und 
 * zählt, wieviele Akteure des Typs innerhalb der Simulation
 * jeweils existieren.
 * 
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class Zaehler
{
    // Ein Name für den Akteurstyp in dieser Simulation
    private String name;
    // Wie viele von diesem Typ existieren in der Simulation.
    private int zaehler;

    /**
     * Initialisiere mit dem Namen des Typs.
     * @param name Ein Name, z.B. "Fuchs".
     */
    public Zaehler(String name)
    {
        this.name = name;
        zaehler = 0;
    }
    
    /**
     * @return den Namen des Typs dieses Zählers.
     */
    public String gibName()
    {
        return name;
    }

    /**
     * @return den aktuellen Zählerstand dieses Typs.
     */
    public int gibStand()
    {
        return zaehler;
    }

    /**
     * Erh�he diesen Zähler um Eins.
     */
    public void erhoehen()
    {
        zaehler++;
    }
    
    /**
     * Setze diesen Zähler auf null zur�ck.
     */
    public void zuruecksetzen()
    {
        zaehler = 0;
    }
}
