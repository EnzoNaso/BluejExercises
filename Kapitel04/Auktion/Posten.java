/**
 * Eine Klasse, die die Posten in einer Auktion modelliert.
 * Ein Posten kann ein einzelner Gegenstand oder eine 
 * Zusammenstellung von Gegenständen sein.
 *
 * @author David J. Barnes und Michael Kölling.
 * @version 31.07.2011
 */
public class Posten
{
    // eine eindeutige Nummer zur Identifikation dieses Postens
    private final int nummer;
    // eine Beschreibung dieses Postens
    private String beschreibung;
    // das aktuell höchste Gebot für diesen Posten
    private Gebot hoechstesGebot;

    /**
     * Erzeuge einen Posten, mit Nummer und Beschreibung.
     * @param nummer die Postennummer.
     * @param beschreibung eine Beschreibung dieses Postens.
     */
    public Posten(int nummer, String beschreibung)
    {
        this.nummer = nummer;
        this.beschreibung = beschreibung;
        this.hoechstesGebot = null;
    }

    /**
     * Ein Versuch, für diesen Posten zu bieten. Ein erfolgreiches
     * Gebot muss höher sein als alle bisherigen Gebote.
     * @param gebot ein neues Gebot.
     * @return true falls erfolgreich, false sonst.
     */
    public boolean hoeheresGebot(Gebot gebot)
    {
        if(hoechstesGebot == null) {
            // es gibt kein älteres Gebot
            hoechstesGebot = gebot;
            return true;
        }
        else if (gebot.gibHoehe() > hoechstesGebot.gibHoehe()){
            // das Gebot ist besser als das letzte
            hoechstesGebot = gebot;
            return true;
        }
        else {
            // das Gebot ist nicht besser
            return false;
        }
    }

    /**
     * @return Eine String-Darstellung der Details dieses Postens.
     */
    public String toString()
    {
        String details = nummer + ": " + beschreibung;
        if(hoechstesGebot != null) {
            details += "    Gebot: " + 
                       hoechstesGebot.gibHoehe();
        }
        else {
            details += "    (Kein Gebot)";
        }
        return details;
    }

    /**
     * @return die Nummer dieses Postens.
     */
    public int gibNummer()
    {
        return nummer;
    }

    /**
     * @return die Beschreibung dieses Postens.
     */
    public String gibBeschreibung()
    {
        return beschreibung;
    }

    /**
     * Liefere das höchste Gebot für diesen Posten.
     * Das Ergebnis kann 'null' sein, wenn noch kein
     * Gebot abgegeben wurde.
     * @return das höchste Gebot
     */
    public Gebot gibHoechstesGebot()
    {
        return hoechstesGebot;
    }
}
