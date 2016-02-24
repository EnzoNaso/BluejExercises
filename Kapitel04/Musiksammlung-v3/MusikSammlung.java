import java.util.ArrayList;

/**
 * Eine Klasse zur Verwaltung von Audiodateien.
 * 
 * @author David J. Barnes und Michael Kölling.
 * @version 31.07.2011
 */
public class MusikSammlung
{
    // Eine ArrayList, in der die Namen von Audiodateien gespeichert werden können.
    private ArrayList<String> dateien;
    // Ein Player zum Abspielen der Musikdateien.
    private MusikPlayer player;

    /**
     * Erzeuge eine MusikSammlung
     */
    public MusikSammlung()
    {
        dateien = new ArrayList<String>();
        player = new MusikPlayer();
    }

    /**
     * Füge der Sammlung eine Datei hinzu.
     * @param dateiname die hinzuzufügende Datei.
     */
    public void dateiHinzufuegen(String dateiname)
    {
        dateien.add(dateiname);
    }

    /**
     * Liefere die Anzahl der Dateien in dieser Sammlung.
     * @return die Anzahl der Dateien in dieser Sammlung.
     */
    public int gibAnzahlDateien()
    {
        return dateien.size();
    }


    /**
     * Gib eine Datei aus der Sammlung auf die Konsole aus.
     * @param index der Index der Datei, deren Name ausgegeben werden soll.
     */
    public void dateiAusgeben(int index)
    {
        if(gueltigerIndex(index)) {
            String dateiname = dateien.get(index);
            System.out.println(dateiname);
        }
    }    
    
    /**
     * Gib eine Liste aller Dateien in der Sammlung aus.
     */
    public void alleDateienAusgeben()
    {
        for(String dateiname : dateien) {
            System.out.println(dateiname);
        }
    }
    
    /**
     * Entferne eine Datei aus der Sammlung.
     * @param index der Index der zu entfernenden Datei.
     */
    public void entferneDatei(int index)
    {
        if(gueltigerIndex(index)) {
            dateien.remove(index);
        }
    }

    /**
     * Starte das Abspielen einer Datei aus der Sammlung.
     * Zum Beenden des Abspielvorgangs verwende beendeAbspielen().
     * @param index der Index der abzuspielenden Datei.
     */
    public void starteAbspielen(int index)
    {
        if(gueltigerIndex(index)) {
            String dateiname = dateien.get(index);
            player.starteAbspielen(dateiname);
        }
    }

    /**
     * Stoppt den Player.
     */
    public void beendeAbspielen()
    {
        player.stop();
    }


    /**
     * Spiele eine Datei aus der Sammlung. Kehrer erst zurück, wenn das Abspielen beendet ist.
     * @param index der Index der abzuspielenden Datei.
     */
    public void abspielenUndWarten(int index)
    {
        if(gueltigerIndex(index)) {
            String dateiname = dateien.get(index);
            player.dateiAnspielen(dateiname);
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
        else if(index >= dateien.size()) {
            System.out.println("Index ist zu gross: " + index);
            gueltig = false;
        }
        else {
            gueltig = true;
        }
        return gueltig;
    }
    
}
