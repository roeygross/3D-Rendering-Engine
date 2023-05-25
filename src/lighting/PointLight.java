package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource{
    final private Point position;
    private double kC =1;
    private double kL = 0;
    private double kQ = 0;

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
}
