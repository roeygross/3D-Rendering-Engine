package geometries;

import primitives.Point;
import primitives.Vector;
/**
 * Sphere class represents a sphere at the 3D world
 * @author roeygross
 */
public class Sphere extends RadialGeometry{
    final private Point center;

    /**
     * Constructs a sphere by a radius and a center point
     * @param radius the radius of the sphere
     * @param center the center point of the sphere
     */
    public Sphere(double radius, Point center)
    {
        super(radius);
        this.center=center;
    }

    @Override
    final public Vector getNormal(Point point) {
        //if (!isZero(point.distance(center)-radius))throw new IllegalArgumentException("Sphere- getNormal- the point is not inside the sphere");
        return point.subtract(center).normalize();
    }
}
