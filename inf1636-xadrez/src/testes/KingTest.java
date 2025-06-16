package testes;

import model.ModelAPI;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class KingTest 
{	
	@Before
	public void setUpOnce()
	{
		ModelAPI.newGame();
		ModelAPI.testMode();
		ModelAPI.testSetPiece('K', 'W', 4, 4); // Centro do tabuleiro
	}
	
	@Test
	public void testKingValidMove() 
	{
		assertTrue(ModelAPI.movePiece(4, 4, 5, 5));
	}
	
	@Test
	public void testKingInvalidMove()
	{
		assertFalse(ModelAPI.movePiece(4, 4, 2, 5));
	}
}