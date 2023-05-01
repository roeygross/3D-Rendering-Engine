package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;
import java.util.List;


public class RayTracerBasic extends RayTracerBase{
    public RayTracerBasic(Scene scene) {
        super(scene);
    }
    private Color calcColor (Point point)
    {
        if (point == null) return scene.background;
        return  scene.ambientLight.getIntensity();
    }
    @Override
    public Color traceRay(Ray ray) {

        return calcColor(ray.findClosestPoint(scene.geometries.findIntsersections(ray)));
    }

}
