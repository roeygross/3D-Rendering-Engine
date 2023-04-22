package geometries;
import java.util.*;
import primitives.*;
/*
* interface for geometries that should provide the method findIntersection for all the forms
* all the information about the implementation of this method you may find on https://moodle.jct.ac.il/pluginfile.php/987458/mod_resource/content/46/4%20-%20ISE.pdf
* */
public interface Intersectable {
    List<Point> findIntsersections(Ray ray);
}
