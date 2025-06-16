package model;

import java.util.List;

public class ModelAPI
{
	static Board board;
	
	public ModelAPI() {}
	
	public void newGame() { board = new Board(); }
	
	// Testes
	
	public void testMode() { board.TestSetUp(); }
	
	public void testSetPiece(char type, char color, int row, int column) { board.TestSetPiece(type, color, row, column); }
		
	// Métodos get()
	
	public char getPieceColor(int row, int column) { return board.getPieceColor(row, column); }
	
	public char getPieceSymbol(int row, int column) { return board.getPieceSymbol(row, column); }
	
	// Peças
	
	public boolean isThereAPiece(int row, int column) { return board.isThereAPiece(row, column); }
	
	public boolean movePiece(int row, int column, int target_row, int target_column) { return board.movePiece(row, column, target_row, target_column); }
	
	// Movimentação
	
	public List<int[]> getPossibleMoves(int row, int column) { return board.getPossibleMoves(row, column); }
	
	// Regras
	
	public boolean isCheck(char color) { return board.isCheck(color); }
	
	public boolean isCheckMate(char color) { return board.isCheckMate(color); }
	
	public boolean isStaleMate(char color) { return board.isStaleMate(color); }
	
	public boolean checkPawnPromotion() { return board.checkPawnPromotion(); }
	
	// Movimentos especiais
	
	public boolean canCastle(char color, boolean isKingside) { return board.canCastle(color, isKingside); }
	
	public void performCastle(char color, boolean isKingside) { board.performCastle(color, isKingside); }
	
}