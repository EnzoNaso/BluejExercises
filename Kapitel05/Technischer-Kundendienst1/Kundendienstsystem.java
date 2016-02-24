/**
 * Diese Klasse implementiert ein System für einen technischen
 * Kundendienst. Das System kommuniziert mit dem Benutzer über 
 * die Konsole.
 * 
 * Diese Klasse benutzt ein Exemplar der Klasse Eingabeleser, 
 * um Eingaben vom Benutzer zu lesen, und ein Exemplar der
 * Klasse Beantworter, um Antworten zu generieren.
 * In einer Schleife werden Eingaben eingelesen und Antworten
 * auf diese Eingaben generiert, bis der Benutzer das System
 * verlässt.
 * 
 * @author     Michael Kölling und David J. Barnes
 * @version    0.1 (31.07.2011)
 */
public class Kundendienstsystem
{
    private Eingabeleser leser;
    private Beantworter beantworter;
    
    /**
     * Erzeuge ein Kundendienstsystem.
     */
    public Kundendienstsystem()
    {
        leser = new Eingabeleser();
        beantworter = new Beantworter();
    }

    /**
     * Starte das System für den technischen Kundendienst.
     * Es wird ein Begrüßungstext ausgegeben und anschließend
     * ein Dialog mit dem Benutzer geführt, bis der Benutzer
     * den Dialog beendet.
     */
    public void starten()
    {
        boolean fertig = false;

        willkommenstextAusgeben();

        while(!fertig) {
            String eingabe = leser.gibEingabe();

            if(eingabe.startsWith("ade")) {
                fertig = true;
            }
            else {
                String antwort = beantworter.generiereAntwort();
                System.out.println(antwort);
            }
        }
        abschiedstextAusgeben();
    }

    /**
     * Gib einen Willkommenstext auf der Konsole aus.
     */
    private void willkommenstextAusgeben()
    {
        System.out.println("Willkommen beim technischen Kundendienst" +
                           " der Firma SeltsamSoft.");
        System.out.println();
        System.out.println("Bitte schildern Sie uns Ihr Problem.");
        System.out.println("Wir werden Sie bestmöglich unterstützen.");
        System.out.println("Bitte tippen Sie 'ade', um unser System zu" +
                           " verlassen.");
    }

    /**
     * Gib einen Abschiedstext auf der Konsole aus.
     */
    private void abschiedstextAusgeben()
    {
        System.out.println("Vielen Dank für das Gespräch. Ade...");
    }
}
