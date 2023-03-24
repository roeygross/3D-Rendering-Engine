package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube{
    final private double height;

    public Cylinder(double radius,Ray axisRay,double height) {

        super(radius,axisRay);
        this.height=height;
    }

    @Override
    final public Vector getNormal(Point point) {
        return null;
    }
}
