package paquete;

public class Mat2 {
	
	public Integer[][] mat;
	private int elements;           //para decir si esta llena o no
	private boolean hasCombined;    //para saber si se combina o no


	


	public Integer [][] getMat(){
		return mat;
	}
	
	

	/**
	 * Mueve la matriz
	 * @param LEFT
	 * @param RIGHT
	 * @param DOWN
	 * @param UP

	 */
	
	public enum direction{
		LEFT,RIGHT,DOWN,UP
	}
	

	@Override
	public boolean equals(Object another){
		if(another == null)
			return false;
		
		if(another instanceof Mat2){
			Mat2 matrix =(Mat2) another;
			
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
	
	public Mat2(int n){
	
		if(n>10||n<3){
			throw new IllegalArgumentException("Invalid Matrix Size");
		}
		
		
		this.mat = new Integer [n][n];	
//		this.initialize();
	}
	
	/**
	 *  Constructor de 4x4
	 */
	public Mat2(){
		this(4);
		this.initialize();
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
	 * busca una posicion al azar y agrega un 2 en la matriz
	 * de existir un numero en esa posicion busca otra
	 */
	public void addNewRandomCell() {  // le cambie el nombre 
		if(isFull())    //si esta llena no hace nada
 			return ;				//TODO La complejidad de pero caso es O(INFINITO) es aleatorio pero puede llegar a caer mas de una vez en la misma posicion
		int x = randomPosition();   //TODO una mejor opcion seria recorrer el arreglo y agregar un num en el X (contador) lugar que encuentre vacio
		int y = randomPosition();	//siendo X un numero aleatorio
									
		if (x == y){					
			y = x+1 % mat.length;  // WTF Despues contame que hace eso que posta no lo pude descifrar
		}						  
		if(this.mat[x][y] == null){
			this.mat[x][y] = 2;     //TODO Agregar mas numeros 4 y 8 que aparezcan aleatoriamente
			this.elements++;
		}
		else
			addNewRandomCell();   
	}
	

	/**
	 * esta funcion mueve de izq a derecha todos los elementos de la fila, despues de que estos fueron combinados
	 * @param dir la direccion en la que se mueve la funcion
	 * @param filaoCol es la fila o columna en la que se opera
	 */
	public void Move(direction dir, int filaoCol){
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
		if(xaxis)
			mat[y] = aux;
		
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
	public void Shift(direction Direction){
		for(int x = 0; x < mat.length; x++){
				combineCells(Direction,x);
				Move(Direction,x);
		}
		addNewRandomCell();
	}

	/**
	 * Devuelve los parametros para recorrer el arreglo en la direccion y orientacion correctas
	 * @param dir la direccion en la que se mueve
	 * @param filaoCol la fila o columna sobre la que se opera
	 * @return Retorna un arreglo de objetos con los parametros, tanto int como boolean 
	 */
	private Object[] getDirectionParameters(direction dir,int filaoCol){
		int y,x,from,to,indexDir;
		boolean xaxis=false;
		boolean yaxis=false;
		
		if (dir.equals(direction.LEFT)){
			from=0;
			to=mat.length;
			indexDir=1;
			y=filaoCol;
			x=from;
			xaxis=true;
		}
		else if (dir.equals(direction.RIGHT)){
			from=mat.length-1;
			to=-1;
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
			to=-1;
			indexDir=-1;
			x=filaoCol;
			y=from;
			yaxis=true;
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
	public void combineCells(direction dir,int filaoCol ){
		int from,to,indexDir,x,y;
		boolean xaxis,yaxis;
		
		Object[] parameters = getDirectionParameters(dir,filaoCol);
		
		hasCombined = false;  //revisa si se combino alguna
		
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

				if(bkp==-1){
					bkp=from;
					checkIfEquals=false;
				}
					
				if(xaxis){  //Si se mueve en el eje x
						
					if(!Current.equals(mat[y][bkp])){      //SI no es igual setea nuevo Bkp
		
						bkp =x ;
						checkIfEquals = false;   
					}
						
					else if(checkIfEquals){   //Si es igual
						Current=null;
						mat[y][x]=null;
						mat[y][bkp]=mat[y][bkp]*2;
						hasCombined = true;    //de combinarse, se cambia
						this.elements--;       //disminuye en uno ya que al combinarse dos numeros se tiene un elemento menos
					} 
				}
						
				if(yaxis){
							//Si se mueve en el eje y
					if(!Current.equals(mat[bkp][x])){  //SI no es igual setea nuevo Bkp
						bkp = y;
						checkIfEquals = false;   
					}
							
					else if(checkIfEquals){   //Si es igual
						
						Current=null;
						mat[y][x]=null;
						mat[bkp][x]=mat[bkp][x]*2;
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
	
	/**
	 * usada para buscar una posicion al azar de la matrizz para agregar 2
	 * @return  el numero entre el rango de la matriz que representa la posicion
	 */
	private int randomPosition(){
		int num = (int) (Math.random()*mat.length-1);
		return  num;
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
	public boolean gameOver(){      //TODO cambiar implementacion
		if( isFull() && !hasCombined)
			return true;
		if( !isFull() || hasCombined)
			return false;
		return false;
	}

	public int getElements() {
		return elements;
	}

	public void setElements(int elements) {
		this.elements = elements;
	}

	

	

}
