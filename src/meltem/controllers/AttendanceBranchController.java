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

public class AttendanceBranchController implements Initializable {

    @FXML
    public TextField txtCourseId;
    public final ObservableList<BranchViewModel> branches = FXCollections.observableArrayList(
            new BranchViewModel("Dans", "Oya Doğan"),
            new BranchViewModel("Fen ve Doğa", "Yasemin Karagöz"),
            new BranchViewModel("Jimnastik", "Merve Keskin"),
            new BranchViewModel("Satranç", "Neriman Ünel")
    );
    @FXML
    public Button btnEdit;
    @FXML
    private TableView<BranchViewModel> tableBranches = new TableView<BranchViewModel>();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(btnEdit != null) {
            btnEdit.setDisable(true);
        }
        tableBranches.setEditable(true);
        Logger.LogDebug(branches.get(1).branch.toString());
        // First Name
        TableColumn<BranchViewModel, SimpleStringProperty> userIdCol = new TableColumn<>("Branş Ders Ismi");
        userIdCol.setMinWidth(100);
        userIdCol.setCellValueFactory(
                user -> user.getValue().nBranchCourse
        );
        // Last Name
        TableColumn<BranchViewModel, SimpleStringProperty> usernameCol = new TableColumn<>("Branş Ders Öğretmeni");
        usernameCol.setMinWidth(100);
        usernameCol.setCellValueFactory(
                user -> user.getValue().nBranchTeacherFullName
        );



        tableBranches.setItems(branches);
        tableBranches.getColumns().addAll(userIdCol, usernameCol);
    }

    @FXML
    public void loadBranch() throws IOException {
        SceneBuilder.Instance.BuildScene("branch_student_list");
    }

    public void findBranch() throws IOException {
        int id = tableBranches.getSelectionModel().getSelectedItem().branch.branchId;
        if(id != 0) {
            btnEdit.setDisable(false);
        }
    }
}
