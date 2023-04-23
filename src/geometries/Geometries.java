package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/*
* composite design pattern, allow to use the method findIntersection on a forms collection
* */
public class Geometries {
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
    public List<Point> findIntsersections(Ray ray)
    {
        if (formsList.size()==0) return  null;
        List <Point> l = new ArrayList<>() ;
        for (Intersectable i:formsList)
        {
            List<Point> tmp = i.findIntsersections(ray);
            if (tmp!= null) l.addAll(tmp);
        }
        return l;
    }


}
