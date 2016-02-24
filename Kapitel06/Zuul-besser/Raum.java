import java.util.Set;
import java.util.HashMap;

/**
 * Diese Klasse modelliert Räume in der Welt von Zuul.
 * 
 * Ein "Raum" repräsentiert einen Ort in der virtuellen Landschaft des
 * Spiels. Ein Raum ist mit anderen Räumen über Ausgänge verbunden.
 * Für jeden existierenden Ausgang hält ein Raum eine Referenz auf 
 * den benachbarten Raum.
 * 
 * @author  Michael Kölling und David J. Barnes
 * @version 31.07.2011
 */

class Raum 
{
    private String beschreibung;
    private HashMap<String, Raum> ausgaenge;        // die Ausgänge dieses Raums

    /**
     * Erzeuge einen Raum mit einer Beschreibung. Ein Raum
     * hat anfangs keine Ausgänge.
     * @param beschreibung enthält eine Beschreibung in der Form
     *        "in einer Küche" oder "auf einem Sportplatz".
     */
    public Raum(String beschreibung) 
    {
        this.beschreibung = beschreibung;
        ausgaenge = new HashMap<String, Raum>();
    }

    /**
     * Definiere einen Ausgang für diesen Raum.
     * @param richtung die Richtung, in der der Ausgang liegen soll
     * @param nachbar der Raum, der über diesen Ausgang erreicht wird
     */
    public void setzeAusgang(String richtung, Raum nachbar) 
    {
        ausgaenge.put(richtung, nachbar);
    }

    /**
     * @return die kurze Beschreibung dieses Raums (die dem Konstruktor
     * übergeben wurde).
     */
    public String gibKurzbeschreibung()
    {
        return beschreibung;
    }

    /**
     * Liefere eine lange Beschreibung dieses Raums, in der Form:
     *     Sie sind in der Küche.
     *     Ausgänge: nord west
     * @return eine lange Beschreibung dieses Raumes.
     */
    public String gibLangeBeschreibung()
    {
        return "Sie sind " + beschreibung + ".\n" + gibAusgaengeAlsString();
    }

    /**
     * Liefere eine Beschreibung der Ausgänge dieses Raumes,
     * beispielsweise
     * "Ausgänge: north west".
     * @return eine Beschreibung der Ausgänge dieses Raumes.
     */
    private String gibAusgaengeAlsString()
    {
        String ergebnis = "Ausgänge:";
        Set<String> keys = ausgaenge.keySet();
        for(String ausgang : keys)
            ergebnis += " " + ausgang;
        return ergebnis;
    }

    /**
     * Liefere den Raum, den wir erreichen, wenn wir aus diesem Raum
     * in die angegebene Richtung gehen. Liefere 'null', wenn in
     * dieser Richtung kein Ausgang ist.
     * @param richtung die Richtung, in die gegangen werden soll.
     * @return den Raum in der angegebenen Richtung.
     */
    public Raum gibAusgang(String richtung) 
    {
        return ausgaenge.get(richtung);
    }
}

