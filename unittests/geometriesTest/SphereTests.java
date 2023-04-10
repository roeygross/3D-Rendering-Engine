package geometriesTest;

import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
class SphereTests {

    /**
     * Test method for {@link .geometries.Sphere.GetNormal(.geometries.Sphere)}.
     */
    @Test
    void testGetNormal() {
        Sphere s = new Sphere(1, new Point(0,0,0));
        //assertThrows(IllegalArgumentException.class,()->s.getNormal(new Point(3,3,3)),"Sphere - getNormal- wrong input is not been care right");//"ERROR-Sphare- GetNormal- the point is not in the sphare")
        // ============ 1 Equivalence Partition ==============
        assertDoesNotThrow(() -> s.getNormal(new Point(1,0,0)));
        Vector normal = s.getNormal(new Point(1,0,0));
        assertEquals(new Vector(1,0,0),normal,"Sphere- getNormal- equivalence partition has been failed ");
    }
}