package geometriesTest;

import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;

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
    /**
     * Test method for {@link .geometries.Sphere.FindIntsersections(.geometries.Sphere)}.
     */
    @Test
    void testFindIntsersections() {
        Sphere sphere = new Sphere(2,new Point(1,1,1));

        // ============ first Equivalence Partitions Tests ============== two intersection
        assertEquals(
                new ArrayList() {{add(new Point(0, 2.414213562373095, 0));add(new Point(0.0,-0.414213562373095,0.0));}},
                sphere.findIntsersections(new Ray(new Point(0,4,0),new Vector(0,-8,0)))
                ,"sphere findIntserctions doesn't work"
        );
        // ============ second Equivalence Partitions Tests ============== po inside the sphere
        assertEquals(
                new ArrayList() {{add(new Point(0,2.414213562373095,0));}},
                sphere.findIntsersections(new Ray(new Point(0,1,0),new Vector(0,2,0)))
                ,"sphere findIntserctions doesn't work"
        );
        // ============ third Equivalence Partitions Tests ==============//no intersection
        assertEquals(
                null,
                sphere.findIntsersections(new Ray(new Point(0,4,0),new Vector(0,1,0)))
                ,"sphere findIntserctions doesn't work"
        );
        // ============ forth Equivalence Partitions Tests ==============//po in the sphere no intersections
        assertEquals(
                null,
                sphere.findIntsersections(new Ray(new Point(0,3,0),new Vector(3.620843234,1.682492256,0.000000000)))
                ,"sphere findIntserctions doesn't work"
        );
        sphere = new Sphere(1,new Point(1,1,1));
        // =============== 1: Boundary Values Tests ================== po in the sphere one intersection
        assertEquals(
                new ArrayList() {{add(new Point (1.044374244814618, 1.860083581487393, 1.508219597470376));}},
                sphere.findIntsersections(new Ray(new Point(1.029040083943156, 0.185473560362465, 1.579399130700051),new Vector (0.020489482348604,2.237611353907887,-0.095109983644785)))
                ,"sphere findIntserctions doesn't work"
        );
        // =============== 2: Boundary Values Tests ================== center is on the ray opposite direction no intersection
        assertEquals(
                null,
                sphere.findIntsersections(new Ray(new Point(1.029040083943157, 0.185473560362465, 1.579399130700052),new Vector (-0.986433917915759,-0.212905949013317,0.383316194214363)))
                ,"sphere findIntserctions doesn't work"
        );
        sphere = new Sphere(1,new Point(1,1,0));

        // =============== 3: Boundary Values Tests ================== po is the sphere center
        assertEquals(
                null,
                sphere.findIntsersections(new Ray(new Point(1.198673355556739,0.873154364897043,0.971822557181687),new Vector (0.211277157767312,-0.134892699550185,1.0334748067255)))
                ,"sphere findIntserctions doesn't work"
        );
        // =============== 4: Boundary Values Tests ==================center is on the ray opposite direction one intersection
        assertEquals(
                new ArrayList() {{add(new Point(1.198673355556739, 0.873154364897043, 0.971822557181687));}},
                sphere.findIntsersections(new Ray(new Point(1, 1, 0),new Vector  (0.198673355556739, -0.126845635102957, 0.971822557181687)))
                ,"sphere findIntserctions doesn't work"
        );
        // =============== 5: Boundary Values Tests ==================center is on the ray opposite direction two intersection
        assertEquals(
                new ArrayList() {{add(new Point(1.198673355556739, 0.873154364897043, 0.971822557181687));}},
                sphere.findIntsersections(new Ray(new Point(1, 1, 0),new Vector  (0.198673355556739, -0.126845635102957, 0.971822557181687)))
                ,"sphere findIntserctions doesn't work"
        );
        // =============== 6: Boundary Values Tests ==================center is on the ray opposite direction one intersection
        assertEquals(
                null,
                sphere.findIntsersections(new Ray(new Point(1.283854391436934,0.818769404394046,1.38849066992635),new Vector (0.126096121887117,-0.080507739047188,0.616806693980837)))
                ,"sphere findIntserctions doesn't work"
        );
        // =============== 7: Boundary Values Tests ==================
        assertEquals(
                new ArrayList() {{add(new Point(0.80132664444326, 1.126845635102955, -0.971822557181687));add(new Point (1.198673355556739, 0.873154364897043, 0.971822557181687));}},
                sphere.findIntsersections(new Ray(new Point(0.742402859098264,1.164466306248371,-1.260051764325899),new Vector(0.211277157767312,-0.134892699550185,1.0334748067255)))
                ,"sphere findIntserctions doesn't work"
        );
        sphere = new Sphere(1,new Point(1,2,-1));
        // =============== 8: Boundary Values Tests ================== tangent
        assertEquals(
                new ArrayList() {{add(new Point(0.938627352667884, 1.43982915796399, -0.173899506178506));}},
                sphere.findIntsersections(new Ray(new Point(1.588828884448867,2.437220850577874,-1.679792963084102),new Vector (-1.588828884448867,-2.437220850577874,3.679792963084102)))
                ,"sphere findIntserctions doesn't work"
        );
        // =============== 9: Boundary Values Tests ================== tangent
        assertEquals(
                new ArrayList() {{add(new Point(0.938627352667884, 1.43982915796399, -0.173899506178506));}},
                sphere.findIntsersections(new Ray(new Point (1.205250695987738, 1.84882220809685, -0.791410121793156),new Vector (-1.205250695987738,-1.84882220809685,2.791410121793156)))
                ,"sphere findIntserctions doesn't work"
        );
        sphere = new Sphere(1,new Point(1,0,1)) ;
        // =============== 10: Boundary Values Tests ================== tangent
        assertEquals(
                null,
                sphere.findIntsersections(new Ray(new Point(0,0,1),new Vector(0,0,-2)))
                ,"sphere findIntserctions doesn't work"
        );
        // =============== 11: Boundary Values Tests ================== tangent
        assertEquals(
                null,
                sphere.findIntsersections(new Ray(new Point(0,0,-1),new Vector(0,0,4)))
                ,"sphere findIntserctions doesn't work"
        );
        // =============== 12: Boundary Values Tests ================== orthogonal
        assertEquals(
                null,
                sphere.findIntsersections(new Ray(new Point(0,0,2),new Vector(0,0,1)))
                ,"sphere findIntserctions doesn't work"
        );
        sphere=new Sphere(1,new Point(1,2,1));
        // =============== 12: Boundary Values Tests ==================
        assertEquals(
                null,
                sphere.findIntsersections(new Ray(new Point(0,0,-1),new Vector(0,0,3)))
                ,"sphere findIntserctions doesn't work"
        );
        assertEquals(
                null,
                sphere.findIntsersections(new Ray(new Point(0,0,-1),new Vector(0,0,3)))
                ,"sphere findIntserctions doesn't work"
        );
    }
}