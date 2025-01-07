package mathtools;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;


public class ImageRotator {
    private BufferedImage imageToRotate;
    private BufferedImage rotatedImage;

    private int imageSize;

    public ImageRotator(BufferedImage sprite) {
        imageSize = Math.max(sprite.getWidth(), sprite.getHeight());

        this.imageToRotate = sprite;
        this.rotatedImage = new BufferedImage(imageSize, imageSize, BufferedImage.TYPE_INT_ARGB_PRE);
    }

    /**
     * This method rotates the given sprite by the following angle (in radians)
     *
     * @param angle
     */
    public void rotate(double angle) {
        Graphics2D g2 = rotatedImage.createGraphics();

        AffineTransform transform = new AffineTransform();

        transform.translate(imageSize / 2.0, imageSize / 2.0);
        transform.rotate(angle);
        transform.translate(-imageSize / 2.0, -imageSize / 2.0);

        g2.drawImage(imageToRotate, transform, null);
    }

    /**
     * This method returns the result of sprite rotation
     *
     * @return Rotated image
     */
    public BufferedImage getImage() {
        return rotatedImage;
    }

}
