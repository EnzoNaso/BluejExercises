/**
 * Eine textuelle Schnittstelle für ein Adressbuch.
 * Über verschiedene Befehle kann auf das Adressbuch
 * zugegriffen werden.
 *
 * @author David J. Barnes und Michael Kölling.
 * @version 31.07.2011
 */
public class AdressbuchTexteingabe
{
    // Das Adressbuch, das angezeigt und manipuliert werden soll.
    private Adressbuch buch;
    // Ein Parser für die Befehlswörter.
    private Parser parser;
    
    /**
     * Konstruktor für Objekte der Klasse AdressbuchTexteingabe.
     * @param buch das Adressbuch, das manipuliert werden soll.
     */
    public AdressbuchTexteingabe(Adressbuch buch)
    {
        this.buch = buch;
        parser = new Parser();
    }
    
    /**
     * Lies interaktiv Befehle vom Benutzer, die 
     * Interaktionen mit dem Adressbuch ermöglichen.
     * Stoppe, wenn der Benutzer 'ende' eingibt.
     */
    public void starten()
    {
        System.out.println(" -- Adressbuch --");
        System.out.println("Tippen Sie 'hilfe' für eine Liste der Befehle.");
        
        String command;
        do{
            command = parser.liefereBefehl();
            if(command.equals("neu")){ 
                neuerEintrag();
            }
            else if(command.equals("gib")){
                gibEintrag();
            }
            else if(command.equals("liste")){
                list();
            }
            else if(command.equals("suche")){
                sucheEintrag();
            }
            else if(command.equals("entferne")){
                entferneEintrag();
            }
            else if(command.equals("hilfe")){
                hilfe();
            }
            else{
                // nichts tun.
            }
        } while(!(command.equals("ende")));

        System.out.println("Ade.");
    }
    
    /**
     * Füge einen neuen Eintrag hinzu.
     */
    private void neuerEintrag()
    {
        System.out.print("Name: ");
        String name = parser.zeileEinlesen();
        System.out.print("Telefon: ");
        String telefon = parser.zeileEinlesen();
        System.out.print("Adresse: ");
        String adresse = parser.zeileEinlesen();
        buch.neuerKontakt(new Kontakt(name, telefon, adresse));
    }
    
    /**
     * Liefere den Eintrag zu einem Schlüssel.
     */
    private void gibEintrag()
    {
        System.out.println("Geben Sie einen Schlüssel des Eintrags an:");
        String schluessel = parser.zeileEinlesen();
        Kontakt ergebnis = buch.gibKontakt(schluessel);
        System.out.println(ergebnis);
    }
    
    /**
     * Entferne einen Eintrag, auf den der Schlüssel passt.
     */
    private void entferneEintrag()
    {
        System.out.println("Schlüssel des zu löschenden Eintrags:");
        String schluessel = parser.zeileEinlesen();
        buch.entferneKontakt(schluessel);
    }
    
    /**
     * Finde Einträge mit passendem Präfix.
     */
    private void sucheEintrag()
    {
        System.out.println("Präfix des Suchschlüssels:");
        String praefix = parser.zeileEinlesen();
        Kontakt[] ergebnisse = buch.suche(praefix);
        for(int i = 0; i < ergebnisse.length; i++){
            System.out.println(ergebnisse[i]);
            System.out.println("=====");
        }
    }
    
    /**
     * Zeige die zur Verfügung stehenden Befehle.
     */
    private void hilfe()
    {
        parser.zeigeBefehle();
    }
    
    /**
     * Gib den Inhalt des Adressbuchs aus.
     */
    private void list()
    {
        System.out.println(buch.alleKontaktdaten());
    }
}
