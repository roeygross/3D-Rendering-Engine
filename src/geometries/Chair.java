package geometries;

import geometries.Cylinder;
import geometries.Geometries;
import geometries.Polygon;
import primitives.*;

/**
 * class represents a chair in a 3D space
 */
public class Chair {

    /**
     * front left leg of chair
     */
    Cylinder frontLeft;
    /**
     * front right leg of chair
     */
    Cylinder frontRight;
    /**
     * back left leg of chair
     */
    Cylinder backLeft;
    /**
     * back right leg of chair
     */
    Cylinder backRight;
    /**
     * horizontal bar between front and back legs on the left side of chair
     */
    Cylinder leftBar;
    /**
     * horizontal bar between front and back legs on the right side of chair
     */
    Cylinder rightBar;

    /**
     * seat and backrest of chair are 3D cubes represented by six polygons each
     * top, bottom, and four sides.
     * additionally , seat and backrest both have a possibility of adding a cushion ,
     * represented by a smaller square on the front and top surfaces
     */

    /**
     * top Polygon of seat cube
     */
    Polygon seatUp;

    /**
     * bottom polygon of seat cube
     */
    Polygon seatDown;
    /**
     * front side polygon of seat cube
     */
    Polygon seatSideFr;
    /**
     * left side polygon of seat cube
     */
    Polygon seatSideLeft;
    /**
     * back side polygon of seat cube
     */
    Polygon seatSideBck;
    /**
     * right side polygon of seat cube
     */
    Polygon seatSideRight;
    /**
     * seat cushion
     */
    Polygon seatCover;

    /**
     * front polygon of backrest cube
     */
    Polygon backrestFr;
    /**
     * front polygon of backrest cube
     */
    Polygon backrestCover;
    /**
     * back polygon of backrest cube
     */
    Polygon backrestBck;
    /**
     * left polygon of backrest cube
     */
    Polygon backrestLft;
    /**
     * right polygon of backrest cube
     */
    Polygon backrestRt;
    /**
     * top polygon of backrest cube
     */
    Polygon backrestTop;
    /**
     * bottom polygon of backrest cube
     */
    Polygon backrestBottom;
    /**
     * peg between seat and backrest on the left side
     */
    Cylinder backrestLftPeg;
    /**
     * peg between seat and backrest on the right side
     */
    Cylinder backrestRtPeg;


