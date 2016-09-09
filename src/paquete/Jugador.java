package paquete;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

 
public class Jugador implements Serializable{
	
	private static final long serialVersionUID = 1L;
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
	public String toString(){
		return "Nombre: "+this.name+" Puntuacion: "+this.record+" Cant. movimientos: "+ this.movements;
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
	
	
	public void generarJSON(String archivo){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(this);
		
		try{
		
		 FileWriter writer = new FileWriter(archivo);
		 writer.write(json);
		 writer.close();
		 System.out.println("a");
		 }
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	public static Jugador leerJSON(String archivo){

	 Gson gson = new Gson();
	 Jugador ret = null;
	
	try{
	    BufferedReader br = new BufferedReader(new FileReader(archivo));
	    ret = gson.fromJson(br, Jugador.class);
	}
	 catch (Exception e){
		 e.printStackTrace();
	 }
	 return ret;
	}
	
	
	
	public static void main(String [] args){
		Jugador j1 = new Jugador("esteban quito");
		Jugador j2 = new Jugador("dolores de barriga");
		j1.increaseScore(2000);
		j2.increaseScore(3000);
		for(int i = 0; i< 10; i++){
			j1.madeMovement();
			if(i %2 == 0)
				j2.madeMovement();
		}
		
		try {
			//
			//para obtener la direccion de los proyectos
			File file = new File(".");
			System.out.println(file.getAbsolutePath());
			//
			
			
			FileOutputStream fos = new FileOutputStream("jugador.txt");
			ObjectOutputStream oup = new ObjectOutputStream(fos);
			
			oup.writeObject(j1);
			oup.writeObject(j2);
			oup.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		
		try{
			FileInputStream fis = new FileInputStream("jugador.txt");
			ObjectInputStream in = new ObjectInputStream(fis);
			
			j1 = (Jugador) in.readObject();
			j2 = (Jugador) in.readObject();
			System.out.println(j1);
			System.out.println(j2);
			in.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		j1.generarJSON("El esteban.txt");
		j2 = leerJSON("El esteban.txt");
		System.out.println(j2);
	}
	
}

