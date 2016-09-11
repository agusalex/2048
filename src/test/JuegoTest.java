package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

import gui.Game;
import paquete.Direction;
import paquete.Jugador;

public class JuegoTest {

	@Test
	public void gameTest() {
		Game game = new Game();
		assertTrue(game.getJugador().equals(new Jugador("Default")));
		assertEquals(0,game.getJugador().getMovements());
		assertEquals(0,game.getJugador().getRecord());
		game.getJugador().madeMovement();
		game.getJugador().increaseScore(2000);
		assertEquals(1,game.getJugador().getMovements());
		assertEquals(2000,game.getJugador().getRecord());
		game.increaseScore(34);
		game.increaseScore(1);
		assertEquals(35,game.getScore());
	}
	
	@Test
	public void gameFullTest(){
		Game game = new Game();
		System.out.println(game.getMatJuego());
	
		boolean over = false;
		Direction opcion ;
		Direction[] opciones={Direction.UP,Direction.DOWN,Direction.LEFT,Direction.RIGHT};
		Random r=new Random();
		int indice;

		while(!over){

			indice=r.nextInt(opciones.length);
			opcion =opciones[indice];
			
			System.out.println(opcion);
		
			game.play(opcion);
			System.out.println(game.getMatJuego());
			if(game.gameOver()){
				over = true;
			
			}
	    }
	}

}
