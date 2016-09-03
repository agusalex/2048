package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Number extends GraphicObject {

	private Integer value;


	public Number(int x, int y, Integer value, Game game) {
		super(x, y, game);
		this.value = value;
		this.height = game.getCellSize();
		this.width = this.height;
		
				
	}


	public void tick() {
		this.x += this.speedX;
		this.y += this.speedY;
		
		
		
		//en base a la tecla de la clase KeyInput , se va a mover el numero a done deba
		//clamp, garantiza que no se pase del limite TODO (igual falta mejorar a la derecha)
		
		x = clamp(x, Game.WIDTH/4+Game.WIDTH/60,(Game.WIDTH/4+Game.WIDTH/60)*4);
		y = clamp(y,Game.HEIGHT/6,(Game.HEIGHT/6)*4);
		
	}

	
	public void render(Graphics g) {

		if(value!=null){
			g.setColor(Color.white);
			g.fillRect(x, y,height, width);
			g.setColor(Color.RED);
			Font font = new Font(Font.SANS_SERIF,1,height/2);
			
			g.setFont(font);
			g.drawString(value.toString(), x+height/5, y+height-height/7);
		
		}
	
	}

	
	
	
	// esto es para que no se pase del limite de la "matriz", ese decir, para que los numeros esten dentro de la tabla
	public static int clamp (int var, int min , int max){
		if(var <= min)
			return var = min;
		else if(var >= max)
			return var = max;
		return var;
	}
	
	
	public Integer getValue() {
		return value;
	}

	
	public void setValue(Integer value) {
		this.value = value;
	}

	
	
	
}
