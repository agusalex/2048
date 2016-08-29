package paquete;

import java.util.Scanner;

import paquete.Mat2.direction;

public class Juego {

	
//	Archivo archivo    este se deberia usar para guardar el dato de los juegadores y para recuperarlos
	
	private Jugador j1;
	private Mat2 matJuego;
	private int score = 0;
	boolean estaEnBasedeDatos = false; //BOrrar 
	Jugador jugenDB = new Jugador("Pepe el bug");
	
	
	public Juego(String j1){      //TODO la idea es que se cree en la clase de la interfaz ? 
		
		if(estaEnBasedeDatos){//TODO REVISAR si el jugador no existe ya en base de datos
			this.j1 = jugenDB;//SI existe cargar su puntaje record
			this.matJuego = new Mat2();
		}                   
		else{
			this.j1=new Jugador(j1); //nuevo jugador
		}
			this.matJuego = new Mat2();
	}
	public Juego(String j1, int size){      
		if(estaEnBasedeDatos){//TODO REVISAR si el jugador no existe ya en base de datos
			this.j1 = jugenDB;//SI existe cargar su puntaje record 
		}                   
		else{
			this.j1=new Jugador(j1); //nuevo jugador
		}
		
		this.matJuego = new Mat2(size); //TODO manejar exception
	}

	public boolean gameOVer(){
		return this.matJuego.gameOver();
	}
	
	public void increaseScore(int value){
		this.score += value;
	}
	
	public int getRecord(){
		return this.score;
	}
	
	public void play(char choice){
		switch(choice){
		case 'W': this.matJuego.Shift(direction.UP);break;
		case 'w': this.matJuego.Shift(direction.UP);break;
		case 'S': this.matJuego.Shift(direction.DOWN);break;
		case 's': this.matJuego.Shift(direction.DOWN);break;
		case 'A': this.matJuego.Shift(direction.LEFT);break;
		case 'a': this.matJuego.Shift(direction.LEFT);break;
		case 'D': this.matJuego.Shift(direction.RIGHT);break;
		case 'd': this.matJuego.Shift(direction.RIGHT);break;
		default: return;
		}
	}
	
	public Jugador getJugador(){
		return this.j1;
	}
	
	public Mat2 getMatJuego(){
		return this.matJuego;
	}
	
	
	
	
	
//	public void importarJugador(BufferedReader file)   esto se realiza si existe el jugador creado, usa el lector de Archivo
//  public void exportarJugador(BufferedWriter fil)    esto es para guardar los datos del jugador , usa el escritor de Archivo
	
	
	
	
}
