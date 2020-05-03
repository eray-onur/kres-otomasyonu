package meltem.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

public class StudentInfoController implements Initializable {
    Student student = new StudentViewModel(1,
            "Ali",
            "Oncul",
            "23/03/2020",
            "07/04/2020",
            "0543 555 4433",
            "Ahmet",
            "Oncul",
            "aoncul76@hotmail.com",
            680
    ).student;
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
                "aoncul76@hotmail.com", 680).student;
        if(txtStudentId != null) {
            txtStudentId.setText(String.valueOf(student.getStudentId()));
        }
        txtStudentName.setText(student.getStudentName());
        txtStudentLastName.setText(student.getStudentLastName());
        txtOrientationStart.setText(student.getOrientationStart().toString());
        txtOrientationEnd.setText(student.getOrientationEnd().toString());
        txtParentName.setText(student.getParentName());
        txtParentLastName.setText(student.getParentLastName());
        txtParentNumber.setText(student.getParentNumber());
        txtParentEmail.setText(student.getParentEmail());

    }

    @FXML
    public void goAssign() throws IOException {
        SceneBuilder.Instance.BuildScene("student_assign");
    }

    @FXML
    public void goBack() throws IOException {
        switch(Main.user.getUserAuth()) {
            case 1:
                SceneBuilder.Instance.BuildScene("classroom_student_list");
                break;
            case 2:
                SceneBuilder.Instance.BuildScene("classroom_student_list");
                break;
            case 3:
                SceneBuilder.Instance.BuildScene("branch_course_single");
                break;
        }
    }
    @FXML
    public void update(ActionEvent actionEvent) throws IOException {
        SceneBuilder.Instance.BuildScene("student_edit", new RouteData(1, "student"));
    }
    @FXML
    public void delete(ActionEvent actionEvent) throws IOException {
        SceneBuilder.Instance.BuildScene("student_list");
    }
}
