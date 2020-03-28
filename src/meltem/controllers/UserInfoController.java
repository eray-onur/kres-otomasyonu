package meltem.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import meltem.models.RouteData;
import meltem.models.Student;
import meltem.models.User;
import meltem.services.SceneBuilder;
import meltem.services.logging.Logger;
import meltem.view_models.StudentViewModel;
import meltem.view_models.UserViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserInfoController implements Initializable {
    User user = new User(
            1,
            "Sema Yirun",
            "*******",
            (short) 1
    );
    @FXML
    public Text txtUserId;
    @FXML
    public Text txtUserName;
    @FXML
    public Text txtPw;
    @FXML
    public ChoiceBox userAuth;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User user = new UserViewModel(1, "sema_yirun", "1", 1).user;
        userAuth.setItems(FXCollections.observableArrayList(
                "Yonetici", "Sinif Ogretmeni", "Brans Ders Ogretmeni"
        ));
        txtUserId.setText(String.valueOf(user.getUserId()));
        txtUserName.setText(user.getUserName());
        txtPw.setText(user.getPassword());
        userAuth.setValue(setChoiceBoxValue());

    }

    public String setChoiceBoxValue() {
        switch(user.getUserAuth()) {
            case 1:
                return "Yonetici";
            case 2:
                return "Sinif Ogretmeni";
            case 3:
                return "Brans Ogretmeni";
            default:
                return "Yetkisiz";
        }
    }

    public void goBack() throws IOException {
        SceneBuilder.Instance.BuildScene("user_list");
    }
    public void update() throws IOException {
        SceneBuilder.Instance.BuildScene("user_edit", new RouteData(1, "user"));
    }
    public void delete() throws IOException {
        Logger.LogDebug("DELETE!");
        SceneBuilder.Instance.BuildScene("user_list");
    }
}