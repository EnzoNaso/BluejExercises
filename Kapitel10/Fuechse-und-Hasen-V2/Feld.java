import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Ein rechteckiges Gitter von Feldpositionen.
 * Jede Position kann ein einzelnes Tier aufnehmen.
 * 
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class Feld
{
    private static final Random rand = Zufallssteuerung.gibZufallsgenerator();
    
    // Die Tiefe und die Breite des Feldes
    private int tiefe, breite;
    // Speicher für die Tiere
    private Object[][] feld;

    /**
     * Erzeuge ein Feld mit den angegebenen Dimensionen.
     * @param tiefe die Tiefe des Feldes.
     * @param breite die Breite des Feldes.
     */
    public Feld(int tiefe, int breite)
    {
        this.tiefe = tiefe;
        this.breite = breite;
        feld = new Object[tiefe][breite];
    }
    
    /**
     * Räume das Feld.
     */
    public void raeumen()
    {
        for(int zeile = 0; zeile < tiefe; zeile++) {
            for(int spalte = 0; spalte < breite; spalte++) {
                feld[zeile][spalte] = null;
            }
        }
    }
    
    /**
     * Räume die gegebene Position.
     * @param position die zu leerende Position
     */
    public void raeumen(Position position)
    {
        feld[position.gibZeile()][position.gibSpalte()] = null;
    }
    
    /**
     * Platziere das gegebene Tier an der angegebenen Position.
     * Wenn an der Position bereits ein Tier eingetragen ist,
     * geht es verloren.
     * @param tier das Tier das platziert werden soll.
     * @param zeile die Zeilenkoordinate der Position.
     * @param spalte die Spaltenkoordinate der Position.
     */
    public void platziere(Object tier, int zeile, int spalte)
    {
        platziere(tier, new Position(zeile, spalte));
    }
    
    /**
     * Platziere das gegebene Tier an der angegebenen Position.
     * Wenn an der Position bereits ein Tier eingetragen ist,
     * geht es verloren.
     * @param tier das Tier, das platziert werden soll.
     * @param position die Position, an der das Tier platziert werden soll.
     */
    public void platziere(Object tier, Position position)
    {
        feld[position.gibZeile()][position.gibSpalte()] = tier;
    }
    
    /**
     * Liefere das Tier an der angegebenen Position, falls vorhanden.
     * @param position die gewünschte Position.
     * @return das Tier an der angegebenen Position oder null, wenn
     *         dort kein Tier eingetragen ist.
     */
    public Object gibObjektAn(Position position)
    {
        return gibObjektAn(position.gibZeile(), position.gibSpalte());
    }
    
    /**
     * Liefere das Tier an der angegebenen Position, falls vorhanden.
     * @param zeile die gewünschte Zeile.
     * @param spalte die gewünschte Spalte.
     * @return das Tier an der angegebenen Position oder null, wenn
     *         dort kein Tier eingetragen ist.
     */
    public Object gibObjektAn(int zeile, int spalte)
    {
        return feld[zeile][spalte];
    }
    
    /**
     * Wähle zufällig eine der Positionen, die an die gegebene Position
     * angrenzen, oder die gegebene Position selbst.
     * Die gelieferte Position liegt innerhalb der gültigen Grenzen
     * dieses Feldes.
     * @param position die Position, von der ein Nachbar zu wählen ist.
     * @return eine gültige Position innerhalb dieses Feldes. Das kann
     *         auch die gegebene Position selbst sein.
     */
    public Position zufaelligeNachbarposition(Position position)
    {
        List<Position> nachbarn = nachbarpositionen(position);
        return nachbarn.get(0);
    }
    
    /**
     * Liefert eine gemischte Liste von freien Nachbarposition.
     * @param position die Position, für die Nachbarpositionen
     *                 zu liefern ist.
     * @return eine Liste freier Nachbarpositionen.
     */
    public List<Position> freieNachbarpositionen(Position position)
    {
        List<Position> frei = new LinkedList<Position>();
        List<Position> nachbarn = nachbarpositionen(position);
        for(Position naechste : nachbarn) {
            if(gibObjektAn(naechste) == null) {
                frei.add(naechste);
            }
        }
        return frei;
    }
    
    /**
     * Versuche, eine freie Nachbarposition zur gegebenen Position zu
     * finden. Wenn es keine gibt, liefere null.
     * Die gelieferte Position liegt innerhalb der Feldgrenzen.
     * @param position die Position, für die eine Nachbarposition
     *                 zu liefern ist.
     * @return eine gültige Position innerhalb der Feldgrenzen. 
     */
    public Position freieNachbarposition(Position position)
    {
        // Die verfügbaren freien Nachbarpositionen
        List<Position> frei = freieNachbarpositionen(position);
        if(frei.size() > 0) {
            return frei.get(0);
        } 
        else {
            return null;
        }
    }

    /**
     * Liefert eine gemischte Liste von Nachbarpositionen
     * zu der gegebenen Position. Diese Liste enthält nicht die gegebene 
     * Position selbst. Alle Positionen liegen innerhalb des Feldes.
     * @param position die Position, für die Nachbarpositionen zu liefern sind.
     * @return eine Liste der Nachbarpositionen zur gegebenen Position.
     */
    public List<Position> nachbarpositionen(Position position)
    {
        assert position != null : "Keine Position an nachbarpostionen uebergeben";
        // Die Liste der zurueckzuliefernden Positionen
        List<Position> positionen = new LinkedList<Position>();
        if(position != null) {
            int zeile = position.gibZeile();
            int spalte = position.gibSpalte();
            for(int zDiff = -1; zDiff <= 1; zDiff++) {
                int naechsteZeile = zeile + zDiff;
                if(naechsteZeile >= 0 && naechsteZeile < tiefe) {
                    for(int sDiff = -1; sDiff <= 1; sDiff++) {
                        int naechsteSpalte = spalte + sDiff;
                        // Ungueltige Positionen und Ausgangsposition ausschliessen.
                        if(naechsteSpalte >= 0 && naechsteSpalte < breite && (zDiff != 0 || sDiff != 0)) {
                            positionen.add(new Position(naechsteZeile, naechsteSpalte));
                        }
                    }
                }
            }          
            // Mische die Liste. Verschiedene andere Methoden verlassen sich darauf, 
            // dass die Liste ungeordnet ist.
            Collections.shuffle(positionen, rand);
        }
        return positionen;
    }

    /**
     * Liefere die Tiefe dieses Feldes.
     * @return die Tiefe dieses Feldes.
     */
    public int gibTiefe()
    {
        return tiefe;
    }
    
    /**
     * Liefere die Breite dieses Feldes.
     * @return die Breite dieses Feldes.
     */
    public int gibBreite()
    {
        return breite;
    }
}
