package testes;

import model.ModelAPI;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class KingTest {
	
	ModelAPI model_api = new ModelAPI();
	
	@Before
	public void setUpOnce()
	{
		model_api.newGame();
		model_api.testGame('W');
		model_api.setPiece('K', 'W', 4, 4); // Centro do tabuleiro
	}
	
	@Test
	public void testKingValidMove() 
	{
		assertTrue(model_api.movePiece(4, 4, 5, 5));
	}
	
	@Test
	public void testKingInvalidMove()
	{
		assertFalse(model_api.movePiece(4, 4, 2, 5));
	}
}