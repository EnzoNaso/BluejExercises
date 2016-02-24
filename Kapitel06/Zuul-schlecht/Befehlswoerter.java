/*
 * Diese Klasse hält eine Aufzählung aller Befehlswörter, die dem
 * Spiel bekannt sind. Mit ihrer Hilfe werden eingetippte Befehle
 * erkannt.
 *
 * @author  Michael Kölling und David J. Barnes
 * @version 31.07.2011
 */

class Befehlswoerter
{
    // ein konstantes Array mit den gültigen Befehlswörtern
    private static final String gueltigeBefehle[] = {
        "go", "quit", "help", "look"
    };

    /**
     * Konstruktor - initialisiere die Befehlswörter.
     */
    public Befehlswoerter()
    {
        // nichts zu tun momentan...
    }

    /**
     * Prüfe, ob eine gegebene Zeichenkette ein gültiger
     * Befehl ist.
     * @return 'true', wenn die gegebene Zeichenkette ein gültiger
     * Befehl ist, 'false' sonst.
     */
    public boolean istBefehl(String eingabe)
    {
        for(int i = 0; i < gueltigeBefehle.length; i++) {
            if(gueltigeBefehle[i].equals(eingabe))
                return true;
        }
        // Wenn wir hierher gelangen, wurde die Eingabe nicht
        // in den Befehlswörter gefunden.
        return false;
    }
}
