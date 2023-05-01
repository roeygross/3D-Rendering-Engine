package primitives;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    /**
     * Test method for {@link .primitives.Ray.FindClosestPoint(.primitives.Ray)}.
     */
    @Test
    void testFindClosestPoint() {
        // ============ Equivalence Partitions Tests ==============
        List<Point> pointList = new ArrayList<Point>(Arrays.asList(new Point(5,3,2),new Point(0,0,1),new Point(3,2,1),new Point(1,1,0)));
        Ray ray = new Ray( new Point(0,0,0),new Vector(1,1,1));
        assertEquals(
                new Point(0,0,1),
                ray.findClosestPoint(pointList),
                "findclosestpoint doesnt work"
        );
        // =============== Boundary Values Tests ==================
        ray = new Ray(new Point(1,1,1),new Vector(1,2,3));
        assertEquals(
                new Point(1,1,0),
                ray.findClosestPoint(pointList),
                "findclosestpoint doesnt work"
        );
        ray = new Ray(new Point(5,3,1),new Vector(1,2,3));
        assertEquals(
                new Point(5,3,2),
                ray.findClosestPoint(pointList),
                "findclosestpoint doesnt work"
        );
        ray = new Ray(new Point(5,3,1),new Vector(1,2,3));
        assertNull(
                ray.findClosestPoint(new ArrayList<>()),
                "findclosestpoint doesnt work"
        );

    }
}