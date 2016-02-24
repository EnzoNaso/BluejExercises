
/**
 * Signal, dass kein Fahrgast am Abholpunkt war.
 * 
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class FehlenderFahrgastException extends RuntimeException
{
    private static final long serialVersionUID = 20060330L;

    private Fahrzeug fahrzeug;

    /**
     * Konstruktor für Objekte der Klasse FehlenderFahrgastException. 
     * @param fahrzeug das Fahrzeug, das einen Passagier erwartet.
     */
    public FehlenderFahrgastException(Fahrzeug fahrzeug)
    {
        super("Kein Fahrgast am Abholpunkt.");
    }

    /**
     * @return das Fahrzeug, für das kein Fahrgast vorhanden war.
     */
    public Fahrzeug gibFahrzeug()
    {
        return fahrzeug;
    }
}
