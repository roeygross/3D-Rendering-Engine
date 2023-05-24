package geometriesTest;

import geometries.Plane;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;
class TriangleTest {

    /**
     * Test method for {@link .geometries.Triangle.GetNormal(.geometries.Triangle)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Point[] pts =
                { new Point(0, 0, 0), new Point(1, 0, 0), new Point(1, 0.5, 0) };
        Triangle triangle = new Triangle(pts);
        //assertThrows(IllegalArgumentException.class,()->triangle.getNormal(new Point(1,0,0)),"Triangle getNormal- point not on the triangle case doesnt throw exception");
        Vector normal = triangle.getNormal(new Point(0,0,0));
        // ensure |result| = 1
        assertTrue( isZero(normal.length()-1),"Triangle -getNormal - the returned vector is not a unit vector");
        // ensure there are no exceptions
        assertDoesNotThrow(() -> triangle.getNormal(new Point(0, 0, 1)), "");
        // generate the test result
        // ensure the result is orthogonal to all the edges
        for (int i = 0; i < 3; ++i)
            assertTrue(isZero(normal.dotProduct(pts[i].subtract(pts[i == 0 ? 2 : i - 1]))),
                    "Polygon's normal is not orthogonal to one of the edges");
    }

    /**
     * Test method for {@link .geometries.Triangle.FindIntsersections(.geometries.Triangle)}.
     */
    @Test
    void testFindIntsersections() {
        Triangle triangle = new Triangle(new Point(-1,0,0),new Point(0,1,0),new Point(0,0,1));
        Plane plane = new Plane(new Point(-1,0,0),new Point(0,1,0),new Point(0,0,1));

        /* ============ first Equivalence Partitions Tests ==============  one intersect*/
        assertEquals(
                new ArrayList() {{add(new Point(-0.312267938755213, 0.303773122945835, 0.383958938298951));}},
                triangle.findIntersections(new Ray(new Point  (-0.161168247993315, 0.156783889527054, 0.198169525932423),new Vector (-0.681654255131519,0.663110797336576,0.838149587596402)))
                ,"triangle findIntserctions doesn't work"
        );
        // ============ second Equivalence Partitions Tests ============== the ray intersect the plane which contain the triangle
        assertEquals(
                null,
                triangle.findIntersections(new Ray(new Point  (0,-1,0),new Vector (0.491148577250395, 1.840858465907946, 0.650290111342449)))
                ,"triangle findIntserctions doesn't work"
        );
        // ============ third Equivalence Partitions Tests ============== the ray is not intersect the plane which contain the triangle
        assertEquals(
                null,
                triangle.findIntersections(new Ray(new Point  (0,0,-1),new Vector (-2.882078564508556, -0.682305953275544, 1.575279632945972)))
                ,"triangle findIntserctions doesn't work"
        );
        triangle = new Triangle(new Point(0,1,0),new Point(-1,0,0),new Point(0,0,2));

        // =============== first Boundary Value Tests ================== against the edge
        assertEquals(
                null,
                triangle.findIntersections(new Ray(new Point  (0,0,-1),new Vector (-0.488126535616246, 0.511873464383754, 1)))
                ,"triangle findIntserctions doesn't work"
        );
        // =============== second Boundary Value Tests ================== against the vertex
        assertEquals(
                null,
                triangle.findIntersections(new Ray(new Point  (1,0,0),new Vector (-1.663099686770676, 0, 3.326199373541352)))
                ,"triangle findIntserctions doesn't work"
        );
        // =============== third Boundary Value Tests ================== on the vertex
        assertEquals(
                null,
                triangle.findIntersections(new Ray(new Point  (2,0,0),new Vector  (-2, 3.1272332639975,0)))
                ,"triangle findIntserctions doesn't work"
        );

    }
}