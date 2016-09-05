package gui;

import java.awt.Color;
import java.awt.Graphics;

public class Cell extends GraphicObject{

	public Cell(int x, int y, Game game) {
		super(x, y,game);
		this.height = game.getCellSize();
		this.width = this.height;
	}


	public void tick() {
		// TODO Auto-generated method stub
		
	}

	
	public void render(Graphics g) {

		g.setColor(Game.CELDA);
		g.fillRect(x, y,height, width);
		
		
	}

}
