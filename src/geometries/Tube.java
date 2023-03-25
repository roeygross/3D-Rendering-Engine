package geometries;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;

/**
 * Tube class represents a ray that has radius
 * @author roeygross
 */
public class Tube extends  RadialGeometry{
    /**
     * AxisRay used for the starting point and direction of the tube
     */
    protected Ray axisRay;

    /**
     * Constructs a tube by a radius and a ray
     * @param radius the radius of the tube
     * @param axisRay the ray of the tube
     */
    public Tube(double radius, Ray axisRay)
    {
        super(radius);
        this.axisRay = axisRay;
    }


    /**
     * Gets the tube's axis ray
     * @return the tube's axis ray
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    @Override
     public Vector getNormal(Point point) {
        return null;
    }
}
