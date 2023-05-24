package geometries;

import primitives.*;

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
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        try
        {
            List<GeoPoint> planeGeoIntersections = plane.findGeoIntersectionsHelper(ray);
            if (planeGeoIntersections == null) return null;
            Point intersectionPoint = planeGeoIntersections.get(0).point;
            Vector n2 = vertices.get(2).subtract(vertices.get(1)).crossProduct(vertices.get(1).subtract(intersectionPoint));
            if ((vertices.get(1).subtract(vertices.get(0)).crossProduct(vertices.get(0).subtract(intersectionPoint))).dotProduct(n2)>0 && n2.dotProduct((vertices.get(0).subtract(vertices.get(2)).crossProduct(vertices.get(2).subtract(intersectionPoint))))>0) return List.of(new GeoPoint(this,intersectionPoint));//the point is inside the triangle
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
