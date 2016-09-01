package gui;

import java.awt.Graphics;

public class Cell extends GraphicObject{

	public Cell(int x, int y,Game game) {
		super(x, y,game);
		
		this.id = ID.CELL;
		this.HEIGHT = game.getHeight() / 24;
		this.WITDH = game.getWidth() / 24;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
