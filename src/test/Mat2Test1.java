package test;

import static org.junit.Assert.*;

import org.junit.Test;

import paquete.Direction;
import paquete.Mat2;


/*
 * NUEVA CLASE MATTEST REVISADO CADA METODO, TODOS FUNCIONAN PERFECTAMENTE, EN CASO DE QUERER REVISAR, ASEGURARSE DE LA FUNCION INITIALIZE DE LOS CONSTRUCTORES 
 * RECORDAR QUE AL CREARSE UNA MATRIZ SE LE ASIGNAN DOS 2 EN POSICIONES ALEATORIAS, POR LO QUE YA TIENEN 2 ELEENTOS POR DEFAULT
 * A TENER ESO EN CUENTA  A LA HORA DE TESTEAR YA QUE PUEDE QUE LOS RESULTADOS NO SEAN LOS ESPERADOS, HAGO MUCHO HINCAPIE EN ESO PORQUE 
 * DEBIDO A ESE FACTOR NO TENIDO EN CUENTA SE GENERAA CONFUSION AL REALIZAR LOS TEST, PERO NO SACARLO 
 */




public class Mat2Test1 {

	public static int aleatorio(){
		int num = (int) (Math.random()*4-2) +2;
		return  num % 2 == 0 ? num : num+1;
	}
	
	
	public static Mat2 matTest(){
		Mat2 MET = new Mat2(6);
		MET.addNewRandomCell();
		MET.addNewRandomCell();
		MET.addNewRandomCell();
		MET.addNewRandomCell();
		MET.addNewRandomCell();
		MET.addNewRandomCell();
		MET.addNewRandomCell();
		MET.addNewRandomCell();
		MET.addNewRandomCell();
		MET.addNewRandomCell();
		MET.addNewRandomCell();
		MET.addNewRandomCell();
		MET.addNewRandomCell();
		MET.addNewRandomCell();
		MET.addNewRandomCell();
		MET.addNewRandomCell();
		MET.addNewRandomCell();
		return MET;
	}
	
	//como lleno toda la matriz los 2 que se hacen al principio no importan
	public static Mat2 matrizGameOver(){
		Mat2 MET = new Mat2(4);
		MET.setElements(0);   //porque por defecto hay 2
		MET.mat[0][0] = new Integer(4);
		MET.mat[0][1] = new Integer(8);
		MET.mat[0][2] = new Integer(4);
		MET.mat[0][3] = new Integer(2);
		MET.mat[1][0] = new Integer(2);
		MET.mat[1][1] = new Integer(4);
		MET.mat[1][2] = new Integer(2);
		MET.mat[1][3] = new Integer(4);
		MET.mat[2][0] = new Integer(4);
		MET.mat[2][1] = new Integer(8);
		MET.mat[2][2] = new Integer(4);
		MET.mat[2][3] = new Integer(8);
		MET.mat[3][0] = new Integer(2);
		MET.mat[3][1] = new Integer(4);
		MET.mat[3][2] = new Integer(8);
		MET.mat[3][3] = new Integer(2);
		MET.setElements(16);           //aca le asigne la cantidad total de elemntos que tiene
		return MET;
	}
	
