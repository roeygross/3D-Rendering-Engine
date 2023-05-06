package primitivesTest;

import org.junit.jupiter.api.Test;
import primitives.Vector;
import primitives.Point;
import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Testing primitives.Point
 *
 * @author Yosef and Gross
 */
class PointTest {

    /**
     * Test method for {@link .primitives.Point.Subtract(.primitives.Point)}.
     */
    double ACCURACY  = 0.00001;
    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        Point p1 = new Point (3,3,3);
        Point p2 = new Point (2,1,0);
        assertDoesNotThrow(() -> p1.subtract(p2), "");
        Vector result = p1.subtract(p2);
        Vector expected = new Vector(1,2,3);

        assertEquals(expected,result,"ERROR-subtract");

        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class ,() -> p1.subtract( new Point (3,3,3)));//subtract point for similar point will give zero Vector that can not be tolerate on this project

    }

    /**
     * Test method for {@link .primitives.Point.Add(.primitives.Point)}.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        Point v1 = new Point (3,3,3);
        Point v2 = new Point (2,1,0);
        assertDoesNotThrow(() -> v1.add(v2));
        Point result = v1.add(v2);
        Point expected = new Point(5,4,3);
        assertEquals(expected,result,"ERROR-add");

    }

    /**
     * Test method for {@link .primitives.Point.DistanceSquared(.primitives.Point)}.
     */
    @Test
    void testDistanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        Point p1 = new Point (2,1,0);
        Point p2 = new Point (1,2,3);
        assertDoesNotThrow(() -> p1.distanceSquared(p2));
        double result= p1.distanceSquared(p2);
        double  expected = 11;
        assertEquals(expected,result,ACCURACY,"ERROR-distanceSquared");

    }

    /**
     * Test method for {@link .primitives.Point.Distance(.primitives.Point)}.
     */
    @Test
    void testDistance() {
        // ============ Equivalence Partitions Tests ==============
        Point p1 = new Point (2,1,0);
        Point p2 = new Point (1,2,3);
        assertDoesNotThrow(() ->p1.distance(p2));
        double result= p1.distance(p2);
        double  expected = sqrt(11);
        assertEquals(expected,result,ACCURACY,"ERROR-distanceSquared");

        // =============== Boundary Values Tests ==================

    }

}