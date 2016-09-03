package gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import paquete.Direction;
import paquete.Jugador;
import paquete.Mat2;


public class Game extends Canvas implements Runnable{

	
//	Archivo archivo    este se deberia usar para guardar el dato de los juegadores y para recuperarlos
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2381537138204047441L;
	
	private Jugador j1;
	private Mat2 matJuego;

	
///////parametros///////////

	//final para que no sean modificables desde ningun lado
	static final int WIDTH = 1024, HEIGHT = WIDTH /12*9;
	
	private int cellSize = WIDTH/10;
	private int cellDistance = cellSize+WIDTH/50;
	private int matrixSize = 4;
	private int score = 0;

	
////////////////////////////
	private Thread thread;
	private boolean running = false;
	
	//tiene un handler
	private Handler handler; 
	
	
	
	public Game(){      //TODO la idea es que se cree en la clase de la interfaz ? 
		
		this.j1=new Jugador("Default"); //nuevo jugador
		if(matrixSize < 2){
			throw new IllegalArgumentException("invalid size:"+matrixSize);
		}
		if(matrixSize > 10){
			throw new IllegalArgumentException("invalid size:"+matrixSize);
		}
		
		this.matJuego = new Mat2(matrixSize);
		System.out.println(this.matJuego);
		

		new Window(WIDTH,HEIGHT, "2048!", this);
		
		//llama a handler
		handler = new Handler();
		
		//agrega a esta clase la posibilidad de escuchar teclas
		//keyInput toma al handler para acceder a los numeros y a game para acceder a shift y poder 
		//mover los numeros en respuesta a las teclas presionadas
		this.addKeyListener(new KeyInput(handler,this));
	}
	
	
	
	
	/*
	 * dibuja las celdas y numeros en pantalla esto se llama en run() ya que es el primer metodo en eejecutarse
	 * la idea es que se refresque siempre
	 */
	public void drawMatrix(){
		Integer[][] mat = this.matJuego.getMat();
		
		int Distance = getCellDistance();

		//lo que habias hecho antes, solo que tiene otro nombre
		int x = WIDTH/4+WIDTH/60;
		int y = HEIGHT/6;
		
		int xaux = x;
		int yaux = y;		
		
		Integer matrixPositionNumber = null;
		Number auxNum;
		
	    for(int i = 0; i < mat.length; i++){
			for( int j = 0; j < mat.length; j++){
				matrixPositionNumber = mat[i][j];
				
				Cell auxCell = new Cell(xaux,yaux,this);
		
			    handler.gameObjects.add(auxCell);
				
				auxNum = new Number(xaux,yaux,matrixPositionNumber,this);
			    
		        handler.gameObjects.add(auxNum);
				
			    xaux += Distance;
				
			}
			
			yaux+=Distance;
			xaux=x;
		}
	}
	
	/*
	 * esta funcion elimina los objetos actuales asi no se dibujan de nuevo y llama a dibujar matriz que dibuja a la matriz
	 * de nuevo para que tenga los valores de la matriz actualizada 
	 * TODO dejame que se me ocurre algo para hacer con esto 
	 */
	public void updateMatrix(){
		for(int x = 0; x < handler.gameObjects.size(); x++){
			handler.gameObjects.remove(x);
		}
		this.drawMatrix();
		
	}
	
	

	
	
	
	public void run() {		//CICLO DEL JUEGO
/*
		boolean over = false;
		Direction opcion ;
		Direction[] opciones={Direction.UP,Direction.DOWN,Direction.LEFT,Direction.RIGHT};
		Random r=new Random();
		int indice;*/
	
		//dDIBUJA A LA MATRIZ
		this.drawMatrix();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			
			/*
			indice=r.nextInt(opciones.length);
			opcion =opciones[indice];
			
			System.out.println(opcion);
			System.out.println(getMatJuego());
			play(opcion);
			
			if(gameOver()){
				over = true;
			}*/
			
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				frames = 0;	
			}
		}
		stop();
	}
	
	private void tick(){
		handler.tick();
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy(); 
		if(bs == null){ 					//inicializa en null
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();    //crea un link para los graficos y el buffer
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		handler.render(g);
		
		g.dispose();    
		bs.show(); 					//para que muestre el buffer renderizado
	}
	
	
	public synchronized void start(){
		thread = new Thread (this);
		thread.start();
		running = true;
	}
	
	
	public synchronized void stop(){
		try{
			thread.join();
			running = false;
		}catch (Exception e){
			e.printStackTrace();
		}
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
	
	
	
	public boolean gameOver(){
		return this.matJuego.gameOver();
	}
	
	public void increaseScore(int value){
		if(value < 0){
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
	

	public static int getWIDTH() {
		return WIDTH;
	}

	public static int getHEIGHT() {
		return HEIGHT;
	}

	
	
	
	public int getCellDistance() {
		return cellDistance;
	}

	public void setCellDistance(int cellDistance) {
		this.cellDistance = cellDistance;
	}

	
	public int getCellSize() {
		return cellSize;
	}

	public Jugador getJugador(){
		return this.j1;
	}
	
	public Mat2 getMatJuego(){
		return this.matJuego;
	}
	
	
	
//	public void importarJugador(BufferedReader file)   esto se realiza si existe el jugador creado, usa el lector de Archivo
//  public void exportarJugador(BufferedWriter fil)    esto es para guardar los datos del jugador , usa el escritor de Archivo
	
	public static void main(String main []){
		Game game = new Game();
	}
	
	
}
