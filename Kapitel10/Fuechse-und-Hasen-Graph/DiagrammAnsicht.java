import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.*;

/**
 * Die Ansicht zeigt die Entwicklung zweier Populationen als Liniengraph über die Zeit.
 * Die aktuelle Version stellt immer genau zwei unterschiedliche Tierklassen dar. Falls weitere
 * Tiere eingeführt werden, werden diese nicht automatisch berücksichtigt.
 * 
 * @author Michael Kölling und David J. Barnes
 * @version 31.07.2011
 */
public class DiagrammAnsicht implements Simulationsansicht
{
    private static final Color HELLGRAU = new Color(0, 0, 0, 40);

    private static JFrame fenster;
    private static GraphPanel graph;
    private static JLabel schrittLabel;
    private static JLabel anzahlLabel;

    // Die Klassen, die von dieser Ansicht beobachtet werden
    private Set<Class> klassen;
    // Eine Map für die Farben der Simulationsteilnehmer
    private Map<Class, Color> farben;
    // Ein Statistik-Objekt zur Berechnung und Speicherung
    // von Simulationsdaten
    private FeldStatistik stats;

    /**
     * Konstruktor.
     * 
     * @param breite die Breite des Plotterfensters(in Pixel).
     * @param hoehe die Höhe des Plotterfensters(in Pixel).
     * @param startMax der anfängliche Maximalwert für die y-Achse.
     */
    public DiagrammAnsicht(int breite, int hoehe, int startMax)
    {
        stats = new FeldStatistik();
        klassen = new HashSet<Class>();
        farben = new HashMap<Class, Color>();

        if (fenster == null) {
            fenster = erzeugeFenster(breite, hoehe, startMax);
        }
        else {
            graph.neuerLauf();
        }

        //zeigeStatus(0, null);
    }

    /**
     * Definiere eine Farbe für die gegebene Tierklasse.
     * @param tierklasse Das Klassenobjekt der Tierklasse.
     * @param farbe Die zu benutzende Farbe für die Tierklasse. 
     */
    public void setzeFarbe(Class tierklasse, Color farbe)
    {
        farben.put(tierklasse, farbe);
        klassen = farben.keySet();
    }

    /**
     * Zeige den aktuellen Zustand des Feldes. Der Status wird durch einen Liniengraphen für 
     * zwei Klassen im Feld dargestellt. Diese Ansicht funktioniert auf dem aktuellen Stand nur für
     * genau zwei Klassen (nicht mehr und nicht weniger). Wenn das Feld mehr als zwei unterschiedliche
     * Tierspezies enthält, werden dennoch nur zwei Klassen dargestellt.
      * 
    * @param schritt welcher Iterationsschritt ist dies.
     * @param feld das Feld, das angezeigt werden soll.
     */
    public void zeigeStatus(int schritt, Feld feld)
    {
        graph.aktualisieren(schritt, feld, stats);
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
        graph.neuerLauf();
    }
    
    /**
     * Bereite das Fenster für die Darstellung des Graphen vor.
     */
    private JFrame erzeugeFenster(int breite, int hoehe, int startMax)
    {
        JFrame fenster = new JFrame("Diagrammansicht");
        fenster.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        Container contentPane = fenster.getContentPane();

        graph = new GraphPanel(breite, hoehe, startMax);
        contentPane.add(graph, BorderLayout.CENTER);

        JPanel unten = new JPanel();
        unten.add(new JLabel("Schritt:"));
        schrittLabel = new JLabel("");
        unten.add(schrittLabel);
        anzahlLabel = new JLabel(" ");
        unten.add(anzahlLabel);
        contentPane.add(unten, BorderLayout.SOUTH);

        fenster.pack();
        fenster.setLocation(20, 600);

        fenster.setVisible(true);

        return fenster;
    }

    // ============================================================================
    /**
     * Eingebettete Klasse: eine Komponente zum Anzeigen des Graphen.
     */
    class GraphPanel extends JComponent
    {
        private static final double SKALIERUNGS_FAKTOR = 0.8;

        // Ein interner Bildpuffer, der zum Zeichnen benutzt wird. Für die 
        // eigentliche Anzeige wird dieser Bildpuffer dann auf den Bildschirm kopiert.
        private BufferedImage graphBild;
        private int letzterWert1, letzterWert2;
        private int yMax;

        /**
         * Erzeuge einen neuen, leeren GraphPanel.
         */
        public GraphPanel(int breite, int hoehe, int startMax)
        {
            graphBild = new BufferedImage(breite, hoehe, BufferedImage.TYPE_INT_RGB);
            bildLoeschen();
            letzterWert1 = hoehe;
            letzterWert2 = hoehe;
            yMax = startMax;
        }

        /**
         * Stelle eine neue Simulation auf dem Panel dar.
         */
        public void neuerLauf()
        {
            int hoehe = graphBild.getHeight();
            int breite = graphBild.getWidth();

            Graphics g = graphBild.getGraphics();
            g.copyArea(4, 0, breite-4, hoehe, -4, 0);            
            g.setColor(Color.BLACK);
            g.drawLine(breite-4, 0, breite-4, hoehe);
            g.drawLine(breite-2, 0, breite-2, hoehe);
            letzterWert1 = hoehe;
            letzterWert2 = hoehe;
            repaint();
        }

