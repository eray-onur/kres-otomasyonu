package meltem.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import meltem.models.RouteData;
import meltem.services.SceneBuilder;
import meltem.services.logging.Logger;
import meltem.view_models.MeetingViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MeetingInfoController implements Initializable {
    public final ObservableList<MeetingViewModel> data = FXCollections.observableArrayList(
            new MeetingViewModel(1, "Ali Öncül Hakkında", "...", "29/03/2020")
    );
    @FXML
    public Text txtMeetingId;
    @FXML
    public Text txtMeetingTitle;
    @FXML
    public Text txtMeetingDate;
    @FXML
    public Text txtMeetingSummary;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Logger.LogDebug(SceneBuilder.routeData.dataName);
        txtMeetingId.setText(String.valueOf(data.get(0).meeting.getMeetingId()));
        txtMeetingTitle.setText(data.get(0).meeting.getMeetingTitle());
        txtMeetingDate.setText(data.get(0).meeting.getMeetingDate());
        txtMeetingSummary.setText(data.get(0).meeting.getMeetingSummary());
    }

    public void goBack() throws IOException {
        SceneBuilder.Instance.BuildScene("meeting_list");
    }
    public void update() throws IOException {
        SceneBuilder.Instance.BuildScene("meeting_edit", new RouteData(1, "meeting"));
    }
    public void delete() throws IOException {
        Logger.LogDebug("DELETE!");
        SceneBuilder.Instance.BuildScene("meeting_list");
    }
}
