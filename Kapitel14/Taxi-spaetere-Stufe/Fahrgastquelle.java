import java.util.Random;

/**
 * Eine Quelle, die periodisch Fahrgäste erzeugt.
 * Zählt die Fahrgäste, die vergeblich ein Taxi
 * gerufen haben.
 * 
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class Fahrgastquelle implements Akteur
{    
    private Stadt stadt;
    private Taxiunternehmen unternehmen;
    private Random zufall;
    private static final double ERZEUGUNGSWAHRSCHEINLICHKEIT = 0.06;
    private int vergeblicheTaxirufe;

    /**
     * Konstruktor für Objekte der Klasse FahrgastQuelle.
     * @param stadt die Stadt der Simulation.
     *              Darf nicht null sein.
     * @param unternehmen das gewählte Unternehmen.
     *                    Darf nicht null sein.
     * @throws NullPointerException wenn stadt oder
     *                unternehmen null ist.
     */
    public Fahrgastquelle(Stadt stadt, Taxiunternehmen unternehmen) {
        if (stadt == null) {
            throw new NullPointerException("stadt");
        }
        if (unternehmen == null) {
            throw new NullPointerException("unternehmen");
        }
        this.stadt = stadt;
        this.unternehmen = unternehmen;
        // Wir initialisieren den Zufallsgenerator mit einem
        // festen Wert, um reproduzierbare Werte zu bekommen.
        // Für "mehr" Zufall kann dies geändert werden.
        zufall = new Random(12345);
        vergeblicheTaxirufe = 0;
    }

    /**
     * Erzeuge zufallsabhängig einen neuen Fahrgast.
     * Vermerke vergebliche Taxirufe.
     */
    public void agiere() {
        if (zufall.nextDouble() <= ERZEUGUNGSWAHRSCHEINLICHKEIT) {
            Fahrgast fahrgast = erzeugeFahrgast();
            if (unternehmen.taxirufFuer(fahrgast)) {
                stadt.gegenstandEinfuegen(fahrgast);
            }
            else {
                vergeblicheTaxirufe++;
            }
        }
    }

    /**
     * @return die Anzahl der vergeblichen Taxirufe.
     */
    public int gibVergeblicheTaxirufe() {
        return vergeblicheTaxirufe;
    }

    /**
     * Erzeuge einen neuen Fahrgast, bei dem sich 
     * Abholpunkt und Ziel garantiert unterscheiden.
     * @return den erzeugten Fahrgast.
     */
    private Fahrgast erzeugeFahrgast() {
        int stadtBreite = stadt.gibBreite();
        int stadtHoehe = stadt.gibHoehe();

        Position abholpunkt =
            new Position(
                zufall.nextInt(stadtBreite),
                zufall.nextInt(stadtHoehe));
        Position ziel;
        do {
            ziel = new Position(zufall.nextInt(stadtBreite),
                                zufall.nextInt(stadtHoehe));
        }
        while (abholpunkt.equals(ziel));
        return new Fahrgast(abholpunkt, ziel);
    }
}
