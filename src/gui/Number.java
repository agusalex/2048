package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Number extends GraphicObject {

	private Integer value;
	private int iX,iY;

	public int getiX() {
		return iX;
	}


	public void setiX(int iX) {
		this.iX = iX;
	}


	public int getiY() {
		return iY;
	}


	public void setiY(int iY) {
		this.iY = iY;
	}


	public Number(int x, int y, int iX, int iY,Integer value, Game game) {
		super(x, y, game);
		this.iX=iX;
		this.iY=iY;
		this.value = value;
		this.height = game.getCellSize();
		this.width = this.height;
		
				
	}


	public void tick() {
		this.x += this.speedX;
		this.y += this.speedY;
		
		
		
		//en base a la tecla de la clase KeyInput , se va a mover el numero a done deba
		//clamp, garantiza que no se pase del limite
		
		//x = clamp(x, Game.WIDTH/4+Game.WIDTH/60,(Game.WIDTH/4+Game.WIDTH/60)*4);
		//y = clamp(y,Game.HEIGHT/6,(Game.HEIGHT/6)*4);
	x = clamp(x,Game.MatrixX,Game.MatrixX+Game.MatrixWIDTH-width);  //ARREGLADO
	y = clamp(y,Game.MatrixY,Game.MatrixY+Game.MatrixWIDTH-height);
		
	}

	
	public void render(Graphics g) {

		if(value!=null){
			switch(value){
				case 2 : g.setColor(Color.white);break;
				case 4 : g.setColor(Color.green);break;
				case 8 : g.setColor(Color.blue);break;
				case 16 : g.setColor(Color.orange);break;
				case 32 : g.setColor(Color.magenta);break;
				default : g.setColor(Color.black);break;
			}
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
