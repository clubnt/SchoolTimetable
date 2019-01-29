package sample;

import javafx.fxml.FXML;
import model.Lesson;
import javafx.scene.control.*;
import model.MainModel;
import model.*;

public class ControllerCreateList {

    private MainModel _model;


@FXML
    Label    lzb;
@FXML
    TextField textfield;
@FXML
void Create()
{

   String j = textfield.getText();
   if(Controller.createListVisualName.equals("Уроки"))
   {
       _model._lessonsList.add(new Lesson(_model._lessonsList.size() + 1,textfield.getText()));
       _model.save();
   }
    if(Controller.createListVisualName.equals("Класс"))
    {
        _model._classesList.add(new SchoolClass(_model._classesList.size() + 1,textfield.getText()));
        _model.save();
    }
    if(Controller.createListVisualName.equals("Учителя"))
    {
        Teacher teacher = new Teacher(_model._lessonsList.size() +1,textfield.getText());
        teacher.addLesson(_model._lessonsList.get(Integer.valueOf(roomtext.getText())));
        _model._teachersList.add(teacher);

        _model.save();
    }
    if(Controller.createListVisualName.equals("Помещения"))
    {
        _model._roomsList.add(new Room(_model._roomsList.size() + 1 ,textfield.getText(),Integer.valueOf(roomtext.getText())));
        _model.save();
    }
}

public void setModel(MainModel model)
{
    _model = model;
}
@FXML
TextField roomtext;
@FXML
void initialize()
{
  lzb.setText(Controller.createListVisualName);
    if(Controller.createListVisualName.equals("Помещения"))
    {
        roomtext.setVisible(true);
        roomtext.setPromptText("Number");

    }
    else if(Controller.createListVisualName.equals("Учителя"))
    {
        roomtext.setPromptText("Lesson");
        roomtext.setVisible(true);
    }
    else {roomtext.setVisible(false);}
}
}
