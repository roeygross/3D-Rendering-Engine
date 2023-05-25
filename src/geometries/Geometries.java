package geometries;

import primitives.Ray;

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
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray)
    {
        if (geometriesList.size()==0) return  null;
        List <GeoPoint> l = new ArrayList<>() ;
        for (Intersectable i: geometriesList)
        {
            List<GeoPoint> tmp = i.findGeoIntersectionsHelper(ray);
            if (tmp!= null) l.addAll(tmp);
        }
        return l;
    }


}
