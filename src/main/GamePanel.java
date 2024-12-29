package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

    /* SCREEN SETTINGS */
    private int screenWidth;
    private int screenHeight;
    private int screenWidthFull;
    private int screenHeightFull;

    /* SYSTEM */
    private Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);

        /* All the drawing from this component will be done in an offscreen painting buffer */
        this.setDoubleBuffered(true);

    }

    /**
     * Sets necessary configuration for game launch
     */
    public void setUpGame() {
    }

    public void startGameThread() {
    }

    @Override
    public void run() {
    }

    /**
     * This method scales temporary screen into full screen of the monitor
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






}
