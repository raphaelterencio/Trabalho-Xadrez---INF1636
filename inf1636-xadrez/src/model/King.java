package model;

class King extends Piece{

	King(char color, int row, int column){ 
		super(color, row, column); 
		symbol = 'K';
	}
	
	@Override
	// Frente/trás, esquerda/direita e diagonais mas apenas a 1 casa de distância
	boolean canMove(int target_row, int target_column, Piece[][] houses) 
	{
		// Confere se a posição de destino é válida
		int diff_x = Math.abs(row - target_row);
		int diff_y = Math.abs(column - target_column);
		if (diff_x <= 1 && diff_y <= 1) return true;
		
		return false;
	}
	
}