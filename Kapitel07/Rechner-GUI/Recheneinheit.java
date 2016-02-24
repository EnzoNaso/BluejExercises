/**
 * Die Haupteinheit eines Rechners, die die Berechnungen
 * durchführt.
 * 
 * @author  (noch keiner) 
 * @version 0.1 (unvollständig)
 */
public class Recheneinheit
{
    // Hier die Datenfelder deklarieren

    /**
     * Erzeuge eine Recheneinheit.
     */
    public Recheneinheit()
    {
    }

    /**
     * @return den Wert, der aktuell in der Anzeige gezeigt
     * werden soll.
     */
    public int gibAnzeigewert()
    {
        return 0;
    }

    /**
     * Eine Zifferntaste wurde getippt.
     * Entweder einen neuen Operanden beginnen oder die getippte
     * Ziffer als letzte Stelle des aktuellen Operanden auffassen.
     * @param ziffer die getippte Ziffer
     */
    public void zifferGetippt(int ziffer)
    {
    }

    /**
     * Die Plus-Taste wurde getippt.
     */
    public void plus()
    {
    }

    /**
     * Die Minus-Taste wurde getippt.
     */
    public void minus()
    {
    }
    
    /**
     * Die Gleich-Taste wurde getippt.
     */
    public void gleich()
    {
    }

    /**
     * Die C-Taste (für 'Clear') wurde getippt.
     */
    public void clear()
    {
    }

    /**
     * @return den Titel dieser Recheneinheit.
     */
    public String gibTitel()
    {
        return "";
    }

    /**
     * @return den Autor dieser Recheneinheit. Die 
     * Zeichenkette wird genauso, wie sie geliefert wird,
     * ausgegeben. Sie sollte also etwas enthalten wie
     * "Erstellt von H. Simpson".
     */
    public String gibAutor()
    {
        return "";
    }

    /**
     * @return die Versionsnummer dieser Recheneinheit. Die
     * Zeichenkette wird genauso, wie sie geliefert wird,
     * ausgegeben. Sie sollte also etwas enthalten wie
     * "Version 1.1".
     */
    public String gibVersion()
    {
       return "";
    }
}
