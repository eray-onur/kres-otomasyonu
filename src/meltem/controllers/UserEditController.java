package meltem.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserEditController implements Initializable {
    User user = new User(
            1,
            "Sema Yirun",
            "*******",
            (short) 1
    );
    @FXML
    public Text indicatorId;
    @FXML
    public Text txtUserId;
    @FXML
    public TextField txtUserName;
    @FXML
    public TextField txtPw;
    @FXML
    public Button btnNew;
    @FXML
    public ChoiceBox<String> userAuth;
    private Integer[] auths = new Integer[]{1, 2, 3};
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Main.userDataService.fetchById(SceneBuilder.routeData.id);
        userAuth.setItems(FXCollections.observableArrayList(
                "Yonetici", "Sinif Ogretmeni", "Brans Ders Ogretmeni"
        ));
        if(SceneBuilder.routeData != null) {
            Logger.LogDebug(SceneBuilder.routeData.dataName);
            txtUserId.setText(String.valueOf(user.getUserId()));
            txtUserName.setText(user.getUserName());
            txtPw.setText(user.getPassword());
            userAuth.setValue(setChoiceBoxValue());
            // Dropdown'a stringler atanir.
        } else {
            if(txtUserId != null) {
                txtUserId.setDisable(false);
            }
            if(indicatorId != null) {
                indicatorId.setDisable(false);
            }
        }
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
    public void update() throws IOException {
        SceneBuilder.Instance.BuildScene("user_edit", new RouteData(1, "user"));
    }
    public void delete() throws IOException {
        Logger.LogDebug("DELETE!");
    }
    @FXML
    public void goBack() throws IOException {
        SceneBuilder.Instance.BuildScene("user_list");
    }
    public void changeAuthSelection() {
        userAuth.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number val, Number newVal) {
                        user.setUserAuth((Short) newVal);
                        Logger.LogDebug(String.valueOf(user.getUserAuth()));
                    }
                }
        );
    }
}