    /**
     * constructor
     * @param p point of center of chair ( center point  of the seat )
     * @param seatLength length of the seat
     * @param height total height of chair
     * @param seatWidth width of the seat
     * @param backWidth width of the backrest
     * @param legRadius radius of the chair lags
     * @param barRadius radius of the horizontal bars
     * @param forward forward direction {@link Vector} of chair
     * @param right right direction {@link Vector} of chair
     * @param color color of chair
     */
    public Chair(Point p, double seatLength, double height, double seatWidth, double backWidth,
                 double legRadius, double barRadius, Vector forward, Vector right, Color color,boolean isUpsideDown) {

        //size of distance from center point to any corner on the top polygon of the seat
        double cornerScale = seatLength / 2;
        //height of the backrest with pegs
        double backrestSize = (height / 2)-seatWidth;
        //height of backrest without the pegs
        double heightScale =backrestSize*( ((double)2)/3);

        double backrestScale = heightScale/3;
        //height of the legs
        double legHeight = height/2;
        //radius of pegs between seat and backrest
        double pegRadius = backWidth/2;

        Vector down=null;
        Vector up=null;
        if(!isUpsideDown) {
            //orthogonal vector to forward and right vectors towards bottom of chair
            down= forward.crossProduct(right).normalize();
            //orthogonal vector to forward and right vectors towards top of chair
            up= down.scale(-1);
        }
        else
        {
            //orthogonal vector to forward and right vectors towards bottom of chair
            down= right.crossProduct(forward).normalize();
            //orthogonal vector to forward and right vectors towards top of chair
            up= down.scale(-1);
        }
        //vector from top polygon of seat scaled to reach bottom polygon of seat
        Vector downScale = down.scale(seatWidth);
        // parallel point to center of seat on the bottom polygon
        Point centerDown = p.add(downScale);

        // four points of top polygon of seat
        Point frLeftUp = p.add(right.scale(-cornerScale).add(forward.scale(cornerScale)));
        Point frRightUp = p.add(right.scale(cornerScale).add(forward.scale(cornerScale)));
        Point bckLeftUp = p.add(right.scale(-cornerScale).add(forward.scale(-cornerScale)));
        Point bckRightUp = p.add(right.scale(cornerScale).add(forward.scale(-cornerScale)));

        //four point of bottom polygon of seat
        Point frLeftDwn = frLeftUp.add(downScale);
        Point frRightDwn = frRightUp.add(downScale);
        Point bckLeftDwn = bckLeftUp.add(downScale);
        Point bckRightDwn = bckRightUp.add(downScale);

        //four points of bottom polygon of backrest
        Point bRestFrLft = bckLeftUp.add(forward.scale(backWidth)).add(up.scale(backrestScale));
        Point bRestFrRt = bckRightUp.add(forward.scale(backWidth)).add(up.scale(backrestScale));
        Point bRestBckLft = bckLeftUp.add(up.scale(backrestScale));
        Point bRestBckRt = bckRightUp.add(up.scale(backrestScale));

        //four points of top polygon of backrest
        Point bRestFrLftUp = bRestFrLft.add(up.scale(heightScale));
        Point bRestFrRtUp = bRestFrRt.add(up.scale(heightScale));
        Point bRestBckLftUp = bRestBckLft.add(up.scale(heightScale));
        Point bRestBckRtUp = bRestBckRt.add(up.scale(heightScale));

        //four points for seat cushion
        // the cushion is moved slightly (0.1) off the seat to allow visibility in the image
        double coverScale = seatLength/7;
        double coverSidesScale = seatWidth/4;
        Point seatCoverBckL = bckLeftUp.add(right.scale(coverSidesScale).add(forward.scale(coverScale))).add(up.scale(0.1));
        Point seatCoverBckR = bckRightUp.add(right.scale(-coverSidesScale).add(forward.scale(coverScale))).add(up.scale(0.1));
        Point seatCoverFrL = frLeftUp.add(right.scale(coverSidesScale).add(forward.scale(-coverScale/2))).add(up.scale(0.1));
        Point seatCoverFrR = frRightUp.add(right.scale(-coverSidesScale).add(forward.scale(-coverScale/2))).add(up.scale(0.1));

        //four points for backrest cushion
        // the cushion is moved slightly (0.1) off the backrest to allow visibility in the image
        double bckCoverScale = heightScale/7;
        double bckCoverSideScale = backWidth/4;
        Point bRestCoverBottomL = bRestFrLft.add(up.scale(bckCoverScale)).add(right.scale(bckCoverSideScale)).add(forward.scale(0.1));
        Point bRestCoverBottomR = bRestFrRt.add(up.scale(bckCoverScale)).add(right.scale(-bckCoverSideScale)).add(forward.scale(0.1));
        Point bRestCoverTopL = bRestFrLftUp.add(up.scale(-bckCoverScale)).add(right.scale(bckCoverSideScale)).add(forward.scale(0.1));
        Point bRestCoverTopR = bRestFrRtUp.add(up.scale(-bckCoverScale)).add(right.scale(-bckCoverSideScale)).add(forward.scale(0.1));


        // seat top and bottom polygons set using their four points
        // all seat side polygons are set using two points from the top and two from the bottom
        // seat cushion is set using its four points
        seatUp = (Polygon) new Polygon(bckLeftUp, frLeftUp, frRightUp, bckRightUp).setEmission(color);
        seatDown = (Polygon) new Polygon(bckLeftDwn, frLeftDwn, frRightDwn, bckRightDwn).setEmission(color);
        seatSideFr = (Polygon) new Polygon(frLeftDwn, frLeftUp, frRightUp, frRightDwn).setEmission(color);
        seatSideLeft = (Polygon) new Polygon(frLeftDwn, frLeftUp, bckLeftUp, bckLeftDwn).setEmission(color);
        seatSideBck = (Polygon) new Polygon(frLeftUp, frLeftDwn, frRightDwn, frRightUp).setEmission(color);
        seatSideRight = (Polygon) new Polygon(bckRightDwn, bckRightUp, frRightUp, frRightDwn).setEmission(color);
        seatCover = (Polygon) new Polygon(seatCoverBckL, seatCoverFrL, seatCoverFrR, seatCoverBckR ).setEmission(color);

        // backrest top and bottom polygons set using their four points
        // all backrest side polygons are set using two points from the top and two from the bottom
        // backrest cushion is set using its four points
        backrestLft = (Polygon) new Polygon(bRestFrLft, bRestFrLftUp, bRestBckLftUp, bRestBckLft).setEmission(color);
        backrestBck = (Polygon) new Polygon(bRestBckLft, bRestBckLftUp, bRestBckRtUp, bRestBckRt).setEmission(color);
        backrestRt = (Polygon) new Polygon(bRestFrRt, bRestFrRtUp, bRestBckRtUp, bRestBckRt).setEmission(color);
        backrestFr = (Polygon) new Polygon(bRestFrLft, bRestFrRt, bRestFrRtUp, bRestFrLftUp).setEmission(color);
        backrestTop = (Polygon) new Polygon(bRestBckLftUp, bRestFrLftUp, bRestFrRtUp, bRestBckRtUp).setEmission(color);
        backrestBottom = (Polygon) new Polygon(bRestBckLft,bRestFrLft,bRestFrRt,bRestBckRt).setEmission(color);
        backrestCover = (Polygon) new Polygon(bRestCoverBottomL, bRestCoverTopL, bRestCoverTopR, bRestCoverBottomR).setEmission(color);

        //legs are set at four corners of bottom polygon of seat
        // all legs are moved length of radius diagonally towards center point, so they stay within border of the seat
        backLeft = (Cylinder) new Cylinder(new Ray(bckLeftDwn.add(forward.scale(legRadius).add(right.scale(legRadius))), down), legRadius, legHeight)
                .setEmission(color);
        backRight = (Cylinder) new Cylinder(new Ray(bckRightDwn.add(forward.scale(legRadius)).add(right.scale(-legRadius)),down ), legRadius, legHeight)
                .setEmission(color);
        frontRight = (Cylinder) new Cylinder(new Ray(frRightDwn.add(forward.scale(-legRadius)).add(right.scale(-legRadius)), down), legRadius, legHeight)
                .setEmission(color);
        frontLeft = (Cylinder) new Cylinder(new Ray(frLeftDwn.add(forward.scale(-legRadius)).add(right.scale(legRadius)), down), legRadius, legHeight)
                .setEmission(color);
        //get the distance between two legs on a side of the chair to set length of horizontal bar
        double distance = backLeft.getAxisRay().getP0().distance(frontLeft.getAxisRay().getP0())-legRadius*2;
        //set two horizontal bars
        leftBar = (Cylinder) new Cylinder(new Ray(bckLeftDwn.add(down.scale(heightScale/ 2)).add(forward.scale(legRadius*2).add(right.scale(legRadius))), forward), barRadius, distance)
                .setEmission(color);
        rightBar = (Cylinder) new Cylinder(new Ray(bckRightDwn.add(down.scale(heightScale / 2)).add(forward.scale(legRadius*2).add(right.scale(-legRadius))), forward), barRadius,distance)
                .setEmission(color);


        // seat two pegs between seat and backrest on left and right side of back of the chair
        backrestLftPeg = (Cylinder) new Cylinder(new Ray(bckLeftUp.add(forward.scale(pegRadius).add(right.scale(pegRadius))), up), pegRadius, backrestScale)
                .setEmission(color);
        backrestRtPeg = (Cylinder) new Cylinder(new Ray(bckRightUp.add(forward.scale(pegRadius).add(right.scale(-pegRadius))), up), pegRadius, backrestScale)
                .setEmission(color);

    }

