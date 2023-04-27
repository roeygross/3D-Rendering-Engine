package CameraTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.*;
import renderer.Camera;

/**
 * Testing Camera Class
 *
 * @author Dan
 *
 */
class CameraTest {
	static final Point ZERO_POINT = new Point(0, 0, 0);

	/**
	 * Test method for
	 * {@link Camera#constructRay(int, int, int, int)}.
	 */
	@Test
	void testConstructRay() {
		Camera camera = new Camera(ZERO_POINT, new Vector(0, 0, -1), new Vector(0, -1, 0)).setVPDistance(10);
		String badRay = "Bad ray";

		// ============ Equivalence Partitions Tests ==============
		// EP01: 4X4 Inside (1,1)
		assertEquals(new Ray(ZERO_POINT, new Vector(1, -1, -10)),
				camera.setVPSize(8, 8).constructRay(4, 4, 1, 1)
				, badRay
		);


		// =============== Boundary Values Tests ==================
		// BV01: 3X3 Center (1,1)
		assertEquals(new Ray(ZERO_POINT, new Vector(0, 0, -10)),
				camera.setVPSize(6, 6).constructRay(3, 3, 1, 1), badRay);

		// BV02: 3X3 Center of Upper Side (0,1)
		assertEquals(new Ray(ZERO_POINT, new Vector(0, -2, -10)),
				camera.setVPSize(6, 6).constructRay(3, 3, 1, 0), badRay);

		// BV03: 3X3 Center of Left Side (1,0)
		assertEquals(new Ray(ZERO_POINT, new Vector(2, 0, -10)),
				camera.setVPSize(6, 6).constructRay(3, 3, 0, 1), badRay);

		// BV04: 3X3 Corner (0,0)
		assertEquals(new Ray(ZERO_POINT, new Vector(2, -2, -10)),
				camera.setVPSize(6, 6).constructRay(3, 3, 0, 0), badRay);

		// BV05: 4X4 Corner (0,0)
		assertEquals(new Ray(ZERO_POINT, new Vector(3, -3, -10)),
				camera.setVPSize(8, 8).constructRay(4, 4, 0, 0), badRay);

		// BV06: 4X4 Side (0,1)
		assertEquals(new Ray(ZERO_POINT, new Vector(1, -3, -10)),
				camera.setVPSize(8, 8).constructRay(4, 4, 1, 0), badRay);

}

	/**
	 * test for spinning the camera right and left
	 */
	@Test
	void spinRight() {
		Camera camera = new Camera(new Point(2,0,0),new Vector(-1,0,0),new Vector(0,0,1));
		camera.setVPDistance(2);
		camera.spinRight(40.452475134789275);
		assertTrue(
				camera.getVto().dotProduct(camera.getVup())==0,
				"spinRight camera vectors are wrong"
		);
		assertTrue(
				camera.getVto().dotProduct(camera.getVright())==0,
				"spinRight camera vectors are wrong"
		);
		assertEquals(
				new Point(-1.297634189138421, 1.521888797244751, 0),
				camera.getPlace(),
				"spinRight camera location is wrong"
		);
		assertEquals(
				new Vector(1.297634189138421, -1.521888797244751, 0).normalize(),
				camera.getVto(),
				"spinRight vto is wrong"
		);

	}

	/**
	 * Test method for {@link .${CLASS_NAME}.Name(.${CLASS_NAME})}.
	 */
	@Test
	void spinUp() {
		Camera camera = new Camera(new Point(2,0,0),new Vector(0,-1,0),new Vector(0,0,1));
		camera.setVPDistance(2);
		camera.spintUp(30.794791187843394);
		assertEquals(
				new Point(1.718012887340227, 0, 1.023929547836616),
				camera.getPlace(),
				"spinRight camera location is wrong"
		);
		assertEquals(
				new Vector(-1.718012887340227, 0, -1.023929547836616).normalize(),
				camera.getVto(),
				"spinRight vto is wrong"
		);
		assertTrue(
				camera.getVto().dotProduct(camera.getVup())==0,
				"spinRight camera vectors are wrong"
		);
		assertTrue(
				camera.getVto().dotProduct(camera.getVright())==0,
				"spinRight camera vectors are wrong"
		);

	}
}
