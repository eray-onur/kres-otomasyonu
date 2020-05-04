package meltem.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import meltem.Main;
import meltem.models.Student;
import meltem.services.SceneBuilder;
import meltem.services.data_access.concrete.ClassroomAttendanceRepository;
import meltem.services.data_access.concrete.StudentRepository;
import meltem.services.logging.Logger;
import meltem.view_models.StudentViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentNewController implements Initializable {
    public static int StudentId = -1;
    public static int ClassroomId = -1;
    public static int BranchCourseId = -1;
    public static int route = 0;
    Student student = null;
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            if(StudentId != -1) {

                student = new StudentViewModel(StudentRepository.Instance.fetchById(StudentId)).student;

                if(txtStudentId != null) {
                    txtStudentId.setText(String.valueOf(StudentId));
                }

                txtStudentName.setText(student.getStudentName());
                txtStudentLastName.setText(student.getStudentLastName());
                txtOryantasyonBas.setText(student.getOrientationStart());
                txtOryantasyonBit.setText(student.getOrientationEnd());
                txtParentNumber.setText(student.getParentNumber());
                txtParentName.setText(student.getParentName());
                txtParentLastName.setText(student.getParentLastName());
                txtParentEmail.setText(student.getParentEmail());
                StudentId = -1;

            }

            if(ClassroomId != -1) {
                Logger.LogDebug(String.valueOf(ClassroomId));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void goBack() throws IOException {
        Logger.LogDebug(String.valueOf(route) + " is the route ID");
        switch(route) {
            case 1:
                SceneBuilder.Instance.BuildScene("classroom_info_admin");
                break;
            case 2:
                SceneBuilder.Instance.BuildScene("student_list");
                break;
            case 3:
                SceneBuilder.Instance.BuildScene("branch_courses");
                break;
        }

    }

    public void update(ActionEvent actionEvent)  {
        Student updatedStudent = new Student(
            student.getStudentId(),
            txtStudentName.getText(),
            txtStudentLastName.getText(),
            txtOryantasyonBas.getText(),
            txtOryantasyonBit.getText(),
            txtParentName.getText(),
            txtParentLastName.getText(),
            txtParentNumber.getText(),
            txtParentEmail.getText(),
            500
        );

        try {
            StudentRepository.Instance.UpdateById(updatedStudent, student.getStudentId());
            Logger.LogDebug(StudentRepository.Instance.fetchById(student.getStudentId()).getParentName());
            goBack();
        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }

    public void add(ActionEvent actionEvent)  {
        Student studentToAdd = new Student(
                99,
                txtStudentName.getText(),
                txtStudentLastName.getText(),
                txtOryantasyonBas.getText(),
                txtOryantasyonBit.getText(),
                txtParentName.getText(),
                txtParentLastName.getText(),
                txtParentNumber.getText(),
                txtParentEmail.getText(),
                500
        );

        try {
            ClassroomAttendanceRepository.Instance.Add(studentToAdd);
            goBack();
        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }
}
