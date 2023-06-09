package geometries;

import static primitives.Util.isZero;
import primitives.*;
import java.util.List;




/** Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 * @author Dan */
public class Polygon extends Geometry {
   /** List of polygon's vertices */
   protected final List<Point> vertices;
   /** Associated plane in which the polygon lays */
   protected final Plane       plane;
   private final int           size;



   @Override
   public List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance)
   {
      List<GeoPoint> res =plane.findGeoIntersections(ray,maxDistance);
      //First ,we check if the plane of our polygon intersects with the ray ,if there's no intersection with the
      //plane so there's no intersection with the polygon.
      if (res == null) {
         return res;
      }

      Point P0 = ray.getP0();
      Vector v = ray.getDir();

      Point P1 = vertices.get(1);
      Point P2 = vertices.get(0);

      Vector v1 = P1.subtract(P0);
      Vector v2 = P2.subtract(P0);

      double sign = Util.alignZero(v.dotProduct(v1.crossProduct(v2)));

      if (isZero(sign)) {
         return null;
      }

      boolean positive = sign > 0;

      //iterate through all vertices of the polygon
      for (int i = vertices.size() - 1; i > 0; --i) {
         v1 = v2;
         v2 = vertices.get(i).subtract(P0);

         sign = Util.alignZero(v.dotProduct(v1.crossProduct(v2)));
         if (isZero(sign)) {
            return null;
         }

         if (positive != (sign > 0)) {
            return null;
         }
      }
      return List.of(new GeoPoint(this, res.get(0).point));
}

   /** Polygon constructor based on vertices list. The list must be ordered by edge
    * path. The polygon must be convex.
    * @param  vertices                 list of vertices according to their order by
    *                                  edge path
    * @throws IllegalArgumentException in any case of illegal combination of
    *                                  vertices:
    *                                  <ul>
    *                                  <li>Less than 3 vertices</li>
    *                                  <li>Consequent vertices are in the same
    *                                  point
    *                                  <li>The vertices are not in the same
    *                                  plane</li>
    *                                  <li>The order of vertices is not according
    *                                  to edge path</li>
    *                                  <li>Three consequent vertices lay in the
    *                                  same line (180&#176; angle between two
    *                                  consequent edges)
    *                                  <li>The polygon is concave (not convex)</li>
    *                                  </ul>
    */
   public Polygon(double radius, Point center,int edges) {
      this.size = edges;
      Point[] points = new Point[edges];

      // Calculate the coordinates of the octagon vertices
      double angleIncrement = Math.toRadians(360/edges);
      double currentAngle = 0;

      for (int i = 0; i < edges; i++) {
         double x = center.getX() + radius * Math.cos(currentAngle);
         double y = center.getY() + radius * Math.sin(currentAngle);
         double z = center.getZ();
         points[i] = new Point(x, y, z);
         currentAngle += angleIncrement;
      }
      this.vertices  = List.of(points);
      this.plane = new Plane(points[0], points[1], points[2]);
   }
   public Polygon(Point... vertices) {
      if (vertices.length < 3)
         throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
      this.vertices = List.of(vertices);
      size          = vertices.length;

      // Generate the plane according to the first three vertices and associate the
      // polygon with this plane.
      // The plane holds the invariant normal (orthogonal unit) vector to the polygon
      plane         = new Plane(vertices[0], vertices[1], vertices[2]);
      if (size == 3) return; // no need for more tests for a Triangle

      Vector  n        = plane.getNormal();
      // Subtracting any subsequent points will throw an IllegalArgumentException
      // because of Zero Vector if they are in the same point
      Vector  edge1    = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
      Vector  edge2    = vertices[0].subtract(vertices[vertices.length - 1]);

      // Cross Product of any subsequent edges will throw an IllegalArgumentException
      // Cross Product of any subsequent edges will throw an IllegalArgumentException
      // because of Zero Vector if they connect three vertices that lay in the same
      // line.
      // Generate the direction of the polygon according to the angle between last and
      // first edge being less than 180 deg. It is hold by the sign of its dot product
      // with
      // the normal. If all the rest consequent edges will generate the same sign -
      // the
      // polygon is convex ("kamur" in Hebrew).
      boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
      for (var i = 1; i < vertices.length; ++i) {
         // Test that the point is in the same plane as calculated originally
         if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
            throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
         // Test the consequent edges have
         edge1 = edge2;
         edge2 = vertices[i].subtract(vertices[i - 1]);
         if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
            throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
      }
   }



   @Override
   public Vector getNormal(Point point) {

      return plane.getNormal();
   }

   @Override
   public boolean isPointInside(Point point) {
      int i, j, n = vertices.size();
      boolean isInside = false;

      for (i = 0, j = n - 1; i < n; j = i++) {
         if ((vertices.get(i).getY() > point.getY()) != (vertices.get(j).getY() > point.getY()) &&
                 (point.getX() < (vertices.get(j).getX() - vertices.get(i).getX()) * (point.getY() - vertices.get(i).getY()) / (vertices.get(j).getY() - vertices.get(i).getY()) + vertices.get(i).getX()) &&
                 (point.getZ() < (vertices.get(j).getZ() - vertices.get(i).getZ()) * (point.getY() - vertices.get(i).getY()) / (vertices.get(j).getY() - vertices.get(i).getY()) + vertices.get(i).getZ())) {
            isInside = !isInside;
         }
      }

      return isInside;
   }
}
