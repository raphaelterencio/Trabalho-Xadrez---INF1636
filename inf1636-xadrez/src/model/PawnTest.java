package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class PawnTest {

	Pawn pawn = new Pawn('W', 4, 4); // Centro do tabuleiro
	
	@Test
	public void testPawnValidMove() 
	{
		assertTrue(pawn.canMove(3, 4));
	}
	
	@Test
	public void testPawnInvalidMove()
	{
		assertFalse(pawn.canMove(4,5));
	}

}
