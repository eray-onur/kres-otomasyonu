package meltem.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import meltem.Main;
import meltem.enums.LogType;
import meltem.models.Classroom;
import meltem.services.SceneBuilder;
import meltem.services.logging.Logger;
import meltem.view_models.BranchViewModel;
import meltem.view_models.ClassroomViewModel;
import meltem.view_models.StudentViewModel;
import meltem.view_models.TeacherViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherAssignController implements Initializable {
    public ObservableList<TeacherViewModel> teachers = FXCollections.observableArrayList();
    @FXML
    private TableView<ClassroomViewModel> classroomTable = new TableView<ClassroomViewModel>();
    @FXML
    private TableView<BranchViewModel> branchTable = new TableView<BranchViewModel>();
    @FXML
    private TableView<TeacherViewModel> teacherTable = new TableView<TeacherViewModel>();
    @FXML
    private Button btnEditClass;
    @FXML
    private Button btnEditBranch;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        teachers.add(new TeacherViewModel(1, "Sema", "Yirun", "s.yirun@abc.com", "", 0));
        teachers.add(new TeacherViewModel(1, "Jane", "Doe", "j.doe@abc.com","",1));
        teacherTable.setEditable(true);
        // First Name
        TableColumn<TeacherViewModel, SimpleStringProperty> firstNameCol = new TableColumn<>("Öğretmen Adı");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                teacher -> teacher.getValue().teacherName
        );
        // Last Name
        TableColumn<TeacherViewModel, SimpleStringProperty> lastNameCol = new TableColumn<>("Öğretmen Soyadı");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                teacher -> teacher.getValue().teacherLastName
        );
        // Orientation Start
        TableColumn<TeacherViewModel, SimpleStringProperty> emailCol = new TableColumn<>("Öğretmen Email'ı");
        emailCol.setMinWidth(250);
        emailCol.setCellValueFactory(
                teacher -> teacher.getValue().teacherEmail
        );
        // Orientation End
        TableColumn<TeacherViewModel, SimpleStringProperty> typeCol = new TableColumn<>("Öğretmen Tipi");
        typeCol.setMinWidth(200);
        typeCol.setCellValueFactory(
                teacher -> teacher.getValue().teacherType
        );


        teacherTable.setItems(teachers);
        teacherTable.getColumns().addAll(
                firstNameCol,
                lastNameCol,
                emailCol,
                typeCol
        );
    }

//    public final ObservableList<ClassroomViewModel> classes = FXCollections.observableArrayList(
//            new ClassroomViewModel(1, "A", 1, 25),
//            new ClassroomViewModel(2, "B", 1, 25),
//            new ClassroomViewModel(3, "C", 1, 25),
//            new ClassroomViewModel(4, "D", 1, 25)
//    );
    public final ObservableList<BranchViewModel> branches = FXCollections.observableArrayList(
            new BranchViewModel("Dans", "Oya Doğan"),
            new BranchViewModel("Fen ve Doğa", "Yasemin Karagöz"),
            new BranchViewModel("Jimnastik", "Merve Keskin"),
            new BranchViewModel("Satranç", "Neriman Ünel")
    );
    @FXML
    public void onClickClass() throws IOException {
        classroomTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            String selectedId;
            @Override
            public void handle(MouseEvent event) {
                selectedId = classroomTable.getSelectionModel().getSelectedItem().classroom.getClassroomName();
                Logger.LogDebug(selectedId);
                if(!selectedId.isEmpty()) {
                    btnEditClass.setDisable(false);
                }
            }
        });
    }
    @FXML
    public void onClickBranch() {
        branchTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            String selectedId;
            @Override
            public void handle(MouseEvent event) {
                selectedId = String.valueOf(1);
                Logger.LogDebug(selectedId);
                if(!selectedId.isEmpty()) {
                    btnEditBranch.setDisable(false);
                }
            }
        });
    }
    @FXML
    public void goBackToSearchPanel() throws IOException {
        SceneBuilder.Instance.BuildScene("search_page");
    }
    @FXML
    public void goBackFromClassroom() throws IOException {
        switch(Main.user.getUserAuth()) {
            case 1:
                SceneBuilder.Instance.BuildScene("attendance_classroom");
                break;
            case 2:
                SceneBuilder.Instance.BuildScene("attendance_classroom");
                break;
            case 3:
                SceneBuilder.Instance.BuildScene("attendance_classroom");
                break;
        }
    }
    @FXML
    public void goBackFromBranch() throws IOException {
        switch(Main.user.getUserAuth()) {
            case 1:
                SceneBuilder.Instance.BuildScene("attendance_branch");
                break;
            case 2:
                SceneBuilder.Instance.BuildScene("attendance_branch");
                break;
            case 3:
                SceneBuilder.Instance.BuildScene("attendance_branch");
                break;
        }
    }
    @FXML
    public void goBranchAssign() throws IOException {
        SceneBuilder.Instance.BuildScene("teacher_assign_branch");
    }
    @FXML
    public void goClassroomAssign() throws IOException {
        SceneBuilder.Instance.BuildScene("teacher_assign_classroom");
    }

    public void onClickTeacher(MouseEvent mouseEvent) {
    }
}
