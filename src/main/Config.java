package main;

import java.awt.desktop.ScreenSleepEvent;

public class Config {

    /* SCREEN CONFIGURATION */
    public static final int SCREEN_WIDTH  = 1280;
    public static final int SCREEN_HEIGHT = 720;

    /* CAMERA CONFIGURATION */
    public static final int CAMERA_INITIAL_X = -SCREEN_WIDTH / 2;
    public static final int CAMERA_INITIAL_Y = SCREEN_HEIGHT / 2;

    /* RENDERING SETTINGS */

    // Angle (in radians) between the `x` axis and temporary screen diagonal
    public static final double ANGLE_DIAG     = Math.atan(SCREEN_HEIGHT / SCREEN_WIDTH);
    public static final double SCREEN_OFFSET  = 10.0;
    public static final int    LINE_THICKNESS = 4;
    public static final int    TEXT_THICKNESS = 2;

    /* COLOR SETTINGS */

    // Line color
    public static final float LINE_HUE = 360.0f;
    public static final float LINE_SAT = 0.21f;
    public static final float LINE_VAL = 0.91f;

    // Land stone solid area points color
    public static final float LS_SA_HUE = 175.0f;
    public static final float LS_SA_SAT = 0.92f;
    public static final float LS_SA_VAL = 0.84f;

    /* GAMELOOP SETTINGS */
    static final int FPS      = 60;
    static final int NANOSEC  = 1000000000;
    static final int MILLISEC = 1000;

    /* SOUND SETTINGS */
    static final int AUDIO_AMOUNT = 0;
    static final int SAMPLE_RATE  = 44100;
    static final int SAMPLE_SIZE  = 16;      // In bits
    static final int CHANNELS     = 2;
    static final int FRAME_SIZE   = 4;       // In bytes (16 * 2 == 32)
    static final int FRAME_RATE   = 44100;

    /* SPRITE SETTINGS */
    public static final int AHMAD_SPRITES_SCALE     = 2;
    public static final int LANDSTONE_SPRITES_SCALE = 2;

    public static final int AHMAD_SPRITE_CHANGE_INTERVAL    = 8;
    public static final int AHMAD_SPRITE_WIDTH        = 64 * AHMAD_SPRITES_SCALE;
    public static final int AHMAD_SPRITE_HEIGHT       = 64 * AHMAD_SPRITES_SCALE;
    public static final int AHMAD_FLY_SPRITES         = 4;
    public static final int AHMAD_STAND_SPRITES       = 4;
    public static final int SUPER_AHMAD_FLY_SPRITES   = 4;

    public static final int LANDSTONE_SPRITE_CHANGE_INTERVAL = 8;
    public static final int LANDSTONE_SPRITE_WIDTH           = 64 * LANDSTONE_SPRITES_SCALE;
    public static final int LANDSTONE_SPRITE_HEIGHT          = 64 * LANDSTONE_SPRITES_SCALE;
    public static final int LANDSTONE_BREAK_SPRITES          = 5;

    /* ENTITIES POSITION */
    public static final double AHMAD_INITIAL_MAP_X    = 0.0;
    public static final double AHMAD_INITIAL_MAP_Y    = 0.0;

    public static final double AHMAD_FLY_SPEED = 4.0;

    /* ENTITIES SOLID AREA SETTINGS */
    public static final int AHMAD_VERTEX_1_X = -8 * AHMAD_SPRITES_SCALE;
    public static final int AHMAD_VERTEX_1_Y = -26 * AHMAD_SPRITES_SCALE;
    public static final int AHMAD_VERTEX_2_X = 8 * AHMAD_SPRITES_SCALE;
    public static final int AHMAD_VERTEX_2_Y = -26 * AHMAD_SPRITES_SCALE;
    public static final int AHMAD_VERTEX_3_X = 8 * AHMAD_SPRITES_SCALE;
    public static final int AHMAD_VERTEX_3_Y = 26 * AHMAD_SPRITES_SCALE;
    public static final int AHMAD_VERTEX_4_X = -8 * AHMAD_SPRITES_SCALE;
    public static final int AHMAD_VERTEX_4_Y = 26 * AHMAD_SPRITES_SCALE;

    /* OBJECTS SOLID AREA SETTINGS */
    public static final int LANDSTONE_VERTEX_1_X = -28 * LANDSTONE_SPRITES_SCALE;
    public static final int LANDSTONE_VERTEX_1_Y = -18 * LANDSTONE_SPRITES_SCALE;
    public static final int LANDSTONE_VERTEX_2_X = 28 * LANDSTONE_SPRITES_SCALE;
    public static final int LANDSTONE_VERTEX_2_Y = -18 * LANDSTONE_SPRITES_SCALE;
    public static final int LANDSTONE_VERTEX_3_X = 15 * LANDSTONE_SPRITES_SCALE;
    public static final int LANDSTONE_VERTEX_3_Y = 28 * LANDSTONE_SPRITES_SCALE;
    public static final int LANDSTONE_VERTEX_4_X = -12 * LANDSTONE_SPRITES_SCALE;
    public static final int LANDSTONE_VERTEX_4_Y = 28 * LANDSTONE_SPRITES_SCALE;

    /* MATH CONSTANTS */
    public static final double PI = 3.14159265358979;


}

