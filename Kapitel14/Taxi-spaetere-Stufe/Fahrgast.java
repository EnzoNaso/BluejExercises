import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Modell eines Fahrgastes, der von einem Abholpunkt zu
 * einem Ziel gebracht werden möchte.
 * 
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class Fahrgast implements GrafischerGegenstand
{
    private Position abholpunkt;
    private Position ziel;
    private Image grafik;

    /**
     * Konstruktor für Objekte der Klasse Fahrgast.
     * @param abholpunkt der Abholpunkt, darf nicht null sein.
     * @param ziel die Zielposition, darf nicht null sein.
     * @throws NullPointerException Wenn eine der Positionen null ist.
     */
    public Fahrgast(Position abholpunkt, Position ziel)
    {
        if(abholpunkt == null) {
            throw new NullPointerException("Abholpunkt");
        }
        if(ziel == null) {
            throw new NullPointerException("Ziel");
        }
        this.abholpunkt = abholpunkt;
        this.ziel = ziel;
        // Lade die Grafik für eine Person.
        grafik = new ImageIcon(getClass().getResource(
                              "grafiken/person.jpg")).getImage();
     }

    /**
     * @return eine String-Darstellung dieser Person.
     */
    public String toString()
    {
        return "Fahrgast fährt von " +
               abholpunkt + " nach " + ziel;
    }

    /**
     * @return die Grafik für die Anzeige in der GUI.
     */
    public Image gibGrafik()
    {
        return grafik;
    }
    
    /**
     * @return die aktuelle Position dieses Fahrgastes.
     */
    public Position gibPosition()
    {
        return abholpunkt;
    }

    /**
     * @return den Abholpunkt.
     */
    public Position gibAbholpunkt()
    {
        return abholpunkt;
    }
    
    /**
     * @return das Ziel.
     */
    public Position gibZiel()
    {
        return ziel;
    }
}
