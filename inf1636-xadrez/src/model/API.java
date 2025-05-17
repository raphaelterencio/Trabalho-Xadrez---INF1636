package model;

public class API
{

	private static API object = null;
	private Board board;
	private char turn_color;
	
	private API() { board = new Board(); turn_color = 'W'; }
	
	public char getTurnColor() { return turn_color; }
	public void printBoard() { board.print(); }
	
	public static API getObject() {
		if (object == null) object = new API();
		return object;
	}
	
	public void restart()
	{
		board = new Board();
		turn_color = 'W';
	}
	
	public boolean movePeca(int i, int j, int target_i, int target_j)
	{
		boolean flag = board.movePiece(i, j, target_i, target_j, turn_color);
		if (flag)
		{
			if (turn_color == 'W') turn_color = 'B';
			else turn_color = 'W';
		}
		return flag;
		
	}
	
}