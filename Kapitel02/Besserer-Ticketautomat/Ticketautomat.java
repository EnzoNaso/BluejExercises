/**
 * Die Klasse Ticketautomat modelliert einfache Ticketautomaten,
 * die Tickets zu einem Einheitspreis herausgeben.
 * Der Preis für die Tickets eines Automaten kann über den Konstruktor
 * festgelegt werden.
 * Ticketautomaten dieser Klasse prüfen, ob sinnvolle Geldbeträge
 * eingeworfen werden, und drucken ein Ticket nur dann, wenn
 * ausreichend Geld eingeworfen wurde.
 *
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class Ticketautomat
{
    // Der Preis eines Tickets dieses Automaten.
    private int preis;
    // Der Betrag, der bisher vom Automatenbenutzer eingeworfen wurde.
    private int bisherGezahlt;
    // Die Geldsumme, die bisher von diesem Automaten eingenommen wurde.
    private int gesamtsumme;
    //Rabatt 
    private int rabatt;

    /**
     * Erzeuge einen Automaten, der Tickets zum angegebenen Preis
     * (in Cent) ausgibt.
     */
    public Ticketautomat(int ticketpreis)
    {
        this.preis = ticketpreis;
        this.bisherGezahlt = 0;
        this.gesamtsumme = 0;
        this.rabatt = 0;
    }

    /**
     * Liefere den Preis inkl. den Rabatt eines Tickets dieses Automaten (in Cent).
     */
    public double gibPreisInklRabatt()
    {
        return this.preis * (100.0 - this.rabatt) / 100.0;
    }

    public int gibRabatt()
    {
        return this.rabatt;
    }
    
    public void setzeRabatt(int rabatt)
    {
        this.rabatt = rabatt;
    }
    
    /**
     * Liefere die Höhe des Betrags, der für das nächste Ticket bereits
     * eingeworfen wurde.
     */
    public int gibBisherGezahltenBetrag()
    {
        return this.bisherGezahlt;
    }

    /**
     * Nimm den angegebenen Betrag (in Cent) als Anzahlung für das 
     * nächste Ticket. Prüfe, ob der Betrag sinnvoll ist.
     */
    public void geldEinwerfen(int betrag)
    {
        if (betrag <= 0) {
            System.out.println("Bitte nur positive Beträge verwenden: "
                + betrag);
        }
        else 
        {
            this.bisherGezahlt += betrag;

        }
    }

    /**
     * Drucke ein Ticket, wenn genügend Geld eingeworfen wurde, und 
     * ziehe den Ticketpreis vom bisher gezahlten Betrag ab. Gib eine 
     * Fehlermeldung aus, falls noch Geld für ein Ticket fehlt.
     */
    public void ticketDrucken()
    {
        double preisinklrabatt = this.preis * (100.0 - this.rabatt) / 100.0;
        double nochZuZahlen = preisinklrabatt - this.bisherGezahlt;

        if (nochZuZahlen <= 0) {

            // Den Ausdruck eines Tickets simulieren.
            System.out.println("##################");
            System.out.println("# Die BlueJ-Linie");
            System.out.println("# Ticket");
            System.out.println("# " + preisinklrabatt + " Cent.");
            System.out.println("##################");
            System.out.println();

            // Die Gesamtsumme um den Ticketpreis erhöhen.
            this.gesamtsumme += preisinklrabatt;
            // Den Preis von der bisherigen Bezahlung abziehen.
            this.bisherGezahlt -= preisinklrabatt;
            return;

        }

        System.out.println("Sie müssen noch mindestens " +
            (nochZuZahlen) +
            " Cent einwerfen.");

    }

    /**
     * Gib das Wechselgeld bzw. den bisher gezahlten Betrag zurück.
     * Setze den bisher gezahlten Betrag zurück auf 0.
     */
    public int wechselgeldAuszahlen()
    {
        int wechselgeld = bisherGezahlt;
        this.bisherGezahlt = 0;

        //         double anzahl = 5.0;
        //         double gesamt = 1000.0;
        //         double rabatt = 0.05;
        //         double preis = 1000.0;
        //         
        //         double ersparnis = preis * rabatt;
        //         double durchschnitt = gesamt / anzahl;
        //         
        return wechselgeld;
    }

    public int entleeren()
    {
        int temp = gesamtsumme;
        this.gesamtsumme = 0;
        return temp;

    }
}
