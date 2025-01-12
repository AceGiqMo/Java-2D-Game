package gameworld;

import java.awt.Graphics2D;

import main.GamePanel;
import main.Config;

import gameworld.entities.Ahmad;
import gameworld.objects.GameObject;
import gameworld.objects.SolidGameObject;
import gameworld.objects.LandStone;

public class MapManager {
    private GamePanel gp;
    /*
     * Basically position of camera is just posistion of left upper corner of the screen on the map
     * in the canonical coordinate system
     */
    private double cameraX;
    private double cameraY;

    /* ENTITIES */
    private Ahmad        ahmad;

    /* OBJECTS */
    private SolidGameObject[] objects;

    public MapManager(GamePanel gp, Ahmad ahmad) {
        this.gp = gp;

        this.ahmad   = ahmad;
        this.objects = new SolidGameObject[1];

        this.cameraX = Config.CAMERA_INITIAL_X;
        this.cameraY = Config.CAMERA_INITIAL_Y;

        LandStone landStone = new LandStone(gp, 0, 400.0, cameraX, cameraY);
        landStone.setSolidArea();

        objects[0] = landStone;
    }

    public final void update() {
        ahmad.update();

        double newCameraX = ahmad.getMapX() - Config.SCREEN_WIDTH / 2;
        double newCameraY = ahmad.getMapY() + Config.SCREEN_HEIGHT / 2;

        for (SolidGameObject object : objects) {
            object.updateScreenPos(newCameraX - cameraX, newCameraY - cameraY);
            object.updateSolidAreaScreenPos(newCameraX - cameraX, newCameraY - cameraY);
            object.update();
        }

        cameraX = newCameraX;
        cameraY = newCameraY;

    }

    public final void draw(Graphics2D g2) {
        for (GameObject object: objects) {
            object.draw(g2);
        }

        ahmad.draw(g2);

    }
}
