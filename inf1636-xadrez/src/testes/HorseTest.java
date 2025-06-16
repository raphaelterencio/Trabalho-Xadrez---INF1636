package testes;

import model.ModelAPI;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class HorseTest 
{
	@Before
	public void setUpOnce()
	{
		ModelAPI.newGame();
		ModelAPI.testMode();
		ModelAPI.testSetPiece('H', 'W', 4, 4); // Centro do tabuleiro
	}
	
	@Test
	public void testHorseValidMove() 
	{
		assertTrue(ModelAPI.movePiece(4, 4, 2, 5));
	}
	
	@Test
	public void testHorseInvalidMove()
	{
		assertFalse(ModelAPI.movePiece(4, 4, 4, 5));
	}
}