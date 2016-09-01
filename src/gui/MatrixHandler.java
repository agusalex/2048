package gui;

import java.awt.Graphics;
import java.util.LinkedList;



public class MatrixHandler extends GraphicObject{

	LinkedList <GraphicObject> gameObjects = new LinkedList<GraphicObject>(); 
	
	
	public MatrixHandler(int x, int y, Game game) {
		super(x, y, game);
	
		Integer[][] mat=game.getMatJuego().mat;
		
		int Distance=game.getCellDistance();
		int xaux=x=x;
		int yaux=y;		
		
		 gameObjects.add(this);
		
		for(int i = 0; i < mat.length; i++){
			for( int j = 0; j < mat.length; j++){
				
				Cell auxCell=new Cell(xaux,yaux,game);
		
				
				 gameObjects.add(auxCell);
				
				
				xaux+=Distance;
				
			}
			yaux+=Distance;
			xaux=x;
		}
		
		
		for(int i = 0; i < mat.length; i++){
			for( int j = 0; j < mat.length; j++){
				
				Number auxNum=new Number(xaux,yaux,mat[i][j],game);
				
			
				
			
				
				 gameObjects.add(auxNum);
				
				xaux+=Distance;
				
				
			}
			yaux+=Distance;
			xaux=x;
		}
		
		
		
		
		
	}


	
	
	
	
	
	
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
