package gui;

import java.awt.Graphics;
import java.util.LinkedList;

/*
 * este es el que maneja a los objetos que estan en pantalla (celdas y numeros)
 * esto va a ser que se llame a los metodos tick de la celda y de numero, y sus corresponfdientes metodos render
 */


public class Handler {

	LinkedList <Cell> gameCells = new LinkedList<Cell>(); 
	LinkedList <Number> gameNumbers = new LinkedList<Number>(); 
	LinkedList <Number> gameMenuNumbers = new LinkedList <Number>();
	
	
	
	public void render(Graphics g){


		

			for(int x = 0; x < gameCells.size(); x++){
				Cell cell = gameCells.get(x);
				cell.render(g);
			}
			if(!Game.isGameInitialized()) {
				for (int x = 0; x < gameMenuNumbers.size(); x++) {
					gameMenuNumbers.get(x).render(g);
				}
			}
			for(int x = 0; x < gameNumbers.size(); x++){
				Number number = gameNumbers.get(x);
				number.render(g);
			}

		
	}
	
	
	public void tick(){
		for(int x = 0; x < gameNumbers.size(); x++){
			Number number = gameNumbers.get(x);
			number.tick();
		}
	}
	
	public void addCell(Cell obj){
		this.gameCells.addLast(obj);
	}
	
	
	public void addNumber(Number obj){
		this.gameNumbers.addLast(obj);
	}
	
	public Number removeNumber(int index){
		return this.gameNumbers.remove(index);
	}
	
	public void addMenuNumber(Number obj){
		this.gameMenuNumbers.add(obj);
	}
	
	public void updateNumbers(){
		this.gameNumbers = new LinkedList <Number>();
	}
}
