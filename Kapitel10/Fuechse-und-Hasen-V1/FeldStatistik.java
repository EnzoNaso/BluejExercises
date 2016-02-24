import java.awt.Color;
import java.util.HashMap;

/**
 * Diese Klasse sammelt und liefert statistische Daten über den
 * Zustand eines Feldes. Auf sehr flexible Weise: Es wird ein
 * Zähler angelegt und gepflegt für jede Objektklasse, die im
 * Feld gefunden wird.
 * 
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class FeldStatistik
{
    // Die Zähler für die jeweiligen Akteurstypen (Fuchs, Hase, etc.)
    // in der Simulation.
    private HashMap<Class, Zaehler> zaehler;
    // Sind die Zählerstände momentan aktuell?
    private boolean zaehlerAktuell;

    /**
     * Erzeuge ein FeldStatistik-Objekt.
     */
    public FeldStatistik()
    {
        // Wir legen eine Sammlung für die Zähler an, die wir für
        // die gefundenen Tierarten erzeugen.
        zaehler = new HashMap<Class, Zaehler>();
        zaehlerAktuell = true;
    }

    /**
     * Liefere Informationen über die Bewohner im Feld.
     * @return Eine Beschreibung, welche Tiere das
     *          Feld bevölkern.
     */
    public String gibBewohnerInfo(Feld feld)
    {
        StringBuffer buffer = new StringBuffer();
        if(!zaehlerAktuell) {
            ermittleZaehlerstaende(feld);
        }
        for (Class schluessel : zaehler.keySet()) {
            Zaehler info = zaehler.get(schluessel);
            buffer.append(info.gibName());
            buffer.append(": ");
            buffer.append(info.gibStand());
            buffer.append(' ');
        }
        return buffer.toString();
    }
    
    /**
     * Verwerfe alle bisher gesammelten Daten; setze alle Zähler
     * auf null zurück.
     */
    public void zuruecksetzen()
    {
        zaehlerAktuell = false;
        for(Class schluessel : zaehler.keySet()) {
            Zaehler einzelZaehler = zaehler.get(schluessel);
            einzelZaehler.zuruecksetzen();
        }
    }

    /**
     * Erhöhe den Zähler für eine Tierklasse.
     * @param tierklasse Klasse der Tierart, für die erhöht werden soll.
     */
    public void erhoeheZaehler(Class tierklasse)
    {
        Zaehler einzelZaehler = zaehler.get(tierklasse);
        if(einzelZaehler == null) {
            // Wir haben noch keinen Zähler für
            // diese Spezies - also neu anlegen
            einzelZaehler = new Zaehler(tierklasse.getName());
            zaehler.put(tierklasse, einzelZaehler);
        }
        einzelZaehler.erhoehen();
    }

    /**
     * Signalisiere, dass eine Tierzählung beendet ist.
     */
    public void zaehlungBeendet()
    {
        zaehlerAktuell = true;
    }

    /**
     * Stelle fest, ob die Simulation noch aktiv ist, also
     * ob sie weiterhin laufen sollte.
     * @return true wenn noch mehr als eine Spezies lebt.
     */
    public boolean istAktiv(Feld feld)
    {
        // Wieviele Zähler sind nicht null.
        int nichtNull = 0;
        if(!zaehlerAktuell) {
            ermittleZaehlerstaende(feld);
        }
        for(Class schluessel : zaehler.keySet()) {
            Zaehler info = zaehler.get(schluessel);
            if(info.gibStand() > 0) {
                nichtNull++;
            }
        }
        return nichtNull > 1;
    }
    
    /**
     * Erzeuge Zähler für die Anzahl der Füchse und Hasen.
     * Diese werden nicht ständig aktuell gehalten, während
     * Füchse und Hasen in das Feld gesetzt werden, sondern
     * jeweils bei der Abfrage der Zählerstände berechnet.
     * @param feld das Feld, für das die Statistik erstellt
     *             werden soll.
     */
    private void ermittleZaehlerstaende(Feld feld)
    {
        zuruecksetzen();
        for(int zeile = 0; zeile < feld.gibTiefe(); zeile++) {
            for(int spalte = 0; spalte < feld.gibBreite(); spalte++) {
                Object tier = feld.gibObjektAn(zeile, spalte);
                if(tier != null) {
                    erhoeheZaehler(tier.getClass());
                }
            }
        }
        zaehlerAktuell = true;
    }
}
