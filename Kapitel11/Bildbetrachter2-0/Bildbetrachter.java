import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

import java.io.File;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Bildbetrachter ist die Hauptklasse der Bildbetrachter-Anwendung. Sie
 * erstellt die GUI der Anwendung, zeigt sie an und initialisiert alle
 * anderen Komponenten.
 * 
 * Erzeugen Sie ein Exemplar dieser Klasse, um die Anwendung zu starten.
 * 
 * @author Michael Kölling und David J Barnes 
 * @version 2.0
 */
public class Bildbetrachter
{
    // statische Datenfelder (Klassenkonstanten und -variablen)
    private static final String VERSION = "Version 2.0";
    private static JFileChooser dateiauswahldialog = new JFileChooser(System.getProperty("user.dir"));

    // Datenfelder
    private JFrame fenster;
    private Bildflaeche bildflaeche;
    private JLabel dateinameLabel;
    private JLabel statusLabel;
    private Farbbild aktuellesBild;
    
    private List<Filter> filterliste;
    
    /**
     * Erzeuge einen Bildbetrachter und zeige seine GUI auf
     * dem Bildschirm an.
     */
    public Bildbetrachter()
    {
        aktuellesBild = null;
        filterliste = filterlisteErzeugen();
        fensterErzeugen();
    }


    // ---- Implementierung der Menü-Funktionen ----
    
