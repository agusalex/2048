package paquete;

import java.util.Arrays;

public class Mat2 {
	private Int2048[][] mat;
	public enum direction{
		LEFT,RIGHT,DOWN,UP
	}

	public boolean esIgual(Mat2 otra){
		
		
		if (otra==null){
			return false;	
		}
		/*if(this.mat.length!=otra.mat.length){
			return false;
		}*/
		
		for(int x=0; x<mat.length;x++){
			for(int y=0; y<mat.length;y++){
				Int2048 actual=mat[x][y];
				Int2048 actual2=otra.mat[x][y];
		
				
				if(actual==null||actual2==null){
					if(!(actual==null&&actual2==null)){
						return false;
					}
					
					
				}
				else{
				if(actual.equals(actual2)){
					
					
					return false;
				}
				}
			}
		}
		
		return true;
		
		
		
		
	}
	
	

	public static void main (String args [] ){
		
		test();
		
	}
	
	
	public static Int2048[][] matTest(){
		Int2048[][] ww= new Int2048[6][6];
		ww[0][0] = new Int2048(1);
		ww[0][1] = new Int2048(1);
		ww[0][4] = new Int2048(2);
		ww[0][5] = new Int2048(3);
		ww[1][3] = new Int2048(2);
		ww[1][4] = new Int2048(1);
		ww[2][3] = new Int2048(2);
		ww[2][2] = new Int2048(2);
		ww[2][4] = new Int2048(2);
		ww[3][5] = new Int2048(2);
		ww[4][0] = new Int2048(1);
		ww[4][3] = new Int2048(1);
		ww[4][5] = new Int2048(2);
		ww[5][1] = new Int2048(1);
		ww[5][4] = new Int2048(2);
		return ww;
		
		
	}
	
