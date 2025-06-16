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
		model_api.testMode();
		model_api.testSetPiece('Q', 'W', 4, 4); // Centro do tabuleiro
	}
	
	@Test
	public void testQueenValidMove() 
	{
		assertTrue(model_api.movePiece(4, 4, 3, 4));
	}
	
	@Test
	public void testQueenInvalidMove()
	{
		assertFalse(model_api.movePiece(4, 4, 2, 5));
	}
}