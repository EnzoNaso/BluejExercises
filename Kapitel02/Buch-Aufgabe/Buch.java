/**
 * Eine Klasse, deren Exemplare Informationen über ein Buch halten.
 * Dies könnte Teil einer größeren Anwendung sein, einer
 * Bibliothekssoftware beispielsweise.
 *
 * @author (Ihren Namen hier eintragen.)
 * @version (das heutige Datum eintragen.)
 */
class Buch
{
    // Exemplarvariablen
    private String autor;
    private String titel;
    private int seiten;
    private String refNummer;
    private int ausgeliehen;
    private boolean kursText; 
    
    /**
     * Setze den Autor und den Titel, wenn ein Exemplar erzeugt wird.
     */
    public Buch(String buchautor, String buchtitel, int seiten)
    {
        this.autor = buchautor;
        this.titel = buchtitel;
        this.seiten = seiten;
        this.refNummer = "";
        this.kursText = true;
    }

    // weitere Methoden hier einfügen ...
    public String gibAutor()
    {
        return this.autor;
    }

    public String gibTitel()
    {
        return this.titel;
    }
    
    public int gibSeiten()
    {
        return this.seiten;
    }

    public String gibRefNummer()
    {
        return this.refNummer;
    }

    public int gibAusgeliehen()
    {
        return this.ausgeliehen;
    }
    
    public boolean istKursText()
    {
        return this.kursText;
    }
    
    public void setzeRefNummer(String refNummer)
    {
        if(refNummer.length() < 3)
        {
           System.out.println("Die refNummer muss mindestens 3 Zeichen lang sein.");
           return;
        }
        this.refNummer = refNummer;
    }
    
    public void autorAusgeben()
    {
        System.out.println(gibAutor());
    }
    
    public void titelAusgeben()
    {
        System.out.println(gibTitel());
    }
    
    public void ausleihen()
    {
        this.ausgeliehen++;
    }
    
    public void detailsAusgeben()
    {
        String ausgabe = "Titel: " + gibTitel() + ", Autor: " + gibAutor() + ", Seiten: " + gibSeiten() + 
                            ", RefNummer: ";
        
        if(gibRefNummer().length() > 0)
        {
            ausgabe += gibRefNummer();
        }
        else
        {
            ausgabe += "ZZZ";
        }
                        
        System.out.println(ausgabe + ", Ausgeliehen: " + gibAusgeliehen());
    }
    
    
}
