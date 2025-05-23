package model;

class Rook extends Piece{

	protected Rook(char color, int row, int column){ super(color, row, column); }
	
	@Override
	// Frente/trás, esquerda/direita
	protected boolean canMove(int target_row, int target_column) 
	{
		if (row == target_row || column == target_column) return true;

		return false;
	}
}