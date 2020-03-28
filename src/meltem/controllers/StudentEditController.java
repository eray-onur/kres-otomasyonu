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
import meltem.services.SceneBuilder;
import meltem.services.logging.Logger;
import meltem.view_models.StudentViewModel;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentEditController implements Initializable {
    Student student = new StudentViewModel(1,
            "Ali",
            "Oncul",
            "23/03/2020",
            "07/04/2020",
            "0543 555 4433",
            "Ahmet",
            "Oncul",
            "aoncul76@hotmail.com"
    ).student;
    @FXML
    public Text txtStudentId;
    @FXML
    public TextField txtStudentName;
    @FXML
    public TextField txtStudentLastName;
    @FXML
    public TextField txtOryantasyonBas;
    @FXML
    public TextField txtOryantasyonBit;
    @FXML
    public TextField txtParentNumber;
    @FXML
    public TextField txtParentName;
    @FXML
    public TextField txtParentLastName;
    @FXML
    public TextField txtParentEmail;
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
            txtParentNumber.setText(student.parentNumber);
            txtParentName.setText(student.parentName);
            txtParentLastName.setText(student.parentLastName);
            txtParentEmail.setText(student.parentEmail);
        }
    }
    @FXML
    public void goBack() throws IOException {
        SceneBuilder.Instance.BuildScene("student_list");
    }

    public void update(ActionEvent actionEvent) throws IOException {
        SceneBuilder.Instance.BuildScene("student_edit", new RouteData(1, "student"));
    }

    public void delete(ActionEvent actionEvent) throws IOException {
        SceneBuilder.Instance.BuildScene("student_list");
    }
}
