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
    Color WHITE = new Color(java.awt.Color.WHITE);
    Color BLACK = new Color(java.awt.Color.BLACK);

    Color RED = new Color(255,0,0);
    Color GRAY = new Color(java.awt.Color.gray);
    Color DARKBLUE = new Color(0, 0, 102);
    Color PURPALE = new Color(67,2,125);
    Color phooksia = new Color(244,108,255);
    Color GREEN = new Color(0,255,0);
    Color YELLOW = new Color(255,255,0);
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
        Point cameraPosition = new Point(-1800, 1000, 400);

        Camera camera = new Camera(cameraPosition, new Point(0,0,-1)).switchUpRight() //
                .setVPSize(200, 200).setVPDistance(100).setAngle(10,new Vector(0,-1,0));
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
        Color DARKWOODCOLOR = new Color(77, 38, 0);
        Material WOODMETIRIAL = new Material().setKs(0.1).setKd(0.1).setnShininess(1);
        scene.geometries.add(
                //walls
                new Floor (new Point(0, 0, 0), new Vector(0, 0, 1), new Vector(-1, 0, 0),
                        RED, YELLOW, 2000, 2000, 20, 20,new Material()).getElements()
                ,new Floor (new Point(0, 0, 0), new Vector(0, 0, 1), new Vector(0, 1, 0),
                       RED, YELLOW, 2000, 2000, 20, 20,new Material()).getElements()
                //floor
                ,new Floor (new Point(0, 0, 0), new Vector(-1, 0, 0), new Vector(0, 1, 0),
                        BLACK, WHITE, 2000, 2000, 15, 15,new Material().setkR(0.01).setKs(0.1).setKd(0.1).setnShininess(2)).getElements()
                //roof
                ,new Floor (new Point(0, 0, 500), new Vector(-1, 0, 0), new Vector(0, 1, 0),
                        DARKBLUE, PURPALE, 2000, 2000, 10, 10,new Material().setkR(0.01).setKs(0.1).setKd(0.1).setnShininess(2)).getElements()

                //0,0 is A0, positive y is 1,2,3 and negative x is A B C D
                //A1,2 Tables and chairs
                ,new Table(160,60, DARKWOODCOLOR,new Point(-100,600,0),new Vector(0,0,1),new Vector(1,0,0)).setMaterialBars(WOODMETIRIAL).setMaterialBase(WOODMETIRIAL).setMaterialLeg(WOODMETIRIAL).setMaterialSurfaceBase(WOODMETIRIAL).setMaterialSurfaceTop(WOODMETIRIAL).getElements()
                ,new Table(160,60, DARKWOODCOLOR,new Point(-100,200,0),new Vector(0,0,1),new Vector(1,0,0)).setMaterialBars(WOODMETIRIAL).setMaterialBase(WOODMETIRIAL).setMaterialLeg(WOODMETIRIAL).setMaterialSurfaceBase(WOODMETIRIAL).setMaterialSurfaceTop(WOODMETIRIAL).getElements()
                ,new Chair(new Point(-100,700,100),100,200,2.5,15,5,5
                        ,new Vector(0,-1,0),new Vector(1,0,0),WOODCOLOR,true).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).setSeatMaterial(WOODMETIRIAL).setSeatCoverMaterial(WOODMETIRIAL).setBrCoverMaterial(WOODMETIRIAL).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).getGeometries()
                ,new Chair(new Point(-100,500,100),100,200,2.5,15,5,5
                        ,new Vector(0,1,0),new Vector(-1,0,0),WOODCOLOR,true).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).setSeatMaterial(WOODMETIRIAL).setSeatCoverMaterial(WOODMETIRIAL).setBrCoverMaterial(WOODMETIRIAL).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).getGeometries()
                ,new Chair(new Point(-100,300,100),100,200,2.5,15,5,5
                    ,new Vector(0,-1,0),new Vector(1,0,0),WOODCOLOR,true).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).setSeatMaterial(WOODMETIRIAL).setSeatCoverMaterial(WOODMETIRIAL).setBrCoverMaterial(WOODMETIRIAL).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).getGeometries()
                ,new Chair(new Point(-100,100,100),100,200,2.5,15,5,5
                    ,new Vector(0,1,0),new Vector(-1,0,0),WOODCOLOR,true).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).setSeatMaterial(WOODMETIRIAL).setSeatCoverMaterial(WOODMETIRIAL).setBrCoverMaterial(WOODMETIRIAL).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).getGeometries()
                //A3,4 Tables and chairs
                ,new Table(160,60, DARKWOODCOLOR,new Point(-600,600,0),new Vector(0,0,1),new Vector(1,0,0)).setMaterialBars(WOODMETIRIAL).setMaterialBase(WOODMETIRIAL).setMaterialLeg(WOODMETIRIAL).setMaterialSurfaceBase(WOODMETIRIAL).setMaterialSurfaceTop(WOODMETIRIAL).getElements()
                ,new Table(160,60, DARKWOODCOLOR,new Point(-600,200,0),new Vector(0,0,1),new Vector(1,0,0)).setMaterialBars(WOODMETIRIAL).setMaterialBase(WOODMETIRIAL).setMaterialLeg(WOODMETIRIAL).setMaterialSurfaceBase(WOODMETIRIAL).setMaterialSurfaceTop(WOODMETIRIAL).getElements()
                ,new Chair(new Point(-600,700,100),100,200,2.5,15,5,5
                        ,new Vector(0,-1,0),new Vector(1,0,0),WOODCOLOR,true).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).setSeatMaterial(WOODMETIRIAL).setSeatCoverMaterial(WOODMETIRIAL).setBrCoverMaterial(WOODMETIRIAL).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).getGeometries()
                ,new Chair(new Point(-600,500,100),100,200,2.5,15,5,5
                        ,new Vector(0,1,0),new Vector(-1,0,0),WOODCOLOR,true).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).setSeatMaterial(WOODMETIRIAL).setSeatCoverMaterial(WOODMETIRIAL).setBrCoverMaterial(WOODMETIRIAL).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).getGeometries()
                ,new Chair(new Point(-600,300,100),100,200,2.5,15,5,5
                        ,new Vector(0,-1,0),new Vector(1,0,0),WOODCOLOR,true).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).setSeatMaterial(WOODMETIRIAL).setSeatCoverMaterial(WOODMETIRIAL).setBrCoverMaterial(WOODMETIRIAL).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).getGeometries()
                ,new Chair(new Point(-600,100,100),100,200,2.5,15,5,5
                        ,new Vector(0,1,0),new Vector(-1,0,0),WOODCOLOR,true).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).setSeatMaterial(WOODMETIRIAL).setSeatCoverMaterial(WOODMETIRIAL).setBrCoverMaterial(WOODMETIRIAL).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).getGeometries()
                //B1,2 Tables and chairs
                ,new Table(160,60, DARKWOODCOLOR,new Point(-100,1400,0),new Vector(0,0,1),new Vector(1,0,0)).setMaterialBars(WOODMETIRIAL).setMaterialBase(WOODMETIRIAL).setMaterialLeg(WOODMETIRIAL).setMaterialSurfaceBase(WOODMETIRIAL).setMaterialSurfaceTop(WOODMETIRIAL).getElements()
                ,new Table(160,60, DARKWOODCOLOR,new Point(-100,1000,0),new Vector(0,0,1),new Vector(1,0,0)).setMaterialBars(WOODMETIRIAL).setMaterialBase(WOODMETIRIAL).setMaterialLeg(WOODMETIRIAL).setMaterialSurfaceBase(WOODMETIRIAL).setMaterialSurfaceTop(WOODMETIRIAL).getElements()
                ,new Chair(new Point(-100,1500,100),100,200,2.5,15,5,5
                        ,new Vector(0,-1,0),new Vector(1,0,0),WOODCOLOR,true).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).setSeatMaterial(WOODMETIRIAL).setSeatCoverMaterial(WOODMETIRIAL).setBrCoverMaterial(WOODMETIRIAL).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).getGeometries()
                ,new Chair(new Point(-100,1300,100),100,200,2.5,15,5,5
                        ,new Vector(0,1,0),new Vector(-1,0,0),WOODCOLOR,true).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).setSeatMaterial(WOODMETIRIAL).setSeatCoverMaterial(WOODMETIRIAL).setBrCoverMaterial(WOODMETIRIAL).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).getGeometries()
                ,new Chair(new Point(-100,1100,100),100,200,2.5,15,5,5
                        ,new Vector(0,-1,0),new Vector(1,0,0),WOODCOLOR,true).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).setSeatMaterial(WOODMETIRIAL).setSeatCoverMaterial(WOODMETIRIAL).setBrCoverMaterial(WOODMETIRIAL).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).getGeometries()
                ,new Chair(new Point(-100,900,100),100,200,2.5,15,5,5
                        ,new Vector(0,1,0),new Vector(-1,0,0),WOODCOLOR,true).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).setSeatMaterial(WOODMETIRIAL).setSeatCoverMaterial(WOODMETIRIAL).setBrCoverMaterial(WOODMETIRIAL).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).getGeometries()
                //B3, 4 Tables and chairs
                ,new Table(160,60, DARKWOODCOLOR,new Point(-600,1400,0),new Vector(0,0,1),new Vector(1,0,0)).setMaterialBars(WOODMETIRIAL).setMaterialBase(WOODMETIRIAL).setMaterialLeg(WOODMETIRIAL).setMaterialSurfaceBase(WOODMETIRIAL).setMaterialSurfaceTop(WOODMETIRIAL).getElements()
                ,new Table(160,60, DARKWOODCOLOR,new Point(-600,1000,0),new Vector(0,0,1),new Vector(1,0,0)).setMaterialBars(WOODMETIRIAL).setMaterialBase(WOODMETIRIAL).setMaterialLeg(WOODMETIRIAL).setMaterialSurfaceBase(WOODMETIRIAL).setMaterialSurfaceTop(WOODMETIRIAL).getElements()
                ,new Chair(new Point(-600,1500,100),100,200,2.5,15,5,5
                        ,new Vector(0,-1,0),new Vector(1,0,0),WOODCOLOR,true).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).setSeatMaterial(WOODMETIRIAL).setSeatCoverMaterial(WOODMETIRIAL).setBrCoverMaterial(WOODMETIRIAL).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).getGeometries()
                ,new Chair(new Point(-600,1300,100),100,200,2.5,15,5,5
                        ,new Vector(0,1,0),new Vector(-1,0,0),WOODCOLOR,true).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).setSeatMaterial(WOODMETIRIAL).setSeatCoverMaterial(WOODMETIRIAL).setBrCoverMaterial(WOODMETIRIAL).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).getGeometries()
                ,new Chair(new Point(-600,1100,100),100,200,2.5,15,5,5
                        ,new Vector(0,-1,0),new Vector(1,0,0),WOODCOLOR,true).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).setSeatMaterial(WOODMETIRIAL).setSeatCoverMaterial(WOODMETIRIAL).setBrCoverMaterial(WOODMETIRIAL).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).getGeometries()
                ,new Chair(new Point(-600,900,100),100,200,2.5,15,5,5
                        ,new Vector(0,1,0),new Vector(-1,0,0),WOODCOLOR,true).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).setSeatMaterial(WOODMETIRIAL).setSeatCoverMaterial(WOODMETIRIAL).setBrCoverMaterial(WOODMETIRIAL).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).getGeometries()
                //c1,2 tables and chairs
                ,new Table(160,60, DARKWOODCOLOR,new Point(-1200,900,0),new Vector(0,0,1),new Vector(1,0,0)).setMaterialBars(WOODMETIRIAL).setMaterialBase(WOODMETIRIAL).setMaterialLeg(WOODMETIRIAL).setMaterialSurfaceBase(WOODMETIRIAL).setMaterialSurfaceTop(WOODMETIRIAL).getElements()
                ,new Table(160,60, DARKWOODCOLOR,new Point(-1200,300,0),new Vector(0,0,1),new Vector(1,0,0)).setMaterialBars(WOODMETIRIAL).setMaterialBase(WOODMETIRIAL).setMaterialLeg(WOODMETIRIAL).setMaterialSurfaceBase(WOODMETIRIAL).setMaterialSurfaceTop(WOODMETIRIAL).getElements()
                ,new Chair(new Point(-1200,1000,100),100,200,2.5,15,5,5
                        ,new Vector(0,-1,0),new Vector(1,0,0),WOODCOLOR,true).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).setSeatMaterial(WOODMETIRIAL).setSeatCoverMaterial(WOODMETIRIAL).setBrCoverMaterial(WOODMETIRIAL).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).getGeometries()
                ,new Chair(new Point(-1200,800,100),100,200,2.5,15,5,5
                        ,new Vector(0,1,0),new Vector(-1,0,0),WOODCOLOR,true).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).setSeatMaterial(WOODMETIRIAL).setSeatCoverMaterial(WOODMETIRIAL).setBrCoverMaterial(WOODMETIRIAL).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).getGeometries()
                ,new Chair(new Point(-1200,400,100),100,200,2.5,15,5,5
                        ,new Vector(0,-1,0),new Vector(1,0,0),WOODCOLOR,true).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).setSeatMaterial(WOODMETIRIAL).setSeatCoverMaterial(WOODMETIRIAL).setBrCoverMaterial(WOODMETIRIAL).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).getGeometries()
                ,new Chair(new Point(-1200,200,100),100,200,2.5,15,5,5
                        ,new Vector(0,1,0),new Vector(-1,0,0),WOODCOLOR,true).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).setSeatMaterial(WOODMETIRIAL).setSeatCoverMaterial(WOODMETIRIAL).setBrCoverMaterial(WOODMETIRIAL).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).getGeometries()
                //d1,2 Tables and chairs
                ,new Table(160,60, DARKWOODCOLOR,new Point(-1500,900,0),new Vector(0,0,1),new Vector(1,0,0)).setMaterialBars(WOODMETIRIAL).setMaterialBase(WOODMETIRIAL).setMaterialLeg(WOODMETIRIAL).setMaterialSurfaceBase(WOODMETIRIAL).setMaterialSurfaceTop(WOODMETIRIAL).getElements()
                ,new Table(160,60, DARKWOODCOLOR,new Point(-1500,300,0),new Vector(0,0,1),new Vector(1,0,0)).setMaterialBars(WOODMETIRIAL).setMaterialBase(WOODMETIRIAL).setMaterialLeg(WOODMETIRIAL).setMaterialSurfaceBase(WOODMETIRIAL).setMaterialSurfaceTop(WOODMETIRIAL).getElements()
                ,new Chair(new Point(-1500,1000,100),100,200,2.5,15,5,5
                        ,new Vector(0,-1,0),new Vector(1,0,0),WOODCOLOR,true).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).setSeatMaterial(WOODMETIRIAL).setSeatCoverMaterial(WOODMETIRIAL).setBrCoverMaterial(WOODMETIRIAL).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).getGeometries()
                ,new Chair(new Point(-1500,800,100),100,200,2.5,15,5,5
                        ,new Vector(0,1,0),new Vector(-1,0,0),WOODCOLOR,true).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).setSeatMaterial(WOODMETIRIAL).setSeatCoverMaterial(WOODMETIRIAL).setBrCoverMaterial(WOODMETIRIAL).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).getGeometries()
                ,new Chair(new Point(-1500,400,100),100,200,2.5,15,5,5
                        ,new Vector(0,-1,0),new Vector(1,0,0),WOODCOLOR,true).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).setSeatMaterial(WOODMETIRIAL).setSeatCoverMaterial(WOODMETIRIAL).setBrCoverMaterial(WOODMETIRIAL).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).getGeometries()
                ,new Chair(new Point(-1500,200,100),100,200,2.5,15,5,5
                        ,new Vector(0,1,0),new Vector(-1,0,0),WOODCOLOR,true).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).setSeatMaterial(WOODMETIRIAL).setSeatCoverMaterial(WOODMETIRIAL).setBrCoverMaterial(WOODMETIRIAL).setLegsMaterial(WOODMETIRIAL).setBarsMaterial(WOODMETIRIAL).getGeometries()

                //mirrors
                ,new Floor(new Point(-0.1,0.1,100),new Vector(-1,0,0),new Vector(0,0,1),Color.BLACK,Color.BLACK,2000,200,1,1,new Material().setkR(0.5).setKs(0.3).setKd(0.3)).getElements()
                ,new Floor(new Point(-0.1,0.1,100),new Vector(0,1,0),new Vector(0,0,1),Color.BLACK,Color.BLACK,2000,200,1,1,new Material().setkR(0.5).setKs(0.3).setKd(0.3)).getElements()
               //little wall in middle
                ,new Floor (new Point(-900, 0, 0), new Vector(0, 0, 1), new Vector(0, 1, 0),
                     GRAY   , LIGHTGRAY, 125, 1200, 15, 15,new Material()).getElements()
                //walls behind camera
                ,new Floor (new Point(-1900, 0, 0), new Vector(0, 0, 1), new Vector(0, 1, 0),
                        RED, YELLOW, 2000, 2000, 20, 20,new Material()).getElements()
                , new Floor (new Point(0, 2000, 0), new Vector(0, 0, 1), new Vector(-1, 0, 0),
                        RED, YELLOW, 2000, 2000, 20, 20,new Material()).getElements()


        );
        scene.lights.add(
                new PointLight(new Color(0.1,0.1,0.1),new Point(-0.5,3.5,2))
        );
        camera.setAperture(0.1);

        camera.setImageWriter(new ImageWriter("roomImage", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();





    }
}
