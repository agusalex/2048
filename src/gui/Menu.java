package gui;

import paquete.Mat2;

import java.awt.*;

import static gui.Game.*;


class Menu {

    private static boolean showRankings=false;

    public static boolean isShowRankings() {
        return showRankings;
    }

    private int opcion;

    public void tick() {
        if (Game.optionSelect) { //Nuevo juegfo
            if (Game.menuOption == 0) {
                if (Game.menu) {
                    Game.matJuego = new Mat2(Game.matrixSize);
                    Game.menu = false;

                    Game.matJuego.initialize();
                    if(Game.debug)
                        Game.Log=Game.matJuego.toString();
                    setInitGame();
                    setGameInitialized();
                }

            } else if (Game.menuOption == 1) { //Opciones

                Game.printLog(Game.Log);

            } else if (Game.menuOption == 2) {

                showRankings = !showRankings;
                //TODO RECORDS
            } else if (Game.menuOption == 3) {

                System.exit(0);
            }
            Game.optionSelect = false;
            opcion = 0;
        }



        opcion = Game.MatrixY + Game.cellDistance * Game.menuOption;
    }


    public void render(Graphics g) {


        Font font;
        float size = 30F;
        font = Game.Fuente.deriveFont(size);
        int botonXS = (int) (Game.MatrixX + Game.cellDistance - Game.lineWidth / 2.3);
        int botonYS = (int) (opcion - Game.lineWidth / 2.3);
        int anchoS = (Game.cellSize * 2) + 2 * Game.lineWidth;
        int altoS = (int) (Game.cellSize + Game.lineWidth / 1.15);
        int curva = Game.cellAndNumberCurve;

        int botonX = Game.MatrixX + (Game.cellDistance);

        int ancho = (int) ((Game.cellSize * 2) + Game.lineWidth * 1.1);
        int alto = Game.cellSize;

        g.setColor(new Color(0x917A64));
        g.setFont(font);

        if (!Game.isGameInitialized()) {

            g.drawString("2048", (int) (Game.WIDTH / 2.3), Game.HEIGHT / 9);
            g.drawString("THE GAME!", (int) (Game.WIDTH / 2.5), Game.HEIGHT / 7);
        }
        //recuadro de seleccion
        if(!showRankings) {
            g.setColor(new Color(0x92360e));

            g.fillRoundRect(botonXS, botonYS, anchoS, altoS, curva, curva);

            g.setColor(new Color(0xFF5B3D));
            g.fillRoundRect(botonX, Game.MatrixY, ancho, alto, curva, curva);
            g.setColor(new Color(0xEEE4DA));
        }

        g.drawString("New Game", (int) (Game.WIDTH / 2.4), Game.HEIGHT / 4);
        //////


        g.setColor(new Color(0xEDC53F));
        g.fillRoundRect(botonX, Game.MatrixY + Game.cellDistance, ancho, alto, curva, curva);
        g.setColor(new Color(0x766B60));


        g.drawString("Options", (int) (Game.WIDTH / 2.3), (int) (Game.HEIGHT / 2.5));

        g.setColor(new Color(0xECE0C8));
        g.fillRoundRect(botonX, Game.MatrixY + Game.cellDistance * 2, ancho, alto, curva, curva);
        g.setColor(new Color(0x766B60));


        g.drawString("Rankings", (int) (Game.WIDTH / 2.37), (int) (Game.HEIGHT / 1.8));

        g.setColor(new Color(0xF2B179));
        g.fillRoundRect(botonX, Game.MatrixY + Game.cellDistance * 3, ancho, alto, curva, curva);
        g.setColor(new Color(0x766B60));


        g.drawString("Quit Game", (int) (Game.WIDTH / 2.412), (int) (Game.HEIGHT / 1.4));


    }


    public static void SetShowRankings(boolean b) {
        showRankings=b;
    }
}
