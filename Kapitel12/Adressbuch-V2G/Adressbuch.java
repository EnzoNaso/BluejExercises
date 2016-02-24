import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Eine Klasse zur Verwaltung einer beliebigen Anzahl von 
 * Kontakten. Die Daten werden sowohl über den Namen
 * als auch über die Telefonnummer indiziert.
 *
 * @author David J. Barnes und Michael Kölling.
 * @version 31.07.2011
 */
public class Adressbuch
{
    // Speicher für beliebige Anzahl von Kontakten.
    private TreeMap<String, Kontakt> buch;
    private int anzahlEintraege;

    /**
     * Initialisiere ein neues Adressbuch.
     */
    public Adressbuch()
    {
        buch = new TreeMap<String, Kontakt>();
        anzahlEintraege = 0;
    }
    
    /**
     * Schlage einen Namen oder eine Telefonnummer
     * nach und liefere den zugehörigen Kontakt.
     * @param schluessel der Name oder die Nummer zum Nachschlagen.
     * @return den zum Schlüssel gehörenden Kontakt.
     */
    public Kontakt gibKontakt(String schluessel)
    {
        return buch.get(schluessel);
    }

    /**
     * Ist der gegebene Schlüssel in diesem Adressbuch bekannt?
     * @param schluessel der Name oder die Nummer zum Nachschlagen.
     * @return true wenn der Schlüssel eingetragen ist, false sonst.
     */
    public boolean schluesselBekannt(String schluessel)
    {
        return buch.containsKey(schluessel);
    }

    /**
     * Füge einen neuen Kontakt in dieses Adressbuch ein.
     * @param kontakt der neue Kontakt.
     */
    public void neuerKontakt(Kontakt kontakt)
    {
        if(kontakt != null) {
            buch.put(kontakt.gibName(), kontakt);
            buch.put(kontakt.gibTelefon(), kontakt);
            anzahlEintraege++;
        }
    }
    
    /**
     * Ändere die Kontaktdaten, die bisher unter dem gegebenen
     * Schlüssel eingetragen waren.
     * @param alterSchluessel einer der verwendeten Schlüssel.
     *        Der Schlüssel sollte im Adressbuch bekannt sein.
     * @param daten die neuen Kontaktdaten.
     */
    public void aendereKontakt(String alterSchluessel,
                               Kontakt daten)
    {
        if(schluesselBekannt(alterSchluessel) && daten != null) {
            entferneKontakt(alterSchluessel);
            neuerKontakt(daten);
        }
    }
    
    /**
     * Suche nach allen Kontakten mit einem Schlüssel, der mit dem
     * gegebenen Präfix beginnt.
     * @param präfix der Schlüsselpräfix, nach dem gesucht werden
     *               soll. Die Länge kann Null sein, aber die 
     *               Referenz darf nicht null sein.
     * @return ein Array mit den gefundenen Kontakten.
     */
    public Kontakt[] suche(String praefix)
    {
        // Eine Liste für die Treffer anlegen.
        List<Kontakt> treffer = new LinkedList<Kontakt>();
        if(praefix != null) {
            // Finden von Schlüsseln, die gleich dem oder größer als
            // der Präfix sind.
            SortedMap<String, Kontakt> rest = buch.tailMap(praefix);
            Iterator<String> it = rest.keySet().iterator();
            // Stoppen bei der ersten Nichtübereinstimmung.
            boolean sucheBeendet = false;
            while(!sucheBeendet && it.hasNext()) {
                String schluessel = it.next();
                if(schluessel.startsWith(praefix)) {
                    treffer.add(buch.get(schluessel));
                }
                else {
                    sucheBeendet = true;
                }
            }
        }
        Kontakt[] ergebnisse = new Kontakt[treffer.size()];
        treffer.toArray(ergebnisse);
        return ergebnisse;
    }

    /**
     * Liefere die Anzahl der Einträge aktuell in diesem Adressbuch.
     * @return die Anzahl der Einträge.
     */
    public int gibAnzahlEintraege()
    {
        return anzahlEintraege;
    }

    /**
     * Entferne den Eintrag mit dem gegebenen Schlüssel aus
     * diesem Adressbuch. Bei einem unbekannten Schlüssel tue nichts.
     * @param schlüssel einer der Schlüssel des Eintrags, 
     *                  der entfernt werden soll.
     */
    public void entferneKontakt(String schluessel)
    {
        if(schluesselBekannt(schluessel)) {
            Kontakt kontakt = buch.get(schluessel);
            buch.remove(kontakt.gibName());
            buch.remove(kontakt.gibTelefon());
            anzahlEintraege--;
        }
    }

    /**
     * Liefere alle Kontaktdaten, sortiert nach der
     * Sortierreihenfolge, die die Klasse Kontakt definiert.
     * @return die sortierten Kontaktdaten.
     */
    public String alleKontaktdaten()
    {
        // Weil jeder Satz unter zwei Schlüsseln eingetragen ist,
        // muss ein Set mit den Kontakten gebildet werden. Dadurch
        // werden Duplikate entfernt.
        StringBuilder alleEintraege = new StringBuilder();
        Set<Kontakt> sortierteDaten = new TreeSet<Kontakt>(buch.values());
        for(Kontakt kontakt : sortierteDaten) {
            alleEintraege.append(kontakt);
            alleEintraege.append('\n');
            alleEintraege.append('\n');
        }
        return alleEintraege.toString();
    }
}
