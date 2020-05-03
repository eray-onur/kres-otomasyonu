package meltem.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import meltem.models.RouteData;
import meltem.services.SceneBuilder;
import meltem.services.logging.Logger;
import meltem.view_models.MeetingViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//new GregorianCalendar(2020,0,31)

public class MeetingsController implements Initializable {
    public final ObservableList<MeetingViewModel> data = FXCollections.observableArrayList(
            new MeetingViewModel(1, "Ali Öncül Hakkında", "...", "29/03/2020")
    );
    int selectedId = 0;
    @FXML
    public Button btnEdit;
    @FXML
    public TextField txtMeetingId;
    public void findMeeting() throws IOException {
        int meetingId = Integer.parseInt(txtMeetingId.getText());
        if(meetingId != 0) {
            SceneBuilder.Instance.BuildScene("meeting_info", new RouteData(1, "meeting"));
        }
    }
    @FXML
    private TableView<MeetingViewModel> table = new TableView<MeetingViewModel>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnEdit.setDisable(true);
        table.setEditable(true);
        Logger.LogDebug(data.get(0).meeting.toString());
        // First Name
        TableColumn<MeetingViewModel, SimpleIntegerProperty> userIdCol = new TableColumn<>("Toplantı Numarası");
        userIdCol.setMinWidth(100);
        userIdCol.setCellValueFactory(
                user -> user.getValue().meetingId
        );
        // Last Name
        TableColumn<MeetingViewModel, SimpleStringProperty> usernameCol = new TableColumn<>("Toplantı Başlığı");
        usernameCol.setMinWidth(100);
        usernameCol.setCellValueFactory(
                user -> user.getValue().meetingTitle
        );
        // Orientation Start
        TableColumn<MeetingViewModel, SimpleStringProperty> pwCol = new TableColumn<>("Toplantı Tarihi");
        pwCol.setMinWidth(250);
        pwCol.setCellValueFactory(
                user -> user.getValue().meetingDate
        );

        TableColumn<MeetingViewModel, SimpleStringProperty> userAuthCol = new TableColumn<>("Toplantı Özeti");
        userAuthCol.setMinWidth(250);
        userAuthCol.setCellValueFactory(
                user -> user.getValue().meetingSummary
        );



        table.setItems(data);
        table.getColumns().addAll(userIdCol, usernameCol, pwCol, userAuthCol);

    }

    public void clickItem(MouseEvent event) {
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //selectedId = table.getSelectionModel().getSelectedItem().meeting.meetingId;
                Logger.LogDebug(String.valueOf(selectedId));
                if(selectedId != 0) {
                    btnEdit.setDisable(false);
                }
            }
        });
    }

    public void addData() throws IOException {
        SceneBuilder.Instance.BuildScene("meeting_new");
    }

    public void proceedToInfo() throws IOException {
        SceneBuilder.Instance.BuildScene("meeting_info", new RouteData(1, "meeting"));
    }

    public void proceedToEdit() throws IOException {
        SceneBuilder.Instance.BuildScene("meeting_edit", new RouteData(1, "meeting"));
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        SceneBuilder.Instance.BuildScene("search_page");
    }
}
