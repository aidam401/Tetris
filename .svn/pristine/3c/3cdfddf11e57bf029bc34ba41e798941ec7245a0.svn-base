package polygons;

import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;

/**
 *
 * @author Adam_Tomasek
 */
public class TypedBlock {

    private static BoxBlur boxblur = new BoxBlur();

    public static Block aBlock(double startX, double startY) {
        return new Block(startX, startY) {
            @Override
            public void setTexture() {
                super.polygon.getStyleClass().add("a-block");
            }
        };
    }

    public static Block bBlock(double startX, double startY) {
        return new Block(startX, startY) {
            @Override
            public void setTexture() {
                super.polygon.getStyleClass().add("b-block");
                boxblur.setHeight(5);
                boxblur.setWidth(5);
                boxblur.setIterations(2);
            }
        };
    }

    public static Block cBlock(double startX, double startY) {
        return new Block(startX, startY) {
            @Override
            public void setTexture() {
                super.polygon.getStyleClass().add("c-block");
            }
        };
    }

    public static Block dBlock(double startX, double startY) {
        return new Block(startX, startY) {
            @Override
            public void setTexture() {
                super.polygon.getStyleClass().add("d-block");
                boxblur.setHeight(5);
                boxblur.setWidth(5);
                boxblur.setIterations(1);
                super.polygon.setEffect(boxblur);
            }
        };
    }

    public static Block eBlock(double startX, double startY) {
        return new Block(startX, startY) {
            @Override
            public void setTexture() {
                super.polygon.getStyleClass().add("e-block");
            }
        };
    }

    public static Block fBlock(double startX, double startY) {
        return new Block(startX, startY) {
            @Override
            public void setTexture() {
                super.polygon.getStyleClass().add("f-block");

                Light.Distant l = new Light.Distant();
                Lighting light = new Lighting();
                light.setDiffuseConstant(1);
                light.setSpecularConstant(0.3);
                light.setSpecularExponent(20.0);
                light.setSurfaceScale(1.5);
                light.setLight(l);
                super.polygon.setEffect(light);
            }
        };
    }

}
