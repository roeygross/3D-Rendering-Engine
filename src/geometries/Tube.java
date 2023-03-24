package geometries;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;
public class Tube extends  RadialGeometry{
    protected Ray axisRay;

    public Tube(double radius, Ray axisRay)
    {
        super(radius);
        this.axisRay = axisRay;
    }


    public Ray getAxisRay() {
        return axisRay;
    }

    @Override
     public Vector getNormal(Point point) {
        return null;
    }
}
