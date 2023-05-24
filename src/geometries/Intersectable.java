package geometries;
import java.util.*;
import primitives.*;
/*
* interface for geometries that should provide the method findIntersection for all the forms
* all the information about the implementation of this method you may find on https://moodle.jct.ac.il/pluginfile.php/987458/mod_resource/content/46/4%20-%20ISE.pdf
* */
public abstract class Intersectable {
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }
    /*passice data sturctue for returning intersections*/
    public static class GeoPoint {
        public Geometry geometry;
        public Point point;
        public Color getEmission()
        {
            return geometry.getEmission();
        }
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object obj) {
            GeoPoint geoPoint =(GeoPoint) obj;
            return geoPoint.getClass()==getClass() && geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
        }

        @Override
        public String toString() {
            return "Geometry: " + geometry.toString() + " Point: " + point.toString();
        }
    }
    public List<GeoPoint> findGeoIntersections(Ray ray)
    {
        return findGeoIntersectionsHelper(ray);
    }
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);


}
