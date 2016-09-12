package paquete;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.LinkedList;


public class Jugador implements Serializable,Comparable {

    private static final long serialVersionUID = 1L;
    private String name;
    private int record = 0, movements;


    public Jugador(String name) {
        this.name = name;
    }
    public Jugador() {
        this.name = "Default";
    }

    public void madeMovement() {
        this.movements++;
    }

    public void setScore(int record) {
        if (record < 0)
            throw new IllegalArgumentException("Invalid score for user");
        this.record = record;
    }


    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object jugador) {
        Jugador j2;
        if (jugador == null)
            return false;
        else if (jugador instanceof Jugador) {
            j2 = (Jugador) jugador;
            if (j2.name == null)
                return false;
            else if (j2.name.equals(this.name) && j2.movements == this.movements && j2.record == this.record)
                return true;
        }
        return false;

    }

    public int getRecord() {
        return this.record;
    }

    public int getMovements() {
        return this.movements;
    }

    @Override
    public int compareTo(Object jugador) {
        Jugador j2;
        if (jugador == null)
            return 1;
        else if (jugador instanceof Jugador) {
            j2 = (Jugador) jugador;
            if (j2.name == null)
                return 1;
            else if (j2.record > this.record)
                return -1;
            else if (j2.record == this.record) {
                    if (j2.getMovements() < this.getMovements())
                        return -1;
                    if (j2.getMovements() == this.getMovements())
                        return 0;
                else return 1;
            } else
                return 1;
        }
        return 0;
    }


    public void copy(Jugador player){
        this.name = player.name;
        this.record = player.record;
        this.movements = player.movements;
    }


    private void generarJSON() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(this);

        try {

            FileWriter writer = new FileWriter("El esteban.txt");
            writer.write(json);
            writer.close();
            System.out.println("a");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static Jugador leerJSON() {

        Gson gson = new Gson();
        Jugador ret = null;

        try {
            BufferedReader br = new BufferedReader(new FileReader("El esteban.txt"));
            ret = gson.fromJson(br, Jugador.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }


    public static Jugador bestScore(LinkedList<Jugador> Jugadores){
        Jugador best = new Jugador("best");
        Jugador current = null;
        for(int index = 0; index < Jugadores.size (); index++){
            current = Jugadores.get(index);
            if(best.compareTo (current) == -1)
                best.copy (current);
        }
        return best;
    }


    public static void sortList(LinkedList<Jugador> lista,LinkedList<Jugador> Jugadores, int size){
        if(lista.size() == size)
            return;

        Jugador minimo = bestScore(Jugadores);
        Jugadores.remove (minimo);
        lista.add(minimo);
        sortList(lista,Jugadores,size);
    }




    public static void main(String[] args) {
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

        System.out.println(list);

        System.out.println(bestScore(list));
        LinkedList<Jugador> sorted = new LinkedList<Jugador> ();
        sortList (sorted,list,list.size());
        System.out.println(sorted);
    }



























      /*  Jugador j1 = new Jugador("esteban quito");
        Jugador j2 = new Jugador("dolores de barriga");
        j1.setScore (2000);
        j2.setScore (3000);
        for (int i = 0; i < 10; i++) {
            j1.madeMovement();
            if (i % 2 == 0)
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
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            FileInputStream fis = new FileInputStream("jugador.txt");
            ObjectInputStream in = new ObjectInputStream(fis);

            j1 = (Jugador) in.readObject();
            j2 = (Jugador) in.readObject();
            System.out.println(j1);
            System.out.println(j2);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        j1.generarJSON();
        j2 = leerJSON();
        System.out.println(j2);
    }
    */

    public void setName(String namee) {
        this.name=namee;
    }

    public String getName() {
        return name;
    }




}

