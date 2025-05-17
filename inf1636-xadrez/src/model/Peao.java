package model;

class Peao extends Peca{

	Peao(char color, int i, int j)
	{ 
		super(color, i, j);
		symbol = 'P';
	}
	
	@Override
	// Frente/trás em 1 casa (na primeira rodada 2). Captura na diagonal.
	boolean move(int target_i, int target_j, Peca[][] squares) 
	{
		int aux;
		if (color == 'W') aux = -1; // Branco | anda pra cima | -1
		else aux = 1; // Preto | anda pra baixo | 1
		
		// O peão anda na diagonal para capturar peças
		if (j != target_j || squares[target_i][target_j] != null) {

			int diff_y = Math.abs(j - target_j);
			
			// Se ele estiver andando na diagonal corretamente
			if(diff_y == 1 && i+aux == target_i)
			{
				if (squares[target_i][target_j] != null && squares[target_i][target_j].color != color) return true;
			}
			
			return false;

		}
		
		// 2 casas à frente na primeira jogada
		boolean isFirstRound = false;
		if ( (color == 'W' && i == 6) || (color == 'B' && i == 1) ) isFirstRound = true;
		if (isFirstRound && target_i == i + 2 * aux && squares[i+aux][target_j] == null && squares[target_i][target_j] == null) return true; // Confere se não há nenhuma peça no caminho
 		
		// 1 casa à frente
		if (i+aux == target_i) return true;
		
		return false;
		
		
	}
	
}