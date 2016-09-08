package gui;

import java.awt.Canvas;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = -4166964448327045634L;

	
	public  Window(int witdh, int height, String title,Game game){
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension (witdh,height));
		frame.setMaximumSize(new Dimension (witdh,height));
		frame.setMinimumSize(new Dimension (witdh,height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);  //empieza en el medio
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}
}