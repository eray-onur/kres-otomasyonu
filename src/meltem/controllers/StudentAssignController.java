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
import meltem.enums.LogType;
import meltem.models.Classroom;
import meltem.services.SceneBuilder;
import meltem.services.logging.Logger;
import meltem.view_models.BranchViewModel;
import meltem.view_models.ClassroomViewModel;
import meltem.view_models.StudentViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentAssignController implements Initializable {
    @FXML
    private TableView<StudentViewModel> studentTable = new TableView<StudentViewModel>();
    @FXML
    private TableView<ClassroomViewModel> classroomTable = new TableView<ClassroomViewModel>();
    @FXML
    private TableView<BranchViewModel> branchTable = new TableView<BranchViewModel>();
    @FXML
    private Button btnEditClass;
    @FXML
    private Button btnEditBranch;

    public final ObservableList<StudentViewModel> data = FXCollections.observableArrayList(
            new StudentViewModel(1,
                    "Ali",
                    "Oncul",
                    "23/03/2020",
                    "07/04/2020",
                    "0543 555 4433",
                    "Ahmet",
                    "Oncul",
                    "aoncul76@hotmail.com",
                    680
            ),
            new StudentViewModel(1,
                    "Veli",
                    "Turk",
                    "23/03/2020",
                    "07/04/2020",
                    "0543 555 4433",
                    "Huseyin",
                    "Turk",
                    "",
                    680
            ),
            new StudentViewModel(1,
                    "Mehmet",
                    "Kaya",
                    "23/03/2020",
                    "07/04/2020",
                    "0543 666 1122",
                    "Nazan",
                    "Ata",
                    "nazan.ata@gmail.com",
                    680
            ),
            new StudentViewModel(1,
                    "Abdullah",
                    "Gök",
                    "23/03/2020",
                    "07/04/2020",
                    "0543 222 3399",
                    "Davud",
                    "Gök",
                    "",
                    680
            ),
            new StudentViewModel(1,
                    "Atakan",
                    "Irmak",
                    "23/03/2020",
                    "07/04/2020",
                    "0543 545 4433",
                    "Davud",
                    "Oncul",
                    "aoncul76@hotmail.com",
                    680
            )
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (btnEditBranch == null && btnEditClass != null) {
            Logger.Log(LogType.Debug, "Initializing class table.");
            btnEditClass.setDisable(true);
            if (btnEditClass == null && btnEditBranch != null) {
                Logger.Log(LogType.Debug, "Initializing branch table.");
                btnEditBranch.setDisable(true);
                branchTable.setEditable(true);
                // First Name
                TableColumn<BranchViewModel, SimpleStringProperty> courseCol = new TableColumn<>("Branş Ders Adı");
                courseCol.setCellValueFactory(
                        course -> course.getValue().nBranchCourse
                );
                // Last Name
                TableColumn<BranchViewModel, SimpleStringProperty> nameCol = new TableColumn<>("Branş Ders Öğretmeni");
                nameCol.setCellValueFactory(
                        course -> course.getValue().nBranchTeacherFullName
                );
                branchTable.getColumns().addAll(
                        courseCol,
                        nameCol
                );
            }
        }
        else {
            studentTable.setEditable(true);
            // First Name
            TableColumn<StudentViewModel, SimpleStringProperty> firstNameCol = new TableColumn<>("Öğrenci Adı");
            firstNameCol.setMinWidth(100);
            firstNameCol.setCellValueFactory(
                    student -> student.getValue().studentName
            );
            // Last Name
            TableColumn<StudentViewModel, SimpleStringProperty> lastNameCol = new TableColumn<>("Öğrenci Soyadı");
            lastNameCol.setMinWidth(100);
            lastNameCol.setCellValueFactory(
                    student -> student.getValue().studentLastName
            );
            // Orientation Start
            TableColumn<StudentViewModel, SimpleStringProperty> orientatonStartCol = new TableColumn<>("Oryantasyon Baslangıç Tarihi");
            orientatonStartCol.setMinWidth(250);
            orientatonStartCol.setCellValueFactory(
                    student -> student.getValue().orientationStart
            );
            // Orientation End
            TableColumn<StudentViewModel, SimpleStringProperty> orientatonEndCol = new TableColumn<>("Oryantasyon Bitiş Tarihi");
            orientatonEndCol.setMinWidth(200);
            orientatonEndCol.setCellValueFactory(
                    student -> student.getValue().orientationEnd
            );
            // Parent Phone Number
            TableColumn<StudentViewModel, SimpleStringProperty> parentNameCol = new TableColumn<>("Veli Adı");
            parentNameCol.setMinWidth(175);
            parentNameCol.setCellValueFactory(
                    student -> student.getValue().parentName
            );
            // Parent Phone Number
            TableColumn<StudentViewModel, SimpleStringProperty> parentLnameCol = new TableColumn<>("Veli Soyadı");
            parentLnameCol.setMinWidth(175);
            parentLnameCol.setCellValueFactory(
                    student -> student.getValue().parentLastName
            );
            // Parent Phone Number
            TableColumn<StudentViewModel, SimpleStringProperty> parentNumberCol = new TableColumn<>("Veli Telefon No.");
            parentNumberCol.setMinWidth(175);
            parentNumberCol.setCellValueFactory(
                    student -> student.getValue().parentNumber
            );
            // Parent Phone Number
            TableColumn<StudentViewModel, SimpleStringProperty> emailCol = new TableColumn<>("Veli Email");
            emailCol.setMinWidth(175);
            emailCol.setCellValueFactory(
                    student -> student.getValue().parentEmail
            );


            studentTable.setItems(data);
            studentTable.getColumns().addAll(
                    firstNameCol,
                    lastNameCol,
                    orientatonStartCol,
                    orientatonEndCol,
                    parentNameCol,
                    parentLnameCol,
                    parentNumberCol
            );
        }
    }
    public final ObservableList<ClassroomViewModel> classes = FXCollections.observableArrayList(
            new ClassroomViewModel(1, 1),
            new ClassroomViewModel(1, 1),
            new ClassroomViewModel(1, 1),
            new ClassroomViewModel(1, 1)
    );
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
                //selectedId = branchTable.getSelectionModel().getSelectedItem().branch.nBranchTeacherFullName;
                Logger.LogDebug(selectedId);
                if(!selectedId.isEmpty()) {
                    btnEditBranch.setDisable(false);
                }
            }
        });
    }
    @FXML
    public void goBack() throws IOException {
        SceneBuilder.Instance.BuildScene("student_list");
    }
    @FXML
    public void goBackAdm() throws IOException {
        SceneBuilder.Instance.BuildScene("classroom_list");
    }
    @FXML
    public void goBranchAssign() throws IOException {
        SceneBuilder.Instance.BuildScene("student_assign_branch");
    }
    @FXML
    public void goClassroomAssign() throws IOException {
        SceneBuilder.Instance.BuildScene("student_assign_classroom");
    }
    @FXML
    public void goBranchAssignAdm() throws IOException {
        SceneBuilder.Instance.BuildScene("student_assign_branch_a");
    }
    @FXML
    public void goClassroomAssignAdm() throws IOException {
        SceneBuilder.Instance.BuildScene("student_assign_classroom_a");
    }
}
