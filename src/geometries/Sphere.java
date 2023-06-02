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
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        if (center.equals(ray.getP0())) return List.of(new GeoPoint(this,ray.getPoing(radius))); //boundary value po is center
        Vector u= center.subtract(ray.getP0());
        double tm = Util.alignZero(u.dotProduct(ray.getDir())) ;
        double d= Util.alignZero(sqrt(Util.alignZero(u.lengthSquared()-tm*tm)));
        if (d>=radius) return null;
        double th = Util.alignZero(sqrt(radius*radius-d*d));
        double tmPlusTh = Util.alignZero(tm + th);
        double tmMinusTh = Util.alignZero(tm - th);
        if (tmPlusTh <=0 || tmPlusTh>maxDistance) {//if tmplusth is not good intersection
            if (tmMinusTh<=maxDistance && tmMinusTh>0)return List.of(new GeoPoint(this,ray.getPoing(tm-th))); //po inside the shpere
            return null;
        }
        if (tmMinusTh <=0 || tmMinusTh>maxDistance)//if tmminusth is not good intersection and because of the last if its known that tmplusth is a good intersection
        {
            return List.of(new GeoPoint(this,ray.getPoing(tm+th))); //po inside the shpere
        }
        return List.of(new GeoPoint(this,ray.getPoing(tm-th)),new GeoPoint(this,ray.getPoing(tm+th)));
    }
    /*
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        if (center.equals(ray.getP0())) {
            return List.of(new GeoPoint(this, center.add(ray.getDir())));
        }
        Vector u = center.subtract(ray.getP0());
        double tm = ray.getDir().dotProduct(u);
        double d = Math.sqrt(u.lengthSquared() - tm * tm);

        if (d >= radius)
            return null;
        double th = Math.sqrt(radius * radius - d * d);
        if (tm + th > 0) {
            if (tm - th > 0) {
                return List.of( new GeoPoint(this, ray.getPoing(tm - th)),new GeoPoint(this, ray.getPoing(tm + th)));
            }
            return List.of(new GeoPoint(this, ray.getPoing(tm + th)));
        }
        if (tm - th > 0)
            return List.of(new GeoPoint(this, ray.getPoing(tm + th)));
        return null;
    }*/
    @Override
    final public Vector getNormal(Point point) {
        //if (!isZero(point.distance(center)-radius))throw new IllegalArgumentException("Sphere- getNormal- the point is not inside the sphere");
        return point.subtract(center).normalize();
    }
}
