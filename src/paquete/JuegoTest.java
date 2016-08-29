package paquete;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

public class JuegoTest {

	@Test
	public void gameTest() {
		Juego game = new Juego("Esteban Quito");
		assertTrue(game.getJugador().equals(new Jugador("Esteban Quito")));
		assertEquals(0,game.getJugador().getMovements());
		assertEquals(0,game.getJugador().getRecord());
		game.getJugador().madeMovement();
		game.getJugador().increaseScore(2000);
		assertEquals(1,game.getJugador().getMovements());
		assertEquals(2000,game.getJugador().getRecord());
		game.increaseScore(34);
		game.increaseScore(1);
		assertEquals(35,game.getRecord());
	}
	
	@Test
	public void gameFullTest(){
		Juego game = new Juego("Dolores de Barriga");
		System.out.println(game.getMatJuego());
		Scanner scan = new Scanner(System.in);
		boolean over = false, correct = false;
		char opcion ;
		while(!over){
			System.out.println("Ingrese A para izquierda\n "
								+ "D para derecha\n "
								+ "S para abajo\n "
								+ "W para arriba");
			opcion = scan.nextLine().charAt(0);
			if (!(opcion == 'a' || opcion == 'A' || opcion == 'W' || opcion == 'w' || opcion == 'D' 
					|| opcion == 'd' || opcion == 'S' || opcion == 's'))
				correct = false;
			else 
				correct = true;
				
			while(!correct){
				System.out.println("Ingrese A para izquierda\n "
						+ "D para derecha\n "
						+ "S para abajo\n "
						+ "W para arriba");
				opcion = scan.nextLine().charAt(0);
				if (!(opcion == 'a' || opcion == 'A' || opcion == 'W' || opcion == 'w' || opcion == 'D' 
						|| opcion == 'd' || opcion == 'S' || opcion == 's'))
					correct = false;
				else 
					correct = true;
			}
			game.play(opcion);
			System.out.println(game.getMatJuego());
			if(game.gameOVer()){
				over = true;
				scan.close();
			}
	    }
	}

}
