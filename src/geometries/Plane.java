package geometries;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import primitives.*;

/**
 *Plane
 *
 * @author Idan and Eliyahu
 */
public class Plane extends Geometry {
    public final Point q0 ;
    private final Vector normal ;

    public Plane(Point q1 , Point q2 , Point q3 ){ //constructor
        Vector v1 = q2.subtract(q1) ; // two new vectors
        Vector v2 =q3.subtract(q2) ; // two new vectors ;
        this.normal = v1.crossProduct(v2).normalize();
        this.q0 = q1 ;
    }

    public Plane( Vector normal,Point q0) { //simple constructor
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    @Override
    public Vector getNormal(Point point) { //get
        return normal ;
    }

    /**
     *  get without the point
     * @return normal
     */
    public Vector getNormal() {
        return normal ;
    }

    /**
     *  get with the point
     * @return normal
     */
    @Override
    public boolean equals(Object obj) {//checks if equals
        return (obj instanceof Plane) && this.normal.equals(((Plane) obj).normal)
                && q0.subtract(((Plane) obj).q0).dotProduct(normal) == 0  ;
    }


    /**
     * finds all the intersections plane
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double max){

        double nv =Util.alignZero(this.getNormal().dotProduct(ray.getDir()));
        if(Util.isZero(nv)){
            return  null ;
        }
        else{
            double nqp =  Util.alignZero(this.getNormal().dotProduct(this.q0.subtract(ray.getP0())));
            double t =Util.alignZero(nqp / nv)  ;
            if(t > 0  ){
                GeoPoint p = new Intersectable.GeoPoint( this,ray.getPoing(t));
                if(Util.alignZero(p.point.distanceSquared(ray.getP0()) - max ) <= 0 ){
                    return List.of(p);
                }

            }
            return null ;

        }
    }


    @Override
    public boolean isPointInside(Point point) {
        return false;
    }
}