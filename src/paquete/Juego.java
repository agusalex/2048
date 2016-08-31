package paquete;




public class Juego {

	
//	Archivo archivo    este se deberia usar para guardar el dato de los juegadores y para recuperarlos
	
	private Jugador j1;
	private Mat2 matJuego;
	private int score = 0;

	
	
	public Juego(){      //TODO la idea es que se cree en la clase de la interfaz ? 
		

			this.j1=new Jugador("Default"); //nuevo jugador

			this.matJuego = new Mat2();
	}
	public Juego(int size){      
          
	
			this.j1=new Jugador("Default"); //nuevo jugador
	
		
		this.matJuego = new Mat2(size); //TODO manejar exception
	}

	public boolean gameOver(){
		return this.matJuego.gameOver();
	}
	
	public void increaseScore(int value){
		if(value<0){
			throw new IllegalArgumentException("Cannot increase negative value");
		}
		this.score += value;
	}
	
	public int getRecord(){
		//TODO BUSCAR EN LA BASE DE DATOS
		return this.score;
	}
	
	public String obtainStatus(){
		return this.j1.obtainStatus();
	}
	public void play(Direction choice){
		
		
		switch(choice){
		
		case UP: this.matJuego.Shift(Direction.UP);break;
		case DOWN: this.matJuego.Shift(Direction.DOWN);break;
		case LEFT: this.matJuego.Shift(Direction.LEFT);break;
		case RIGHT: this.matJuego.Shift(Direction.RIGHT);break;

		default: throw new IllegalArgumentException("Direction not allowed");
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
