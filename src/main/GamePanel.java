package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

    /* SCREEN SETTINGS */
    private int screenWidth;
    private int screenHeight;
    private int screenWidthFull;
    private int screenHeightFull;

    /* SYSTEM */
    private Thread gameThread;
    private MouseHandler mouseH = new MouseHandler();
    private Sound sound = new Sound();

    /* TEMPORARY SCREEN */
    private BufferedImage tempScreen;
    private Graphics2D g2;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);

        /* All the drawing from this component will be done in an offscreen painting buffer */
        this.setDoubleBuffered(true);
        this.addMouseListener(mouseH);
        this.addMouseMotionListener(mouseH);
        this.setFocusable(true);

    }

    /**
     * This method sets necessary configuration for game launch
     */
    public void setUpGame() {
        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB_PRE);
        g2 = (Graphics2D) tempScreen.getGraphics();
    }

    /**
     * This method launches the game thread
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
    }

    /**
     * This method sets full screen mode according to the size of the monitor
     */
    public void setFullScreen() {
        /* GET LOCAL SCREEN DEVICE */
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice      gd = ge.getDefaultScreenDevice();

        gd.setFullScreenWindow(Main.getWindow());

        /* GET FULL SCREEN WIDTH AND HEIGHT */
        screenWidthFull  = Main.getWindow().getWidth();
        screenHeightFull = Main.getWindow().getHeight();
    }

    /**
     * This method updates all changes in the game process
     */
    public void update() {
    }

    /**
     * This method draws all graphics of the frame inside the temporary screen
     */
    public void drawToTempScreen() {
        g2.clearRect(0, 0, screenWidthFull, screenHeightFull);
    }

    /**
     * This method scales temporary screen and projects it onto full screen of the monitor
     */
    public void drawToScreen() {
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidthFull, screenHeightFull, null);
        g.dispose();
    }

    /**
     * This method sets the definite melody on the background
     * @param i
     */
    public final void playMusic(int i) {

        sound.setFile(i);
        sound.play();
        sound.loop();

    }

    /**
     * This method stops the background melody
     */
    public final void stopMusic() {
        sound.stop();
    }

    /**
     * This method plays the definite sound effect depending on the game process
     * @param i
     */
    public final void playSoundEffect(int i) {
        sound.setFile(i);
        sound.play();
    }

}
