package games;

import java.awt.Color;
import java.awt.Graphics;

public class Player {

    private int circleRadius = 25;
    private final int mouthAngle = 160;
    private final int mouthLength = 10;
    private final int eyeRadius = 5;
    private final int eyeDistance = 10;
    private final int eyeAngle = 45;
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
        gr.setColor(new Color(120, 120, 120));
        gr.drawArc(this.x - this.mouthLength, this.y + this.mouthLength, this.mouthLength, this.mouthLength, 0,
                this.mouthAngle);
        gr.setColor(new Color(80, 80, 80));
        gr.drawArc(this.x - this.mouthLength, this.y + this.mouthLength, this.mouthLength, this.mouthLength, 180,
                this.mouthAngle);
        gr.setColor(new Color(40, 40, 40));
        gr.drawArc(this.x - this.mouthLength, this.y + this.mouthLength, this.mouthLength, this.mouthLength, 360,
                this.mouthAngle);
        // body
        gr.drawOval(this.x - this.circleRadius, this.y - this.circleRadius, this.circleRadius * 2,
                this.circleRadius * 2);

    }

}
