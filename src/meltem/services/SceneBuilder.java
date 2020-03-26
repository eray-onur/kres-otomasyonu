package meltem.services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import meltem.enums.LogType;
import meltem.models.RouteData;
import meltem.services.data_access.IDataService;
import meltem.services.data_access.concrete.UserDataService;
import meltem.services.logging.Logger;

import java.io.IOException;
import java.sql.SQLException;

public class SceneBuilder<T> {
    public static RouteData routeData = new RouteData();
    public static SceneBuilder Instance;
    static Stage PrimaryStage;
    private String getSceneName(String sceneName) {
        // return sceneName.getClass().getSimpleName().toLowerCase();
        return sceneName;
    }

    public SceneBuilder(Stage primaryStage) {
        if(SceneBuilder.Instance == null) {
            Instance = this;
        }
        if(SceneBuilder.PrimaryStage == null) {
            SceneBuilder.PrimaryStage = primaryStage;
        }
    }

    public void BuildScene(String sceneName) throws IOException {
        routeData = null;
        Logger.Log(LogType.Debug, String.format("../scenes/%s.fxml", getSceneName(sceneName)));
        Parent root = FXMLLoader.load(getClass().getResource(String.format("../scenes/%s.fxml", getSceneName(sceneName))));
        PrimaryStage.setTitle("Kres Otomasyonu");
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("meltem/styles/main.css");
        scene.getStylesheets().add(
                String.format("meltem/styles/%s.css", sceneName)
        );
        for(String s :scene.getStylesheets()) {
            Logger.Log(LogType.Debug, String.format("Stylesheet %s sayfa ile birlikte yuklendi.", s));
        }
        PrimaryStage.setScene(scene);
    }
    public void BuildScene(String sceneName, RouteData data) throws IOException {
        routeData = data;
        Logger.Log(LogType.Debug, String.format("../scenes/%s.fxml", getSceneName(sceneName)));
        Parent root = FXMLLoader.load(getClass().getResource(String.format("../scenes/%s.fxml", getSceneName(sceneName))));
        PrimaryStage.setTitle("Kres Otomasyonu");
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("meltem/styles/main.css");
        scene.getStylesheets().add(
                String.format("meltem/styles/%s.css", sceneName)
        );
        for(String s :scene.getStylesheets()) {
            Logger.Log(LogType.Debug, String.format("Stylesheet %s sayfa ile birlikte yuklendi.", s));
        }
        PrimaryStage.setScene(scene);
    }
}
