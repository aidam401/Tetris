package polygons;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

/**
 *
 * @author Adam_Tomasek
 */
public abstract class Structure {

    protected double actualX;
    protected double actualY;

    private double pivotX;
    private double pivotY;

    private boolean pinned = false;

    protected Collection<Block> polygons;

    protected Structure(double startX, double startY, double pivotX, double pivotY) {

        this.actualX = startX;
        this.actualY = startY;

        this.pivotX = pivotX;
        this.pivotY = pivotY;
    }

    public void pinn() {
        this.pinned = true;
    }

    public boolean isPinned() {
        return this.pinned;
    }

    public void moveDown() {
        if (!pinned) {
            this.polygons.forEach(bl -> bl.moveDown());
            this.actualY += 25;

        }
    }

    public void moveRight() {
        if (!pinned) {
            this.polygons.forEach(bl -> bl.moveRight());
            this.actualX += 25;

        }
    }

    public void moveLeft() {
        if (!pinned) {
            this.polygons.forEach(bl -> bl.moveLeft());
            this.actualX -= 25;

        }
    }

    public ArrayList<Point2D> getPointsAfterRotation(int addAngle, Pane pane) {
        ArrayList<Point2D> output = new ArrayList<Point2D>();
        rotate(90, pane);
        this.polygons.forEach((block) -> output.add(block.getPoints()));
        rotate(-90, pane);
        return output;

    }

    public void rotate(int angle, Pane pane) {
        this.polygons.forEach(bl -> {

            ObservableList<Transform> trans = bl.getPolygon().getTransforms();
            Rotate rot2 = new Rotate(-angle, bl.getPoints().getX() + (bl.getWIDTH() / 2), bl.getPoints().getY() + (bl.getHEIGHT() / 2));
            bl.recalculateByRotation(rot2);
            Rotate rot1 = new Rotate(angle, this.actualX + pivotX, this.actualY + pivotY);
            bl.recalculateByRotation(rot1);

//            K??d k prezentaci            
//            Point2D realPoint = new Point2D(bl.getPoints().getX(), bl.getPoints().getY());
//            Polygon npl = new Polygon(realPoint.getX(), realPoint.getY(),
//                    realPoint.getX() + 5, realPoint.getY(),
//                    realPoint.getX() + 5, realPoint.getY() + 5,
//                    realPoint.getX(), realPoint.getY() + 5);
//            npl.setFill(Color.GREEN);
//            pane.getChildren().add(npl);
        });

    }

    public Collection<Block> getPolygons() {
        return polygons;
    }

    public void setPolygons(Collection<Block> polygons) {
        this.polygons = polygons;
    }

    public abstract void create();

    public double getActualX() {
        return this.actualX;
    }

    public double getActualY() {
        return this.actualY;
    }

    public Point2D[] getPositions() {

        return this.polygons
                .stream()
                .map(bl -> bl.getPoints())
                .toArray(Point2D[]::new);

    }

    public Group getStructure() {

        return new Group(this.polygons
                .stream()
                .map(bl -> bl.getPolygon())
                .collect(Collectors.toList()));

    }

}
