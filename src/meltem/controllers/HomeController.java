package meltem.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import meltem.services.SceneBuilder;
import meltem.services.logging.Logger;

import java.io.IOException;

public class HomeController {
    @FXML
    Button btnStudents;
    @FXML
    Button btnClassrooms;
    @FXML
    Button btnMeetings;
    @FXML
    Button btnEmergency;
    @FXML
    public void initialize() throws Exception {
        LoadStudents();
        LoadClassrooms();
        LoadMeetings();
        LoadEmergency();
    }
    private void LoadStudents() throws IOException {
        btnStudents.setOnAction(e -> {
            try {
                SceneBuilder.Instance.BuildScene("student_list");
            } catch (IOException ex) {
                Logger.LogError(ex.getMessage());
            }
        });
    }
    private void LoadClassrooms() throws IOException {
        btnClassrooms.setOnAction(e -> {
            try {
                SceneBuilder.Instance.BuildScene("classroom");
            } catch (IOException ex) {
                Logger.LogError(ex.getMessage());
            }
        });
    }
    private void LoadMeetings() throws IOException {
        btnMeetings.setOnAction(e -> {
            try {
                SceneBuilder.Instance.BuildScene("meeting_list");
            } catch (IOException ex) {
                Logger.LogError(ex.getMessage());
            }
        });
    }
    private void LoadEmergency() throws IOException {
        btnEmergency.setOnAction(e -> {
            try {
                SceneBuilder.Instance.BuildScene("emergency_numbers");
            } catch (IOException ex) {
                Logger.LogError(ex.getMessage());
            }
        });
    }
}
