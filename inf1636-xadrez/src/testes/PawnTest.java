package testes;

import model.ModelAPI;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class PawnTest {

	ModelAPI model_api = new ModelAPI();
	
	@Before
	public void setUpOnce()
	{
		model_api.newGame();
		model_api.testGame('W');
		model_api.setPiece('P', 'W', 4, 4); // Centro do tabuleiro
	}
	
	@Test
	public void testPawnValidMove() 
	{
		assertTrue(model_api.movePiece(4, 4, 3, 4));
	}
	
	@Test
	public void testPawnInvalidMove()
	{
		assertFalse(model_api.movePiece(4, 4, 4, 5));
	}
}