package lighting;

import primitives.Color;
import primitives.Double3;


/**
 *  ambient light for all the forms in the scene.
 */
public class AmbientLight extends Light{
    public static final AmbientLight NONE =new AmbientLight(Color.BLACK,Double3.ZERO);

    public AmbientLight(Color iA, Double3 kA) {
        super(iA.scale(kA));
    }
    public AmbientLight(Color iA, double kA) {
        super(iA.scale(kA));
    }
}
