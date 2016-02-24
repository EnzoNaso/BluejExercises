import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * Ein simples Modell eines Fuchses.
 * Füchse altern, bewegen sich, fressen Hasen und sterben.
 * 
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class Fuchs extends Tier
{
    // Eigenschaften aller Füchse (Klassenvariablen)
    
    // Das Alter, in dem ein Fuchs gebärfähig wird.
    private static final int GEBAER_ALTER = 15;
    // Das Höchstalter eines Fuchses.
    private static final int MAX_ALTER = 150;
    // Die Wahrscheinlichkeit, mit der ein Fuchs Nachwuchs gebärt.
    private static final double GEBAER_WAHRSCHEINLICHKEIT = 0.08;
    // Die maximale Größe eines Wurfes (Anzahl der Jungen).
    private static final int MAX_WURFGROESSE = 2;
    // Der Nährwert eines einzelnen Hasen. Letztendlich ist
    // dies die Anzahl der Schritte, die ein Fuchs bis zur
    //nächsten Mahlzeit laufen kann.
    private static final int HASEN_NAEHRWERT = 9;
    // Ein gemeinsamer Zufallsgenerator, der die Geburten steuert.
    private static final Random rand = Zufallssteuerung.gibZufallsgenerator();
    
    // Individuelle Eigenschaften (Instanzfelder).

    // Das Alter dieses Fuchses.
    private int alter;
    // Der Futter-Level, der durch das Fressen von Hasen erhöht wird.
    private int futterLevel;

    /**
     * Erzeuge einen Fuchs. Ein Fuchs wird entweder neugeboren
     * (Alter 0 Jahre und nicht hungrig) oder kann mit einem zufälligen Alter
     * und zufälligem Hungergefühl erzeugt werden.
     * 
     * @param zufaelligesAlter falls true, hat der neue Fuchs ein 
     *        zufälliges Alter und einen zufälligen Futter-Level.
     * @param feld das aktuelle belegte Feld
     * @param position die Position im Feld
     */
    public Fuchs(boolean zufaelligesAlter, Feld feld, Position position)
    {
        super(feld, position);
        if(zufaelligesAlter) {
            alter = rand.nextInt(MAX_ALTER);
            futterLevel = rand.nextInt(HASEN_NAEHRWERT);
        }
        else {
            alter = 0;
            futterLevel = HASEN_NAEHRWERT;
        }
    }
    
    /**
     * Das ist was ein Fuchs die meiste Zeit tut: er jagt Hasen.
     * Dabei kann er Nachwuchs gebären, vor Hunger sterben oder
     * an Altersschwäche.
     * @param neueFuechse eine Liste zum Zurückliefern neugeborener Füchse.
     */
    public void agiere(List<Tier> neueFuechse)
    {
        alterErhoehen();
        hungerVergroessern();
        if(istLebendig()) {
            gebaereNachwuchs(neueFuechse);
            // In die Richtung bewegen, in der Futter gefunden wurde.
            Position neuePosition = findeNahrung();
            if(neuePosition == null) {  
                // kein Futter - zufällig bewegen
                neuePosition = gibFeld().freieNachbarposition(gibPosition());
            }
            // Ist Bewegung möglich?
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
     * Erhöhe das Alter dieses Fuchses. Dies kann zu seinem
     * Tod führen.
     */
    private void alterErhoehen()
    {
        alter++;
        if(alter > MAX_ALTER) {
            sterben();
        }
    }
    
    /**
     * Vergrößere den Hunger dieses Fuchses. Dies kann zu seinem
     * Tode führen.
     */
    private void hungerVergroessern()
    {
        futterLevel--;
        if(futterLevel <= 0) {
            sterben();
        }
    }
    
    /**
     * Suche nach Nahrung (Hasen) in den Nachbarpositionen.
     * Es wird nur der erste lebendige Hase gefressen.
     * @return die Position mit Nahrung, oder null, wenn keine vorhanden.
     */
    private Position findeNahrung()
    {
        Feld feld = gibFeld();
        List<Position> nachbarPositionen = 
                               feld.nachbarpositionen(gibPosition());
        Iterator<Position> iter = nachbarPositionen.iterator();
        while(iter.hasNext()) {
            Position pos = iter.next();
            Object tier = feld.gibObjektAn(pos);
            if(tier instanceof Hase) {
                Hase hase = (Hase) tier;
                if(hase.istLebendig()) { 
                    hase.sterben();
                    futterLevel = HASEN_NAEHRWERT;
                    return pos;
                }
            }
        }
        return null;
    }
        
    /**
     * Prüfe, ob dieser Fuchs in diesem Schritt gebären kann.
     * Neugeborene kommen in freie Nachbarpositionen.
     * @param neueFuechse eine Liste zum Zurückliefern neugeborener Füchse.
     */
    private void gebaereNachwuchs(List<Tier> neueFuechse)
    {
        // Neugeborene kommen in freie Nachbarpositionen.
        // Freie Nachbarpositionen abfragen.
        Feld feld = gibFeld();
        List<Position> frei = feld.freieNachbarpositionen(gibPosition());
        int geburten = traechtig();
        for(int b = 0; b < geburten && frei.size() > 0; b++) {
            Position pos = frei.remove(0);
            Fuchs jung = new Fuchs(false, feld, pos);
            neueFuechse.add(jung);
        }
    }
        
    /**
     * Erzeuge eine Zahl für die Wurfgroesse, wenn der Fuchs
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
     * Ein Fuchs kann gebären, wenn er das gebärfähige
     * Alter erreicht hat.
     */
    private boolean kannGebaeren()
    {
        return alter >= GEBAER_ALTER;
    }
    
}
