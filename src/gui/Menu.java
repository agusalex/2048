package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class Menu {

	private int opcion;
	
	public void tick(){
		if(Game.optionSelect){ //Nuevo juegfo
			if(Game.menuOption==0){
				if(Game.menu)
					Game.menu = false;
				else
					Game.menu = true;
			}
			else if(Game.menuOption==1){ //Opciones
				
				
			}
			
			else if(Game.menuOption==2){
				
			}
			else if(Game.menuOption==3){
				System.exit(0);
			}
			Game.optionSelect=false;
		}
		
		
		
		opcion =Game.MatrixY+Game.cellDistance*Game.menuOption;	
	}
	
	
	
	public void render(Graphics g){
		
		g.setColor(Color.RED);
		Font font = Game.getCustomFont();
		float size=30F;
		font = Game.Fuente.deriveFont(size);
		int botonXS=(int)(Game.MatrixX+Game.cellDistance-Game.lineWidth/2.3);
		int botonYS=(int)(opcion-Game.lineWidth/2.3);
		int anchoS=(int)((Game.cellSize*2)+2*Game.lineWidth);
		int altoS=(int)(Game.cellSize+Game.lineWidth/1.15);
		int curva=Game.cellAndNumberCurve;
		
		int botonX=(int)(Game.MatrixX+(Game.cellDistance)+Game.lineWidth/15);
	
		int ancho= (int)((Game.cellSize*2)+Game.lineWidth*1.1);
		int alto=(int)(Game.cellSize);

		
		
		g.setFont(font);
		g.drawString("2048",(int)(Game.WIDTH/2.3),Game.HEIGHT/9);
		g.drawString("THE GAME!",(int)(Game.WIDTH/2.5),Game.HEIGHT/7);
		
		//recuadro de seleccion
		g.setColor(new Color(0x92360e));
		
		g.fillRoundRect(botonXS,botonYS,anchoS,altoS,curva,curva);
		
		g.setColor(new Color(0xFF5B3D));
		g.fillRoundRect(botonX,Game.MatrixY,ancho,alto,curva,curva);
		g.setColor(new Color(0xEEE4DA));


		g.drawString("New Game",(int)(Game.WIDTH/2.4),Game.HEIGHT/4);
		//////
		

	
		
		
		g.setColor(new Color(0xEDC53F));
		g.fillRoundRect(botonX,Game.MatrixY+Game.cellDistance,ancho,alto,curva,curva);
		g.setColor(new Color(0x766B60));


		g.drawString("Options",(int)(Game.WIDTH/2.3),(int)(Game.HEIGHT/2.5));
		
		g.setColor(new Color(0xECE0C8));
		g.fillRoundRect(botonX,Game.MatrixY+Game.cellDistance*2,ancho,alto,curva,curva);
		g.setColor(new Color(0x766B60));


		g.drawString("Rankings",(int)(Game.WIDTH/2.3),(int)(Game.HEIGHT/1.8));

		g.setColor(new Color(0xF2B179));
		g.fillRoundRect(botonX,Game.MatrixY+Game.cellDistance*3,ancho,alto,curva,curva);
		g.setColor(new Color(0x766B60));


		g.drawString("Quit Game",(int)(Game.WIDTH/2.3),(int)(Game.HEIGHT/1.4));
		
		
		
	}

	
	
	
}
