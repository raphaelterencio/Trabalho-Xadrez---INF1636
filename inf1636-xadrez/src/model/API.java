package model;

import java.util.List;

// Restringe o acesso do usuário a comandos estratégicos
public class API
{
	private Board board;
	
	// Board
	
	public API() { board = new Board(); }
	
	public void startGame() { board.launch(); }
	
	public boolean isThereAPiece(int row, int column) { return board.isThereAPiece(row, column); }
	
	public void capturePiece(int row, int column) { board.capturePiece(row, column); }
	
	// Piece
	
	public boolean canMovePiece(int row, int column, int target_row, int target_column)
	{
		Piece piece = board.getPiece(row, column);
		return piece.canMove(target_row, target_column);
	}
	
	public List<int[]> getPiecePath(int row, int column, int target_row, int target_column)
	{
		Piece piece = board.getPiece(row, column);
		return piece.getPath(target_row, target_column);
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
	
}