package paquete;

import static org.junit.Assert.*;

import org.junit.Test;

import paquete.Mat2.direction;

public class Mat2Test {

	public static int aleatorio(){
		int num = (int) (Math.random()*4-2) +2;
		return  num % 2 == 0 ? num : num+1;
	}
	
	
	public static Integer[][] matTestRan(){
		Integer[][] ww= new Integer[6][6];
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
		return ww;
		
		
	}
	public static Integer[][] matTest(){
		Integer[][] ww= new Integer[6][6];
		ww[0][0] = new Integer(2);
		ww[0][1] = new Integer(4);
		ww[0][4] = new Integer(4);
		ww[0][5] = new Integer(4);
		ww[1][3] = new Integer(4);
		ww[1][4] = new Integer(4);
		ww[2][3] = new Integer(3);
		ww[2][2] = new Integer(3);
		ww[2][4] = new Integer(3);
		ww[3][5] = new Integer(1);
		ww[4][0] = new Integer(1);
		ww[4][3] = new Integer(2);
		ww[4][5] = new Integer(2);
		ww[5][1] = new Integer(1);
		ww[5][4] = new Integer(2);
		return ww;
		
		
	}
	

	
	
	public static void test1(){
		Mat2 original= new Mat2(6);
		original.mat=matTestRan();
		boolean ok=true;
		
		Mat2 Up2= new Mat2(6);
		Up2.mat=matTest();
		System.out.println("COMIENZA EL TEST");
		
		
		
		try{
			Up2.Shift(direction.UP);
			System.out.println("Original:\n"+original);

			System.out.println("UP");
			System.out.println("Mat a testear");
			System.out.println(Up2);
		}
		catch (Exception e){
			System.out.println("Up:EXCEPTION Error");
			e.printStackTrace(System.out);
		}
		
		///DOWN
		Mat2 Down2= new Mat2(6);
		Down2.mat=matTest();
		
		try{
			Down2.Shift(direction.DOWN);
			System.out.println("Original:\n"+original);
			System.out.println("DOWN");
			System.out.println("Mat a testear");
			System.out.println(Down2);			
		}
		catch (Exception e){
			System.out.println("DOWN:EXCEPTION Error");
			e.printStackTrace(System.out);
		}
		
		//LEFT
		Mat2 Left2= new Mat2(6);
		Left2.mat=matTest();
		
		try{
			Left2.Shift(direction.LEFT);
			System.out.println("Original:\n"+original);
			
			System.out.println("DOWN");
			System.out.println("Mat a testear");
			System.out.println(Left2);			
		}
		catch (Exception e){
			System.out.println("LEFT:EXCEPTION Error");
			e.printStackTrace(System.out);
		}
		
		//RIGHT
		Mat2 Right2= new Mat2(6);
		Right2.mat=matTest();
		try{
			Right2.Shift(direction.RIGHT);
			System.out.println("Right:OK"); 
		 	System.out.println("Original:\n"+original);
		 	System.out.println("RIGHT");
			System.out.println("Mat 1");
			System.out.println("Mat a testear");
			System.out.println(Right2);
					
	}
		catch (Exception e){
			System.out.println("RIGHT:EXCEPTION Error");
			e.printStackTrace(System.out);
		}
			if(ok){
				System.out.println("All system check, green across the board.  \n All signs are go prepare for downcount \n Test finalized");
			}		
	}
	
	@Test
	public void test() {
		test1();
		
		
		
//		fail("Not yet implemented");
	}

}
