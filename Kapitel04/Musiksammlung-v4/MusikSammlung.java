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
        if(index >= 0 && index < dateien.size()) {
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
     * Liste die Namen aller Dateien auf, die mit einer zu suchenden Zeichenfolge übereinstimmen.
     * @param suchbegriff die Zeichenfolge, die im Namen enthalten sein muss.
     */
    public void bestimmteDateienAusgeben(String suchbegriff)
    {
        for(String dateiname : dateien) {
            if(dateiname.contains(suchbegriff)) {
                // Treffer.
                System.out.println(dateiname);
            }
        }
    }

    /**
     * Finde den Index der ersten Datei, die mit dem Suchbegriff übereinstimmt.
     * @param suchbegriff die Zeichenfolge, die im Namen enthalten sein muss.
     * @return der Index der ersten Überstimmung oder -1, wenn
     *         kein übereinstimmender Dateiname gefunden wurde.
     */
    public int findeErste(String suchbegriff)
    {
        int index = 0;
        // Wir suchen so lange, bis eine Übereinstimmung gefunden wurde.
        boolean amSuchen = true;
        int dateienAnzahl = dateien.size();
        
        while(amSuchen && index < dateienAnzahl) {
            String dateiname = dateien.get(index);
            if(dateiname.contains(suchbegriff)) {
                // Eine Übereinstimmung. Wir können die Suche beenden.
                amSuchen = false;
            }
            else {
                // Weiter.
                index++;
            }
        }
        if(amSuchen) {
            // Nichts gefunden.
            return -1;
        }
        else {
            // Teile mit, wo die Übereinstimmung gefunden wurde.
            return index;
        }
    }
    
    /**
     * Entferne eine Datei aus der Sammlung.
     * @param index der Index der zu entfernenden Datei.
     */
    public void entferneDatei(int index)
    {
        if(index >= 0 && index < dateien.size()) {
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
    
    public void vielFachesVonFuenf()
    {
        int index = 5;
        while(index < 95)
        {
            System.out.println(index);
            index += 5;
        }
    }
    
    public void summieren(int startZahl, int stopZahl)
    {
        int summe = 0;
        int zahl = 0;
        int letzteZahl = 0;
        if (startZahl < stopZahl)
        {
            zahl = startZahl;
            letzteZahl = stopZahl;
        }
        else
        {
            zahl = stopZahl;
            letzteZahl = startZahl;
        }
        while(zahl <= letzteZahl)
        {
            summe += zahl;
            zahl++;
        }
        System.out.println(summe);
        
    }
    
    public boolean isPrim(int zahl)
    {
        int index = 2;
        while(index < zahl)
        {
            if(zahl % index == 0 )
            {
                return false;
            }
            index++;
        }
        return true;
    }
    
}
