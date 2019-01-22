package model;

public class Lesson
{

    private int _id;
    private String _name;


    public Lesson(int id, String name)
    {
        _id = id;
        _name = name;
    }


    public int getId()
    {
        return _id;
    }

    public String getName()
    {
        return _name;
    }
}