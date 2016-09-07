package gui;


import java.awt.Graphics;

public class Cell extends GraphicObject{

	public Cell(int x, int y, Game game) {
		super(x, y,game);
		this.height = game.getCellSize();
		this.width = this.height;
	}


	public void tick() {
		// tick tock
		
	}

	
	public void render(Graphics g) {
	int curve=Game.cellAndNumberCurve;
		g.setColor(Game.CELDA);
		g.fillRoundRect(x, y,height, width,curve,curve);
		
		
	}

}
