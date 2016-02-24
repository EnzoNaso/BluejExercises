import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

/**
 * Modell eines Taxiunternehmens, das verschiedene Fahrzeugtypen
 * betreibt.
 * 
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class Taxiunternehmen  
{
    private List<Fahrzeug> fahrzeuge;

    /**
     * Konstruktor für Objekte der Klasse TaxiUnternehmen
     */
    public Taxiunternehmen()
    {
        fahrzeuge = new LinkedList<Fahrzeug>();
    }

    /**
     * Rufe ein Taxi für den angegebenen Fahrgast.
     * @param fahrgast der Fahrgast, der gefahren werden möchte.
     * @return ob ein freies Fahrzeug verfügbar ist.
     */
    public boolean taxirufFuer(Fahrgast fahrgast)
    {
        Fahrzeug fahrzeug = waehleFahrzeug();
        if(fahrzeug != null) {
            fahrzeug.setzeAbholpunkt(fahrgast.gibAbholpunkt());
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Ein Fahrzeug hat den Abholpunkt erreicht.
     * @param fahrzeug das Fahrzeug am Abholpunkt.
     */
    public void ankunftAmAbholpunkt(Fahrzeug fahrzeug)
    {
    }
    
    /**
     * Ein Fahrzeug hat das Ziel eines Fahrgastes erreicht.
     * @param fahrzeug das Fahrzeug am Zielpunkt.
     * @param fahrgast der abgesetzte Fahrgast.
     */
    public void ankunftAmZiel(Fahrzeug fahrzeug,
                              Fahrgast fahrgast)
    {
    }
    
    /**
     * Wähle ein freies Fahrzeug, wenn verfügbar.
     * @return ein freies Fahrzeug oder null, wenn keines verfügbar ist.
     */
    private Fahrzeug waehleFahrzeug()
    {
        Iterator<Fahrzeug> it = fahrzeuge.iterator();
        while(it.hasNext()) {
            Fahrzeug fahrzeug = it.next();
            if(fahrzeug.istFrei()) {
                return fahrzeug;
            }
        }
        return null;
    }
}
