package model;

class Rei extends Peca{

	Rei(char color, int i, int j){ 
		super(color, i, j); 
		symbol = 'K';
	}
	
	@Override
	// Frente/trás, esquerda/direita e diagonais mas apenas a 1 casa de distância
	boolean move(int target_i, int target_j, Peca[][] squares) 
	{
		int diff_x = Math.abs(i - target_i);
		int diff_y = Math.abs(j - target_j);
		
		if (diff_x <= 1 && diff_y <= 1) return true;
		else return false;
	}
	
}