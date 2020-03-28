package meltem.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import meltem.Main;
import meltem.models.RouteData;
import meltem.models.Student;
import meltem.models.User;
import meltem.services.SceneBuilder;
import meltem.services.logging.Logger;
import meltem.view_models.MeetingViewModel;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MeetingEditController implements Initializable {
    public final ObservableList<MeetingViewModel> data = FXCollections.observableArrayList(
            new MeetingViewModel(1, "Ali Öncül Hakkında", "...", "29/03/2020")
    );
    @FXML
    public Text indicatorId;
    @FXML
    public Text txtMeetingId;
    @FXML
    public TextField txtMeetingTitle;
    @FXML
    public TextField txtMeetingDate;
    @FXML
    public TextField txtMeetingSummary;
    @FXML
    public Button btnNew;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Main.userDataService.fetchById(SceneBuilder.routeData.id);
        if(SceneBuilder.routeData != null) {
            Logger.LogDebug(SceneBuilder.routeData.dataName);
            txtMeetingId.setText(String.valueOf(data.get(0).meeting.meetingId));
            txtMeetingTitle.setText(data.get(0).meeting.meetingTitle);
            txtMeetingDate.setText(data.get(0).meeting.meetingDate);
            txtMeetingSummary.setText(data.get(0).meeting.meetingDescription);
            // Dropdown'a stringler atanir.
        } else {
            if(txtMeetingId != null) {
                txtMeetingId.setDisable(false);
            }
            if(indicatorId != null) {
                indicatorId.setDisable(false);
            }
        }
    }
    public void update() throws IOException {
        SceneBuilder.Instance.BuildScene("meeting_edit", new RouteData(1, "user"));
    }
    public void delete() throws IOException {
        Logger.LogDebug("DELETE!");
        SceneBuilder.Instance.BuildScene("meeting_list");
    }
    @FXML
    public void goBack() throws IOException {
        SceneBuilder.Instance.BuildScene("meeting_list");
    }
}
