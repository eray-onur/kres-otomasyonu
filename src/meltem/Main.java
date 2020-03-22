package meltem;

import javafx.application.Application;
import javafx.stage.Stage;
import meltem.enums.LogType;
import meltem.services.logging.Logger;
import meltem.services.SceneBuilder;

public class Main extends Application {

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
