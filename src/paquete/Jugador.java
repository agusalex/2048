package paquete;

public class Jugador implements Exportable{
	private String name;
	private int record, movements;
	
	
	public Jugador(String name){
		this.name = name;
	}
	
	public void madeMovement(){
		this.movements++;
	}
	
	public void increaseScore(int record){
		if(record < 0)
			throw new IllegalArgumentException("Invalid score for user");
		this.record += record;
	}
	
	@Override
	public String obtainStatus(){
		return "Nombre: "+this.name+" Puntuacion: "+this.record+" Cant. movimientos: "+ this.movements;
	}
	
	@Override 
	public String toString(){
		return this.obtainStatus();
	}
	
}

