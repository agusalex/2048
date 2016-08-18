package paquete;

import java.util.Arrays;

public class Mat2 {
	private Integer[][] Mat;
	
	public Mat2(int n){
		//TODO chequear que no este out of bounds
		Integer[][] Mat = new Integer [n][n];
		boolean [][] MatBool = new boolean [n][n];
		
		Arrays.fill(MatBool, true); //Pone todo en true
	}
	
	
	public void moveLeft(){ //Une los nnumeros que son iguales
		
	}
	public void moveRight(){

	}
	public void moveDown(){
		
	}
	public void moveUp(){
		
	}
	
	public void addElement(int e, int x, int y){
		//TODO chequear que no este out of bounds y que sea potencia de 2
		Mat[x][y]=y;
	}
	
	public Integer[][] getMat(){
		return Mat;
	}

	
}
