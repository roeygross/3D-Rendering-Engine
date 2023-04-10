package geometriesTest;

import geometries.Plane;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;
class PlaneTest {

    /**
     * Test method for {@link .geometries.Plane.GetNormal(.geometries.Plane)}.
     */
    @Test
    public void testConstructor()
    {
        // ============ 1 Equivalence Partition Test ==============
        Plane plane= new Plane(new Point(0,0,0),new Point(0,1,0),new Point(0,0,1));
        Vector normal = plane.getNormal();
        assertTrue(isZero(1-normal.length()), "Plane- constructor- the normal is not a unit vector");
        assertTrue(isZero(new Vector(0,1,0).dotProduct(normal)) && isZero(new Vector(0,1,0).dotProduct(normal)),"Plane - constructor - the normal is wrong");
        // =============== first Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class,()->new Plane(new Point(1,0,0),new Point(2,0,0),new Point(3,0,0)),"Plane- constructor - 3 points on the same line case doesn't throw exception");
        // =============== second Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class,()->new Plane(new Point(0,0,0),new Point(2,0,0),new Point(2,0,0)),"Plane- constructor - two identical point case doesn't throw exception");

    }
    @Test
    void testGetNormal() {
        Point[] pts = { new Point(0, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0) };
        Plane plane= new Plane(new Point(0,0,0),new Point(1,0,0),new Point(0,1,0));
        // ensure there are no exceptions
        //assertThrows(IllegalArgumentException.class,()-> plane.getNormal(new Point(0,0,1)),"Plane - getNormal - point not on the plane case doesn't throw exception");
        // ============ Equivalence Partitions Tests ==============
        assertDoesNotThrow(() -> plane.getNormal(new Point(0, 0, 0)), "");
        Vector result = plane.getNormal(new Point(0,0,0));
        assertTrue(isZero(1-result.length()),"Plane - getNormal- the normal is not a  unit vector");
        // ensure the result is orthogonal to all the edges
        for (int i = 0; i < 3; ++i)
            assertTrue(isZero(result.dotProduct(pts[i].subtract(pts[i == 0 ? 2 : i - 1]))),
                    "Plane's normal is not orthogonal to one of the edges");


    }
}