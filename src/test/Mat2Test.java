package test;

import org.junit.Test;
import paquete.Direction;
import paquete.Mat2;

import static org.junit.Assert.*;


public class Mat2Test {

    public static Mat2 ultima;

    private static int aleatorio() {
        int num = (int) (Math.random() * 4 - 2) + 2;
        return num % 2 == 0 ? num : num + 1;
    }


    public static Mat2 matTestRan() {
        Mat2 MET = new Mat2(6);
        MET.mat[0][0] = aleatorio();
        MET.mat[0][1] = aleatorio();
        MET.mat[0][4] = aleatorio();
        MET.mat[0][5] = aleatorio();
        MET.mat[1][3] = aleatorio();
        MET.mat[1][4] = aleatorio();
        MET.mat[2][3] = aleatorio();
        MET.mat[2][2] = aleatorio();
        MET.mat[2][4] = aleatorio();
        MET.mat[3][5] = aleatorio();
        MET.mat[4][0] = aleatorio();
        MET.mat[4][3] = aleatorio();
        MET.mat[4][5] = aleatorio();
        MET.mat[5][1] = aleatorio();
        MET.mat[5][4] = aleatorio();
        MET.setElements(15);
        return MET;
    }


    private static Mat2 matTest() {
        Mat2 MET = new Mat2(6);
        MET.mat[0][0] = 2;
        MET.mat[0][1] = 4;
        MET.mat[0][4] = 4;
        MET.mat[0][5] = 4;
        MET.mat[1][3] = 4;
        MET.mat[1][4] = 4;
        MET.mat[2][3] = 2;
        MET.mat[2][2] = 4;
        MET.mat[2][4] = 2;
        MET.mat[3][5] = 4;
        MET.mat[4][0] = 4;
        MET.mat[4][3] = 2;
        MET.mat[4][5] = 2;
        MET.mat[5][1] = 4;
        MET.mat[5][4] = 2;
        MET.setElements(15);
        return MET;
    }

    private static Mat2 matrizGameOver() {
        Mat2 MET = new Mat2(4);
        MET.mat[0][0] = 4;
        MET.mat[0][1] = 8;
        MET.mat[0][2] = 4;
        MET.mat[0][3] = 2;
        MET.mat[1][0] = 2;
        MET.mat[1][1] = 4;
        MET.mat[1][2] = 2;
        MET.mat[1][3] = 4;
        MET.mat[2][0] = 4;
        MET.mat[2][1] = 8;
        MET.mat[2][2] = 4;
        MET.mat[2][3] = 8;
        MET.mat[3][0] = 2;
        MET.mat[3][1] = 4;
        MET.mat[3][2] = 8;
        MET.mat[3][3] = 2;
        MET.setElements(16);           //aca le asigne la cantidad total de elemntos que tiene
        return MET;
    }

    // VA A DAR FALSO A MENOS QUE SE QUITE INITIALIZE() DEL CONSTRUCTOR
    @Test
    public void equalsTest() {
        Mat2 a = matTest();
        Mat2 b = matTest();
        assertTrue(a.equals(b));
    }

    public void agregarCelda(Mat2 esta, int x, int y, int carga) {
        esta.mat[x][y] = carga;

    }


    @Test
    public void agregarTest() {
        Mat2 a = new Mat2(4);
        assertEquals(2, a.getElements());
        a.addNewRandomCell();
        assertEquals(3, a.getElements());
    }

    @Test
    public void isFullTest() {
        Mat2 a = matrizGameOver();
        assertTrue(a.isFull());
    }

    //aca probe varios casos de mover incluso con la gameOver
    //notar que cada vez que se hace shidt se agrega un 2 de manera aleatoria
    @Test
    public void gameOverTest() {
        Mat2 a = matTest();

        System.out.println(a);     //TODO HARDCODEAR RESULTADOS ASI NOS ENTERAMOS SI CAMBIA ALGO
        a.Shift(Direction.UP);
        System.out.println("up\n" + a);
        a.Shift(Direction.DOWN);
        System.out.println("down\n" + a);  //TODO QUE SI PASO EL TEST DEVUELVA OK
        a.Shift(Direction.LEFT);
        System.out.println("left\n" + a);
        a.Shift(Direction.RIGHT);
        System.out.println("right\n" + a);  //TODO PESANR MAS TESTS?

        assertFalse(a.gameOver());

        Mat2 b = matrizGameOver();

        System.out.println(b);
        b.Shift(Direction.UP);
        System.out.println("up\n" + b);
        b.Shift(Direction.DOWN);
        System.out.println("down\n" + b);
        b.Shift(Direction.LEFT);
        System.out.println("left\n" + b);
        b.Shift(Direction.RIGHT);
        System.out.println("right\n" + b);

        assertTrue(b.gameOver());
        assertEquals(16, b.getElements());
    }


}
