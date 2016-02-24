import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

/**
 * Modell eines Taxi-Unternehmens, das verschiedene
 * Fahrzeugtypen betreibt. Diese Version betreibt nur Taxis.
 * 
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class Taxiunternehmen
{
    // Die Fahrzeuge dieses Unternehmens.
    private List<Fahrzeug> fahrzeuge;
    private Stadt stadt;
    // Die Zuordnungen von Fahrzeugen zu Passagieren,
    // die sie transportieren.
    private Map<Fahrzeug, Fahrgast> zuordnungen;

    private static final int ANZAHL_TAXIS = 3;

    /**
     * @param stadt die Stadt.
     */
    public Taxiunternehmen(Stadt stadt)
    {
        this.stadt = stadt;
        fahrzeuge = new LinkedList<Fahrzeug>();
        zuordnungen = new HashMap<Fahrzeug, Fahrgast>();
        fahrzeugeEinrichten();
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
        stadt.gegenstandEntfernen(fahrgast);
        fahrzeug.aufnehmen(fahrgast);
    }

    /**
     * Ein Fahrzeug hat das Ziel eines Fahrgastes erreicht.
     * @param fahrzeug das Fahrzeug am Zielpunkt.
     * @param fahrgast der abgesetzte Fahrgast.
     */
    public void ankunftAmZiel(Fahrzeug fahrzeug, Fahrgast fahrgast)
    {
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
     * @return ein freies Fahrzeug oder null, wenn
     *         keines verfügbar ist.
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
    
    /**
     * Richte die Fahrzeuge dieses Unternehmens ein.
     * Die optimale Anzahl an Fahrzeugen sollte durch
     * die Analyse der Daten ermittelt werden, die
     * durch diese Simulation geliefert werden.
     * 
     * Fahrzeuge starten an zufälligen Positionen.
     */
    private void fahrzeugeEinrichten()
    {
        int stadtBreite = stadt.gibBreite();
        int stadtHoehe = stadt.gibHoehe();
        // Ein fester Startwert für den Zufallsgenerator
        // ermöglicht vorhersagbares Verhalten.
        // Für "mehr" Zufall können unterschiedliche Werte
        // verwendet werden.
        Random zufall = new Random(12345);

        // Erzeugen der Taxis.
        for(int i = 0; i < ANZAHL_TAXIS; i++){
            Taxi taxi =
                new Taxi(this,
                         new Position(zufall.nextInt(stadtBreite),
                                      zufall.nextInt(stadtHoehe)));
            fahrzeuge.add(taxi);
            stadt.gegenstandEinfuegen(taxi);
        }
    }

}
