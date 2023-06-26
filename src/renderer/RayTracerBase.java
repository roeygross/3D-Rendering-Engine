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

    public abstract Color AdaptiveSuperSamplingRec(Point centerP, double Width, double Height, double minWidth, double minHeight, Point cameraLoc, Vector Vright, Vector Vup, List<Point> prePoints);
}
