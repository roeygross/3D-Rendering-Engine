package rendererTests;

import org.junit.jupiter.api.Test;
import primitives.Color;
import renderer.ImageWriter;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {
    /**
     * Test method for {@link .${CLASS_NAME}.Name(.${CLASS_NAME})}.
     */
    @Test
    void letsStudyTest() {
        ImageWriter imageWriter = new ImageWriter("letsStudy",800,500);
        Color yellow = new Color(255,255,0);
        Color red = new Color(255,0,0);
        for (int i=0;i<800;i+=50)
        {

            for (int q=0;q<500;q+=50)
            {
                imageWriter.writePixel(i,q,red);
            }

        }
        imageWriter.writeToImage();
    }
}