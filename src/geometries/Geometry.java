package geometries;
import primitives.*;

public abstract class Geometry extends Intersectable {

    private Material material = new Material();

    public Material getMaterial() {
        return material;
    }
    public Double3 getKR(){return material.kR;}
    public Double3 getKT(){return material.kT;}

    /**
     * The emission color of an object refers to the color of light that the object emits or radiates.
     */
    protected Color emission = Color.BLACK;

    /**
     *
     * @return emission color of the object
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * sets the emission color of an object
     * @param emission
     * @return this
     */
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
