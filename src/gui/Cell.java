package gui;

import java.awt.Color;
import java.awt.Graphics;

public class Cell extends GraphicObject{

	public Cell(int x, int y, Game game) {
		super(x, y,game);
		
		this.id = ID.CELL;
		this.height =game.getCellSize();
		this.width = height;
		
	
		
		
	}


	public void tick() {
		// TODO Auto-generated method stub
		
	}

	
	public void render(Graphics g) {

		g.setColor(Color.yellow);
		g.fillRect(x, y,height, width);
		
		
	}

}
