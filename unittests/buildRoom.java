import geometries.Plane;
import geometries.Polygon;
import geometries.*;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.PointLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

public class buildRoom {
    Scene scene = new Scene("buildRoomimage");
    Color SKY = new Color(115,235,249);
    Color LIGHTGRAY = new Color(java.awt.Color.LIGHT_GRAY);

    Color RED = new Color(255,0,0);
    Color GRAY = new Color(java.awt.Color.gray);

    Color PURPALE = new Color(67,2,125);
    Color phooksia = new Color(244,108,255);
    Color GREEN = new Color(0,255,0);
    Color YELLOW = new Color(0,255,255);
    double kt = 0.15;
    double kd = 0.3;
    double ks = 0.3;
    double kr = 0.4;
    @Test
    void testName() {
        double kt = 0.001;
        double kd = 1;
        double ks = 1;
        double kr = 0.001;
        scene.setAmbientLight(new AmbientLight(GREEN,new Double3(0.1)));
        Material glassMatetrial = new Material().setkT(kt).setKd(kd).setKs(ks).setkR(kr);
        scene.lights.add(
                new DirectionalLight(new Color(100,100,100),new Vector(-1.012534429138574, -3.682651905023338, -2))
        );
        Point cameraPosition = new Point(-9, 5, 2);
        Vector cameraDirectionTo =new Point(1,0,1).subtract(cameraPosition);
        Vector cameraDirectionUp = cameraDirectionTo.Roatate(90,new Vector(0,0,1));

        Camera camera = new Camera(cameraPosition, new Point(0,0,-1)).switchUpRight() //
                .setVPSize(200, 200).setVPDistance(1000).setAngle(10,new Vector(0,-1,0));
        Point A=new Point(0,0,0),
                B= new Point(-3,0,0),
                C= new Point(0,2,0),
                D = new Point(-3,2,0);
        Point A1=new Point(0,0,0.5),
                B1= new Point(-3,0,0.5),
                C1= new Point(0,2,0.5),
                D1 = new Point(-3,2,0.5);
        Material wallMatirial = new Material().setKs(0.0001).setKd(0.001).setnShininess(1);
        Point lampBaseB = C.add(new Point(-1, 0, 0));
       /* scene.geometries.add(
                //new Sphere(0.5,new Point(-0.5,3.5,2)).setMaterial(glassMatetrial).setEmission(PURPALE),
                new Polygon(new Point(-1.5,0,0),Point.ZERO,new Point(0,0,2),new Point(-1.5,0,2)).setMaterial(wallMatirial).setEmission(LIGHTGRAY)
                //,new Plane(new Vector(0,0,1),Point.ZERO).setMaterial(wallMatirial)
                ,new Polygon(new Point(-2.5,0,0),new Point(-5,0,0),new Point(-5,0,2),new Point(-2.5,0,2)).setEmission(LIGHTGRAY)
                ,new Polygon(new Point(-1.5,0,0),new Point(-2.5,0,0),new Point(-2.5,0,0.5),new Point(-1.5,0,0.5)).setEmission(LIGHTGRAY)
                ,new Polygon(new Point(-2.5,0,2),new Point(-1.5,0,2),new Point(-1.5,0,1.25),new Point(-2.5,0,1.25)).setEmission(LIGHTGRAY)
                //,new Polygon(new Point(-5,0,1.5),new Point(-2.5,0,1.5),new Point(-5,0,2),new Point(-2.5,0,2))
                ,new Plane(new Vector(0,0,1),Point.ZERO).setMaterial(wallMatirial).setEmission(GRAY),
                new Plane(new Vector(0,1,0),Point.ZERO).setMaterial(wallMatirial).setEmission(LIGHTGRAY)
                //new Plane(new Vector(0,0,-1),new Point(0,0,100))


        );*/
        Color WOODCOLOR = new Color(133, 94, 66);
        Material WOODMETIRIAL = new Material().setKs(0.1).setKd(0.1).setnShininess(1);
        scene.geometries.add(
                new Floor (new Point(0, 0, 0), new Vector(0, 0, 1), new Vector(-1, 0, 0),
                        PURPALE, SKY, 4, 4, 10, 10,new Material()).getElements()
    ,new Floor (new Point(0, 0, 0), new Vector(0, 0, 1), new Vector(0, 1, 0),
                       PURPALE, SKY, 4, 4, 10, 10,new Material()).getElements()
                ,new Floor (new Point(0, 0, 0), new Vector(-1, 0, 0), new Vector(0, 1, 0),
                        YELLOW, LIGHTGRAY, 4, 4, 10, 10,new Material().setkR(0.01).setKs(0.1).setKd(0.1).setnShininess(2)).getElements()
                ,new Table(0.5,0.25, WOODCOLOR,new Point(-0.5,0.5,0),new Vector(0,0,1),new Vector(0,0,1)).setMaterialBars(WOODMETIRIAL).setMaterialBase(WOODMETIRIAL).setMaterialLeg(WOODMETIRIAL).setMaterialSurfaceBase(WOODMETIRIAL).setMaterialSurfaceTop(WOODMETIRIAL).getElements()
                ,new Table(0.5,0.25, WOODCOLOR,new Point(-1,0.5,0),new Vector(0,0,1),new Vector(0,0,1)).setMaterialBars(WOODMETIRIAL).setMaterialBase(WOODMETIRIAL).setMaterialLeg(WOODMETIRIAL).setMaterialSurfaceBase(WOODMETIRIAL).setMaterialSurfaceTop(WOODMETIRIAL).getElements()
                ,new Table(0.5,0.25, WOODCOLOR,new Point(-1.5,0.5,0),new Vector(0,0,1),new Vector(0,0,1)).setMaterialBars(WOODMETIRIAL).setMaterialBase(WOODMETIRIAL).setMaterialLeg(WOODMETIRIAL).setMaterialSurfaceBase(WOODMETIRIAL).setMaterialSurfaceTop(WOODMETIRIAL).getElements()
                ,new Chair(new Point(-1.5,1.45,1),0.2,0.4,0.005,0.03,0.01,0.01
                        ,new Vector(0,-1,0),new Vector(1,0,0),WOODCOLOR,true).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).setSeatMaterial(WOODMETIRIAL).setSeatCoverMaterial(WOODMETIRIAL).setBrCoverMaterial(WOODMETIRIAL).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).getGeometries()
                ,new Chair(new Point(-1.1,1.45,1),0.2,0.4,0.005,0.03,0.01,0.01
                        ,new Vector(0,-1,0),new Vector(1,0,0),WOODCOLOR,true).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).setSeatMaterial(WOODMETIRIAL).setSeatCoverMaterial(WOODMETIRIAL).setBrCoverMaterial(WOODMETIRIAL).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).getGeometries()
                ,new Floor(new Point(-0.1,0.1,0.2),new Vector(-1,0,0),new Vector(0,0,1),Color.BLACK,Color.BLACK,1.5,1.5,8,20,new Material().setkR(0.5).setKs(0.3).setKd(0.3)).getElements()
                ,new Floor(new Point(-0.1,0.1,0.2),new Vector(0,1,0),new Vector(0,0,1),Color.BLACK,Color.BLACK,1.5,1.5,8,20,new Material().setkR(0.5).setKs(0.3).setKd(0.3)).getElements()

        );
        scene.lights.add(
                new PointLight(new Color(0.1,0.1,0.1),new Point(-0.5,3.5,2))
        );

        camera.setImageWriter(new ImageWriter("roomImage", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();





    }
}
