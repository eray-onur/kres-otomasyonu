package meltem.controllers;

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
import meltem.view_models.StudentViewModel;
import meltem.view_models.TeacherViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TeachersController implements Initializable {
    public ObservableList<TeacherViewModel> teachers = FXCollections.observableArrayList();
    @FXML
    public Button btnEdit;
    public TextField txtTeacherId;
    public Button btnAdd;
    private int selectedId;
    @FXML
    private TableView<TeacherViewModel> table = new TableView<TeacherViewModel>();

    public void clickItem(MouseEvent mouseEvent) {
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedId = table.getSelectionModel().getSelectedItem().teacher.getTeacherId();
                Logger.LogDebug(String.valueOf(selectedId));
                if(selectedId != 0) {
                    btnEdit.setDisable(false);
                }
            }
        });
    }

    public void findTeacher(ActionEvent event) throws IOException {
        int teacherId = Integer.parseInt(txtTeacherId.getText());
        if(teacherId != 0) {
            SceneBuilder.Instance.BuildScene("teacher_info", new RouteData(teacherId, "teacher"));
        }
    }

    public void goBack(ActionEvent event) throws IOException {
        SceneBuilder.Instance.BuildScene("search_page");
    }

    public void addData(ActionEvent event) throws IOException {
        SceneBuilder.Instance.BuildScene("teacher_new");
    }

    public void proceedToEdit(ActionEvent event) throws IOException {
        SceneBuilder.Instance.BuildScene("teacher_edit", new RouteData(selectedId, "teacher"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //teachers.add(new TeacherViewModel(1, "Sema", "Yirun", "s.yirun@abc.com", (byte)0));
        //teachers.add(new TeacherViewModel(1, "Jane", "Doe", "j.doe@abc.com", (byte)1));

        btnEdit.setDisable(true);
        table.setEditable(true);
        // First Name
        TableColumn<TeacherViewModel, SimpleStringProperty> firstNameCol = new TableColumn<>("Öğretmen Adı");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                teacher -> teacher.getValue().teacherName
        );
        // Last Name
        TableColumn<TeacherViewModel, SimpleStringProperty> lastNameCol = new TableColumn<>("Öğretmen Soyadı");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                teacher -> teacher.getValue().teacherLastName
        );
        // Orientation Start
        TableColumn<TeacherViewModel, SimpleStringProperty> emailCol = new TableColumn<>("Öğretmen Email'ı");
        emailCol.setMinWidth(250);
        emailCol.setCellValueFactory(
                teacher -> teacher.getValue().teacherEmail
        );
        // Orientation End
        TableColumn<TeacherViewModel, SimpleStringProperty> typeCol = new TableColumn<>("Öğretmen Tipi");
        typeCol.setMinWidth(200);
        typeCol.setCellValueFactory(
                teacher -> teacher.getValue().teacherType
        );


        table.setItems(teachers);
        table.getColumns().addAll(
                firstNameCol,
                lastNameCol,
                emailCol,
                typeCol
        );
    }
}
