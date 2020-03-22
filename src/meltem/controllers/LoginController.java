package meltem.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import meltem.services.SceneBuilder;

import java.io.IOException;

public class LoginController {
    public TextField txtUsername;
    public TextField txtPassword;

    private Boolean userIsValid() {
        return true;
    }

    @FXML
    public void login() throws IOException {
        if(userIsValid()) {
            SceneBuilder.Instance.BuildScene("home");
        }
    }
}
