package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;



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
    public List<Point> findIntsersections(Ray ray) {
        try
        {
            List<Point> list = plane.findIntsersections(ray);
            if (list == null) return null;
            Point p= list.get(0);
            Vector n2 = vertices.get(2).subtract(vertices.get(1)).crossProduct(vertices.get(1).subtract(p));
            if ((vertices.get(1).subtract(vertices.get(0)).crossProduct(vertices.get(0).subtract(p))).dotProduct(n2)>0 && n2.dotProduct((vertices.get(0).subtract(vertices.get(2)).crossProduct(vertices.get(2).subtract(p))))>0) return list;//the point is inside the triangle
            return null;
        }
        catch (IllegalArgumentException i)//boundry values
        {
            return null;
        }
    }

    @Override
    public Vector getNormal(Point point) {

        return super.getNormal(point);
    }
}
