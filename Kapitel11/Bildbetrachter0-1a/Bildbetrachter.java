import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

/**
 * Bildbetrachter ist die Hauptklasse der Bildbetrachter-Anwendung. Sie
 * erstellt die GUI der Anwendung, zeigt sie an und initialisiert alle
 * anderen Komponenten.
 * 
 * Erzeugen Sie ein Exemplar dieser Klasse, um die Anwendung zu starten.
 * 
 * @author Michael Kölling und David J Barnes 
 * @version 0.1a
 */
public class Bildbetrachter extends JFrame
{ 
    /**
     * Erzeuge einen Bildbetrachter und zeige seine GUI auf
     * dem Bildschirm an.
     */
    public Bildbetrachter()
    {
        super("Bildbetrachter");
        fensterErzeugen();
        setVisible(true);
    }
    
    
    // ---- Swing-Anteil zum Erzeugen des Fensters mit allen Komponenten ----
    
    /**
     * Erzeuge das Swing-Fenster samt Inhalt.
     */
    private void fensterErzeugen()
    {
        Container contentPane = getContentPane();
        
        JLabel label = new JLabel("Ich bin ein Label. Ich kann Text anzeigen.");
        contentPane.add(label);

        pack();
    }    
}
