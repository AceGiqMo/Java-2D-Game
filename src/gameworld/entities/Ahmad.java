package gameworld.entities;

import java.io.IOException;

import java.awt.geom.Point2D;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.BasicStroke;
import java.awt.Color;

import javax.imageio.ImageIO;

import main.Main;
import main.Config;
import main.GamePanel;
import main.MouseHandler;

import mathtools.SolidArea;
import mathtools.LineTransformer;
import mathtools.ImageRotator;

import gameworld.Rotatable;

public class Ahmad extends Entity implements Rotatable {

    private GamePanel gp;
    private MouseHandler mouseH;

    private SolidArea solidArea;

    private LineTransformer dirLine;
    private Point2D         dirPoint;

    private BufferedImage[] flySprites;
    private BufferedImage[] standSprites;

    private BufferedImage[] flySpritesRotated;
    private BufferedImage[] standSpritesRotated;

    private BufferedImage currentSprite;

    private int flySpritesIndex;     // Points at the current sprite of fly animation
    private int standSpritesIndex;   // Points at the current sprite of stand animation

    private int spriteCounter;       // Frame counter for sprite change

    private AhmadState state;        // Fly or stand

    public Ahmad(GamePanel gp, MouseHandler mouseH) {
        this.gp = gp;
        this.mouseH = mouseH;

        this.dirLine  = new LineTransformer(this.gp);
        this.dirPoint = new Point2D.Double(0.0, 0.0);

        flySprites = new BufferedImage[Config.AHMAD_FLY_SPRITES];
        standSprites = new BufferedImage[Config.AHMAD_STAND_SPRITES];

        flySpritesRotated = new BufferedImage[Config.AHMAD_FLY_SPRITES];
        standSpritesRotated = new BufferedImage[Config.AHMAD_STAND_SPRITES];

        setDefaultValues();
        setSolidArea();
        setSprites();

    }

    public final void setDefaultValues() {
        mapX = Config.AHMAD_INITIAL_MAP_X;
        mapY = Config.AHMAD_INITIAL_MAP_Y;

        screenX = Config.SCREEN_WIDTH / 2 - Config.AHMAD_FLY_SPRITE_WIDTH / 2;
        screenY = Config.SCREEN_HEIGHT / 2 - Config.AHMAD_FLY_SPRITE_HEIGHT / 2;

        speed     = Config.AHMAD_FLY_SPEED;
        state     = AhmadState.FLY;
        angle     = 0;

        flySpritesIndex   = 0;
        standSpritesIndex = 0;

        spriteCounter = 0;

        Point2D.Double mousePos = mouseH.getPos();
        Point2D.Double ahmadPos = new Point2D.Double(screenX, screenY);

        dirLine.rebuilt(mousePos, ahmadPos);

        dirPoint = dirLine.getPointOutside();
    }

    public final void setSolidArea() {
        Point2D vertex1 = new Point2D.Double(Config.SCREEN_WIDTH / 2 + Config.AHMAD_VERTEX_1_X,
                                             Config.SCREEN_HEIGHT / 2 + Config.AHMAD_VERTEX_1_Y);
        Point2D vertex2 = new Point2D.Double(Config.SCREEN_WIDTH / 2 + Config.AHMAD_VERTEX_2_X,
                                             Config.SCREEN_HEIGHT / 2 + Config.AHMAD_VERTEX_2_Y);
        Point2D vertex3 = new Point2D.Double(Config.SCREEN_WIDTH / 2 + Config.AHMAD_VERTEX_3_X,
                                             Config.SCREEN_HEIGHT / 2 + Config.AHMAD_VERTEX_3_Y);
        Point2D vertex4 = new Point2D.Double(Config.SCREEN_WIDTH / 2 + Config.AHMAD_VERTEX_4_X,
                                             Config.SCREEN_HEIGHT / 2 + Config.AHMAD_VERTEX_4_Y);

        Point2D[] vertices = {vertex1, vertex2, vertex3, vertex4};

        solidArea = new SolidArea(vertices);

    }

