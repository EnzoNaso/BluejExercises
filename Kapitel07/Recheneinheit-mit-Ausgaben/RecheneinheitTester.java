/**
 * Tests der Klasse Recheneinheit.
 * 
 * @author Hacker T. Largebrain 
 * @version 1.0
 */
public class RecheneinheitTester
{
    // Die zu testende Recheneinheit.
    private Recheneinheit recheneinheit;

    /**
     * Konstruktor für Objekte der Klasse RecheneinheitTester.
     */
    public RecheneinheitTester()
    {
        recheneinheit = new Recheneinheit();
    }
    
    /**
     * Alles testen.
     */
    public void allesTesten()
    {
        System.out.println("Testen der Addition.");
        System.out.println("Ergebnis ist: " + testPlus());
        System.out.println("Testen der Subtraktion.");
        System.out.println("Ergebnis ist: " + testMinus());
        System.out.println("Alle Tests erfolgreich.");
    }

    /**
     * Teste die Plus-Operation der Recheneinheit.
     * @return das Ergebnis der Berechnung 3+4.
     */
    public int testPlus()
    {
        // Sicherstellen, das die Einheit einen gültigen Zustand hat.
        recheneinheit.clear();
        // Simulieren der Tastendrücke: 3 + 4 =
        recheneinheit.zifferGetippt(3);
        recheneinheit.plus();
        recheneinheit.zifferGetippt(4);
        recheneinheit.gleich();
        // Liefere das Ergebnis, das 7 lauten sollte.
        return recheneinheit.gibAnzeigewert();
    }

    /**
     * Teste die Minus-Operation der Recheneinheit.
     * @return das Ergebnis der Berechnung 9 - 4.
     */
    public int testMinus()
    {
        // Sicherstellen, das die Einheit einen gültigen Zustand hat.
        recheneinheit.clear();
        // Simulieren der Tastendrücke: 9 - 4 =
        recheneinheit.zifferGetippt(9);
        recheneinheit.minus();
        recheneinheit.zifferGetippt(4);
        recheneinheit.gleich();
        // Liefere das Ergebnis, das 5 lauten sollte.
        return recheneinheit.gibAnzeigewert();
    }
}
