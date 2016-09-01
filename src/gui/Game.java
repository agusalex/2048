package gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

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
	private int score = 0;
	
	public static int getWITDH() {
		return WITDH;
	}

	public static int getHEIGHT() {
		return HEIGHT;
	}

	public static void setHEIGHT(int hEIGHT) {
		HEIGHT = hEIGHT;
	}


	private static int WITDH = 800, HEIGHT = WITDH /12*9;
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	
	
	public Game(){      //TODO la idea es que se cree en la clase de la interfaz ? 
		
		this.j1=new Jugador("Default"); //nuevo jugador
		
		this.matJuego = new Mat2();
		
		new Window(WITDH,HEIGHT, "Jueguito!", this);
		handler = new Handler();
	}
	
	public Game(int size){      
          
	
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
	
	
	
	
	public void run() {
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
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println(("FPS : "+frames));
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
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WITDH, HEIGHT);
		
     	handler.render(g);
		g.dispose();
		bs.show();
	}
	
	
	public synchronized void start(){
		thread = new Thread (this);
		thread.start();
		running = true;
	}
	
	
	public synchronized void stop(){
		try{
			thread.join();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
//	public void importarJugador(BufferedReader file)   esto se realiza si existe el jugador creado, usa el lector de Archivo
//  public void exportarJugador(BufferedWriter fil)    esto es para guardar los datos del jugador , usa el escritor de Archivo
	
	public static void main(String main []){
		Game game = new Game();
	}
	
	
}
