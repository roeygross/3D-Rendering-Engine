package geometries;
import primitives.*;

public abstract class Geometry extends Intersectable {

    private Material material = new Material();

    public Material getMaterial() {
        return material;
    }
    public Double3 getKR(){return material.kR;}
    public Double3 getKT(){return material.kT;}
    protected Color emission = Color.BLACK;

    public Color getEmission() {
        return emission;
    }

    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    public abstract Vector getNormal(Point point);


    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }
    public  Double3 getKD ()
    {
        return material.kD;
    }
    public  Double3 getKS ()
    {
        return material.kS;
    }
    public  int getNShininess ()
    {
        return material.nShininess;
    }

}
