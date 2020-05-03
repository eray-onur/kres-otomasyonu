package meltem.view_models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import meltem.models.Teacher;
import meltem.services.logging.Logger;

public class TeacherViewModel {
    public Teacher teacher;

    public ObservableValue<SimpleIntegerProperty> teacherId;
    public ObservableValue<SimpleStringProperty> teacherName;
    public ObservableValue<SimpleStringProperty> teacherLastName;
    public ObservableValue<SimpleStringProperty> teacherEmail;
    public ObservableValue<SimpleStringProperty> teacherType;

    public TeacherViewModel (
            int id,
            String name,
            String lname,
            String phone,
            String email,
            int type
    ) {
        try {
            this.teacher = new Teacher(id, name, lname, phone, email, type);
            this.teacherId = (ObservableValue) new SimpleIntegerProperty(id);
            this.teacherName = (ObservableValue) new SimpleStringProperty(name);
            this.teacherLastName = (ObservableValue) new SimpleStringProperty(lname);
            this.teacherEmail = (ObservableValue) new SimpleStringProperty(email);
            switch (this.teacher.getTeacherAuth()) {
                case 0:
                    this.teacherType = (ObservableValue) new SimpleStringProperty("Sınıf Öğretmeni");
                    break;
                case 1:
                    this.teacherType = (ObservableValue) new SimpleStringProperty("Branş Ders Öğretmeni");
                    break;
            }
        }
        catch(NullPointerException ex) {
            Logger.LogError(ex.toString());
        }
    }
}
