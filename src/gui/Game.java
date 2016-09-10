package gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JTextField;

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
	private  Mat2 matJuego;
	private Menu mainMenu;
	
	
	
	
	
/////////COLORES//////
static Font Fuente;
static Font FuenteScore;
static final Color FONDO=new Color(0xE9E9E0);
//static final Color FONDO=new Color(0xFBF8F1);
static final Color MARCO=new Color(0xB8AC9F);
static final Color CELDA=new Color(0xCDC1B5);
///////parametros///////////

	//final para que no sean modificables desde ningun lado
	static final int WIDTH = 1024, HEIGHT = WIDTH /12*9;
	static final boolean debug = true;
	//Ubicacion de la matriz en la ventana
	static final int MatrixX = WIDTH/4+WIDTH/60;
	static final int MatrixY = HEIGHT/6;
	static final long threshold = 3;
	
	//////////////////////////////
	public static int matrixSize = 4;
	static  int cellSize = WIDTH/10;
	static int cellDistance = cellSize + WIDTH/70;
	static int lineWidth=cellDistance-cellSize;
	static int cellAndNumberCurve =3;
	static int MatrixWIDTH = cellDistance*matrixSize -(Game.cellDistance - Game.cellSize);
	static int MatrixHEIGHT = MatrixWIDTH;
	//////////////////////////////
	
	
	private static boolean countTicks = false;
	private static long Tick = 0; //Cuando se activa ResetTickTimer empieza a contar ticks
	private int score = 0;

	
