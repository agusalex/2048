package gui;

import java.awt.Graphics;

public class Number extends GraphicObject {

	private Integer value;


	public Number(int x, int y, Integer value, Game game) {
		super(x, y, game);
		this.value=value;

		id=ID.NUMBER;
				
	}

	
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
}
