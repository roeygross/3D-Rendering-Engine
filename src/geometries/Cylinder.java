package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Cylinder class represents a tube with a limited height
 * @author roeygross
 */
public class Cylinder extends Tube{
    /**
     * The height of the cylinder
     */
    final private double height;

    /**
     * Constructs a cylinder by a radius, ray, and height
     * @param radius the radius of the cylinder
     * @param axisRay the tay of the cylinder
     * @param height the height of the cylinder
     */
    public Cylinder(double radius,Ray axisRay,double height) {

        super(radius,axisRay);
        this.height=height;
    }

    @Override
    final public Vector getNormal(Point point) {
        return null;
    }
}
