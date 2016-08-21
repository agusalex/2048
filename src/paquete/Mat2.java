package paquete;



public class Mat2 {
	private Int2048[][] mat;

	
	public static void main (String args [] ){
		Mat2 ww= new Mat2(4);
		
	
		ww.mat[0][0] = new Int2048(1);
		ww.mat[0][1] = new Int2048(1);
		ww.mat[0][2] = new Int2048(1);
		ww.mat[0][3] = new Int2048(1);
		
		System.out.println(ww);
		
		ww.conbineCellsR(0);
		
		System.out.println(ww.moveR(0));
		System.out.println(ww);
		ww.unlock();
		System.out.println(ww.conbineCellsR(0));
		System.out.println(ww);
		
	}
	
	
	public Mat2(int n){
		//TODO chequear que no este out of bounds
		this.mat = new Int2048 [n][n];	
	}
	
	/**
	 * constructor de 4x4
	 */
	public Mat2(){
		this(4);
	}
	
	public String toString(){
		String numeros = "";
		for(int x = 0; x < this.mat.length; x++){
			for(int y = 0; y < this.mat.length; y++){
				if(this.mat[x][y] == null)
					numeros += "0 | ";
				else
					numeros +=mat[x][y].toString() + " | ";
			}
			numeros += "\n";
		}
		
		return numeros;
	}
	
	
	public void moveRight(){ //Une los nnumeros que son iguales
	
	
	
	}

	
	public void moveLeft(){

	}
	public void moveDown(){
		
	}
	public void moveUp(){
		
	}
	
	// to' piola
	public void unlock(){
		for(int x=0;x<mat.length;x++)
			for(int y=0;y<mat.length;y++)
			    if(mat[x][y]!=null)
			    	mat[x][y].setUnlocked(true);
			
	}
	public boolean conbineCellsR(int fila){
		boolean ret = false;
		boolean nuevobkp = false;
		Int2048 bkp = null;
		Int2048 Actual = null;
			for(int y = mat.length-1; y >= 0 ; y--){
				nuevobkp = false;
				Actual = mat[fila][y];
				
				if(Actual != null){
					
					if(Actual.isUnlocked()){
						
						if(!Actual.equals(bkp)){
							bkp = Actual;
							nuevobkp = true;
						}
					
						if(Actual.equals(bkp) &&! nuevobkp){  //Si se pueden conbinar
							bkp.multiply();				//combinar
							Actual = null;
							mat[fila][y] = null;
							bkp.setUnlocked(false);     //bloqueo
							ret = true;                 //para saber si se combinaron o no
						}	
					
					}
					
				}
			}
			return ret;
	}
	
	public boolean moveR(int fila){
		boolean ret = false;
		Int2048 actual = null; 
		int index=mat.length-1;
		Int2048 [] aux = new Int2048 [mat.length];
		for(int y = mat.length-1; y >= 0; y-- ){
			actual = mat [fila][y];
			if(actual != null){
				aux [index] = actual;
				index--;
			}
			else{
				if (y > 0 && !ret){
					if(mat[fila][y-1] != null)
						ret = true;
				}
			}
		}
		mat[fila] = aux;
		for(int y =0; y < aux.length; y++ ){
			System.out.println(aux[y]);
		}
		return ret;
	}
	
	public Int2048 [][] getMat(){
		return mat;
	}

	
}
