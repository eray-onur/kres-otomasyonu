package meltem.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import meltem.models.RouteData;
import meltem.services.SceneBuilder;


import java.io.IOException;

public class CourseSearchController {
    @FXML
    public TextField searchedId;
    @FXML
    public void proceedToSearch() throws IOException {
        SceneBuilder.Instance.BuildScene("course_info", new RouteData(Integer.getInteger(searchedId.getText()), "course"));
    }
}
