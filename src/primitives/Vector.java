package primitives;

import static java.lang.Math.sqrt;
/**
 * Vector class represents a algebraic vector at the 3D Cartesian coordinate
 * @author roeygross
 */
public class  Vector  extends Point {


    /**
     * Vector constructions by the Double3 constructor
     * @param xyz point of the end of the vector while the start is at zero point
     */
    Vector(Double3 xyz) {
        super(xyz);
        if (xyz.equals(Double3.ZERO)) throw new IllegalArgumentException("Zero Vector can not be tolerate");
    }

    /**
     * Vector construction by three points in the 3D Cartesian coordinate
     * @param x x coordinate
     * @param y y coordinate
     * @param z z coordinate
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (xyz.equals(Double3.ZERO)) throw new IllegalArgumentException("Zero Vector can not be tolerate");

    }

    /**
     * Vector addition
     * @param v vector to add
     * @return new vector of sum of vectors
     */
    final public Vector add(Vector v)
    {
        return new Vector(this.xyz.add(v.xyz));
    }

    /**
     *
     * @param rhs multiplier
     * @return new scaled vector
     */
    final public Vector scale (double rhs)
    {
        return new Vector(this.xyz.scale(rhs));
    }

    /**
     * Calculates cross product of two vectors
     * @param v vector to multiply with
     * @return the cross product of two vectors
     */
    final public Vector crossProduct (Vector v)
    {

        return new Vector(this.xyz.d2*v.xyz.d3 - this.xyz.d3*v.xyz.d2,this.xyz.d3*v.xyz.d1-this.xyz.d1*v.xyz.d3,this.xyz.d1*v.xyz.d2-this.xyz.d2*v.xyz.d1);//calculate the cross product by this formula

    }

    /**
     * Calculates dot product of two vectors
     * @param v vector to multiply with
     * @return the dot product of two vectors
     */
    final  public double dotProduct (Vector v)
    {
        return this.xyz.d1*v.xyz.d1+this.xyz.d2 * v.xyz.d2+this.xyz.d3 *v.xyz.d3;
    }

    /**
     * Calculates the squared length of the vector
     * @return squared length of the vector
     */
    final  public double lengthSquared ()
    {
        return this.xyz.d1*this.xyz.d1+ this.xyz.d3*this.xyz.d3+this.xyz.d2*this.xyz.d2;
    }

    /**
     * Calculates the length of the vector
     * @return length of vector
     */
    final  public double length()
    {
        return sqrt(lengthSquared());
    }

    /**
     * Calculates the normalization of the vector
     * @return the normalized vector
     */
    final public Vector normalize ()
    {
        return new Vector (this.xyz.reduce(this.length()));
    }

    @Override
    final public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vector other)) return false;//if the type isnt vector
        return super.equals((Point) obj);
    }
    /*all the spin methods use spining matrix */
    final public  Vector  spinX(double angle)
    {
        double angleRadian = Math.toRadians(angle);
        double cos = Math.cos(angleRadian);
        double sin = Math.sin(angleRadian);
        return new Vector(xyz.d1,cos*xyz.d2+sin* xyz.d3,cos* xyz.d3-xyz.d2*sin);
    }
    final public Vector  spinY(double angle)
    {
        double angleRadian = Math.toRadians(angle);
        double cos = Math.cos(angleRadian);
        double sin = Math.sin(angleRadian);
        return new Vector(xyz.d1*cos+ xyz.d3*sin,xyz.d2,cos* xyz.d3-xyz.d1*sin);
    }
    final public Vector  spinZ(double angle)
    {
        double angleRadian = Math.toRadians(angle);
        double cos = Math.cos(angleRadian);
        double sin = Math.sin(angleRadian);
        return new Vector(xyz.d1*cos - xyz.d2*sin,xyz.d1*sin+xyz.d2*cos,xyz.d3);
    }
}
