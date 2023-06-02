package geometries;
import primitives.*;
import java.util.List;
/**
 * Tube class represents a ray that has radius
 * @author roeygross
 */
public class Tube extends  RadialGeometry{
    /**
     * AxisRay used for the starting point and direction of the tube
     */
    protected Ray axisRay;

    /**
     * Constructs a tube by a radius and a ray
     * @param radius the radius of the tube
     * @param axisRay the ray of the tube
     */
    public Tube(double radius, Ray axisRay)
    {
        super(radius);
        this.axisRay = axisRay;
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance) {
        return null;
    }

    /**
     * Gets the tube's axis ray
     * @return the tube's axis ray
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    @Override
     public Vector getNormal(Point point)
    {
        Vector op;
        try //O is projection of P on cylinder's ray:  𝒕 = 𝒗  𝑷 − 𝑷𝟎 𝑶 = 𝑷𝟎 + 𝒕 ∙ 𝒗
        {
             op = point.subtract ((Point) axisRay.getDir().scale(axisRay.getDir().dotProduct(point.subtract(axisRay.getP0()))).add(axisRay.getP0()));

        }
        catch (IllegalArgumentException i)//1 extreme case when 𝑷 − 𝑷𝟎 is orthogonal to v
        {
            return point.subtract(axisRay.getP0()).normalize();
        }
        //if (!isZero(radius-op.length())) throw new IllegalArgumentException("Tube- GetNormal- the point is not on the tube");
        return op.normalize();

    }
}
