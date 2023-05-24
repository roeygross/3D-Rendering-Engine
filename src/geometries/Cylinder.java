package geometries;
import java.util.List;
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
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        return null;
    }

    @Override
    final public Vector getNormal(Point point) {
        //for information about this calculate you may check on Tube
        if (point.equals(axisRay.getP0())) return axisRay.getDir().scale(-1);//boundary case of point on first center
        if (point.equals(axisRay.getPoing(height)))
            return axisRay.getDir();//boundary case of point on first center
        //first EQ if the point is on the second base
        if (point.subtract((Point) axisRay.getPoing(height)).dotProduct(axisRay.getDir()) == 0) {
            return axisRay.getDir();
        }
        //second EQ the point is on the first base

        if (point.subtract(axisRay.getP0()).dotProduct(axisRay.getDir())==0 ) return axisRay.getDir().scale(-1);
        //if the point is not on the first or second EQ nor on the boundary cases the normal is simply the op
        return point.subtract(axisRay.getDir().scale(point.subtract(axisRay.getP0()).dotProduct(axisRay.getDir())).add(axisRay.getP0())).normalize();
    }


}