	public static void test(){
		Mat2 original= new Mat2(6);
		original.mat=matTest();
		
		
		Mat2 Up= new Mat2(6);
		Up.mat=matTest();
		/////
		Up.moveUp();
		
		Mat2 Up2= new Mat2(6);
		Up2.mat=matTest();
		System.out.println("COMIENZA EL TEST");
		
		
		
		try{
			Up2.Shift(direction.UP);
			if(Up.esIgual(Up2)){
				System.out.println("Up:OK"); 
			} 
			
			else{
				System.out.println("Original:\n"+original);
				System.out.println("Up:Error");
				System.out.println("Mat 1");
				System.out.println(Up);
				System.out.println("Mat a testear");
				System.out.println(Up2);
			}
			
		}
		catch (Exception e){
			System.out.println("Up:EXCEPTION Error");
			e.printStackTrace(System.out);
		}
		
		
		///DOWN
		Mat2 Down= new Mat2(6);
		Down.mat=matTest();
		Down.moveDown();
		Mat2 Down2= new Mat2(6);
		Down2.mat=matTest();
		
		try{
			Down2.Shift(direction.DOWN);
			if(Down.equals(Down2)){
				System.out.println("Down:OK"); 
			} 
			
			else{
				System.out.println("Original:\n"+original);
				System.out.println("Down:Error");
				System.out.println("Mat 1");
				System.out.println(Down);
				System.out.println("Mat a testear");
				System.out.println(Down2);
			}
			
		}
		catch (Exception e){
			System.out.println("DOWN:EXCEPTION Error");
			e.printStackTrace(System.out);
		}
		
		//LEFT
		Mat2 Left= new Mat2(6);
		Left.mat=matTest();
		Left.moveLeft();
		Mat2 Left2= new Mat2(6);
		Left2.mat=matTest();
		
		try{
			Left2.Shift(direction.LEFT);
			if(Left.equals(Left2)){
				System.out.println("Left:OK"); 
			} 
			
			else{
				System.out.println("Original:\n"+original);
				System.out.println("Left:Error");
				System.out.println("Mat 1");
				System.out.println(Left);
				System.out.println("Mat a testear");
				System.out.println(Left2);
			}
			
		}
		catch (Exception e){
			System.out.println("LEFT:EXCEPTION Error");
			e.printStackTrace(System.out);
		}
		
		//RIGHT
				Mat2 Right= new Mat2(6);
				Right.mat=matTest();
				Right.moveRight();
				Mat2 Right2= new Mat2(6);
				Right2.mat=matTest();
				
				try{
					Right2.Shift(direction.RIGHT);
					if(Right.equals(Right2)){
						System.out.println("Right:OK"); 
					} 
					
					else{
						System.out.println("Original:\n"+original);
						System.out.println("Right:Error");
						System.out.println("Mat 1");
						System.out.println(Right);
						System.out.println("Mat a testear");
						System.out.println(Right2);
					}
					
				}
				catch (Exception e){
					System.out.println("RIGHT:EXCEPTION Error");
					e.printStackTrace(System.out);
					
				}
					
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
	
	
	public void Move(direction dir, int filaoCol){
		int y,x,from,to,indexDir,indexAux = 0;
		
		boolean xaxis=false;
		boolean yaxis=false;
		Int2048 [] aux = new Int2048 [mat.length];
		Int2048 actualElem = null;
		if (dir.equals(direction.LEFT)){
			from=0;
			to=mat.length-1;
			indexDir=1;
			y=filaoCol;
			x=from;
			xaxis=true;

		}
		else if (dir.equals(direction.RIGHT)){
			from=mat.length-1;
			to=0;
			indexDir=-1;
			y=filaoCol;
			x=from;
			xaxis=true;
		}
		else if (dir.equals(direction.UP)){
			from=0;
			to=mat.length;
			indexDir=1;
			x=filaoCol;
			y=from;
			yaxis=true;
		}
		else{
			from=mat.length-1;
			to=0;
			indexDir=-1;
			x=filaoCol;
			y=from;
			yaxis=true;
		}
		indexAux=from;
		
		while(from!=to){
			actualElem = mat [y][x];
			if(actualElem != null){
				aux [indexAux] = actualElem;
				indexAux+=indexDir;
				}
			
			
			if(xaxis){
				
				x+=indexDir;
			}
			else if(yaxis){
				y+=indexDir;
			}
			
			from+=indexDir;
			
	
		}
		if(xaxis)
			mat[y] = aux;
		
		else if(yaxis){
		
			for(int ind = 0; ind < mat.length;ind++){
				mat[ind][x] = aux[ind];
			}
		}
		
		
	}
	
	public void Shift(direction dir){
		for(int x=0;x<mat.length;x++){
				combineCells(dir,x);
				Move(dir,x);
		}
		
	}
	
	
	
	public void combineCells(direction dir,int filaoCol ){
		int y,x,from,to,indexDir;
		
		boolean xaxis=false;
		boolean yaxis=false;
		
		if (dir.equals(direction.LEFT)){
			from=0;
			to=mat.length-1;
			indexDir=1;
			y=filaoCol;
			x=from;
			xaxis=true;
		}
		else if (dir.equals(direction.RIGHT)){
			from=mat.length-1;
			to=0;
			indexDir=-1;
			y=filaoCol;
			x=from;
			xaxis=true;
		}
		else if (dir.equals(direction.UP)){
			from=0;
			to=mat.length;
			indexDir=1;
			x=filaoCol;
			y=0;
			yaxis=true;
		}
		else{
			from=mat.length-1;
			to=0;
			indexDir=-1;
			x=filaoCol;
			y=from;
			yaxis=true;
		}
		
		boolean comparable = true;         
		Int2048 bkp = null;					
		Int2048 Actual = null;   
		
			while(from!=to){
				
				comparable = true;		
				////
				Actual = mat[y][x];      
				/////
				
				if(Actual != null){
					
						if(!Actual.equals(bkp)){
							bkp = Actual;
							comparable = false;   
						}
					
						if(Actual.equals(bkp) && comparable){ 
							bkp.multiply();	
							//////
							mat[y][x] = null;
							/////
							
							bkp = null;					
						}	
					
				}
				
				if(xaxis){
					
					x+=indexDir;
				}
				if(yaxis){
					y+=indexDir;
				}
				
				from+=indexDir;
				
			}
			
		
		
	}
		
		
	
	
	
	
	
	
	
	
	
	//DERECHA
	public void moveRight(){ //Une los nnumeros que son iguales
		   for(int i = 0; i< mat.length; i++){
			   combineCellsR(i);
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
	private void combineCellsR(int fila){
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
