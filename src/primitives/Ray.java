package primitives;

/**
 * Ray class represents a ray (=vector that starts from a specific point) in 3D Cartesian coordinates
 * @author roeygross
 */
public class Ray {
    final private Point p0;
    final private Vector dir;

    /**
     * Ray construction is made from a start point and a direction vector
     * The normalized direction vector is being saved
     * @param p0 starting point
     * @param dir direction of the ray
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir;
        this.dir.normalize();
    }

    /**
     * Gets the starting point
     * @return starting point of the ray
     */
    final public Point getP0() {
        return p0;
    }

    /**
     * Gets the normalized direction vector
     * @return normalized direction vector of the ray
     */
    final public Vector getDir() {
        return dir;
    }
}
