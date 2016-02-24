import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Eine Klasse zur Verwaltung von Audiotracks.
 * Die einzelnen Tracks können abgespielt werden.
 * 
 * @author David J. Barnes und Michael Kölling.
 * @version 31.07.2011
 */
public class Musiksammlung
{
    // Eine ArrayList, in der die Musik-Tracks gespeichert werden können.
    private ArrayList<Track> tracks;
    // Ein Reader, der Musikdateien lesen und als Tracks laden kann
    private TrackReader reader;

    /**
     * Erzeuge eine Musiksammlung
     * @param ordnername der Ordner mit den Audiodateien.
     */
    public Musiksammlung(String ordnername)
    {
        tracks = new ArrayList<Track>();
        reader = new TrackReader();
        liesBibliothek(ordnername);
    }

    /**
     * Füge der Sammlung eine Track-Datei hinzu.
     * @param dateiname der Dateiname des hinzuzufügenden Tracks.
     */
    public void dateiHinzufuegen(String dateiname)
    {
        tracks.add(new Track(dateiname));
    }

    /**
     * Füge der Sammlung einen Track hinzu.
     * @param dateiname der hinzuzufügende Track.
     */
    public void trackHinzufuegen(Track track)
    {
        tracks.add(track);
    }
    
    /**
     * Hole einen Track aus der Sammlung.
     * @param index der Index des abzuspielenden Tracks.
     * @return den ausgewählten Track; oder null, falls der Track nicht existiert.
     */
    public Track gibTrack(int index)
    {
        if(gueltigerIndex(index)) {
            return tracks.get(index);
        }
        else {
            return null;
        }
    }
    
    /**
     * Liefere die Anzahl der Tracks in dieser Sammlung.
     * @return die Anzahl der tracks in dieser Sammlung.
     */
    public int gibAnzahlTracks()
    {
        return tracks.size();
    }
     
    /**
     * Liefere eine Kopie aller Tracks in der Sammlung.
     * @return alle Tracks in der Sammlung.
     */
    public List<Track> gibAlleTracks()
    {
        return new ArrayList<Track>(tracks);
    }
    
    /**
     * Liefere eine Liste der Tracks, sortiert nach den Interpreten.
     * @return die Tracks, sortiert nach Interpreten.
     */
    public List<Track> sortiereNachInterpret()
    {
        return sortiereNachFeld("Interpret");
    }
    
    /**
     * Liefere eine Liste der Tracks, sortiert nach den Titeln.
     * @return die Tracks, sortiert nach Titel.
     */
    public List<Track> sortiereNachTitel()
    {
       return sortiereNachFeld("Feld");
    }
    
    /**
     * Liefere eine sortierte Kopie der Track-Liste.
     * @param vergleichsobjekt der Comparator für die Sortierung.
     * @return eine sortierte Kopie der Liste.
     */
    private List<Track> sortiereNach(Comparator<Track> vergleichsobjekt)
    {
        List<Track> kopie = gibAlleTracks();
        Collections.sort(kopie, vergleichsobjekt);
        return kopie;
    }
    
    /**
     * Liefere eine Liste der Tracks, sortiert nach dem durch seinen Namen angegebenen Feld.
     * @param feld das Feld, nach dem sortiert werden soll, z.B. Titel, Interpret, etc.
     *              @see Track.Felder
     * @return die Tracks, sortiert nach dem Feld.
     */
    public List<Track> sortiereNachFeld(final String feld)
    {
        return sortiereNach(new Comparator<Track>() {
            public int compare(Track t1, Track t2)
            {
                return t1.gibFeld(feld).compareTo(t2.gibFeld(feld));
            }
        });
    }
    
    /**
     * Entferne einen Track aus der Sammlung.
     * @param index der Index, des zu entfernenden Tracks.
     */
    public void entferneTrack(int index)
    {
        if(gueltigerIndex(index)) {
            tracks.remove(index);
        }
    }

    /**
     * Stelle fest, ob der gegebene Index für die Sammlung gültig ist.
     * Falls nicht, wird eine Fehlermeldung ausgegeben.
     * @param index der zu prüfende Index.
     * @return true, wenn der Index gültig ist, andernfalls false.
     */
    private boolean gueltigerIndex(int index)
    {
        // Der Rückgabewert.
        // Setze den Rückgabewert abhängig davon, ob der Index gültig ist oder nicht.
        boolean gueltig;
        
        if(index < 0) {
            System.out.println("Indizes koennen nicht negativ sein: " + index);
            gueltig = false;
        }
        else if(index >= tracks.size()) {
            System.out.println("Index ist zu gross: " + index);
            gueltig = false;
        }
        else {
            gueltig = true;
        }
        return gueltig;
    }
    
    public void liesBibliothek(String ordnerName)
    {
        ArrayList<Track> tempTracks = reader.liesTracks(ordnerName, ".mp3");

        // Alle Tracks in die Sammlung einfügen.
        for(Track track : tempTracks) {
            trackHinzufuegen(track);
        }
    }
    
}
