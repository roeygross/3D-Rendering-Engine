package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

/*
* composite design pattern, allow to use the method findIntersection on a forms collection
* */
public class Geometries extends Intersectable {
    private List<Intersectable> geometriesList;

    public Geometries() {
       geometriesList = new ArrayList<Intersectable>();
    }
    public  Geometries (Intersectable ... geometries)
    {
        geometriesList = List.of(geometries);
    }
    public void add(Intersectable... geometries)
    {
        geometriesList.addAll(List.of(geometries));
    }

    @Override
    public boolean isPointInside(Point point) {
        for (Intersectable geometry:
             geometriesList) {
            if (geometry.isPointInside(point))return true;
        }
        return  false;
    }

    public Vector getNormal(Point point)
    {
        for (Intersectable geometry:
             geometriesList) {
            if (geometry.isPointInside(point)) return geometry.getNormal(point);

        }
        return null;
    }

    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance)
    {
        if (geometriesList.size()==0) return  null;
        List <GeoPoint> l = new ArrayList<>() ;
        for (Intersectable i: geometriesList)
        {
            List<GeoPoint> tmp = i.findGeoIntersectionsHelper(ray,maxDistance);
            if (tmp!= null) l.addAll(tmp);
        }
        return l;
    }


}
