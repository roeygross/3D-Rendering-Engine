package scene;
import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.*;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Scene {
    public String name;
    public Color background = Color.BLACK;
    public  AmbientLight ambientLight = AmbientLight.NONE;
    public Geometries geometries = new Geometries();
    public List<LightSource> lights = new LinkedList<>();

    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }

    public Scene(String name) {
        this.name = name;
    }

    public Scene setName(String name) {
        this.name = name;
        return  this;
    }
    /*method get xml file and fil the info into the class*/
    public Scene xmlSet(File xmlFile)
    {
        
        return this;
    }
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

}
