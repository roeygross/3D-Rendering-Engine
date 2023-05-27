package lighting;

import primitives.*;

/*interface to all light sources in the scene */
public interface LightSource {
    double getNarrowBeam();
    public Color getIntensity(Point p);
    public Vector getL(Point p);
}
