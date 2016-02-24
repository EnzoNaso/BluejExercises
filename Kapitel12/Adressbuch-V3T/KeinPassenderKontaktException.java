/**
 * Diese Exception hält den Schlüssel, für den keine
 * passenden Kontaktdaten im Adressbuch gefunden werden
 * konnten.
 * 
 * @author David J. Barnes und Michael Kölling.
 * @version 31.07.2011
 */
public class KeinPassenderKontaktException extends Exception
{
    // Der Schlüssel ohne passende Kontaktdaten.
    private String schluessel;

    /**
     * Speichere Fehlerinformationen.
     * @param schluessel der Schlüssel ohne passenden Kontakt.
     */
    public KeinPassenderKontaktException(String schluessel)
    {
        this.schluessel = schluessel;
    }

    /**
     * @return den fehlerhaften Schlüssel.
     */
    public String gibSchluessel()
    {
        return schluessel;
    }
    
    /**
     * @return einen Diagnosetext mit dem fehlerhaften Schlüssel.
     */
    public String toString()
    {
        return "Keine passender Kontakt für '"
                + schluessel + "' gefunden.";
    }
}
