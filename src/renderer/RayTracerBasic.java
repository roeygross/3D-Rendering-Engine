package renderer;


import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;



public class RayTracerBasic extends RayTracerBase {

    /**
     * stop condition for recursive tracing
     */
    private static final int MAX_CALC_COLOR_LEVEL = 20;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final Double3 INITIAL_K = Double3.ONE;

    /**
     *
     * @param gp the point of which we want to calculate the shadiness - geoPoint type
     * @param l the direction of the light source - vector type
     * @param normal the normal of the point - vector type
     * @param lightSource the light source of which the shadiness will be checked - lightSource type
     * @return shadiness of a point base on the transparency of objects between this object and the light
     */
    private Double3 transparency(GeoPoint gp, Vector l, Vector normal, LightSource lightSource)//checks if the point should be shaded
    {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray shadowRay = new Ray(gp.point,lightDirection, normal);
        List<GeoPoint> intersections = scene.findGeoIntersections(shadowRay,lightSource.getDistance(gp.point));
        Double3 ktr=Double3.ONE;
        for (GeoPoint intersection:
                intersections) {
            ktr = ktr.product(intersection.geometry.getKT());
            if (ktr.equals(Double3.ZERO)) return Double3.ZERO;

        }
        return ktr;
    }

    /**
     * find the closest intersection of the ray to geometries in the scene
     * @param ray
     * @return point of interrsection
     */
    private GeoPoint findClosestIntersection(Ray ray)
    {
        return ray.findClosestGeoPoint(scene.findGeoIntersections(ray));

    }
    /*constructor*/

    /**
     * Constructor raytracerbasic
     * @param scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * alculate the local effect by the formula of phong 𝑰𝑷 = 𝒌𝑨 ∙ 𝑰𝑨 + 𝑰𝑬 + 𝒌𝑫 ∙ 𝒍 ∙ 𝒏 + 𝒌𝑺 ∙ −𝒗 ∙ 𝒓𝒏𝒔𝒉 ∙ �
     * @param intersectionPoint - intersection point
     * @param rayFromViewPoint - ray from our view point
     * @return
     */
    private Color calcLocalEffects (GeoPoint intersectionPoint,Ray rayFromViewPoint)
    {
        if (intersectionPoint==null) return scene.background;
        Vector vto= rayFromViewPoint.getDir();
        Color iA = scene.ambientLight.getIntensity();//ambient light
        Color iE = intersectionPoint.getIE();//emission light
        Color iL = Color.BLACK;//light from light sources in the scene
        double kDtmp =0;
        double kStmp =0;
        Double3 kDS = Double3.ZERO ;
        Vector l;
        Vector normal;
        Vector r;
        double narrowBeam;
        for (LightSource lightSource: scene.lights)//for all light source gather the |l*n|
        {
            l = lightSource.getL(intersectionPoint.point);
            normal = intersectionPoint.getNormal();
            double ln = l.dotProduct(normal);
            if (Double3.ZERO.lowerThan(ln *normal.dotProduct(vto)) ) //chek if the camera and the light source are on the same side of the surface
            {
                narrowBeam = lightSource.getNarrowBeam();
                iL = iL.add(lightSource.getIntensity(intersectionPoint.point));
                kDtmp += Math.pow(Math.abs(ln),narrowBeam);
                if (Util.isZero(ln)) r=normal.scale(-1);
                else r = l.subtract(normal.scale(2 * ln)).normalize();//reflecting vector from the surface
                kStmp += Math.pow((Math.max(0, vto.scale(-1).dotProduct(r))), intersectionPoint.getNShininess());
                kDS = kDS.add(intersectionPoint.getKD().scale(kDtmp).add(intersectionPoint.getKS().scale(kStmp))).product(transparency(intersectionPoint,l,normal,lightSource));
            }
        }

        iL = iL.scale(kDS);
        return iA.add(iE).add(iL);//𝑰𝑷 = 𝒌𝑨 ∙ 𝑰𝑨 + 𝑰𝑬 + 𝒌𝑫 ∙ 𝒍 ∙ 𝒏 + 𝒌𝑺 ∙ −𝒗 ∙ 𝒓𝒏𝒔𝒉 ∙ �
    }

