package geometriesTest;

import geometries.Cylinder;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {

    /**
     * Test method for {@link .geometries.Cylinder.GetNormal(.geometries.Cylinder)}.
     */
    @Test
    void testGetNormal() {
        Cylinder cylinder= new Cylinder(1,new Ray(new Point(0,0,0),new Vector(5,0,0)),1);
        //assertThrows(IllegalArgumentException.class,()->cylinder.getNormal(new Point(1,2,3)),"Tube- getNormal- wrong input is not been care right");
        // ============ first Equivalence Partitions Tests ============== on the base
        assertEquals (1,cylinder.getNormal(new Point(0,0.5,0)).length(),0.000001,"the vector is not a unit one");
        assertEquals (new Vector(-1,0,0),cylinder.getNormal(new Point(0,0.5,0)));
        // ============ second Equivalence Partitions Tests ============== on the second base
        assertEquals (1,cylinder.getNormal(new Point(1,0.5,0)).length(),0.000001,"the vector is not a unit one");
        assertEquals(new Vector(1,0,0),cylinder.getNormal(new Point(1,0.5,0)),"get normal returned wrong value");
        // ============ third Equivalence Partitions Tests ============== regular point
        assertEquals (1,cylinder.getNormal(new Point(0.5,1,0)).length(),0.000001,"the vector is not a unit one");
        assertEquals(new Vector(0,1,0),cylinder.getNormal(new Point(0.5,1,0)),"get normal returned wrong value");



        // =============== first Boundary Values Test ================== on the center
        assertEquals (1,cylinder.getNormal(new Point(0,0,0)).length(),0.000001,"the vector is not a unit one");
        assertEquals(new Vector(-1,0,0),cylinder.getNormal(new Point(0,0,0)),"get normal returned wrong value");
        // =============== second Boundary Values Test ================== on the base
        assertEquals (1,cylinder.getNormal(new Point(1,0,0)).length(),0.000001,"the vector is not a unit one");
        assertEquals(new Vector(1,0,0),cylinder.getNormal(new Point(1,0,0)),"get normal returned wrong value");



    }
}