    /**
     * get a chair object as a list of geometries
     * @return {@link Geometries} objects containing all geometries used to construct the chair
     */
    public Geometries getGeometries() {
        return new Geometries(seatUp, seatDown, seatSideFr, seatSideLeft, seatSideBck, seatSideRight,
                backLeft, backRight, frontLeft, frontRight, leftBar, rightBar
                , backrestLft, backrestBck, backrestRt, backrestFr, backrestTop,backrestLftPeg,backrestRtPeg,
                seatCover,backrestCover);
    }


    //region setters for chair seat
    public Chair setSeatEmission(Color color) {
        seatUp = (Polygon) seatUp.setEmission(color);
        seatDown = (Polygon) seatDown.setEmission(color);
        seatSideFr = (Polygon) seatSideFr.setEmission(color);
        seatSideLeft = (Polygon) seatSideLeft.setEmission(color);
        seatSideBck = (Polygon) seatSideBck.setEmission(color);
        seatSideRight = (Polygon) seatSideRight.setEmission(color);
        return this;

    }




    public Chair setSeatMaterial(Material mt) {
        seatUp = (Polygon) seatUp.setMaterial(mt);
        seatDown = (Polygon) seatDown.setMaterial(mt);
        seatSideFr = (Polygon) seatSideFr.setMaterial(mt);
        seatSideLeft = (Polygon) seatSideLeft.setMaterial(mt);
        seatSideBck = (Polygon) seatSideBck.setMaterial(mt);
        seatSideRight = (Polygon) seatSideRight.setMaterial(mt);
        return this;
    }

