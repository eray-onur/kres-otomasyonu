package meltem.view_models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import meltem.models.Classroom;
import meltem.models.Course;
import meltem.models.Student;
import meltem.services.logging.Logger;

import java.util.ArrayList;
import java.util.List;

public class ClassroomViewModel {
    public Classroom classroom;

    public ObservableValue<SimpleIntegerProperty> classroomId;
    public ObservableValue<SimpleStringProperty> classroomName;
    public ObservableValue<SimpleIntegerProperty> capacity;

    public ClassroomViewModel (
            int id,
            String name,
            int capacity
    ) {
        try {
            this.classroomId = (ObservableValue) new SimpleIntegerProperty(id);
            this.classroomName = (ObservableValue) new SimpleStringProperty(name);
            this.capacity = (ObservableValue) new SimpleIntegerProperty(capacity);

            this.classroom = new Classroom(id, name, capacity);
        }
        catch(NullPointerException ex) {
            Logger.LogError(ex.toString());
        }
    }

    public CourseViewModel[] courses = new CourseViewModel[]{
            new CourseViewModel(1, "Sabah Jimnastiği", "Neşe Sönmez", 2),
            new CourseViewModel(1, "Türkçe", "Sema Yirun", 1),
            new CourseViewModel(1, "Çizim", "Büşra Özel", 2),
            new CourseViewModel(1, "Serbest Oyun", "Hülya Özdin",2),
            new CourseViewModel(1, "İlgili Köşelerinde Oyun", "Seher Sedef Kurubaş",2),
    };

}
