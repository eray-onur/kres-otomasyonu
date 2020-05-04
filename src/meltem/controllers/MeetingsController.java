package meltem.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import meltem.Main;
import meltem.models.Meeting;
import meltem.models.Teacher;
import meltem.services.SceneBuilder;
import meltem.services.data_access.concrete.MeetingRepository;
import meltem.services.data_access.concrete.TeacherRepository;
import meltem.services.logging.Logger;
import meltem.view_models.MeetingViewModel;
import meltem.view_models.TeacherViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MeetingsController implements Initializable {
    public static int route = 0;
    @FXML
    public Button btnAddMeeting;
    @FXML
    public Button btnDetailMeeting;
    @FXML
    public Button btnSearchMeeting;
    private int selectedId = 1;

    public TextField txtMeetingId;
    public ObservableList<MeetingViewModel> meetings = FXCollections.observableArrayList(
            fetchAllModelsForMeetings()
    );

    public List<MeetingViewModel> fetchAllModelsForMeetings()  {
        List<MeetingViewModel> meetingVMs = new ArrayList<>();
        try {
            List<Meeting> meetings = MeetingRepository.Instance.fetchAll();
            for (Meeting meeting: meetings) {
                meetingVMs.add(new MeetingViewModel(meeting));
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return meetingVMs;
    }

    @FXML
    public Button btnUpdateMeeting;

    public TableColumn<MeetingViewModel, SimpleIntegerProperty> colMeetingId;
    public TableColumn<MeetingViewModel, SimpleStringProperty> colMeetingTitle;
    public TableColumn<MeetingViewModel, SimpleStringProperty> colMeetingDate;
    public TableColumn<MeetingViewModel, SimpleStringProperty> colMeetingSummary;


    @FXML
    private TableView<MeetingViewModel> table = new TableView<MeetingViewModel>();

    @FXML
    public void getCourseViewModel(MouseEvent actionEvent) {
        if(table.getSelectionModel().getSelectedItem() != null) {
            selectedId = table.getSelectionModel().getSelectedItem().meeting.getMeetingId();
            MeetingEditController.MeetingId = selectedId;
            Logger.LogDebug(String.valueOf(TeacherEditController.TeacherId) + " is the selected ID.");
            btnUpdateMeeting.setDisable(false);
            btnDetailMeeting.setDisable(false);
        }
    }

    public void goBack(ActionEvent event) throws IOException {
        switch(route) {
            case 1:
                SceneBuilder.Instance.BuildScene("classroom_info_admin");
                break;
            case 2:
                SceneBuilder.Instance.BuildScene("student_list");
                break;
            case 3:
                SceneBuilder.Instance.BuildScene("branch_courses");
                break;
        }
    }

    public void goBackToMenu(ActionEvent event) throws IOException {
        switch(Main.user.getUserAuth()) {
            case 1:
                SceneBuilder.Instance.BuildScene("search_page");
                break;
            case 2:
                SceneBuilder.Instance.BuildScene("home_class");
                break;
            case 3:
                SceneBuilder.Instance.BuildScene("home_branch");
                break;
        }
    }

    public void proceedToEdit(ActionEvent event) throws IOException {
        TeacherEditController.TeacherId = selectedId;
        SceneBuilder.Instance.BuildScene("teacher_edit");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnUpdateMeeting.setDisable(true);
        colMeetingId.setCellValueFactory(
                meeting -> meeting.getValue().meetingId
        );
        colMeetingTitle.setCellValueFactory(
                meeting -> meeting.getValue().meetingTitle
        );
        colMeetingDate.setCellValueFactory(
                meeting -> meeting.getValue().meetingDate
        );
        colMeetingSummary.setCellValueFactory(
                meeting -> meeting.getValue().meetingSummary
        );
        table.setItems(meetings);
        table.setFixedCellSize(75);
    }

    public void searchMeeting(ActionEvent event) {
    }

    public void proceedToNew(ActionEvent event) {
        SceneBuilder.Instance.BuildScene("meeting_new");
    }

    public void proceedToDetails(ActionEvent event) {
        SceneBuilder.Instance.BuildScene("meeting_info");
    }
}
