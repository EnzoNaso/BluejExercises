/**
 * Eine Klasse, die einen einfachen Email-Client modelliert.
 * Ein Client ist einem bestimmten Benutzer zugeordnet und
 * sendet und empfängt Nachrichten über einen festgelegten Server.
 * 
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class MailClient
{
    // der Server zum Senden und Empfangen
    private MailServer server;
    // der Benutzer, dem dieser Client zugeordnet ist
    private String benutzer;

    /**
     * Erzeuge einen MailClient für den angegebenen Benutzer
     * und verbinde ihn mit dem gegebenen Server.
     * @param server der MailServer für diesen Client
     * @param benutzer der Name des Benutzers dieses MailClients.
     */
    public MailClient(MailServer server, String benutzer)
    {
        this.server = server;
        this.benutzer = benutzer;
    }

    /**
     * Liefere die nächste Nachricht (falls vorhanden)
     * für den Benutzer dieses Clients.
     */
    public Nachricht gibNaechsteNachricht()
    {
        return server.gibNaechsteNachrichtFuer(benutzer);
    }

    /**
     * Gib die nächste Nachricht (falls vorhanden) für den
     * Benutzer dieses Clients auf der Konsole aus.
     */
    public void naechsteNachrichtAusgeben()
    {
        Nachricht nachricht = server.gibNaechsteNachrichtFuer(benutzer);
        if(nachricht == null) {
            System.out.println("Keine neue Nachricht.");
        }
        else {
            nachricht.ausgeben();
        }
    }

    /**
     * Sende den gegebenen Nachrichtentext an den angebenen
     * Empfänger über den zugeordneten Mail-Server.
     * @param empfaenger der gewünschte Empfänger
     * @param text der Text der Nachricht
     */
    public void sendeNachricht(String empfaenger, String text, String betreff)
    {
        Nachricht nachricht = new Nachricht(benutzer, empfaenger, text, betreff);
        server.leiteWeiter(nachricht);
    }
}
