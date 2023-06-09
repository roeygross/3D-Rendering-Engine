package renderer;
import java.security.SecureRandom;

import primitives.*;
import primitives.Vector;


import java.util.*;

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
    double highet ;
    double width;
    double distance;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracerBasic;
    AntiAliasing antiAliasing =AntiAliasing.NONE;


    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }


    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracerBasic = rayTracer;
        return this;
    }
    private Color castRay (int xIndex,int yIndex)
    {
        try
        {
            switch (antiAliasing)
            {
                case NONE -> {
                    return rayTracerBasic.traceRay(constructRay(imageWriter.getNx(), imageWriter.getNy(), xIndex,yIndex));

                }
                case GRID ->
                {
                    return rayTracerBasic.traceBeamRay(constructRayBeam(yIndex,xIndex, imageWriter.getNx(),  imageWriter.getNy(), 10,10, highet / imageWriter.getNy(), width / imageWriter.getNx()));
                }
                case Random ->
                {
                    return rayTracerBasic.traceBeamRay(constructRayBeamRandom(yIndex,xIndex, imageWriter.getNx(),  imageWriter.getNy(), 10,10, highet / imageWriter.getNy(), width / imageWriter.getNx()));
                }
                default ->
                {
                    throw (new UnsupportedOperationException("one or more of the field is not inialized"));
                }
            }
        }
        catch (MissingResourceException missingResourceException)
        {
            throw (new UnsupportedOperationException("one or more of the field is not inialized"));
        }
    }
    /**/
    public Camera  renderImage()
    {
        try
        {
            int nX = imageWriter.getNx();
            int ny = imageWriter.getNy();
            for (int i=0;i<ny;++i)
            {
                for (int q=0;q<nX;q++)
                {
                    Color color = castRay(q, i);
                    imageWriter.writePixel(q,i, color);
                }
            }
            return this;
        }
        catch (MissingResourceException e)//unvaild varibles
        {
            throw (new UnsupportedOperationException("one or more of the field is not inialized"));
        }


    }
    /*gets color and interval and paint a grid upon the image*/
    public void printGrid(int interval,Color color)
    {
        imageWriter.printGrid(interval,color);
    }
    /*create image from the information*/
    public void writeToImage()
    {
        try
        {
            imageWriter.writeToImage();
        }
        catch (MissingResourceException missingResourceException)
        {
            throw (new UnsupportedOperationException("one or more of the field is not inialized"));

        }

    }
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

        Point pij = getCenterOfPixel(i,j,nX,nY,highet/nX,width/nY);
        return new Ray(place,pij.subtract(place));
    }
    private Ray constructRayInPixel(int nX, int nY, int j, int i,Point center,int gridWidth, int gridHighet) {

        Point pij = center;
        double yi = -(i - ((double)gridHighet - 1) / 2) * (highet / nY) /gridHighet;
        if (yi !=0 ) pij = pij.add(vup.scale(yi));
        double xj = (j - ((double)gridWidth - 1) / 2) * (width/ nX) /gridWidth;
        if (xj !=0 ) pij = pij.add(vright.scale(xj));
        return new Ray(place,pij.subtract(place));

    }
    private Ray constructRayInPixelRandom(int nX, int nY, int j, int i,Point center,int gridWidth, int gridHighet) {

        Point pij = center;
        double yi = -(i - ((double)gridHighet - 1) / 2) * (highet / nY) /gridHighet;
        if (yi !=0 ) pij = pij.add(vup.scale(yi));
        double xj = (j - ((double)gridWidth - 1) / 2) * (width/ nX) /gridWidth;
        if (xj !=0 ) pij = pij.add(vright.scale(xj));
        return new Ray(place,generateRandomPoint(pij,Math.min(highet/nY,width/nX)/2).subtract(place));

    }

    private Point generateRandomPoint(Point center,double radius)
    {

        Point randomPoint = center;
        double yi = -Math.floor(Math.random() *(radius - 0 + 1) + 0);//generate y coordinate from 0 to radius
        if (yi !=0 ) randomPoint = randomPoint.add(vup.scale(yi));
        double xj = Math.floor(Math.random() *((radius-yi) - 0 + 1) + 0);//generate x coordinate from 0 to radius-y cordinate so the sum of the distance from the center in absoolute do not be greater then radius
        if (xj !=0 ) randomPoint = randomPoint.add(vright.scale(xj));
        return randomPoint;
    }
    /*private Ray constructRayInPixelRandom(Point center,double radius)
    {
        Point randomPoint = center;
        double yi = -Math.floor(Math.random() *(radius - 0 + 1) + 0);//generate y coordinate from 0 to radius
        if (yi !=0 ) randomPoint = randomPoint.add(vup.scale(yi));
        double xj = Math.floor(Math.random() *((radius-yi) - 0 + 1) + 0);//generate x coordinate from 0 to radius-y cordinate so the sum of the distance from the center in absoolute do not be greater then radius
        if (xj !=0 ) randomPoint = randomPoint.add(vright.scale(xj));
        return new Ray(place,randomPoint.subtract(place));
    }*/
    public List<Ray> constructRayBeam(int i, int j, int nX, int nY, int gridWidth, int gridHighet, double pixelHighet, double pixelWidth)
    {
        List<Ray> beam = new ArrayList<>();
        Point center = getCenterOfPixel(i,j,nX,nY, pixelHighet, pixelWidth);
        for (int i1=0;i1<gridHighet;i1++)
        {
            for (int j1=0;j1<gridWidth;j1++)
            {
                beam.add(constructRayInPixel(nX, nY,j1,i1,center,gridWidth,gridHighet));
            }
        }
        return beam;
    }
    public List<Ray> constructRayBeamRandom(int i, int j, int nX, int nY, int gridWidth, int gridHighet, double pixelHighet, double pixelWidth)
    {
        List<Ray> beam = new ArrayList<>();
        Point center = getCenterOfPixel(i,j,nX,nY, pixelHighet, pixelWidth);
        for (int i1=0;i1<gridHighet;i1++)
        {
            for (int j1=0;j1<gridWidth;j1++)
            {
                beam.add(constructRayInPixelRandom(nX, nY,j1,i1,center,gridWidth,gridHighet));
            }
        }
        return beam;
    }
    /*public List<Ray> constructRayBeamRandom(int i, int j, int nX, int nY, int gridWidth, int gridHighet, double pixelHighet, double pixelWidth)
    {
        List<Ray> beam = new ArrayList<>();
        Point center = getCenterOfPixel(i,j,nX,nY, pixelHighet, pixelWidth);
        for (int i1=0;i1<gridHighet;i1++)
        {
            for (int j1=0;j1<gridWidth;j1++)
            {
                beam.add(constructRayInPixel(nX, nY,j1,i1,center,gridWidth,gridHighet));
            }
        }
        return beam;
    }*/
    /*public List<Ray> constructRayBeamRandomDisk(int i, int j, int nX, int nY,int numOfRaysInBeam) {
        List<Ray> beam = new ArrayList<>();
        double pixelHighet = highet / nY;
        double pixelWidth = width / nX;
        Point center = getPlace().add(vto.scale(distance));
        double yi = -(i - ((double) nY - 1) / 2) * pixelHighet;
        if (yi != 0) center = center.add(vup.scale(yi));
        double xj = (j - ((double) nX - 1) / 2) * pixelWidth;
        if (xj != 0) center = center.add(vright.scale(xj));//the center of i j pixel
        double radius = Math.sqrt(pixelHighet * pixelHighet + pixelWidth * pixelWidth);
        double radiusDisk =radius/numOfRaysInBeam;
        Ray randomRay = new Ray(place,generateRandomPoint(center,radiusDisk).subtract(place));
        beam.add(randomRay);
        for (int i1=0;i1<numOfRaysInBeam;i1++)
        {
            boolean goodPoint = true;
            Point randomPoint = generateRandomPoint(center,radius);
            for (Ray randomRayInArray:
                 beam) {
                if (randomRayInArray.distance(randomPoint)<radiusDisk)
                {
                    goodPoint = false;
                    break;
                }

            }
            if (goodPoint)
            {
                beam.add(new Ray(place,randomPoint.subtract(place)));
            }
        }
        return  beam;
    }*/

    public Point getCenterOfPixel(int i, int j, int nX,int nY,double pixelHighet,double pixelWidth)
    {
        Point center = getPlace().add(vto.scale(distance));
        double yi = -(i - ((double)nY - 1) / 2) * pixelHighet;
        if (yi !=0 ) center = center.add(vup.scale(yi));
        double xj = (j - ((double)nX - 1) / 2) * pixelWidth;
        if (xj !=0 ) center = center.add(vright.scale(xj));
        return center;
    }
    /*public List<Ray> constructRayBeamRandom(int i, int j, int nX, int nY, int numOfRaysInBeam, double pixelHighet, double pixelWidth)
    {
        List<Ray> beam = new ArrayList<>();
        Point center = getCenterOfPixel(i,j,nX,nY,pixelHighet, pixelWidth);
        //double radius = Math.sqrt(pixelHighet*pixelHighet+ pixelWidth* pixelWidth);
        double radius =Math.min(pixelHighet, pixelWidth)/2;
        double yRandom;
        double xRandom;
        Point randomPoint;
        SecureRandom random = new SecureRandom();
        SecureRandom randomS = new SecureRandom();
        double yLimit = pixelHighet/2;
        double xLimit = pixelWidth /2;
        for (int rayCounter=0;rayCounter<numOfRaysInBeam;rayCounter++)
        {
             randomPoint = center;
             //yRandom = -yLimit + (yLimit+ yLimit) * random.nextDouble();
            yRandom = randomS.nextDouble(-yLimit,yLimit);
            if (!Util.isZero(yRandom) ) randomPoint = randomPoint.add(vup.scale(yRandom));
             xRandom =randomS.nextDouble(-xLimit,xLimit);//generate x coordinate from 0 to radius-y cordinate so the sum of the distance from the center in absoolute do not be greater then radius
            if (!Util.isZero(xRandom) ) randomPoint = center = center.add(vright.scale(xRandom));
            beam.add(new Ray(place,randomPoint.subtract(place)));
        };
        return beam;
    }*/
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
    /*spinnig function cant get 90 angle or any multipltion or it.*/
    public Camera spinX(double angle)//the functino use vector spin to change the diraction
    {

        vto = vto.spinX(angle);

        vright = vto.crossProduct(vup);
        return this;
    }
    public Camera rotateAroundPointRight(double angle, Point center )
    {
        double d = center.distance(place);
        double radian = Math.toRadians(angle);
        place = place.add(new Point(-d*Math.sin(radian),-d+d*Math.cos(radian),0));
        vto =  center.subtract(place).normalize();
        vright = vto.crossProduct(vup);

        return this;
    }
    public Camera rotateAroundPointUP(double angle, Point center )
    {
        double d = center.distance(place);
        double radian = Math.toRadians(angle);
        place = place.add(new Point(0,-d*Math.sin(radian),-d+d*Math.cos(radian)));
        vto =  center.subtract(place).normalize();
        vup = vright.crossProduct(vto);

        return this;
    }

    public Camera spinY(double angle)//the functino use vector spin to change the diraction
    {
        vto = vto.spinY(angle);
        vup = vright.crossProduct(vto);
        return this;
    }
    public Camera spinZ(double angle)//the functino use vector spin to change the diraction
    {
        vto = vto.spinZ(angle);
        vup = vright.crossProduct(vto);
        return this;
    }

}