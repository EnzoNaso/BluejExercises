import java.util.List;

/**
 * Tier ist eine abstrakte Superklasse für Tiere. 
 * Sie verwaltet Eigenschaften, die alle Tiere gemein haben,
 * wie etwas das Alter oder eine Position.
 * 
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public abstract class Tier
{
    // Ist dieses Tier noch lebendig?
    private boolean lebendig;
    // Das Feld des Tieres
    private Feld feld;
    // Die Position dieses Tieres.
    private Position position;

    /**
     * Erzeuge ein neues Tier an der gegebenen Position im Feld.
     * 
     * @param feld das aktuelle belegte Feld
     * @param position die Position im Feld
     */
    public Tier(Feld feld, Position position)
    {
        lebendig = true;
        this.feld = feld;
        setzePosition(position);
    }
    
    /**
     * Lasse dieses Tier agieren - es soll das tun, was
     * es tun muss oder möchte.
     * @param neueTiere eine Liste zum Aufnehmen neuer Tiere.
     */
    abstract public void agiere(List<Tier> neueTiere);

    /**
     * Prüfe, ob dieses Tier noch lebendig ist.
     * @return true wenn dieses Tier noch lebendig ist.
     */
    protected boolean istLebendig()
    {
        return lebendig;
    }

    /**
     * Anzeigen, dass das Tier nicht mehr länger lebendig ist
     * Es wird aus dem Feld entfernt.
     */
    protected void sterben()
    {
        lebendig = false;
        if(position != null) {
            feld.raeumen(position);
            position = null;
            feld = null;
        }
    }

    /**
     * Liefere die Position dieses Tieres.
     * @return die Position dieses Tieres.
     */
    protected Position gibPosition()
    {
        return position;
    }
    
    /**
     * Setze das Tier auf die gegebene Position im aktuellen Feld.
     * @param neuePosition die neue Position des Tieres.
     */
    protected void setzePosition(Position neuePosition)
    {
        if(position != null) {
            feld.raeumen(position);
        }
        position = neuePosition;
        feld.platziere(this, neuePosition);
    }
    
    /**
     * Liefere das Feld des Tieres.
     * @return das Feld des Tieres.
     */
    protected Feld gibFeld()
    {
        return feld;
    }    
}
