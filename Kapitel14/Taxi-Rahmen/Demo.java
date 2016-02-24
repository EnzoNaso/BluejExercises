/**
 * Erzeuge eine Fahrgast-Quelle und ein Taxi-Unternehmen,
 * um das Szenario einer Abholung zu illustrieren.
 * 
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class Demo
{
    private Fahrgastquelle quelle;
    private Taxiunternehmen unternehmen;

    /**
     * Erzeuge einige Test-Objekte für die Demo.
     */
    public Demo()
    {
        unternehmen = new Taxiunternehmen();
        quelle = new Fahrgastquelle(unternehmen);
    }

    /**
     * Erbitte die Abholung eines Fahrgastes.
     * In dieser Version wird diese Anfrage fehlschlagen.
     */
    public void testeAbholung()
    {
        if(quelle.erbitteAbholung()) {
            System.out.println("Bitte um Abholung war erfolgreich.");
        }
        else {
            System.out.println("Bitte um Abholung nicht erfüllt.");
        }
    }
}
