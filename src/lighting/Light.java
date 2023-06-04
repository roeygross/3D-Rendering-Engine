package lighting;

import primitives.Color;

/*a*/

/**
 * astract light for light source to the scene
 */
public abstract class Light {
    final private Color intensity;

    /**
     * constructor with the intensity of light
     * @param intensity
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * getter for intensity
     * @return intensity
     */
    public Color getIntensity() {
        return intensity;
    }
}
