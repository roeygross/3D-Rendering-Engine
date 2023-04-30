package renderer;

import primitives.*;

import java.util.ArrayList;
import java.util.List;

/*
*class for implement camera
*  */
public class Camera {
    /*the location of the camera*/
    Point place;
    /* three diraction vectors for the camera*/
    Vector vto ;
    Vector vup;
    Vector vright;
    /* the highet and the width and the distance between the camera and the view plane*/
    double highet;
    double width;
    double distance;

    public Camera(Point place, Vector vto, Vector vup) {

        this.place = place;
        if (vto.dotProduct(vup) != 0) throw (new IllegalArgumentException("Vector is not orthogonal"));
        this.vto = vto.normalize();
        this.vup = vup.normalize();
        this.vright = vto.crossProduct(vup).normalize();//orthogonal to vto and vup
    }
    public Camera setVPSize(double width, double height)
    {
        this.width = width;
        this.highet = height;
        return this;
    }
    public Camera setVPDistance(double distance)
    {
        this.distance =distance;
        return this;
    }
    public Ray constructRay(int nX, int nY, int j, int i) {

        Point pij = getPlace().add(vto.scale(distance));
        double yi = -(i - ((double)nY - 1) / 2) * highet / nY;
        if (yi !=0 ) pij = pij.add(vup.scale(yi));
        double xj = (j - ((double)nX - 1) / 2) * width / nX;
        if (xj !=0 ) pij = pij.add(vright.scale(xj));
        return new Ray(place,pij.subtract(place));
    }
    public Point getPlace() {
        return place;
    }

    public Vector getVto() {
        return vto;
    }

    public Vector getVup() {
        return vup;
    }

    public Vector getVright() {
        return vright;
    }

    public double getHighet() {
        return highet;
    }

    public double getWidth() {
        return width;
    }

    public double getDistance() {
        return distance;
    }
    public Camera spinRight(double angle)
    {
        Point newPlace = place.add(new Point (0,distance*Math.cos(Math.toRadians(angle)),0)).setX(-distance*Math.sin(Math.toRadians(angle)));
        vto = place.add(vto.scale(distance)).subtract(newPlace).normalize();
        if (vto.dotProduct(vup)!=0) vup=vright.crossProduct(vup).normalize();
           else vright = vto.crossProduct(vup).normalize();
        place= newPlace;
        return this;

    }
    public Camera spintUp(double angle)
    {
        double tmpp = Math.sin(Math.toRadians(angle));
        Point newPlace = place.add(new Point (0,0, tmpp)).setX(distance*Math.cos(Math.toRadians(angle)));
        Point tmp = place.add(vto.scale(distance));
        vto = place.add(vto.scale(distance)).subtract(newPlace).normalize();
        if (vto.dotProduct(vright)==0) vup=vright.crossProduct(vup).normalize();
        else if(vto.dotProduct(vup)==0) vright=vto.crossProduct(vup).normalize();
        place= newPlace;
        return this;
    }
}
