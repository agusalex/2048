package gui;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	LinkedList <GraphicObject> gameObjects = new LinkedList<GraphicObject>(); 
	
	
	public void tick(){
		for(int x = 0; x < gameObjects.size(); x++){
			GraphicObject objeto = gameObjects.get(x);
			objeto.tick();
		}
	}
	
	public void render(Graphics g){
		for(int x = 0; x < gameObjects.size(); x++){
			GraphicObject objeto = gameObjects.get(x);
			objeto.render(g);
		}
	}
}
