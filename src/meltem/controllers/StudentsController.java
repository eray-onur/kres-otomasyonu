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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import meltem.models.RouteData;
import meltem.models.Student;
import meltem.services.SceneBuilder;
import meltem.services.logging.Logger;
import meltem.view_models.StudentViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//new GregorianCalendar(2020,0,31)

public class StudentsController implements Initializable {
    int selectedId = 0;
    @FXML
    public Button btnEdit;
    @FXML
    public TextField txtStudentId;
    public void findStudent() throws IOException {
        int studentId = Integer.parseInt(txtStudentId.getText());
        if(studentId != 0) {
            SceneBuilder.Instance.BuildScene("student_info", new RouteData(studentId, "student"));
        }
    }
    @FXML
    private TableView<StudentViewModel> table = new TableView<StudentViewModel>();
    public final ObservableList<StudentViewModel> data = FXCollections.observableArrayList(
            new StudentViewModel(1,
                    "Ali",
                    "Oncul",
                    "23/03/2020",
                    "07/04/2020",
                    "0543 555 4433",
                    "Ahmet",
                    "Oncul",
                    "aoncul76@hotmail.com"
            ),
            new StudentViewModel(1,
                    "Veli",
                    "Turk",
                    "23/03/2020",
                    "07/04/2020",
                    "0543 555 4433",
                    "Huseyin",
                    "Turk",
                    ""
            ),
            new StudentViewModel(1,
                    "Mehmet",
                    "Kaya",
                    "23/03/2020",
                    "07/04/2020",
                    "0543 666 1122",
                    "Nazan",
                    "Ata",
                    "nazan.ata@gmail.com"
            ),
            new StudentViewModel(1,
                    "Abdullah",
                    "Gök",
                    "23/03/2020",
                    "07/04/2020",
                    "0543 222 3399",
                    "Davud",
                    "Gök",
                    ""
            ),
            new StudentViewModel(1,
                    "Atakan",
                    "Irmak",
                    "23/03/2020",
                    "07/04/2020",
                    "0543 545 4433",
                    "Davud",
                    "Oncul",
                    "aoncul76@hotmail.com"
            )
    );
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnEdit.setDisable(true);
        table.setEditable(true);
        // First Name
        TableColumn<StudentViewModel, SimpleStringProperty> firstNameCol = new TableColumn<>("Öğrenci Adı");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                student -> student.getValue().studentName
        );
        // Last Name
        TableColumn<StudentViewModel, SimpleStringProperty> lastNameCol = new TableColumn<>("Öğrenci Soyadı");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                student -> student.getValue().studentLastName
        );
        // Orientation Start
        TableColumn<StudentViewModel, SimpleStringProperty> orientatonStartCol = new TableColumn<>("Oryantasyon Baslangıç Tarihi");
        orientatonStartCol.setMinWidth(250);
        orientatonStartCol.setCellValueFactory(
                student -> student.getValue().orientationStart
        );
        // Orientation End
        TableColumn<StudentViewModel, SimpleStringProperty> orientatonEndCol = new TableColumn<>("Oryantasyon Bitiş Tarihi");
        orientatonEndCol.setMinWidth(200);
        orientatonEndCol.setCellValueFactory(
                student -> student.getValue().orientationEnd
        );
        // Parent Phone Number
        TableColumn<StudentViewModel, SimpleStringProperty> parentNameCol = new TableColumn<>("Veli Adı");
        parentNameCol.setMinWidth(175);
        parentNameCol.setCellValueFactory(
                student -> student.getValue().parentName
        );
        // Parent Phone Number
        TableColumn<StudentViewModel, SimpleStringProperty> parentLnameCol = new TableColumn<>("Veli Soyadı");
        parentLnameCol.setMinWidth(175);
        parentLnameCol.setCellValueFactory(
                student -> student.getValue().parentLastName
        );
        // Parent Phone Number
        TableColumn<StudentViewModel, SimpleStringProperty> parentNumberCol = new TableColumn<>("Veli Telefon No.");
        parentNumberCol.setMinWidth(175);
        parentNumberCol.setCellValueFactory(
                student -> student.getValue().parentNumber
        );
        // Parent Phone Number
        TableColumn<StudentViewModel, SimpleStringProperty> emailCol = new TableColumn<>("Veli Email");
        emailCol.setMinWidth(175);
        emailCol.setCellValueFactory(
                student -> student.getValue().parentEmail
        );


        table.setItems(data);
        table.getColumns().addAll(
                firstNameCol,
                lastNameCol,
                orientatonStartCol,
                orientatonEndCol,
                parentNameCol,
                parentLnameCol,
                parentNumberCol
        );

    }

    public void clickItem(MouseEvent event) {
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedId = table.getSelectionModel().getSelectedItem().student.studentId;
                Logger.LogDebug(String.valueOf(selectedId));
                if(selectedId != 0) {
                    btnEdit.setDisable(false);
                }
            }
        });
    }

    public void addData() throws IOException {
        SceneBuilder.Instance.BuildScene("student_new");
    }

    public void proceedToEdit() throws IOException {
        SceneBuilder.Instance.BuildScene("student_edit", new RouteData(selectedId, "student"));
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        SceneBuilder.Instance.BuildScene("search_page");
    }
}
