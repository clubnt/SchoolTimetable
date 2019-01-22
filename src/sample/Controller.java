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
    MainModel model = new MainModel();


    public Controller()
    {

    }
    @FXML
    public void updater()
    {
        model.parse();
        model.save();
        items_lessons.remove(0,items_lessons.size()-1);

        for (int i = 0; i < model._lessonsList.size(); i++) {

            items_lessons.add(model._lessonsList.get(i).getName());
        }
        for (int i = 0; i < items_lessons.size(); i++) {
            System.out.println(items_lessons.get(i));
        }

        System.out.println();
        lessonsList.setItems(items_lessons);

    }
public void initialize() {

    }

    public static String createListVisualName;
    @FXML
    ListView lessonsList;
    @FXML
    ListView teacherlist;
    @FXML
    ListView lessonslistlist;
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
        void newLessonslist()
    {
        Controller.createListVisualName = "Расписания";
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
