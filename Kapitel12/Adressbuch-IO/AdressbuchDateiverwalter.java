import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Ein AdressbuchDateiverwalter bietet eine Reihe von
 * Datei-Operationen für ein Adressbuch an.
 * Diese Methoden demonstrieren einige grundlegende
 * Eigenschaften des Pakets java.io.
 * 
 * @author David J. Barnes und Michael Kölling.
 * @version 31.07.2011
 */
public class AdressbuchDateiverwalter
{
    // Das Adressbuch, mit dem gearbeitet wird.
    private Adressbuch buch;
    // Der Name der Datei, in der die Suchergebnisse gespeichert
    // werden.
    private static final String DATEINAME = "Ergebnisse.txt";

    /**
     * Konstruktor für Objekte der Klasse AdressbuchDateiverwalter.
     * @param buch das Adressbuch, das benutzt werden soll.
     */
    public AdressbuchDateiverwalter(Adressbuch buch)
    {
        this.buch = buch;
    }
    
    /**
     * Speichere die Ergebnisse einer Suche im Adressbuch
     * in der Datei 'Ergebnisse.txt' im Projektordner.
     * @param praefix Der Schlüssel-Präfix, mit dem gesucht
     *                werden soll.
     */
    public void speichereSuchergebnisse(String praefix) throws IOException
    {
        File ergebnisdatei = erzeugeAbsolutenDateinamen(DATEINAME);
        Kontakt[] ergebnisse = buch.suche(praefix);
        FileWriter writer = new FileWriter(ergebnisdatei);
        for(Kontakt kontakt : ergebnisse) {
            writer.write(kontakt.toString());
            writer.write('\n');
            writer.write('\n');
        }
        writer.close();
    }
    
    /**
     * Zeige die Ergebnisse des letzten Aufrufs von
     * speichereSuchergebnisse. Da die Ausgabe auf der
     * Konsole erfolgt, werden alle Probleme von dieser
     * Methode unmittelbar gemeldet.
     */
    public void zeigeSuchergebnisse()
    {
        BufferedReader reader = null;
        try {
            File ergebnisdatei = erzeugeAbsolutenDateinamen(DATEINAME);
            reader = new BufferedReader(
                        new FileReader(ergebnisdatei));
            System.out.println("Ergebnisse ...");
            String zeile;
            zeile = reader.readLine();
            while(zeile != null) {
                System.out.println(zeile);
                zeile = reader.readLine();
            }
            System.out.println();
        }
        catch(FileNotFoundException e) {
            System.out.println("Datei nicht gefunden: " +
                               DATEINAME);
        }
        catch(IOException e) {
            System.out.println("Fehler beim Lesen der Datei: " +
                               DATEINAME);
        }
        finally {
            if(reader != null) {
                // Fangen jeder Exception, aber nicht viel zu tun
                try {
                    reader.close();
                }
                catch(IOException e) {
                    System.out.println("Fehler beim Schließen: " +
                                       DATEINAME);
                }
            }
        }
    }

    /**
     * Füge weitere Kontakte aus einer Textdatei dem Adressbuch
     * hinzu. Die Datei sollte ein Element pro Zeile enthalten,
     * plus eine Leerzeile zwischen den Kontakten:
     *     Name \n Telefon \n Adresse \n \n
     * Eine Zeile kann leer sein, wenn die entsprechende
     * Information fehlt.
     * @param dateiname der Name der Textdatei mit den Daten.
     * @throws IOException Bei einem Fehler beim Einlesen.
     */
    public void neueKontakteAusDatei(String dateiname) throws IOException
    {
        // Sicherstellen, dass die Datei auffindbar ist.
        URL resource = getClass().getResource(dateiname);
        if(resource == null) {
            throw new FileNotFoundException(dateiname);
        }
        dateiname = resource.getFile();
        BufferedReader reader = new BufferedReader(
                                   new FileReader(dateiname));
        String name;
        name = reader.readLine();
        while(name != null) {
            String telefon = reader.readLine();
            String adresse = reader.readLine();
            // Überlesen der Leerzeile.
            reader.readLine();
            buch.neuerKontakt(new Kontakt(name, telefon, adresse));
            name = reader.readLine();
        }
        reader.close();
    }
    
    /**
     * Lies die Binärversion eines Adressbuchs aus der gegebenen
     * Datei. Wenn der Dateiname kein absoluter Pfad ist, dann
     * wird der Name als relativ zum Projektordner angesehen.
     * @param dateiname der Name der Datei, aus der zu lesen ist.
     * @return das Adressbuch-Objekt.
     * @throws IOException Wenn das Einlesen fehlschlägt.
     */
    public Adressbuch liesAusDatei(String dateiname)
        throws IOException, ClassNotFoundException
    {
        // Sicherstellen, dass die Datei auffindbar ist.
        URL resource = getClass().getResource(dateiname);
        if(resource == null) {
            throw new FileNotFoundException(dateiname);
        }
        try {
            File quelle = new File(resource.toURI());
            ObjectInputStream is = new ObjectInputStream(
                                    new FileInputStream(quelle));
            Adressbuch gespeichertesBuch = (Adressbuch) is.readObject();
            is.close();
            return gespeichertesBuch;
        }
        catch(URISyntaxException e) {
            throw new IOException("Kein gültiger Dateiname erstellbar für " +
                                  dateiname);
        }        
    }
    
    /**
     * Speichere eine Binärversion des Adressbuchs in
     * die Datei mit dem gegebenen Namen. Wenn der Dateiname
     * nicht absolut ist, wird er als relativ zum aktuellen
     * Projektordner angenommen. 
     * @param dateiname der Name der Datei, in der gespeichert
     *                  werden soll.
     * @throws IOException falls das Speichern aus irgendeinem
     *                     Grund fehlschlägt.
     */
    public void speichereInDatei(String dateiname) throws IOException
    {
        File zieldatei = erzeugeAbsolutenDateinamen(dateiname);
        ObjectOutputStream os = new ObjectOutputStream(
                                    new FileOutputStream(zieldatei));
        os.writeObject(buch);
        os.close();
    }
    
    /**
     * Erzeuge aus dem gegebenen Dateinamen einen absoluten
     * Dateinamen. Wenn der Dateiname bereits absolut ist,
     * wird er unverändert gelassen, ansonsten wird der Name
     * relativ zum aktuellen Projektordner erzeugt.
     * @throws IOException wenn kein gültiger Dateiname erstellbar ist.
     */
    private File erzeugeAbsolutenDateinamen(String dateiname) throws IOException
    {
        try {
            File datei = new File(dateiname);
            if(!datei.isAbsolute()) {
                datei = new File(gibProjektordner(), dateiname);
            }
            return datei;
        }
        catch(URISyntaxException e) {
            throw new IOException("Kein gültiger Dateiname erstellbar für " +
                                  dateiname);
        }        
    }
    
    /**
     * Versuche den Namen des aktuellen Projektordners zu
     * ermitteln. Dazu wird der Pfad der .class-Datei dieser
     * Klasse ermittelt und daraus der Name des Ordners
     * extrahiert, der diese Datei enthält.
     * @throws URISyntaxException Wenn die URL nicht korrekt formatiert ist.
     * @return den aktuellen Projektordner.
     */
    private File gibProjektordner() throws URISyntaxException
    {
         String meineClassDatei = getClass().getName() + ".class";
         URL url = getClass().getResource(meineClassDatei);
         return new File(url.toURI()).getParentFile();
    }
}
