import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Die Ansicht zeigt für jede Position ein gefärbtes Rechteck,
 * das den jeweiligen Inhalt repräsentiert.
 * Die Farben für die verschiedenen Tierarten können mit
 * der Methode setzeFarbe definiert werden.
 * 
 * @author Michael Kölling und David J. Barnes
 * @version 31.07.2011
 */
public class GitterAnsicht extends JFrame implements Simulationsansicht
{
    // Die Farbe für leere Positionen
    private static final Color LEER_FARBE = Color.white;

    // Die Farbe für Objekte ohne definierte Farbe
    private static final Color UNDEF_FARBE = Color.gray;

    private final String SCHRITT_PREFIX = "Schritt: ";
    private final String POPULATION_PREFIX = "Population: ";
    private JLabel schrittLabel, population;
    private Feldansicht feldansicht;
    
    // Eine Map für die Farben der Simulationsteilnehmer
    private Map<Class, Color> farben;
    // Ein Statistik-Objekt zur Berechnung und Speicherung
    // von Simulationsdaten
    private FeldStatistik stats;

    /**
     * Erzeuge eine Ansicht mit der gegebenen Breite und Höhe.
     * @param hoehe Die Höhe der Simulation.
     * @param breite Die Breite der Simulation.
     */
    public GitterAnsicht(int hoehe, int breite)
    {
        stats = new FeldStatistik();
        farben = new HashMap<Class, Color>();

        setTitle("Simulation von Füchsen und Hasen");
        schrittLabel = new JLabel(SCHRITT_PREFIX, JLabel.CENTER);
        population = new JLabel(POPULATION_PREFIX, JLabel.CENTER);
        
        setLocation(20, 50);
        
        feldansicht = new Feldansicht(hoehe, breite);

        Container inhalt = getContentPane();
        inhalt.add(schrittLabel, BorderLayout.NORTH);
        inhalt.add(feldansicht, BorderLayout.CENTER);
        inhalt.add(population, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }
    
    /**
     * Definiere eine Farbe für die gegebene Tierklasse.
     * @param tierklasse Das Klassenobjekt der Tierklasse.
     * @param farbe Die zu benutzende Farbe für die Tierklasse. 
     */
    public void setzeFarbe(Class tierklasse, Color farbe)
    {
        farben.put(tierklasse, farbe);
    }

    /**
     * @return die definierte Farbe für die gegebene Tierklasse.
     */
    private Color gibFarbe(Class tierklasse)
    {
        Color farbe = farben.get(tierklasse);
        if(farbe == null) {
            // für die gegebene Klasse ist keine Farbe definiert
            return UNDEF_FARBE;
        }
        else {
            return farbe;
        }
    }

    /**
     * Zeige den aktuellen Zustand des Feldes.
     * @param schritt welcher Iterationsschritt ist dies.
     * @param feld das Feld, das angezeigt werden soll.
     */
    public void zeigeStatus(int schritt, Feld feld)
    {
        if(!isVisible())
            setVisible(true);

        schrittLabel.setText(SCHRITT_PREFIX + schritt);
        stats.zuruecksetzen();
        
        feldansicht.zeichnenVorbereiten();
            
        for(int zeile = 0; zeile < feld.gibTiefe(); zeile++) {
            for(int spalte = 0; spalte < feld.gibBreite(); spalte++) {
                Object tier = feld.gibObjektAn(zeile, spalte);
                if(tier != null) {
                    stats.erhoeheZaehler(tier.getClass());
                    feldansicht.zeichneMarkierung(spalte, zeile, gibFarbe(tier.getClass()));
                }
                else {
                    feldansicht.zeichneMarkierung(spalte, zeile, LEER_FARBE);
                }
            }
        }
        stats.zaehlungBeendet();

        population.setText(POPULATION_PREFIX + stats.gibBewohnerInfo(feld));
        feldansicht.repaint();
    }

    /**
     * Entscheide, ob die Simulation weiterlaufen soll.
     * @return true wenn noch mehr als eine Spezies lebendig ist.
     */
    public boolean istAktiv(Feld feld)
    {
        return stats.istAktiv(feld);
    }
    
    /**
     * Bereite einen neuen Lauf vor.
     */
    public void zuruecksetzen()
    {
    }
    
    /**
     * Liefere eine grafische Ansicht eines rechteckigen Feldes.
     * Dies ist eine geschachtelte Klasse (eine Klasse, die
     * innerhalb einer anderen Klasse definiert ist), die eine
     * eigene grafische Komponente für die Benutzungsschnittstelle
     * definiert. Diese Komponente zeigt das Feld an.
     * Dies ist fortgeschrittene GUI-Technik - Sie können sie
     * für Ihr Projekt ignorieren, wenn Sie wollen.
     */
    private class Feldansicht extends JPanel
    {
        private static final long serialVersionUID = 20060330L;
        private final int DEHN_FAKTOR = 6;

        private int feldBreite, feldHoehe;
        private int xFaktor, yFaktor;
        Dimension groesse;
        private Graphics g;
        private Image feldImage;

        /**
         * Erzeuge eine neue Komponente zur Feldansicht.
         */
        public Feldansicht(int hoehe, int breite)
        {
            feldHoehe = hoehe;
            feldBreite = breite;
            groesse = new Dimension(0, 0);
        }

        /**
         * Der GUI-Verwaltung mitteilen, wie groß wir sein wollen.
         * Der Name der Methode ist durch die GUI-Verwaltung festgelegt.
         */
        public Dimension getPreferredSize()
        {
            return new Dimension(feldBreite * DEHN_FAKTOR,
                                 feldHoehe * DEHN_FAKTOR);
        }
        
        /**
         * Bereite eine neue Zeichenrunde vor. Da die Komponente
         * in der Größe geändert werden kann, muss der Maßstab neu
         * berechnet werden.
         */
        public void zeichnenVorbereiten()
        {
            if(! groesse.equals(getSize())) {  // Größe wurde geändert...
                groesse = getSize();
                feldImage = feldansicht.createImage(groesse.width, groesse.height);
                g = feldImage.getGraphics();

                xFaktor = groesse.width / feldBreite;
                if(xFaktor < 1) {
                    xFaktor = DEHN_FAKTOR;
                }
                yFaktor = groesse.height / feldHoehe;
                if(yFaktor < 1) {
                    yFaktor = DEHN_FAKTOR;
                }
            }
        }
        
        /**
         * Zeichne an der gegebenen Position ein Rechteck mit
         * der gegebenen Farbe.
         */
        public void zeichneMarkierung(int x, int y, Color farbe)
        {
            g.setColor(farbe);
            g.fillRect(x * xFaktor, y * yFaktor, xFaktor-1, yFaktor-1);
        }

        /**
         * Die Komponente für die Feldansicht muss erneut angezeigt
         * werden. Kopiere das interne Image in die Anzeige.
         * Der Name der Methode ist durch die GUI-Verwaltung festgelegt.
         */
        public void paintComponent(Graphics g)
        {
            if(feldImage != null) {
                Dimension aktuelleGroesse = getSize();
                if(groesse.equals(aktuelleGroesse)) {
                    g.drawImage(feldImage, 0, 0, null);
                }
                else {
                    // Größe des aktuellen Images anpassen.
                    g.drawImage(feldImage, 0, 0, aktuelleGroesse.width,
                                aktuelleGroesse.height, null);
                }
            }
        }
    }
}