    /**
     * 'Datei oeffnen'-Funktion: Öffnet einen Dateiauswahldialog zur 
     * Auswahl einer Bilddatei und zeigt das selektierte Bild an.
     */
    private void dateiOeffnen()
    {
        int ergebnis = dateiauswahldialog.showOpenDialog(fenster);

        if(ergebnis != JFileChooser.APPROVE_OPTION) { // abgebrochen
            return;  
        }
        File selektierteDatei = dateiauswahldialog.getSelectedFile();
        aktuellesBild = BilddateiManager.ladeBild(selektierteDatei);
        
        if(aktuellesBild == null) {   // Bilddatei nicht im gültigen Format
            JOptionPane.showMessageDialog(fenster,
                    "Die Datei hat keines der unterstützten Formate.",
                    "Fehler beim Bildladen",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        bildflaeche.setzeBild(aktuellesBild);
        dateinameAnzeigen(selektierteDatei.getPath());
        statusAnzeigen("Datei geladen.");
        fenster.pack();
    }
    
    
    /**
     * 'Schliessen'-Funktion: Schließt das aktuelle Bild.
     */
    private void schliessen()
    {
        aktuellesBild = null;
        bildflaeche.loeschen();
        dateinameAnzeigen(null);
    }
    
    
    /**
     * 'Beenden'-Funktion: Beendet die Anwendung.
     */
    private void beenden()
    {
        System.exit(0);
    }
    
    
    /**
     * Wende den gegebenen Filter auf das aktuelle Bild an.
     * 
     * @param filter   Der anzuwendende Filter.
     */
    private void filterAnwenden(Filter filter)
    {
        if(aktuellesBild != null) {
            filter.anwenden(aktuellesBild);
            fenster.repaint();
            statusAnzeigen("Angewendet: " + filter.gibName());
        }
        else {
            statusAnzeigen("Kein Bild geladen.");
        }
    }
    

    /**
     * 'Info'-Funktion: Zeige Informationen zur Anwendung.
     */
    private void zeigeInfo()
    {
        JOptionPane.showMessageDialog(fenster, 
                    "Bildbetrachter\n" + VERSION,
                    "Info zu Bildbetrachter", 
                    JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    // ---- Hilfsmethoden ----

    /**
     * Zeigt den Dateinamen des aktuellen Bildes auf dem Label für den
     * Dateinamen.
     * Der Parameter sollte 'null' sein, wenn kein Bild geladen ist. 
     * 
     * @param dateiname  Der anzuzeigende Dateiname, oder null für 'keine Datei'.
     */
    private void dateinameAnzeigen(String dateiname)
    {
        if(dateiname == null) {
            dateinameLabel.setText("Keine Datei angezeigt.");
        }
        else {
            dateinameLabel.setText("Datei: " + dateiname);
        }
    }
    
    
    /**
     * Zeige den gegebenen Text in der Statuszeile am unteren
     * Rand des Fensters.
     * @param text der anzuzeigende Statustext.
     */
    private void statusAnzeigen(String text)
    {
        statusLabel.setText(text);
    }
    
    
    /**
     * Erzeuge und liefere eine Liste mit allen bekannten Filtern.
     * @return die Liste der Filter.
     */
    private List<Filter> filterlisteErzeugen()
    {
        List<Filter> filterliste = new ArrayList<Filter>();
        filterliste.add(new Abdunkelfilter("Dunkler"));
        filterliste.add(new Aufhellfilter("Heller"));
        filterliste.add(new Schwellwertfilter("Schwellwert"));
       
        return filterliste;
    }
    
    // ---- Swing-Anteil zum Erzeugen des Fensters mit allen Komponenten ----
    
    /**
     * Erzeuge das Swing-Fenster samt Inhalt.
     */
    private void fensterErzeugen()
    {
        fenster = new JFrame("Bildbetrachter");
        Container contentPane = fenster.getContentPane();

        menuezeileErzeugen(fenster);
        
        // Ein Layout mit hübschen Abständen definieren
        contentPane.setLayout(new BorderLayout(6, 6));
        
        // Die Bildfläche in der Mitte erzeugen
        bildflaeche = new Bildflaeche();
        contentPane.add(bildflaeche, BorderLayout.CENTER);

        // Zwei Labels oben und unten für den Dateinamen und Statusmeldungen
        dateinameLabel = new JLabel();
        contentPane.add(dateinameLabel, BorderLayout.NORTH);

        statusLabel = new JLabel(VERSION);
        contentPane.add(statusLabel, BorderLayout.SOUTH);
        
        // Aufbau abgeschlossen - Komponenten arrangieren lassen
        dateinameAnzeigen(null);
        fenster.pack();
        
        // Das Fenster in der Mitte des Bildschirms platzieren und anzeigen
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        fenster.setLocation(d.width/2 - fenster.getWidth()/2, d.height/2 - fenster.getHeight()/2);
        fenster.setVisible(true);
    }
    
    /**
     * Die Menüzeile des Hauptfensters erzeugen.
     * 
     * @param fenster  Das Fenster, in das die Menüzeile eingefügt werden soll.
     */
    private void menuezeileErzeugen(JFrame fenster)
    {
        final int SHORTCUT_MASK =
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenuBar menuezeile = new JMenuBar();
        fenster.setJMenuBar(menuezeile);
        
        JMenu menue;
        JMenuItem eintrag;
        
        // Das Datei-Menü erzeugen
        menue = new JMenu("Datei");
        menuezeile.add(menue);
        
        eintrag = new JMenuItem("Öffnen...");
            eintrag.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, SHORTCUT_MASK));
            eintrag.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { dateiOeffnen(); }
                           });
        menue.add(eintrag);

        eintrag = new JMenuItem("Schließen");
            eintrag.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, SHORTCUT_MASK));
            eintrag.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { schliessen(); }
                           });
        menue.add(eintrag);
        menue.addSeparator();

        eintrag = new JMenuItem("Beenden");
            eintrag.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
            eintrag.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { beenden(); }
                           });
        menue.add(eintrag);


        // Das Filter-Menü erzeugen
        menue = new JMenu("Filter");
        menuezeile.add(menue);
        
        for(final Filter filter : filterliste) {
            eintrag = new JMenuItem(filter.gibName());
            eintrag.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) { 
                                    filterAnwenden(filter);
                                }
                           });
             menue.add(eintrag);
         }

        // Das Hilfe-Menü erzeugen
        menue = new JMenu("Hilfe");
        menuezeile.add(menue);
        
        eintrag = new JMenuItem("Info...");
            eintrag.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { zeigeInfo(); }
                           });
        menue.add(eintrag);

    }
}
