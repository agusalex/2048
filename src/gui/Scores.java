package gui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import paquete.Jugador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.LinkedList;


public class Scores implements Serializable{

    private static final long serialVersionUID = 2L;

     LinkedList<Jugador> records;

    public Scores(){
        records = Game.Jugadores;
    }

    public void generarJSON(String archivo){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(this);
        try{
            FileWriter writer = new FileWriter(archivo);
            writer.write(json);
            writer.close();
        }
        catch(Exception e) {
            e.printStackTrace ( );
        }
    }


    public static Scores leerJSON(String archivo) {
        Gson gson = new Gson();
        Scores ret = null;

        try {

            BufferedReader br = new BufferedReader (new FileReader (archivo));
            ret = gson.fromJson (br, Scores.class);
        }
        catch(Exception e){
            e.printStackTrace ();
        }

        return ret;
    }



    public static void main(String[] args){

        Jugador j1 = new Jugador ("juan");
        j1.setScore (200);
        j1.madeMovement ();
        Jugador j2 = new Jugador ("jorge");
        j2.setScore (300);
        j2.madeMovement ();
        Jugador j3 = new Jugador ("roverto");
        j3.setScore (400);
        j3.madeMovement ();
        Jugador j4 = new Jugador ("gus");
        j4.setScore (1000);
        j4.madeMovement ();
        Jugador j5 = new Jugador ("tony");
        j5.setScore (800);
        j5.madeMovement ();
        j5.madeMovement ();
        Jugador j6 = new Jugador ("paul");
        j6.setScore (9000);
        j6.madeMovement ();
        Jugador j8 = new Jugador ("angel");
        j8.setScore (800);
        j8.madeMovement ();
        Jugador j9 = new Jugador ("alfredo");
        j9.setScore (600);
        j9.madeMovement ();
        Jugador j10 = new Jugador ("mat");
        j10.setScore (500);
        j10.madeMovement ();


        LinkedList<Jugador> list = new LinkedList<Jugador>();
        list.add (j1);
        list.add (j2);
        list.add (j3);
        list.add (j4);
        list.add (j5);
        list.add (j6);
        list.add (j8);
        list.add (j9);
        list.add (j10);


        Scores puntos = new Scores();
        puntos.records = list;
        puntos.generarJSON("high scores.txt");
        Scores lista = leerJSON ("high scores.txt");
        System.out.println(lista.records);

    }

}
