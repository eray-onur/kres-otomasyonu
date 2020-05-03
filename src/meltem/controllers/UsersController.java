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
import meltem.view_models.StudentViewModel;
import meltem.view_models.UserViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//new GregorianCalendar(2020,0,31)

public class UsersController implements Initializable {
    int selectedId = 0;
    @FXML
    public Button btnEdit;
    @FXML
    public TextField txtUserId;
    public void findUser() throws IOException {
        int userId = Integer.parseInt(txtUserId.getText());
        if(userId != 0) {
            SceneBuilder.Instance.BuildScene("user_info");
        }
    }
    @FXML
    private TableView<UserViewModel> table = new TableView<UserViewModel>();
    public final ObservableList<UserViewModel> data = FXCollections.observableArrayList(
            new UserViewModel(1, "sema_yirun", "1234", 1),
            new UserViewModel(2, "busra_ozel", "123", 2),
            new UserViewModel(3, "neriman_unel", "1", 3)
    );
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnEdit.setDisable(true);
        table.setEditable(true);
        Logger.LogDebug(data.get(1).user.toString());
        // First Name
        TableColumn<UserViewModel, SimpleIntegerProperty> userIdCol = new TableColumn<>("Kullanici ID'si");
        userIdCol.setMinWidth(100);
        userIdCol.setCellValueFactory(
                user -> user.getValue().userId
        );
        // Last Name
        TableColumn<UserViewModel, SimpleStringProperty> usernameCol = new TableColumn<>("Kullanici Adi");
        usernameCol.setMinWidth(100);
        usernameCol.setCellValueFactory(
                user -> user.getValue().userName
        );
        // Orientation Start
        TableColumn<UserViewModel, SimpleStringProperty> pwCol = new TableColumn<>("Sifre");
        pwCol.setMinWidth(250);
        pwCol.setCellValueFactory(
                user -> user.getValue().userPassword
        );

        TableColumn<UserViewModel, SimpleIntegerProperty> userAuthCol = new TableColumn<>("Yetki Seviyesi");
        userAuthCol.setMinWidth(250);
        userAuthCol.setCellValueFactory(
                user -> user.getValue().userAuth
        );



        table.setItems(data);
        table.getColumns().addAll(userIdCol, usernameCol, pwCol, userAuthCol);

    }

    public void clickItem(MouseEvent event) {
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedId = table.getSelectionModel().getSelectedItem().user.getUserId();
                if(selectedId != 0) {
                    btnEdit.setDisable(false);
                }
            }
        });
    }

    public void addData() throws IOException {
        SceneBuilder.Instance.BuildScene("user_new");
    }

    public void proceedToEdit() throws IOException {
        SceneBuilder.Instance.BuildScene("user_edit", new RouteData(selectedId, "user"));
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        SceneBuilder.Instance.BuildScene("search_page");
    }
}
