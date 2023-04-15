package games;

import java.awt.Graphics;

public class Player {

    private int circleRadius = 25;
    private final int mouthAngle = 160;
    private final int mouthLength = 10;
    private final int eyeRadius = 5;
    private final int eyeDistance = 9;
    private int x = 0, y = 0;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
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

}
