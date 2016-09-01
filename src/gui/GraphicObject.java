package gui;

import java.awt.Graphics;

public abstract class GraphicObject {

	protected int x, y, speedX, speedY, width, height;
	protected ID id;
	
	
	
	public GraphicObject(int x, int y,Game game){
		this.x = x;
		this.y = y;
	}

	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
	
	
	
}
