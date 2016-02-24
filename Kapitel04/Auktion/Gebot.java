/**
 * Eine Klasse, die Gebote in einer Auktion modelliert.
 * Ein Gebot enthält eine Referenz auf die Person, die
 * das Gebot abgegeben hat, und die Höhe des Gebots.
 * 
 * @author David J. Barnes und Michael Kölling.
 * @version 31.07.2011
 */
public class Gebot
{
    // die Person, die das Gebot abgegeben hat.
    private final Person bieter;
    // Die Höhe des Gebots. Da dies potentiell ein sehr hohes
    // Gebot sein kann, wurde der Basistyp 'long' gewählt.
    private final long hoehe;

    /**
     * Erzeuge ein Gebot.
     * @param bieter die Person, die das Gebot abgibt.
     * @param hoehe die Höhe des Gebots.
     */
    public Gebot(Person bieter, long hoehe)
    {
        this.bieter = bieter;
        this.hoehe = hoehe;
    }

    /**
     * Liefere den Bieter dieses Gebots.
     * @return den Bieter.
     */
    public Person gibBieter()
    {
        return bieter;
    }

    /**
     * Liefere die Höhe dieses Gebots.
     * @return die Höhe dieses Gebots.
     */
    public long gibHoehe()
    {
        return hoehe;
    }
}
