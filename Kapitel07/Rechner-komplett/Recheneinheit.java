/**
 * Die Haupteinheit eines Rechners, die die Berechnungen
 * durchführt.
 * 
 * @author  David J. Barnes und Michael Kölling 
 * @version 31.07.2011
 */
public class Recheneinheit
{
    // Der Zustand der Recheneinheit wird in drei Datenfeldern
    // gehalten:
    // anzeigewertImAufbau, linkerOperandGegeben, letzterOperator

    // Setzen wir gerade einen Wert in der Anzeige zusammen oder
    // wird die nächste Ziffer einen neuen beginnen?
    private boolean anzeigewertImAufbau;
    // Wurde bereits ein linker Operand eingegeben (oder berechnet)?
    private boolean linkerOperandGegeben;
    // Der zuletzt eingebene Operator.
    private char letzterOperator;

    // Der aktuelle Wert, der in der Anzeige gezeigt wird
    // (bzw. gezeigt werden soll)
    private int anzeigewert;
    // Der Wert des linken Operanden, falls gegeben.
    private int linkerOperand;

    /**
     * Erzeuge eine Recheneinheit.
     */
    public Recheneinheit()
    {
        clear();
    }

    /**
     * @return den Wert, der aktuell in der Anzeige gezeigt
     * werden soll.
     */
    public int gibAnzeigewert()
    {
        return anzeigewert;
    }

    /**
     * Eine Zifferntaste wurde getippt. Entweder einen neuen
     * Operanden starten oder diese Ziffer als Einerstelle
     * in einen bereits gegebenen Operanden einarbeiten.
     * @param ziffer die getippte Ziffer
     */
    public void zifferGetippt(int ziffer)
    {
        if(anzeigewertImAufbau) {
            // Diese Ziffer einarbeiten.
            anzeigewert = anzeigewert*10 + ziffer;
        }
        else {
            // Einen neuen Operanden beginnen.
            anzeigewert = ziffer;
            anzeigewertImAufbau = true;
        }
    }

    /**
     * Die Plus-Taste wurde getippt.
     */
    public void plus()
    {
        operatorAnwenden('+');
    }

    /**
     * Die Minus-Taste wurde getippt.
     */
    public void minus()
    {
        operatorAnwenden('-');
    }
    
    /**
     * Die Gleich-Taste wurde getippt.
     */
    public void gleich()
    {
        // Dies sollte den Aufbau eines zweiten Operanden abschließen,
        // also prüfen wir, ob ein linker Operand, ein Operator und
        // ein rechter Operand gegeben sind.
        if(linkerOperandGegeben &&
                letzterOperator != '?' &&
                anzeigewertImAufbau) {
            berechneErgebnis();
            letzterOperator = '?';
            anzeigewertImAufbau = false;
        }
        else {
            tippfehlerMelden();
        }
    }

    /**
     * Die C-Taste (für 'Clear') wurde getippt.
     * Versetze diese Recheneinheit in den Anfangszustand.
     */
    public void clear()
    {
        letzterOperator = '?';
        linkerOperandGegeben = false;
        anzeigewertImAufbau = false;
        anzeigewert = 0;
    }

    /**
     * @return den Titel dieser Recheneinheit.
     */
    public String gibTitel()
    {
        return "Java-Rechner";
    }

    /**
     * @return den Autor dieser Recheneinheit.
     */
    public String gibAutor()
    {
        return "David J. Barnes und Michael Kölling";
    }

    /**
     * @return die Versionsnummer dieser Recheneinheit.
     */
    public String gibVersion()
    {
       return "Version 1.0";
    }

    /**
     * Kombiniere linkerOperand, letzterOperator und den
     * aktuellen Anzeigewert.
     * Das Ergebnis wird sowohl zum linken Operand als auch
     * zum neuen Anzeigewert.
     */
    private void berechneErgebnis()
    {
        switch(letzterOperator) {
            case '+':
                anzeigewert = linkerOperand + anzeigewert;
                linkerOperandGegeben = true;
                linkerOperand = anzeigewert;
                break;
            case '-':
                anzeigewert = linkerOperand - anzeigewert;
                linkerOperandGegeben = true;
                linkerOperand = anzeigewert;
                break;
            default:
                tippfehlerMelden();
                break;
        }
    }
    
    /**
     * Wende den gegebenen Operator an.
     * @param operator der anzuwendende Operator
     */
    private void operatorAnwenden(char operator)
    {
        // Wenn wir nicht gerade einen neuen Operanden bauen,
        // dann ist dies ein Fehler, es sei denn, dass wir
        // gerade ein Ergebnis mit '=' berechnet haben.
        if(!anzeigewertImAufbau &&
                    !(linkerOperandGegeben && letzterOperator == '?')) {
            tippfehlerMelden();
            return;
        }

        if(letzterOperator != '?') {
            // Zuerst den vorherigen Operator anwenden.
            berechneErgebnis();
        }
        else {
            // Der anzeigewert wird zum linken Operanden dieses
            // neuen Operators.
            linkerOperandGegeben = true;
            linkerOperand = anzeigewert;
        }
        letzterOperator = operator;
        anzeigewertImAufbau = false;
    }

    /**
     * Melde, dass ein Tippfehler aufgetreten ist.
     */
    private void tippfehlerMelden()
    {
        System.out.println("Ein Tippfehler ist aufgetreten.");
        // Alles zurück setzen.
        clear();
    }
}
