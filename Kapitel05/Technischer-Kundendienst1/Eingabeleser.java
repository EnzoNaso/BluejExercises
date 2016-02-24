import java.util.Scanner;

/**
 * Ein Eingabeleser liest eingetippten Text von der Konsole.
 * Der vom Benutzer eingetippte Text wird zurückgeliefert.
 * 
 * @author     Michael Kölling und David J. Barnes
 * @version    0.1 (31.07.2011)
 */
public class Eingabeleser
{
    private Scanner scanner;

    /**
     * Erzeuge einen neuen Eingabeleser, der Text von der Konsole
     * einliest.
     */
    public Eingabeleser()
    {
        scanner = new Scanner(System.in);
    }

    /**
     * Lies eine Zeile von der Konsole und liefere sie als String.
     *
     * @return  Eine Zeichenkette, die vom Benutzer eingetippt wurde.
     */
    public String gibEingabe() 
    {
        System.out.print("> ");   // Eingabebereitschaft anzeigen
        String eingabezeile = scanner.nextLine();

        return eingabezeile;
    }

}
