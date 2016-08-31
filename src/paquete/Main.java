package paquete;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;


public class Main {
	static Juego j;
	private JFrame frame;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
		j= new Juego();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnBoton = new JButton("BOTON");
		btnBoton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
				j.play(Direction.LEFT);
				
				lblNewLabel.setText(j.obtainStatus());
				System.out.println(j.getMatJuego());
			
				
				
				
			}
		});
		btnBoton.setBounds(152, 129, 89, 23);
		frame.getContentPane().add(btnBoton);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 48, 403, 14);
		frame.getContentPane().add(lblNewLabel);
	}
}
