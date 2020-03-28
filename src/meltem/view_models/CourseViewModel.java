package meltem.view_models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import meltem.models.Course;
import meltem.models.User;
import meltem.services.logging.Logger;

public class CourseViewModel{
    public Course course;

    public ObservableValue<SimpleIntegerProperty> courseId;
    public ObservableValue<SimpleStringProperty> courseName;
    public ObservableValue<SimpleStringProperty> courseTeacher;
    public ObservableValue<SimpleStringProperty> teacherAuth;

    public StudentViewModel[] studentViewModelList = new StudentViewModel[]{
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
            ),
    };


    public CourseViewModel (
            int id,
            String name,
            String teacher,
            int auth
    ) {
        try {
            this.course = new Course(id, name, teacher, auth);
            this.courseId = (ObservableValue) new SimpleIntegerProperty(id);
            this.courseName = (ObservableValue) new SimpleStringProperty(name);
            this.courseTeacher = (ObservableValue) new SimpleStringProperty(teacher);
            this.teacherAuth = (ObservableValue) new SimpleStringProperty(course.getTrueAuth());
            Logger.LogDebug(this.course.courseName + " is generated!");
        }
        catch(NullPointerException ex) {
            Logger.LogError(ex.toString());
        }
    }

}
