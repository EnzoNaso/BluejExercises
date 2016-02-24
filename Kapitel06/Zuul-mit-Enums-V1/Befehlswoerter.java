import java.util.HashMap;

/**
 * Diese Klasse hält eine Aufzählung aller Befehlswörter, die dem
 * Spiel bekannt sind. Mit ihrer Hilfe werden eingetippte Befehle
 * erkannt.
 *
 * @author  Michael Kölling und David J. Barnes
 * @version 31.07.2011
 */

class Befehlswoerter
{
    // eine Abbildung von Befehlswörtern auf Elemente des 
    // Aufzählungstyps Befehlswort
    private HashMap<String, Befehlswort> gueltigeBefehle;

    /**
     * Konstruktor - initialisiere die Befehlswörter.
     */
    public Befehlswoerter()
    {
        gueltigeBefehle = new HashMap<String, Befehlswort>();
        gueltigeBefehle.put("go", Befehlswort.GO);
        gueltigeBefehle.put("help", Befehlswort.HELP);
        gueltigeBefehle.put("quit", Befehlswort.QUIT);        
    }

    
    /**
     * Finde das Befehlswort, das mit einem Befehls-String verknüpft ist.
     * @param befehlswort das nachzuschlagende Wort (als String)
     * @return Das zugehörige Befehlswort zum dem Wort oder UNKNOWN,
     *         wenn das Wort kein gültiges Befehlswort ist.
     */
    public Befehlswort gibBefehlswort(String wort)
    {
        Befehlswort befehlswort = gueltigeBefehle.get(wort);
        if(befehlswort != null) {
            return befehlswort;
        }
        else {
            return Befehlswort.UNKNOWN;
        }
    }
        
    /**
     * Prüfe, ob eine gegebene Zeichenkette ein gültiger
     * Befehl ist.
     * @return 'true', wenn die gegebene Zeichenkette ein gültiger
     * Befehl ist, 'false' sonst.
     */
    public boolean istBefehl(String eingabe)
    {
        return gueltigeBefehle.containsKey(eingabe);
    }

    /**
     * Gib alle gültigen Befehlswörter auf die Konsole aus.
     */
    public void alleAusgeben() 
    {
        for(String befehl : gueltigeBefehle.keySet()) {
            System.out.print(befehl + "  ");
        }
        System.out.println();
    }
}
