package games;

import java.awt.Graphics;
import java.awt.Point;

public class Player {

    private int circleRadius = 25;
    private final int mouthAngle = 160;
    private final int mouthLength = 10;
    private final int eyeRadius = 5;
    private final int eyeDistance = 9;
    private int x = 0, y = 0;
    private int initialX = 0, initialY = 0;

    public Player(Point p) {
        this.x = p.x;
        this.y = p.y;
        this.initialX = p.x;
        this.initialY = p.y;
    }

    public void move(int x, int y) {
        this.x = this.initialX + x;
        this.y = this.initialY + y;
    }

    public void draw(Graphics gr) {
        // eyes
        gr.drawOval(this.x - this.eyeDistance, this.y - this.eyeDistance, this.eyeRadius, this.eyeRadius);
        gr.drawOval(this.x + this.eyeDistance, this.y - this.eyeDistance, this.eyeRadius, this.eyeRadius);
        // mouth
        gr.drawArc(this.x - this.mouthLength + 2, this.y + 2,
                this.mouthLength * 2, this.mouthLength, 180,
                this.mouthAngle);
        // body
        gr.drawOval(this.x - this.circleRadius, this.y - this.circleRadius, this.circleRadius * 2,
                this.circleRadius * 2);
    }

    public boolean isPointInside(Point p) {
        if (p.x > this.x - this.circleRadius && p.x < this.x + this.circleRadius
                && p.y > this.y - this.circleRadius && p.y < this.y + this.circleRadius) {
            return true;
        }
        return false;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getSpeed() {
        return 2;
    }
}
