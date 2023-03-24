package primitives;

public class Ray {
    final private Point p0;
    final private Vector dir;

    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir;
        this.dir.normalize();
    }

    final public Point getP0() {
        return p0;
    }

    final public Vector getDir() {
        return dir;
    }
}
