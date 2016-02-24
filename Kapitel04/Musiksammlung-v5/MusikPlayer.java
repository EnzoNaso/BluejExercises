import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.advanced.AdvancedPlayer;

/**
 * Stellt mithilfe der javazoom-Bibliothek die grundlegende Funktionalität zum
 * Abspielen von MP3-Dateien zur Verfügung.
 * siehe http://www.javazoom.net/
 * 
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */
public class MusikPlayer
{
    // Das aktuell verwendete Abspielgerät. Kann null sein.
    private AdvancedPlayer player;
    
    /**
     * Konstruktor für Objekte der Klasse MusicPlayer
     */
    public MusikPlayer()
    {
        player = null;
    }
    
    /**
     * Spiele einen Teil der gegebenen Datei ab.
     * Die Methode kehrt zurück, sobald sie mit Abspielen fertig ist. 
     * @param dateiname die abzuspielende Datei.
     */
    public void dateiAnspielen(String dateiname)
    {
        try {
            playerVorbereiten(dateiname);
            player.play(500);
        }
        catch(JavaLayerException e) {
            meldeProblem(dateiname);
        }
        finally {
            killPlayer();
        }
    }    
    
    /**
     * Spiele die gegebene Audiodatei ab.
     * Die Methode kehrt zurück, sobald der Abspielvorgang gestartet wurde.
     * @param dateiname die abzuspielende Datei.
     */
    public void starteAbspielen(final String dateiname)
    {
        try {
            playerVorbereiten(dateiname);
            Thread playerThread = new Thread() {
                public void run()
                {
                    try {
                        player.play(5000);
                    }
                    catch(JavaLayerException e) {
                        meldeProblem(dateiname);
                    }
                    finally {
                        killPlayer();
                    }
                }
            };
            playerThread.start();
        }
        catch (Exception ex) {
            meldeProblem(dateiname);
        }
    }
    
    public void stop()
    {
        killPlayer();
    }
    
    /**
     *Bereite den Player für das Abspielen der gegebenen Datei vor.
     * @param dateiname der Name der abzuspielenden Datei.
     */
    private void playerVorbereiten(String dateiname)
    {
        try {
            InputStream is = gibEingabestream(dateiname);
            player = new AdvancedPlayer(is, erzeugeAudiogeraet());
        }
        catch (IOException e) {
            meldeProblem(dateiname);
            killPlayer();
        }
        catch(JavaLayerException e) {
            meldeProblem(dateiname);
            killPlayer();
        }
    }

    /**
     * Liefere einen Eingabestream für die gegebene Datei.
     * @param dateiname die zu öffnende Datei.
     * @throws IOException wenn die Datei nicht geöffnet werden kann.
     * @return ein InputStream für die Datei.
     */
    private InputStream gibEingabestream(String dateiname)
        throws IOException
    {
        return new BufferedInputStream(
                    new FileInputStream(dateiname));
    }

    /**
     * Erzeuge ein Audiogerät.
     * @throws JavaLayerException wenn das Gerät nicht erzeugt werden kann.
     * @return ein Audiogerät.
     */
    private AudioDevice erzeugeAudiogeraet()
        throws JavaLayerException
    {
        return FactoryRegistry.systemRegistry().createAudioDevice();
    }

    /**
     * Beende den Player, sofern ein solcher existiert.
     */
    private void killPlayer()
    {
        synchronized(this) {
            if(player != null) {
                player.stop();
                player = null;
            }
        }
    }
    
    /**
     * Berichte über ein Problem beim Abspielen der gegebenen Datei.
     * @param dateiname die abgespielte Datei.
     */
    private void meldeProblem(String dateiname)
    {
        System.out.println("Es gab ein Problem beim Abspielen von: " + dateiname);
    }

}
