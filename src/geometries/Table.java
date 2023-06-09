package geometries;

import primitives.*;

import java.util.List;

/**
 * class represents a circular table in a 3D space
 */
public class Table {

    /**
     * center leg of the table
     */
    Cylinder leg;
    /**
     * top surface of the table
     */
    Cylinder surfaceTop;
    /**
     * bottom surface of the table
     */
    Cylinder surfaceBase;
    /**
     * base of the table
     */
    Cylinder base;
    /**
     * first peg between top and bottom surfaces of table
     */
    Cylinder cy1;
    /**
     * second peg between top and bottom surfaces of table
     */
    Cylinder cy2;
    /**
     * third peg between top and bottom surfaces of table
     */
    Cylinder cy3;
    /**
     * fourth peg between top and bottom surfaces of table
     */
    Cylinder cy4;


    /**
     * constructor
     * @param height total height of table
     * @param radius radius of surface of table
     * @param color color of the table
     * @param position canter position {@link Point} of base of table
     * @param dirHeight vertical direction {@link Vector}of table
     * @param dirSurface horizontal direction {@link Vector} of table
     */
    public Table(double height, double radius, Color color, Point position, Vector dirHeight, Vector dirSurface){

        double radiusMini=radius/15;
        Vector dirMini=dirSurface.normalize().scale(radius-radiusMini);
        Point tpSurfaceTop =position.add(dirHeight.scale(height*19/20));
        Point tpSurfaceBase=position.add(dirHeight.scale(height*15/20));

        surfaceTop =(Cylinder) new Cylinder(new Ray(tpSurfaceTop,dirHeight),radius,height/20).setEmission(color);
        surfaceBase=(Cylinder) new Cylinder(new Ray(tpSurfaceBase,dirHeight),radius,height/20).setEmission(color)
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60));

        leg=(Cylinder) new Cylinder(new Ray(position.add(dirHeight.scale(height*2/20)),dirHeight),radius/7,height*13/20)
                .setEmission(color);

        base=(Cylinder) new Cylinder(new Ray(position,dirHeight),radius/2,height*2/70).setEmission(color);

        int i=0;
        double angle = 360 / 4;
        cy1 = (Cylinder) new Cylinder(new Ray(tpSurfaceBase.add(dirMini.vectorRotate(dirHeight, i * angle)), dirHeight), radiusMini, height * 4 / 20)
                .setEmission(color);
        i++;
        cy2 = (Cylinder) new Cylinder(new Ray(tpSurfaceBase.add(dirMini.vectorRotate(dirHeight, i * angle)), dirHeight), radiusMini, height * 4 / 20)
                .setEmission(color);
        i++;
        cy3 = (Cylinder) new Cylinder(new Ray(tpSurfaceBase.add(dirMini.vectorRotate(dirHeight, i * angle)), dirHeight), radiusMini, height * 4 / 20)
                .setEmission(color);
        i++;
        cy4 = (Cylinder) new Cylinder(new Ray(tpSurfaceBase.add(dirMini.vectorRotate(dirHeight, i * angle)), dirHeight), radiusMini, height * 4 / 20)
                .setEmission(color);
    }


    /**
     * get a Table object as a list of geometries
     * @return {@link Geometries} objects containing all geometries used to construct the tabler
     */
    public Geometries getGeometries() {
        return new Geometries(surfaceTop,leg,base,surfaceBase,cy1,cy2,cy3,cy4);
    }

    //region top surface material and color setters
    public Table setColorSurfaceTop(Color color) {
        surfaceTop= (Cylinder) surfaceTop.setEmission(color);
        return this;
    }

    public Table setMaterialSurfaceTop(Material material) {
        surfaceTop = (Cylinder) surfaceTop.setMaterial(material);
        return this;
    }

    public Table setSurfaceTopKs(double kS){
        Material mt = surfaceTop.getMaterial().setKs(kS);
        return setMaterialSurfaceTop(mt);

    }
    public Table setSurfaceTopKt(double kT){
        Material mt = surfaceTop.getMaterial().setkT(kT);
        return setMaterialSurfaceTop(mt);

    }
    public Table setSurfaceTopKr(double kR){
        Material mt = surfaceTop.getMaterial().setkR(kR);
        return setMaterialSurfaceTop(mt);

    }
    public Table setSurfaceTopKd(double kD){
        Material mt = surfaceTop.getMaterial().setKd(kD);
        return setMaterialSurfaceTop(mt);

    }

    public Table setSurfaceTopShininess(int shininess){
        Material mt = surfaceTop.getMaterial().setnShininess(shininess);
        return setMaterialSurfaceTop(mt);

    }
    //endregion

    //region bottom surface material and color setters
    public Table setColorSurfaceBase(Color color) {
        surfaceBase = (Cylinder) surfaceBase.setEmission(color);
        return this;
    }
    public Table setMaterialSurfaceBase(Material material) {
        surfaceBase = (Cylinder) surfaceBase.setMaterial(material);
        return this;
    }

    public Table setSurfaceBaseKs(double kS){
        Material mt = surfaceBase.getMaterial().setKs(kS);
        return setMaterialSurfaceBase(mt);

    }
    public Table setSurfaceBaseKt(double kT){
        Material mt = surfaceBase.getMaterial().setkT(kT);
        return setMaterialSurfaceBase(mt);

    }
    public Table setSurfaceBaseKr(double kR){
        Material mt = surfaceBase.getMaterial().setkR(kR);
        return setMaterialSurfaceBase(mt);

    }
    public Table setSurfaceBaseKd(double kD){
        Material mt = surfaceBase.getMaterial().setKd(kD);
        return setMaterialSurfaceBase(mt);

    }

    public Table setSurfaceBaseShininess(int shininess){
        Material mt = surfaceBase.getMaterial().setnShininess(shininess);
        return setMaterialSurfaceBase(mt);
    }
    //endregion

    //region four pegs material and color setters
    public Table setColorBars(Color color) {
        cy1 = (Cylinder) cy1.setEmission(color);
        cy2 = (Cylinder) cy2.setEmission(color);
        cy3 = (Cylinder) cy3.setEmission(color);
        cy4= (Cylinder) cy4.setEmission(color);
        return this;
    }
    public Table setMaterialBars(Material material) {
        cy1 = (Cylinder) cy1.setMaterial(material);
        cy2 = (Cylinder) cy2.setMaterial(material);
        cy3 = (Cylinder) cy3.setMaterial(material);
        cy4= (Cylinder)  cy4.setMaterial(material);
        return this;
    }
    public Table setBarsKs(double kS){
        Material mt = cy1.getMaterial().setKs(kS);
        return setMaterialBars(mt);

    }
    public Table setBarsKt(double kT){
        Material mt = cy1.getMaterial().setKd(kT);
        return setMaterialBars(mt);

    }
    public Table setBarsKr(double kR){
        Material mt =cy1.getMaterial().setkR(kR);
        return setMaterialBars(mt);

    }
    public Table setBarsKd(double kD){
        Material mt = cy1.getMaterial().setKd(kD);
        return setMaterialBars(mt);

    }

    public Table setBarsShininess(int shininess){
        Material mt = cy1.getMaterial().setnShininess(shininess);
        return setMaterialBars(mt);
    }
    //endregion

    //region leg material and color setters
    public Table setColorLeg(Color color) {
        leg = (Cylinder) leg.setEmission(color);
        return this;
    }

    public Table setMaterialLeg(Material material) {
        leg = (Cylinder) leg.setMaterial(material);
        return this;
    }
    public Table setLegKs(double kS){
        Material mt = leg.getMaterial().setKs(kS);
        return setMaterialLeg(mt);

    }
    public Table setLegKt(double kT){
        Material mt = leg.getMaterial().setkT(kT);
        return setMaterialLeg(mt);

    }
    public Table setLegKr(double kR){
        Material mt =leg.getMaterial().setkR(kR);
        return setMaterialLeg(mt);

    }
    public Table setLegKd(double kD){
        Material mt = leg.getMaterial().setKd(kD);
        return setMaterialLeg(mt);

    }

    public Table setLegShininess(int shininess){
        Material mt = leg.getMaterial().setnShininess(shininess);
        return setMaterialLeg(mt);

    }
    //endregion

    //region base material and color setters
    public Table setColorBase(Color color) {
        base = (Cylinder) base.setEmission(color);
        return this;
    }

    public Table setMaterialBase(Material material) {
        base = (Cylinder)base.setMaterial(material);
        return this;
    }
    public Table setBaseKs(double kS){
        Material mt = base.getMaterial().setKs(kS);
        return setMaterialBase(mt);

    }
    public Table setBaseKt(double kT){
        Material mt = base.getMaterial().setKs(kT);
        return setMaterialBase(mt);

    }
    public Table setBaseKr(double kR){
        Material mt =base.getMaterial().setkR(kR);
        return setMaterialBase(mt);


    }
    public Table setBaseKd(double kD){
        Material mt = leg.getMaterial().setKd(kD);
        return  setMaterialBase(mt);

    }

    public Table setBaseShininess(int shininess){
        Material mt = base.getMaterial().setnShininess(shininess);
        return  setMaterialBase(mt);

    }
    public  Geometries getElements()
    {
        return new Geometries(leg,surfaceBase,surfaceTop,cy1,cy2,cy3,cy4);
    }




    //endregion
}