    /**
     * calculates the color of point with all the effects
     * @param intersection
     * @param ray
     * @return color of point
     */
    private Color calcColor(GeoPoint intersection, Ray ray)
    {

        return calcColor(intersection,ray,MAX_CALC_COLOR_LEVEL, INITIAL_K);
    }


    /**
     *  recursive method for calculating the color
     * @param intersection point
     * @param inRay ray
     * @param level of the recursive
     * @param k the factor of the calculation of the color
     * @return
     */
    private Color calcColor(GeoPoint intersection, Ray inRay,int level, Double3 k)
    {
        if (intersection==null) return scene.background;
        Color color = calcLocalEffects(intersection, inRay);
        return 1 == level ? color
                : color.add(calcGlobalEffects(intersection, inRay, level, k));
    }

    /**
     * constructing reflected ray
     * @param geoPoint point
     * @param v vector
     * @param normal normal
     * @return new ray
     */
    private Ray constructReflectedRay(GeoPoint geoPoint, Vector v, Vector normal)
    {
        double vn = v.dotProduct(normal);
        if (Util.isZero(vn)) return new Ray(geoPoint.point,normal,normal);
        return new Ray(geoPoint.point,v.subtract(normal.scale(2 * vn)).normalize(),normal);
    }

    /**
     * consructing refracted ray
     * @param geoPoint point
     * @param v vector
     * @param normal normal
     * @return new ray
     */
    private Ray constructRefractedRay (GeoPoint geoPoint, Vector v, Vector normal)
    {
        return new Ray(geoPoint.point,v,normal);
    }
    /*calc the transperncy and reflaction with recursive calls*/
    private Color calcGlobalEffects(GeoPoint gp, Ray inRay,int level, Double3 k)
    {
        //if (gp==null) return scene.background;
        Vector rayDirection=inRay.getDir();
        Color color = Color.BLACK;
        Vector n = gp.getNormal();
        Material material = gp.getMaterial();
        Double3 kr = material.kR;
        Double3 kkr = kr.product(k);
        if (!kkr.lowerThan(MIN_CALC_COLOR_K))
        {
            Ray reflectedRay = constructReflectedRay(gp,rayDirection,n);
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            if (reflectedPoint!=null)color = color.add(calcColor(reflectedPoint, reflectedRay, level-1, kkr).scale(kr));
        }
        Double3 kt = material.kT;
        Double3 kkt = k.product(kt);
        if (!kkt.lowerThan(MIN_CALC_COLOR_K) )
        {
            Ray refractedRay = constructRefractedRay(gp, rayDirection, n);
            GeoPoint refractedPoint = findClosestIntersection(refractedRay);
            if (refractedPoint!=null)color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
        }
        return color;

    }


    /**
     * finds the closest intersection with the ray and calaulates the color of it
     * @param ray
     * @return color of the closest intersection point
     */
    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        if (closestPoint ==null ) return scene.background;
        return calcColor(closestPoint,ray);
    }

    @Override
    public Color traceBeamRay(List<Ray> beam) {
        Color tmp = Color.BLACK;
        for (Ray ray:
                beam) {
            Color color = traceRay(ray);
            tmp = tmp.add(color);
        }
        return tmp.reduce(beam.size());
    }
    @Override
    public Color traceDOF(Ray mainRay,List<Ray> secondaryRays, Point focusTarget,double depth,double blurIntensity ) {
        Color color = traceRay(mainRay);
        for (Ray secondaryRay :
             secondaryRays) {
            GeoPoint closestPoint = findClosestIntersection(secondaryRay);
            if (closestPoint!=null)color = color.add(traceRay(secondaryRay).scale(Math.abs(1-Math.abs(focusTarget.distance(closestPoint.point)/(depth*blurIntensity)))));
        }
        color = color.reduce(secondaryRays.size()+1);
        return color;
    }

}


