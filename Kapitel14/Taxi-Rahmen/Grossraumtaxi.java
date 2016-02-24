import java.util.List;
import java.util.LinkedList;
    
/**
 * Ein Grossraumtaxi kann mehrere Fahrgäste transportieren.
 * 
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class Grossraumtaxi extends Fahrzeug
{
    // Die Liste der Ziele dieses Großraumtaxis.
    private List<Position> ziele;
    // Die Liste der Fahrgäste im Großraumtaxi.
    private List<Fahrgast> fahrgaeste;

    /**
     * Konstruktor für Objekte der Klasse Shuttle.
     * @param unternehmen das Taxi-Unternehmen. Darf nicht null sein.
     * @param position die Startposition des Fahrzeugs. Darf nicht null sein.
     * @throws NullPointerException wenn Unternehmen oder Position null sind.
     */
    public Grossraumtaxi(Taxiunternehmen unternehmen, Position position)
    {
        super(unternehmen, position);
        ziele = new LinkedList<Position>();
        fahrgaeste = new LinkedList<Fahrgast>();
    }

    /**
     * Ist dieses Fahrzeug frei?
     * @return ob dieses Fahrzeug frei ist.
     */
    public boolean istFrei()
    {
        return true;
    }
    
    /**
     * Setze einen Abholpunkt.
     * @param position der Abholpunkt.
     */
    public void setzeAbholpunkt(Position position)
    {
        ziele.add(position);
        waehleNaechstesZiel();
    }
    
    /**
     * Nimm einen Fahrgast auf.
     * Füge sein Ziel zur Liste der Ziele hinzu.
     * @param fahrgast der aufgenommene Fahrgast.
     */
    public void aufnehmen(Fahrgast fahrgast)
    {
        fahrgaeste.add(fahrgast);
        ziele.add(fahrgast.gibZiel());
        waehleNaechstesZiel();
    }

    /**
     * Wähle das nächste Ziel, abhängig von der Liste
     * der möglichen Ziele.
     */
    private void waehleNaechstesZiel()
    {
    }

    /**
     * Setze einen Fahrgast ab, dessen Ziel die aktuelle
     * Position ist.
     */
    public void fahrgastAbsetzen()
    {
    }
}
