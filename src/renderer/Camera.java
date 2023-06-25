package renderer;

import geometries.Plane;
import primitives.*;
import primitives.Vector;
import java.util.stream.*;
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
    /*threading 2 variubles*/
    private  double intervalThread =0;
    private double threads = 1;

    public Camera setThreads(double threads) {
        this.threads = threads;
        return this;
    }


    public Camera setintervalThread(double intervalThread)
    {
        this.intervalThread = intervalThread;
        return this;
    }

    public Camera setAperture(double aperture) {
        this.aperture = aperture;
        return this;
    }

    private double aperture = 0;
    private  double DOFPlaneDistance = 100;
    private int samples = 5;
    private boolean DOF = false;
    AntiAliasing antiAliasing =AntiAliasing.DOF;
    Plane DOFPlane;

    public Camera setDOF(boolean DOF) {
        this.DOF = DOF;
        return  this;

    }


    public Camera setDOFPlaneDistance(double DOFPlaneDistance) {
        this.DOFPlaneDistance = DOFPlaneDistance;
        DOFPlane = new Plane(vto,place.add(vto.scale(distance+DOFPlaneDistance)));
        return this;
    }

    public Camera setAntiAliasing(AntiAliasing antiAliasing) {
        this.antiAliasing = antiAliasing;
        return this;
    }

    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }


    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracerBasic = rayTracer;
        return this;
    }
    private Point constructPixelPoint(int nX, int nY, int j, int i) {
        double Ry = highet / nY;
        double Rx = width / nX;
        double yI = -(i - ((nY - 1) / 2.0)) * Ry;//how much to move on the plane in order to get to the j i index
        double xJ = (j - ((nX - 1) / 2.0)) * Rx;

        Point centerOfPlane = place.add(vto.scale(distance));
        Point pIJ = centerOfPlane;
        if (xJ != 0) pIJ = pIJ.add(vright.scale(xJ));
        if (yI != 0) pIJ = pIJ.add(vup.scale(yI));

        return pIJ;
    }
    /*get the index of a pixel and return a grid with ray's po */
    private List<Point> getGridDOF(int xIndex, int yIndex, int nx, int ny) {
        Point pixelCenter = constructPixelPoint(nx,ny,xIndex,yIndex);
        if (Util.isZero(aperture)) return List.of(pixelCenter);
        List<Point> targetArea = new LinkedList<>();
        Point leftCorner = pixelCenter.add(vup.scale(aperture / 2)).add(vright.scale(-aperture / 2));
        Point current = leftCorner;
        for (int i = 0; i < samples; ++i)
        {
            if (i != 0)
                current = leftCorner.add(vup.scale(-aperture * i / samples));
            for (int j = 0; j < samples; ++j) {
                targetArea.add(current);
                current = current.add(vright.scale(aperture / samples));
            }
        }
        return targetArea;
    }


    private Color castRay (int xIndex, int yIndex)
    {
            switch (antiAliasing) {
                case NONE -> {
                    return rayTracerBasic.traceRay(constructRay(imageWriter.getNx(), imageWriter.getNy(), xIndex, yIndex));

                }
                case GRID -> {
                    return rayTracerBasic.traceBeamRay(constructRayBeam(yIndex, xIndex, imageWriter.getNx(), imageWriter.getNy(), samples, samples, highet / imageWriter.getNy(), width / imageWriter.getNx()));
                }
                case Random -> {
                    return rayTracerBasic.traceBeamRay(constructRayBeamRandom(yIndex, xIndex, imageWriter.getNx(), imageWriter.getNy(), samples, samples, highet / imageWriter.getNy(), width / imageWriter.getNx()));
                }
                case DOF -> {
                    Ray mainRay = constructRay(imageWriter.getNx(), imageWriter.getNy(), xIndex, yIndex);
                    if (aperture == 0) {
                        return rayTracerBasic.traceRay(mainRay);
                    }
                    Point focalPoint = DOFPlane.findIntersections(mainRay).get(0);
                    List<Point> gridPoints = getGridDOF(xIndex, yIndex, imageWriter.getNx(), imageWriter.getNy());
                    List<Ray> DOFBeam = Ray.generateRayBeamToPoint(gridPoints, focalPoint);
                    //List<Ray> DOFBeam = constructDOFBeam(xIndex,yIndex, imageWriter.getNx(), imageWriter.getNy(),focalPoint);
                    DOFBeam.add(mainRay);
                    Color color = rayTracerBasic.traceBeamRay(DOFBeam);
                    Color color1=Color.BLACK;
                    Color mainColor = rayTracerBasic.traceRay(mainRay);
                    for (Ray ray:
                         DOFBeam) {
                        Color tmp = rayTracerBasic.traceRay(ray);
                        color1= color1.add(tmp);
                    }
                    int k = samples * samples + 1;
                    color1 = color1.reduce(k);
                    return  color1;
                }

                default -> {
                    throw (new UnsupportedOperationException("one or more of the field is not inialized"));
                }
            }
    }
    public Camera setAngle(double angle, Vector k) {

        vto = vto.Roatate(angle, k).normalize();
        vup = vup.Roatate(angle, k).normalize();
        vright = vto.crossProduct(vup);
        return this;
    }
    /**/

    public Camera  renderImage()
    {
        if (imageWriter==null) throw (new IllegalArgumentException("image writer had not been inatilized"));
            int nX = imageWriter.getNx();
            int ny = imageWriter.getNy();
            if (threads==1)
            {
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
            Pixel.initialize(ny,nX,intervalThread);
            while (threads-- > 0 )
            {
                new Thread(()->{
                    for (Pixel pixel= new Pixel(); pixel.nextPixel();Pixel.pixelDone())
                    {
                        int col = pixel.col;
                        int row = pixel.row;
                        imageWriter.writePixel(col, row,castRay(col, row));
                    }
                }).start();
            }
            Pixel.waitToFinish();
            return this;
    }

    /*gets color and interval and paint a grid upon the image*/
    public Camera printGrid(int interval,Color color)
    {
        imageWriter.printGrid(interval,color);
        return this;
    }
    /*create image from the information*/
    public void writeToImage()
    {
        if (imageWriter == null)
            throw new MissingResourceException("image writer is not initialized", ImageWriter.class.getName(), "");
        imageWriter.writeToImage();
    }
    public Camera(Point place, Vector vto, Vector vup) {

        this.place = place;
        if (!Util.isZero(vto.dotProduct(vup))) throw (new IllegalArgumentException("Vector is not orthogonal"));
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
        //double yi = -Math.floor(Math.random() *(radius - 0 + 1) + 0);//generate y coordinate from 0 to radius
        double yi = Util.random(-radius,radius);
        if (yi !=0 ) randomPoint = randomPoint.add(vup.scale(yi));
        double limit = Math.sqrt(radius * radius - yi * yi);
        double xj = Util.random(-limit, limit);
        //double xj = Math.floor(Math.random() *((radius-yi) - 0 + 1) + 0);//generate x coordinate from 0 to radius-y cordinate so the sum of the distance from the center in absoolute do not be greater then radius
        if (xj !=0 ) randomPoint = randomPoint.add(vright.scale(xj));
        return randomPoint;
    }
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
    public Point getCenterOfPixel(int i, int j, int nX,int nY,double pixelHighet,double pixelWidth)
    {
        Point center = getPlace().add(vto.scale(distance));
        double yi = -(i - ((double)nY - 1) / 2) * pixelHighet;
        if (yi !=0 ) center = center.add(vup.scale(yi));
        double xj = (j - ((double)nX - 1) / 2) * pixelWidth;
        if (xj !=0 ) center = center.add(vright.scale(xj));
        return center;
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
    public  Camera(Point newPosition, Point target)
    {
        cameraPosition(newPosition,target,0);
    }
    public Camera cameraPosition(Point newPosition, Point target, double angle) {
        place = newPosition;
        vto = target.subtract(newPosition).normalize();
        try {
            vright = vto.crossProduct(Vector.Y).normalize();
            vup = vto.crossProduct(vright).normalize();

        } catch (IllegalArgumentException e) {
            vup = Vector.Z;
            vright = vto.crossProduct(vup).normalize();
        }
        return angle == 0 ? this : rotateCamera(angle);
    }
    public Camera rotateCamera(double angle) {
        angle = Math.toRadians(angle);
        vup = vup.vectorRotate(vto, angle);
        vright = vup.crossProduct(vto).normalize();
        return this;
    }
    public Camera switchUpRight()
    {
        Vector tmp = vright;
        vright = vup;
        vup = tmp;
        return this;
    }

}
