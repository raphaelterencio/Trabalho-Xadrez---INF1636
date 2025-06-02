package model;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;

public class BoardTest {

	private Board board;
	
	@Before
	public void setUp() { 
		board = new Board(); 
		board.launch();
	}
	
	@After
	public void tearDown() { board = null; }
	
	@Test
	public void testCheckRegular()
	{
        board.movePiece(1, 4, 3, 4); // Move peão preto para liberar rainha
        board.movePiece(6, 5, 5, 5); // Move peão branco para abrir caminho
        board.movePiece(0, 3, 4, 7); // Move rainha preta
        assertTrue(board.isCheck('W')); // Rei branco em xeque
	}

	@Test
    public void testCheckDiscovered() {
        board.movePiece(1, 4, 3, 4); // Move peão preto para liberar rainha
        board.movePiece(0, 3, 4, 7); // Move rainha preta
        board.movePiece(6, 5, 5, 5); // Move peão branco para abrir caminho
        assertTrue(board.isCheck('W')); // Rei branco em xeque descoberto
    }
    
    @Test
    public void testCheckMate() {
        // Movimentos para criar um xeque-mate básico
        board.movePiece(1, 4, 3, 4); // Move peão preto
        board.movePiece(6, 5, 5, 5); // Move peão branco
        board.movePiece(0, 3, 4, 7); // Move rainha preta
        board.movePiece(7, 6, 5, 5); // Move cavalo branco
        board.movePiece(0, 5, 3, 2); // Move bispo preto
        board.movePiece(7, 1, 5, 2); // Move cavalo branco
        board.movePiece(4, 7, 6, 5); // Move rainha preta para xeque-mate
        
        assertTrue(board.isCheckMate('W')); // Rei branco em xeque-mate
    }
    
    @Test
    public void testNotCheckMate() {
        // Movimentos para criar um xeque que não é mate
        board.movePiece(1, 4, 3, 4); // Move peão preto
        board.movePiece(6, 5, 5, 5); // Move peão branco
        board.movePiece(0, 3, 4, 7); // Move rainha preta
        
        assertTrue(board.isCheck('W')); // Rei branco em xeque
        assertFalse(board.isCheckMate('W')); // Mas não é xeque-mate
    }
    
    @Test
    public void testStalemate() {
        // Movimentos para criar um congelamento
        board.movePiece(1, 4, 3, 4); // Move peão preto
        board.movePiece(6, 5, 5, 5); // Move peão branco
        board.movePiece(0, 3, 4, 7); // Move rainha preta
        board.movePiece(7, 4, 6, 4); // Move rei branco
        board.movePiece(4, 7, 6, 6); // Move rainha preta para posição de congelamento
        
        assertFalse(board.isCheck('W')); // Rei branco não está em xeque
        assertTrue(board.isStalemate('W')); // Mas está em congelamento
    }
    
    @Test
    public void testNotStalemate() {
        // Movimentos para criar uma situação que não é congelamento
        board.movePiece(1, 4, 3, 4); // Move peão preto
        board.movePiece(6, 5, 5, 5); // Move peão branco
        board.movePiece(0, 3, 4, 7); // Move rainha preta
        board.movePiece(7, 4, 6, 4); // Move rei branco
        
        assertFalse(board.isCheck('W')); // Rei branco não está em xeque
        assertFalse(board.isStalemate('W')); // E não está em congelamento
    }
    
    @Test
    public void testKingsideCastle() {
        // Move peões para liberar o caminho do roque
        board.movePiece(6, 5, 5, 5); // Move peão branco
        board.movePiece(6, 6, 5, 6); // Move peão branco
        
        assertTrue(board.canCastle('W', true)); // Pode fazer roque curto
        
        board.performCastle('W', true);
        
        // Verifica se o rei e a torre estão nas posições corretas
        assertTrue(board.getPiece(7, 6) instanceof King); // Rei
        assertTrue(board.getPiece(7, 5) instanceof Rook); // Torre
    }
    
    @Test
    public void testQueensideCastle() {
        // Move peões para liberar o caminho do roque
        board.movePiece(6, 1, 5, 1); // Move peão branco
        board.movePiece(6, 2, 5, 2); // Move peão branco
        board.movePiece(6, 3, 5, 3); // Move peão branco
        
        assertTrue(board.canCastle('W', false)); // Pode fazer roque longo
        
        board.performCastle('W', false);
        
        // Verifica se o rei e a torre estão nas posições corretas
        assertTrue(board.getPiece(7, 2) instanceof King); // Rei
        assertTrue(board.getPiece(7, 3) instanceof Rook); // Torre
    }
    
    @Test
    public void testCannotCastleInCheck() {
        // Move peões para liberar o caminho do roque
        board.movePiece(6, 5, 5, 5); // Move peão branco
        board.movePiece(6, 6, 5, 6); // Move peão branco
        
        // Coloca o rei em xeque
        board.movePiece(1, 4, 3, 4); // Move peão preto
        board.movePiece(0, 3, 4, 7); // Move rainha preta
        
        assertFalse(board.canCastle('W', true)); // Não pode fazer roque em xeque
    }
    
    @Test
    public void testCannotCastleThroughCheck() {
        // Move peões para liberar o caminho do roque
        board.movePiece(6, 5, 5, 5); // Move peão branco
        board.movePiece(6, 6, 5, 6); // Move peão branco
        
        // Coloca uma peça que ataca o caminho do roque
        board.movePiece(1, 4, 3, 4); // Move peão preto
        board.movePiece(0, 5, 3, 2); // Move bispo preto
        
        assertFalse(board.canCastle('W', true)); // Não pode fazer roque através de xeque
    }
	
}
