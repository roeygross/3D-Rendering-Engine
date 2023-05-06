package primitivesTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import primitives.Vector;


import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;



class VectorTest {
    double ACCURACY  = 0.00001;
    /**
     * Test method for {@link .primitives.Vector.Add(.primitives.Vector)}.
     */
    @Test
    void testAdd() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        // ============ Equivalence Partitions Tests ==============
        assertDoesNotThrow(() ->v1.add(v2));
        assertEquals(new Vector(-1, -2, -3),v1.add(v2),"ERROR: Point - Point does not work correctly");

        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class,()->v1.add(new Vector(-1,-2,-3)));// ] zero vector ] need to be care
    }

    /**
     * Test method for {@link .primitives.Vector.Scale(.primitives.Vector)}.
     */
    @Test
    void testScale() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector (2,1,0);
        assertDoesNotThrow(() ->v1.scale(2));
        assertEquals(
                new Vector(4,2,0),
                v1.scale(2),
                "ERROR-scale"
        );

        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class,()->v1.scale(0));// zero vector  need to be care

    }

    /**
     * Test method for {@link .primitives.Vector.CrossProduct(.primitives.Vector)}.
     */
    @Test
    void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);
        // ============ Equivalence Partitions Tests ==============
        assertDoesNotThrow(() ->v1.crossProduct(v3));
        Vector vr = v1.crossProduct(v3);
        assertEquals(v1.length() * v3.length(),vr.length(), ACCURACY  ,"ERROR: crossProduct() wrong result length");//Obtuse angle equivalence partition
        assertTrue(isZero(vr.dotProduct(v1)) || !isZero(vr.dotProduct(v3)),"ERROR: crossProduct() result is not orthogonal to its operands");
        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class,()-> v1.crossProduct(v2));// zero vector  need to be care
    }

    /**
     * Test method for {@link .primitives.Vector.DotProduct(.primitives.Vector)}.
     */
    @Test
    void testDotProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);
        // ============ Equivalence Partitions Tests ==============
        assertDoesNotThrow(() ->v1.dotProduct(v2));
        assertEquals(-28,v1.dotProduct(v2), ACCURACY ,"ERROR: dotProduct() wrong value");
        // =============== Boundary Values Tests ==================
        assertDoesNotThrow(() ->v1.dotProduct(v3));
        assertEquals(0,v1.dotProduct(v3),ACCURACY,"ERROR: dotProduct() for orthogonal vectors is not zero");



    }

    /**
     * Test method for {@link .primitives.Vector.LengthSquared(.primitives.Vector)}.
     */
    @Test
    void testLengthSquared() {

        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1, 2, 3);
        assertDoesNotThrow(() ->v1.lengthSquared());
        assertEquals(14,v1.lengthSquared(),ACCURACY,"ERROR: lengthSquared() wrong value");


    }

    /**
     * Test method for {@link .primitives.Vector.Length(.primitives.Vector)}.
     */
    @Test
    void testLength() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 =new Vector(0, 3, 4);
        assertDoesNotThrow(() ->v1.length());
        assertEquals(5,v1.length(),ACCURACY,"ERROR: length() wrong value");


    }

    /**
     * Test method for {@link .primitives.Vector.Normalize(.primitives.Vector)}.
     */
    @Test
    void testNormalize() {
        // ============ Equivalence Partitions Tests ==============
        Vector v = new Vector(1, 2, 3);
        assertDoesNotThrow(() -> v.normalize());
        Vector u = v.normalize();

        assertTrue(isZero(u.length()-1),"ERROR: the normalized vector is not a unit vector"  );
        assertThrows(IllegalArgumentException.class,()->v.crossProduct(u));
        assertTrue(v.dotProduct(u) > 0,"ERROR: the normalized vector is opposite to the original one");


    }

    /**
     * Test method for {@link .primitives.Vector.SpinX(.primitives.Vector)}.
     */

    @Test
    void testSpinX() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(
                new Vector(0.5773502691896258,0.788675134594813,0.21132486540518725),
                new Vector(1,1,1).normalize().spinX(30)
        );
        // =============== Boundary Values Tests ==================

    }

    /**
     * Test method for {@link .primitives.Vector.SpinY(.primitives.Vector)}.
     */
    @Test
    void testSpinY() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(
                new Vector(0.788675134594813,0.5773502691896258,0.21132486540518725),
                new Vector(1,1,1).normalize().spinY(30)
        );

        // =============== Boundary Values Tests ==================

    }

    /**
     * Test method for {@link .primitives.Vector.SpinZ(.primitives.Vector)}.
     */
    @Test
    void testSpinZ() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(
                new Vector(0.788675134594813,0.21132486540518725,0.5773502691896258),
                new Vector(1,1,1).normalize().spinZ(30)
        );


        // =============== Boundary Values Tests ==================

    }


    /**
     * Test method for {@link .primitives.Vector.Multiplication(.primitives.Vector)}.
     */

}