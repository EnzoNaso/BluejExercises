/**
 * Die Haupteinheit eines Rechners. Sie ist für
 * die arithmetischen Berechnungen zuständig.
 * 
 * @author  Hacker T. Largebrain
 * @version 1.0
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
     * @param ziffer die einzelne Ziffer.
     */
    public void zifferGetippt(int ziffer)
    {
        anzeigewert = anzeigewert * 10 + ziffer;
    }

    /**
     * Die '+'-Taste wurde getippt.
     */
    public void plus()
    {
        letztenOperatorAnwenden();
        letzterOperator = '+';
        anzeigewert = 0;
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
        if(letzterOperator == '+') {
            anzeigewert = linkerOperand + anzeigewert;
        }
        else {
            anzeigewert = linkerOperand - anzeigewert;
        }
        linkerOperand = 0;
    }

    /**
     * Die C-Taste (für 'Clear') wurde getippt.
     */
    public void clear()
    {
        anzeigewert = 0;
    }

    /**
     * @return den Titel dieser Recheneinheit.
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
     * Eine Taste mit einem Operator wurde getippt.
     * Wende den unmittelbar vorhergegangenen Operator
     * an, um ein Zwischenergebnis zu berechnen. Dieses
     * bildet dann den linken Operand des neuen Operators.
     */
    private void letztenOperatorAnwenden()
    {
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
    }
}
