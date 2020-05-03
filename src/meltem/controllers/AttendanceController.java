package meltem.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import meltem.Main;
import meltem.services.SceneBuilder;
import meltem.services.logging.Logger;
import meltem.view_models.BranchViewModel;
import meltem.view_models.ClassroomViewModel;
import meltem.view_models.StudentViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AttendanceController implements Initializable {
    @FXML
    public TextField txtClassroomId;
    @FXML
    public TextField txtCourseId;
    @FXML
    public TabPane tabPane;
    @FXML
    public Tab tabSinif;
    @FXML
    public Tab tabBrans;
    public final ObservableList<BranchViewModel> branches = FXCollections.observableArrayList(
            new BranchViewModel("Dans", "Oya Doğan"),
            new BranchViewModel("Fen ve Doğa", "Yasemin Karagöz"),
            new BranchViewModel("Jimnastik", "Merve Keskin"),
            new BranchViewModel("Satranç", "Neriman Ünel")
    );
    @FXML
    public Button btnEdit;
    @FXML
    public Button btnEditT;
    @FXML
    public Button btnCourse;

    @FXML
    private TableView<ClassroomViewModel> tableClassroom = new TableView<ClassroomViewModel>();
    @FXML
    private TableView<ClassroomViewModel> tableBranches = new TableView<ClassroomViewModel>();


    public void findBranch() throws IOException {
        int id = Integer.parseInt(txtCourseId.getText());
        if(id != 0) {
            loadBranch();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(btnEdit != null) {
            btnEdit.setDisable(true);
        }
        if(btnEditT != null) {
            btnEditT.setDisable(true);
        }
        if(btnCourse != null) {
            btnCourse.setDisable(true);
        }

        tableClassroom.setEditable(true);
        //Logger.LogDebug(classes.get(1).classroom.toString());
        // First Name
        TableColumn<ClassroomViewModel, SimpleIntegerProperty> classroomIdCol = new TableColumn<>("Sınıf Numarası");
        classroomIdCol.setMinWidth(100);
        classroomIdCol.setCellValueFactory(
                classroom -> classroom.getValue().classroomId
        );
        // Last Name
        TableColumn<ClassroomViewModel, SimpleStringProperty> classroomNameCol = new TableColumn<>("Sınıf Ismi");
        classroomNameCol.setMinWidth(100);
        classroomNameCol.setCellValueFactory(
                classroom -> classroom.getValue().classroomName
        );
        // Orientation Start
        TableColumn<ClassroomViewModel, SimpleIntegerProperty> capacityCol = new TableColumn<>("Sınıf Kontenjanı");
        capacityCol.setMinWidth(250);
        capacityCol.setCellValueFactory(
                classroom -> classroom.getValue().capacity
        );
        // Orientation Start
        TableColumn<ClassroomViewModel, SimpleStringProperty> teachCol = new TableColumn<>("Sınıf Öğretmeni");
        teachCol.setMinWidth(250);
        teachCol.setCellValueFactory(
                classroom -> classroom.getValue().classroomTeacherFullName
        );



        //tableClassroom.setItems(classes);
        tableClassroom.getColumns().addAll(classroomIdCol, classroomNameCol, capacityCol, teachCol);
    }

    @FXML
    public void loadBranch() throws IOException {
        SceneBuilder.Instance.BuildScene("attendance_branch");
    }
    @FXML
    public void loadClassroom() throws IOException {
        SceneBuilder.Instance.BuildScene("classroom_student_list");
    }
    @FXML
    public void loadClassroomCourses() throws IOException {
        SceneBuilder.Instance.BuildScene("course_assign");
    }

    public void findClass() throws IOException {
        int id = tableClassroom.getSelectionModel().getSelectedItem().classroomId.getValue().get();
        if(id != 0) {
            btnEdit.setDisable(false);
            if(btnEditT != null) {
                btnEditT.setDisable(false);
            }
            if(btnCourse != null) {
                btnCourse.setDisable(false);
            }
        }
    }

    public void loadTeacherClassroom(ActionEvent event) throws IOException {
        SceneBuilder.Instance.BuildScene("teacher_assign_classroom");
    }
    public void loadTeacherBranch(ActionEvent event) throws IOException {
        SceneBuilder.Instance.BuildScene("teacher_assign_branch");
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

    public void addClassroom(ActionEvent event) throws IOException {
        SceneBuilder.Instance.BuildScene("classroom_new");
    }
}
