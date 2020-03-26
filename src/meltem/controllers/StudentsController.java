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
                    new StudentViewModel(1, "Ali", "Ozcan" , "27/03/2020", "28/03/2020", "0 555 333 2211"),
                    new StudentViewModel(2, "Veli", "Ozcan" , "27/03/2020", "28/03/2020", "0 555 333 2211"),
                    new StudentViewModel(3, "Ahmet", "Ozcan" , "27/03/2020", "28/03/2020", "0 555 333 2211"),
                    new StudentViewModel(4, "Mehmet", "Ozcan" , "27/03/2020", "28/03/2020", "0 555 333 2211"),
                    new StudentViewModel(5, "Ali", "Ozcan" , "27/03/2020", "28/03/2020", "0 555 333 2211"),
                    new StudentViewModel(6, "Ali", "Ozcan" , "27/03/2020", "28/03/2020", "0 555 333 2211")
    );
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnEdit.setDisable(true);
        table.setEditable(true);
        // First Name
        TableColumn<StudentViewModel, SimpleStringProperty> firstNameCol = new TableColumn<>("Ogrenci Adi");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                student -> student.getValue().studentName
        );
        // Last Name
        TableColumn<StudentViewModel, SimpleStringProperty> lastNameCol = new TableColumn<>("Ogrenci Soyadi");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                student -> student.getValue().studentLastName
        );
        // Orientation Start
        TableColumn<StudentViewModel, SimpleStringProperty> orientatonStartCol = new TableColumn<>("Oryantasyon Baslangic Tarihi");
        orientatonStartCol.setMinWidth(250);
        orientatonStartCol.setCellValueFactory(
                student -> student.getValue().orientationStart
        );
        // Orientation End
        TableColumn<StudentViewModel, SimpleStringProperty> orientatonEndCol = new TableColumn<>("Oryantasyon Bitis Tarihi");
        orientatonEndCol.setMinWidth(200);
        orientatonEndCol.setCellValueFactory(
                student -> student.getValue().orientationEnd
        );
        // Parent Phone Number
        TableColumn<StudentViewModel, SimpleStringProperty> phoneNumberCol = new TableColumn<>("Veli Telefon Numarasi");
        phoneNumberCol.setMinWidth(175);
        phoneNumberCol.setCellValueFactory(
                student -> student.getValue().parentNumber
        );


        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol, orientatonStartCol, orientatonEndCol, phoneNumberCol);

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
