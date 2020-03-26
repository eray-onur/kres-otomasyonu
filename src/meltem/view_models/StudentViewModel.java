package meltem.view_models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import meltem.models.Student;
import meltem.services.logging.Logger;

public class StudentViewModel {
    public Student student;

    public ObservableValue<SimpleIntegerProperty> studentId;
    public ObservableValue<SimpleStringProperty> studentName;
    public ObservableValue<SimpleStringProperty> studentLastName;
    public ObservableValue<SimpleStringProperty> orientationStart;
    public ObservableValue<SimpleStringProperty> orientationEnd;
    public ObservableValue<SimpleStringProperty> parentNumber;

    public StudentViewModel (
            int id,
            String name,
            String lname,
            String orientationStart,
            String orientationEnd,
            String parentNumber
    ) {
        try {
            this.studentId = (ObservableValue) new SimpleIntegerProperty(id);
            this.studentName = (ObservableValue) new SimpleStringProperty(name);
            this.studentLastName = (ObservableValue) new SimpleStringProperty(lname);
            this.orientationStart = (ObservableValue) new SimpleStringProperty(orientationStart);
            this.orientationEnd = (ObservableValue) new SimpleStringProperty(orientationEnd);
            this.parentNumber = (ObservableValue) new SimpleStringProperty(parentNumber);

            this.student = new Student(id, name, lname, orientationStart, orientationEnd);
        }
        catch(NullPointerException ex) {
            Logger.LogError(ex.toString());
        }
    }

}
