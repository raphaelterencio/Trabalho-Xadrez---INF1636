package testes;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Queen;

public class QueenTest {

	Queen queen = new Queen('W', 4, 4); // Centro do tabuleiro
	
	@Test
	public void testQueenValidMove() 
	{
		assertTrue(queen.canMove(3, 4));
	}
	
	@Test
	public void testQueenInvalidMove()
	{
		assertFalse(queen.canMove(2, 5));
	}
}
