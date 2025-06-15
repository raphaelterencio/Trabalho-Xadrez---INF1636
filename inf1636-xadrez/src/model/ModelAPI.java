package model;

import java.util.List;

public class ModelAPI
{
	Game game;
	
	public ModelAPI() {}
	
	public void newGame() { game = new Game(); }
	
	public char getRoundColor() { return game.getRoundColor(); }
	
	public boolean movePiece(int row, int column, int target_row, int target_column){ return game.movePiece(row, column, target_row, target_column); }
	
	public List<int[]> getPossibleMoves(int row, int column){ return game.getPossibleMoves(row, column); }
	
	public boolean isCheck() { return game.isCheck(game.getRoundColor()); } 
	
	public boolean isCheckMate() { return game.isCheckMate(game.getRoundColor()); } 
	
	public boolean checkPawnPromotion() { return game.checkPawnPromotion(); }
	
	public boolean isStalemate() { return game.isStalemate(getRoundColor()); } 
	
	public void testGame(char round_color) { game.testMode(round_color); }
	
	public void setPiece(char type, char color, int row, int column) { game.setPiece(type, color, row, column); } 
	
	// 
	
	public char getPieceColor(int row, int column) { return game.getPieceColor(row, column); }
	
	public boolean isThereAPiece(int row, int column) { return game.isThereAPiece(row, column); }
	
	public char getPieceSymbol(int row, int column) { return game.getPieceSymbol(row, column); }
}