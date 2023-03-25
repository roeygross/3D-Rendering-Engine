package geometries;
import primitives.Point;
import primitives.Vector;
public interface Geometry  {
    /**
     * Calculates the normal vector of the geometry
     * @param point point on the plane
     * @return normal vector of the geometry
     * @author roeygross
     */
    Vector getNormal(Point point);

}
