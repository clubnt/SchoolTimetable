package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.MainModel;
import model.Room;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 450, 400));
        primaryStage.show();
    }


    public static void main(String[] args)
    {
        MainModel mn = new MainModel();

        mn.parse();
        mn._roomsList.add(new Room(1,"Q",1));
        mn.save();

        launch(args);
    }
}
