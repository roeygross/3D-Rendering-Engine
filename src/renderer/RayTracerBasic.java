package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;


public class RayTracerBasic extends RayTracerBase{
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
        for (LightSource lightSource: scene.lights)//for all light source gather the |l*n|
        {
             l = lightSource.getL(geoPoint.point);
             normal = geoPoint.getNormal();
            if (Double3.ZERO.lowerThan(l.dotProduct(normal)*normal.dotProduct(vto))) //chek if the camera and the light source are on the same side of the surface
            {
                iL = iL.add(lightSource.getIntensity(geoPoint.point));
                kDtmp += Math.abs(l.dotProduct(normal));
                 r = l.subtract(normal.scale(2 * l.dotProduct(normal))).normalize();//reflecting vector from the surface
                kStmp += Math.pow((Math.max(0, vto.scale(-1).dotProduct(r))), geoPoint.getNShininess());
            }
        }
        kDS = geoPoint.getKD().scale(kDtmp).add(geoPoint.getKS().scale(kStmp));
        iL = iL.scale(kDS);
        return iA.add(iE).add(iL);//𝑰𝑷 = 𝒌𝑨 ∙ 𝑰𝑨 + 𝑰𝑬 + 𝒌𝑫 ∙ 𝒍 ∙ 𝒏 + 𝒌𝑺 ∙ −𝒗 ∙ 𝒓𝒏𝒔𝒉 ∙ �
    }
    @Override
    public Color traceRay(Ray ray,Vector vto) {

        return calcColor(ray.findClosestGeoPoint(scene.geometries.findGeoIntersectionsHelper(ray)),vto);
    }

}
