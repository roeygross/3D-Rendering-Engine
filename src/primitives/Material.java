package primitives;



public class Material {
    public Double3 kD = Double3.ZERO;
    public Double3 kS =Double3.ZERO;
    public int nShininess =0;
    /*kT transparency factor*/
    public Double3 kT = Double3.ZERO;
    /*kR reflection factor*/
    public Double3 kR =Double3.ZERO;
    /*kR kT setter by builder design pattern*/

    public Material setkT(Double3 kT) {
        this.kT = kT;
        return this;
    }
    public Material setkT(double kT) {
        this.kT = new Double3(kT);
        return this;
    }

    public Material setkR(Double3 kR) {
        this.kR = kR;
        return this;
    }

    public Material setkR(double kR) {
        this.kR = new Double3(kR);
        return this;
    }
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
