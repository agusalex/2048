package gui;

import paquete.Direction;
import paquete.Mat2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



/*
 * clase que maneja las acciones del teclado, y tiene los metodos keyPressed y keyReleased que manjean opciones al apretar
 * y soltar botones , nos interesa solo la de soltar botones, ya que al soltar ahi se mueven los numeros
 */

class KeyInput extends KeyAdapter {
    private Direction dir;
    private final Handler handler;
    private final Game game;
    private boolean Animate = false;

	/*
	 * el constructor llama a un handler y a un jjuego, esto es para acceder a los numeros que quiero mover y
	 * al metodo de SHIFT que esta en la clase juego asi regula los movimientos al soltar las teclas
	 */

    public boolean isAnimate() {
        return Animate;
    }

    public void setAnimate() {
        Animate = false;
    }

    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
    }

    public void tick() {


        if (dir != null && (!Animate)) {
            for (int x = 0; x < handler.gameNumbers.size(); x++) {
                Number number = handler.gameNumbers.get(x);
                //de acuerda a su direccion , mueve al numero tantas unidades como se pasa
                Mat2 mat2 = game.getMatJuego();

                switch (dir) {
                    case UP:                            //TODO ajustar threshold segunt cant de elementos asi evitar superosicion
                        if (mat2.isRowOrColumnChanged(dir, number.getiY()))
                            number.setSpeedY(-30);
                        Animate = true;
                        break;

                    case DOWN:
                        if (mat2.isRowOrColumnChanged(dir, number.getiY()))
                            number.setSpeedY(30);
                        Animate = true;
                        break;

                    case LEFT:
                        if (mat2.isRowOrColumnChanged(dir, number.getiX()))
                            number.setSpeedX(-30);
                        Animate = true;
                        break;

                    case RIGHT:
                        if (mat2.isRowOrColumnChanged(dir, number.getiX()))
                            number.setSpeedX(30);
                        Animate = true;
                        break;
                }

            }

            if (Animate)
                Game.setTickTimer();

        }
    }


    public Direction getDir() {
        return dir;
    }

    public void setDir() {
        this.dir = null;
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();
        if (!Animate) {

            if (key == KeyEvent.VK_UP) {

                dir = Direction.UP;

            } else if (key == KeyEvent.VK_DOWN) {

                dir = Direction.DOWN;

            } else if (key == KeyEvent.VK_LEFT) {
                dir = Direction.LEFT;

            } else if (key == KeyEvent.VK_RIGHT) {
                dir = Direction.RIGHT;

            } else if (key == KeyEvent.VK_ESCAPE) {
                Game.menu = !Game.menu;

            } else if (key == KeyEvent.VK_ENTER && Game.isMenu()) {
                Game.optionSelect = true;
            } else if (key == KeyEvent.VK_DELETE) {
                System.exit(0);
            }


            if (Game.menu)
                dir = null;

            if (Game.debug)
                System.out.print(Game.menuOption);
        }


    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();


        if (key == KeyEvent.VK_UP) {
            if (Game.menu) {
                Game.menuOption--;
            }


        } else if (key == KeyEvent.VK_DOWN) {
            if (Game.menu) {
                Game.menuOption++;

            }


        } else if (key == KeyEvent.VK_DELETE) {
            System.exit(0);
        }

        if (Game.menuOption > 3)
            Game.menuOption = 0;

        else if (Game.menuOption < 0)
            Game.menuOption = 3;


        if (Game.menu)
            dir = null;

        if (Game.debug)
            System.out.print(Game.menuOption);


    }


}
