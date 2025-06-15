package testes;

import model.ModelAPI;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class QueenTest {

	ModelAPI model_api = new ModelAPI();
	
	@Before
	public void setUp()
	{
		model_api.newGame();
		model_api.testGame('W');
		model_api.setPiece('Q', 'W', 4, 4); // Centro do tabuleiro
	}
	
	@Test
	public void testQueenValidMove() 
	{
		System.out.println("\n> Teste movimento válido");
		assertTrue(model_api.movePiece(4, 4, 3, 4));
	}
	
	@Test
	public void testQueenInvalidMove()
	{
		System.out.println("\n> Teste movimento inválido");
		assertFalse(model_api.movePiece(4, 4, 2, 5));
	}
}