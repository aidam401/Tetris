package semestralka;

import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

/**
 *
 * @author Adam_Tomasek
 */
public class Semestrálka extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/menu/Menu.fxml"));
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
