package main;

import java.awt.geom.Point2D;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class MouseHandler implements MouseListener, MouseMotionListener {

    private int mouseX;
    private int mouseY;

    private boolean moved;
    private boolean pressed;
    private boolean oneFramePressed;

    @Override
    public final void mouseClicked(MouseEvent e) {
    }

    @Override
    public final void mousePressed(MouseEvent e) {
        if (pressed) {
            return;
        }

        pressed = true;
        oneFramePressed = true;
    }

    @Override
    public final void mouseReleased(MouseEvent e) {
        pressed = false;
    }

    @Override
    public final void mouseEntered(MouseEvent e) {
    }

    @Override
    public final void mouseExited(MouseEvent e) {
    }

    @Override
    public final void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        moved = true;
    }

    @Override
    public final void mouseDragged(MouseEvent e) {
    }

    public final Point2D.Double getPos() {
        return new Point2D.Double(mouseX, mouseY);
    }

    public final boolean isMoved() {
        if (moved) {
            moved = false;
            return true;
        }

        return false;
    }

    public final boolean isPressed() {
        if (oneFramePressed) {
            oneFramePressed = false;
            return true;
        }

        return false;
    }

}
