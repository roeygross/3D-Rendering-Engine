package primitives;


public class Material {
    public Double3 kD = Double3.ZERO;
    public Double3 kS =Double3.ZERO;
    public int nShininess =0;


    public Material setKd(Double3 kD) {
        this.kD = kD;
        return this;
    }
    public Material setKd(double x) {
        this.kD = new Double3(x);
        return this;
    }
    public Material setKs(double x) {
        this.kS = new Double3(x);
        return this;
    }

    public Material setKS(Double3 kS) {
        this.kS = kS;
        return this;
    }

    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
