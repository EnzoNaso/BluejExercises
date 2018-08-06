import java.util.ArrayList;

/**
 * Eine Klasse zur Verwaltung von Audiodateien.
 * Diese Version kann die Dateien auch abspielen.
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
        if(index >= 0 && index < dateien.size()) {
            String dateiname = dateien.get(index);
            System.out.println(dateiname);
        }
    }    
    
    /**
     * Entferne eine Datei aus der Sammlung.
     * @param index der Index, der zu entfernenden Datei.
     */
    public void entferneDatei(int index)
    {
        if(index >= 0 && index < dateien.size()) {
            dateien.remove(index);
        }
    }

    /**
     * Starte das Abspielen einer Datei aus der Sammlung.
     * Zum Beenden des Abspielvorgangs verwende beendeAbspielen() .
     * @param index der Index, der abzuspielenden Datei.
     */
    public void starteAbspielenDatei(int index)
    {
        String dateiname = dateien.get(index);
        player.starteAbspielen(dateiname);
    }

    /**
     * Stoppt den Player.
     */
    public void beendeAbspielen()
    {
        player.stop();
    }   
    
    public void alleDateienAusgeben()
    {
        int position = 0;
        for(String datei : dateien)
        {
            //position++;
            System.out.println(position++ + ": " + datei);
        }
    }
    
    public void bestimmteDateienAusgeben(String suchbegriff)
    { 
        boolean gefunden = false;
        for (String dateiname : dateien) 
        { 
            if (dateiname.contains(suchbegriff)) 
            {   
                // Treffer. 
                System.out.println(dateiname);
                gefunden = true;
            }
        }
        
        if (!gefunden)
        {
            System.out.println(suchbegriff + " nicht gefunden.");
        }
    } 
    
}
