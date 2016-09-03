package gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import paquete.Direction;



/*
 * clase que maneja las acciones del teclado, y tiene los metodos keyPressed y keyReleased que manjean opciones al apretar
 * y soltar botones , nos interesa solo la de soltar botones, ya que al soltar ahi se mueven los numeros
 */

public class KeyInput extends KeyAdapter{

	private Handler handler;
	private Game game;
	
	
	
	/*
	 * el constructor llama a un handler y a un jjuego, esto es para acceder a los numeros que quiero mover y
	 * al metodo de SHIFT que esta en la clase juego asi regula los movimientos al soltar las teclas
	 */
	
	public KeyInput(Handler handler,Game game){
		this.handler = handler;
		this.game = game;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
	//	CODIGO DE PRUEBA ESTO MUEVE LAS CASILLAS CON 2
		for(int x = 0; x < handler.gameObjects.size(); x++){
			GraphicObject element = handler.gameObjects.get(x);
			if(element instanceof Number){
				//if(key == KeyEvent.VK_UP) element.setSpeedY(-5);
				//if(key == KeyEvent.VK_DOWN) element.setSpeedY(5);
				//if(key == KeyEvent.VK_LEFT) element.setSpeedX(-5);
				//if(key == KeyEvent.VK_RIGHT) element.setSpeedX(5);	
				
			}
		}
    
	}
	
	//TODO intentar refractorear para no hacer un ciclo para cada una 
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		//si soltas la flecha de arriba
			if(key == KeyEvent.VK_UP) {
				//busca en todos los objetos graficos
				for(int x = 0; x < handler.gameObjects.size(); x++){
					GraphicObject objeto = handler.gameObjects.get(x);
					//si encuentra numeros
					if(objeto instanceof Number){
						objeto = (Number) objeto;
						//de acuerda a su direccion , mueve al numero tantas unidades como se pasa 
						objeto.setSpeedY(-30);	
						
					}
				}
				//mueve y combina llamando a play de juego que llama a SHIFT
				game.play(Direction.UP);
				//para verificar igualdad
				System.out.println(game.getMatJuego());
				//actualizo a las celdas con numeros arriba de ellas
				game.updateMatrix();
			}
			if(key == KeyEvent.VK_DOWN){ 
				for(int x = 0; x < handler.gameObjects.size(); x++){
					GraphicObject objeto = handler.gameObjects.get(x);
					if(objeto instanceof Number){
						objeto = (Number) objeto;
						objeto.setSpeedY(30);
						
					}
				}
				game.play(Direction.DOWN);
				System.out.println(game.getMatJuego());
				game.updateMatrix();
			}
			if(key == KeyEvent.VK_LEFT){
				for(int x = 0; x < handler.gameObjects.size(); x++){
					GraphicObject objeto = handler.gameObjects.get(x);
					if(objeto instanceof Number){
						objeto = (Number) objeto;
						objeto.setSpeedX(-30);						
					}
				}
				game.play(Direction.LEFT);
				System.out.println(game.getMatJuego());
				game.updateMatrix();
			}
			if(key == KeyEvent.VK_RIGHT){
				for(int x = 0; x < handler.gameObjects.size(); x++){
					GraphicObject objeto = handler.gameObjects.get(x);
					if(objeto instanceof Number){
						objeto = (Number) objeto;
						objeto.setSpeedX(30);
						
					}
				}
				game.play(Direction.RIGHT);
				System.out.println(game.getMatJuego());
				game.updateMatrix();
			}
		
			
	}
	
	
	
	
}
