package testes;

import model.ModelAPI;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class RookTest 
{
	@Before
	public void setUpOnce()
	{
		ModelAPI.newGame();
		ModelAPI.testMode();
		ModelAPI.testSetPiece('R', 'W', 4, 4); // Centro do tabuleiro
	}
	
	@Test
	public void testRookValidMove() 
	{
		assertTrue(ModelAPI.movePiece(4, 4, 3, 4));
	}
	
	@Test
	public void testRookInvalidMove()
	{
		assertFalse(ModelAPI.movePiece(4, 4, 2, 5));
	}

}