package gui;

import java.awt.*;
import java.util.LinkedList;

/*
 * este es el que maneja a los objetos que estan en pantalla (celdas y numeros)
 * esto va a ser que se llame a los metodos tick de la celda y de numero, y sus corresponfdientes metodos render
 */


class Handler {

    private final LinkedList<Cell> gameCells = new LinkedList<>();
    LinkedList<Number> gameNumbers = new LinkedList<>();
    private final LinkedList<Number> gameMenuNumbers = new LinkedList<>();


    public void render(Graphics g) {


        for (Cell cell : gameCells) {
            cell.render(g);
        }
        if (!Game.isGameInitialized()) {
            for (Number gameMenuNumber : gameMenuNumbers) {
                gameMenuNumber.render(g);
            }
        }
        for (Number number : gameNumbers) {
            number.render(g);
        }


    }


    public void tick() {
        gameNumbers.forEach(Number::tick);
    }

    public void addCell(Cell obj) {
        this.gameCells.addLast(obj);
    }


    public void addNumber(Number obj) {
        this.gameNumbers.addLast(obj);
    }

    public void addMenuNumber(Number obj) {
        this.gameMenuNumbers.add(obj);
    }

    public void updateNumbers() {
        this.gameNumbers = new LinkedList<>();
    }
}
