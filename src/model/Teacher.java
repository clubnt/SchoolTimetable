package model;

import java.util.ArrayList;

public class Teacher
{

    private ArrayList<Lesson> _lessonsList = new ArrayList<Lesson>();
    private int _id;
    private String _name;

   public Teacher(int id, String name)
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

    public void addLesson(Lesson lesson)
    {
        _lessonsList.add(lesson);
    }

    public ArrayList<Lesson> getLessonsList()
    {
        return _lessonsList;
    }
}
