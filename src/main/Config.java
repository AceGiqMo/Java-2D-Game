package main;

public class Config {

    /* SCREEN CONFIGURATION */
    public static final int SCREEN_WIDTH  = 1280;
    public static final int SCREEN_HEIGHT = 720;

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
    public static final int AHMAD_FLY_SPRITE_WIDTH    = 64;
    public static final int AHMAD_FLY_SPRITE_HEIGHT   = 128;
    public static final int AHMAD_STAND_SPRITE_WIDTH  = 100;
    public static final int AHMAD_STAND_SPRITE_HEIGHT = 120;
    public static final int AHMAD_FLY_SPRITES         = 4;
    public static final int AHMAD_STAND_SPRITES       = 4;
    public static final int SUPER_AHMAD_FLY_SPRITES   = 4;

    /* ENTITIES POSITION */
    public static final int AHMAD_INITIAL_MAP_X    = 0;
    public static final int AHMAD_INITIAL_MAP_Y    = 0;

    public static final int AHMAD_FLY_SPEED = 4;


}
