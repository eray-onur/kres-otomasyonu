package meltem.controllers;

import javafx.fxml.FXML;
import meltem.services.SceneBuilder;

import java.io.IOException;

public class EmergencyController {
    @FXML
    public void goBack() throws IOException {
        SceneBuilder.Instance.BuildScene("home");
    }
}
