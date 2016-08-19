package paquete;



public class Mat2 {
	private Integer[][] Mat;
	
	
	public static void main (String args [] ){
		Mat2 ww= new Mat2(3);
		
		System.out.print(ww);
		
		for(int x=0;x<ww.Mat.length;x++){
			for(int y=0;y<ww.Mat.length;y++){
				ww.Mat[x][y]=3;
			}
		}
	}
	
	
	public Mat2(int n){
		//TODO chequear que no este out of bounds
		Integer[][] Mat = new Integer [n][n];
		
		
	}
	
	
	public String toString(){
		String esuntring="";
		for(int x=0;x<Mat.length;x++){
			for(int y=0;y<Mat.length;y++){
				esuntring=esuntring+" | "+Mat[x][y];
			}
			esuntring=esuntring+"\n";
		}
		
		return esuntring;
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
