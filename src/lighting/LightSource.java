package lighting;

import primitives.*;


/**
 * interface for all light sources in the scene
 */
public interface LightSource {
    /**
     * gets the distance of light from point
     * @param point
     * @return distance
     */
    double getDistance(Point point);

    /**
     * gets the narrow beam
     * @return narrow beam
     */
    double getNarrowBeam();

    /**
     * gets the intensity in a point
     * @param p - point
     * @return intensity
     */
    public Color getIntensity(Point p);

    /**
     * gets the lighting direction in a point
     * @param p - point
     * @return light dir
     */
    public Vector getL(Point p);
}
