package paquete;



public class Mat2 {
	private Int2048[][] mat;

	
	public static void main (String args [] ){
		Mat2 ww= new Mat2(6);
		
	
		ww.mat[0][0] = new Int2048(1);
		ww.mat[0][1] = new Int2048(1);
		ww.mat[0][4] = new Int2048(2);
		ww.mat[0][5] = new Int2048(3);
		ww.mat[1][3] = new Int2048(2);
		ww.mat[1][4] = new Int2048(1);
		ww.mat[2][3] = new Int2048(2);
		ww.mat[2][2] = new Int2048(2);
		ww.mat[2][4] = new Int2048(2);
		ww.mat[3][5] = new Int2048(2);
		ww.mat[4][0] = new Int2048(1);
		ww.mat[4][3] = new Int2048(1);
		ww.mat[4][5] = new Int2048(2);
		ww.mat[5][1] = new Int2048(1);
		ww.mat[5][4] = new Int2048(2);
		
		
		System.out.println(ww);
		ww.moveUp();
		System.out.println(ww);
		ww.moveUp();
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
	
	public Int2048 [][] getMat(){
		return mat;
	}

	public String toString(){
		String numeros = "";
		for(int x = 0; x < this.mat.length; x++){
			for(int y = 0; y < this.mat.length; y++){
				if(this.mat[x][y] == null)
					numeros += "- | ";
				else
					numeros +=mat[x][y].toString() + " | ";
			}
			numeros += "\n";
		}
		
		return numeros;
	}
	
	
	
	//DERECHA
	public void moveRight(){ //Une los nnumeros que son iguales
		   for(int i = 0; i< mat.length; i++){
			   conbineCellsR(i);
			   moveR(i);
			   unlock();
		   }
	}
	
	
	/**
	 * combina las celdas que tengan mismo numero, revisa de derecha a izquierda, buscando un numero identico a la celda actual
	 * una vez que los combina, pasa a buscar otra operacion
	 * @param fila , la fila a combinar
	 * @return booleano, este indica si se realizo alguna combinacion para poder pasar a reubicar los resultados
	 */
	private void conbineCellsR(int fila){
		boolean comparable = true;           //indica si se puede combinar las dos celdas
		Int2048 bkp = null;					//auxiliar para el numero con el cual operar
		Int2048 Actual = null;   
			for(int y = mat.length-1; y >= 0 ; y--){
				comparable = true;
				Actual = mat[fila][y];      //el actual es el elemento en cuestion
				
				if(Actual != null){
					
					if(Actual.isUnlocked()){     
						
						if(!Actual.equals(bkp)){
							bkp = Actual;
							comparable = false;   //para evitar que se combine el mismo numero con sigo mismo
						}
					
						if(Actual.equals(bkp) && comparable){  //Si se pueden combinar
							bkp.multiply();				//combinar
							mat[fila][y] = null;		
							bkp.setUnlocked(false);     //bloqueo
							bkp = null;					//para evitar que encuentre otro igual a ese
						}	
					}
				}
			}
	}
	
	/**
	 * esta funcion mueve de izq a derecha todos los elementos de la fila, despues de que estos fueron combinados
	 * 
	 * @param fila
	 * @return
	 */
	private void moveR(int fila){
		Int2048 actual = null; 
		int index = mat.length-1;
		Int2048 [] aux = new Int2048 [mat.length];
		for(int y = mat.length-1; y >= 0; y-- ){
			actual = mat [fila][y];
			if(actual != null){
				aux [index] = actual;
				index--;
			}
		}
		mat[fila] = aux;
	}

	
	//IZQYUERDA
	public void moveLeft(){
		for(int i = 0; i< mat.length; i++){
			   conbineCellsL(i);
			   moveL(i);
			   unlock();
	    }
	}
	
	/**
	 * combina las celdas que tengan mismo numero, revisa de izquierda a derecha, buscando un numero identico a la celda actual
	 * una vez que los combina, pasa a buscar otra operacion
	 * @param fila , la fila a combinar
	 * @return booleano, este indica si se realizo alguna combinacion para poder pasar a reubicar los resultados
	 */
	private void conbineCellsL(int fila) {
		boolean comparable = true;         
		Int2048 bkp = null;					
		Int2048 Actual = null;   
			for(int y = 0; y < mat.length ; y++){
				comparable = true;
				Actual = mat[fila][y];      
				
				if(Actual != null){
					
					if(Actual.isUnlocked()){     
						
						if(!Actual.equals(bkp)){
							bkp = Actual;
							comparable = false;   
						}
					
						if(Actual.equals(bkp) && comparable){ 
							bkp.multiply();				
							mat[fila][y] = null;		
							bkp.setUnlocked(false);     
							bkp = null;					
						}	
					}
				}
			}
		
	}

	/**
	 * esta funcion mueve de izq a derecha todos los elementos de la fila, despues de que estos fueron combinados
	 * @param fila
	 * @return
	 */
	private void moveL(int fila) {
		Int2048 actual = null; 
		int index = 0;
		Int2048 [] aux = new Int2048 [mat.length];
		for(int y = 0; y < mat.length; y++ ){
			actual = mat [fila][y];
			if(actual != null){
				aux [index] = actual;
				index++;
			}
		}
		mat[fila] = aux;
	}

   //ABAJO
	public void moveDown(){
		for(int col = 0; col < mat.length; col++){
			 conbineCellsD(col);
			 moveD(col);
			 unlock();
		}
	}
	
	/**
	 * combina las celdas que tengan mismo numero, revisa de izquierda a derecha, buscando un numero identico a la celda actual
	 * una vez que los combina, pasa a buscar otra operacion
	 * @param fila , la fila a combinar
	 * @return booleano, este indica si se realizo alguna combinacion para poder pasar a reubicar los resultados
	 */
	private void conbineCellsD(int columna) {
		boolean comparable = true;         
		Int2048 bkp = null;					
		Int2048 Actual = null;   
			for(int x = mat.length-1; x >= 0 ; x--){
				comparable = true;
				Actual = mat[x][columna];      
				
				if(Actual != null){
					
					if(Actual.isUnlocked()){     
						
						if(!Actual.equals(bkp)){
							bkp = Actual;
							comparable = false;   
						}
					
						if(Actual.equals(bkp) && comparable){ 
							bkp.multiply();				
							mat[x][columna] = null;		
							bkp.setUnlocked(false);     
							bkp = null;					
						}	
					}
				}
			}
		
	}

	/**
	 * esta funcion mueve de izq a derecha todos los elementos de la fila, despues de que estos fueron combinados
	 * @param fila
	 * @return
	 */
	private void moveD(int columna) {
		Int2048 actual = null; 
		int index = mat.length-1;
		Int2048 [] aux = new Int2048 [mat.length];
		for(int x = mat.length-1; x >= 0; x-- ){
			actual = mat [x][columna];
			if(actual != null){
				aux [index] = actual;
				index--;
			}
		}
		for(int x = 0; x<aux.length; x++){
			mat[x][columna] = aux[x];
		}
	}

	
	//ARRIBA
	public void moveUp(){
		for(int col = 0; col < mat.length; col++){
			 conbineCellsU(col);
			 moveU(col);
			 unlock();
		}
	}
	
	private void conbineCellsU(int columna) {
		boolean comparable = true;         
		Int2048 bkp = null;					
		Int2048 Actual = null;   
			for(int x = 0; x <  mat.length; x++){
				comparable = true;
				Actual = mat[x][columna];      
				
				if(Actual != null){
					
					if(Actual.isUnlocked()){     
						
						if(!Actual.equals(bkp)){
							bkp = Actual;
							comparable = false;   
						}
					
						if(Actual.equals(bkp) && comparable){ 
							bkp.multiply();				
							mat[x][columna] = null;		
							bkp.setUnlocked(false);     
							bkp = null;					
						}	
					}
				}
			}
		
	}


	private void moveU(int columna) {
		Int2048 actual = null; 
		int index = 0;
		Int2048 [] aux = new Int2048 [mat.length];
		for(int x =0; x <  mat.length; x++ ){
			actual = mat [x][columna];
			if(actual != null){
				aux [index] = actual;
				index++;
			}
		}
		for(int x = 0; x < aux.length; x++){
			mat[x][columna] = aux[x];
		}
	}
	
	/** desbloquea todas las casillas luego de haber hecho un movimiento*/
	private void unlock(){
		for(int x=0;x<mat.length;x++)
			for(int y=0;y<mat.length;y++)
			    if(mat[x][y]!=null)
			    	mat[x][y].setUnlocked(true);
			
	}
}
