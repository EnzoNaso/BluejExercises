import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Ein Taxi kann einen einzelnen Fahrgast transportieren.
 * 
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class Taxi extends Fahrzeug implements GrafischerGegenstand
{
    private Fahrgast fahrgast;

    // Unterschiedliche Grafiken für leeres und beladenes Taxi.
    private Image leerGrafik, beladenGrafik;
      
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
        // Laden der beiden Grafiken.
        leerGrafik = new ImageIcon(getClass().getResource(
                                "grafiken/taxi.jpg")).getImage();

        beladenGrafik = new ImageIcon(getClass().getResource(
                                "grafiken/taxi+person.jpg")).getImage();
    }

    /**
     * Das Ziel ansteuern, falls wir eines haben.
     * Ansonsten vermerken, dass wir ungenutzt sind.
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
     * Liefere eine Grafik, die den Zustand dieses
     * Taxis beschreibt: entweder leer oder mit 
     * einem Fahrgast beladen.
     */
    public Image gibGrafik()
    {
        if(fahrgast != null) {
            return beladenGrafik;
        }
        else {
            return leerGrafik;
        }
    }

    /**
     * Liefere Details über ein Taxi, etwa seine Position.
     * @return eine String-Darstellung dieses Taxis.
     */
    public String toString()
    {
        return "Taxi bei " + gibPosition();
    }

}
