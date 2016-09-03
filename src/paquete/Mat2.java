package paquete;
import paquete.Direction;
public class Mat2 {
	
	public Integer[][] mat;
	private int elements = 0;           //para decir si esta llena o no
	private boolean hasCombined;        //para decir si se combinaron o no
	private boolean Win;
	
	public int getElements() {
		return elements;
	}

	public void setElements(int elements) {
		this.elements = elements;
	}

	public Integer [][] getMat(){
		return mat;
	}
	


	public Mat2(int n){
	
		if(n>10||n<3){
			throw new IllegalArgumentException("Invalid Matrix Size");
		}
		
		Win=false;
		this.mat = new Integer [n][n];	
		initialize();
	}
	
	/**
	 *  Constructor de 4x4
	 */
	public Mat2(){
		
		this(4);
		initialize();
	}
	
	
	/**
	 * asigna dos numeros 2 en la matriz en una posicion aleatoria
	 * @param x     en alguna fila
	 * @param y		en alguna columna
	 */
	private void initialize(){
		addNewRandomCell();
		addNewRandomCell();
	}
	


	/**
	 * esta funcion mueve de izq a derecha todos los elementos de la fila, despues de que estos fueron combinados
	 * @param dir la direccion en la que se mueve la funcion
	 * @param filaoCol es la fila o columna en la que se opera
	 */
	public void Move(Direction dir, int filaoCol){
		int from,to,indexDir,x,y,indexAux;
		
		boolean xaxis,yaxis;
		
		Object[] parameters = getDirectionParameters(dir,filaoCol);
		
		from = (int) parameters[0];
		to = (int) parameters[1];
		indexDir = (int) parameters[2];
		x = (int) parameters[3];
		y = (int) parameters[4];
		xaxis = (boolean) parameters[5];
		yaxis = (boolean) parameters[6];
		////
		Integer [] aux = new Integer [mat.length];
		Integer Current = null;
		
		indexAux = from;
	
		
		while(from != to){
			//////
			Current = mat [y][x]; //ELemeto Actual
			///////
			
			if(Current != null){
				
				aux [indexAux] = Current;
				indexAux += indexDir;
			}
		
			if(xaxis)
				x += indexDir;
			else if(yaxis)
				y += indexDir;
			
			from+=indexDir;
		}
		if(xaxis){
			mat[y] = aux;
		}
		else if(yaxis){
			for(int ind = 0; ind < mat.length;ind++){
				mat[ind][x] = aux[ind];
			}
		}
	}
	
	/**
	 * Mueve la matrix y conbina todas  sus filas en la direccion indicada
	 * @param Direction la direccion en la que se mueve la funcion
	 * @param filaoCol es la fila o columna en la que se opera

	 */
	public void Shift(Direction Direction){
		Integer [] [] copy = copiarMatriz();
		for(int x = 0; x < mat.length; x++){
				combineCells(Direction,x);
				Move(Direction,x);
		}
		if(!this.mat.equals(copy) && !hasCombined )   //si se movio y si no se combino nada, de ser igual la mtriz actual que la copia quiere decir que no se hicieron cambios
			addNewRandomCell();
		else if (hasCombined)    // con solo combinar ya se agrega una nueva celda dado que se mueve
			addNewRandomCell();
		gameOver();
		
	}

