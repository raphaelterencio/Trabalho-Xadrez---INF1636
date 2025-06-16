package testes;

import model.ModelAPI;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class RookTest {
	
	ModelAPI model_api = new ModelAPI();
	
	@Before
	public void setUpOnce()
	{
		model_api.newGame();
		model_api.testMode();
		model_api.testSetPiece('R', 'W', 4, 4); // Centro do tabuleiro
	}
	
	@Test
	public void testRookValidMove() 
	{
		assertTrue(model_api.movePiece(4, 4, 3, 4));
	}
	
	@Test
	public void testRookInvalidMove()
	{
		assertFalse(model_api.movePiece(4, 4, 2, 5));
	}

}