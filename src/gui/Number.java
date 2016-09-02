package gui;

import java.awt.Color;
import java.awt.Graphics;

public class Number extends GraphicObject {

	private Integer value;


	public Number(int x, int y, Integer value, Game game) {
		super(x, y, game);
		this.value=value;
		this.height =game.getCellSize();
		this.width = height;
		id=ID.NUMBER;
				
	}

	
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	
	public void render(Graphics g) {
		g.setColor(Color.RED);
		if(value!=null){
			g.drawString(value.toString(), x, y);
		}
	
	}

	
	
	
	
	
}
