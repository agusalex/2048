package paquete;

public class Juego {

	private Jugador j1;
	private Mat2 matJuego;
	private int record;
	
	public Juego(Jugador j1){      //la idea es que se cree en la clase de la interfaz
		this.j1 = j1;
		this.matJuego = new Mat2();
	}
	
	public boolean gameOVer(){
		return this.matJuego.gameOver();
	}
	
	
	
	
}
