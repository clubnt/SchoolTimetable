package model;

import java.io.*;
import java.util.ArrayList;

import com.google.gson.*;


public class MainModel
{

    private final String _FILE_PATH = "school_timetable_data.json";

    public ArrayList<SchoolClass> _classesList = new ArrayList<>();
    public ArrayList<Room> _roomsList = new ArrayList<>();

    public ArrayList<Lesson> _lessonsList = new ArrayList<>();
    public ArrayList<Teacher> _teachersList = new ArrayList<>();


    public void save()
    {

        JsonObject result = new JsonObject();

        //Classes

        JsonArray classesListJson = new JsonArray();

        for (SchoolClass clas : _classesList)
        {
            JsonObject classJsonObject = new JsonObject();
            classJsonObject.addProperty("id", clas.getId());
            classJsonObject.addProperty("name", clas.getName());
            classesListJson.add(classJsonObject);
        }

        //Rooms

        JsonArray roomsListJson = new JsonArray();

        for (Room room : _roomsList)
        {
            JsonObject roomJsonObject = new JsonObject();
            roomJsonObject.addProperty("id", room.getId());
            roomJsonObject.addProperty("name", room.getName());
            roomJsonObject.addProperty("number", room.getNumber());
            roomsListJson.add(roomJsonObject);
        }

        //Lesson

        JsonArray lessonsListJson = new JsonArray();

        for (Lesson lesson : _lessonsList)
        {
            JsonObject lessonJsonObject = new JsonObject();
            lessonJsonObject.addProperty("id", lesson.getId());
            lessonJsonObject.addProperty("name", lesson.getName());

            lessonsListJson.add(lessonJsonObject);
        }

        //Teachers

        JsonArray teachersListJson = new JsonArray();
        JsonArray teacherLessonsListJson = new JsonArray();

        int teacherLessonId = 0;

        for (Teacher teacher : _teachersList)
        {
            JsonObject teacherJsonObject = new JsonObject();
            teacherJsonObject.addProperty("id", teacher.getId());
            teacherJsonObject.addProperty("name", teacher.getName());

            teachersListJson.add(teacherJsonObject);

            ArrayList<Lesson> lessons = teacher.getLessonsList();

            for (Lesson lesson : lessons)
            {
                JsonObject lessonJsonObject = new JsonObject();
                lessonJsonObject.addProperty("id", teacherLessonId);
                lessonJsonObject.addProperty("id_lessons", lesson.getId());
                lessonJsonObject.addProperty("id_teacher", teacher.getId());

                teacherLessonsListJson.add(lessonJsonObject);
            }
        }

        result.add("classes", classesListJson);
        result.add("rooms", roomsListJson);
        result.add("teachers", teachersListJson);
        result.add("lessons", lessonsListJson);
        result.add("teacher_lessons", teacherLessonsListJson);

        //Teacher lessons

        try (Writer writer = new FileWriter(_FILE_PATH))
        {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            gson.toJson(result, writer);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public void parse()
    {
        String fileText = loadFileText(_FILE_PATH);

        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(fileText).getAsJsonObject();

        //Classes

        JsonArray classesJsonArray = jsonObject.get("classes").getAsJsonArray();

        for (int i = 0; i < classesJsonArray.size(); i++)
        {
            JsonElement classJsonElement = classesJsonArray.get(i);
            int classId = classJsonElement.getAsJsonObject().get("id").getAsInt();
            String className = classJsonElement.getAsJsonObject().get("name").getAsString();

            _classesList.add(new SchoolClass(classId, className));
        }

        //Rooms

        JsonArray roomsJsonArray = jsonObject.get("rooms").getAsJsonArray();

        for (int i = 0; i < classesJsonArray.size(); i++)
        {
            JsonElement roomJsonElement = roomsJsonArray.get(i);
            int roomId = roomJsonElement.getAsJsonObject().get("id").getAsInt();
            String roomName = roomJsonElement.getAsJsonObject().get("name").getAsString();
            int roomNumber = roomJsonElement.getAsJsonObject().get("number").getAsInt();

            _roomsList.add(new Room(roomId, roomName, roomNumber));
        }

        //Teachers

        JsonArray teachersJsonArray = jsonObject.get("teachers").getAsJsonArray();

        for (int i = 0; i < teachersJsonArray.size(); i++)
        {
            JsonElement teacherJsonElement = teachersJsonArray.get(i);
            int teacherId = teacherJsonElement.getAsJsonObject().get("id").getAsInt();
            String teacherName = teacherJsonElement.getAsJsonObject().get("name").getAsString();

            _teachersList.add(new Teacher(teacherId, teacherName));
        }

        //Lesson

        JsonArray lessonsJsonArray = jsonObject.get("lessons").getAsJsonArray();

        for (int i = 0; i < lessonsJsonArray.size(); i++)
        {
            JsonElement lessonJsonElement = lessonsJsonArray.get(i);
            int lessonId = lessonJsonElement.getAsJsonObject().get("id").getAsInt();
            String lessonName = lessonJsonElement.getAsJsonObject().get("name").getAsString();

            _lessonsList.add(new Lesson(lessonId, lessonName));
        }

        //Teacher lessons

        JsonArray teacherLessonsJsonArray = jsonObject.get("teacher_lessons").getAsJsonArray();

        for (int i = 0; i < teacherLessonsJsonArray.size(); i++)
        {
            JsonElement teacherLessonJsonElement = teacherLessonsJsonArray.get(i);

            int teacherId = teacherLessonJsonElement.getAsJsonObject().get("id_teacher").getAsInt();
            int lessonId = teacherLessonJsonElement.getAsJsonObject().get("id_lessons").getAsInt();

            Teacher teacher = getTeacherById(teacherId);
            teacher.addLesson(getLessonById(lessonId));
        }

    }


    private Teacher getTeacherById(int id)
    {
        for (int i = 0; i < _teachersList.size(); i++)
        {
            Teacher teacher = _teachersList.get(i);

            if (teacher.getId() == id)
            {
                return  teacher;
            }
        }

        return null;
    }


    private Lesson getLessonById(int id)
    {
        for (int i = 0; i < _lessonsList.size(); i++)
        {
            Lesson lesson = _lessonsList.get(i);

            if (lesson.getId() == id)
            {
                return  lesson;
            }
        }

        return null;
    }


    private String loadFileText(String filePath)
    {
        String fileText = "";

        FileReader reader;

        try
        {
            reader = new FileReader(filePath);
            int ch;

            StringBuilder strBuilder = new StringBuilder();

            while ((ch = reader.read()) != -1)
            {
                strBuilder.append((char)ch);
            }

            fileText = strBuilder.toString();

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }

        return fileText;
    }

}