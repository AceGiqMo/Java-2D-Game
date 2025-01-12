package gameworld.objects;

import java.io.IOException;

import java.awt.geom.Point2D;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import gameworld.components_behaviour.Breakable;
import gameworld.components_behaviour.Rotatable;

import main.Config;
import main.GamePanel;

import mathtools.SolidArea;
import mathtools.ImageRotator;

public class LandStone extends SolidGameObject implements Breakable, Rotatable {

    private GamePanel gp;

    private BufferedImage stoneSprite;
    private BufferedImage stoneSpriteRotated;

    private BufferedImage currentSprite;

    private double angle;
    private double rotationAngle;

    public LandStone(GamePanel gp, double mapX, double mapY, double camX, double camY) {
        this.gp = gp;

        this.mapX = mapX;
        this.mapY = mapY;

        this.screenX = mapX - camX;
        this.screenY = camY - mapY;

        setDefaultValues();
        setSprites();
        setState(ObjectState.BASE);
        setBreakable(true);
    }

    /**
     * This methods sets a solid area for a land stone if necessary
     */
    public void setSolidArea() {
        Point2D vertex1 = new Point2D.Double(screenX + Config.LANDSTONE_VERTEX_1_X,
                                             screenY + Config.LANDSTONE_VERTEX_1_Y);
        Point2D vertex2 = new Point2D.Double(screenX + Config.LANDSTONE_VERTEX_2_X,
                                             screenY + Config.LANDSTONE_VERTEX_2_Y);
        Point2D vertex3 = new Point2D.Double(screenX + Config.LANDSTONE_VERTEX_3_X,
                                             screenY + Config.LANDSTONE_VERTEX_3_Y);
        Point2D vertex4 = new Point2D.Double(screenX + Config.LANDSTONE_VERTEX_4_X,
                                             screenY + Config.LANDSTONE_VERTEX_4_Y);

        Point2D[] vertices = {vertex1, vertex2, vertex3, vertex4};

        this.solidArea = new SolidArea(vertices);

    }

    /**
     * This method sets default values for a land stone
     */
    protected void setDefaultValues() {
        solidArea = null;

        angle         = 0;
        rotationAngle = 0;
    }

    /**
     * This method sets sprites for a land stone
     */
    protected void setSprites() {
        try {
            stoneSprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(
                                  "res/sprites/objects/rock.png"));

            stoneSpriteRotated = stoneSprite;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final void rotate(Point2D axis) {
        angle += rotationAngle;

        ImageRotator spriteToRotate = new ImageRotator(stoneSprite);

        spriteToRotate.rotate(-angle);
        stoneSpriteRotated = spriteToRotate.getImage();

        solidArea.rotate(-angle, axis);
    }

    public final void update() {
        if (solidArea != null && rotationAngle != 0) {

            rotate(new Point2D.Double(screenX + Config.LANDSTONE_SPRITE_WIDTH / 2,
                                      screenY + Config.LANDSTONE_SPRITE_HEIGHT / 2));

        }

        currentSprite = stoneSpriteRotated;
    }

    public final void draw(Graphics2D g2) {
        g2.setColor(Color.getHSBColor(Config.LS_SA_HUE, Config.LS_SA_SAT, Config.LS_SA_VAL));

        g2.drawImage(currentSprite,
                     (int) screenX - Config.LANDSTONE_SPRITE_WIDTH / 2,
                     (int) screenY - Config.LANDSTONE_SPRITE_HEIGHT / 2,
                     Config.LANDSTONE_SPRITE_WIDTH,
                     Config.LANDSTONE_SPRITE_HEIGHT,
                     null
        );

        solidArea.draw(g2);
    }

    @Override
    public final String toString() {
        return "LandStone{mapX = " + mapX + ", mapY = " + mapY
             + ", screenX = " + screenX + ", screenY = " + screenY + "}";
    }

}