	/**
	 * Devuelve los parametros para recorrer el arreglo en la direccion y orientacion correctas
	 * @param dir la direccion en la que se mueve
	 * @param filaoCol la fila o columna sobre la que se opera
	 * @return Retorna un arreglo de objetos con los parametros, tanto int como boolean 
	 */
	private Object[] getDirectionParameters(Direction dir,int filaoCol){
		int y,x,from,to,indexDir;
		boolean xaxis = false;
		boolean yaxis = false;
		
		if (dir.equals(Direction.LEFT)){
			from = 0;
			to = mat.length;
			indexDir = 1;
			y = filaoCol;
			x = from;
			xaxis = true;
		}
		else if (dir.equals(Direction.RIGHT)){
			from = mat.length-1;
			to =- 1;
			indexDir =- 1;
			y = filaoCol;
			x = from;
			xaxis = true;
		}
		else if (dir.equals(Direction.UP)){
			from = 0;
			to = mat.length;
			indexDir = 1;
			x = filaoCol;
			y = from;
			yaxis = true;
		}
		else{
			from = mat.length-1;
			to =- 1;
			indexDir =- 1;
			x = filaoCol;
			y = from;
			yaxis = true;
		}
		
		return  new Object[]{from,to,indexDir,x,y,xaxis,yaxis};
	}
	
	
	/**
	 * combina las celdas que tengan mismo numero, revisa de izquierda a derecha, buscando un numero identico a la celda actual
	 * una vez que los combina, pasa a buscar otra operacion
	 * @param dir la direccion en la que se mueve
	 * @param filaoCol la fila o columna sobre la que se opera
	 * @return true si se realizo por lo menos una combinacion en alguna fila, o columna
	 */
	public void combineCells(Direction dir,int filaoCol ){
		int from,to,indexDir,x,y;
		boolean xaxis,yaxis;
		
		Object[] parameters = getDirectionParameters(dir,filaoCol);
		
		
		from = (int) parameters[0];
		to = (int) parameters[1];
		indexDir = (int) parameters[2];
		x = (int) parameters[3];
		y = (int) parameters[4];
		xaxis = (boolean) parameters[5];
		yaxis = (boolean) parameters[6];
	
		boolean checkIfEquals = true;         
		int bkp = -1;					
		Integer Current = null;   
		hasCombined = false;              //supongoi que no se combinan
		
		while(from!=to){
				
			checkIfEquals = true;		
			////
			Current = mat[y][x];      
			/////
				
			if(Current != null){

				if(bkp == -1){
					bkp = from;
					checkIfEquals = false;
				}
					
				if(xaxis){  //Si se mueve en el eje x
						
					if(!Current.equals(mat[y][bkp])){      //SI no es igual setea nuevo Bkp
		
						bkp =x ;
						checkIfEquals = false;   
					}
						
					else if(checkIfEquals){   //Si es igual
						Current = null;
						mat[y][x] = null;
						mat[y][bkp] = mat[y][bkp]*2;
						
						if(mat[y][bkp]==2048){   //SI GANO
							Win=true;
						}
						
						
						this.elements--;       //disminuye en uno ya que al combinarse dos numeros se tiene un elemento menos
						hasCombined = true;
					} 
				}
						
				if(yaxis){ //Si se mueve en el eje y
							
					if(!Current.equals(mat[bkp][x])){  //SI no es igual setea nuevo Bkp
						bkp = y;
						checkIfEquals = false;   
					}
							
					else if(checkIfEquals){   //Si es igual
						
						Current = null;
						mat[y][x] = null;
						mat[bkp][x] = mat[bkp][x]*2;
						
						if(mat[bkp][x]==2048){//SI GANO
							Win=true;
						}
						this.elements--;       //disminuye en uno ya que al combinarse dos numeros se tiene un elemento menos
						hasCombined = true;
					}
				}
					
			}
				
			if(xaxis){					//Incremante el x
					
			   x+=indexDir;
			}							// O
				
			if(yaxis){
				y+=indexDir;			//Incrementa el y
			}
				
			from+=indexDir;
		}


	}
		
	
	private void addNewRandomCell(int cont, int x, int y){
		Integer elem = mat[x][y];
		//si llego a su destino
		if(this.isFull())
			return ;
		
		if(elem == null && cont == 0){  //CASO BASE1
			this.mat[x][y] = 2;
			this.elements++;
			return;
			
		}
		if( x == this.mat.length-1 && y == this.mat.length-1)  //CASO BASE2
			return;

		
		//esto es para que, como el valor de coordinate se obtiene a traves de random(),
		//se use para no buscar siempre la primer celda vacia, y busque varias de acuerdo al valor de coordinate
		int addOrNot=0;
		if(elem==null){
			addOrNot=1;
		}
		
			if(y == this.mat.length-1 && x < this.mat.length-1)   //si llegue al final de una fila y no coloca
				addNewRandomCell(cont-addOrNot, x+1, y-y);     //pasa a la siguiente
			else 
				addNewRandomCell(cont-addOrNot, x, y+1);
		
		//lo mismo pasa si encuentra casillas llenas, solo que no disminuya coordinate
		//es mas para el caso en que queden pocas celdas vacias
								

	}	
	
		
	/**
	 * usada para buscar una posicion al azar de la matriz para agregar 2
	 * @return  el numero entre 0 y la cantidad de celdas vacias que se usa para ubicar un 2 en alguna de ellas
	 */
	private int randomPosition(){
		int totalElements = mat.length*mat.length;
		int num = (int) (Math.random()*(totalElements - this.elements));
		return  num;
	}
	
	
	/**
	 * busca una posicion al azar y agrega un 2 en la matriz
	 * de existir un numero en esa posicion busca otra
	 */
	public void addNewRandomCell() {  // le cambie el nombre 
		this.addNewRandomCell(randomPosition(),0,0);   
	}
	
	
	public Integer[][] copiarMatriz(){
		Integer [][] copy = new Integer [mat.length][mat.length];
		for(int x = 0; x < mat.length; x++){
			for( int y = 0; y < mat.length; y++){
				copy [x][y] = mat[x][y];
			}
		}
		return copy;
	}
	
	

