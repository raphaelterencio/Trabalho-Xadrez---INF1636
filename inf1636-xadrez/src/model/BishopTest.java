package model;

import static org.junit.Assert.*;
import org.junit.Test;

public class BishopTest {

	Bishop bishop = new Bishop('W', 4, 4); // Centro do tabuleiro
	
	@Test
	public void testBishopValidMove() 
	{
		assertTrue(bishop.canMove(5, 5));
	}
	
	@Test
	public void testBishopInvalidMove()
	{
		assertFalse(bishop.canMove(4,5));
	}

}
