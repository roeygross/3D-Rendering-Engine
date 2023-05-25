package lighting;

import primitives.Color;

/*abstract light for light source to the scnene*/
public abstract class Light {
    final private Color intensity;

    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    public Color getIntensity() {
        return intensity;
    }
}
