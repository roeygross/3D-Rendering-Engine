package geometriesTest;

import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

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
}