	//una matriz de resultado con la que comparar un caso de movimiento
	//SOLO FUNCIONA SI NO SE AGREGAN CELDAS AL MOVER , USE WITH CAUTION!!!
	public Mat2  result(){
		Mat2 MET = new Mat2(6);
		MET.mat[4][4] = new Integer(2);
		MET.mat[4][5] = new Integer(16);
		MET.mat[5][0] = new Integer(4);
		MET.mat[5][1] = new Integer(8);
		MET.mat[5][2] = new Integer(4);
		MET.mat[5][3] = new Integer(8);
		MET.mat[5][4] = new Integer(4);
		MET.mat[5][5] = new Integer(2);
		MET.setElements(8);          
		return MET;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test       //	OK
	public void constructorTest(){
		System.out.println("constructor " + "ok");
		Mat2 a= new Mat2();
		System.out.println(a);
	}
	
	@Test     //OK
	public void addNewRandomCellTest(){
		System.out.println("agregar " + "ok");		
		Mat2 a = new Mat2();
		System.out.println(a);
		a.addNewRandomCell();
		System.out.println(a);
		assertEquals(3,a.getElements());
		System.out.println("fin agregar");
	}
	
	@Test
	public void equalsTest(){
		System.out.println("equals " + "ok");	
		Mat2 a = new Mat2();
		assertTrue(a.equals(a));
		Mat2 b = new Mat2();
		assertFalse(b.equals(a));
		System.out.println(a+"\n"+b);
		System.out.println("fin equals");
	}

	//metodos para mover
	private void MoveLeft(Mat2 a) {
		a.Move(Direction.LEFT, 0);
		a.Move(Direction.LEFT, 1);
		a.Move(Direction.LEFT, 2);
		a.Move(Direction.LEFT, 3);
	}
	private void MoveRight(Mat2 a) {
		a.Move(Direction.RIGHT, 0);
		a.Move(Direction.RIGHT, 1);
		a.Move(Direction.RIGHT, 2);
		a.Move(Direction.RIGHT, 3);
	}
	private void MoveUp(Mat2 a) {
		a.Move(Direction.UP, 0);
		a.Move(Direction.UP, 1);
		a.Move(Direction.UP, 2);
		a.Move(Direction.UP, 3);
	}
	private void MoveDown(Mat2 a) {
		a.Move(Direction.DOWN, 0);
		a.Move(Direction.DOWN, 1);
		a.Move(Direction.DOWN, 2);
		a.Move(Direction.DOWN, 3);
	}
	//fin metodos para mover
	@Test
	public void moveTest(){
		System.out.println("move " + "ok");
		Mat2 a = new Mat2();
		System.out.println(a);
		a.addNewRandomCell();
		a.addNewRandomCell();
		System.out.println(a);
		MoveLeft(a);
		System.out.println("hacia la izquierda: \n"+a);
		MoveRight(a);
		System.out.println("hacia la derecha: \n"+a);
		MoveUp(a);
		System.out.println("hacia la arriba: \n"+a);
		MoveDown(a);
		System.out.println("hacia la abajo: \n"+a);
		System.out.println("fin mover");

	}
/*funca bien si queres probarlo solo cambia a public
	@Test
	public void getDirectionParametersTest(){
		Mat2 a = new Mat2();
		Object[] parameters = a.getDirectionParameters(direction.UP, 0);
		assertEquals(0, (int) parameters[0]);
		assertEquals(a.mat.length, (int) parameters[1]);
		assertEquals(1, (int) parameters[2]);
		assertEquals(0, (int) parameters[3]);
		assertEquals(0, (int) parameters[4]);
		assertFalse( (boolean) parameters[5]);
		assertTrue( (boolean) parameters[6]);
		
	}
*/
	//metodos para combinar
		private void combineLeft(Mat2 a) {
			a.combineCells(Direction.LEFT, 0);
			a.combineCells(Direction.LEFT, 1);
			a.combineCells(Direction.LEFT, 2);
			a.combineCells(Direction.LEFT, 3);
		}
		private void combineRight(Mat2 a) {
		a.combineCells(Direction.RIGHT, 0);
		a.combineCells(Direction.RIGHT, 1);
		a.combineCells(Direction.RIGHT, 2);
		a.combineCells(Direction.RIGHT, 3);
		}
		private void combineUp(Mat2 a) {
			a.combineCells(Direction.UP, 0);
			a.combineCells(Direction.UP, 1);
			a.combineCells(Direction.UP, 2);
			a.combineCells(Direction.UP, 3);
		}
		private void combineDown(Mat2 a) {
			a.combineCells(Direction.DOWN, 0);
			a.combineCells(Direction.DOWN, 1);
			a.combineCells(Direction.DOWN, 2);
			a.combineCells(Direction.DOWN, 3);	
		}
		//fin metodos para combinar
	@Test
	public void combineCellsTest(){
		System.out.println("COMBINAR CELDAS OK");
		Mat2 a = new Mat2();
		a.addNewRandomCell();
		a.addNewRandomCell();
		a.addNewRandomCell();
		a.addNewRandomCell();
		a.addNewRandomCell();
		a.addNewRandomCell();
		a.addNewRandomCell();
		System.out.println(a);
		combineLeft(a);
		System.out.println("hacia la izquierda: \n"+a);
		combineRight(a);
		System.out.println("hacia la derecha: \n"+a);
		combineUp(a);
		System.out.println("hacia la arriba: \n"+a);
		combineDown(a);
		System.out.println("hacia la abajo: \n"+a);
		System.out.println("fin combinar");
	}
	
	@Test
	public void shiftTest(){
		System.out.println("prueba shift");
		Mat2 a = matTest();
		System.out.println(a.getElements());
		System.out.println(a);
		a.Shift(Direction.RIGHT);
		System.out.println("prueba shift derecha");
		System.out.println(a);
		System.out.println(a.getElements());
		a.Shift(Direction.LEFT);
		System.out.println("prueba shift izquierda");
		System.out.println(a);
		System.out.println(a.getElements());
		a.Shift(Direction.UP);
		System.out.println("prueba shift arriba");
		System.out.println(a);
		System.out.println(a.getElements());
		a.Shift(Direction.DOWN);
		System.out.println("prueba shift abajo");
		System.out.println(a);
		System.out.println(a.getElements());
	}
	
	@Test
	public void gameOverTest(){
		System.out.println("prueba gameover");
		Mat2 a = matrizGameOver();
		Mat2 b = matrizGameOver();
		System.out.println(a);
		assertTrue(a.isFull());
		assertTrue(a.gameOver());
		a.Shift(Direction.RIGHT);
		a.Shift(Direction.LEFT);
		a.Shift(Direction.DOWN);
		a.Shift(Direction.UP);
		System.out.println(a);
		assertTrue(a.isFull());
		assertTrue(a.gameOver());
		assertTrue(a.equals(b));
		System.out.println("prueba gameover fin ok ");
	}
	
	@Test
	public void isCombinableTest(){
		System.out.println("prueba is combinable ok");
		Mat2 a = matrizGameOver();
		assertFalse(a.isCombinable());
		System.out.println(a);
		Mat2 b = matTest();
		System.out.println(b);
		b.Shift(Direction.RIGHT);
		b.Shift(Direction.LEFT);
		b.Shift(Direction.DOWN);
		b.Shift(Direction.UP);
		System.out.println(b);
		assertTrue(b.isCombinable());
		System.out.println("prueba combinable ok");
	}
}