	@Override
	public boolean equals(Object another){
		if(another == null)
			return false;
		
		if(another instanceof Mat2){
			Mat2 matrix =(Mat2) another;
			
			if(matrix.mat.length!=mat.length){
				return false;
			}
			
			for(int x = 0; x< mat.length;x++){
				for(int y =0 ; y < mat.length; y++ ){
					
					Integer actualMat = mat[x][y];
					Integer actualOther = matrix.mat[x][y];
					
					if(actualMat != null && actualOther != null){
						
						if(!actualMat.equals(actualOther))
							return false;
					}
					else if(actualMat == null && actualOther != null)	
						return false;

					else if(actualMat != null && actualOther == null)	
						return false;
				}
			}
		}
		
		else{
			return false;}  //Si another no es un objeto de tipo Mat2
		
		return true;
	
	}
	

	/**
	 * @return devuelve si esta llena la matriz
	 */
	public boolean isFull(){
		return this.elements == mat.length*mat.length;
	}
	


	@Override
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
	
	
	/**
	 * revisa si esta llena y si se combino alguna celda
	 * @param dir
	 * @param rowOrColumn
	 * @return  true si esta llena y no se combino, false si no esta llena o si se combino alguna 
	 */
	public boolean gameOver(){     
		if(isWin())
			return true;
		else if( isFull() && !isCombinable())
			return true;
		else if( !isFull() || isCombinable())
			return false;
		return false;
	}
	
	
	public boolean isWin() {
		return Win;
	}

	public void setWin(boolean win) {
		Win = win;
	}

	/**
	 * revisa si hay filas o columnas por combinar
	 * @return  true si existe al menos una fila o columna para combinar 
	 */
	public boolean isCombinable(){
		boolean ret = false;
		for(int row = 0; row < mat.length; row++){
			ret = ret || isCombinable(Direction.RIGHT,row) || isCombinable(Direction.DOWN,row);
		}
		return ret;
	}
	
	/**
	 * revisa si hay filas o columnas por combinar en la direccion especificada
	 * @return  true si existe al menos una fila o columna para combinar 
	 */	
	public boolean isCombinable(Direction dir, int row_Column){
		int from,to,indexDir,x,y;
		boolean xaxis,yaxis;
		Object [] parameters = null; 
		
		if(dir.equals(Direction.LEFT) || dir.equals(Direction.RIGHT))
			parameters = getDirectionParameters(Direction.RIGHT,row_Column);
		else
			parameters = getDirectionParameters(Direction.DOWN,row_Column);

		from = (int) parameters[0];
		to = (int) parameters[1];
		indexDir = (int) parameters[2];
		x = (int) parameters[3];
		y = (int) parameters[4];
		xaxis = (boolean) parameters[5];
		yaxis = (boolean) parameters[6];
		
		boolean checkIfEquals = true;         
		int bkp = -1;					
		Integer Current = null;   
		
		while(from!=to){	
			checkIfEquals = true;		
			////
			Current = mat[y][x];      
			/////
			if(Current != null){
				
				if(bkp == -1){
					bkp = from;
					checkIfEquals = false;
				}
					
				if(xaxis){  //Si se mueve en el eje x
						
					if(!Current.equals(mat[y][bkp]))      //SI no es igual setea nuevo Bkp
						bkp = x ;
					
					else if(checkIfEquals)   //Si es igual
						return true;
					
				}
						
				if(yaxis){ //Si se mueve en el eje y
							
					if(!Current.equals(mat[bkp][x]))  //SI no es igual setea nuevo Bkp
						bkp = y;
					  
					else if(checkIfEquals)   //Si es igual
						return true;
					
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

	return false;
	}	

	
}
