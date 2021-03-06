package polygons;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

/**
 *
 * @author Adam_Tomasek
 */
public abstract class Block {

    protected Polygon polygon = new Polygon();

    private final double WIDTH = 25;
    private final double HEIGHT = 25;

    public Block(double startX, double startY) {

        create(startX, startY);
        setTexture();
    }

    public void recalculateByRotation(Rotate rot) {
        ObservableList<Double> fullPoints = this.polygon.getPoints();
        ObservableList<Double> realFullPoints = FXCollections.observableArrayList();

        for (int i = 0; i < fullPoints.size() - 1; i += 2) {
            Point2D realPoint = rot.transform(
                    new Point2D(fullPoints.get(i),
                            fullPoints.get(i + 1)));

            realFullPoints.addAll((double) Math.round(realPoint.getX()),
                    (double) Math.round(realPoint.getY()));
        }

        this.polygon.getPoints().clear();
        this.polygon.getPoints().addAll(realFullPoints);

    }

    public Point2D getPoints() {
        ObservableList<Double> fullPoints = this.polygon.getPoints();

        return new Point2D(fullPoints.get(0), fullPoints.get(1));
    }

    public Polygon getPolygon() {
        return polygon;
    }

    private void create(double x, double y) {
        this.polygon.getPoints().addAll(new Double[]{
            x, y,
            x + this.WIDTH, y,
            x + this.WIDTH, y + this.HEIGHT,
            x, y + this.HEIGHT});

    }

    public void moveDown() {
        ObservableList<Double> polPoints = this.polygon.getPoints();

        for (int i = 0; i < polPoints.size(); i++) {
            if (i % 2 == 1) {
                polPoints.set(i, polPoints.get(i) + 25);
            }
        }

    }

    public void moveRight() {
        ObservableList<Double> polPoints = this.polygon.getPoints();
        for (int i = 0; i < polPoints.size(); i++) {
            if (i % 2 == 0) {
                polPoints.set(i, polPoints.get(i) + 25);
            }
        }

    }

    public void moveLeft() {
        ObservableList<Double> polPoints = this.polygon.getPoints();
        for (int i = 0; i < polPoints.size(); i++) {
            if (i % 2 == 0) {
                polPoints.set(i, polPoints.get(i) - 25);
            }
        }

    }

    public abstract void setTexture();

    public double getWIDTH() {
        return WIDTH;
    }

    public double getHEIGHT() {
        return HEIGHT;
    }

}
