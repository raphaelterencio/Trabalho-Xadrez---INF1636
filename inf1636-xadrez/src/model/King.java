package model;

import java.util.List;

class King extends Piece{

	protected King(char color)
	{ 
		super(color);
		this.setColor('K');
	}
	
	@Override
	// Frente/trás, esquerda/direita e diagonais mas apenas a 1 casa de distância
	protected boolean canMove(int row, int column, int target_row, int target_column) 
	{
		// Confere se a posição de destino é válida
		int diff_x = Math.abs(row - target_row);
		int diff_y = Math.abs(column - target_column);
		if (diff_x <= 1 && diff_y <= 1) return true;
		
		return false;
	}
	
	@Override
	protected List<int[]> getPath(int row, int column, int target_row, int target_column)
	{ 
		return null; 
	}
	
	@Override
	protected King clone()
	{
		King copy = new King(this.getColor());
		return copy;
	}
}