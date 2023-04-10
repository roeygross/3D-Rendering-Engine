package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Triangle Class represents a triangle at the 3D Cartesian coordinate world
 * @author roeygross
 */
public class Triangle extends Polygon{


    /**
     * Constructs a triangle
     * @param vertices the points of the triangle
     */
    public Triangle(Point... vertices) {
        super(vertices);
        if (vertices.length != 3)
            throw (new IllegalArgumentException("ERROR - triangle must get exactly three points"));
    }

    @Override
    public Vector getNormal(Point point) {

        return super.getNormal(point);
    }
}
