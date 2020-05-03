package meltem.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import meltem.models.User;
import meltem.services.SceneBuilder;
import meltem.services.data_access.concrete.UserRepository;

public class ForgotPasswordController {
    @FXML
    public PasswordField txtPassword;
    @FXML
    public PasswordField txtPasswordConfirm;
    @FXML
    public TextField txtUserName;


    @FXML
    public void confirmPassword(ActionEvent event) {
        if(!txtPassword.getText().equals(txtPasswordConfirm.getText()) || txtPassword.getText().length() < 4 || txtUserName.getText().equals("")) {
            SceneBuilder.Instance.BuildWarningModal("modal_invalid_input",
                    "Geçersiz değerler girdiniz. ( Şifreler uyuşmalı ve karakter sayısı 4'ten büyük olmalı. )",
                    event
            );
        }
        else {
            if(UserRepository.Instance.fetchByUsername(txtUserName.getText()) != null) {
                User user = UserRepository.Instance.fetchByUsername(txtUserName.getText());
                user.setPassword(txtPassword.getText());
                UserRepository.Instance.UpdateByUsername(user, user.getUserName());
                SceneBuilder.Instance.BuildWarningModal("modal_success_pwchange", "Warning", event);
            } else {
                SceneBuilder.Instance.BuildWarningModal("modal_notfound_user", "Warning", event);
            }
        }
    }

    @FXML
    public void goBack(ActionEvent event) {
        SceneBuilder.Instance.BuildScene("login");
    }
}
