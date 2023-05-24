package geometries;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.sqrt;
import primitives.*;
/**
 * Sphere class represents a sphere at the 3D world
 * @author roeygross
 */
public class Sphere extends RadialGeometry{

    final private Point center;

    /**
     * Constructs a sphere by a radius and a center point
     * @param radius the radius of the sphere
     * @param center the center point of the sphere
     */
    public Sphere(double radius, Point center)
    {
        super(radius);
        this.center=center;
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        if (center.equals(ray.getP0())) return List.of(new GeoPoint(this,ray.getPoing(radius))); //boundary value po is center
        Vector u= center.subtract(ray.getP0());
        double tm = Util.alignZero(u.dotProduct(ray.getDir())) ;
        double d= sqrt(Util.alignZero(u.lengthSquared()-tm*tm));
        if (d>=radius) return null;
        double th = sqrt(radius*radius-d*d);
        if (Util.alignZero(tm+th)<=0 && Util.alignZero(tm-th)<=0 ) return null;
        if (Util.alignZero(tm+th)<=0) return List.of(new GeoPoint(this,ray.getPoing(tm-th))); //po inside the shpere
        if (Util.alignZero(tm-th)<=0) return List.of(new GeoPoint(this,ray.getPoing(tm+th))); ;//po inside the shpere


        return List.of(new GeoPoint(this,ray.getPoing(tm-th))); //regular case to intersection
    }

    @Override
    final public Vector getNormal(Point point) {
        //if (!isZero(point.distance(center)-radius))throw new IllegalArgumentException("Sphere- getNormal- the point is not inside the sphere");
        return point.subtract(center).normalize();
    }
}
