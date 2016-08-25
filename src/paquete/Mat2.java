package paquete;

public class Mat2 {
	public Integer[][] mat;
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
	public boolean equals(Object otra1){
		Mat2 otra=(Mat2) otra1;
		
		if(otra==null){
			return false;	
		}
		
		else{
			if(otra.mat==null||mat==null){
				if(otra.mat==null&&mat==null){
					return true;
				}
				else{
					return false;
				}
				
			}
		}
		if(otra.mat.length!=mat.length){
		return false;
		}
		
		
		for(int x=0; x<otra.mat.length; x++){
			for(int y=0; y<otra.mat.length; y++){
				if(otra.mat[y][x]!=mat[y][x]){
					return false;
				}
			}
		}
		
		
		
		
		
		return true;
	}
	
	public Mat2(int n){
	
		if(n>10||n<3){
			throw new IllegalArgumentException("Invalid Matrix Size");
		}
		
		
		this.mat = new Integer [n][n];	
	}
	
	/**
	 * constructor de 4x4
	 */
	public Mat2(){
		this(4);
	}
	
	public Integer [][] getMat(){
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
	
	/**
	 * esta funcion mueve de izq a derecha todos los elementos de la fila, despues de que estos fueron combinados
	 * @param dir la direccion en la que se mueve la funcion
	 * @param filaoCol es la fila o columna en la que se opera
	 */
	public void Move(direction dir, int filaoCol){
		int from,to,indexDir,x,y,indexAux;
		
		boolean xaxis,yaxis;
		
		Object[] parameters=getDirectionParameters(dir,filaoCol);
		
		from=(int) parameters[0];
		to=(int) parameters[1];
		indexDir=(int) parameters[2];
		x=(int) parameters[3];
		y=(int) parameters[4];
		xaxis=(boolean) parameters[5];
		yaxis=(boolean) parameters[6];
		////
		Integer [] aux = new Integer [mat.length];
		Integer Current = null;
		
		indexAux=from;
		
		while(from!=to){
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
		for(int x=0;x<mat.length;x++){
				combineCells(Direction,x);
				Move(Direction,x);
		}
		
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
	 */
	public void combineCells(direction dir,int filaoCol ){
		int from,to,indexDir,x,y;
		boolean xaxis,yaxis;
		
		Object[] parameters=getDirectionParameters(dir,filaoCol);
		
		from=(int) parameters[0];
		to=(int) parameters[1];
		indexDir=(int) parameters[2];
		x=(int) parameters[3];
		y=(int) parameters[4];
		xaxis=(boolean) parameters[5];
		yaxis=(boolean) parameters[6];
	
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





}
