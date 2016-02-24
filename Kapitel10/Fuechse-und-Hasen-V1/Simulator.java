import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;

/**
 * Ein einfacher Jäger-Beute-Simulator, basierend auf einem
 * Feld mit Füchsen und Hasen.
 * 
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class Simulator
{
    // Konstanten für Konfigurationsinformationen über die Simulation.
    // Die Standardbreite für ein Feld.
    private static final int STANDARD_BREITE = 120;
    // Die Standardtiefe für ein Feld.
    private static final int STANDARD_TIEFE = 80;
    // Die Wahrscheinlichkeit für die Geburt eines Fuchses an
    // einer beliebigen Position im Feld.
    private static final double FUCHSGEBURT_WAHRSCHEINLICH = 0.02;
    // Die Wahrscheinlichkeit für die Geburt eines Hasen an
    // einer beliebigen Position im Feld.
    private static final double HASENGEBURT_WAHRSCHEINLICH = 0.08;    

    // Listen der Tiere im Feld. Getrennte Listen vereinfachen das Iterieren.
    private List<Hase> hasen;
    private List<Fuchs> fuechse;
    // Der aktuelle Zustand des Feldes
    private Feld feld;
    // Der aktuelle Schritt der Simulation
    private int schritt;
    // Eine grafische Ansicht der Simulation
    private Simulationsansicht ansicht;
    
    /**
     * Erzeuge ein Simulationsfeld mit einer Standardgröße.
     */
    public Simulator()
    {
        this(STANDARD_TIEFE, STANDARD_BREITE);
    }
      
    /**
     * Erzeuge ein Simulationsfeld mit der gegebenen Größe.
     * @param tiefe die Tiefe des Feldes (muss größer als null sein).
     * @param breite die Breite des Feldes (muss größer als null sein).
     */
    public Simulator(int tiefe, int breite)
    {
        if(breite <= 0 || tiefe <= 0) {
            System.out.println("Abmessungen müssen größer als null sein.");
            System.out.println("Benutze Standardwerte.");
            tiefe = STANDARD_TIEFE;
            breite = STANDARD_BREITE;
        }
        hasen = new ArrayList<Hase>();
        fuechse = new ArrayList<Fuchs>();
        feld = new Feld(tiefe, breite);

        // Eine Ansicht der Zustände aller Positionen im Feld erzeugen.
        ansicht = new Simulationsansicht(tiefe, breite);
        ansicht.setzeFarbe(Fuchs.class, Color.blue);
        ansicht.setzeFarbe(Hase.class, Color.orange);
        
        // Einen gültigen Startzustand einnehmen.
        zuruecksetzen();
    }
    
    /**
     * Starte die Simulation vom aktuellen Zustand aus für einen längeren
     * Zeitraum (4000 Schritte).
     */
    public void starteLangeSimulation()
    {
        simuliere(4000);
    }
    
    /**
     * Führe vom aktuellen Zustand aus die angegebene Anzahl an
     * Simulationsschritten durch.
     * Brich vorzeitig ab, wenn die Simulation nicht mehr aktiv ist.
     * @param schritte die Anzahl der auszuführenden Schritte.
     */
    public void simuliere(int schritte)
    {
        for(int schritt = 1; schritt <= schritte && ansicht.istAktiv(feld); schritt++) {
            simuliereEinenSchritt();
        }
    }
    
    /**
     * Führe einen einzelnen Simulationsschritt aus:
     * Durchlaufe alle Feldpositionen und aktualisiere den 
     * Zustand jedes Fuchses und Hasen.
     */
    public void simuliereEinenSchritt()
    {
        schritt++;
        
        // Platz für neugeborene Hasen anlegen.
        List<Hase> neueHasen = new ArrayList<Hase>();
        // Alle Hasen agieren lassen.
        for(Iterator<Hase> iter = hasen.iterator(); iter.hasNext(); ) {
            Hase hase = iter.next();
            hase.laufe(neueHasen);
            if(!hase.istLebendig()) {
                iter.remove();
            }
        }
        
        // Platz für neugeborene Füchse anlegen.
        List<Fuchs> neueFuechse = new ArrayList<Fuchs>();
        // Alle Füchse agieren lassen.
        for(Iterator<Fuchs> iter = fuechse.iterator(); iter.hasNext(); ) {
            Fuchs fuchs = iter.next();
            fuchs.jage(neueFuechse);
            if(!fuchs.istLebendig()) {
                iter.remove();
            }
        }

        // Neugeborene Füchse und Hasen in die Hauptlisten einfügen.
        hasen.addAll(neueHasen);
        fuechse.addAll(neueFuechse);

        ansicht.zeigeStatus(schritt, feld);
    }
        
    /**
     * Setze die Simulation an den Anfang zurück.
     */
    public void zuruecksetzen()
    {
        schritt = 0;
        hasen.clear();
        fuechse.clear();
        bevoelkere();
        
        // Zeige den Startzustand in der Ansicht.
        ansicht.zeigeStatus(schritt, feld);
    }
    
    /**
     * Bevölkere das Feld mit Füchsen und Hasen.
     */
    private void bevoelkere()
    {
        Random rand = Zufallssteuerung.gibZufallsgenerator();
        feld.raeumen();
        for(int zeile = 0; zeile < feld.gibTiefe(); zeile++) {
            for(int spalte = 0; spalte < feld.gibBreite(); spalte++) {
                if(rand.nextDouble() <= FUCHSGEBURT_WAHRSCHEINLICH) {
                    Position position = new Position(zeile, spalte); 
                    Fuchs fuchs = new Fuchs(true, feld, position);
                    fuechse.add(fuchs);
                }
                else if(rand.nextDouble() <= HASENGEBURT_WAHRSCHEINLICH) {
                    Position position = new Position(zeile, spalte); 
                    Hase hase = new Hase(true, feld, position);
                    hasen.add(hase);
                }
                // ansonsten die Position leer lassen
            }
        }
    }
}