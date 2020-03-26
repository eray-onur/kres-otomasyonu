package meltem.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import meltem.services.SceneBuilder;

import java.io.IOException;

public class SearchController {
    @FXML
    public void goStudent() throws IOException {
        SceneBuilder.Instance.BuildScene("student_list");
    }
    @FXML
    public void goClasses() throws IOException {
        SceneBuilder.Instance.BuildScene("classroom_list");
    }
    @FXML
    public void goCourses() throws IOException {
        SceneBuilder.Instance.BuildScene("course_list");
    }
    @FXML
    public void goTeachers() throws IOException {
        SceneBuilder.Instance.BuildScene("teacher_list");
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        SceneBuilder.Instance.BuildScene("home");
    }
}
