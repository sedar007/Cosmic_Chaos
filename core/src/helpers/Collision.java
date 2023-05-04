package helpers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Collision {
    public boolean checkCollision(float image1X, float image1Y, float image1Width, float image1Height, float image2X, float image2Y, float image2Width, float image2Height) {
        Rectangle image1Bounds = new Rectangle(image1X, image1Y, image1Width, image1Height);
        Rectangle image2Bounds = new Rectangle(image2X, image2Y, image2Width, image2Height);



        return image1Bounds.overlaps(image2Bounds);



        }




}
