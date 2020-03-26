package meltem.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import meltem.Main;
import meltem.enums.LogType;
import meltem.models.User;
import meltem.services.SceneBuilder;
import meltem.services.logging.Logger;

import java.io.IOException;
import java.util.List;

public class LoginController {
    @FXML
    public TextField txtUsername;
    @FXML
    public TextField txtPassword;

    private Boolean userIsValid() {
        List<User> userList = Main.userDataService.fetchAll();
        for(User u: userList) {
            if(u.getUserName().equals(txtUsername.getText()) && u.getPassword().equals(txtPassword.getText())) {
                return true;
            }
        }
        return false;
    }

    @FXML
    public void login() throws IOException {
        if(userIsValid()) {
            SceneBuilder.Instance.BuildScene("home");
        } else {
            Logger.Log(LogType.Warning, "Yanlis hesap bilgileri girdiniz!");
        }
    }
}
