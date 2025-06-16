package testes;

import static org.junit.Assert.*;
import org.junit.Test;
import model.ModelAPI;
import org.junit.Before;

public class BoardTest {
	ModelAPI api = new ModelAPI();
	
	@Before
	public void setUp() { 
		api.newGame();
		api.testGame('W');
	}
	
	@Test
	public void testCheckRegular()
	{
		api.setPiece('P', 'W', 1, 4);
        api.setPiece('P', 'W', 6, 5);
        api.setPiece('Q', 'B', 0, 3);
        
        api.movePiece(1, 4, 3, 4);
        api.movePiece(6, 5, 5, 5);
        api.movePiece(0, 3, 4, 7);
        
        assertTrue(api.isCheck());
	}

	@Test
    public void testCheckDiscovered() {
        api.setPiece('K', 'W', 7, 4);  // Rei branco
        api.setPiece('B', 'B', 0, 5);  // Bispo preto
        api.setPiece('P', 'W', 6, 5);  // Peão branco bloqueando
        
        api.movePiece(6, 5, 5, 5);     // Move o peão, descobrindo o xeque
        
        assertTrue(api.isCheck());
    }
    
    @Test
    public void testCheckMate() {
        api.setPiece('K', 'W', 7, 7);  // Rei branco no canto
        api.setPiece('R', 'B', 7, 6);  // Torre preta
        api.setPiece('Q', 'B', 6, 7);  // Rainha preta
        
        assertTrue(api.isCheck());
        assertTrue(api.isCheckMate());
    }
    
    @Test
    public void testNotCheckMate() {
        api.setPiece('P', 'B', 1, 4);
        api.setPiece('P', 'W', 6, 5);
        api.setPiece('Q', 'B', 0, 3);
        
        api.movePiece(1, 4, 3, 4);
        api.movePiece(6, 5, 5, 5);
        api.movePiece(0, 3, 4, 7);
        
        assertTrue(api.isCheck());
        assertFalse(api.isCheckMate());
    }
    
    @Test
    public void testStalemate() {
        // Configuração mais simples para afogamento
        api.setPiece('K', 'W', 7, 7);  // Rei branco no canto
        api.setPiece('Q', 'B', 5, 6);  // Rainha preta bloqueando movimentos
        
        assertFalse(api.isCheck());
        assertTrue(api.isStalemate());
    }
    
    @Test
    public void testNotStalemate() {
        api.setPiece('P', 'B', 1, 4);
        api.setPiece('P', 'W', 6, 5);
        api.setPiece('Q', 'B', 0, 3);
        api.setPiece('K', 'W', 7, 4);
        
        api.movePiece(1, 4, 3, 4);
        api.movePiece(6, 5, 5, 5);
        api.movePiece(0, 3, 4, 7);
        api.movePiece(7, 4, 6, 4);
        
        assertFalse(api.isCheck());
        assertFalse(api.isStalemate());
    }

    @Test
    public void testKingsideCastle() {
        // Coloca as peças necessárias
        api.setPiece('K', 'W', 7, 4);  // Rei branco
        api.setPiece('R', 'W', 7, 7);  // Torre branca
        api.setPiece('P', 'W', 6, 5);  // Peão branco
        api.setPiece('P', 'W', 6, 6);  // Peão branco
        
        // Move os peões para liberar o caminho
        api.movePiece(6, 5, 5, 5);     // Move peão
        api.movePiece(6, 6, 5, 6);     // Move peão
        
        assertTrue(api.canCastle('W', true));  // Verifica se pode fazer roque curto
        api.performCastle('W', true);          // Faz o roque
    }
    
    @Test
    public void testQueensideCastle() {
        // Coloca as peças necessárias
        api.setPiece('K', 'W', 7, 4);  // Rei branco
        api.setPiece('R', 'W', 7, 0);  // Torre branca
        api.setPiece('P', 'W', 6, 1);  // Peão branco
        api.setPiece('P', 'W', 6, 2);  // Peão branco
        api.setPiece('P', 'W', 6, 3);  // Peão branco
        
        // Move os peões para liberar o caminho
        api.movePiece(6, 1, 5, 1);     // Move peão
        api.movePiece(6, 2, 5, 2);     // Move peão
        api.movePiece(6, 3, 5, 3);     // Move peão
        
        assertTrue(api.canCastle('W', false)); // Verifica se pode fazer roque longo
        api.performCastle('W', false);         // Faz o roque
    }
    
    @Test
    public void testCannotCastleInCheck() {
        api.setPiece('K', 'W', 7, 4);
        api.setPiece('R', 'W', 7, 7);
        api.setPiece('Q', 'B', 0, 4);  // Rainha preta diretamente na frente do rei
        
        assertTrue(api.isCheck());  // Verifica se está em xeque
        assertFalse(api.canCastle('W', true));
    }
    
    @Test
    public void testCannotCastleThroughCheck() {
        api.setPiece('K', 'W', 7, 4);
        api.setPiece('R', 'W', 7, 7);
        api.setPiece('B', 'B', 0, 5);  // Bispo preto
        
        api.movePiece(0, 5, 3, 2);     // Move o bispo para atacar o caminho do roque
        
        assertFalse(api.canCastle('W', true));
    }
}
