/**
 * Eine Klasse, die einfache Nachrichten modelliert. Eine Nachricht hat
 * einen Absender und einen Empfänger und enthält Text.
 * 
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class Nachricht
{
    // Der Absender der Nachricht
    private String absender;
    // Der gewünschte Empfänger der Nachricht
    private String empfaenger;
    // Der Text der Nachricht
    private String text;

    /**
     * Erzeuge eine Nachricht vom gegebenen 'absender' an den gegebenen
     * 'empfaenger' mit dem gegebenen 'text'.
     * @param absender der Absender dieser Nachricht
     * @param empfaenger der gewünschte Empfänger dieser Nachricht.
     * @param text der Text der Nachricht.
     */
    public Nachricht(String absender, String empfaenger, String text)
    {
        this.absender = absender;
        this.empfaenger = empfaenger;
        this.text = text;
    }

    /**
     * @return den Absender dieser Nachricht.
     */
    public String gibAbsender()
    {
        return absender;
    }

    /**
     * @return den gewünschten Empfänger dieser Nachricht.
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
     * Gib Informationen über diese Nachricht auf der Konsole aus.
     */
    public void ausgeben()
    {
        System.out.println("Von: " + absender);
        System.out.println("An: " + empfaenger);
        System.out.println("Text: " + text);
    }
}
