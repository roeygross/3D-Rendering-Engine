package primitives;

import static java.lang.Math.sqrt;

public class  Vector  extends Point {


     Vector(Double3 xyz) {
        super(xyz);
        if (xyz.equals(Double3.ZERO)) throw new IllegalArgumentException("Zero Vector can not be tolerate");
    }

    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (xyz.equals(Double3.ZERO)) throw new IllegalArgumentException("Zero Vector can not be tolerate");

    }
    final public Vector add(Vector v)
    {
        return new Vector(this.xyz.add(v.xyz));
    }
    final public Vector scale (double rhs)
    {
        return new Vector(this.xyz.scale(rhs));
    }
    final public Vector crossProduct (Vector v)
    {

        return new Vector(this.xyz.d2*v.xyz.d3 - this.xyz.d3*v.xyz.d2,this.xyz.d3*v.xyz.d1-this.xyz.d1*v.xyz.d3,this.xyz.d1*v.xyz.d2-this.xyz.d2*v.xyz.d1);//calculate the cross product by this formula

    }
   final  public double dotProduct (Vector v)
    {
        return this.xyz.d1*v.xyz.d1+this.xyz.d2 * v.xyz.d2+this.xyz.d3 *v.xyz.d3;
    }
   final  public double lengthSquared ()
    {
        return dotProduct(this);
    }
   final  public double length()
    {
        return sqrt(lengthSquared());
    }
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
}
