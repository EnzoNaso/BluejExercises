import java.util.List;
import java.util.Random;

/**
 * Ein einfaches Modell eines Hasen.
 * Ein Hase altert, bewegt sich, gebärt Nachwuchs und stirbt.
 * 
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class Hase
{
    // Eigenschaften aller Hasen (Klassenvariablen).

    // Das Alter, in dem ein Hase gebärfähig wird.
    private static final int GEBAER_ALTER = 5;
    // Das Höchstalter eines Hasen.
    private static final int MAX_ALTER = 40;
    // Die Wahrscheinlichkeit, mit der ein Hase Nachwuchs gebärt.
    private static final double GEBAER_WAHRSCHEINLICHKEIT = 0.12;
    // Die maximale Größe eines Wurfes (Anzahl der Jungen)
    private static final int MAX_WURFGROESSE = 4;
    // Ein gemeinsamer Zufallsgenerator, der die Geburten steuert.
    private static final Random rand = Zufallssteuerung.gibZufallsgenerator();
    
    // Individuelle Eigenschaften eines Hasen (Instanzfelder).
    
    // Das Alter dieses Hasen.
    private int alter;
    // Ist dieser Hase noch lebendig?
    private boolean lebendig;
    // Die Position dieses Hasen
    private Position position;
    // Das belegte Feld
    private Feld feld;

    /**
     * Erzeuge einen neuen Hasen. Ein neuer Hase kann das Alter 0 
     * (neugeboren) oder ein zufälliges Alter haben.
     * @param zufaelligesAlter soll der Hase ein zufälliges Alter haben?
     * @param feld das aktuelle belegte Feld
     * @param position die Position im Feld
     */
    public Hase(boolean zufaelligesAlter, Feld feld, Position position)
    {
        alter = 0;
        lebendig = true;
        this.feld = feld;
        setzePosition(position);
        if(zufaelligesAlter) {
            alter = rand.nextInt(MAX_ALTER);
        }
    }
    
    /**
     * Das ist was ein Hase die meiste Zeit tut - er läuft herum.
     * Manchmal gebärt er Nachwuchs und irgendwann stirbt er
     * an Altersschwäche.
     * @param neueHasen eine Liste zum Zurückliefern der neugeborenen Hasen.
     */
    public void laufe(List<Hase> neueHasen)
    {
        alterErhoehen();
        if(lebendig) {
            gebaereNachwuchs(neueHasen);
            // nur in das nächste Feld setzen, wenn eine Position frei ist
            Position neuePosition = feld.freieNachbarposition(position);
            if(neuePosition != null) {
                setzePosition(neuePosition);
            }
            else {
                // Überpopulation 
                sterben();
            }
        }
    }

    /**
     * Prüfe, ob dieser Hase noch lebendig ist.
     * @return true wenn dieser Hase noch lebt.
     */
    public boolean istLebendig()
    {
        return lebendig;
    }

    /**
     * Liefere die Position des Hasen.
     * @return die Position des Hasen.
     */
    public Position gibPosition()
    {
        return position;
    }
    
    /**
     * Anzeigen, dass der Hase nicht mehr laenger lebendig ist.
     * Hase aus dem Feld entfernen.
     */
    public void sterben()
    {
        lebendig = false;
        if(position != null) {
            feld.raeumen(position);
            position = null;
            feld = null;
        }
    }

    /**
     * Setze den Hasen auf die gegebene Position im aktuellen Feld.
     * @param neuePosition die neue Position dieses Hasen.
     */
    public void setzePosition(Position neuePosition)
    {
        if(position != null) {
            feld.raeumen(position);
        }
        position = neuePosition;
        feld.platziere(this, neuePosition);
    }

    /**
     * Erhöhe das Alter. 
     * Dies kann zum Tod des Hasen führen.
     */
    private void alterErhoehen()
    {
        alter++;
        if(alter > MAX_ALTER) {
            sterben();
        }
    }
       
    /**
     * Prüfe, ob dieser Hase in diesem Schritt gebären kann.
     * Neugeborene kommen in freie Nachbarpositionen.
     * @param neueHasen eine Liste zum Zurückliefern der neugeborenen Hasen.
     */
    private void gebaereNachwuchs(List<Hase> neueHasen)
    {
        // Neugeborene kommen in freie Nachbarpositionen.
        // Freie Nachbarpositionen abfragen.
        List<Position> frei = feld.freieNachbarpositionen(position);
        int geburten = traechtig();
        for(int b = 0; b < geburten && frei.size() > 0; b++) {
            Position pos = frei.remove(0);
            Hase jung = new Hase(false, feld, pos);
            neueHasen.add(jung);
        }
    }

    /**
     * Erzeuge eine Zahl für die Wurfgroesse, wenn der Hase
     * gebaeren kann.
     * @return  Wurfgroesse (kann null sein).
     */
    private int traechtig()
    {
        int wurfgroesse = 0;
        if(kannGebaeren() && rand.nextDouble() <= GEBAER_WAHRSCHEINLICHKEIT) {
            wurfgroesse = rand.nextInt(MAX_WURFGROESSE) + 1;
        }
        return wurfgroesse;
    }
    
    /**
     * Ein Hase kann gebären, wenn er das gebärfähige
     * Alter erreicht hat.
     */
    private boolean kannGebaeren()
    {
        return alter >= GEBAER_ALTER;
    }
}
