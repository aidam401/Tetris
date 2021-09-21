package menu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.time.LocalDateTime;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Adam_Tomasek
 */
public class MenuController implements Initializable {

    private boolean music = true;
    private boolean sound = true;
    @FXML
    private Button start;

    private MediaPlayer soundtrack;
    @FXML
    private ImageView musicIcon;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setPlayer();

    }

    private void setPlayer() {
        String sountrack = "soundtrack.mp3";

        Media media = new Media(Paths.get("src/soundtrack/" + sountrack).toUri().toString());
        this.soundtrack = new MediaPlayer(media);
    }

    private static String createFile(String cesta) {
        File soubor = new File(cesta.toString());
        try {
            if (soubor.createNewFile()) {
                System.out.println("New file created.");
            }
        } catch (IOException e) {
            System.err.println("Error in file creating - "
                    + e.getMessage());

        }
        return soubor.getAbsolutePath();

    }

    private ArrayList<XYChart.Data> loadData() {
        ArrayList<XYChart.Data> data = new ArrayList<XYChart.Data>();

        String cesta = "src/backup/chart.txt";
        String text = "";

        try {
            FileReader reader = new FileReader(cesta);
            BufferedReader stream = new BufferedReader(reader);
            String radek;
            while ((radek = stream.readLine()) != null) {
                if (radek == System.lineSeparator()) {
                    continue;
                }
                String stringArr[] = radek.split(",");
                data.add(new XYChart.Data(stringArr[0], Integer.parseInt(stringArr[1])));
            }

            reader.close();
        } catch (Exception e) {
            System.err.println("Error in loading - " + e.getMessage());
        }
        return data;
    }

    private void saveData(LocalDateTime date, int score) {
        String cesta = createFile("src/backup/chart.txt");

        try {
            FileWriter writer = new FileWriter(cesta, true);

            writer.append(date + "," + score);
            writer.append(System.lineSeparator());

            writer.close();
        } catch (IOException e) {
            System.err.println("Error in saving " + e.getMessage());
        }

    }

    @FXML
    private void startTrigg(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/game/Game.fxml"));
        Scene scene = new Scene(root);
        if (this.music) {
            soundtrack.play();
        }
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(start.getScene().getWindow());
        stage.setResizable(false);

        stage.setOnCloseRequest((evt) -> {
            Integer score = Integer.parseInt(((Label) scene.lookup("#scoreCounter")).getText());
            if (score > 0) {
                saveData(LocalDateTime.now(), score);
            }

            soundtrack.stop();
        });

        stage.show();
    }

    @FXML
    private void statistTrigg(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/statistics/Statistics.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(start.getScene().getWindow());
        stage.setResizable(false);

        BarChart chart = ((BarChart) scene.lookup("#barChart"));
        chart.setLegendVisible(false);

        for (XYChart.Data data : loadData()) {
            XYChart.Series dataSeries = new XYChart.Series();
            dataSeries.getData().add(data);
            chart.getData().add(dataSeries);
        }

        stage.show();
    }

    @FXML
    private void quitTrigg(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void musicButton(ActionEvent event) {
        this.music = !this.music;
        if (this.music) {
            musicIcon.setImage(new Image(Paths.get("src/images/musicON.png").toUri().toString()));
            soundtrack.play();
        } else {
            musicIcon.setImage(new Image(Paths.get("src/images/musicOFF.png").toUri().toString()));
            soundtrack.stop();
        }
    }

}
