import java.util.Iterator;
import java.awt.*;
import javax.swing.*;
    
/**
 * Eine Ansicht für die Fahrzeuge und Fahrgäste
 * in einer Stadt.
 * 
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class StadtGUI extends JFrame implements Akteur
{
    private static final long serialVersionUID = 20060330L;

    private Stadt stadt;
    private Stadtansicht stadtansicht;
    
    /**
     * Konstruktor für Objekte der Klasse StadtGUI
     * @param stadt die Stadt, deren Zustand angezeigt werden soll.
     */
    public StadtGUI(Stadt stadt)
    {
        this.stadt = stadt;
        stadtansicht = new Stadtansicht(stadt.gibBreite(), stadt.gibHoehe());
        getContentPane().add(stadtansicht);
        setTitle("Taxiville");
        setSize(600, 600);
        setVisible(true);
    }
    
    /**
     * Die GUI für eine Stadt agiert, indem sie den aktuellen
     * Zustand der Stadt anzeigt.
     */
    public void agiere()
    {
        stadtansicht.zeichnenVorbereiten();
        Iterator gegenstaende = stadt.gibGegenstaende();
        while(gegenstaende.hasNext()) {
            Object obj = gegenstaende.next();
            if(obj instanceof GrafischerGegenstand){
                GrafischerGegenstand item = (GrafischerGegenstand) obj;
                Position location = item.gibPosition();
                stadtansicht.zeichneImage(location.gibX(), location.gibY(), item.gibGrafik());
            }
        }
        stadtansicht.repaint();
    }
    
    /**
     * Eine grafische Ansicht einer rechteckigen Stadt. Dies
     * ist eine geschachtelte Klasse (eine Klasse, die
     * innerhalb einer anderen Klasse definiert ist), die eine
     * eigene grafische Komponente für die Benutzungsschnittstelle
     * definiert. Diese Komponente zeigt die Stadt an.
     * Dies ist fortgeschrittene GUI-Technik - Sie können sie
     * für Ihr Projekt ignorieren, wenn Sie wollen.
     */
    private class Stadtansicht extends JPanel
    {
        private static final long serialVersionUID = 20060330L;
        private final int DEHN_FACTOR = 6;

        private int stadtBreite, stadtHoehe;
        private int xFaktor, yFaktor;
        private Dimension groesse;
        private Graphics g;
        private Image stadtImage;

        /**
         * Erzeuge eine neue GUI-Komponente Stadtansicht.
         */
        public Stadtansicht(int breite, int hoehe)
        {
            stadtBreite = breite;
            stadtHoehe = hoehe;
            setBackground(Color.white);
            groesse = new Dimension(0, 0);
        }

        /**
         * Der GUI-Verwaltung mitteilen, wie groß wir sein wollen.
         * Der Name der Methode ist durch die GUI-Verwaltung festgelegt.
         */
        public Dimension getPreferredSize()
        {
            return new Dimension(stadtBreite * DEHN_FACTOR,
                                 stadtHoehe * DEHN_FACTOR);
        }
        
        /**
         * Bereite eine neue Zeichenrunde vor. Da die Komponente
         * in der Größe geändert werden kann, muss der Maßstab neu
         * berechnet werden.
         */
        public void zeichnenVorbereiten()
        {
            if(!groesse.equals(getSize())) {  // Größe wurde geändert...
                groesse = getSize();
                stadtImage = stadtansicht.createImage(groesse.width, groesse.height);
                g = stadtImage.getGraphics();

                xFaktor = groesse.width / stadtBreite;
                if(xFaktor < 1) {
                    xFaktor = DEHN_FACTOR;
                }
                yFaktor = groesse.height / stadtHoehe;
                if(yFaktor < 1) {
                    yFaktor = DEHN_FACTOR;
                }
            }
            g.setColor(Color.white);
            g.fillRect(0, 0, groesse.width, groesse.height);
            g.setColor(Color.gray);
            for(int i = 0, x = 0; x < groesse.width; i++, x = i * xFaktor) {
                g.drawLine(x, 0, x, groesse.height - 1);
            }
            for(int i = 0, y = 0; y < groesse.height; i++, y = i * yFaktor) {
                g.drawLine(0, y, groesse.width - 1, y);
            }
        }
        
        /**
         * Zeichne das Image eines Gegenstandes.
         */
        public void zeichneImage(int x, int y, Image image)
        {
            g.drawImage(image, x * xFaktor + 1, y * yFaktor + 1,
                        xFaktor - 1, yFaktor - 1, this);
        }

        /**
         * Die Komponente für die Stadtansicht muss erneut angezeigt
         * werden. Kopiere das interne Image in die Anzeige.
         * Der Name der Methode ist durch die GUI-Verwaltung festgelegt.
         */
        public void paintComponent(Graphics g)
        {
            if(stadtImage != null) {
                g.drawImage(stadtImage, 0, 0, null);
            }
        }
    }
}