////////////////////////////
	private Thread thread;
	private boolean running = false;
	public static boolean menu = true;
	public static int menuOption = 0;
	public static boolean optionSelect = false;
	public static boolean rebootGame = false;

	//tiene un handler
    public  Handler handler; 
	private static KeyInput keylistener;
	
	
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
		
		
		Fuente = getCustomFont();
		
		mainMenu = new Menu();
		
		handler = new Handler();
		new Window(WIDTH,HEIGHT, "2048!", this);
		//llama a handler
		//agrega a esta clase la posibilidad de escuchar teclas
		//keyInput toma al handler para acceder a los numeros y a game para acceder a shift y poder 
		//mover los numeros en respuesta a las teclas presionadas
		keylistener= new KeyInput(handler,this);
		this.addKeyListener(keylistener);
	}
	
	
	
	public void matrixMenu(){
		handler.addMenuNumber(new Number(MatrixX,MatrixY, 0, 0, 2,this));
		handler.addMenuNumber(new Number(MatrixX,MatrixY + cellDistance, 0, 0, 0,this));
		handler.addMenuNumber(new Number(MatrixX,MatrixY + cellDistance*2 , 0 , 0 , 4,this));
		handler.addMenuNumber(new Number(MatrixX,MatrixY + cellDistance*3 , 0, 0, 8,this));
		handler.addMenuNumber(new Number(MatrixX + cellDistance*3,MatrixY, 0, 0, 2,this));
		handler.addMenuNumber(new Number(MatrixX + cellDistance*3,MatrixY + cellDistance, 0, 0, 0,this));
		handler.addMenuNumber(new Number(MatrixX + cellDistance*3,MatrixY + cellDistance*2, 0, 0, 4,this));
		handler.addMenuNumber(new Number(MatrixX + cellDistance*3,MatrixY + cellDistance*3, 0, 0, 8,this));
	}
	
	
	/*
	 * dibuja las celdas y numeros en pantalla esto se llama en run() ya que es el primer metodo en eejecutarse
	 * la idea es que se refresque siempre
	 */
	public void createMatrix(){
			
		Integer[][] mat = this.matJuego.getMat();
		
		int Distance = getCellDistance();

		//lo que habias hecho antes, solo que tiene otro nombre

		
		int xaux = MatrixX;
		int yaux = MatrixY;		
		
		Integer matrixPositionNumber = null;
		Number auxNum;

	
			for(int i = 0; i < mat.length; i++){
				
				for( int j = 0; j < mat.length; j++){
				  
					
					matrixPositionNumber = mat[i][j];
					
					Cell auxCell = new Cell(xaux,yaux,this);
		
			    	handler.addCell(auxCell);
			    	
					auxNum = new Number(xaux,yaux,i,j,matrixPositionNumber,this);
			    
		        	handler.addNumber(auxNum);
				
			    	xaux += Distance;
				
				}
			
				yaux+=Distance;
				xaux=MatrixX;
			
		}	
		
	}

	/*
	 * esta funcion elimina los objetos actuales asi no se dibujan de nuevo y llama a dibujar matriz que dibuja a la matriz
	 * de nuevo para que tenga los valores de la matriz actualizada 
	 */
	
	public void updateMatrix(){
		
		handler.updateNumbers();
        
		
		Integer[][] mat = this.matJuego.getMat();
		
		int Distance = getCellDistance();

		//lo que habias hecho antes, solo que tiene otro nombre

		
		int xaux = MatrixX;
		int yaux = MatrixY;		
		
		Integer matrixPositionNumber = null;
		Number auxNum;
		
		
	    for(int i = 0; i < mat.length; i++){
			for( int j = 0; j < mat.length; j++){
				matrixPositionNumber = mat[i][j];
				
				if(matrixPositionNumber!=null){
				auxNum = new Number(xaux,yaux,i,j,matrixPositionNumber,this);
			    
		        handler.addNumber(auxNum);
				}
			    xaux += Distance;
				
			}
			
			yaux+=Distance;
			xaux=MatrixX;
		}
		
	}
	
	public void run() {		//CICLO DEL JUEGO
		//dDIBUJA A LA MATRIZ
		this.createMatrix();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
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
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;	
			}
		}
		stop();
	}
	
	private void tick(){
		if(menu){
			   mainMenu.tick();
		 }
		
		else{
			
			if(countTicks && debug){
				System.out.println(Tick);
			}
				if (Tick >= Long.MAX_VALUE-1)
					Tick=0;
				
				Tick++;
			
				keylistener.tick();
			
				if(keylistener.isAnimate()){
					if(Game.getTickTimer()>threshold){
						//mueve y combina llamando a play de juego que llama a SHIFT
						keylistener.setAnimate(false);
				
						play(keylistener.getDir());
				
						keylistener.setDir(null);
						//para verificar igualdad
						if(debug)
							System.out.println(getMatJuego());
						//actualizo a las celdas con numeros arriba de ellas
						updateMatrix();
						StopTickTimer();
				
						//aca se actualiza la puntuacion al combinar
						this.increaseScore(this.matJuego.getScore());
					}
				}
		   handler.tick();
		  
		   //si se termina el juego se vuelve al menu principal
		   if(gameOver()){
			   menu = true;
			   this.matJuego = new Mat2();
			   updateMatrix();
		   }
		   
		}
		
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy(); 
		if(bs == null){ 					//inicializa en null
			this.createBufferStrategy(3);
			return;
		}
		
		
		Graphics g = bs.getDrawGraphics();    //crea un link para los graficos y el buffer
		
		
		g.setColor(FONDO);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		if(menu ){
			matrixMenu();  
			drawMatrixBorders(g);
			handler.render(g);
			drawMatrixLines(g);
			mainMenu.render(g);
			
		}
		else if (!menu){
			drawMatrixBorders(g); 
			handler.render(g);
			drawMatrixLines(g);
			drawScores(g);
		}
		
		g.dispose();    
		bs.show(); 					//para que muestre el buffer renderizado
	}
	
	
	public boolean isMenu() {
		return menu;
	}




	public void setMenu(boolean menu) {
		Game.menu = menu;
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
	
	
	public static void drawMatrixBorders(Graphics g){
		g.setColor(MARCO);
		int POLO=(int) (cellDistance-(cellSize));
		g.fillRoundRect(Game.MatrixX-POLO, Game.MatrixY-POLO, Game.getMatrixBounds().height+POLO*2,Game.getMatrixBounds().height+POLO*2,cellAndNumberCurve+5,cellAndNumberCurve+5);

	}
	
	public void drawMatrixLines(Graphics g){
		int lineWidth=(cellDistance-cellSize);
		Integer [][] mat=matJuego.getMat();
		g.setColor(MARCO);	
		
		for( int j = 0; j <= mat.length; j++){
					
			g.fillRect(MatrixX+(cellDistance*j)-lineWidth,MatrixY,cellDistance-cellSize,Game.MatrixWIDTH);

			}
		for( int j = 0; j <= mat.length; j++){
					
		   g.fillRect(MatrixX,MatrixY+(cellDistance*j)-lineWidth,Game.MatrixWIDTH,cellDistance-cellSize);
				    
			}

		
		}
		


	public static Rectangle getMatrixBounds(){
		int size=Game.MatrixWIDTH;
		Rectangle rect=new Rectangle(MatrixX,MatrixY,size,size);
		
		return rect;
	}
   
   
	public void drawScores(Graphics g){
		FuenteScore = new Font(Font.SANS_SERIF,1,30);
		g.setFont(FuenteScore);
		
		g.setColor(MARCO);
		g.fillRoundRect(WIDTH/2, HEIGHT/20, (int)(cellSize*1.1), (int)(cellSize/1.5), cellAndNumberCurve, cellAndNumberCurve);
		g.setColor(FONDO);
		g.drawString("Score", WIDTH/2, HEIGHT/12);
		g.drawString(Integer.toString(getRecord()),(int) (WIDTH/1.9), HEIGHT/8);
		
		g.setColor(MARCO);
		g.fillRoundRect((int)(WIDTH/1.5), HEIGHT/20, (int)(cellSize*1.1), (int)(cellSize/1.5), cellAndNumberCurve, cellAndNumberCurve);
		g.setColor(FONDO);
		g.drawString("Best", (int) (WIDTH/1.5), HEIGHT/12);
		g.drawString(Integer.toString(this.j1.getRecord()),(int) (WIDTH/1.5), HEIGHT/8);
		
	}
	
	
	
	
	
	
	public boolean gameOver(){
		return this.matJuego.gameOver();
	}
	
	public static void setTickTimer(){
		Tick=0;
		countTicks=true;
	}
	public static void StopTickTimer(){
		Tick=0;
		countTicks=false;
	}
	
	public static long getTickTimer(){
		return Tick;
	}
	
	public static Font getCustomFont(){
		Font customFont=null;
		try {
		    //create the font to use. Specify the size!
			java.io.InputStream is = Game.class.getResourceAsStream("ClearSans-Bold.ttf");
			customFont = Font.createFont(Font.TRUETYPE_FONT,is);
		    
		  
		    
		   
		} catch (IOException e) {
		    e.printStackTrace();
		} catch(FontFormatException e) {
		    e.printStackTrace();
		}

		//use the font
		return customFont;
	}
	
	
	
	public void increaseScore(int value){
		if(value < 0){
			throw new IllegalArgumentException("Cannot increase negative value");
		}
		this.score = value;
	}
	
	public int getRecord(){
		//TODO BUSCAR EN LA BASE DE DATOS
		return this.score;
	}
	
	public String obtainStatus(){
		return this.j1.toString();
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

	
	public int getCellSize() {
		return cellSize;
	}

	public Jugador getJugador(){
		return this.j1;
	}
	
	public Mat2 getMatJuego(){
		return this.matJuego;
	}
	
	public int getMatrixHEIGHT() {
		return MatrixHEIGHT;
	}




	public int getMatrixWIDTH() {
		return MatrixWIDTH;
	}


	
//	public void importarJugador(BufferedReader file)   esto se realiza si existe el jugador creado, usa el lector de Archivo
//  public void exportarJugador(BufferedWriter fil)    esto es para guardar los datos del jugador , usa el escritor de Archivo
	
	@SuppressWarnings("unused")
	public static void main(String main []){
		Game game = new Game();
	}
	
	
}