    public Chair setSeatKs(double ks) {
        Material mt = seatUp.getMaterial();
        mt = mt.setKs(ks);
        return setSeatMaterial(mt);
    }

    public Chair setSeatKd(double kd) {
        Material mt = seatUp.getMaterial();
        mt = mt.setKd(kd);
        return setSeatMaterial(mt);
    }

    public Chair setSeatKr(double kr) {
        Material mt = seatUp.getMaterial();
        mt = mt.setkR(kr);
        return setLegsMaterial(mt);
    }

    public Chair setSeatKt(double kt) {
        Material mt = seatUp.getMaterial();
        mt = mt.setkT(kt);
        return setSeatMaterial(mt);
    }

    public Chair setSeatShinines(int nshinines) {
        Material mt = seatUp.getMaterial();
        mt = mt.setnShininess(nshinines);
        return setSeatMaterial(mt);
    }
    //endregion

    //region seat cover setters

    public Chair setSeatCoverEmission(Color color) {
        seatCover = (Polygon)seatCover.setEmission(color);
        return this;
    }

    public Chair setSeatCoverMaterial(Material mt) {
        seatCover = (Polygon) seatCover.setMaterial(mt);
        return this;
    }
    public Chair setSeatCoverKs(double ks) {
        Material mt = seatCover.getMaterial();
        mt = mt.setKs(ks);
        return setSeatCoverMaterial(mt);
    }

    public Chair setSeatCoverKd(double kd) {
        Material mt = seatCover.getMaterial();
        mt = mt.setKd(kd);
        return setSeatCoverMaterial(mt);
    }

    public Chair setSeatCoverKr(double kr) {
        Material mt = seatCover.getMaterial();
        mt = mt.setkR(kr);
        return setSeatCoverMaterial(mt);
    }

    public Chair setSeatCoverKt(double kt) {
        Material mt = seatCover.getMaterial();
        mt = mt.setkT(kt);
        return setSeatCoverMaterial(mt);
    }

    public Chair setSeatCoverShinines(int nshinines) {
        Material mt = seatCover.getMaterial();
        mt = mt.setnShininess(nshinines);
        return setSeatCoverMaterial(mt);
    }
    //endregion

    //region setters for chair backrest
    public Chair setBackRestEmission(Color color) {
        backrestFr = (Polygon) backrestFr.setEmission(color);
        backrestBck = (Polygon) backrestBck.setEmission(color);
        backrestLft = (Polygon) backrestLft.setEmission(color);
        backrestRt = (Polygon) backrestRt.setEmission(color);
        backrestTop = (Polygon) backrestTop.setEmission(color);
        return this;

    }

    public Chair setBackRestMaterial(Material mt) {
        backrestFr = (Polygon) backrestFr.setMaterial(mt);
        backrestBck = (Polygon) backrestBck.setMaterial(mt);
        backrestLft = (Polygon) backrestLft.setMaterial(mt);
        backrestRt = (Polygon) backrestRt.setMaterial(mt);
        backrestTop = (Polygon) backrestTop.setMaterial(mt);
        return this;
    }

    public Chair setBackRestKs(double ks) {
        Material mt = backrestFr.getMaterial();
        mt = mt.setKs(ks);
        return setBackRestMaterial(mt);
    }

    public Chair setBackRestKd(double kd) {
        Material mt = backrestFr.getMaterial();
        mt = mt.setKd(kd);
        return setBackRestMaterial(mt);
    }

    public Chair setBackRestKr(double kr) {
        Material mt = backrestFr.getMaterial();
        mt = mt.setkR(kr);
        return setBackRestMaterial(mt);
    }

    public Chair setBackRestKt(double kt) {
        Material mt = backrestFr.getMaterial();
        mt = mt.setkT(kt);
        return setBackRestMaterial(mt);
    }

