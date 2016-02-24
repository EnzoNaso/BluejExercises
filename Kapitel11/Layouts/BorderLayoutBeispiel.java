import java.awt.*;
import javax.swing.*;

/**
 * Demonstriert das BorderLayout.
 * 
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class BorderLayoutBeispiel
{
    private JFrame fenster;

    /**
     * Konstruktor für Objekte der Klasse BorderLayoutBeispiel
     */
    public BorderLayoutBeispiel()
    {
        fensterErzeugen();
    }

    /**
     * Füge fünf Komponenten in die verfügbaren Positionen ein.
     */
    private void fensterErzeugen()
    {
        fenster = new JFrame("BorderLayout-Beispiel");
        
        Container contentPane = fenster.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(new JButton("Norden"), BorderLayout.NORTH);
        contentPane.add(new JButton("Süden"), BorderLayout.SOUTH);
        contentPane.add(new JButton("Mitte"), BorderLayout.CENTER);
        contentPane.add(new JButton("Westen"), BorderLayout.WEST);
        contentPane.add(new JButton("Osten"), BorderLayout.EAST);
        
        fenster.pack();
        fenster.setVisible(true);
    }
}
