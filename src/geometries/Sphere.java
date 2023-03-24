package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere extends RadialGeometry{
    final private Point center;
    public Sphere(double radius, Point center)
    {
        super(radius);
        this.center=center;
    }

    @Override
    final public Vector getNormal(Point point) {
        return null;
    }
}
