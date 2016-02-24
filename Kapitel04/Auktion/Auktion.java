import java.util.ArrayList;

/**
 * Ein einfaches Modell einer Auktion.
 * Ein Exemplar dieser Klasse hält eine Liste von Posten,
 * die beliebig lang werden kann.
 * 
 * @author David J. Barnes und Michael Kölling.
 * @version 31.07.2011
 */
public class Auktion
{
    // eine Liste der Posten dieser Auktion
    private ArrayList<Posten> postenliste;
    // die Nummer, die an den nächsten Posten vergeben wird,
    // der für diese Auktion angemeldet wird.
    private int naechstePostennummer;

    /**
     * Erzeuge eine neue Auktion.
     */
    public Auktion()
    {
        postenliste = new ArrayList<Posten>();
        naechstePostennummer = 1;
    }

    /**
     * Melde einen Posten für diese Auktion an.
     * @param beschreibung die Beschreibung des Postens.
     */
    public void postenAnmelden(String beschreibung)
    {
        postenliste.add(new Posten(naechstePostennummer, beschreibung));
        naechstePostennummer++;
    }

    /**
     * Zeige die komplette Liste der Posten dieser Auktion.
     */
    public void zeigePostenliste()
    {
        for (Posten posten : postenliste) {
            System.out.println(posten.toString());
        }
    }

    /**
     * Gebe für einen Posten ein Angebot ab.
     * Es erfolgt eine Ausgabe, ob das Gebot erfolgreich war oder nicht.
     * @param postennummer Der Posten, für den geboten wird.
     * @param bieter die Person, die bietet.
     * @param betrag die Höhe des Gebots.
     */
    public void gibGebotAb(int postennummer, Person bieter, long betrag)
    {
        Posten gewaehlterPosten = gibPosten(postennummer);
        if(gewaehlterPosten != null) {
            Gebot gebot = new Gebot(bieter, betrag);
            boolean erfolgreich = gewaehlterPosten.hoeheresGebot(gebot);
            if(erfolgreich) {
                System.out.println("Das Gebot für Posten Nummer " +
                                   postennummer + " war erfolgreich.");
            }
            else {
                // Melden, welches Gebot höher ist.
                Gebot hoechstesGebot = gewaehlterPosten.gibHoechstesGebot();
                System.out.println("Posten Nummer " + postennummer +
                           ": Gebot steht bereits bei " +
                           hoechstesGebot.gibHoehe());
            }
        }
    }

    /**
     * Liefere den Posten mit der angegebenen Nummer.
     * Liefere 'null', wenn ein Posten mit dieser Nummer
     * nicht existiert.
     * @param nummer die Nummer des Postens, der geliefert
     *                werden soll.
     */
    public Posten gibPosten(int nummer)
    {
        if((nummer >= 1) && (nummer < naechstePostennummer)) {
            // die Nummer scheint gültig zu sein.
            Posten gewaehlterPosten = postenliste.get(nummer-1);
            // ein Sicherheitscheck, ob wir auch den richtigen
            // Posten haben:
            if(gewaehlterPosten.gibNummer() != nummer) {
                System.out.println("Interner Fehler: Posten Nummer " +
                                   gewaehlterPosten.gibNummer() +
                                   " wurde geliefert anstelle von " +
                                   nummer);
            }
            return gewaehlterPosten;
        }
        else {
            System.out.println("Einen Posten mit der Nummer: " + nummer +
                               " gibt es nicht.");
            return null;
        }
    }
}
