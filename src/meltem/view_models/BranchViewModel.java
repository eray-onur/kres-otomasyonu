package meltem.view_models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import meltem.models.Branch;
import meltem.models.User;
import meltem.services.logging.Logger;

import java.util.ArrayList;
import java.util.List;

public class BranchViewModel{
    public Branch branch;

    public ObservableValue<SimpleStringProperty> nBranchCourse;
    public ObservableValue<SimpleStringProperty> nBranchTeacherFullName;
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

    public BranchViewModel (
            String course,
            String teacher
    ) {
        try {
            this.nBranchCourse = (ObservableValue) new SimpleStringProperty(course);
            this.nBranchTeacherFullName = (ObservableValue) new SimpleStringProperty(teacher);

            this.branch = new Branch(course, teacher);
            this.branch.branchId = 1;
            Logger.LogDebug(this.branch.nBranchCourse);
        }
        catch(NullPointerException ex) {
            Logger.LogError(ex.toString());
        }
    }

}
