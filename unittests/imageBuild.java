import geometries.*;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import static java.awt.Color.*;

public class imageBuild {
    private static final int     SHININESS               = 301;
    private static final double  KD                      = 0.7;
    private static final Double3 KD3                     = new Double3(0.2, 0.6, 0.4);

    private static final double  KS                      = 0.7;
    private static final Double3 KS3                     = new Double3(0.2, 0.4, 0.3);

    private final Material       material                = new Material().setKd(KD3).setks(KS3).setnShininess(SHININESS);
    private final Color          trianglesLightColor     = new Color(800, 500, 250);
    private final Color          sphereLightColor        = new Color(800, 500, 0);
    private final Color          sphereColor             = new Color(BLUE).reduce(2);

    private final Point          sphereCenter            = new Point(0, 0, -50);
    private static final double  SPHERE_RADIUS           = 50d;
    Color SKY = new Color(115,235,249);
    Color RED = new Color(255,0,0);
    Color PURPALE = new Color(67,2,125);
    Color phooksia = new Color(244,108,255);
    Color GREEN = new Color(0,255,0);
    Color YELLOW = new Color(0,255,255);
    private Scene scene = new Scene("imageBuild");


    /**
     * Test method for {@link .${CLASS_NAME}.Name(.${CLASS_NAME})}.
     */
    @Test
    void imageBuild() {

        Point A=new Point(100,0,0),
                B=new Point(0,0,100),
                C=new Point(0,100,0),
                D= new Point(-100,0,0),
                E = new Point(0,-100,0),
                F= new Point(0,0,0),
                G= new Point(0, 0, -59.6212025348053),
                H = new Point(0,0,-100);
        double radius= F.distance(G)/2;
        Camera camera = new Camera(new Point(2000,2000,0), new Vector(0,1,0), new Vector(0, 0, 1)) //
                .setVPSize(150, 150).setVPDistance(1000).rotateAroundPointRight(20,F).rotateAroundPointUP(20,F);
        double kt = 0.15;
        double kd = 0.3;
        double ks = 0.3;
        double kr = 0.4;
        scene.setAmbientLight(new AmbientLight(GREEN,new Double3(0.1)));
        Material glassMatetrial = new Material().setkT(kt).setKd(kd).setKs(ks).setkR(kr);
        /*scene.geometries
                .add(
                        new Triangle(A,B,C)
                                .setEmission(RED) //
                                .setMaterial(glassMatetrial)
                        ,new Triangle(C,B,D)
                                .setEmission(GREEN) //
                                .setMaterial(glassMatetrial),
                        new Triangle(D,B,E)
                                .setEmission(SKY) //
                                .setMaterial(glassMatetrial),
                        new Triangle(E,B,A)
                                .setEmission(PURPALE) //
                                .setMaterial(glassMatetrial),
                        new Triangle(E,H,A)                                .
                                setEmission(YELLOW) //
                                .setMaterial(glassMatetrial),
                        new Triangle(D,E,H)
                                .setEmission(RED) //
                                .setMaterial(glassMatetrial),
                        new Triangle(H,C,D)
                                .setEmission(SKY) //
                                .setMaterial(glassMatetrial),
                        new Triangle(H,A,C)
                                .setEmission(PURPALE) //
                                .setMaterial(glassMatetrial)

                );*/

       /* scene.geometries
                .add(
                        new Sphere(radius,F).
                                setEmission(sphereColor).setMaterial(new Material().setKd(KD).setKs(KS).setnShininess(SHININESS))
                );*/
        Diamond diamond= (Diamond) new Diamond(Point.ZERO,radius).setEmission(YELLOW).setMaterial(glassMatetrial);
         Polygon table= (Polygon) new Polygon(radius,Point.ZERO,8).setMaterial(glassMatetrial).setEmission(YELLOW);
        scene.geometries.add(
                table

        );



        scene.lights.add( //
                new SpotLight(phooksia, B.add(new Point(0,0,-1)), new Vector(0,0,-1)) //
                        .setkL(0.00000004).setkQ(0.0000000006).setNarrowBeam(0.5));
        scene.lights.add( //
                new SpotLight(new Color(1000, 600, 0), H.add(new Point(0,0,1)), new Vector(0,0,1)) //
                        .setkL(0.0000004).setkQ(0.0000006).setNarrowBeam(0.3));
        scene.lights.add(
                new DirectionalLight(new Color(100, 60, 0),new Vector(-1,0,0))
        );
        scene.setBackground(new Color(GRAY));


        camera.setImageWriter(new ImageWriter("imageBuild", 1000, 1000)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }



}