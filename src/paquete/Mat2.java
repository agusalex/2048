package paquete;

import java.util.Arrays;



public class Mat2 {
	public Integer[][] mat;
	public enum direction{
		LEFT,RIGHT,DOWN,UP
	}

	public  Mat2 duplicarmat(){
		
		Mat2 otra= new Mat2(6);
		
		for(int x = 0; x < mat.length;x++){
			for(int y = 0; y < mat.length;y++){
				Integer actual = mat[x][y];
				if(actual!=null){
					otra.mat[x][y]=actual;
				}
			
			
			}}
		return otra;
	}
	
	
	
	public boolean esIgual(Mat2 otra){
		
		
		if (otra==null){
			return false;	
		}
		/*if(this.mat.length!=otra.mat.length){
			return false;
		}*/
		
		for(int x = 0; x < mat.length;x++){
			for(int y = 0; y < mat.length;y++){
				Integer actual = mat[x][y];
				Integer actual2 = otra.mat[x][y];
		
				
				if(actual == null || actual2 == null){
					if(!(actual == null && actual2 == null)){
						return false;
					}
				}
				else{
				if(!(actual.equals(actual2))){
					return false;
				}
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
	 * @return
	 */
	
	public void Move(direction dir, int filaoCol){
		int y,x,from,to,indexDir,indexAux = 0;
		
		boolean xaxis=false;
		boolean yaxis=false;
		Integer [] aux = new Integer [mat.length];
		Integer actualElem = null;
		
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
		
		indexAux=from;
		
		while(from!=to){
			
			actualElem = mat [y][x];
			if(actualElem != null){
				
				aux [indexAux] = actualElem;
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
	
	public void Shift(direction dir){
		for(int x=0;x<mat.length;x++){
				combineCells(dir,x);
				Move(dir,x);
		}
		
	}
	
	/**
	 * combina las celdas que tengan mismo numero, revisa de izquierda a derecha, buscando un numero identico a la celda actual
	 * una vez que los combina, pasa a buscar otra operacion
	 * @param dir la direccion en la que se mueve
	 * @param filaoCol la fila o columna sobre la que se opera
	 * @return booleano este indica si se realizo alguna combinacion para poder pasar a reubicar los resultados
	 */
	
	public void combineCells(direction dir,int filaoCol ){
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
		
		boolean comparable = true;         
		Integer bkp = null;					
		Integer Actual = null;   
		
		
			while(from!=to){
				
				comparable = true;		
				////
				Actual = mat[y][x];      
				/////
				
				if(Actual != null){

					
					
					if(xaxis){  //Si se mueve en el eje x
						
						if(bkp==null){   //SI recien inicia bk inicializa en el primer elemento no nulo
							bkp=x;
							comparable = false;   
						}	
						
						
						else if(!Actual.equals(mat[y][bkp])){      //SI EL ACTUAL ES DIFERENTE DEL BKPA
							bkp =y ;
							
							comparable = false;   
						}
						}
						
						if(yaxis){   //Si se mueve en el eje y
							if(bkp==null){  //SI recien inicia bk inicializa en el primer elemento no nulo
								bkp=y;
								comparable = false;   
							}
							
							else if(!Actual.equals(mat[bkp][x])){
								bkp = x;
								
								comparable = false;   
							}
							
							
						}
						
						if(xaxis){
						
						if(Actual.compareTo(mat[y][bkp])==0 && comparable){ 
							
							mat[y][bkp]*=2;
							mat[y][x] = null;
							bkp = null;					
						}	
					
				}
						
						if(yaxis){
							
							if(Actual.compareTo(mat[bkp][y])==0 && comparable){ 
								
								mat[bkp][y]*=2;
								mat[y][x] = null;
								bkp = null;					
							}	
						
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





}
