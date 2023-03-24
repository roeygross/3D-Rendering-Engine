package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry {
    Vector normal;
    Point p0;
    public Plane(Point a,Point b,Point c) {
        normal=null;
        p0=a;
    }

    public Plane(Vector normal, Point p0) {
        this.normal = normal;
        this.normal.normalize();
        this.p0 = p0;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
    public Vector getNormal()
    {
        Vector x = new Vector(0,0,0);
        x.subtract(x);
        return normal;
    }
}
