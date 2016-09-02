package gui;

import java.awt.Color;
import java.awt.Font;
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



		
		
		
		if(value!=null){
			g.setColor(Color.white);
			g.fillRect(x, y,height, width);
			g.setColor(Color.RED);
			Font font=new Font(Font.SANS_SERIF,1,height);
			
			g.setFont(font);
			g.drawString(value.toString(), x+height/5, y+height-height/7);
		}
	
	}

	
	
	
	
	
}
