import java.awt.Color;

/**
 * Eine grafische Ansicht des Simulationsfeldes. Diese Schnittstelle definiert alle
 * möglichen unterschiedlichen Ansichten.
 *
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public interface Simulationsansicht 
{    
    /**
     * Definiere eine Farbe f�r die gegebene Tierklasse.
     * @param tierklasse Das Klassenobjekt der Tierklasse.
     * @param farbe Die zu benutzende Farbe f�r die Tierklasse. 
     */
    void setzeFarbe(Class tierklasse, Color farbe);

    /**
     * Entscheide, ob die Simulation weiterlaufen soll.
     * @param true wenn mehr als eine Spezies existiert. 
     */
    boolean istAktiv(Feld feld);

    /**
     * Zeige den aktuellen Zustand des Feldes.
     * @param schritt welcher Iterationsschritt ist dies.
     * @param feld das Feld, das angezeigt werden soll.
     */
    void zeigeStatus(int schritt, Feld feld);

    /**
     * Bereite einen neuen Lauf vor.
     */
    void zuruecksetzen();

}
