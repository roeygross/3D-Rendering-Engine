package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;


public class RayTracerBasic extends RayTracerBase {
    private static final double DELTA = 0.1; //constance varible for changing ray starting point

    private boolean unshaded(GeoPoint gp, Vector l, Vector normal,double ln, LightSource lightSource)//checks if the point should be shaded
    {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector delta = normal.scale(normal.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);
        Point point = gp.add(delta);
        Ray shadowRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = scene.findGeoIntersections(shadowRay,lightSource.getDistance(point));
        return intersections.isEmpty();
    }

    public RayTracerBasic(Scene scene) {
        super(scene);
    }
    private Color calcColor (GeoPoint geoPoint,Vector vto)
    {

            if (geoPoint == null) return scene.background;
            Color iA = scene.ambientLight.getIntensity();//ambient light
            Color iE = geoPoint.getIE();//emission light
            Color iL = Color.BLACK;//light from light sources in the scene
            double kDtmp =0;
            double kStmp =0;
            Double3 kDS  ;
            Vector l;
            Vector normal;
            Vector r;
            double narrowBeam;
            for (LightSource lightSource: scene.lights)//for all light source gather the |l*n|
            {
                l = lightSource.getL(geoPoint.point);
                normal = geoPoint.getNormal();
                double ln = l.dotProduct(normal);
                if (Double3.ZERO.lowerThan(ln *normal.dotProduct(vto))&&unshaded(geoPoint,l,normal,ln,lightSource)) //chek if the camera and the light source are on the same side of the surface
                {

                    narrowBeam = lightSource.getNarrowBeam();
                    iL = iL.add(lightSource.getIntensity(geoPoint.point));
                    kDtmp += Math.pow(Math.abs(ln),narrowBeam);
                    r = l.subtract(normal.scale(2 * ln)).normalize();//reflecting vector from the surface
                    kStmp += Math.pow((Math.max(0, vto.scale(-1).dotProduct(r))), geoPoint.getNShininess());
                }
            }
            kDS = geoPoint.getKD().scale(kDtmp).add(geoPoint.getKS().scale(kStmp));
            iL = iL.scale(kDS);
            return iA.add(iE).add(iL);//𝑰𝑷 = 𝒌𝑨 ∙ 𝑰𝑨 + 𝑰𝑬 + 𝒌𝑫 ∙ 𝒍 ∙ 𝒏 + 𝒌𝑺 ∙ −𝒗 ∙ 𝒓𝒏𝒔𝒉 ∙ �



    }
    @Override
    public Color traceRay(Ray ray,Vector vto) {

        return calcColor(ray.findClosestGeoPoint(scene.findGeoIntersections(ray)),vto);
    }

}
