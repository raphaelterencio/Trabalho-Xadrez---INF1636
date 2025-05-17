package model;

class Bispo extends Peca{

	Bispo(char color, int i, int j)
	{ 
		super(color, i, j); 
		symbol = 'B';
	}
	
	@Override
	// Movimento diagonal
	boolean move(int target_i, int target_j, Peca[][] squares) 
	{
		int diff_x = Math.abs(target_i - target_i);
		int diff_y = Math.abs(j - target_j);
		
		if (diff_x == diff_y) return true;
		else return false;
	}
	
}