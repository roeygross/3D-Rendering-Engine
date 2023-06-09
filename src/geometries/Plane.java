package geometries;
import primitives.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Plane class represents a plane at the 3D world
 * @author roeygross
 */
public class Plane extends Geometry {
    /**
     * The normal vector to the plane
     */
    Vector normal;
    /**
     * The point on the plane that the normal goes through
     */
    Point p0;

    /**
     * Constructs an empty plane
     * @param a first point the plane
     * @param b second point the plane
     * @param c third point on the plane
     */
    public Plane(Point a,Point b,Point c) {
        normal=(b.subtract(a).crossProduct(c.subtract(a))).normalize();//vector orthogonal to vab and vac vector should be unit vector
        p0=a;
    }

    /**
     * Constructs a plane and saving the normalized normal vector
     * @param normal the normal vector to the plane
     * @param p0 the point on the plane
     */
    public Plane(Vector normal, Point p0) {
        this.normal = normal;
        this.normal.normalize();
        this.p0 = p0;
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance) {
        if(p0.equals(ray.getP0())) return null;
        Vector n = getNormal();
        double nv =  ray.getDir().dotProduct(getNormal());
        if (Util.isZero(nv)) return null;//the ray parallel or on the plane
        double t= Util.alignZero((p0.subtract(ray.getP0() ).dotProduct(getNormal()))/nv) ;
        if (t<=0||t>maxDistance) return null;//the ray points on the other direction
        return  List.of(new GeoPoint(this,ray.getPoing(t)));
    }



    @Override
    public Vector getNormal(Point point) {

        //if ( !point.equals(p0)  && point.subtract(p0).dotProduct(normal)!=0)throw new IllegalArgumentException("Plane - getNormal - the point is not on the plane");
        return normal;
    }

    /**
     * Calculates a normal vector to the plane
     * @return normal vector to the plane
     */
    public Vector getNormal()
    {
        return normal;
    }

    @Override
    public boolean isPointInside(Point point) {
        return true;
    }
}