        /**
         * Zeige eine neuen Datenpunkt an.
         */
        public void aktualisieren(int schritt, Feld feld, FeldStatistik stats)
        {
            if (klassen.size() >= 2) {
                Iterator<Class> it = klassen.iterator();
                Class klasse1 = it.next();
                Class klasse2 = it.next();

                stats.zuruecksetzen();
                int anzahl1 = stats.gibBewohnerAnzahl(feld, klasse1);
                int anzahl2 = stats.gibBewohnerAnzahl(feld, klasse2);

                Graphics g = graphBild.getGraphics();

                int hoehe = graphBild.getHeight();
                int breite = graphBild.getWidth();

                // verschiebe den Graphen um einen Pixel nach links
                g.copyArea(1, 0, breite-1, hoehe, -1, 0);

                // berechne y, prüfe, ob es außerhalb des Bildschirms liegt. Skaliere notfalls herunter.
                int y = hoehe - ((hoehe * anzahl1) / yMax) - 1;
                while (y<0) {
                    herunterSkalieren();
                    y = hoehe - ((hoehe * anzahl1) / yMax) - 1;
                }
                g.setColor(HELLGRAU);
                g.drawLine(breite-2, y, breite-2, hoehe);
                g.setColor(farben.get(klasse1));
                g.drawLine(breite-3, letzterWert1, breite-2, y);
                letzterWert1 = y;

                y = hoehe - ((hoehe * anzahl2) / yMax) - 1;
                while (y<0) {
                    herunterSkalieren();
                    y = hoehe - ((hoehe * anzahl2) / yMax) - 1;
                }
                g.setColor(HELLGRAU);
                g.drawLine(breite-2, y, breite-2, hoehe);
                g.setColor(farben.get(klasse2));
                g.drawLine(breite-3, letzterWert2, breite-2, y);
                letzterWert2 = y;

                jetztNeuZeichnen();

                schrittLabel.setText("" + schritt);
                anzahlLabel.setText(stats.gibBewohnerInfo(feld));
            }
        }

        /**
         * Skaliere den aktuellen Graphen vertikal etwas herunter, so dass im oberen Bereich mehr Platz bleibt.
         */
        public void herunterSkalieren()
        {
            Graphics g = graphBild.getGraphics();
            int hoehe = graphBild.getHeight();
            int breite = graphBild.getWidth();

            BufferedImage tmpBild = new BufferedImage(breite, (int)(hoehe*SKALIERUNGS_FAKTOR), 
                                                       BufferedImage.TYPE_INT_RGB);
            Graphics2D gtmp = (Graphics2D) tmpBild.getGraphics();

            gtmp.scale(1.0, SKALIERUNGS_FAKTOR);
            gtmp.drawImage(graphBild, 0, 0, null);

            int oben = (int) (hoehe * (1.0-SKALIERUNGS_FAKTOR));

            g.setColor(Color.WHITE);
            g.fillRect(0, 0, breite, oben);
            g.drawImage(tmpBild, 0, oben, null);

            yMax = (int) (yMax / SKALIERUNGS_FAKTOR);
            letzterWert1 = oben + (int) (letzterWert1 * SKALIERUNGS_FAKTOR);
            letzterWert2 = oben + (int) (letzterWert2 * SKALIERUNGS_FAKTOR);

            repaint();
        }

        /**
         * Veranlasst das sofortige Neuzeichnen des Panels.
         */
        public void jetztNeuZeichnen()
        {
            paintImmediately(0, 0, graphBild.getWidth(), graphBild.getHeight());
        }

        /**
         * Lösche das Bild im Panel.
         */
        public void bildLoeschen()
        {
            Graphics g = graphBild.getGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, graphBild.getWidth(), graphBild.getHeight());
            repaint();
        }

        // Die folgenden Methoden sind Redefinitionen von Methoden, die von
        // den Superklassen geerbt wurden.

        /**
         * Teile dem Layout-Manager mit, wie groß wir sein möchten.
         * (Diese Methode wird automatisch vom Layout-Manager beim Platzieren der
         * Kompoennten aufgerufen.)
         * 
         * @return die bevorzugten Abmaße für diese Komponente.
         */
        public Dimension getPreferredSize()
        {
            return new Dimension(graphBild.getWidth(), graphBild.getHeight());
        }

        /**
         * Diese Komponente ist undurchsichtig.
         */
        public boolean isOpaque()
        {
            return true;
        }

        /**
         * Diese Komponente muss neu gezeichnet werden. Kopiere das interne Bild auf den 
         * Bildschirm. (Diese Methode wird automatisch vom Swing-Bildschirmmanager aufgerufen, 
         * wenn er möchte, dass die Komponente neu gezeichnet wird.)
         * 
         * @param g der Grafikkontext, der zum Zeichnen dieser Komponente verwendet werden kann.
         */
        public void paintComponent(Graphics g)
        {
            Dimension groesse = getSize();
            //g.clearRect(0, 0, size.breite, size.hoehe);
            if(graphBild != null) {
                g.drawImage(graphBild, 0, 0, null);
            }
        }
    }
}
