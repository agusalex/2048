package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Number extends GraphicObject {

	private Integer value;
	private int iX,iY;
	private static final Color fontC1=new Color(0x766B60);
	private static final Color fontC2=new Color(0xFDFEFA);
	private static final Color N_2 = new Color(0xEEE4DA);
	private static final Color N_4 = new Color(0xECE0C8);
	private static final Color N_8 = new Color(0xF2B179);
	private static final Color N_16 = new Color(0xF6955F);
	private static final Color N_32= new Color(0xFB7864);
	private static final Color N_64 = new Color(0xF25F3D);
	private static final Color N_128 = new Color(0xEDCE71);
	private static final Color N_256 = new Color(0xEDCC63);
	private static final Color N_512 = new Color(0xECC850);
	private static final Color N_1024 = new Color(0xEDC53F);
	private static final Color N_2048 = new Color(0xFF5B3D);
	
	
	
	
	
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
		Color fontColor=fontC1;
		int fontSize=height/2;
		int curve=Game.cellAndNumberCurve;
		if(value!=null){
			switch(value){
				case 2 : g.setColor(N_2);break;
				case 4 : g.setColor(N_4);break;
				case 8 : g.setColor(N_8);break;
				case 16 : g.setColor(N_16);break;
				case 32 : g.setColor(N_32);break;
				case 64 : g.setColor(N_64);break;
				case 128 : g.setColor(N_128);break;
				case 256 : g.setColor(N_256);break;
				case 512 : g.setColor(N_512);break;
				case 1024 : g.setColor(N_1024);break;
				case 2048 : g.setColor(N_2048);break;
			}
			
			g.fillRoundRect(x, y,height, width,curve,curve);
			
			if(value >= 8){
				fontColor = fontC2;
			}
				g.setColor(fontColor);
			Font font = new Font(Font.SANS_SERIF,1,fontSize);
			
			g.setFont(font);
			//g.drawString(value.toString(), x+height/2, y+height/2);
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
	
	
	public int getCifras(){
		if (value==null)
			return 0;
		
		if(value<10)
			return 1;
		
		if(value<100)
			return 2;
		if (value<1000)
			return 3;
		else{
			return 4;
		}

	}
	
	
	
	
	public Integer getValue() {
		return value;
	}

	
	public void setValue(Integer value) {
		this.value = value;
	}

	
	
	
}
