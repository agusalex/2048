package paquete;

public class Juego {

	private Jugador j1;
	private Mat2 matJuego;
	private int record;
	boolean estaEnBasedeDatos=false; //BOrrar 
	Jugador jugenDB=new Jugador("Pepe el bug");
	
	public Juego(String j1){      //TODO la idea es que se cree en la clase de la interfaz ?
		
		if(estaEnBasedeDatos){//TODO REVISAR si el jugador no existe ya en base de datos
			this.j1 = jugenDB;//SI existe cargar su puntaje record 
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
	
	
	
	
}
