package game;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Objects;

import java.util.Random;
import java.util.ResourceBundle;

import java.util.stream.Collectors;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javafx.util.Duration;

import polygons.Structure;

import polygons.TypedStructure;

/**
 *
 * @author Adam_Tomasek
 */
public class GameController implements Initializable {

    @FXML
    private Pane gamePane;
    @FXML
    private Label lineCounter;
    @FXML
    private Label topCounter;
    @FXML
    private Label scoreCounter;
    @FXML
    private Label aCounter;
    @FXML
    private Label bCounter;
    @FXML
    private Label cCounter;
    @FXML
    private Label dCounter;
    @FXML
    private Label eCounter;

    @FXML
    private Label fCounter;

    @FXML
    private Button hiddenButton;

    @FXML
    private StackPane nextPane;

    private Structure fallingStructure;
    private Structure nextStructure;

    private ArrayList<Structure> structureList = new ArrayList();

    private Boolean[][] grid = new Boolean[10][20];

    private Duration fallTick = new Duration(750);

    private Timeline fallingCounter = fallingCounter();
    @FXML
    private AnchorPane exitAnchor;
    @FXML
    private Button exitButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Orezavaci obdelnik
        gamePane.setClip(new Rectangle(250, 500));

        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                this.grid[x][y] = false;
            }
        }
        setControls();
        startFalling();
    }

    private void setControls() {
        hiddenButton.setOnKeyPressed((event) -> {
            switch (event.getCode()) {
                case UP:
                    rotate();

                    break;
                case RIGHT:
                    moveRight();

                    break;

                case LEFT:

                    moveLeft();
                    break;

                case DOWN:
                    moveDown();
                    break;
            }

        });
    }

    private void pinnStructure() {
        try {

            Arrays.asList(this.fallingStructure.getPositions())
                    .stream()
                    .forEach((points) -> {
                        this.grid[(int) points.getX() / 25][(int) points.getY() / 25] = true;
                    });
            this.fallingStructure.pinn();
        } catch (Exception e) {
        }
    }

    private void moveRight() {
        Point2D[] structPoints = this.fallingStructure.getPositions();
        Point2D validPointsAfterMove[]
                = Arrays.asList(structPoints)
                        .stream()
                        .filter((points) -> {
                            int movedX = (int) points.getX() / 25 + 1;
                            int movedY = (int) points.getY() / 25;

                            if (movedX >= 0 && movedX < this.grid.length
                                    && movedY >= 0 && movedY < this.grid[0].length) {

                                return !this.grid[movedX][movedY];
                            } else {
                                return false;
                            }

                        })
                        .toArray(Point2D[]::new);

        if (validPointsAfterMove.length == structPoints.length) {
            this.fallingStructure.moveRight();

        }
    }

    private void moveLeft() {
        Point2D[] structPoints = this.fallingStructure.getPositions();
        Point2D validPointsAfterMove[]
                = Arrays.asList(structPoints)
                        .stream()
                        .filter((points) -> {
                            int movedX = (int) points.getX() / 25 - 1;
                            int movedY = (int) points.getY() / 25;

                            if (movedX >= 0 && movedX < this.grid.length
                                    && movedY >= 0 && movedY < this.grid[0].length) {

                                return !this.grid[movedX][movedY];
                            } else {
                                return false;
                            }

                        })
                        .toArray(Point2D[]::new);
        if (validPointsAfterMove.length == structPoints.length) {
            this.fallingStructure.moveLeft();

        }
    }

    private void moveDown() {
        Point2D[] structPoints = this.fallingStructure.getPositions();
        Point2D validPointsAfterMove[]
                = Arrays.asList(structPoints)
                        .stream()
                        .filter((points) -> {

                            int movedX = (int) points.getX() / 25;
                            int movedY = (int) points.getY() / 25 + 1;

                            if (movedY < 0) {
                                return true;
                            } else if (movedX >= 0 && movedX < this.grid.length
                                    && movedY < this.grid[0].length) {
                                return !this.grid[movedX][movedY];
                            } else {
                                return false;
                            }

                        })
                        .toArray(Point2D[]::new);

        if (validPointsAfterMove.length == structPoints.length) {
            this.fallingStructure.moveDown();
        } else {
            pinnStructure();
            this.hiddenButton.setDisable(true);
            if (isEnd()) {
                return;
            }
            this.hiddenButton.setDisable(false);
            clearLine();
            raiseBlockCounter();
            startFalling();

        }
    }

    private void rotate() {
        ArrayList<Point2D> structPoints = this.fallingStructure.getPointsAfterRotation(90, this.gamePane);
        Point2D validPointsAfterRotation[]
                = structPoints
                        .stream()
                        .filter((points) -> {

                            int rotatedX = (int) points.getX() / 25;
                            int rotatedY = (int) points.getY() / 25;

                            if (rotatedX >= 0 && rotatedX < this.grid.length
                                    && rotatedY >= 0 && rotatedY < this.grid[0].length) {

                                return !this.grid[rotatedX][rotatedY];
                            } else {
                                return false;
                            }

                        })
                        .toArray(Point2D[]::new);

        if (validPointsAfterRotation.length == structPoints.size()) {

            this.fallingStructure.rotate(90, this.gamePane);
        }
    }

    private boolean isEnd() {
        for (int x = 0; x < this.grid.length; x++) {
            if (this.grid[x][0]) {
                hiddenButton.setDisable(true);
                exitAnchor.setVisible(true);
                exitAnchor.setDisable(false);
                return true;
            }
        }
        return false;

    }

    private void redraw() {
        this.gamePane.getChildren().clear();
        structureList.forEach((structure) -> {
            this.gamePane.getChildren().add(structure.getStructure());
        });
    }

    private void clearLine() {
        for (int mainY = 0; mainY < this.grid[0].length; mainY++) {
            byte lineCounter = 0;
            for (Boolean[] grid1 : grid) {
                lineCounter += (grid1[mainY] == true) ? 1 : 0;
            }
            if (lineCounter == this.grid.length) {
                raiseScore();
                raiseLine();
                fallSpeedUp();

                //Grafika
                final double yAxis = (double) mainY * 25;
                for (Structure structure : structureList) {

                    //Smazani radku
                    structure.setPolygons(
                            structure.getPolygons()
                                    .stream()
                                    .filter((blok) -> {
                                        return blok.getPoints().getY() != yAxis;
                                    })
                                    .collect(Collectors.toList())
                    );
                    //Posunuti radku
                    structure.getPolygons()
                            .forEach((blok) -> {
                                if (blok.getPoints().getY() < yAxis) {
                                    blok.moveDown();
                                }
                            });
                    redraw();

                }
                //Smazani radku ve vnitrnim poli
                for (Boolean[] grid1 : grid) {
                    grid1[mainY] = false;
                }

                //Posunuti radku ve vnitrnim poli
                for (Boolean[] grid1 : grid) {

                    for (int y = mainY - 1; y >= 0; y--) {
                        grid1[y + 1] = grid1[y];
                    }
                }
            }

        }
    }

    private void raiseBlockCounter() {
        String className = this.fallingStructure.getClass().getName();
        switch (className.substring(className.length() - 1)) {
            case "1":
                aCounter.setText(String.format("%03d", Integer.parseInt(aCounter.getText()) + 1));
                break;
            case "2":
                bCounter.setText(String.format("%03d", Integer.parseInt(bCounter.getText()) + 1));
                break;
            case "3":
                cCounter.setText(String.format("%03d", Integer.parseInt(cCounter.getText()) + 1));
                break;
            case "4":
                dCounter.setText(String.format("%03d", Integer.parseInt(dCounter.getText()) + 1));
                break;
            case "5":
                eCounter.setText(String.format("%03d", Integer.parseInt(eCounter.getText()) + 1));
                break;
            case "6":
                fCounter.setText(String.format("%03d", Integer.parseInt(fCounter.getText()) + 1));

        }
    }

    private void raiseScore() {
        scoreCounter.setText(String.format("%06d", Integer.parseInt(scoreCounter.getText()) + 100));
        if (Integer.parseInt(topCounter.getText()) < Integer.parseInt(scoreCounter.getText())) {
            topCounter.setText(scoreCounter.getText());
        }
    }

    private void raiseLine() {
        lineCounter.setText(String.format("%03d", Integer.parseInt(lineCounter.getText()) + 1));
    }

    private void fallSpeedUp() {

        fallingCounter.stop();
        if (this.fallTick.toMillis() > 100) {
            this.fallTick = this.fallTick.add(new Duration(-20));
            fallingCounter = fallingCounter();
        }
        fallingCounter.play();

    }

    private void drawNext() {

        this.nextStructure.create();
        Group struc = this.nextStructure.getStructure();
        nextPane.setAlignment(Pos.CENTER);
        nextPane.getChildren().add(struc);

    }

    private Timeline fallingCounter() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "",
                KeyCode.DOWN, true, true, true, true);

        Timeline timeLine = new Timeline(
                new KeyFrame(
                        this.fallTick,
                        n -> {
                            hiddenButton.fireEvent(keyEvent);
                        }
                )
        );
        timeLine.setCycleCount(Animation.INDEFINITE);

        return timeLine;
    }

    private Structure getRandomStructure() {
        Structure structure = null;
        //TODO: edit random
        // Vyb??r?? n??hodn?? metody z classy TypedStructure (ka??d?? vrac?? n??jakou strukturu)
        Method strucGets[] = TypedStructure.class.getDeclaredMethods();
        Method randomGetr = strucGets[new Random().nextInt(strucGets.length)];

        try {
            structure = (Structure) randomGetr.invoke(null);
        } catch (IllegalAccessException
                | IllegalArgumentException
                | InvocationTargetException ex) {
            System.err.print("Vnit??n?? chyba - " + ex.getLocalizedMessage());
        }
        return structure;

    }

    private void generateFallingStructure() {
        if (Objects.isNull(this.nextStructure)) {
            this.nextStructure = getRandomStructure();
            this.fallingStructure = getRandomStructure();
        } else {
            this.fallingStructure = this.nextStructure;
            this.nextStructure = getRandomStructure();
        }
        drawNext();
        this.fallingStructure.create();
        structureList.add(fallingStructure);
        gamePane.getChildren().add(fallingStructure.getStructure());
    }

    private void startFalling() {
        if (Objects.nonNull(this.fallingStructure)) {
            this.nextPane.getChildren().clear();
        }
        generateFallingStructure();

        this.fallingCounter.play();
    }

    @FXML
    private void exit(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.getOnCloseRequest()
                .handle(
                        new WindowEvent(
                                stage,
                                WindowEvent.WINDOW_CLOSE_REQUEST
                        )
                );
        stage.close();

    }

}
