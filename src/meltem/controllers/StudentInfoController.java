package meltem.controllers;

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
    public Text txtStudentName;
    @FXML
    public Text txtStudentLastName;
    @FXML
    public Text txtOrientationStart;
    @FXML
    public Text txtOrientationEnd;
    @FXML
    public Text txtParentNumber;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Student student = new StudentViewModel(1, "Ali", "Ozcan" , "27/03/2020", "28/03/2020", "0 555 333 2211")
                .student;
        txtStudentName.setText(student.studentName);
        txtStudentLastName.setText(student.studentLastName);
        txtOrientationStart.setText(student.orientationStart);
        txtOrientationEnd.setText(student.orientationEnd);
        txtParentNumber.setText("0 555 333 2211");

    }

    public void goBack() throws IOException {
        SceneBuilder.Instance.BuildScene("student_list");
    }
    public void goUpdate() throws IOException {
        SceneBuilder.Instance.BuildScene("student_edit", new RouteData(1, "student"));
    }
    public void goDelete() throws IOException {
        Logger.LogDebug("DELETE!");
    }
}
