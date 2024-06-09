package principal;

import archivos.LecturaArchivos;
import filtros.filtroAnio;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.stage.StageStyle;

/**
 * JavaFX App
 */
public class App extends Application {
    
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        try{
            scene = new Scene(loadFXML("inicio").load(), 925, 580);
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
        }catch(Exception e){
            System.out.println("ERROR FATAL");
        }
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml).load());
    }

    public static FXMLLoader loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader;
    }

    public static void main(String[] args) {
        launch();
    }

}