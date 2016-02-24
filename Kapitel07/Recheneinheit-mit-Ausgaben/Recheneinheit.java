/**
 * Die Haupteinheit eines Rechners. Sie ist für
 * die arithmetischen Berechnungen zuständig.
 * 
 * @author  Hacker T. Largebrain
 * @version 1.1
 */
public class Recheneinheit
{
    // Der Anzeigewert.
    private int anzeigewert;
    // Der zuletzt getippte Operator (+ oder -).
    private char letzterOperator;
    // Der linke Operand zum letzten Operator.
    private int linkerOperand;

    /**
     * Erzeuge eine Recheneinheit.
     */
    public Recheneinheit()
    {
        anzeigewert = 0;
        letzterOperator = ' ';
        linkerOperand = 0;
    }

    /**
     * Liefere den Wert, der aktuell in der Anzeige gezeigt
     * wird.
     */
    public int gibAnzeigewert()
    {
        return anzeigewert;
    }

    /**
     * Eine Zifferntaste wurde getippt.
     */
    public void zifferGetippt(int ziffer)
    {
        System.out.println("zifferGetippt aufgerufen mit: "
                            + ziffer);
        anzeigewert = anzeigewert * 10 + ziffer;
        zustandAusgeben("Ende von zifferGetippt");
    }

    /**
     * Die '+'-Taste wurde getippt.
     */
    public void plus()
    {
        System.out.println("plus wurde aufgerufen.");
        letztenOperatorAnwenden();
        letzterOperator = '+';
        anzeigewert = 0;
        zustandAusgeben("Ende von plus");
    }

    /**
     * Die '-'-Taste wurde getippt.
     */
    public void minus()
    {
        letztenOperatorAnwenden();
        letzterOperator = '-';
        anzeigewert = 0;
    }
    
    /**
     * Die Gleich-Taste wurde getippt.
     */
    public void gleich()
    {
        System.out.println("gleich wurde aufgerufen.");
        if(letzterOperator == '+') {
            anzeigewert = linkerOperand + anzeigewert;
        }
        else {
            anzeigewert = linkerOperand - anzeigewert;
        }
        linkerOperand = 0;
        zustandAusgeben("Ende von gleich");
    }

    /**
     * Die C-Taste (für 'Clear') wurde getippt.
     */
    public void clear()
    {
        System.out.println("clear wurde aufgerufen.");
        anzeigewert = 0;
        zustandAusgeben("Ende von clear");
    }

    /**
     * Liefere den Titel dieser Recheneinheit.
     */
    public String gibTitel()
    {
        return "Der Super-Rechner";
    }

    /**
     * @return den Autor dieser Recheneinheit.
     */
    public String gibAutor()
    {
        return "Hacker T. Largebrain";
    }

    /**
     * @return die Versionsnummer dieser Recheneinheit.
     */
    public String gibVersion()
    {
       return "Version 0.2";
    }
    
    /**
     * Gib die Werte aller Datenfelder dieses Objekts aus.
     * @param wo an welcher Stelle der Zustand ausgegeben wird.
     */
    private void zustandAusgeben(String wo)
    {
        System.out.println("anzeigewert: " + anzeigewert
                           + " linkerOperand: " + linkerOperand
                           + " letzterOperator: " + letzterOperator
                           + " am " + wo);
    }
    
    /**
     * Eine Taste mit einem Operator wurde getippt.
     * Wende den unmittelbar vorhergegangenen Operator
     * an, um ein Zwischenergebnis zu berechnen. Dieses
     * bildet dann den linken Operand des neuen Operators.
     */
    private void letztenOperatorAnwenden()
    {
        System.out.println("letzenOperatorAnwenden wurde aufgerufen");
        if(letzterOperator == '+') {
            linkerOperand += anzeigewert;
        }
        else if(letzterOperator == '-') {
            linkerOperand -= anzeigewert;
        }
        else {
            // Es gibt keinen letzten Operator.
            linkerOperand = anzeigewert;
        }
        zustandAusgeben("Ende von letztenOperatorAnwenden");
    }
    
}
