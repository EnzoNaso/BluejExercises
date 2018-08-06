/**
 * Die Klasse Ticketautomat modelliert einfache Ticketautomaten,
 * die Tickets zu einem Einheitspreis herausgeben.
 * Der Preis für die Tickets eines Automaten kann über den Konstruktor
 * festgelegt werden.
 * Ein Ticketautomat ist insofern 'naiv', dass er seinen Benutzern
 * vertraut, dass sie genügend Geld einwerfen, bevor sie sich ein Ticket
 * ausdrucken lassen.
 * Außerdem nimmt er an, dass sinnvolle Beträge eingeworfen werden.
 *
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class Ticketautomat 
{
    // Die Geldsumme, die bisher von diesem Automaten eingenommen wurde.
    private int gesamtsumme;
    // Der Preis eines Tickets dieses Automaten.
    private int preis;
    // Der Betrag, der bisher vom Automatenbenutzer eingeworfen wurde.
    private int bisherGezahlt;
    
    private int punktestand;
    
    /**
     * Erzeuge eine Maschine, die Tickets zum angegebenen Preis
     * (in Cent) ausgibt.
     * Zu beachten: Der Preis muss größer als null sein,
     * der Automat überprüft dies jedoch nicht.
     */
    public Ticketautomat()
    {
        preis = 1000;
        bisherGezahlt = 0;
        gesamtsumme = 0;
    }

    public Ticketautomat(int ticketPreis)
    {
        preis = ticketPreis;
        bisherGezahlt = 0;
        gesamtsumme = 0;
    }
    
    /** * Reduziere den Preis um den gegebenen Betrag. */ 
    public void reduzieren(int betrag) 
    {
        preis -= betrag;
    }
    
    /** * Erhöhe den Punktestand um die angegebenen Punkte. */ 
    public void erhoehen(int punkte) 
    {
        punktestand += punkte;
    }

    public void leeren()
    {
        gesamtsumme = 0;
    }
    
    public void setzePreis(int neuerPreis)
    {
        preis = neuerPreis;
    }
    
    /**
     * Liefere den Preis eines Tickets dieses Automaten (in Cent).
     */
    public int gibPreis()
    {
        return preis;
    }

    /**
     * Liefere die Höhe des Betrags, der für das nächste Ticket bereits
     * eingeworfen wurde.
     */
    public int gibBetrag()
    {
        return bisherGezahlt;
    }

    
    public int gibGesamtsumme()
    {
        return gesamtsumme;
    }
    /**
     * Nimm den angegebenen Betrag (in Cent) als Anzahlung für das
     * nächste Ticket.
     */
    public void geldEinwerfen(int betrag)
    {
        bisherGezahlt += betrag;
    }

    /**
     * Drucke ein Ticket.
     * Aktualisiere die eingenommene Gesamtsumme und setze den gezahlten
     * Betrag auf null.
     */
    public void ticketDrucken()
    {
         // Den Ausdruck eines Tickets simulieren.
        System.out.println("##################");
        System.out.println("# Die BlueJ-Linie");
        System.out.println("# Ticket");
        System.out.println("# " + preis + " Cent.");
        System.out.println("##################");
        System.out.println();

        // Die Gesamtsumme mit dem eingezahlten Betrag aktualisieren.
        gesamtsumme = gesamtsumme + bisherGezahlt;
        // Die bisherige Bezahlung zurücksetzen.
        bisherGezahlt = 0;
    }
    
    public void meldung()
    {
        System.out.println("Bitte werfen Sie den passenden Geldbetrag ein.");
        
    }
    
    public void preisAusgeben()
    {
        System.out.println("Der Preis eines Tickets beträgt " + preis + " Cent.");
    }
}
