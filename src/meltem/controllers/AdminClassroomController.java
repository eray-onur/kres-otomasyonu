package meltem.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import meltem.models.Classroom;
import meltem.models.RouteData;
import meltem.models.Student;
import meltem.models.User;
import meltem.services.SceneBuilder;
import meltem.services.data_access.concrete.ClassroomRepository;
import meltem.services.logging.Logger;
import meltem.view_models.ClassroomViewModel;
import meltem.view_models.CourseViewModel;
import meltem.view_models.StudentViewModel;
import meltem.view_models.UserViewModel;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminClassroomController implements Initializable {
    private int selectedId = -1;
    @FXML
    public Button btnShowClassInfo;
    @FXML
    public TableView<ClassroomViewModel> classroomTable;
    @FXML
    public TableColumn<ClassroomViewModel, SimpleIntegerProperty> colClassId;
    @FXML
    public TableColumn<ClassroomViewModel, SimpleStringProperty> colClassName;
    @FXML
    public TableColumn<ClassroomViewModel, SimpleStringProperty> colClassTeacher;
    @FXML
    public TableColumn<ClassroomViewModel, SimpleIntegerProperty> colClassCapacity;

    public ObservableList<ClassroomViewModel> classroomTableList;

    public ArrayList<ClassroomViewModel> classroomList = new ArrayList<>();

    private List<Classroom> classroomModels = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        classroomTable.setOnMouseClicked(v -> getViewModel());

        classroomModels = ClassroomRepository.Instance.fetchAll();

        classroomTableList = FXCollections.observableArrayList(
            fetchAllModels()
        );
        // Classroom ID
        colClassId.setCellValueFactory(
                classroom -> classroom.getValue().classroomId
        );
        // Classroom Name
        colClassName.setCellValueFactory(
                classroom -> classroom.getValue().classroomName
        );
        // Classroom Teacher Fullname
        colClassTeacher.setCellValueFactory(
                classroom -> classroom.getValue().classroomTeacherFullName
        );
        // Classroom Capacity
        colClassCapacity.setCellValueFactory(
                classroom -> classroom.getValue().capacity
        );

        classroomTable.setItems(classroomTableList);

    }

    public void getViewModel() {
        if(classroomTable.getSelectionModel().getSelectedItem() != null) {
            btnShowClassInfo.setDisable(false);
            ClassroomViewModel vm = classroomTable.getSelectionModel().getSelectedItem();
            AdminClassroomInfoController.ClassroomId = vm.classroom.getClassroomId();
            AdminClassroomInfoController.ClassroomTeacherFullName = vm.classroom.getClassroomTeacherName() + " " + vm.classroom.getClassroomTeacherLastName();
            Logger.LogDebug(AdminClassroomInfoController.ClassroomTeacherFullName + " IS The FULLNAME!");
        }
    }

    public ArrayList<ClassroomViewModel> fetchAllModels() {
        for (Classroom classroom: classroomModels) {
            classroomList.add(new ClassroomViewModel(classroom));
        }
        return classroomList;
    }

    public void goBack() throws IOException {
        SceneBuilder.Instance.BuildScene("search_page");
    }
    public void update() throws IOException {
        SceneBuilder.Instance.BuildScene("classroom_edit", new RouteData(1, "user"));
    }
    public void delete() throws IOException {
        Logger.LogDebug("DELETE!");
        SceneBuilder.Instance.BuildScene("classroom_list");
    }

    public void findClassroom(ActionEvent event) {
    }

    public void goBackToClassroomAdmin(ActionEvent event) {
        SceneBuilder.Instance.BuildScene("attendance_classroom_admin");
    }
    public void addTeacher() throws IOException {
        SceneBuilder.Instance.BuildScene("teacher_new_admin");
    }

    public void proceedToEdit() throws IOException {
        SceneBuilder.Instance.BuildScene("classroom_edit", new RouteData(1, "classroom"));
    }



    public void proceedToInfo() {
        SceneBuilder.Instance.BuildScene("classroom_info_admin");
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        SceneBuilder.Instance.BuildScene("search_page");
    }

    public void clickItem(MouseEvent mouseEvent) {
    }
}
