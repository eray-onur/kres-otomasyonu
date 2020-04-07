package meltem;

import javafx.application.Application;
import javafx.stage.Stage;
import meltem.enums.LogType;
import meltem.services.data_access.concrete.ClassroomDataService;
import meltem.services.data_access.concrete.StudentDataService;
import meltem.services.data_access.concrete.TeacherDataService;
import meltem.services.data_access.concrete.UserDataService;
import meltem.services.logging.Logger;
import meltem.services.SceneBuilder;

public class Main extends Application {
//    public static UserDataService userDataService = new UserDataService();
//    public static StudentDataService studentDataService = new StudentDataService();
//    public static ClassroomDataService classroomDataService = new ClassroomDataService();
//    public static TeacherDataService teacherDataService = new TeacherDataService();

    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneBuilder sb = new SceneBuilder(primaryStage);
        Logger.Log(LogType.Debug, this.getClass().getSimpleName());
        sb.BuildScene("login");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
