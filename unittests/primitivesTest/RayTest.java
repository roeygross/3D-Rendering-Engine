package primitivesTest;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.isZero;
import static org.junit.jupiter.api.Assertions.*;

class RayTest {
    @Test
    public void testConstructor()
    {
        Ray ray = new Ray(new Point(0,0,0),new Vector(1,1,1));
        System.out.print(ray.getDir().toString());
        assertTrue(isZero(ray.getDir().length()-1),"the vector is not a unit one");
    }

}