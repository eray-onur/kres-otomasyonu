package meltem.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import meltem.Main;
import meltem.models.Student;
import meltem.models.User;
import meltem.services.SceneBuilder;
import meltem.services.logging.Logger;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserEditController implements Initializable {
    User user = new User(
            1,
            "Sema Yirun",
            "*******",
            (short) 1
    );
    @FXML
    public TextField txtUserId;
    @FXML
    public TextField txtUserName;
    @FXML
    public TextField txtPw;
    @FXML
    public TextField txtUserAuth;
    @FXML
    public Button btnNew;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Main.userDataService.fetchById(SceneBuilder.routeData.id);
        if(SceneBuilder.routeData != null) {
            Logger.LogDebug(SceneBuilder.routeData.dataName);
            txtUserId.setText(String.valueOf(user.getUserId()));
            txtUserName.setText(user.getUserName());
            txtPw.setText(user.getPassword());
            txtUserAuth.setText(String.valueOf(user.getUserAuth()));
        }
    }
    @FXML
    public void goBack() throws IOException {
        SceneBuilder.Instance.BuildScene("user_list");
    }
}
