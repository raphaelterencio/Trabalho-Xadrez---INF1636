package model;

import java.util.List;

public class ModelAPI
{
	static Board board;
	
	public ModelAPI() { }
	
	public static void newGame() { board = new Board(); }
	
	// Métodos get()
	
	public static char getPieceColor(int row, int column) { return board.getPieceColor(row, column); }
	
	public static char getPieceSymbol(int row, int column) { return board.getPieceSymbol(row, column); }
	
	public static char setGameState(String game_state) { return board.setGameState(game_state); }
	
	public static String getGameState() { return board.getGameState(); }
	
	// Peças
	
	public static boolean isThereAPiece(int row, int column) { return board.isThereAPiece(row, column); }
	
	public static boolean movePiece(int row, int column, int target_row, int target_column){ return board.movePiece(row, column, target_row, target_column); }
	
	// Movimentação
	
	public static List<int[]> getPossibleMoves(int row, int column) { return board.getPossibleMoves(row, column); }
	
	// Regras
	
	public static boolean isCheck(char color) { return board.isCheck(color); }
	
	public static boolean isCheckMate(char color){ return board.isCheckMate(color); }
	
	public static boolean isStaleMate(char color) { return board.isStaleMate(color); }
	
	public static boolean checkPawnPromotion(char color) { return board.checkPawnPromotion(color); }
	
	public static void promotePawn(String piece, int row, int column) { board.promotePawn(piece, row, column); }
	
	// Movimentos especiais
	
	public static boolean canCastle(char color, boolean isKingside) { return board.canCastle(color, isKingside); }
	
	public static void performCastle(char color, boolean isKingside) { board.performCastle(color, isKingside); }
	
}