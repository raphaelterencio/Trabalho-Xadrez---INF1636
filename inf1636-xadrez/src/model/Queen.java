package model;

class Queen extends Piece{

	protected Queen(char color, int row, int column){ 
		super(color, row, column);
		this.symbol = 'Q';
		}
	
	@Override
	// Frente/trás, esquerda/direita e diagonais
	protected boolean canMove(int target_row, int target_column) 
	{
		// Confere se a posição de destino é válida
		int diff_row = Math.abs(row - target_row);
		int diff_column = Math.abs(column - target_column);
		if (row == target_row || column == target_column || diff_row == diff_column) return true;

		return false;
	}
}