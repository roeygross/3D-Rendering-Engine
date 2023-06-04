package primitives;
import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;

import java.util.List;

/**
 * Ray class represents a ray (=vector that starts from a specific point) in 3D Cartesian coordinates
 * @author roeygross
 */
public class Ray {
    private static final double DELTA = 0.1; //constant variable for changing ray starting point
    final private Point p0;
    final private Vector dir;

    /**
     * Ray construction is made from a start point and a direction vector
     * The normalized direction vector is being saved
     * @param p0 starting point
     * @param dir direction of the ray
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }
    /*construct ray from point plus delta point to the normal's direction*/
    public  Ray (Point point, Vector diraction, Vector normal)
    {

        Vector delta = normal.scale(normal.dotProduct(diraction) >= 0 ? DELTA : -DELTA);
        this.p0= point.add(delta);
        this.dir=diraction.normalize();
    }

    /**
     * Gets the starting point
     * @return starting point of the ray
     */
    final public Point getP0() {
        return p0;
    }

    /**
     * Gets the normalized direction vector
     * @return normalized direction vector of the ray
     */
    final public Vector getDir() {
        return dir;
    }

    final public Point getPoing(double t) {
        if (t<0) throw  (new IllegalArgumentException("getPoing must get a non negative value "));
        return dir.scale(t).add(p0);
    }

    @Override
    public String toString() {
        return p0.toString()+" "+dir.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Ray other)) return false;//if the type isnt vector
        return dir.equals(((Ray)obj).dir) && p0.equals(( (Ray)obj).p0);
    }

    /**
     * finds the closest geopoint/s to the ray given list of geopoints
     * @param listPoints - the list of the geopoints
     * @return closest geopoint to the ray point
     */
    public Intersectable.GeoPoint findClosestGeoPoint(List<Intersectable.GeoPoint> listPoints)
    {
        if (listPoints==null||listPoints.isEmpty()) return  null;
        Intersectable.GeoPoint closestGeoPoint = listPoints.get(0);
        double closestDistance= closestGeoPoint.point.distance(p0);
        for (Intersectable.GeoPoint pointOnRay:
             listPoints) {
            if (pointOnRay.point.distance(p0)<closestDistance)
            {
                closestGeoPoint = pointOnRay;
                closestDistance = pointOnRay.point.distance(p0);
            }
        }
        return closestGeoPoint;

    }

    /**
     *
     * finds the closest point/s to the ray given list of points
     *      * @param listPoints - the list of the points
     *      * @return closest point to the ray point
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

}
