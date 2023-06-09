package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

public class Diamond extends Geometry{
    double width;
    Point center;
    Point spitz;
    Vector dir = new Vector(0,0,1);
    Geometries diamondGeometries;
    double tableSize;
    double depth;
    double upeerPart ;
    double downPart ;

    public Diamond(Point center,double width)
    {
        this.diamondGeometries = new Geometries();
        this.width=width;
        this.center=center;
         tableSize = width*0.53;
         depth = width*0.593;
         upeerPart =  width*0.162;
         downPart = 0.431*width;
        this.spitz= center.add(dir.scale(-1*downPart));
        Octagon table = new Octagon(tableSize,spitz.add(dir.scale(depth)));
        Polygon slant = new Polygon(depth,center,16);
        Polygon thirdslant = new Polygon(tableSize+depth*0.07833,center.add(dir.scale(upeerPart/3)),16);
        diamondGeometries.add(
                table,
                slant,
                thirdslant
        );
        List<Point> pointsUpperDownLimitThird =slant.vertices;
        List<Point> pointsTable =table.vertices;
        List<Point> pointsUpperDownLimit = thirdslant.vertices;
       for (int i=0;i<8;i++)//create the triangles below the surface of the diamond
        {
            diamondGeometries.add(
                    new Triangle(pointsTable.get(i),pointsTable.get((i+1)%8),pointsUpperDownLimitThird.get(i*2+1))
                    ,new Triangle(pointsTable.get(i),pointsUpperDownLimitThird.get(i*2+1),pointsUpperDownLimitThird.get((15+i*2+1)%16)),
                    new Triangle(pointsUpperDownLimitThird.get(i*2+1),pointsUpperDownLimitThird.get((15+i*2+1)%16),pointsUpperDownLimit.get(i)),
                    new Triangle(pointsUpperDownLimitThird.get(i*2+1),pointsUpperDownLimit.get(i),pointsUpperDownLimit.get(i+1))
                    ,new Triangle(pointsUpperDownLimitThird.get(i*2+1),pointsUpperDownLimit.get(i),pointsUpperDownLimit.get((15+i)%16))
            );
        }

    }




    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        return diamondGeometries.findGeoIntersectionsHelper(ray,maxDistance);
    }

    @Override
    public boolean isPointInside(Point point) {
        return diamondGeometries.isPointInside(point);
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }

}
