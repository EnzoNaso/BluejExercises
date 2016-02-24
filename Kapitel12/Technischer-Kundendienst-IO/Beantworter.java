import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Die Klasse Beantworter beschreibt Exemplare, die 
 * automatische Antworten in Abhängigkeit einer Eingabe
 * generieren. Die Eingabe wird einem Beantworter als 
 * eine Menge von Wörtern übergeben. Abhängig von diesen
 * Wörtern wird ein String als Antwort generiert.
 * 
 * Intern benutzt diese Klasse eine HashMap, um bestimmte
 * Stichwörter auf Antworten abzubilden, sowie eine Liste
 * von Standardantworten. Wenn eines der Wörter der Eingabe
 * als Stichwort in der HashMap vorkommt, wird die zugeordnete
 * Antwort zurückgegeben. Wenn keines der Eingabewörter erkannt
 * wird, wird aus den Standardantworten zufällig eine ausgewählt.
 * 
 * @author     David J. Barnes und Michael Kölling  
 * @version    31.07.2011
 */
public class Beantworter
{
    // Abbildung von Stichwörtern auf Antworten.
    private HashMap<String, String> antwortMap;

    // Liste von Standardantworten, falls kein Wort erkannt wird.
    private ArrayList<String> standardantworten;

    // Der Name der Datei mit den Standardantworten.
    private static final String DATEI_MIT_STANDARDANTWORTEN = "standardantworten.txt";

    private Random zufallsgenerator;

    /**
     * Erzeuge einen Beantworter.
     */
    public Beantworter()
    {
        antwortMap = new HashMap<String, String>();
        standardantworten = new ArrayList<String>();
        antwortMapBefuellen();
        standardantwortlisteFuellen();
        zufallsgenerator = new Random();
    }

    /**
     * Generiere eine Antwort auf eine gegebene Menge von Eingabewörtern.
     * 
     * @param woerter Eine Menge von Wörtern, die der Benutzer eingegeben hat
     * @return Eine Zeichenkette, die als Antwort geliefert werden soll
     */
    public String generiereAntwort(HashSet<String> woerter)
    {
        Iterator<String> it = woerter.iterator();
        while(it.hasNext()) {
            String wort = it.next();
            String antwort = antwortMap.get(wort);
            if(antwort != null) {
                return antwort;
            }
        }
        // Wenn wir hierher gelangen, wurde keines der Eingabewörter erkannt.
        // In diesem Fall wählen wir eine unserer Standardantworten (die
        // wir geben, wenn uns nichts Besseres mehr einfällt).
        
        return standardantwortAuswählen();
    }

