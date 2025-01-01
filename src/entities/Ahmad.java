package entities;

import java.io.IOException;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.Config;
import main.GamePanel;
import main.MouseHandler;

public class Ahmad extends Entity {

    private GamePanel gp;
    private MouseHandler mouseH;

    private BufferedImage[] flySprites;
    private BufferedImage[] standSprites;

    private int flySpritesIndex;     // Points at the current sprite of fly animation
    private int standSpritesIndex;   // Points at the current sprite of stand animation

    private int spriteCounter;       // Frame counter for sprite change

    private AhmadState state;        // Fly or stand

    public Ahmad(GamePanel gp, MouseHandler mouseH) {
        this.gp = gp;
        this.mouseH = mouseH;

        flySprites = new BufferedImage[Config.AHMAD_FLY_SPRITES];
        standSprites = new BufferedImage[Config.AHMAD_STAND_SPRITES];

        setDefaultValues();
        setSprites();

    }

    public final void setDefaultValues() {
        mapX = Config.AHMAD_INITIAL_MAP_X;
        mapY = Config.AHMAD_INITIAL_MAP_Y;

        screenX = Config.SCREEN_WIDTH / 2 - Config.AHMAD_FLY_SPRITE_WIDTH / 2;
        screenY = Config.SCREEN_HEIGHT / 2 - Config.AHMAD_FLY_SPRITE_HEIGHT / 2;

        speed     = Config.AHMAD_FLY_SPEED;
        angleCoef = Double.POSITIVE_INFINITY;      // tan(pi - 0) = tan(pi) = infinity
        state     = AhmadState.FLY;
        angle     = 0;

        flySpritesIndex   = 0;
        standSpritesIndex = 0;

        spriteCounter = 0;
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
            }

            for (int i = Config.AHMAD_FLY_SPRITES / 2; i >= 1; i--) {
                flySprites[Config.AHMAD_FLY_SPRITES - i] = flySprites[i];
            }

            /* Stand sprites */

            for (int i = 0; i <= Config.AHMAD_STAND_SPRITES / 2; i++) {
                standSprites[i] = ImageIO.read(getClass().getClassLoader().getResourceAsStream(
                                               "res/sprites/ahmad/ahmad_stand" + (i + 1) + ".png"));
            }

            for (int i = Config.AHMAD_STAND_SPRITES / 2; i >= 1; i--) {
                standSprites[Config.AHMAD_STAND_SPRITES - i] = standSprites[i];
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final void update() {
        spriteCounter++;

        if (spriteCounter > Config.SPRITE_CHANGE_INTERVAL) {
            flySpritesIndex   = ((flySpritesIndex + 1) % Config.AHMAD_FLY_SPRITES);
            standSpritesIndex = ((standSpritesIndex + 1) % Config.AHMAD_STAND_SPRITES);
            spriteCounter = 0;
        }

    }

    public final void draw(Graphics2D g2) {
        if (state.getState().equals(AhmadState.FLY.toString())) {
            g2.drawImage(
                            flySprites[flySpritesIndex],
                            screenX,
                            screenY,
                            Config.AHMAD_FLY_SPRITE_WIDTH,
                            Config.AHMAD_FLY_SPRITE_HEIGHT,
                            null
                        );
        } else if (state.getState().equals(AhmadState.STAND.toString())) {
            g2.drawImage(
                            standSprites[standSpritesIndex],
                            screenX,
                            screenY,
                            Config.AHMAD_FLY_SPRITE_WIDTH,
                            Config.AHMAD_FLY_SPRITE_HEIGHT,
                            null
                        );
        }
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
