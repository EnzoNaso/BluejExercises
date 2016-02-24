import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Eine grafische Schnittstelle für den Rechner. Hier wird keine
 * Berechnung durchgeführt. Diese Klasse ist ausschließlich für
 * die Anzeige auf dem Bildschirm zuständig. Sie lässt die
 * Recheneinheit alle Berechnungen ausführen.
 * 
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class GrafischeSchnittstelle
    implements ActionListener
{
    private Recheneinheit rechner;
    private boolean autorAnzeigen;

    private JFrame fenster;
    private JTextField anzeige;
    private JLabel status;

    /**
     * Erzeuge eine grafische Benutzungsschnittstelle für 
     * eine Recheneinheit.
     * @param recheneinheit die Recheneinheit
     */
    public GrafischeSchnittstelle(Recheneinheit recheneinheit)
    {
        rechner = recheneinheit;
        autorAnzeigen = true;
        fensterAufbauen();
        fenster.setVisible(true);
    }

    /**
     * Setze die Sichtbarkeit dieser grafischen Schnittstelle.
     * @param sichtbar
     *        true, wenn die Schnittstelle sichtbar sein soll, false sonst.
     * 
     */
    public void setzeSichtbarkeit(boolean sichtbar)
    {
        fenster.setVisible(sichtbar);
    }

    /**
     * Baue das Fenster für diese grafische Oberfläche auf.
     */
    private void fensterAufbauen()
    {
        fenster = new JFrame(rechner.gibTitel());
        
        JPanel fensterflaeche = (JPanel)fenster.getContentPane();
        fensterflaeche.setLayout(new BorderLayout(8, 8));
        fensterflaeche.setBorder(new EmptyBorder( 10, 10, 10, 10));

        anzeige = new JTextField();
        fensterflaeche.add(anzeige, BorderLayout.NORTH);

        JPanel tastenfeld = new JPanel(new GridLayout(4, 4));
            tasteHinzufuegen(tastenfeld, "7");
            tasteHinzufuegen(tastenfeld, "8");
            tasteHinzufuegen(tastenfeld, "9");
            tasteHinzufuegen(tastenfeld, "C");
            
            tasteHinzufuegen(tastenfeld, "4");
            tasteHinzufuegen(tastenfeld, "5");
            tasteHinzufuegen(tastenfeld, "6");
            tasteHinzufuegen(tastenfeld, "?");
            
            tasteHinzufuegen(tastenfeld, "1");
            tasteHinzufuegen(tastenfeld, "2");
            tasteHinzufuegen(tastenfeld, "3");
            tastenfeld.add(new JLabel(" "));
            
            tasteHinzufuegen(tastenfeld, "0");
            tasteHinzufuegen(tastenfeld, "+");
            tasteHinzufuegen(tastenfeld, "-");
            tasteHinzufuegen(tastenfeld, "=");
            
        fensterflaeche.add(tastenfeld, BorderLayout.CENTER);

        status = new JLabel(rechner.gibAutor());
        fensterflaeche.add(status, BorderLayout.SOUTH);

        fenster.pack();
    }

    /**
     * Füge dem Tastenfeld eine Taste hinzu.
     * @param panel der Panel, der die Taste aufnehmen soll
     * @param tastentext der Text für die Taste
     */
    private void tasteHinzufuegen(Container panel, String tastentext)
    {
        JButton taste = new JButton(tastentext);
        taste.addActionListener(this);
        panel.add(taste);
    }

    /**
     * Eine Aktion an der Schnittstelle wurde ausgeführt.
     * Finde heraus, welche es war und behandle sie.
     * @param event die Beschreibung der Aktion
     */
    public void actionPerformed(ActionEvent event)
    {
        String taste = event.getActionCommand();

        if(taste.equals("0") ||
           taste.equals("1") ||
           taste.equals("2") ||
           taste.equals("3") ||
           taste.equals("4") ||
           taste.equals("5") ||
           taste.equals("6") ||
           taste.equals("7") ||
           taste.equals("8") ||
           taste.equals("9")) {
            int number = Integer.parseInt(taste);
            rechner.zifferGetippt(number);
        }
        else if(taste.equals("+")) {
            rechner.plus();
        }
        else if(taste.equals("-")) {
            rechner.minus();
        }
        else if(taste.equals("=")) {
            rechner.gleich();
        }
        else if(taste.equals("C")) {
            rechner.clear();
        }
        else if(taste.equals("?")) {
            infoZeigen();
        }
        // ansonsten: Unbekannter Befehl

        auffrischen();
    }

    /**
     * Frische die grafische Anzeige mit dem aktuellen Wert der
     * Recheneinheit auf.
     */
    private void auffrischen()
    {
        anzeige.setText("" + rechner.gibAnzeigewert());
    }

    /**
     * Wechsle die Informationsausgabe in der Statuszeile zwischen
     * der Autoren- und der Versionsinformation.
     */
    private void infoZeigen()
    {
        if(autorAnzeigen)
            status.setText(rechner.gibVersion());
        else
            status.setText(rechner.gibAutor());

        autorAnzeigen = !autorAnzeigen;
    }
}
