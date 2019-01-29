package sample;
import javafx.beans.InvalidationListener;
import javafx.collections.ArrayChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Lesson;
import model.MainModel;

import java.io.IOException;

public class Controller
{
    ObservableList<String> items_lessons = FXCollections.observableArrayList();
    ObservableList<String> items_class= FXCollections.observableArrayList();
    ObservableList<String> items_rooms= FXCollections.observableArrayList();
    MainModel model = new MainModel();


    public Controller()
    {

    }
    @FXML
    public void updater()
    {
        model.clear();
        model.parse();



        {
                items_class.clear();
                for (int i = 0; i < model._classesList.size(); i++) {

                    items_class.add(model._classesList.get(i).getName());
                }


                System.out.println();
                classeslist.setItems(items_class);

        }

        {
            items_rooms.clear();
            for (int i = 0; i < model._roomsList.size(); i++) {

                items_rooms.add(model._roomsList.get(i).getName() + " " + model._roomsList.get(i).getNumber());

            }


            System.out.println();
            roomlist.setItems(items_rooms);

        }

        {
            items_lessons.clear();
            for (int i = 0; i < model._lessonsList.size(); i++) {

                items_lessons.add(model._lessonsList.get(i).getName());
            }


            System.out.println();
            lessonsList.setItems(items_lessons);

        }
        model.save();
    }
public void initialize() {



    }
    public static String createListVisualName;
    @FXML
    ListView lessonsList;
    @FXML
    ListView teacherlist;
    @FXML
    ListView classeslist;
    @FXML
    ListView roomlist;

    @FXML
    void newLesson()
    {

        Controller.createListVisualName = "Уроки";
        this.startList();
    }
    @FXML
    void newTeacher()
    {
        Controller.createListVisualName = "Учителя";
        this.startList();
    }
    @FXML
        void newRoom()
    {
        Controller.createListVisualName = "Помещения";
        this.startList();
    }
    @FXML
        void newClass()
    {
        Controller.createListVisualName = "Класс";
        this.startList();

    }
   public void startList()
    {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("createList.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ControllerCreateList controllerCreateList = loader.getController();
        controllerCreateList.setModel(model);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();

    }

}
