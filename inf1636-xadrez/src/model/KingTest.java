package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class KingTest {

	King king = new King('W', 4, 4); // Centro do tabuleiro
	
	@Test
	public void testKingValidMove() 
	{
		assertTrue(king.canMove(5, 5));
	}
	
	@Test
	public void testKingInvalidMove()
	{
		assertFalse(king.canMove(2, 5));
	}

}
