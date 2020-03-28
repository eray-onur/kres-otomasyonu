package meltem.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import meltem.models.Classroom;
import meltem.models.RouteData;
import meltem.services.SceneBuilder;
import meltem.services.logging.Logger;
import meltem.view_models.ClassroomViewModel;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClassroomEditController implements Initializable {
    Classroom classroom = new ClassroomViewModel(1, "A Sınıfı", 25).classroom;
    @FXML
    public Text txtClassroomId;
    @FXML
    public TextField txtClassroomName;
    @FXML
    public TextField txtClassroomCapacity;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Main.userDataService.fetchById(SceneBuilder.routeData.id);
        if(SceneBuilder.routeData != null) {
            Logger.LogDebug(SceneBuilder.routeData.dataName);
            txtClassroomId.setText(String.valueOf(classroom.getClassroomId()));
            txtClassroomName.setText(classroom.getClassroomName());
            txtClassroomCapacity.setText(String.valueOf(classroom.getClassroomCapacity()));
        } else {
            if(txtClassroomId != null) {
                txtClassroomId.setDisable(false);
            }
        }
    }
    public void update() throws IOException {
        SceneBuilder.Instance.BuildScene("classroom_edit", new RouteData(1, "user"));
    }
    public void delete() throws IOException {
        Logger.LogDebug("DELETE!");
        SceneBuilder.Instance.BuildScene("classroom_list");
    }
    @FXML
    public void goBack() throws IOException {
        SceneBuilder.Instance.BuildScene("classroom_list");
    }
}
