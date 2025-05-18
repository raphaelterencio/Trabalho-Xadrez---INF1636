package model;

// Restringe o acesso do usuário a comandos estratégicos
public class API
{
	private Board board;
	
	public void start() { board = new Board(); }
	
	public void printBoard() { board.print(); }
	
	public boolean movePiece(int row, int column, int target_row, int target_column)
	{
		Piece piece = board.getPiece(row, column);
		return piece.canMove(target_row, target_column);		
	}
	
}