package testes;

import model.ModelAPI;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class QueenTest 
{
	@Before
	public void setUp()
	{
		ModelAPI.newGame();
		ModelAPI.testMode();
		ModelAPI.testSetPiece('Q', 'W', 4, 4); // Centro do tabuleiro
	}
	
	@Test
	public void testQueenValidMove() 
	{
		assertTrue(ModelAPI.movePiece(4, 4, 3, 4));
	}
	
	@Test
	public void testQueenInvalidMove()
	{
		assertFalse(ModelAPI.movePiece(4, 4, 2, 5));
	}
}