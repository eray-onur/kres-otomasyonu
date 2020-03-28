package meltem.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
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
    public final ObservableList<ClassroomViewModel> classes = FXCollections.observableArrayList(
            new ClassroomViewModel(1, "A", 25),
            new ClassroomViewModel(2, "B", 25),
            new ClassroomViewModel(3, "C", 25),
            new ClassroomViewModel(4, "D", 25)
    );
    public final ObservableList<BranchViewModel> branches = FXCollections.observableArrayList(
            new BranchViewModel("Dans", "Oya Doğan"),
            new BranchViewModel("Fen ve Doğa", "Yasemin Karagöz"),
            new BranchViewModel("Jimnastik", "Merve Keskin"),
            new BranchViewModel("Satranç", "Neriman Ünel")
    );
    @FXML
    public Button btnEdit;

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
        tableClassroom.setEditable(true);
        Logger.LogDebug(classes.get(1).classroom.toString());
        // First Name
        TableColumn<ClassroomViewModel, SimpleIntegerProperty> userIdCol = new TableColumn<>("Sınıf Numarası");
        userIdCol.setMinWidth(100);
        userIdCol.setCellValueFactory(
                user -> user.getValue().classroomId
        );
        // Last Name
        TableColumn<ClassroomViewModel, SimpleStringProperty> usernameCol = new TableColumn<>("Sınıf Ismi");
        usernameCol.setMinWidth(100);
        usernameCol.setCellValueFactory(
                user -> user.getValue().classroomName
        );
        // Orientation Start
        TableColumn<ClassroomViewModel, SimpleIntegerProperty> pwCol = new TableColumn<>("Sınıf Kontenjanı");
        pwCol.setMinWidth(250);
        pwCol.setCellValueFactory(
                user -> user.getValue().capacity
        );



        tableClassroom.setItems(classes);
        tableClassroom.getColumns().addAll(userIdCol, usernameCol, pwCol);
    }

    @FXML
    public void loadBranch() throws IOException {
        SceneBuilder.Instance.BuildScene("attendance_branch");
    }
    @FXML
    public void loadClassroom() throws IOException {
        SceneBuilder.Instance.BuildScene("classroom_student_list");
    }

    public void findClass() throws IOException {
        int id = tableClassroom.getSelectionModel().getSelectedItem().classroom.getClassroomId();
        if(id != 0) {
            btnEdit.setDisable(false);
        }
    }
}
