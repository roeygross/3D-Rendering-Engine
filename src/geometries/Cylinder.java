package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.isZero;

/**
 * Finite tube delimited by two planes
 */
public class Cylinder extends Tube{

    /**
     * height of cylinder
     */
    final private double height;

    /**
     * cylinder constructor based on a radius , ray (direction), and a height
     * @param axisRay ray originating from base of cylinder
     * @param radius radius of cylinder
     * @param height height of cylinder
     * @throws IllegalArgumentException <p>if height sent as parameter is not a positive value</p>
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        if(height<= 0)
            throw new IllegalArgumentException("height must be positive value");
        this.height=height;
    }

    /**
     * getter for height field
     * @return height of cylinder
     */
    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Cylinder: " +
                "height = " + height +
                ", axisRay = " + _axisRay +
                ", radius=" + _radius ;
    }

    /**
     * implementation {@link Geometry#getNormal(Point)}
     *
     * @param point point to calculate normal from/to
     * @return normal
     */
    @Override
    public Vector getNormal(Point point){
        Vector direction = _axisRay.getDir();
        Point P0 = _axisRay.getP0();

        //given point is on base of cylinder
        if(point.equals(P0)||isZero(point.subtract(P0).dotProduct(direction)))
            return direction.normalize();


        // given point is on top base of the cylinder
        if (point.equals(P0.add(direction.scale(height)))||isZero(point.subtract(P0.add(direction.scale(height))).dotProduct(direction)))
            return direction.normalize();

        // given point is on the circumference of cylinder
        return super.getNormal(point);
    }

    /**
     * find intersection points between ray and 3D cylinder
     * @param ray ray towards the sphere
     * @return immutable list containing 0/1/2 intersection points as {@link GeoPoint}s objects
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray ,double maxDistance) {
        // origin point of cylinder (on bottom base)
        Point basePoint=_axisRay.getP0();
        // point across base point on top base
        Point topPoint =_axisRay.getPoing(height);
        // direction vector of cylinder (orthogonal to base point)
        Vector vC=_axisRay.getDir();

        // find intersection points of ray with bottom base of cylinder
        List<GeoPoint> result= new LinkedList<>();
        // crate plane that contains base point in it
        Plane basePlane= new Plane(vC,basePoint);
        // find intersection between ray and plane
        List<GeoPoint> intersectionsBase=basePlane.findGeoIntersections(ray,maxDistance);

        // if intersections were found, check that point are actually on the base of the cylinder
        //if distance from base point to intersection point holds the equation ->  distance² < from radius²
        if(intersectionsBase!=null){
            for (GeoPoint p:intersectionsBase) {
                Point pt = p.point;
                // intersection point is the base point itself
                if(pt.equals(basePoint))
                    result.add(new GeoPoint(this,basePoint));
                    // intersection point is different to base point but is on the bottom base
                else if(pt.subtract(basePoint).dotProduct(pt.subtract(basePoint))<_radius*_radius)
                    result.add(new GeoPoint(this,pt));
            }
        }

        // find intersection points of ray with bottom base of cylinder
        // crate plane that contains top point in it
        Plane topPlane= new Plane(vC,topPoint);
        // find intersection between ray and plane
        List<GeoPoint> intersectionsTop=topPlane.findGeoIntersections(ray,maxDistance);
        // if intersections were found, check that point are actually on the base of the cylinder
        //if distance from top point to intersection point holds the equation ->  distance² < from radius²
        if(intersectionsTop!=null){
            for (var p:intersectionsTop) {
                Point pt = p.point;
                // intersection point is the top point itself
                if(pt.equals(topPoint))
                    result.add(new GeoPoint(this,topPoint));
                    // intersection point is different to base point but is on the bottom base
                else if(pt.subtract(topPoint).dotProduct(pt.subtract(topPoint))<_radius*_radius)
                    result.add(new GeoPoint(this,pt));
            }
        }

        // if rsy intersects both bases , no other intersections possible - return the result list
        if (result.size()==2)
            return result;

        // use tube parent class function to find intersections with the cylinder represented
        // as an infinite tube
        List<GeoPoint> intersectionsTube=super.findGeoIntersectionsHelper(ray ,maxDistance);

        // if intersection points were found check that they are within the finite cylinder's boundary
        // by checking if  scalar product fo direction vector with a vector from intersection point
        // to bottom base point is positive, and scalar product of direction vector with a
        // vector from intersection point to top base point is negative
        if(intersectionsTube!=null){
            for (var p:intersectionsTube){
                Point pt = p.point;
                if(vC.dotProduct(pt.subtract(basePoint))>0 && vC.dotProduct(pt.subtract(topPoint))<0)
                    result.add(new GeoPoint(this,pt));
            }
        }

        // return an immutable list
        int len = result.size();
        if(len>0)
            if (len ==1)
                return List.of(result.get(0));
            else
                return List.of(result.get(0), result.get(1));

        // no intersections
        return null;
    }
}