package meltem.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import meltem.models.RouteData;
import meltem.models.Student;
import meltem.services.SceneBuilder;
import meltem.services.logging.Logger;
import meltem.view_models.BranchViewModel;
import meltem.view_models.StudentViewModel;
import meltem.view_models.UserViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//new GregorianCalendar(2020,0,31)

public class BranchController implements Initializable {
    int selectedId = 0;
    @FXML
    public Button btnEdit;
    @FXML
    public TextField txtCourseId;
    @FXML
    public Text txtCourseName;
    @FXML
    public Text txtCourseTeacher;
    public void findCourse() throws IOException {
        int userId = Integer.parseInt(txtCourseId.getText());
        if(userId != 0) {
            SceneBuilder.Instance.BuildScene("course_info");
        }
    }
    @FXML
    private TableView<BranchViewModel> table = new TableView<BranchViewModel>();
    public final ObservableList<BranchViewModel> data = FXCollections.observableArrayList(
            new BranchViewModel("Dans", "sema_yirun"),
            new BranchViewModel("Fen ve Doğa", "Neriman Ünel"),
            new BranchViewModel("Jimnastik", "neriman_unel")
    );
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(btnEdit != null) {
            btnEdit.setDisable(true);
        }

        table.setEditable(true);
        Logger.LogDebug(data.get(1).branch.toString());
        // First Name
        TableColumn<BranchViewModel, SimpleStringProperty> userIdCol = new TableColumn<>("Branş Ders İsmi");
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



        table.setItems(data);
        table.getColumns().addAll(userIdCol, usernameCol);

    }

    public void clickItem(MouseEvent event) {
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedId = table.getSelectionModel().getSelectedItem().branch.getBranchId();
                if(selectedId != 0) {
                    btnEdit.setDisable(false);
                }
            }
        });
    }

    public void addData() throws IOException {
        SceneBuilder.Instance.BuildScene("branch_new");
    }

    public void proceedToEdit() throws IOException {
        SceneBuilder.Instance.BuildScene("branch_edit", new RouteData(selectedId, "branch"));
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        SceneBuilder.Instance.BuildScene("home");
    }
}
