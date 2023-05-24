package geometries;
import primitives.Color;
import primitives.Point;
import primitives.Vector;
public abstract class Geometry extends Intersectable {
    /**
     * Calculates the normal vector of the geometry
     * @param point point on the plane
     * @return normal vector of the geometry
     * @author roeygross
     */
    protected Color emission = Color.BLACK;

    public Color getEmission() {
        return emission;
    }

    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    public abstract Vector getNormal(Point point);


}
