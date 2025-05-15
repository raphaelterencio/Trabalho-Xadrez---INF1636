package model;

class Torre extends Peca{

	Torre(char color, int i, int j){
		super(color, i, j); 
		symbol = 'T';
	}
	
	@Override
	// Frente/trás, esquerda/direita
	boolean move(int target_i, int target_j, Peca[][] squares) 
	{
		if (i == target_i || j == target_j) return true;
		else return false;
	}
	
}