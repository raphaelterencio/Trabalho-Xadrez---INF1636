package model;

class Cavalo extends Peca{

	Cavalo(char color, int i, int j)
	{ 
		super(color, i, j);
		symbol = 'C';
	}
	
	@Override
	// Movimento em L
	boolean move(int target_i, int target_j, Peca[][] squares) 
	{
		int diff_x = Math.abs(i - target_i);
		int diff_y = Math.abs(j - target_j);
		
		if ( (diff_x == 2 && diff_y == 1) || (diff_x == 1 && diff_y == 2) ) return true;
		else return false;
	}
	
}