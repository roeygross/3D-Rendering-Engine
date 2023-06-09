import geometries.Plane;
import geometries.Sphere;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import static java.awt.Color.*;

public class DiamondImage {
    private Scene scene = new Scene("imageBuild");
    private final Color          lightColor        = new Color(800, 500, 0);
    private final Color sphereColor = new Color(BLUE).reduce(2);
    private static final int SHININESS = 301;
    private static final double KD = 0.7;
    private static final Double3 KD3 = new Double3(0.2, 0.6, 0.4);

    private static final double KS = 0.7;
    private static final Double3 KS3 = new Double3(0.2, 0.4, 0.3);

    private final Material material = new Material().setKd(KD3).setks(KS3).setnShininess(SHININESS);
    private final Color trianglesLightColor = new Color(800, 500, 250);
    private final Color sphereLightColor = new Color(800, 500, 0);


    private final Point sphereCenter = new Point(0, 0, 0);
    private static final double SPHERE_RADIUS = 50d;
    Color SKY = new Color(115, 235, 249);
    Color RED = new Color(255, 0, 0);
    Color PURPALE = new Color(67, 2, 125);
    Color phooksia = new Color(244, 108, 255);
    Color GREEN = new Color(0, 255, 0);
    Color YELLOW = new Color(0, 255, 255);

    @Test
    void diamondImage() {


        Camera camera = new Camera(new Point(20, 0, 0), new Vector(-1, 0, 0), new Vector(0, 0, 1)) //
                .setVPSize(200, 200).setVPDistance(1000).rotateAroundPointRight(360, Point.ZERO).rotateAroundPointUP(0, Point.ZERO);
        double kt = 0.01;
        double kd = 0.4;
        double ks = 0.4;
        double kr = 0.01;
        scene.setAmbientLight(new AmbientLight(YELLOW, new Double3(0.1)));
        Point spotLightPoint = new Point(1 ,0, 2);
        scene.geometries
                .add(
                        new Plane(new Vector(0,0,1),Point.ZERO).setMaterial(new Material().setkT(kt).setKd(kd).setKs(ks).setkR(kr))
                       , new Sphere(1, spotLightPoint)
                );


        scene.lights.add(
               new SpotLight( new Color(800, 500, 0),spotLightPoint, spotLightPoint.subtract(Point.ZERO).scale(-1)).setkL(0.000000001).setkQ(0.000000000004)
        );
        scene.lights.add(
               new DirectionalLight(new Color(800, 500, 0),spotLightPoint.subtract(Point.ZERO).scale(1))
        );
        scene.lights.add(
               new PointLight( new Color(800, 500, 0),Point.ZERO).setkL(0.001).setkQ(0.0002)
        );
        scene.lights.add(
               new PointLight( new Color(800, 500, 0),spotLightPoint)
        );

        scene.setBackground(new Color(GRAY));
        camera.setImageWriter(new ImageWriter("diamondimage", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();

    }
}


