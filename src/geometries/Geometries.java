package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;
/*
* composite design pattern, allow to use the method findIntersection on a forms collection
* */
public class Geometries extends Intersectable {
    private List<Intersectable> formsList;

    public Geometries() {
       formsList= new ArrayList<Intersectable>();
    }
    public  Geometries (Intersectable ... geometries)
    {
        formsList = List.of(geometries);
    }
    public void add(Intersectable... geometries)
    {
        formsList.addAll(List.of(geometries));
    }
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray)
    {
        if (formsList.size()==0) return  null;
        List <GeoPoint> l = new ArrayList<>() ;
        for (Intersectable i:formsList)
        {
            List<GeoPoint> tmp = i.findGeoIntersectionsHelper(ray);
            if (tmp!= null) l.addAll(tmp);
        }
        return l;
    }


}
