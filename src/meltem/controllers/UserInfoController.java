package meltem.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import meltem.models.RouteData;
import meltem.models.Student;
import meltem.models.User;
import meltem.services.SceneBuilder;
import meltem.services.logging.Logger;
import meltem.view_models.StudentViewModel;
import meltem.view_models.UserViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserInfoController implements Initializable {
    @FXML
    public Text txtUserId;
    @FXML
    public Text txtUserName;
    @FXML
    public Text txtPw;
    @FXML
    public Text txtUserAuth;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User user = new UserViewModel(1, "sema_yirun", "1", 1).user;
        txtUserId.setText(String.valueOf(user.getUserId()));
        txtUserName.setText(user.getUserName());
        txtPw.setText(user.getPassword());
        txtUserAuth.setText(user.getTrueAuth());

    }

    public void goBack() throws IOException {
        SceneBuilder.Instance.BuildScene("user_list");
    }
    public void update() throws IOException {
        SceneBuilder.Instance.BuildScene("user_edit", new RouteData(1, "user"));
    }
    public void delete() throws IOException {
        Logger.LogDebug("DELETE!");
    }
}
