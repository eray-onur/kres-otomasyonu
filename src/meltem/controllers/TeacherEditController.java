package meltem.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import meltem.Main;
import meltem.models.RouteData;
import meltem.models.Student;
import meltem.models.Teacher;
import meltem.services.SceneBuilder;
import meltem.services.logging.Logger;
import meltem.view_models.StudentViewModel;
import meltem.view_models.TeacherViewModel;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherEditController implements Initializable {
//    TeacherViewModel teacherVM = new TeacherViewModel(1,
//            "Sema",
//            "Yirun",
//            "s.yirun@abd.com",
//            (byte)0);
    @FXML
    public Text txtTeacherId;
    @FXML
    public TextField txtTeacherName;
    @FXML
    public TextField txtTeacherLastName;
    @FXML
    public TextField txtTeacherEmail;
    @FXML
    public TextField txtTeacherType;
    @FXML
    public Button btnNew;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Main.userDataService.fetchById(SceneBuilder.routeData.id);
        if(SceneBuilder.routeData != null) {
            Logger.LogDebug(SceneBuilder.routeData.dataName);
            //txtTeacherId.setText(String.valueOf(teacherVM.teacher.getTeacherId()));
            //txtTeacherName.setText(teacherVM.teacher.getTeacherName());
            //txtTeacherLastName.setText(teacherVM.teacher.getTeacherLastName());
            //txtTeacherEmail.setText(teacherVM.teacher.getTeacherEmail());
            //txtTeacherType.setText("Sınıf Öğretmeni");
        }
    }
    @FXML
    public void goAssign() throws IOException {
        SceneBuilder.Instance.BuildScene("teacher_assign");
    }
    @FXML
    public void goBack() throws IOException {
        SceneBuilder.Instance.BuildScene("teacher_list");
    }

    @FXML
    public void goBackToClassroomAdmin() throws IOException {
        SceneBuilder.Instance.BuildScene("attendance_classroom_admin");
    }

    public void update(ActionEvent actionEvent) throws IOException {
        SceneBuilder.Instance.BuildScene("teacher_edit", new RouteData(1, "student"));
    }
}
