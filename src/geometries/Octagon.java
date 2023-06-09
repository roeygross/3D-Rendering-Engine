package  geometries;

import primitives.Point;

class Octagon extends   Polygon{

    public Octagon(double radius, Point center) {
        super(radius,center,8);
    }
    public Octagon(Point... points)
    {
        super(points);
    }

}
