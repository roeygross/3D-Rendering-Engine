package renderer;
import primitives.*;
import scene.Scene;

import java.util.List;

public abstract class RayTracerBase {
    protected Scene scene;

    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }
    public  abstract Color traceRay(Ray ray);
    public abstract  Color traceBeamRay(List<Ray> beam);

    public abstract Color traceDOF(Ray mainRay, List<Ray> secondaryRays, Point focusTarget, double depth,double blurIntensity);
}
