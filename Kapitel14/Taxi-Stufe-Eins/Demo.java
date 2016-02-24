import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

/**
 * Eine einfache Demonstration eines Szenarios aus Stufe
 * Eins. Ein einzelner Fahrgast wird erzeugt und ein Taxi
 * für ihn gerufen. Während der Simulation sollte der 
 * Fahrgast abgeholt und zu seinem Ziel gebracht werden.
 * 
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class Demo
{
    private List<Akteur> akteure;

    /**
     * Konstruktor für Objekte der Klasse Demo
     */
    public Demo()
    {
        akteure = new LinkedList<Akteur>();
        zuruecksetzen();
    }
    
    /**
     * Starte eine Demo mit einer festen Anzahl an Schritten.
     */
    public void starten()
    {
        for(int schritt = 0; schritt < 100; schritt++) {
            schritt();
        }
    }

    /**
     * Setze die Demo um einen Schritt fort, indem
     * alle Akteure zum Agieren aufgefordert werden.
     */
    public void schritt()
    {
        for(Akteur akteur : akteure) {
            akteur.agiere();
        }
    }
    
    /**
     * Setze die Demo an den Anfangspunkt zurück.
     * Es wird ein einzelnes Taxi erzeugt und die
     * Abholung eines einzelnen Fahrgastes wird erbeten.
     * @throws IllegalStateException Wenn kein Taxi frei ist.
     */
    public void zuruecksetzen()
    {
        akteure.clear();
        Taxiunternehmen unternehmen = new Taxiunternehmen();
        Taxi taxi = new Taxi(unternehmen, new Position(10, 10));
        List<Fahrzeug> fahrzeuge = unternehmen.gibFahrzeuge();
        fahrzeuge.add(taxi);
        akteure.addAll(fahrzeuge);
        
        Fahrgast fahrgast = new Fahrgast(new Position(0, 0),
                                         new Position(10, 20));
        if(!unternehmen.taxirufFuer(fahrgast)) {
            throw new IllegalStateException("kein freies Taxi.");
        }
    }
}
