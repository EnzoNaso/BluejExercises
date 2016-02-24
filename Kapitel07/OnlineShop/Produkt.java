import java.util.ArrayList;
import java.util.Iterator;

/**
 * Diese Klasse repräsentiert Produkte einer Online-E-Commerce-Website (wie 
 * z.B. Amazon.com). Produkt-Objekte speichern alle für das Produkt relevanten Daten, 
 * einschließlich einer Beschreibung, dem Preis, Kommentare der Kunden etc.
 * 
 * HINWEIS: Die vorliegende Version ist nicht vollständig! Derzeit ist nur der 
 * Code für die Behandlung der Kundenkommentare vorhanden.
 * 
 * @autor Michael Kölling und David J. Barnes
 * @version 0.1 (31.07.2011)
 */
public class Produkt
{
    private String name;
    private int preis;  // in Cent
    private ArrayList<Kommentar> kommentare;
    
    /**
     * Erzeuge ein neues Produkt.
     */
    public Produkt(String name, int preis)
    {
        this.name = name;
        this.preis = preis;
        kommentare = new ArrayList<Kommentar>();
    }

    /**
     * Liefere den Namen des Produkts.
     */
    public String gibName()
    {
        return name;
    }
    
    /**
     * Liefere den Preis des Produkts.
     */
    public int gibPreis()
    {
        return preis;
    }
    
    /**
     * Liefere die Anzahl an Kundenkommentaren zu diesem Produkt.
     */
    public int gibAnzahlKommentare()
    {
        return kommentare.size();
    }
    
    /**
     * Füge der Kommentarliste dieses Produkts einen Kommentar hinzu. Liefere im 
     * Erfolgsfall true, und false, wenn der Kommentar abgelehnt wurde.
     * 
     * Der Kommentar wird abgelehnt, wenn der Autor bereits einen Kommentar 
     * hinterlassen hat oder wenn die Bewertung ungültig ist. Gültige Bewertungen 
     * liegen zwischen 1 und 5 (inklusive).
     */
    public boolean kommentarHinzufuegen(String autor, String text, int bewertung)
    {
        if(bewertungUngueltig(bewertung)) {  // ungültige Bewertungen ablehnen
            return false;
        }
        
        if(findeKommentar(autor) != null) {  // mehrere Bewertungen desselben 
                                             // Autors ablehnen
            return false;
        }
        
        kommentare.add(new Kommentar(autor, text, bewertung));
        return true;
    }
    
    /**
     * Lösche den Kommentar, der unter dem angegebenen Index gespeichert ist. Ist der 
     * Index ungültig, tue nichts.
     */
    public void kommentarLoeschen(int index)
    {
        if(index >=0 && index < kommentare.size()) { // wenn der Index gültig ist
            kommentare.remove(index);
        }
    }
    
    /**
     * Stimme dem Kommentar unter 'index' zu. Das heißt, stufe diesen Kommentar als 
     * hilfreich ein. Ist der Index ungültig, tue nichts.
     */
    public void kommentarZustimmen(int index)
    {
        if(index >=0 && index < kommentare.size()) { // wenn der Index gültig ist
            kommentare.get(index).zustimmen();
        }
    }
    
    /**
     * Lehne den Kommentar unter 'index' ab. Das heißt, stufe diesen Kommentar als 
     * weniger hilfreich ein. Ist der Index ungültig, tue nichts.
     */
    public void kommentarAblehnen(int index)
    {
        if(index >=0 && index < kommentare.size()) { // wenn der Index gültig ist
            kommentare.get(index).ablehnen();
        }
    }
    
    /**
     * Gib alle Kommentare aus. (Zum Testen erfolgt die Ausgabe derzeit auf die 
     * Konsole. Soll später überarbeitet werden, sodass die Ausgabe im Web erfolgt.)
     */
    public void infoZeigen()
    {
        System.out.println("*** " + name + " ***");
        System.out.println("Preis: " + preisString(preis));
        System.out.println();
        System.out.println("Kundenkommentare:");
        for(Kommentar kommentar : kommentare) {
            System.out.println("-------------------------------------------");
            System.out.println(kommentar.gibAlleDetails());
        }
        System.out.println();
        System.out.println("===========================================");
    }
    
    /**
     * Liefere den Kommentar, der am hilfreichsten ist. Der nützlichste 
     * Kommentar ist derjenige mit dem höchsten Stimmenverhältnis. Gibt es mehrere
     * Kommentare mit dem höchsten Stimmenverhältnis, liefere irgendeinen von ihnen.
     */
    public Kommentar findeHilfreichstenKommentar()
    {
        Iterator<Kommentar> it = kommentare.iterator();
        Kommentar bester = it.next();
        while(it.hasNext()) 
        {
            Kommentar aktuell = it.next();
            if(aktuell.gibStimmenAnzahl() > bester.gibStimmenAnzahl()) {
                bester = aktuell;
            }
        }
        return bester;
    }
    
    /**
     * Prüfe, ob die gegebene Bewertung ungültig ist. Liefere true, wenn 
     * die Bewertung ungültig ist. Gültige Bewertungen liegen 
     * im Bereich [1..5].
     */
    private boolean bewertungUngueltig(int bewertung)
    {
        return bewertung < 0 || bewertung > 5;
    }
    
    /**
     * Finde den Kommentar des angegebenen Autors.
     * 
     * @return der Kommentar, falls ein solcher existiert, ansonsten null.
     */
    private Kommentar findeKommentar(String autor)
    {
        for(Kommentar kommentar : kommentare) {
            if(kommentar.gibAutor().equals(autor)) {
                return kommentar;
            }
        }
        return null;
    }
    
    /**
     * Liefere für einen als int angegebenen Preis den zugehörigen String, der 
     * denselben Preis repräsentiert. Der Preis wird in ganzen Cent angegeben. 
     * Für preis==12345 wird z.B. der folgende String zurückgeliefert: 
     * Euro 123,45
     */
    private String preisString(int preis)
    {
        int euro = preis / 100;
        int cents = preis - (euro*100);
        if(cents <= 9) {
            return "Euro" + euro + ",0" + cents;  // falls nötig 0 einfügen
        }
        else {
            return "Euro" + euro + "," + cents;
        }
    }
}
