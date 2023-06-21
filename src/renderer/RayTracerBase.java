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

}
