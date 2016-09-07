package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Menu {

	public void render(Graphics g){
		
		g.setColor(Color.RED);
		Font font = new Font(Font.SANS_SERIF,1,30);
		g.setFont(font);
		g.drawString("2048",(int)(Game.WIDTH/2.3),Game.HEIGHT/9);
		g.drawString("THE GAME!",(int)(Game.WIDTH/2.5),Game.HEIGHT/7);
		
		g.setColor(new Color(0xFF5B3D));
		g.fillRect((int)(Game.WIDTH/2.6),(int)(Game.HEIGHT/5.8), (int)(Game.HEIGHT/3.6),Game.HEIGHT/9);
		g.setColor(new Color(0xEEE4DA));
		font =  new Font(Font.SANS_SERIF,1,30);
		g.setFont(font);
		g.drawString("New Game",(int)(Game.WIDTH/2.4),Game.HEIGHT/4);
		
		g.setColor(new Color(0xEDC53F));
		g.fillRect((int)(Game.WIDTH/2.6),(int)(Game.HEIGHT/3.1), (int)(Game.HEIGHT/3.6),Game.HEIGHT/9);
		g.setColor(new Color(0x766B60));
		font =  new Font(Font.SANS_SERIF,1,30);
		g.setFont(font);
		g.drawString("Options",(int)(Game.WIDTH/2.3),(int)(Game.HEIGHT/2.5));
		
		
		
		
		
		
	}
	
	
}