    /**
     * Trage alle bekannten Stichwörter mit ihren verknüpften
     * Antworten in die Map 'antwortMap' ein.
     */
    private void antwortMapBefuellen()
    {
        antwortMap.put("absturz", 
                       "Tja, auf unserem System kommt es nie zu einem Absturz. Das muss \n" +
                       "an Ihrem System liegen. Welche Konfiguration haben Sie?");
        antwortMap.put("abstürzt", 
                       "Tja, auf unserem System kommt es nie zu einem Absturz. Das muss \n" +
                       "an Ihrem System liegen. Welche Konfiguration haben Sie?");
        antwortMap.put("stürzt", 
                       "Tja, auf unserem System kommt es nie zu einem Absturz. Das muss \n" +
                       "an Ihrem System liegen. Welche Konfiguration haben Sie?");
        antwortMap.put("langsam", 
                       "Ich vermute, dass das mit Ihrer Hardware zu tun hat. Ein Upgrade\n" +
                       "für Ihren Prozessor sollte diese Probleme lösen. Haben Sie ein\n" +
                       "Problem mit unserer Software?");
        antwortMap.put("performance", 
                       "Bei all unseren Tests war die Performance angemessen. Haben Sie\n" +
                       "andere Prozesse, die im Hintergrund laufen?");
        antwortMap.put("fehler", 
                       "Wissen Sie, jede Software hat Fehler. Aber unsere Entwickler arbeiten\n" +
                       "sehr hart daran, diese Fehler zu beheben. Können Sie das Problem ein\n" +
                       "wenig genauer beschreiben?");
        antwortMap.put("fehlerhaft", 
                       "Wissen Sie, jede Software hat Fehler. Aber unsere Entwickler arbeiten\n" +
                       "sehr hart daran, diese Fehler zu beheben. Können Sie das Problem ein\n" +                        "wenig genauer beschreiben?");
        antwortMap.put("windows", 
                       "Das ist ein bekanntes Problem, das im Betriebssystem von Microsoft begründet\n" +
                       "ist. Bitte leiten Sie es an Microsoft weiter. Da können wir leider nichts \n" +
                       "dran machen.");
        antwortMap.put("macintosh", 
                       "Das ist ein bekanntes Problem, das im Betriebssystem des Mac begründet\n" +
                       "ist. Bitte leiten Sie es an Apple weiter. Da können wir leider nichts \n" +
                       "dran machen.");
        antwortMap.put("teuer", 
                       "Unsere Preise sind absolute Marktpreise. Haben Sie sich mal umgesehen\n" +
                       "und wirklich unser Leistungsspektrum verglichen?");
        antwortMap.put("installation", 
                       "Die installation ist wirklich sehr einfach. Wir haben haufenweise\n" +
                       "Wizards, die die Arbeit für sie erledigen. Haben Sie die Installations-\n" +
                       "anleitung gelesen?");
        antwortMap.put("speicher", 
                       "Wenn sie die Systemanforderungen gründlich lesen, werden Sie feststellen,\n" +
                       "dass die Speicheranforderung 1,5 Gigabyte beträgt. Sie sollten Ihren\n" +
                       "Hauptspeicher unbedingt aufrüsten. Können wir sonst noch etwas für Sie tun?");
        antwortMap.put("linux", 
                       "Wir nehmen Linux sehr ernst. Aber es gibt da einige Probleme.\n" +
                       "Die meisten hängen mit der inkompatiblen glibc-Version zusammen. Können \n" +
                       "Sie das Problem etwas präziser beschreiben?");
        antwortMap.put("bluej", 
                       "Ahhh, BlueJ, ja. Wir haben schon vor längerer Zeit versucht, diese Leute \n" +
                       "aufzukaufen, aber sie wollen nicht verkaufen... Sturköpfe sind das. \n" +
                       "Ich fürchte, da können wir nichts dran ändern.");
    }

    /**
     * Generiere eine Liste von Standardantworten, aus denen wir eine
     * auswählen können, wenn wir keine bessere Antwort wissen.
     */
    private void standardantwortlisteFuellen()
    {        
        try {            
            BufferedReader reader = new BufferedReader(
                     new FileReader(DATEI_MIT_STANDARDANTWORTEN));
            String antwort = reader.readLine();
            while(antwort != null) {
                standardantworten.add(antwort);
                antwort = reader.readLine();
            }
            reader.close();
       }
       catch(FileNotFoundException e) {
           System.err.println(DATEI_MIT_STANDARDANTWORTEN
                              + " kann nicht geöffnet werden");
       }
       catch(IOException e) {
          System.err.println("Beim Lesen von " +
                             DATEI_MIT_STANDARDANTWORTEN +  
                            " ist ein Fehler aufgetreten");
       }
       // Sicherstellen, dass mindestens eine Antwort gegeben wird.
       if(standardantworten.size() == 0) {
           standardantworten.add("Könnten Sie sich näher dazu äußern?");
       }
    }

    /**
     * Wähle zufällig eine der Standardantworten aus.
     * @return eine zufällig gewählte Standardantwort.
     */
    private String standardantwortAuswählen()
    {
        // Erzeuge eine Zufallszahl, die als Index in der Liste der
        // Standardantworten benutzt werden kann. Die Zahl wird im Bereich
        // von Null (inklusiv) bis zur Größe der Liste (exklusiv) liegen.
        int index = zufallsgenerator.nextInt(standardantworten.size());
        return standardantworten.get(index);
    }
}
