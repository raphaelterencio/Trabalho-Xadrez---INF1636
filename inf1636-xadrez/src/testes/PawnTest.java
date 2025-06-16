package testes;

import model.ModelAPI;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class PawnTest 
{
	@Before
	public void setUpOnce()
	{
		ModelAPI.newGame();
		ModelAPI.testMode();
		ModelAPI.testSetPiece('P', 'W', 4, 4); // Centro do tabuleiro
	}
	
	@Test
	public void testPawnValidMove() 
	{
		assertTrue(ModelAPI.movePiece(4, 4, 3, 4));
	}
	
	@Test
	public void testPawnInvalidMove()
	{
		assertFalse(ModelAPI.movePiece(4, 4, 4, 5));
	}
}