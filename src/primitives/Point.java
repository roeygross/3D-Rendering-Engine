package primitives;


import static java.lang.Math.sqrt;

public class Point {
    protected Double3 xyz;

      Point(Double3 xyz) {
        this.xyz = xyz;
    }
    public Point(double x,double y,double z)
    {

        this.xyz = new Double3(x,y,z);
    }
     final public Vector subtract (Point p)
    {
        return new Vector(this.xyz.subtract(p.xyz));

    }
     final public Point add (Point p)
    {
        return new Point(this.xyz.add(p.xyz));
    }
     final public double distanceSquared(Point p)
    {
        return this.xyz.d1 - p.xyz.d1+this.xyz.d2 - p.xyz.d2+this.xyz.d3 - p.xyz.d3;
    }
    final public double distance(Point p)
    {
        return sqrt(distanceSquared(p));
    }

    @Override
     public String toString() {
        return xyz.toString();
    }

    @Override
     public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof Point )) {
            return false;
        }
        return xyz.equals(((Point) obj).xyz);

    }
}
