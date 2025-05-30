package model;

class Bishop extends Piece{

	protected Bishop(char color, int row, int column){ super(color, row, column); }
	
	@Override
	// Movimento diagonal
	protected boolean canMove(int target_row, int target_column) 
	{
		int diff_row = Math.abs(row - target_row);
		int diff_column = Math.abs(column - target_column);
		
		if (diff_row == diff_column) return true;
		
		return false;
	}
} 