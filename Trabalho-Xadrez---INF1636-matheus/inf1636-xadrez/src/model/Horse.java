package model;

class Horse extends Piece{

	Horse(char color, int row, int column)
	{ 
		super(color, row, column);
		symbol = 'H';
	}
	
	@Override
	// Movimento em L
	boolean canMove(int target_row, int target_column, Piece[][] houses) 
	{
		// Confere se a posição de destino é válida
		int diff_x = Math.abs(row - target_row);
		int diff_y = Math.abs(column - target_column);
		if ( !(diff_x == 2 && diff_y == 1) && !(diff_x == 1 && diff_y == 2) ) return false;

		// Confere se a única casa que o cavalo pisa antes do destino está ocupada
		int middle_row = row + (target_row - row) / 2;
		int middle_column = column + (target_column - column) / 2;
		if (houses[middle_row][middle_column] != null) return false;
		
		// Caso não tenha nenhuma peça no caminho
		return true;
	}
	
}