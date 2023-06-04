package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Util;
import primitives.Vector;

/**
 * Directional light is a light that has only a direction and intensity. like sun beams!
 */
public class DirectionalLight extends Light implements LightSource{
    final private Vector direction;

    /**
     * constructor for directional light
     * @param intensity - intensity of the light
     * @param direction - direction of the light
     */
    public DirectionalLight(Color intensity,Vector direction) {
        super(intensity);
        this.direction=direction.normalize();
    }
    @Override
    public Color getIntensity(Point p) {
        return getIntensity();
    }

    @Override
    public Vector getL(Point p) {
        return direction;
    }

    @Override
    public double getNarrowBeam() {
        return 1;
    }

    @Override
    public double getDistance(Point point) {
        return  Double.POSITIVE_INFINITY;
    }
}
