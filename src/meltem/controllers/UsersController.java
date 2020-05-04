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
import meltem.models.User;
import meltem.services.SceneBuilder;
import meltem.services.data_access.concrete.UserRepository;
import meltem.services.logging.Logger;
import meltem.view_models.StudentViewModel;
import meltem.view_models.UserViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

//new GregorianCalendar(2020,0,31)

public class UsersController implements Initializable {
    @FXML
    TableColumn<UserViewModel, SimpleIntegerProperty> userIdCol;
    @FXML
    TableColumn<UserViewModel, SimpleStringProperty> userNameCol;
    @FXML
    TableColumn<UserViewModel, SimpleStringProperty> trueAuthCol;
    int selectedId = 0;
    @FXML
    public Button btnUpdateUser;
    @FXML
    public TextField txtUserId;
    @FXML
    public void findUser() {
        if(tableUsers.getSelectionModel().getSelectedItem() != null) {
            btnUpdateUser.setDisable(false);
            UserEditController.User = tableUsers.getSelectionModel().getSelectedItem().user;
            Logger.LogDebug(UserEditController.User.getUserName());
        }
    }
    @FXML
    private TableView<UserViewModel> tableUsers = new TableView<UserViewModel>();
    public final ObservableList<UserViewModel> data = FXCollections.observableArrayList(
            fetchUserList()
    );

    private List<UserViewModel> fetchUserList() {
        ArrayList<User> userList = (ArrayList<User>) UserRepository.Instance.fetchAll();
        ArrayList<UserViewModel> userViewModels = new ArrayList<>();
        for(User user: userList) {
            userViewModels.add(new UserViewModel(user.getUserId(), user.getUserName(), user.getPassword(), user.getUserAuth()));
        }
        return userViewModels;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableUsers.setEditable(true);
        // First Name
        tableUsers.setFixedCellSize(50);
        userIdCol.setCellValueFactory(
                user -> user.getValue().userId
        );
        // Last Name
        userNameCol.setPrefWidth(50);
        userNameCol.setCellValueFactory(
                user -> user.getValue().userName
        );
        trueAuthCol.setPrefWidth(50);
        trueAuthCol.setCellValueFactory(
                user -> user.getValue().userTrueAuth
        );



        tableUsers.setItems(data);
        tableUsers.setOnMouseClicked(v -> findUser());
    }

    public void proceedToEdit() throws IOException {
        Logger.LogDebug(UserEditController.User.getUserName() + "???????????");
        SceneBuilder.Instance.BuildScene("user_edit");
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        SceneBuilder.Instance.BuildScene("search_page");
    }

    public void proceedToNew(ActionEvent event) {
        SceneBuilder.Instance.BuildScene("user_new");
    }
}
