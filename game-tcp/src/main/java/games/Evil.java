package games;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Evil {

    private int x = 0, y = 0;
    private Color evilColor = new Color(255, 0, 0);
    int[] xPoints = { 50, 70, 90, 100, 120, 100, 90, 70, 50, 30, 20, 0, 20 };
    int[] yPoints = { 0, 40, 40, 70, 100, 80, 110, 110, 150, 110, 110, 80, 100 };

    private int speed = 01;

    public Evil(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics gr) {
        gr.setColor(this.evilColor);
        // gr.drawPolygon(xPoints, yPoints, xPoints.length);
        int[] intX = { this.x + 10, this.x + 50, this.x + 50, this.x + 10 };
        int[] intY = { this.y + 40, this.y + 40, this.y + 80, this.y + 80 };
        gr.drawPolygon(intX, intY, intX.length);
    }

    public boolean isPointInside(Point p) {
        if (p.x > this.x && p.x < this.x + 50 && p.y > this.y && p.y < this.y + 80) {
            return true;
        }
        return false;
    }

    public void moveToDirection(int x2, int y2) {
        x2 += (int) (Math.random() * 200 - 100 + 1);
        y2 += (int) (Math.random() * 200 - 100 + 1);

        if (this.x < x2) {
            this.x += this.speed;
        } else if (this.x > x2) {
            this.x -= this.speed;
        }
        if (this.y < y2) {
            this.y += this.speed;
        } else if (this.y > y2) {
            this.y -= this.speed;
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setPoint(Point newEvilPoint) {
        this.x = newEvilPoint.x;
        this.y = newEvilPoint.y;
    }
}
