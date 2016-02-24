import java.awt.Image;
    
/**
 * Ein Gegenstand, der eine grafische Darstellung von
 * sich selbst liefern kann.
 * 
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 */

public interface GrafischerGegenstand extends Gegenstand
{
    public Image gibGrafik();
}
