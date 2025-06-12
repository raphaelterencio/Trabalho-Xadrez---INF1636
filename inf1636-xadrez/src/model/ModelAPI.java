package model;

import java.util.List;

// Restringe o acesso do usuário a comandos estratégicos
public class ModelAPI
{
	private Board board;
	
	// Board
	
	public ModelAPI() { board = new Board(); }
	
	public void startGame() { board.launch(); }
	
	public boolean isThereAPiece(int row, int column) { return board.isThereAPiece(row, column); }
	
	public void capturePiece(int row, int column) { board.capturePiece(row, column); }
	
	// Piece
	
	public List<int[]> getPossibleMoves(int row, int column)
	{
		Piece piece = board.getPiece(row, column);
		return piece.getPossibleMoves();
	}
	
	public char getPieceColor(int row, int column)
	{
		Piece piece = board.getPiece(row, column);
		return piece.getColor();
	}
	
	// Board e piece
	
	public void movePiece(int row, int column, int target_row, int target_column)
	{
		Piece piece = board.getPiece(row, column);
		piece.setPos(target_row, target_column);
		board.movePiece(row, column, target_row, target_column);
	}
	public char getPieceSymbol(int row, int column) {
	    return board.getPiece(row, column).getSymbol();
	}
	public boolean isCheck(char color) {
	    return board.isCheck(color);
	}
	public boolean isCheckMate(char color) {
	    return board.isCheckMate(color);
	}
	public boolean isStalemate(char color) {
	    return board.isStalemate(color);
	}
}