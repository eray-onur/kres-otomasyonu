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
import meltem.models.RouteData;
import meltem.models.Student;
import meltem.services.SceneBuilder;
import meltem.services.logging.Logger;
import meltem.view_models.ClassroomViewModel;
import meltem.view_models.StudentViewModel;
import meltem.view_models.UserViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//new GregorianCalendar(2020,0,31)

public class ClassroomsController implements Initializable {
    int selectedId = 0;
    @FXML
    public Button btnEdit;
    @FXML
    public TextField txtClassroomId;
    public void findUser() throws IOException {
        int userId = Integer.parseInt(txtClassroomId.getText());
        if(userId != 0) {
            SceneBuilder.Instance.BuildScene("classroom_info");
        }
    }
    @FXML
    private TableView<ClassroomViewModel> table = new TableView<ClassroomViewModel>();
    public final ObservableList<ClassroomViewModel> data = FXCollections.observableArrayList(
            new ClassroomViewModel(1, "A", 25),
            new ClassroomViewModel(2, "B", 25),
            new ClassroomViewModel(3, "C", 25),
            new ClassroomViewModel(4, "D", 25)
    );
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnEdit.setDisable(true);
        table.setEditable(true);
        Logger.LogDebug(data.get(1).classroom.toString());
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



        table.setItems(data);
        table.getColumns().addAll(userIdCol, usernameCol, pwCol);

    }

    public void clickItem(MouseEvent event) {
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedId = table.getSelectionModel().getSelectedItem().classroom.getClassroomId();
                if(selectedId != 0) {
                    btnEdit.setDisable(false);
                }
            }
        });
    }

    public void addData() throws IOException {
        SceneBuilder.Instance.BuildScene("classroom_new");
    }

    public void proceedToEdit() throws IOException {
        SceneBuilder.Instance.BuildScene("classroom_edit", new RouteData(selectedId, "classroom"));
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        SceneBuilder.Instance.BuildScene("home");
    }
}