    public final void setSprites() {
        try {
            /*
             Our animation is looped, if we have unique list of sprites, e.g. [img<1>, img<2>, ..., img<n>],
             then our animation list should be [img<1>, img<2>, ..., img<n>, img<n - 1>, ..., img<2>] to be looped
             */

            /* Fly sprites */
            for (int i = 0; i <= Config.AHMAD_FLY_SPRITES / 2; i++) {
                flySprites[i] = ImageIO.read(getClass().getClassLoader().getResourceAsStream(
                                             "res/sprites/ahmad/ahmad_fly" + (i + 1) + ".png"));

                flySpritesRotated[i] = flySprites[i];
            }

            for (int i = Config.AHMAD_FLY_SPRITES / 2; i >= 1; i--) {
                flySprites[Config.AHMAD_FLY_SPRITES - i]        = flySprites[i];
                flySpritesRotated[Config.AHMAD_FLY_SPRITES - i] = flySpritesRotated[i];
            }

            /* Stand sprites */

            for (int i = 0; i <= Config.AHMAD_STAND_SPRITES / 2; i++) {
                standSprites[i] = ImageIO.read(getClass().getClassLoader().getResourceAsStream(
                                               "res/sprites/ahmad/ahmad_stand" + (i + 1) + ".png"));

                standSpritesRotated[i] = standSprites[i];
            }

            for (int i = Config.AHMAD_STAND_SPRITES / 2; i >= 1; i--) {
                standSprites[Config.AHMAD_STAND_SPRITES - i]        = standSprites[i];
                standSpritesRotated[Config.AHMAD_STAND_SPRITES - i] = standSpritesRotated[i];
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final void rotate() {
        /* We apply rotation to every sprite and edit `flySpritesRotated` array */
        for (int i = 0; i < Config.AHMAD_FLY_SPRITES; i++) {
            ImageRotator spriteToRotate = new ImageRotator(flySprites[i]);

            /* The argument is negative, because of specifics of the interface's coordinate system */
            spriteToRotate.rotate(-angle);
            flySpritesRotated[i] = spriteToRotate.getImage();
        }

        /* We rotate the vertices of base position */
        solidArea.rotate(-angle, new Point2D.Double(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT / 2));
    }

    public final void update() {
        spriteCounter++;

        if (spriteCounter > Config.SPRITE_CHANGE_INTERVAL) {
            flySpritesIndex   = ((flySpritesIndex + 1) % Config.AHMAD_FLY_SPRITES);
            standSpritesIndex = ((standSpritesIndex + 1) % Config.AHMAD_STAND_SPRITES);
            spriteCounter = 0;
        }

        if (state.getState().equals(AhmadState.FLY.toString())) {
            updateFly();
        } else if (state.getState().equals(AhmadState.STAND.toString())) {
            updateStand();
        }

    }

    /**
     * This method updates all data related to Ahmad in flight state
     */
    public void updateFly() {
        if (mouseH.isMoved()) {
            /*
             * We get positions regarding the full screen and then transform them into positions
             * regarding the temporary screen
             */
            Point2D.Double mousePos = mouseH.getPos();
            Point2D.Double ahmadPos = new Point2D.Double(Main.getWindow().getWidth() / 2,
                                                         Main.getWindow().getHeight() / 2);

            dirLine.rebuilt(mousePos, ahmadPos);

            dirPoint = dirLine.getPointOutside();

            /* Since basically Ahmad's sprite's direction angle is 90 degrees, its relative angle is PI / 2 - angle */
            angle    = dirLine.getAngle() - Config.PI / 2;
        }

        if (mouseH.isPressed()) {
            rotate();
        }

        currentSprite = flySpritesRotated[flySpritesIndex];

    }

    /**
     * This method updates all data related to Ahmad in stand state
     */
    public void updateStand() {
        currentSprite = standSprites[standSpritesIndex];
    }

    public final void draw(Graphics2D g2) {
        /*  Direction line drawing */
        g2.setStroke(new BasicStroke(Config.LINE_THICKNESS));
        g2.setColor(Color.getHSBColor(Config.LINE_HUE, Config.LINE_SAT, Config.LINE_VAL));
        g2.drawLine((int) dirPoint.getX(), (int) dirPoint.getY(), Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT / 2);

        /* Ahmad sprites drawing */
        g2.drawImage(
                    currentSprite,
                    screenX,
                    screenY,
                    Config.AHMAD_FLY_SPRITE_WIDTH,
                    Config.AHMAD_FLY_SPRITE_HEIGHT,
                    null
        );

        solidArea.draw(g2);
    }

    public final SolidArea getSolidArea() {
        return solidArea;
    }
}


enum AhmadState {
    FLY("FLY"),
    STAND("STAND");

    private String state;

    private AhmadState(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

}
