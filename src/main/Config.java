package main;

public class Config {

    /* SCREEN CONFIGURATION */
    public static final int SCREEN_WIDTH  = 1280;
    public static final int SCREEN_HEIGHT = 720;

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
    public static final int SPRITE_CHANGE_INTERVAL    = 8;
    public static final int AHMAD_FLY_SPRITE_WIDTH    = 128;
    public static final int AHMAD_FLY_SPRITE_HEIGHT   = 128;
    public static final int AHMAD_STAND_SPRITE_WIDTH  = 120;
    public static final int AHMAD_STAND_SPRITE_HEIGHT = 120;
    public static final int AHMAD_FLY_SPRITES         = 4;
    public static final int AHMAD_STAND_SPRITES       = 4;
    public static final int SUPER_AHMAD_FLY_SPRITES   = 4;

    /* ENTITIES POSITION */
    public static final int AHMAD_INITIAL_MAP_X    = 0;
    public static final int AHMAD_INITIAL_MAP_Y    = 0;

    public static final int AHMAD_FLY_SPEED = 4;

    /* MATH CONSTANTS */
    public static final double PI = 3.14159265358979;


}

