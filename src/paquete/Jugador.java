package paquete;

import java.io.Serializable;
import java.util.LinkedList;


public class Jugador implements Comparable , Serializable{

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

    private int getMovements() {
        return this.movements;
    }

    @Override
    public int compareTo(Object jugador) {
        Jugador j2;
        if (jugador instanceof Jugador) {
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





    private static Jugador bestScore(LinkedList<Jugador> Jugadores){
        Jugador best = new Jugador("best");
        Jugador current;
        for (Jugador Jugadore : Jugadores) {
            current = Jugadore;
            if (best.compareTo(current) == -1)
                best.copy(current);
        }
        return best;
    }


    private static void sortList(LinkedList<Jugador> lista, LinkedList<Jugador> Jugadores, int size){
        if(lista.size() == size)
            return;

        Jugador minimo = bestScore(Jugadores);
        Jugadores.remove (minimo);
        lista.add(minimo);
        sortList(lista,Jugadores,size);
    }



    public void setName(String namee) {
        this.name=namee;
    }

    public String getName() {
        return name;
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



































}

