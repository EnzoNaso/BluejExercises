import java.awt.*;
import javax.swing.*;

/**
 * Demonstriert das GridLayout.
 * 
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class GridLayoutBeispiel
{
    private JFrame fenster;

    /**
     * Konstruktor für Objekte der Klasse GridLayoutBeispiel
     */
    public GridLayoutBeispiel()
    {
        fensterErzeugen();
    }

    /**
     * Erzeuge ein 3x2-Gitter und füge fünf Komponenten ein.
     */
    private void fensterErzeugen()
    {
        fenster = new JFrame("GridLayout-Beispiel");
        
        Container contentPane = fenster.getContentPane();
        contentPane.setLayout(new GridLayout(3, 2));
        contentPane.add(new JButton("Erster"));
        contentPane.add(new JButton("Zweiter"));
        contentPane.add(new JButton("Der dritte String ist lang"));
        contentPane.add(new JButton("Vierter"));
        contentPane.add(new JButton("Fünfter"));
        
        fenster.pack();
        fenster.setVisible(true);
    }
}
