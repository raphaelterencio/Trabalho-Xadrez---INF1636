package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class RookTest {

	Rook rook = new Rook('W', 4, 4); // Centro do tabuleiro
	
	@Test
	public void testRookValidMove() 
	{
		assertTrue(rook.canMove(3, 4));
	}
	
	@Test
	public void testRookInvalidMove()
	{
		assertFalse(rook.canMove(2, 5));
	}

}
