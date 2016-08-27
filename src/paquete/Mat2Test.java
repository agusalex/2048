package paquete;



import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import paquete.Mat2.direction;


public class Mat2Test {

	public static Mat2 ultima;
	
	public static int aleatorio(){
		int num = (int) (Math.random()*4-2) +2;
		return  num % 2 == 0 ? num : num+1;
	}
	
	
	public static Mat2 matTestRan(){
		
		Mat2 MET= new Mat2(6);
		Integer[][] ww=MET.mat;
		ww[0][0] = new Integer(aleatorio());
		ww[0][1] = new Integer(aleatorio());
		ww[0][4] = new Integer(aleatorio());
		ww[0][5] = new Integer(aleatorio());
		ww[1][3] = new Integer(aleatorio());
		ww[1][4] = new Integer(aleatorio());
		ww[2][3] = new Integer(aleatorio());
		ww[2][2] = new Integer(aleatorio());
		ww[2][4] = new Integer(aleatorio());
		ww[3][5] = new Integer(aleatorio());
		ww[4][0] = new Integer(aleatorio());
		ww[4][3] = new Integer(aleatorio());
		ww[4][5] = new Integer(aleatorio());
		ww[5][1] = new Integer(aleatorio());
		ww[5][4] = new Integer(aleatorio());
		return MET;
		
		
	}
	public static Mat2 matTest(){
		
		Mat2 MET= new Mat2(6);
		Integer[][] ww=MET.mat;
		ww[0][0] = new Integer(2);
		ww[0][1] = new Integer(4);
		ww[0][4] = new Integer(4);
		ww[0][5] = new Integer(4);
		ww[1][3] = new Integer(4);
		ww[1][4] = new Integer(4);
		ww[2][3] = new Integer(2);
		ww[2][2] = new Integer(4);
		ww[2][4] = new Integer(2);
		ww[3][5] = new Integer(4);
		ww[4][0] = new Integer(4);
		ww[4][3] = new Integer(2);
		ww[4][5] = new Integer(2);
		ww[5][1] = new Integer(4);
		ww[5][4] = new Integer(2);
		return MET;
		
		
	}
	
	public static Mat2 matrizGameOver(){
		
		Mat2 MET= new Mat2(4);
		Integer[][] mat=MET.mat;
		mat[0][0] = new Integer(4);
		mat[0][1] = new Integer(8);
		mat[0][2] = new Integer(4);
		mat[0][3] = new Integer(2);
		mat[1][0] = new Integer(2);
		mat[1][1] = new Integer(4);
		mat[1][2] = new Integer(2);
		mat[1][3] = new Integer(4);
		mat[2][0] = new Integer(4);
		mat[2][1] = new Integer(8);
		mat[2][2] = new Integer(4);
		mat[2][3] = new Integer(8);
		mat[3][0] = new Integer(2);
		mat[3][1] = new Integer(4);
		mat[3][2] = new Integer(8);
		mat[3][3] = new Integer(2);
		
		ultima=MET;
		return MET;

	}
	

public static boolean testUP(Mat2 test){
		
		boolean ok=true;
		Mat2 UP2= new Mat2(6);
		UP2.mat=test.mat;

		
		try{
			UP2.Shift(direction.UP);
			System.out.println("UP");
			System.out.println("Original:\n"+test);
			System.out.println("Mat a testear");
			System.out.println(UP2);		
			System.out.println("///////////////////////////////////////");
		}
		catch (Exception e){
			ok=false;
			System.out.println("UP:EXCEPTION Error");
			e.printStackTrace(System.out);
		}
		ultima=UP2;
		return ok;
	}
	
public static boolean testDOWN(Mat2 test){
	
	boolean ok=true;
	Mat2 DOWN2= new Mat2(6);
	DOWN2.mat=test.mat;

	
	try{
		DOWN2.Shift(direction.DOWN);
		System.out.println("DOWN");
		System.out.println("Original:\n"+test);
		System.out.println("Mat a testear");
		System.out.println(DOWN2);		

	}
	catch (Exception e){
		ok=false;
		System.out.println("DOWN:EXCEPTION Error");
		e.printStackTrace(System.out);
	}
	ultima=DOWN2;
	return ok;
}

public static boolean testLEFT(Mat2 test){
	
	boolean ok=true;
	Mat2 LEFT2= new Mat2(6);
	LEFT2.mat=test.mat;

	
	try{
		LEFT2.Shift(direction.LEFT);
		System.out.println("LEFT");
		System.out.println("Original:\n"+test);
		System.out.println("Mat a testear");
		System.out.println(LEFT2);		
		System.out.println("///////////////////////////////////////");
	}
	catch (Exception e){
		ok=false;
		System.out.println("LEFT:EXCEPTION Error");
		e.printStackTrace(System.out);
	}
	ultima=LEFT2;
	return ok;
}
public static boolean testRIGHT(Mat2 test){
	
	boolean ok=true;
	Mat2 RIGHT2= new Mat2(6);
	RIGHT2.mat=test.mat;

	
	try{
		RIGHT2.Shift(direction.RIGHT);
		System.out.println("RIGHT");
		System.out.println("Original:\n"+test);
		System.out.println("Mat a testear");
		System.out.println(RIGHT2);		
		System.out.println("///////////////////////////////////////");
	}
	catch (Exception e){
		ok=false;
		System.out.println("RIGHT:EXCEPTION Error");
		e.printStackTrace(System.out);
	}
	ultima=RIGHT2;
	return ok;
}




	
	
	public static boolean testGameOver(){
		try{
			System.out.println("Checking game over...");
			boolean ok=true&&testUP(matrizGameOver())&&testRIGHT(ultima)&&testLEFT(ultima)&&testDOWN(ultima);
			
			if(matrizGameOver().equals(ultima)){
				
				if(ok)
				System.out.println("////***////***//TEST OK///***///***///");
				
				return ok;}
			System.out.println("Matriz de GO no coincide");
			return false;
		}
		catch (Exception e){
			e.printStackTrace(System.out);
			return false;
		}
		
	}
	
	
	public static boolean testfijo(){
		try{
			System.out.println("Checking control case matTest...");
			boolean ok=true&testUP(matTest())&testRIGHT(matTest())&testLEFT(matTest())&&testDOWN(matTest());
			if(ok)
				System.out.println("////***////***//TEST OK///***///***///");
			else{
				System.out.println("////***////***//TEST Error///***///***///");
			}
			return ok;
			
		}
		catch (Exception e){
			e.printStackTrace(System.out);
			return false;
		}
		
	}
	public static boolean testRandom(){
		try{
			System.out.println("Checking Random Matrix...");
			boolean ok=true&testUP(matTestRan())&testRIGHT(matTestRan())&testLEFT(matTestRan())&&testDOWN(matTestRan());
			System.out.println("////***////***//TEST OK///***///***///");
			
			return ok;
		}
		catch (Exception e){
			e.printStackTrace(System.out);
			return false;
		}
		
	}
	
	
	
	
	@Ignore
	public void test() {
		boolean ok=testGameOver()&&testfijo()&&testRandom();
		try{
			if(!matTest().equals(matTest())){
			ok=false;
			System.out.println("///error en el equals///");
			}}
			catch(Exception e){
				e.printStackTrace(System.out);
			}
		
		if(ok){
			System.out.println("\n All system check.....green across the board. \n Test finalized \n .");
			
		}	
		
		else{
			System.out.println("error");
			fail("some error in test refer to test");
		}
		
	}
	
	@Test
	public void equalsTest(){
		Mat2 a = matTest();
		Mat2 b = matTest();
		assertTrue(a.equals(b));
	}

}
