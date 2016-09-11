package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Window extends Canvas{
	public static boolean restart=true;
	
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
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);  //empieza en el medio
		
		frame.add(game);


		try {
		    frame.setIconImage(ImageIO.read(new File("img/icon.png")));
		}
		catch (IOException exc) {
		    exc.printStackTrace();
		}
		
		
		
		frame.setVisible(true);
		
	
		game.start();
		
		
		

	}
	
	
	
}