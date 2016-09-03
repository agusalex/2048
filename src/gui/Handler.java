package gui;

import java.awt.Graphics;
import java.util.LinkedList;

/*
 * este es el que maneja a los objetos que estan en pantalla (celdas y numeros)
 * esto va a ser que se llame a los metodos tick de la celda y de numero, y sus corresponfdientes metodos render
 */


public class Handler {

	LinkedList <GraphicObject> gameObjects = new LinkedList<GraphicObject>(); 
	
	public void render(Graphics g){
		for(int x = 0; x < gameObjects.size(); x++){
			GraphicObject objeto = gameObjects.get(x);
			objeto.render(g);
		}
	}
	
	
	public void tick(){
		for(int x = 0; x < gameObjects.size(); x++){
			GraphicObject objeto = gameObjects.get(x);
			objeto.tick();
		}
	}
	
	public void addObject(GraphicObject obj){
		this.gameObjects.add(obj);
	}
}
