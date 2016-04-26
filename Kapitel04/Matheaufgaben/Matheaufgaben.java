
/**
 * Write a description of class Matheaufgaben here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Matheaufgaben
{

    /**
     * Constructor for objects of class Matheaufgaben
     */
    public Matheaufgaben()
    {
     
    }

    public void vielfacheVonFuenf()
    {
        int zahl = 5;
        while(zahl < 100)
        {
            System.out.println(zahl);
            zahl += 5;
        }
    }
    
    public void summieren(int a, int b)
    {
        int zahl = 0;
        int start = 0;
        int end = 0;
        if(a < b)
        {
            start = a;
            end = b;
        }
        else
        {
            start = b;
            end = a;
        }
        while(start < end + 1)
        {
            zahl += start;
            start++;
        }
        System.out.println("a..b: " + zahl);
    }
    
    public boolean istPrim(int n)
    {
        boolean istPrimzahl = true;
        int position = 2;
        while(position < n && istPrimzahl)
        {
            if(n % position == 0)
            {
                istPrimzahl = false;
            }
            position++; 
        }
        return istPrimzahl;
    }
}
