/**
 * Die Klasse Ziegelstein modelliert einfache Ziegelsteine.
 * 
 * @autor: Michael Kölling und David J. Barnes
 * @version: 31.07.2011
 */
public class Ziegelstein
{
    // Konstante: Gewicht pro Kubikzentimeter in Gramm
    private static final int GEWICHT_PRO_CM3 = 2;

    private int hoehe;
    private int breite;
    private int tiefe;

    /**
     * Erzeuge einen Ziegelstein. Die Parameter geben die 
     * Kantenlängen in Zentimetern an.
     * @param hoehe die Höhe des Ziegelsteins
     * @param breite die Breite des Ziegelsteins
     * @param tiefe die Tiefe des Ziegelsteins
     */
    public Ziegelstein(int hoehe, int breite, int tiefe)
    {
        this.hoehe = hoehe;
        this.breite = breite;
        this.tiefe = tiefe;
    }

    /**
     * @return die Größe der Oberfläche dieses Ziegelsteins
     * in Quadratzentimetern.
     */
    public double gibOberflaeche()
    {
        double seite1 = breite * hoehe;
        double seite2 = breite * tiefe;
        double seite3 = tiefe * hoehe;
        double total = (seite1 + seite1 + seite3) * 2;

        return total;
    }

    /**
     * @return das Gewicht dieses Ziegelsteins in kg.
     */
    public double gibGewicht()
    {
        return (gibVolumen() * GEWICHT_PRO_CM3) / 1000;
    }

    /**
     * @return das Volumen dieses Ziegelsteins in
     * Kubikzentimetern.
     */
    public int gibVolumen()
    {
        return breite * hoehe * tiefe;
    }

    /**
     * @return die Höhe dieses Ziegelsteins in Zentimetern.
     */
    public double gibHoehe()
    {
        return hoehe;
    }
}
