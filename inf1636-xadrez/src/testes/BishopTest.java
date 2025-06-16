package testes;

import model.ModelAPI;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class BishopTest 
{
	
	@Before
	public void setUpOnce()
	{
		ModelAPI.newGame();
		ModelAPI.testMode();
		ModelAPI.testSetPiece('B', 'W', 4, 4); // Centro do tabuleiro
	}
	
	@Test
	public void testBishopValidMove() 
	{
		assertTrue(ModelAPI.movePiece(4, 4, 5, 5));
	}
	
	@Test
	public void testBishopInvalidMove()
	{
		assertFalse(ModelAPI.movePiece(4, 4, 4, 5));
	}
}