package gui;

import java.awt.Graphics;

abstract class GraphicObject {

    int x;
    int y;
    int speedX;
    int speedY;
    int width;
    int height;


    GraphicObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void tick();

    public abstract void render(Graphics g);




    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }


    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }


}
