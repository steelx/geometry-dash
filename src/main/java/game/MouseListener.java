package game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter {
    public boolean mousePressed = false;
    public boolean mouseDragged = false;
    public int mouseButton = -1;
    public float x = -1f, y = -1f;
    public float dx = -1f, dy = -1f;

    @Override
    public void mousePressed(MouseEvent event) {
        this.mousePressed = true;
        this.mouseButton = event.getButton();
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        this.mousePressed = false;
        this.mouseDragged = false;
        this.dx = 0;
        this.dy = 0;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.mouseDragged = true;
        this.dx = e.getX() + this.x;
        this.dy = e.getY() + this.y;
    }
}
