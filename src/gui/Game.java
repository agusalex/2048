package gui;

import paquete.Direction;
import paquete.Jugador;
import paquete.Mat2;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;


public class Game extends Canvas implements Runnable {


//	Archivo archivo    este se deberia usar para guardar el dato de los juegadores y para recuperarlos

    /**
     *
     */
    private static final long serialVersionUID = 2381537138204047441L;

    private final Jugador Player;
    public static Mat2 matJuego;
    private Menu mainMenu;


    /////////COLORES//////
    static Font Fuente;
    private static final Color FONDO = new Color(0xFFFCED);
    //static final Color FONDO=new Color(0xFBF8F1);
    static final Color MARCO = new Color(0xB8AC9F);
    static final Color CELDA = new Color(0xCDC1B5);
///////parametros///////////

    //final para que no sean modificables desde ningun lado
    static final int WIDTH = 1024, HEIGHT = WIDTH / 12 * 9;
    static final boolean debug = true;
    //Ubicacion de la matriz en la ventana
    static final int MatrixX = WIDTH / 4 + WIDTH / 60;
    static final int MatrixY = HEIGHT / 6;





    private static final long threshold = 3;
    static String Log ="";
    //////////////////////////////
    public static int matrixSize = 4;
    static final int cellSize = WIDTH / 10;
    static final int cellDistance = cellSize + WIDTH / 70;
    static final int lineWidth = cellDistance - cellSize;
    static final int cellAndNumberCurve = 3;
    static final int MatrixWIDTH = cellDistance * matrixSize - (Game.cellDistance - Game.cellSize);
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
    private static boolean initGame = false;


    //tiene un handler
    private Handler handler;
    private static KeyInput keylistener;
    public static JButton inputButton = new JButton("Send");
    public static JTextArea editTextArea = new JTextArea("Type Here!");
    public static JTextArea uneditTextArea = new JTextArea();


        //SET LAYOUT MANAGER (How it arranges components)



    public Game() {      //TODO la idea es que se cree en la clase de la interfaz ?

        this.Player = new Jugador("Default"); //nuevo jugador
        if (matrixSize < 2) {
            throw new IllegalArgumentException("invalid size:" + matrixSize);
        }
        if (matrixSize > 10) {
            throw new IllegalArgumentException("invalid size:" + matrixSize);
        }
        this.requestFocus();
        matJuego = new Mat2(matrixSize);
        System.out.println(matJuego);


        Fuente = getCustomFont();

        mainMenu = new Menu();

        handler = new Handler();
        Window win=new Window(this);


        //llama a handler
        //agrega a esta clase la posibilidad de escuchar teclas
        //keyInput toma al handler para acceder a los numeros y a game para acceder a shift y poder
        //mover los numeros en respuesta a las teclas presionadas
        keylistener = new KeyInput(handler, this);
        this.addKeyListener(keylistener);



    }


    public void play(Direction choice) {

        switch (choice) {

            case UP:
                matJuego.Shift(Direction.UP);
                break;
            case DOWN:
                matJuego.Shift(Direction.DOWN);
                break;
            case LEFT:
                matJuego.Shift(Direction.LEFT);
                break;
            case RIGHT:
                matJuego.Shift(Direction.RIGHT);
                break;

            default:
                throw new IllegalArgumentException("Direction not allowed");
        }
        Log=Log+choice.toString()+"h";
    }




    /*
     * dibuja las celdas y numeros en pantalla esto se llama en run() ya que es el primer metodo en eejecutarse
     * la idea es que se refresque siempre
     */