    public Chair setBackRestShinines(int nshinines) {
        Material mt = backrestFr.getMaterial();
        mt = mt.setnShininess(nshinines);
        return setBackRestMaterial(mt);
    }
    //endregion

    //region backrest cover setters
    public Chair setBrCoverEmission(Color color) {
        backrestCover = (Polygon) backrestCover.setEmission(color);
        return this;
    }

    public Chair setBrCoverMaterial(Material mt) {
        backrestCover = (Polygon) backrestCover.setMaterial(mt);
        return this;
    }
    public Chair setBrCoverKs(double ks) {
        Material mt = backrestCover.getMaterial();
        mt = mt.setKs(ks);
        return setBrCoverMaterial(mt);
    }

    public Chair setBrCoverKd(double kd) {
        Material mt = backrestCover.getMaterial();
        mt = mt.setKd(kd);
        return setBrCoverMaterial(mt);
    }

    public Chair setBrCoverKr(double kr) {
        Material mt =backrestCover.getMaterial();
        mt = mt.setkR(kr);
        return setBrCoverMaterial(mt);
    }

    public Chair setBrCoverKt(double kt) {
        Material mt = backrestCover.getMaterial();
        mt = mt.setkT(kt);
        return setBrCoverMaterial(mt);
    }

    public Chair setBrCoverShinines(int nshinines) {
        Material mt = backrestCover.getMaterial();
        mt = mt.setnShininess(nshinines);
        return setBrCoverMaterial(mt);
    }
    //endregion

    //region setters for chair legs

    public Chair setLegsEmission(Color color) {
        frontLeft = (Cylinder) frontLeft.setEmission(color);
        frontRight = (Cylinder) frontRight.setEmission(color);
        backLeft = (Cylinder) backLeft.setEmission(color);
        backRight = (Cylinder) backRight.setEmission(color);
        return this;
    }

    public Chair setLegsMaterial(Material mt) {
        frontLeft = (Cylinder) frontLeft.setMaterial(mt);
        frontRight = (Cylinder) frontRight.setMaterial(mt);
        backLeft = (Cylinder) backLeft.setMaterial(mt);
        backRight = (Cylinder) backRight.setMaterial(mt);
        return this;
    }

    public Chair setLegsKs(double ks) {
        Material mt = frontLeft.getMaterial();
        mt = mt.setKs(ks);
        return setLegsMaterial(mt);
    }

    public Chair setLegsKd(double kd) {
        Material mt = frontLeft.getMaterial();
        mt = mt.setKd(kd);
        return setLegsMaterial(mt);
    }

    public Chair setLegsKr(double kr) {
        Material mt = frontLeft.getMaterial();
        mt = mt.setkR(kr);
        return setLegsMaterial(mt);
    }

    public Chair setLegsKt(double kt) {
        Material mt = frontLeft.getMaterial();
        mt = mt.setkT(kt);
        return setLegsMaterial(mt);
    }

    public Chair setLegsShinines(int nshinines) {
        Material mt = frontLeft.getMaterial();
        mt = mt.setnShininess(nshinines);
        return setLegsMaterial(mt);
    }
    //endregion

    //region setters for chair leg bars
    public Chair setBarsEmission(Color color) {
        leftBar = (Cylinder) leftBar.setEmission(color);
        rightBar = (Cylinder) rightBar.setEmission(color);
        return this;
    }

    public Chair setBarsMaterial(Material mt) {
        leftBar = (Cylinder) leftBar.setMaterial(mt);
        rightBar = (Cylinder) rightBar.setMaterial(mt);
        return this;
    }

    public Chair setBarsKs(double ks) {
        Material mt = leftBar.getMaterial();
        mt = mt.setKs(ks);
        return setBarsMaterial(mt);
    }

    public Chair setBarsKd(double kd) {
        Material mt = leftBar.getMaterial();
        mt = mt.setKd(kd);
        return setBarsMaterial(mt);
    }

    public Chair setBarsKr(double kr) {
        Material mt = leftBar.getMaterial();
        mt = mt.setkR(kr);
        return setBarsMaterial(mt);
    }

    public Chair setBarsKt(double kt) {
        Material mt = leftBar.getMaterial();
        mt = mt.setkT(kt);
        return setBarsMaterial(mt);
    }

    public Chair setBarsShinines(int nshinines) {
        Material mt = leftBar.getMaterial();
        mt = mt.setnShininess(nshinines);
        return setBarsMaterial(mt);
    }
    //endregion


}