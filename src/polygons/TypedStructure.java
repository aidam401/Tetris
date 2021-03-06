package polygons;

import java.util.Arrays;
import static polygons.TypedBlock.*;

/**
 *
 * @author Adam_Tomasek
 */
public final class TypedStructure {

    public static Structure typeAStructure() {
        return new Structure(125, 0, 37.5, 12.5) {
            @Override
            public void create() {
                super.polygons = Arrays.asList(
                        aBlock(this.actualX, this.actualY),
                        aBlock(this.actualX + 25, this.actualY),
                        aBlock(this.actualX + 25, this.actualY - 25),
                        aBlock(this.actualX + 50, this.actualY));
            }
        };
    }

    public static Structure typeBStructure() {
        return new Structure(125, 0, 37.5, 12.5 - 25) {
            @Override
            public void create() {
                super.polygons = Arrays.asList(
                        bBlock(this.actualX, this.actualY - 25),
                        bBlock(this.actualX + 25, this.actualY - 25),
                        bBlock(this.actualX + 50, this.actualY - 25),
                        bBlock(this.actualX + 50, this.actualY)
                );
            }
        };
    }

    public static Structure typeCStructure() {
        return new Structure(125, 0, 37.5, 12.5) {
            @Override
            public void create() {
                super.polygons = Arrays.asList(
                        cBlock(this.actualX, this.actualY - 25),
                        cBlock(this.actualX + 25, this.actualY - 25),
                        cBlock(this.actualX + 25, this.actualY),
                        cBlock(this.actualX + 50, this.actualY)
                );
            }

        };
    }

    public static Structure typeDStructure() {
        return new Structure(125, 0, 25, 25) {
            @Override
            public void create() {
                super.polygons = Arrays.asList(
                        dBlock(this.actualX, this.actualY),
                        dBlock(this.actualX + 25, this.actualY),
                        dBlock(this.actualX + 25, this.actualY + 25),
                        dBlock(this.actualX, this.actualY + 25)
                );
            }

        };
    }

    public static Structure typeEStructure() {
        return new Structure(125, 0, 37.5, 12.5) {
            @Override
            public void create() {
                super.polygons = Arrays.asList(
                        eBlock(this.actualX, this.actualY),
                        eBlock(this.actualX + 25, this.actualY),
                        eBlock(this.actualX + 25, this.actualY - 25),
                        eBlock(this.actualX + 50, this.actualY - 25)
                );
            }

        };
    }

    public static Structure typeFStructure() {
        return new Structure(125, 0, 50, 0) {
            @Override
            public void create() {
                super.polygons = Arrays.asList(
                        fBlock(this.actualX, this.actualY),
                        fBlock(this.actualX + 25, this.actualY),
                        fBlock(this.actualX + 50, this.actualY),
                        fBlock(this.actualX + 75, this.actualY)
                );
            }

        };
    }

}
