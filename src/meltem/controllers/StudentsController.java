package meltem.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import meltem.Main;
import meltem.models.RouteData;
import meltem.models.Student;
import meltem.services.SceneBuilder;
import meltem.services.data_access.concrete.StudentRepository;
import meltem.services.logging.Logger;
import meltem.view_models.StudentViewModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

//new GregorianCalendar(2020,0,31)

public class StudentsController implements Initializable {
    @FXML
    public Button btnUpdateStudent;
    @FXML
    public TableView<StudentViewModel> tableStudents;
    @FXML
    public TableColumn<StudentViewModel, SimpleIntegerProperty> colStudentId;
    @FXML
    public TableColumn<StudentViewModel, SimpleStringProperty> colStudentName;
    @FXML
    public TableColumn<StudentViewModel, SimpleStringProperty> colStudentLastName;
    @FXML
    public TableColumn<StudentViewModel, SimpleStringProperty> colParentPhone;
    @FXML
    public TableColumn<StudentViewModel, SimpleStringProperty> colOrientationStart;
    @FXML
    public TableColumn<StudentViewModel, SimpleStringProperty> colOrientationEnd;
    @FXML
    public Button btnParentInfo;
    @FXML
    public Button btnPaymentInfo;
    @FXML
    public Button btnEdit;
    @FXML
    public TextField txtStudentId;

    public ObservableList<StudentViewModel> studentVMs = FXCollections.observableArrayList();

    private int selectedId;

    public StudentsController() throws SQLException {
    }

    public void findStudent() throws IOException {
        int studentId = Integer.parseInt(txtStudentId.getText());
        if(studentId != 0) {
            SceneBuilder.Instance.BuildScene("student_info", new RouteData(studentId, "student"));
        }
    }
    public void findStudentForClass() throws IOException {
        int studentId = Integer.parseInt(txtStudentId.getText());
        if(studentId != 0) {
            SceneBuilder.Instance.BuildScene("student_info_branch", new RouteData(studentId, "student"));
        }
    }

    public final ObservableList<StudentViewModel> data = FXCollections.observableArrayList(
            fetchAllModelsForStudents()
    );

    public List<StudentViewModel> fetchAllModelsForStudents() throws SQLException {
        List<Student> students = StudentRepository.Instance.fetchAll();
        for (Student student: students) {
            studentVMs.add(new StudentViewModel(student));
        }
        return studentVMs;
    }

    private void getCourseViewModel() {
        if(tableStudents.getSelectionModel().getSelectedItem() != null) {
            selectedId = tableStudents.getSelectionModel().getSelectedItem().student.getStudentId();
            StudentEditController.StudentId = selectedId;
            Logger.LogDebug(String.valueOf(selectedId) + " is the selected ID.");
            btnUpdateStudent.setDisable(false);
            btnPaymentInfo.setDisable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableStudents.setFixedCellSize(50);

        colStudentId.setCellValueFactory(
                student -> student.getValue().studentId
        );

        // First Name
        colStudentName.setCellValueFactory(
                student -> student.getValue().studentName
        );
        // Last Name
        colStudentLastName.setCellValueFactory(
                student -> student.getValue().studentLastName
        );
        // Orientation Start
        colParentPhone.setCellValueFactory(
                student -> student.getValue().parentNumber
        );
        colOrientationStart.setCellValueFactory(
                student -> student.getValue().orientationStart
        );
        colOrientationEnd.setCellValueFactory(
                student -> student.getValue().orientationEnd
        );



        tableStudents.setItems(data);

        tableStudents.setOnMouseClicked(v -> getCourseViewModel());

    }

    public void proceedToAdd() throws IOException {
        StudentNewController.route = 2;
        SceneBuilder.Instance.BuildScene("student_new");
    }

    public void proceedToEdit() throws IOException {
        StudentEditController.route = 2;
        SceneBuilder.Instance.BuildScene("student_edit");
    }
    public void proceedToInfo() throws IOException {
        SceneBuilder.Instance.BuildScene("student_info", new RouteData(selectedId, "student"));
    }
    public void proceedToUneditableInfo() throws IOException {
        SceneBuilder.Instance.BuildScene("student_info_branch", new RouteData(selectedId, "student"));
    }

    @FXML
    public void goBackToBranch(ActionEvent actionEvent) throws IOException {
        SceneBuilder.Instance.BuildScene("attendance_branch");
    }

    @FXML
    public void goBackToClassroom(ActionEvent actionEvent) throws IOException {
        SceneBuilder.Instance.BuildScene("attendance_classroom");
    }


    public void goBack(ActionEvent event) throws IOException {
        switch(Main.user.getUserAuth()) {
            case 1:
                SceneBuilder.Instance.BuildScene("search_page");
                break;
            case 2:
                SceneBuilder.Instance.BuildScene("home_class");
                break;
            case 3:
                SceneBuilder.Instance.BuildScene("home_branch");
                break;
        }
    }

    public void proceedToAssignBranch(ActionEvent event) throws IOException {
        SceneBuilder.Instance.BuildScene("student_assign_branch");
    }
    public void proceedToAssignClassroom(ActionEvent event) throws IOException {
        SceneBuilder.Instance.BuildScene("student_assign_branch");
    }
}
