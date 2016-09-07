package gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import paquete.Direction;
import paquete.Mat2;



/*
 * clase que maneja las acciones del teclado, y tiene los metodos keyPressed y keyReleased que manjean opciones al apretar
 * y soltar botones , nos interesa solo la de soltar botones, ya que al soltar ahi se mueven los numeros
 */

public class KeyInput extends KeyAdapter{
	private Direction dir;
	private Handler handler;
	private Game game;
	private boolean Animate=false;
	
	/*
	 * el constructor llama a un handler y a un jjuego, esto es para acceder a los numeros que quiero mover y
	 * al metodo de SHIFT que esta en la clase juego asi regula los movimientos al soltar las teclas
	 */
	
	public boolean isAnimate() {
		return Animate;
	}

	public void setAnimate(boolean animate) {
		Animate = animate;
	}

	public KeyInput(Handler handler,Game game){
		this.handler = handler;
		this.game = game;
	}

	public void tick(){
		if(game.isMenu()){
			
		}
		else{
		  if(dir!=null&&(!Animate)){
			for(int x = 0; x < handler.gameObjects.size(); x++){
				GraphicObject objeto = handler.gameObjects.get(x);
					//si encuentra numeros
				if(objeto instanceof Number){
				Number objeti = (Number) objeto;
				//de acuerda a su direccion , mueve al numero tantas unidades como se pasa 
				Mat2 mat2=game.getMatJuego();
				
				switch (dir){
				case UP:							//TODO ajustar threshold segunt cant de elementos asi evitar superosicion
					if(mat2.isRowOrColumnChanged(dir,objeti.getiY()))
						objeto.setSpeedY(-30);Animate=true;break;
				
				case DOWN:
					if(mat2.isRowOrColumnChanged(dir,objeti.getiY()))
					objeto.setSpeedY(30);Animate=true;break;
				
				case LEFT:
					if(mat2.isRowOrColumnChanged(dir,objeti.getiX()))
					objeto.setSpeedX(-30);Animate=true;break;
					
				case RIGHT:if(mat2.isRowOrColumnChanged(dir,objeti.getiX()))
					objeto.setSpeedX(30);Animate=true;break;
				}
				
				}
			}
			
			if(Animate)
				Game.setTickTimer();
		
			}
	     }
	}
		
	
	
	
	public Direction getDir() {
		return dir;
	}

	public void setDir(Direction dir) {
		this.dir = dir;
	}

	public void keyReleased(KeyEvent e){
		
		int key = e.getKeyCode();
		if(!Animate){
		
			if(key == KeyEvent.VK_UP){
				dir=Direction.UP;
				
			}
			else if(key == KeyEvent.VK_DOWN){
				dir=Direction.DOWN;
				
			}
			else if(key == KeyEvent.VK_LEFT){
				dir=Direction.LEFT;
				
			}
			else if(key == KeyEvent.VK_RIGHT){
				dir=Direction.RIGHT;
				
			}
			else if(key == KeyEvent.VK_ESCAPE){
				System.exit(0); ///TODO ABRIR MENU
				
			}
			
	}}
	
	
	
	
	
	
	
	
	
}
