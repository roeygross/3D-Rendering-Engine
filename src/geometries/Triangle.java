package geometries;

import primitives.Point;

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
    }
}
