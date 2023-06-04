package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * spot light is a type of point light, that has also a direction, like a big flashlight
 */
public class SpotLight extends PointLight{
    final private Vector direction;

    /**
     * constructor for a spotlight
     * @param intensity - intensity of the light
     * @param position - point of the light
     * @param direction - direction of the light
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity,position);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity(p).scale(Math.max(0,direction.dotProduct(getL(p))));
    }


}
