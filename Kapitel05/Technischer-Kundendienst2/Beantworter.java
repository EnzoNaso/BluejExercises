import java.util.ArrayList;
import java.util.Random;

/**
 * Die Klasse Beantworter beschreibt Exemplare, die 
 * automatische Antworten generieren.
 * 
 * Dies ist die zweite Version dieser Klasse. In dieser Version
 * generieren wir Antworten, indem wir zufällig eine Antwortphrase
 * aus einer Liste auswählen.
 * 
 * @author     Michael Kölling und David J. Barnes
 * @version    0.2 (31.07.2011)
 */
public class Beantworter
{
    private Random zufallsgenerator;
    private ArrayList<String> antworten;

    /**
     * Erzeuge einen Beantworter
     */
    public Beantworter()
    {
        zufallsgenerator = new Random();
        antworten = new ArrayList<String>();
        antwortlisteFuellen();
    }

    /**
     * Generiere eine Antwort.
     * 
     * @return einen String, der die Antwort enthält.
     */
    public String generiereAntwort()
    {
        // Erzeuge eine Zufallszahl, die als Index in der Liste der
        // Antworten benutzt werden kann. Die Zahl wird im Bereich von
        // null (inklusiv) bis zur Größe der Liste (exklusiv) liegen.
        int index = zufallsgenerator.nextInt(antworten.size());
        return antworten.get(index);
    }

    /**
     * Generiere eine Liste von Standardantworten, aus denen wir eine
     * auswählen können, wenn wir keine bessere Antwort wissen.
     */
    private void antwortlisteFuellen()
    {
        antworten.add("Das klingt seltsam. Können Sie das Problem" +
                      " ausführlicher beschreiben?");
        antworten.add("Bisher hat sich noch kein Kunde darüber\n" +
                      "beschwert. Welche Systemkonfiguration haben Sie?");
        antworten.add("Das klingt interessant. Erzählen Sie mehr...");
        antworten.add("Da brauche ich etwas ausführlichere Angaben.");
        antworten.add("Haben Sie geprüft, ob Sie einen Konflikt mit" +
                      " einer DLL haben?");
        antworten.add("Das steht im Handbuch. Haben Sie das Handbuch" +
                      " gelesen?");
        antworten.add("Das klingt alles etwas Wischi-Waschi. Haben Sie\n" +
                      "einen Experten in der Nähe, der das etwas\n" +
                      "präziser beschreiben kann?");
        antworten.add("Das ist kein Fehler, das ist eine" +
                      " Systemeigenschaft!");
        antworten.add("Könnten Sie es anders erklären?");
    }
}