    private static Font getCustomFont() {
        Font customFont = null;
        try {
            //create the font to use. Specify the size!
            java.io.InputStream is = Game.class.getResourceAsStream("ClearSans-Bold.ttf");
            customFont = Font.createFont(Font.TRUETYPE_FONT, is);


        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        //use the font
        return customFont;
    }

	/*
     * esta funcion elimina los objetos actuales asi no se dibujan de nuevo y llama a dibujar matriz que dibuja a la matriz
	 * de nuevo para que tenga los valores de la matriz actualizada 
	 */

    private void createMatrix() {

        Integer[][] mat = matJuego.getMat();


        //lo que habias hecho antes, solo que tiene otro nombre


        int xaux = MatrixX;
        int yaux = MatrixY;

        Integer matrixPositionNumber;
        Number auxNum;


        for (int i = 0; i < mat.length; i++) {

            for (int j = 0; j < mat.length; j++) {


                matrixPositionNumber = mat[i][j];

                Cell auxCell = new Cell(xaux, yaux, this);

                handler.addCell(auxCell);

                auxNum = new Number(xaux, yaux, i, j, matrixPositionNumber, this);

                handler.addNumber(auxNum);

                xaux += cellDistance;

            }

            yaux += cellDistance;
            xaux = MatrixX;

        }

    }

    public void run() {        //CICLO DEL JUEGO


        //dDIBUJA A LA MATRIZ
        this.createMatrix();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }

            if (running)
                render();
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
            }
        }
        stop();
    }
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {                    //inicializa en null
            this.createBufferStrategy(3);
            return;
        }


        Graphics g = bs.getDrawGraphics();    //crea un link para los graficos y el buffer


        g.setColor(FONDO);
        g.fillRect(0, 0, WIDTH, HEIGHT);


        if (menu) {
            matrixMenu();
            drawMatrixBorders(g);
            handler.render(g);
            drawMatrixLines(g);
            mainMenu.render(g);
            if(mainMenu.isShowRankings()) {
                drawRanking(g);
            }
            if (isGameInitialized()) drawScores(g);
        } else {
            drawMatrixBorders(g);
            handler.render(g);
            drawMatrixLines(g);
            drawScores(g);
        }

        g.dispose();
        bs.show();                    //para que muestre el buffer renderizado
    }

    private void tick() {
        if (menu) {
            mainMenu.tick();
        } else {


            if (Tick >= Long.MAX_VALUE - 1)
                Tick = 0;

            Tick++;

            keylistener.tick();

            if (initGame) {
                this.updateMatrix();
                initGame = false;
            }
            if (keylistener.isAnimate()) {
                if (Game.getTickTimer() > threshold) {
                    //mueve y combina llamando a play de juego que llama a SHIFT
                    keylistener.setAnimate();

                    play(keylistener.getDir());

                    keylistener.setDir();
                    //para verificar igualdad
                    if (debug)
                        System.out.println(getMatJuego());
                        Log = Log +getMatJuego();
                        if (Log.length()>3000){
                            Log=Log.substring(Log.length()-3000,Log.length());
                        }
                    //actualizo a las celdas con numeros arriba de ellas
                    updateMatrix();
                    StopTickTimer();

                    //aca se actualiza la puntuacion al combinar
                    this.increaseScore(matJuego.getScore());
                }
            }
            handler.tick();

            //si se termina el juego se vuelve al menu principal
            if (gameOver()) {
                menu = true;
                matJuego = new Mat2();
                updateMatrix();
            }

        }

    }

    private void updateMatrix() {

        handler.updateNumbers();


        Integer[][] mat = matJuego.getMat();


        //lo que habias hecho antes, solo que tiene otro nombre


        int xaux = MatrixX;
        int yaux = MatrixY;

        Integer matrixPositionNumber;
        Number auxNum;


        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                matrixPositionNumber = mat[i][j];

                if (matrixPositionNumber != null) {
                    auxNum = new Number(xaux, yaux, i, j, matrixPositionNumber, this);

                    handler.addNumber(auxNum);
                }
                xaux += cellDistance;

            }

            yaux += cellDistance;
            xaux = MatrixX;
        }

    }


    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
        requestFocusInWindow();
    }


    private synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void drawMatrixBorders(Graphics g) {
        g.setColor(MARCO);
        int POLO = cellDistance - (cellSize);
        g.fillRoundRect(Game.MatrixX - POLO, Game.MatrixY - POLO, Game.getMatrixBounds().height + POLO * 2, Game.getMatrixBounds().height + POLO * 2, cellAndNumberCurve + 5, cellAndNumberCurve + 5);

    }

    private void drawMatrixLines(Graphics g) {
        int lineWidth = (cellDistance - cellSize);
        Integer[][] mat = matJuego.getMat();
        g.setColor(MARCO);

        for (int j = 0; j <= mat.length; j++) {

            g.fillRect(MatrixX + (cellDistance * j) - lineWidth, MatrixY, cellDistance - cellSize, Game.MatrixWIDTH);

        }
        for (int j = 0; j <= mat.length; j++) {

            g.fillRect(MatrixX, MatrixY + (cellDistance * j) - lineWidth, Game.MatrixWIDTH, cellDistance - cellSize);

        }


    }


    private static Rectangle getMatrixBounds() {
        int size = Game.MatrixWIDTH;

        return new Rectangle(MatrixX, MatrixY, size, size);
    }

    public  void drawRanking(Graphics g) {
        g.setColor(MARCO);
        g.fillRect(MatrixX + (cellDistance *2) - lineWidth, MatrixY, cellDistance - cellSize, Game.MatrixWIDTH);
        int botonX = MatrixX;
        int ancho = cellDistance *2- lineWidth;
        int alto = Game.cellSize;
        int cont=1;
        for(int i=0;i<matrixSize/2;i++)
            for (int j = 0; j < matrixSize; j++) {
                g.setColor(CELDA);
                g.fillRoundRect(botonX+Game.cellDistance *i*2, Game.MatrixY + Game.cellDistance * j, ancho, alto, Game.cellAndNumberCurve, Game.cellAndNumberCurve);
                g.setColor(FONDO);
                g.drawString(Player+": "+Integer.toString(getScore()), botonX+lineWidth+Game.cellDistance *i*2, Game.MatrixY + cellSize/2+(Game.cellDistance )* j);
                cont++;
            }



    }



    private void drawScores(Graphics g) {

        Font newFont = Game.Fuente.deriveFont(28F);
        g.setFont(newFont);

        g.setColor(new Color(0xBEAC9E));
        g.fillRoundRect(WIDTH / 3, HEIGHT / 20, cellSize, (int) (cellSize / 1.5), cellAndNumberCurve * 2, cellAndNumberCurve * 2);
        g.setColor(new Color(0xE8DACD));
        g.drawString("Score", (int) (WIDTH / 2.9), HEIGHT / 12);
        g.setColor(Game.FONDO);
        g.drawString(Integer.toString(getScore()), (int) (WIDTH / 2.75), HEIGHT / 8);

        g.setColor(new Color(0xBEAC9E));
        g.fillRoundRect((int) (WIDTH / 1.8), HEIGHT / 20, (cellSize), (int) (cellSize / 1.5), cellAndNumberCurve * 2, cellAndNumberCurve * 2);
        g.setColor(new Color(0xE8DACD));
        g.drawString("Best", (int) (WIDTH / 1.73), HEIGHT / 12);
        g.setColor(Game.FONDO);
        g.drawString(Integer.toString(Player.getRecord()), (int) (WIDTH / 1.69), HEIGHT / 8);

    }


    public static boolean isMenu() {
        return menu;
    }


    private void matrixMenu() {
        handler.addMenuNumber(new Number(MatrixX, MatrixY, 0, 0, 2, this));
        handler.addMenuNumber(new Number(MatrixX, MatrixY + cellDistance, 0, 0, 0, this));
        handler.addMenuNumber(new Number(MatrixX, MatrixY + cellDistance * 2, 0, 0, 4, this));
        handler.addMenuNumber(new Number(MatrixX, MatrixY + cellDistance * 3, 0, 0, 8, this));
        handler.addMenuNumber(new Number(MatrixX + cellDistance * 3, MatrixY, 0, 0, 2, this));
        handler.addMenuNumber(new Number(MatrixX + cellDistance * 3, MatrixY + cellDistance, 0, 0, 0, this));
        handler.addMenuNumber(new Number(MatrixX + cellDistance * 3, MatrixY + cellDistance * 2, 0, 0, 4, this));
        handler.addMenuNumber(new Number(MatrixX + cellDistance * 3, MatrixY + cellDistance * 3, 0, 0, 8, this));
    }


    private void drawGameover(Graphics g){
      //TODO CARTEL Y INPUT POR SI EL USER ROMPIO RECORDS


    }
    public boolean gameOver() {
        return matJuego.gameOver();
    }

    public static void setTickTimer() {
        Tick = 0;
        countTicks = true;
    }

    private static void StopTickTimer() {
        Tick = 0;
        countTicks = false;
    }

    private static long getTickTimer() {
        return Tick;
    }



    public static void printLog(String Log) {
        try {
            PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
            System.setOut(out);

            int cont=0;
            int cont2=0;
            for (int j = 0; j < Log.length(); j++) {
                if(Log.charAt(j) != '\n'&&Log.charAt(j) != 'h') {
                    System.out.print(Log.charAt(j));

                if(Log.charAt(j) == '|'){
                    cont++;
                }
                if (cont==4) {
                    cont2++;
                    cont=0;
                    System.out.println();

                    if (cont2 == 4) {
                        cont = 0;
                        cont2=0;
                        System.out.println();



                    }

                }
                }

                if(Log.charAt(j) == 'h'){
                    System.out.println();
                    System.out.println();
                }


            }


           out.close();
        } catch (IOException ignored) {

        }


    }


    public void increaseScore(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Cannot increase negative value");
        }
        this.score = value;
    }

    public int getScore() {
        return score;
    }




    public int getCellSize() {
        return cellSize;
    }

    public Jugador getJugador() {
        return Player;
    }

    public Mat2 getMatJuego() {
        return matJuego;
    }

    public static boolean isGameInitialized() {
        return GameInitialized;
    }

    public static void setGameInitialized() {
        GameInitialized = true;
    }

    private static boolean GameInitialized = false;

    public static void setInitGame() {
        Game.initGame = true;
    }


//	public void importarJugador(BufferedReader file)   esto se realiza si existe el jugador creado, usa el lector de Archivo
//  public void exportarJugador(BufferedWriter fil)    esto es para guardar los datos del jugador , usa el escritor de Archivo

    @SuppressWarnings("unused")
    public static void main(String main[]) {
        Game game = new Game();
    }



}
