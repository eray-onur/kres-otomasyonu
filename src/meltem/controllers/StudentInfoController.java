package meltem.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import meltem.models.RouteData;
import meltem.models.Student;
import meltem.services.SceneBuilder;
import meltem.services.logging.Logger;
import meltem.view_models.StudentViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentInfoController implements Initializable {
    @FXML
    public Text txtStudentId;
    @FXML
    public Text txtStudentName;
    @FXML
    public Text txtStudentLastName;
    @FXML
    public Text txtOrientationStart;
    @FXML
    public Text txtOrientationEnd;
    @FXML
    public Text txtParentNumber;
    @FXML
    public Text txtParentName;
    @FXML
    public Text txtParentLastName;
    @FXML
    public Text txtParentEmail;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Student student = new StudentViewModel(
                1,
                "Ali",
                "Oncul",
                "23/03/2020",
                "07/04/2020",
                "0543 555 4433",
                "Ahmet",
                "Oncul",
                "aoncul76@hotmail.com").student;
        txtStudentId.setText(String.valueOf(student.studentId));
        txtStudentName.setText(student.studentName);
        txtStudentLastName.setText(student.studentLastName);
        txtOrientationStart.setText(student.orientationStart);
        txtOrientationEnd.setText(student.orientationEnd);
        txtParentName.setText(student.parentName);
        txtParentLastName.setText(student.parentLastName);
        txtParentNumber.setText(student.parentNumber);
        txtParentEmail.setText(student.parentEmail);

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
