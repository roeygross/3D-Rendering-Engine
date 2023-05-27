package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource{
    final private Vector direction;

    protected DirectionalLight(Color intensity,Vector direction) {
        super(intensity);
        this.direction=direction.normalize();
    }
    @Override
    public Color getIntensity(Point p) {
        return getIntensity();
    }

    @Override
    public Vector getL(Point p) {
        return direction;
    }

    @Override
    public double getNarrowBeam() {
        return 1;
    }
}
