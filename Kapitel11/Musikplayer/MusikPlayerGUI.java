import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import java.io.*;

/**
 * Ein einfacher Sound-Player. Erzeugen Sie eine Instanz dieser
 * Klasse zum Starten.
 * 
 * Der Sound-Player fungiert als Schnittstelle zu der Musiksammlung-Klasse
 * aus Kapitel 4.
 * 
 * @author Michael Kölling und David J Barnes 
 * @version 1.0
 */
public class MusikPlayerGUI extends JFrame
    implements ChangeListener, ActionListener
{
    private static final String VERSION = "Version 1.0";
    private static final String AUDIO_VERZEICHNIS = "audio";
    
    private JList dateiliste;
    private JSlider schieberegler;
    private JLabel infoLabel;
    private Musiksammlung sammlung;
    // Ein Player f�r die Musik-Tracks
    private MusikPlayer player;
    // Die aktuelle Track-Liste
    private List<Track> trackListe;

    /**
     * Main-Methode zum Starten von der Kommandozeile.
     */
    public static void main(String[] args)
    {
        MusikPlayerGUI gui = new MusikPlayerGUI();
    }
    
    /**
     * Erzeuge einen Sound-Player und zeige seine GUI
     * auf dem Bildschirm an.
     */
    public MusikPlayerGUI()
    {
        super("Musik-Player");
        sammlung = new Musiksammlung(AUDIO_VERZEICHNIS);
        player = new MusikPlayer();
        fensterErzeugen();
    }

    /**
     * Spiele die Sound-Datei, die aktuell in der Dateiliste selektiert ist.
     * Wenn keine Datei selektiert ist oder die selektierte Datei keine
     * Sound-Datei ist, tue nichts.
     */
    private void start()
    {
        int index = dateiliste.getSelectedIndex();
        if(index >= 0 && index < trackListe.size()) {
            schieberegler.setValue(0);
            player.starteAbspielen(trackListe.get(index).gibDateiname());
        }
    }
    
    /**
     * Stoppe die aktuell gespielten Sound-Datei (falls eine gespielt wird).
     */
    private void stop()
    {
        player.stop();
    }

    /**
     * Halte die aktuell gespielte Sound-Datei an(falls eine gespielt wird).
     */
    private void pause()
    {
        player.pause();
    }

    /**
     * Spiele einen zuvor angehaltene Sound-Datei weiter ab.
     */
    private void weiter()
    {
        player.weiter();
    }

    /**
     * Zeige Informationen �ber eine selektierte Sound-Datei (Name und L�nge).
     * @param text der anzuzeigende Text.
     */
    private void zeigeDateiInfo(String text)
    {
        infoLabel.setText(text);
    }
    
    /**
     * 'Beenden'-Funktion: Beenden der Anwendung.
     */
    private void beenden()
    {
        System.exit(0);
    }
    
    
    /**
     * 'Info'-Funktion: Zeige Informationen zur Anwendung.
     */
    private void zeigeInfo()
    {
        JOptionPane.showMessageDialog(this, 
                    "Musik-Player\n" + VERSION,
                    "Info zum Musik-Player", 
                    JOptionPane.INFORMATION_MESSAGE);
    }
    

    // ------- ChangeListener Interface (f�r den JSlider) -------

    /**
     * Methode aus dem Interface ChangeListener f�r �nderungen am JSlider.
     * Diese Methode wird aufgerufen, wenn der Benutzer den Schieberegler
     * ver�ndert.
     * @param evt Details �ber das Event.
     */
    public void stateChanged(ChangeEvent evt)
    {
        // Gehe zu einer neuen Position in der aktuellen Datei.
        // Der Wertebereich des Schiebereglers wird in Prozent angegeben.
    }
    
    // ------- ActionListener Interface (f�r die JComboBox) -------

    /**
     * Methode aus dem Interface ActionListener f�r �nderungen an der
     * JComboBox. Diese Methode wird aufgerufen, wenn der Benutzer
     * einen anderen Eintrag in der Combo-Box ausw�hlt.
     * @param evt Details �ber das Event.
     */
    public void actionPerformed(ActionEvent evt) 
    {
        JComboBox cb = (JComboBox)evt.getSource();
        String reihenfolge = (String) cb.getSelectedItem();
        if(reihenfolge != null) {
            setzeListenReihenfolge(reihenfolge);
        }
    }
    
    /**
     * Setze die Reihenfolge für die Track-Liste.
     * @param reihenfolge die zu verwendende Reihenfolge.
     */
    private void setzeListenReihenfolge(String reihenfolge)
    {
        trackListe = sammlung.sortiereNachFeld(reihenfolge);
        String[] tracks = gibTrackAnzeigeliste(trackListe);
        dateiliste.setListData(tracks);
    }

    /**
     * Liefere eine für die Anzeige aufbereitete Version der Track-Liste .
     * @param trackListe die Liste der anzuzeigenden Tracks.
     * @return die Tracks im Anzeigeformat.
     */
    private String[] gibTrackAnzeigeliste(List<Track> trackListe)
    {
        int anzahlTracks = trackListe.size();
        String[] tracks = new String[anzahlTracks];
        for(int i = 0; i < anzahlTracks; i++) {
            String[] felder = trackListe.get(i).gibFelder();
            StringBuilder auflistung = new StringBuilder();
            for(String feld : felder) {
                auflistung.append(feld);
                auflistung.append(" ");
            }
            tracks[i] = auflistung.toString().trim();
        }
        return tracks;
    }
    
    // ---- Swing-Anteil zum Erzeugen des Fensters mit allen Komponenten ----
    
    /**
     * Konstruktion der kompletten grafischen Benutzungsoberfl�che.
     * @param audioDateien die Dateinamen, die angezeigt werden sollen.
     */
    private void fensterErzeugen()
    {
        // Sicherstellen, dass die Anwendung beendet wird, wenn der
        // Benutzer das Fenster schließt
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JPanel contentPane = (JPanel)getContentPane();
        contentPane.setBorder(new EmptyBorder(6, 10, 10, 10));

        menuezeileErzeugen();
        
        // Ein Layout mit hübschen Abständen definieren
        contentPane.setLayout(new BorderLayout(8, 8));

        // Die Combo-Box links mit Scrollbalken erzeugen
        JPanel linkerBereich = new JPanel();
        {
            linkerBereich.setLayout(new BorderLayout(8, 8));

            // Komponenten für die Sortierung der Track-Liste einrichten.
            JPanel sortierPanel = new JPanel();
            sortierPanel.setLayout(new BorderLayout());
            sortierPanel.add(new JLabel("Sortiere nach:"), BorderLayout.NORTH);
            
            // Hole die Liste der Feldnamen für die Sortierung.
            String[] reihenfolge = Track.FELDER;
            
            // Die Combo-Box erzeugen
            JComboBox formatListe = new JComboBox(reihenfolge);
            formatListe.addActionListener(this);
            sortierPanel.add(formatListe, BorderLayout.NORTH);
    
            linkerBereich.add(sortierPanel, BorderLayout.NORTH);
    
            // Eine scrollbare Liste der Dateinamen erzeugen
            dateiliste = new JList();
            dateiliste.setForeground(new Color(140,171,226));
            dateiliste.setBackground(new Color(0,0,0));
            dateiliste.setSelectionBackground(new Color(87,49,134));
            dateiliste.setSelectionForeground(new Color(140,171,226));
            JScrollPane scrollPane = new JScrollPane(dateiliste);
            scrollPane.setColumnHeaderView(new JLabel("Audio-Dateien"));
            linkerBereich.add(scrollPane, BorderLayout.CENTER);
            
            // die anfängliche Auflistung erstellen.
            setzeListenReihenfolge(reihenfolge[0]);
        }
        contentPane.add(linkerBereich, BorderLayout.CENTER);

        // Mittelbereich mit Bild-Label, Text-Label und Schieberegler erzeugen
        JPanel mittelbereich = new JPanel();
        {
            mittelbereich.setLayout(new BorderLayout(8, 8));
    
            JLabel bildLabel = new JLabel(new ImageIcon("title.jpg"));
            mittelbereich.add(bildLabel, BorderLayout.NORTH);
            mittelbereich.setBackground(Color.BLACK);

            infoLabel = new JLabel("  ");
            infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            infoLabel.setForeground(new Color(140,171,226));
            mittelbereich.add(infoLabel, BorderLayout.CENTER);

            schieberegler = new JSlider(0, 100, 0);
            TitledBorder border = new TitledBorder("Positionieren");
            border.setTitleColor(Color.white);
            schieberegler.setBorder(new CompoundBorder(new EmptyBorder(6, 10, 10, 10), border));
            schieberegler.addChangeListener(this);
            schieberegler.setBackground(Color.BLACK);
            schieberegler.setMajorTickSpacing(25);
            schieberegler.setPaintTicks(true);
            mittelbereich.add(schieberegler, BorderLayout.SOUTH);
        }
        contentPane.add(mittelbereich, BorderLayout.EAST);

        // Die Tastenreihe mit den Tasten erzeugen
        JPanel tastenreihe = new JPanel();
        {
            tastenreihe.setLayout(new GridLayout(1, 0));
  
            JButton taste = new JButton("Start");
            taste.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) { 
                                    start(); 
                                }
                               });
            tastenreihe.add(taste);
            
            taste = new JButton("Stop");
            taste.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) { 
                                    stop(); 
                                }
                               });
            tastenreihe.add(taste);
    
            taste = new JButton("Pause");
            taste.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) { 
                                    pause(); 
                                }
                               });
            tastenreihe.add(taste);
            
            taste = new JButton("Weiter");
            taste.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) { 
                                    weiter(); 
                                    }
                               });
            tastenreihe.add(taste);
        }
        
        contentPane.add(tastenreihe, BorderLayout.NORTH);

        // Aufbau abgeschlossen - Komponenten arrangieren lassen
        pack();
        
        // Das Fenster in der Mitte des Bildschirms platzieren und anzeigen lassen
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(d.width/2 - getWidth()/2, d.height/2 - getHeight()/2);
        setVisible(true);
    }
    
    /**
     * Erzeugen der Menüzeile.
     */
    private void menuezeileErzeugen()
    {
        final int SHORTCUT_MASK =
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenuBar menuezeile = new JMenuBar();
        setJMenuBar(menuezeile);
        
        JMenu menue;
        JMenuItem eintrag;
        
        // Das Datei-Menü erzeugen
        menue = new JMenu("Datei");
        menuezeile.add(menue);
        
        eintrag = new JMenuItem("Beenden");
            eintrag.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
            eintrag.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) { 
                                beenden(); 
                            }
                           });
        menue.add(eintrag);

        // Das Hilfe-Men� erzeugen
        menue = new JMenu("Hilfe");
        menuezeile.add(menue);
        
        eintrag = new JMenuItem("Info...");
            eintrag.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) { 
                                zeigeInfo(); 
                            }
                           });
        menue.add(eintrag);
    }
}
