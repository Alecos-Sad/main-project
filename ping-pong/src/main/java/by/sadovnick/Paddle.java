package by.sadovnick;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle {

    private int id;
    private int yVelocity;
    int speed = 10;

    public Paddle(int x, int y, int PUDDLE_WIDTH, int PUDDLE_HEIGHT, int id) {
        super(x, y, PUDDLE_WIDTH, PUDDLE_HEIGHT);
        this.id = id;
    }

    public void keyPressed(KeyEvent event) {
        switch (id) {
            case 1 -> {
                if (event.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(-speed);
                }
                if (event.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(speed);
                }
            }
            case 2 -> {
                if (event.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(-speed);
                }
                if (event.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(speed);
                }
            }
        }
    }

    public void keyUp(KeyEvent event) {
        switch (id) {
            case 1 -> {
                if (event.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(0);
                }
                if (event.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(0);
                }
            }
            case 2 -> {
                if (event.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(0);
                }
                if (event.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(0);
                }
            }
        }
    }

    private void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }

    public void move() {
        y += yVelocity;
    }

    public void draw(Graphics graphics) {
        if (id == 1) {
            graphics.setColor(Color.red);
        } else {
            graphics.setColor(Color.blue);
        }

        graphics.fillRect(x, y, width, height);
    }
}
