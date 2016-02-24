/**
 * Die Hauptklasse eines simplen Rechners. Erzeugen Sie 
 * ein Exemplar dieser Klasse und der Rechner erscheint
 * auf dem Bildschirm.
 * 
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class Rechner
{
    private Recheneinheit recheneinheit;
    private GrafischeSchnittstelle gui;

    /**
     * Erzeuge einen neuen Rechner und zeige ihn an.
     */
    public Rechner()
    {
        recheneinheit = new Recheneinheit();
        gui = new GrafischeSchnittstelle(recheneinheit);
    }

    /**
     * Erneut anzeigen, falls das Fenster geschlossen wurde.
     */
    public void anzeigen()
    {
        gui.setzeSichtbarkeit(true);
    }
}
