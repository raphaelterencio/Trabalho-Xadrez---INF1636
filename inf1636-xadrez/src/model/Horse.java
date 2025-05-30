package model;

class Horse extends Piece{

	protected Horse(char color, int row, int column){ super(color, row, column); }
	
	@Override
	// Movimento em L
	protected boolean canMove(int target_row, int target_column) 
	{
		int diff_row = Math.abs(row - target_row);
		int diff_column = Math.abs(column - target_column);
		if ( (diff_row == 2 && diff_column == 1) || (diff_row == 1 && diff_column == 2) ) return true;

		return false;
	}
}