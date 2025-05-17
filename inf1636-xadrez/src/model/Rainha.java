package model;

class Rainha extends Peca{

	Rainha(char color, int i, int j)
	{ 
		super(color, i, j); 
		symbol = 'Q';
	}
	
	@Override
	// Frente/trás, esquerda/direita e diagonais
	boolean move(int target_i, int target_j, Peca[][] squares) 
	{
		int diff_x = Math.abs(i - target_i);
		int diff_y = Math.abs(j - target_j);
		
		if (i == target_i || j == target_j || diff_x == diff_y) return true;
		else return false;
	}
	
}