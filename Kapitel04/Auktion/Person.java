/**
 * Modellierung von Personen, die an einer
 * Auktion teilnehmen.
 * 
 * @author David J. Barnes und Michael Kölling.
 * @version 31.07.2011
 */
public class Person
{
    // der Name dieser Person
    private final String name;

    /**
     * Erzeuge eine neue Person mit dem angegebenen Namen.
     * @param name der Name der Person
     */
    public Person(String name)
    {
        this.name = name;
    }

    /**
     * @return den Namen der Person.
     */
    public String gibName()
    {
        return name;
    }
}
