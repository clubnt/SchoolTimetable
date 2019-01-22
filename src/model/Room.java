package model;

public class Room
{
    private int _id;
    private String _name;
    private int _number;


    public Room(int id, String name, int number)
    {
        _id = id;
        _name = name;
        _number = number;
    }


    public int getId()
    {
        return _id;
    }

    public String getName()
    {
        return _name;
    }

    public int getNumber()
    {
        return _number;
    }
}
