package testes;

import static org.junit.Assert.*;
import org.junit.Test;

import model.ModelAPI;
import org.junit.Before;

public class BoardTest 
{
	
	@Before
	public void setUp() { 
		ModelAPI.newGame();
		ModelAPI.testMode();
	}
	
	@Test
	public void testCheckRegular()
	{
		ModelAPI.testSetPiece('P', 'W', 1, 4);
        ModelAPI.testSetPiece('P', 'W', 6, 5);
        ModelAPI.testSetPiece('Q', 'B', 0, 3);
        
        ModelAPI.movePiece(1, 4, 3, 4);
        ModelAPI.movePiece(6, 5, 5, 5);
        ModelAPI.movePiece(0, 3, 4, 7);
        
        assertTrue(ModelAPI.isCheck('W')); 
	}

	@Test
    public void testCheckDiscovered() {
        ModelAPI.testSetPiece('K', 'W', 7, 4);  // Rei branco
        ModelAPI.testSetPiece('B', 'B', 0, 5);  // Bispo preto
        ModelAPI.testSetPiece('P', 'W', 6, 5);  // Peão branco bloqueando
        
        ModelAPI.movePiece(6, 5, 5, 5);     // Move o peão, descobrindo o xeque
        
        assertTrue(ModelAPI.isCheck('W')); 
    }
    
    @Test
    public void testCheckMate() {
        ModelAPI.testSetPiece('K', 'W', 7, 7);  // Rei branco no canto
        ModelAPI.testSetPiece('R', 'B', 7, 6);  // Torre preta
        ModelAPI.testSetPiece('Q', 'B', 6, 7);  // Rainha preta
        
        assertTrue(ModelAPI.isCheck('W')); 
        assertTrue(ModelAPI.isCheckMate('W')); 
    }
    
    @Test
    public void testNotCheckMate() {
        ModelAPI.testSetPiece('P', 'B', 1, 4);
        ModelAPI.testSetPiece('P', 'W', 6, 5);
        ModelAPI.testSetPiece('Q', 'B', 0, 3);
        
        ModelAPI.movePiece(1, 4, 3, 4);
        ModelAPI.movePiece(6, 5, 5, 5);
        ModelAPI.movePiece(0, 3, 4, 7);
        
        assertTrue(ModelAPI.isCheck('W')); 
        assertFalse(ModelAPI.isCheckMate('W')); 
    }
    
    @Test
    public void testStalemate() {
        // Configuração mais simples para afogamento
        ModelAPI.testSetPiece('K', 'W', 7, 7);  // Rei branco no canto
        ModelAPI.testSetPiece('Q', 'B', 5, 6);  // Rainha preta bloqueando movimentos
        
        assertFalse(ModelAPI.isCheck('W')); 
        assertTrue(ModelAPI.isStaleMate('W')); 
    }
    
    @Test
    public void testNotStalemate() {
        ModelAPI.testSetPiece('P', 'B', 1, 4);
        ModelAPI.testSetPiece('P', 'W', 6, 5);
        ModelAPI.testSetPiece('Q', 'B', 0, 3);
        ModelAPI.testSetPiece('K', 'W', 7, 4);
        
        ModelAPI.movePiece(1, 4, 3, 4);
        ModelAPI.movePiece(6, 5, 5, 5);
        ModelAPI.movePiece(0, 3, 4, 7);
        ModelAPI.movePiece(7, 4, 6, 4);
        
        assertFalse(ModelAPI.isCheck('W')); 
        assertFalse(ModelAPI.isStaleMate('W')); 
    }

    @Test
    public void testKingsideCastle() {
        // Coloca as peças necessárias
        ModelAPI.testSetPiece('K', 'W', 7, 4);  // Rei branco
        ModelAPI.testSetPiece('R', 'W', 7, 7);  // Torre branca
        ModelAPI.testSetPiece('P', 'W', 6, 5);  // Peão branco
        ModelAPI.testSetPiece('P', 'W', 6, 6);  // Peão branco
        
        // Move os peões para liberar o caminho
        ModelAPI.movePiece(6, 5, 5, 5);     // Move peão
        ModelAPI.movePiece(6, 6, 5, 6);     // Move peão
        
        assertTrue(ModelAPI.canCastle('W', true));  // Verifica se pode fazer roque curto
        ModelAPI.performCastle('W', true);          // Faz o roque
    }
    
    @Test
    public void testQueensideCastle() {
        // Coloca as peças necessárias
        ModelAPI.testSetPiece('K', 'W', 7, 4);  // Rei branco
        ModelAPI.testSetPiece('R', 'W', 7, 0);  // Torre branca
        ModelAPI.testSetPiece('P', 'W', 6, 1);  // Peão branco
        ModelAPI.testSetPiece('P', 'W', 6, 2);  // Peão branco
        ModelAPI.testSetPiece('P', 'W', 6, 3);  // Peão branco
        
        // Move os peões para liberar o caminho
        ModelAPI.movePiece(6, 1, 5, 1);     // Move peão
        ModelAPI.movePiece(6, 2, 5, 2);     // Move peão
        ModelAPI.movePiece(6, 3, 5, 3);     // Move peão
        
        assertTrue(ModelAPI.canCastle('W', false)); // Verifica se pode fazer roque longo
        ModelAPI.performCastle('W', false);         // Faz o roque
    }
    
    @Test
    public void testCannotCastleInCheck() {
        ModelAPI.testSetPiece('K', 'W', 7, 4);
        ModelAPI.testSetPiece('R', 'W', 7, 7);
        ModelAPI.testSetPiece('Q', 'B', 0, 4);  // Rainha preta diretamente na frente do rei
        
        assertTrue(ModelAPI.isCheck('W'));  // Verifica se está em xeque
        assertFalse(ModelAPI.canCastle('W', true));
    }
    
    @Test
    public void testCannotCastleThroughCheck() {
        ModelAPI.testSetPiece('K', 'W', 7, 4);
        ModelAPI.testSetPiece('R', 'W', 7, 7);
        ModelAPI.testSetPiece('B', 'B', 0, 5);  // Bispo preto
        
        ModelAPI.movePiece(0, 5, 3, 2);     // Move o bispo para atacar o caminho do roque
        
        assertFalse(ModelAPI.canCastle('W', true));
    }
}