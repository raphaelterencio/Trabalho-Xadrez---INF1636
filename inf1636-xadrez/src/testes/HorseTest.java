package testes;

import model.ModelAPI;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class HorseTest {

	ModelAPI model_api = new ModelAPI();
	
	@Before
	public void setUpOnce()
	{
		model_api.newGame();
		model_api.testMode();
		model_api.testSetPiece('H', 'W', 4, 4); // Centro do tabuleiro
	}
	
	@Test
	public void testHorseValidMove() 
	{
		assertTrue(model_api.movePiece(4, 4, 2, 5));
	}
	
	@Test
	public void testHorseInvalidMove()
	{
		assertFalse(model_api.movePiece(4, 4, 4, 5));
	}
}