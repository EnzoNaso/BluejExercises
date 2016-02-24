
/**
 * Diese Klasse repräsentiert Kommentare, die auf der Website eines Online-Shops zu einem Produkt
 * geschrieben wurden. Ein Kommentar besteht aus einem Text, einer Bewertung und dem Namen des Autors.
 * Andere Besucher können angeben, ob der Kommentar hilfreich für sie war (wir sprechen von 'zustimmen' bzw. 
 * 'ablehnen'). Das Verhältnis zwischen Zustimmungen und Ablehnungen wird zusammen mit den Kommentaren gespeichert.
 * Ein negatives Verhältnis bedeutet, dass es für den Kommentar mehr Ablehnungen als Zustimmungen gibt.
 * 
 * @autor Michael Kölling und David J. Barnes
 * @version 31.07.2011
 */
public class Kommentar
{
    private String autor;
    private String text;
    private int bewertung;
    private int stimmen;

    /**
     * Erzeuge einen Kommentar mit allen notwendigen Angaben. Das anfängliche Stimmenverhältnis ist 0.
     */
    public Kommentar(String autor, String text, int bewertung)
    {
        this.autor = autor;
        this.text = text;
        this.bewertung = bewertung;
        stimmen = 0;
    }

    /**
     * Zeige an, dass der Kommentar hilfreich ist ('zustimmen'). Wird verwendet, wenn ein Besucher nach dem
     * Lesen der "War dieser Kommentar hilfreich?"-Frage auf die'Ja'-Schaltfläche klickt .
     */
    public void zustimmen()
    {
        stimmen++;
    }

    /**
     * Zeige an, dass der Kommentar nicht hilfreich ist ('ablehnen'). Wird verwendet, wenn ein Besucher nach dem
     * Lesen der "War dieser Kommentar hilfreich?"-Frage auf die'Nein'-Schaltfläche klickt .
     */
    public void ablehnen()
    {
        stimmen--;
    }
    
    /**
     * Liefere den Autor des Kommentars.
     */
    public String gibAutor()
    {
        return autor;
    }
    
    /**
     * Liefere die Bewertung des Kommentars.
     */
    public int gibBewertung()
    {
        return bewertung;
    }
    
    /**
     * Liefere das Stimmenverhältnis (Verhöltnis von zustimmenden zu ablehnenden Stimmen).
     */
    public int gibStimmenAnzahl()
    {
        return stimmen;
    }
    
    /**
     * Liefere den vollständigen Text und alle Angaben zu dem Kommentar, einschließlich
     * des Kommentartextes, des Autors und der Bewertung.
     */
    public String gibAlleDetails()
    {
        String details = "Bewertung: " + "*****".substring(0,bewertung) + "    "
                         + "von: " + autor + "\n"
                         + "    " + text + "\n";
        // Hinweise: 'stimmen' wird hier nur zu Testzwecken mitgeliefert. In der endgültigen
        // Anwendung wird diese Angabe nicht enthalten sein. Stattdessen wird die Anzahl der Abstimmungen
        // dazu benutzt, Kommentare auszuwählen und auf dem Bildschirm anzuordnen.
        details += "(Als hilfreich eingestuft: " + stimmen + ")\n";
        return details;
    }
}
