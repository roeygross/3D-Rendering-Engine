package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;
import geometries.Intersectable.GeoPoint;


public class RayTracerBasic extends RayTracerBase{
    public RayTracerBasic(Scene scene) {
        super(scene);
    }
    private Color calcColor (GeoPoint geoPoint)
    {
        if (geoPoint == null) return scene.background;
        return  scene.ambientLight.getIntensity().add(geoPoint.getEmission());
    }
    @Override
    public Color traceRay(Ray ray) {

        return calcColor(ray.findClosestGeoPoint(scene.geometries.findGeoIntersectionsHelper(ray)));
    }

}
