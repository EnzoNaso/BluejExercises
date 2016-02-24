/**
 * Eine Klasse, die einfache Nachrichten modelliert. Eine Nachricht hat
 * einen Absender und einen Empf�nger und enth�lt Text.
 * 
 * @author David J. Barnes und Michael K�lling
 * @version 31.07.2011
 */
public class Nachricht
{
    // Der Absender der Nachricht
    private String absender;
    // Der gew�nschte Empf�nger der Nachricht
    private String empfaenger;
    // Der Text der Nachricht
    private String text;
    // Der Betrefftext der Nachricht
    private String betreff;
    
    /**
     * Erzeuge eine Nachricht vom gegebenen 'absender' an den gegebenen
     * 'empfaenger' mit dem gegebenen 'text'.
     * @param absender der Absender dieser Nachricht
     * @param empfaenger der gew�nschte Empf�nger dieser Nachricht.
     * @param text der Text der Nachricht.
     * @param betreff der Betrefftext der Nachricht.
     */
    public Nachricht(String absender, String empfaenger, String text, String betreff)
    {
        this.absender = absender;
        this.empfaenger = empfaenger;
        this.text = text;
        this.betreff = betreff;
    }

    /**
     * @return den Absender dieser Nachricht.
     */
    public String gibAbsender()
    {
        return absender;
    }

    /**
     * @return den gew�nschten Empf�nger dieser Nachricht.
     */
    public String gibEmpfaenger()
    {
        return empfaenger;
    }

    /**
     * @return den Text dieser Nachricht.
     */
    public String gibText()
    {
        return text;
    }
    
    /**
     * @return den Betreff dieser Nachricht.
     */
    public String gibBetreff()
    {
        return betreff;
    }    

    /**
     * Gib Informationen �ber diese Nachricht auf der Konsole aus.
     */
    public void ausgeben()
    {
        System.out.println("Von: " + absender);
        System.out.println("An: " + empfaenger);
        System.out.println("Betreff: " + betreff);
        System.out.println("Text: " + text);
    }
}
