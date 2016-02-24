
/**
 * Ein Taxi kann einen einzelnen Fahrgast transportieren.
 * 
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class Taxi extends Fahrzeug
{
    private Fahrgast fahrgast;
    
    /**
     * Konstruktor für Objekte der Klasse Taxi.
     * @param unternehmen das Taxi-Unternehmen. Darf nicht null sein.
     * @param position die Startposition des Fahrzeugs.
     *                 Darf nicht null sein.
     * @throws NullPointerException wenn Unternehmen oder 
     *                              Position null sind.
     */
    public Taxi(Taxiunternehmen unternehmen, Position position)
    {
        super(unternehmen, position);
    }

    /**
     * Führe die Aktionen eines Taxis aus.
     */
    public void agiere()
    {
        Position ziel = gibZiel();
        if(ziel != null) {
            // nächste Position ermitteln.
            Position naechste = gibPosition().naechstePosition(ziel);
            setzePosition(naechste);
            if(naechste.equals(ziel)) {
                if(fahrgast != null) {
                    signalisiereFahrgastAmZiel(fahrgast);
                    fahrgastAbsetzen();
                }
                else {
                    signalisiereAnkunftAbholung();
                }
            }
        }
        else {
            erhoeheUngenutztZaehler();
        }
    }

    /**
     * Ist dieses Taxi frei?
     * @return ob dieses Taxi frei ist.
     */
    public boolean istFrei()
    {
        return gibZiel() == null && fahrgast == null;
    }
    
    /**
     * Setze einen Abholpunkt. Dieser wird zur
     * Zielposition.
     * @param position der Abholpunkt.
     */
    public void setzeAbholpunkt(Position position)
    {
        setzeZiel(position);
    }
    
    /**
     * Nimm einen Fahrgast auf.
     * Setze sein Ziel als die Zielposition.
     * @param fahrgast der Fahrgast.
     */
    public void aufnehmen(Fahrgast fahrgast)
    {
        this.fahrgast = fahrgast;
        setzeZiel(fahrgast.gibZiel());
    }

    /**
     * Setze den Fahrgast ab.
     */
    public void fahrgastAbsetzen()
    {
        fahrgast = null;
        loescheZiel();
    }

    /**
     * Liefere Details über ein Taxi, etwa seine Position.
     * @return Eine String-Darstellung dieses Taxis.
     */
    public String toString()
    {
        return "Taxi bei " + gibPosition();
    }

}
