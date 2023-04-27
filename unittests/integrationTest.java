import geometries.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import renderer.Camera;

import java.util.ArrayList;
import java.util.List;

public class integrationTest {
    /**
     * Test method for Sphere.
     * interation test for constructing ray through view plane
     */
    static final int Nx = 3;
    static final int Ny = 3;
    List<Point> createViewPlane(Geometries geometries, Camera camera)
    {
        List<Point> result = new ArrayList<>();
        Point pc = camera.getPlace().add(camera.getVto().scale(camera.getDistance()));
        double ry =camera.getHighet()/Ny;
        double rx= camera.getWidth()/Nx;
        camera.setVPSize(3, 3).setVPDistance(4);
        for (int i=0;i<Ny;++i)
        {
            double yi = -(i-(Ny-1)/2)*ry;
            for (int j=0;j<Nx;++j)
            {

                result.addAll(geometries.findIntsersections( camera.constructRay(Nx, Ny, j, i)));

            }
        }
        return result;
    }
    @Test
    void SphereTest() {
        Sphere sphere = new Sphere(2,new Point(-3,0,0));
        Camera camera = new Camera(new Point(4,0,0),new Vector(-1,0,0),new Vector(0,0,1));
        camera.setVPSize(3,3).setVPDistance(4);
        assertEquals(
                10,
                createViewPlane(new Geometries(sphere),camera).size(),
                "integration test for sphere doesn't work"
        );
    }

    /**
     * Test method for plane.
     * interation test for constructing ray through view plane
     */
    @Test
    void TriangleTest() {
        Camera camera = new Camera(new Point(4,0,0),new Vector(-1,0,0),new Vector(0,0,1));
        camera.setVPSize(3,3).setVPDistance(4);
        Triangle triangle = new Triangle(new Point(0,0,-4),new Point(-6,0,0),new Point(0, -16.516541450298497, 0));
        assertEquals(
                1,
                createViewPlane(new Geometries(triangle),camera).size(),
                "integration test for plane doesn't work"
        );

    }

    /**
     * Test method for plane.
     * interation test for constructing ray through view plane
     */
    @Test
    void PlaneTest() {
        Camera camera = new Camera(new Point(4,0,0),new Vector(-1,0,0),new Vector(0,0,1));
        camera.setVPSize(3,3).setVPDistance(4);
        Plane plane = new Plane(new Point(0,0,-2),new Point(0,-4,0),new Point(-3,0,0));
        assertEquals(
                9,
                createViewPlane(new Geometries(plane),camera).size(),
                "integration test for plane doesn't work"
        );

    }
}
