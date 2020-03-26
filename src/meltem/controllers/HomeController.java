package meltem.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import meltem.services.SceneBuilder;
import meltem.services.logging.Logger;

import java.io.IOException;

public class HomeController {
    public Button btnUsers;
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
        LoadClassroom();
        LoadMeetings();
        LoadEmergency();
        LoadUsers();
    }
    private void LoadStudents() throws IOException {
        btnStudents.setOnAction(e -> {
            try {
                SceneBuilder.Instance.BuildScene("search_page");
            } catch (IOException ex) {
                Logger.LogError(ex.getMessage());
            }
        });
    }
    private void LoadClassroom() throws IOException {
        btnClassrooms.setOnAction(e -> {
            try {
                SceneBuilder.Instance.BuildScene("classroom");
            } catch (IOException ex) {
                Logger.LogError(ex.getMessage());
            }
        });
    }
    private void LoadCourse() throws  IOException {
        btnClassrooms.setOnAction(e -> {
            try {
                SceneBuilder.Instance.BuildScene("course");
            } catch(IOException ex) {
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
    private void LoadUsers() throws IOException {
        btnUsers.setOnAction(e -> {
            try {
                SceneBuilder.Instance.BuildScene("user_list");
            } catch (IOException ex) {
                Logger.LogError(ex.getMessage());
            }
        });
    }
}
