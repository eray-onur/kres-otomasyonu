package meltem.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import meltem.Main;
import meltem.models.Student;
import meltem.services.SceneBuilder;
import meltem.services.logging.Logger;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentEditController implements Initializable {
    Student student = new Student(
            1,
            "Ali Ekber",
            "Celayir",
            "21/03/2020",
            "05/04/2020"
    );
    @FXML
    public TextField txtStudentName;
    @FXML
    public TextField txtStudentLastName;
    @FXML
    public TextField txtOryantasyonBas;
    @FXML
    public TextField txtOryantasyonBit;
    @FXML
    public Button btnNew;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Main.userDataService.fetchById(SceneBuilder.routeData.id);
        if(SceneBuilder.routeData != null) {
            Logger.LogDebug(SceneBuilder.routeData.dataName);
            txtStudentName.setText(student.studentName);
            txtStudentLastName.setText(student.studentLastName);
            txtOryantasyonBas.setText(student.orientationStart);
            txtOryantasyonBit.setText(student.orientationEnd);
        }
    }
    @FXML
    public void goBack() throws IOException {
        SceneBuilder.Instance.BuildScene("student_list");
    }
}
