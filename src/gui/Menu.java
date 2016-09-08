package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import paquete.Mat2;

public class Menu {

	private int opcion;
	
	public void tick(){
		if(Game.optionSelect){
			if(Game.menuOption==0){
				Game.rebootGame=true;
			}
			else if(Game.menuOption==1){
				
			}
			
			else if(Game.menuOption==2){
				
			}
			else if(Game.menuOption==3){
				System.exit(0);
			}
		}
		
		
		
		opcion =Game.MatrixY+Game.cellDistance*Game.menuOption;	
	}
	
	
	
	public void render(Graphics g){
		
		g.setColor(Color.RED);
		Font font = Game.getCustomFont();
		float size=30F;
		font = Game.Fuente.deriveFont(size);
		
		g.setFont(font);
		g.drawString("2048",(int)(Game.WIDTH/2.3),Game.HEIGHT/9);
		g.drawString("THE GAME!",(int)(Game.WIDTH/2.5),Game.HEIGHT/7);
		
		g.setColor(new Color(0x92360e));
		g.fillRoundRect((int)(Game.MatrixX+Game.cellDistance-Game.lineWidth/2.3),(int)(opcion-Game.lineWidth/2.3),(int)((Game.cellSize*2)+2*Game.lineWidth),(int)(Game.cellSize+Game.lineWidth/1.15),Game.cellAndNumberCurve,Game.cellAndNumberCurve);
		
		g.setColor(new Color(0xFF5B3D));
		g.fillRoundRect((int)(Game.MatrixX+(Game.cellDistance)+Game.lineWidth/10),Game.MatrixY, (int)(Game.cellSize*2)+Game.lineWidth,(int)(Game.cellSize),Game.cellAndNumberCurve,Game.cellAndNumberCurve);
		g.setColor(new Color(0xEEE4DA));


		g.drawString("New Game",(int)(Game.WIDTH/2.4),Game.HEIGHT/4);
		//////
		

	
		
		
		g.setColor(new Color(0xEDC53F));
		g.fillRoundRect((int)(Game.MatrixX+(Game.cellDistance)+Game.lineWidth/10),Game.MatrixY+Game.cellDistance,  (int)(Game.cellSize*2)+Game.lineWidth,(int)(Game.cellSize),Game.cellAndNumberCurve,Game.cellAndNumberCurve);
		g.setColor(new Color(0x766B60));


		g.drawString("Options",(int)(Game.WIDTH/2.3),(int)(Game.HEIGHT/2.5));
		
		g.setColor(new Color(0xECE0C8));
		g.fillRoundRect((int)(Game.MatrixX+(Game.cellDistance)+Game.lineWidth/10),Game.MatrixY+Game.cellDistance*2, (int)(Game.cellSize*2)+Game.lineWidth,(int)(Game.cellSize),Game.cellAndNumberCurve,Game.cellAndNumberCurve);
		g.setColor(new Color(0x766B60));


		g.drawString("Rankings",(int)(Game.WIDTH/2.3),(int)(Game.HEIGHT/1.8));

		g.setColor(new Color(0xF2B179));
		g.fillRoundRect((Game.MatrixX+(Game.cellDistance)+Game.lineWidth/10),Game.MatrixY+Game.cellDistance*3, (int)(Game.cellSize*2)+Game.lineWidth,(int)(Game.cellSize),Game.cellAndNumberCurve,Game.cellAndNumberCurve);
		g.setColor(new Color(0x766B60));


		g.drawString("Quit Game",(int)(Game.WIDTH/2.3),(int)(Game.HEIGHT/1.4));
		
		
		
	}

	
	
	
}
