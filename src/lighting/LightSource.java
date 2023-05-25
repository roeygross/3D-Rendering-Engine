package lighting;

import primitives.*;

/*interface to all light sources in the scene */
public interface LightSource {
    public Color getIntensity(Point p);
    public Vector getL(Point p);
}
