import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;

/**
 * Modell eines Taxi-Unternehmens, das verschiedene Fahrzeugtypen
 * betreibt.
 * 
 * @author David J. Barnes und Michael Kölling
 * @version 206.03.30
 */
public class Taxiunternehmen
{
    // Die Fahrzeuge dieses Unternehmens.
    private List<Fahrzeug> fahrzeuge;

    // Die Zuordnungen von Fahrzeugen zu Fahrgästen,
    // die sie transportieren.
    private Map<Fahrzeug, Fahrgast> zuordnungen;

    /**
     * Konstruktor für Objekte der Klasse TaxiUnternehmen
     */
    public Taxiunternehmen()
    {
        fahrzeuge = new LinkedList<Fahrzeug>();
        zuordnungen = new HashMap<Fahrzeug, Fahrgast>();
    }

    /**
     * Rufe ein Taxi für den angegebenen Fahrgast.
     * @param fahrgast der Fahrgast, der gefahren werden möchte.
     * @return ob ein freies Fahrzeug verfügbar ist.
     */
    public boolean taxirufFuer(Fahrgast fahrgast)
    {
        Fahrzeug fahrzeug = waehleFahrzeug();
        if (fahrzeug != null) {
            zuordnungen.put(fahrzeug, fahrgast);
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
     * @throws FehlenderFahrgastException wenn kein Fahrgast
     *                  am Abholpunkt wartet.
     */
    public void ankunftAmAbholpunkt(Fahrzeug fahrzeug)
    {
        Fahrgast fahrgast = zuordnungen.remove(fahrzeug);
        if (fahrgast == null) {
            throw new FehlenderFahrgastException(fahrzeug);
        }
        System.out.println(fahrzeug + " nimmt Fahrgast "
                           + fahrgast + " auf.");
        fahrzeug.aufnehmen(fahrgast);

    }

    /**
     * Ein Fahrzeug hat das Ziel eines Fahrgastes erreicht.
     * @param fahrzeug das Fahrzeug am Zielpunkt.
     * @param fahrgast der abgesetzte Fahrgast.
     */
    public void ankunftAmZiel(Fahrzeug fahrzeug, Fahrgast fahrgast)
    {
        System.out.println(fahrzeug + " lädt "
                           + fahrgast + " ab.");
    }

    /**
     * @return die Liste der Fahrzeuge.
     */
    public List<Fahrzeug> gibFahrzeuge()
    {
        return fahrzeuge;
    }

    /**
     * Wähle ein freies Fahrzeug, wenn verfügbar.
     * @return ein freies Fahrzeug oder null,
     *         wenn keines verfügbar ist.
     */
    private Fahrzeug waehleFahrzeug()
    {
        Iterator<Fahrzeug> it = fahrzeuge.iterator();
        while (it.hasNext()) {
            Fahrzeug fahrzeug = it.next();
            if (fahrzeug.istFrei()) {
                return fahrzeug;
            }
        }
        return null;
    }
}
