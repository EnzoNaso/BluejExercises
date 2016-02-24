
/**
 * Write a description of class Heizung here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Heizung
{
    // instance variables - replace the example below with your own
    private double temperatur;
    private double min;
    private double max;
    private double schrittweite;
    
    /**
     * Constructor for objects of class Heizung
     */
    public Heizung()
    {
        // initialise instance variables
        this.temperatur = 15.0;
        this.schrittweite = 5.0;
        this.max = 25.0;
        this.min = 5.0;
    }

    public double gibTemperatur()
    {
        return this.temperatur;
    }
    
    public void setzeSchrittweite(double schrittweite)
    {
        if (schrittweite > 0)
        {
            this.schrittweite = schrittweite;
        }
    }
    
    public void waermer()
    {
        if(this.temperatur + this.schrittweite > this.max)
        {
            this.temperatur = this.max;
            return;
        }
        this.temperatur += this.schrittweite;
    }
    
    public void kuehler()
    {
        if(this.temperatur - this.schrittweite < this.min)
        {
            this.temperatur = this.min;
            return;
        }
        this.temperatur -= this.schrittweite;
    }
    
}
