package meltem.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    @FXML
    public Button btnAssignTeacher;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(btnEdit != null) {
            btnEdit.setDisable(true);
        }
        if(btnAssignTeacher != null) {
            btnAssignTeacher.setDisable(true);
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
//    public void clickItem(MouseEvent event) {
//        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                selectedId = table.getSelectionModel().getSelectedItem().user.getUserId();
//                if(selectedId != 0) {
//                    btnEdit.setDisable(false);
//                }
//            }
//        });
//    }

    @FXML
    public void loadTeacher() throws IOException {
        SceneBuilder.Instance.BuildScene("teacher_list");
    }

    @FXML
    public void loadBranch() throws IOException {
        SceneBuilder.Instance.BuildScene("teacher_assign_branch");
    }

    public void findBranch() throws IOException {
        int id = tableBranches.getSelectionModel().getSelectedItem().branch.getBranchId();
        if(id != 0) {
            btnEdit.setDisable(false);
            if(btnAssignTeacher != null) {
                btnAssignTeacher.setDisable(false);
            }
        }
    }

    public void proceedToAttendance(ActionEvent event) throws IOException {
        SceneBuilder.Instance.BuildScene("branch_student_list");
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

    public void addBranch(ActionEvent event) throws IOException {
        SceneBuilder.Instance.BuildScene("course_new");
    }
}
