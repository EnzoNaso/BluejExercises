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
    private MusicFilePlayer player;
    // Die aktuell abgespielte Datei.
    private String dateiname;
   
    /**
     * Konstruktor für Objekte der Klasse MusicPlayer
     */
    public MusikPlayer()
    {
        player = null;
        dateiname = "";
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
            spieleAb(0);
        }
        catch(JavaLayerException e) {
            meldeProblem();
        }
    }
    
    /**
     * Stoppe das Abspielen der aktuellen Datei.
     */
    public void stop()
    {
        killPlayer();
    }
    
    /**
     * Halte die aktuelle Datei an.
     */
    public void pause()
    {
        if(player != null) {
            try {
                player.pause();
            }
            catch(JavaLayerException e) {
                meldeProblem();
                killPlayer();
            }
        }
    }
    
    /**
     * Nehme das Abspielen einer zuvor angehaltenen Datei wieder auf.
     */
    public void weiter()
    {
        if(player != null) {
            Thread playerThread = new Thread() {
                public void run()
                {
                    try {
                        player.resume();
                    }
                    catch(JavaLayerException e) {
                        meldeProblem();
                        killPlayer();
                    }
                }
            };
            playerThread.setPriority(Thread.MIN_PRIORITY);
            playerThread.start();
        }
    }
    
    /**
     * Gehe in der aktuellen Datei zu der angegebenen Position.
     * Der Track wird als Folge dieser Operation angehalten.
     * 
     * @param position Position in der Datei, zu der vorgerückt werden soll.
     */
    public void geheZu(int position)
    {
        if(player != null && position >= 0 && position < player.getLength()) {
            // Setze die Position des Players.
        }
            
    }
    
    /**
     * Liefere die Länge der aktuellen Musikdatei, sofern vorhanden.
     * Die Länge wird in 'frames' angegeben, nicht in Sekunden oder anderen Zeitangaben.
     * 
     * @return die Dateilänge in Frames.
     */
    public int gibLaenge()
    {
        if(player != null) {
            return player.getLength();
        }
        else {
            return 0;
        }
    }
    
    
    /**
     *Bereite den Player für das Abspielen der gegebenen Datei vor.
     * @param dateiname der Name der abzuspielenden Datei.
     */
    private void playerVorbereiten(String dateiname)
    {
        try {
            if(player != null) {
                killPlayer();
            }
            this.dateiname = dateiname;
            player = new MusicFilePlayer(dateiname);
        }
        catch(JavaLayerException e) {
            System.out.println("Problem setting up player");
            e.printStackTrace();
            meldeProblem();
            killPlayer();
        }
    }

    /**
     * Spiele ab der gegebenen Position.
     * @param start die Position, ab der abgespielt werden soll.
     *              Diese darf nicht außerhalb der Länge der aktuellen Datei liegen.
     */
    private void spieleAb(final int start) throws JavaLayerException
    {
        Thread playerThread = new Thread() {
            public void run()
            {
                try {
                    player.playFrom(start);
                }
                catch(JavaLayerException e) {
                    meldeProblem();
                    killPlayer();
                }
            }
        };
        playerThread.setPriority(Thread.MIN_PRIORITY);
        playerThread.start();
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
                dateiname = "";
            }
        }
    }
    
    /**
     * Berichte über ein Problem beim Abspielen der aktuellen Datei.
     */
    private void meldeProblem()
    {
        System.out.println("Es gab ein Problem beim Abspielen von: " + dateiname);
    }

}
