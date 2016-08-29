package paquete;

import java.io.*;


public class Jugador implements Exportable, Comparable{
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
		this.record = record;
	}
	
	@Override
	public String obtainStatus(){
		return "Nombre: "+this.name+" Puntuacion: "+this.record+" Cant. movimientos: "+ this.movements;
	}
	
	@Override 
	public String toString(){
		return this.obtainStatus();
	}
	
	@Override 
	public boolean equals(Object jugador){
		Jugador j2 = null;
		if(jugador == null)
			return false;
		else if(jugador instanceof Jugador){
			j2 = (Jugador) jugador;
			if(j2.name == null)
				return false;
			else if(j2.name.equals(this.name) && j2.movements == this.movements && j2.record == this.record)
				return true;
		}
		return false;
		
	}

	public int getRecord(){
		return this.record;
	}
	
	public int getMovements(){
		return this.movements;
	}

	@Override
	public int compareTo(Object jugador) {
		Jugador j2 = null;
		if(jugador == null)
			return 1;
		else if(jugador instanceof Jugador){
			j2 = (Jugador) jugador;
			if(j2.name == null)
				return 1;
			else if(j2.record > this.record)
				return -1;
			else if(j2.record == this.record){
				if(j2.getMovements() < this.getMovements())
					return -1;
				if(j2.getMovements() == this.getMovements())
					return 0;
				else return  1;
			}
			else
				return 1;
		}
		return 0;
	}
	
}

