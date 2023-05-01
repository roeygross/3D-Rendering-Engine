package lighting;

import primitives.Color;
import primitives.Double3;
/*
* ambient light for all the forms in the scene.
* */
public class AmbientLight {
    private Color intensity;
      public static final AmbientLight NONE =new AmbientLight(Color.BLACK,Double3.ZERO);

    public AmbientLight(Color iA, Double3 kA) {
        this.intensity = iA.scale(kA);

    }

    public Color getIntensity() {
        return intensity;
    }
}
