package by.sadovnick;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.Random;

@Getter
@Setter
public class Ball extends Rectangle {

    private Random random;
    private int xVelocity;
    private int yVelocity;
    private int initSpeed = 5;

    public Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        random = new Random();
        int randomXDirection = random.nextInt(2);
        if (randomXDirection == 0) {
            randomXDirection--;
        }
        setXDirection(randomXDirection * initSpeed);
        int randomYDirection = random.nextInt(2);
        if (randomYDirection == 0) {
            randomYDirection--;
        }
        setYDirection(randomYDirection * initSpeed);

    }

    public void setXDirection(int randomXDirection) {
        this.xVelocity = randomXDirection;
    }

    public void setYDirection(int randomYDirection) {
        this.yVelocity = randomYDirection;
    }

    public void move() {
        x += xVelocity;
        y += yVelocity;
    }

    public void draw(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fillOval(x, y, width, height);
    }
}
