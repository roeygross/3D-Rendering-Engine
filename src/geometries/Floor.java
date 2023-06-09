package geometries;

import geometries.Geometries;
import geometries.Polygon;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

import static primitives.Util.alignZero;

/**
 * class represents an alternating multicolor floor in a 3D space
 */
public class Floor {

    Geometries elements;

    /**
     * constructor
     * @param start starting point of floor
     * @param len vector in forward direction of floor
     * @param wid vector in right direction of floor
     * @param color1 first color of tile
     * @param color2 second color of tile
     * @param length length of floor
     * @param width width of floor
     * @param sumLength number of tiles per column
     * @param sumWidth number of tiles per row
     */
    public Floor(Point start, Vector len, Vector wid, Color color1, Color color2, double length, double width, int sumLength, int sumWidth, Material mt) {

        elements = new Geometries();

        //length of a tile
        double squareLength = alignZero(length / sumLength);
        //width of a tile
        double squareWidth = alignZero(width / sumWidth);

        //four point to create a tile
        Point p1 =start;
        Point p2 =null;
        Point p3 =null;
        Point p4 =null;

        //create alternated color tiles across floor
        for (int i = 0; i < sumLength; i++) {
            for (int j = 0; j < sumLength; j++) {

                //if row is not the first advance starting point to correct row
                if(i!=0)
                    p1 = start.add(len.scale(alignZero(squareLength*i)));

                //if column is first, set all four points
                if (j == 0 ) {
                    p2 = p1.add(len.scale(squareLength));
                    p3 = p2.add(wid.scale(squareWidth));
                    p4 = p1.add(wid.scale(squareWidth));
                }
                // from second column and on, set first two points to previous tile third and fourth point
                // then set new third and fourth point
                else {
                    p1 = p4;
                    p2 = p3;
                    p3 = p2.add(wid.scale(squareWidth));
                    p4 = p1.add(wid.scale(squareWidth));
                }

                //alternate color of tiles
                if ((i+j)%2==0){
                    elements.add(new Polygon(p1, p2, p3,p4).setEmission(color1).setMaterial(mt));
                }
                else{
                    elements.add(new Polygon(p1, p2, p3,p4).setEmission(color2).setMaterial(mt));
                }
            }
        }
    }

    public Geometries getElements() {
        return elements;
    }
}