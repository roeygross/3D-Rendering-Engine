package geometries;
import java.util.*;
import primitives.*;
import primitives.Vector;

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
        public Point add (Point point)
        {
            return this.point.add(point);
        }
        public double distance (GeoPoint geoPoint)
        {
            return this.point.distance(geoPoint.point);
        }
        public double distance (Point point)
        {
            return this.point.distance(point);
        }
        public Color getIE()
        {
            return geometry.getEmission();
        }

        public Double3 getKD ()
        {
            return geometry.getKD();
        }
        public Double3 getKS ()
        {
            return geometry.getKS();
        }
        public Vector getNormal()
        {
            return geometry.getNormal(point);
        }
        public Material getMaterial(){return geometry.getMaterial();}
        public int getNShininess()
        {
            return geometry.getNShininess();
        }



        @Override
        public boolean equals(Object obj) {
            if (obj.getClass()!=getClass()) return false;
            GeoPoint geoPoint =(GeoPoint) obj;
            return   geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
        }

        @Override
        public String toString() {
            return "Geometry: " + geometry.toString() + " Point: " + point.toString();
        }
    }


    public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        return findGeoIntersectionsHelper(ray, maxDistance);
    }
    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
    }
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);



    }
