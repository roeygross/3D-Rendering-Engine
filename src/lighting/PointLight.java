package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * point light is a light that has a point that emits light and spans from it, for example, a bulb
 */
public class PointLight extends Light implements LightSource{
    final private Point position;
    private double kC =1;
    private double kL = 0;
    private double kQ = 0;
    private double narrowBeam = 1;

    public PointLight setkC(double kC) {
        this.kC = kC;
        return this;
    }

    public PointLight setkL(double kL) {
        this.kL = kL;
        return this;
    }

    public PointLight setkQ(double kQ) {
        this.kQ = kQ;
        return this;
    }

    /**
     * constructor for point light
     * @param intensity - in the point
     * @param position - point
     */
    protected PointLight(Color intensity,Point position) {
        super(intensity);
        this.position = position;
    }

    @Override
    public Color getIntensity(Point p) {
        double d = position.distance(p);
        return getIntensity().reduce(kC +d*kL +d*d*kQ );
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }


    public PointLight setNarrowBeam(double narrowBeam) {
        this.narrowBeam =narrowBeam;
        return this;
    }
    @Override
    public double getNarrowBeam() {
        return narrowBeam;
    }

    @Override
    public double getDistance(Point point) {
        return position.distance(point);
    }
}
