package model;

class Pawn extends Piece{

	Pawn(char color, int row, int column)
	{ 
		super(color, row, column);
		symbol = 'P';
	}
	
	@Override
	// Move pra frente e come na diagonal
	boolean canMove(int target_row, int target_column, Piece[][] houses) 
	{
		if (canVerticalMove(target_row, target_column, houses)) return true;
		if (canDiagonalMove(target_row, target_column, houses)) return true;
		
		// Caso nenhum dos movimentos possa ser feito
		return false;
	}
	
	// Movimento para frente - 2 casas na 1a rodada, 1 casa nas demais
	// OBS: apenas nessa classe vamos conferir a posição de destino, já que o peão não pode comer andando pra frente
	private boolean canVerticalMove(int target_row, int target_column, Piece[][] houses)
	{
		// Restringe o movimento à vertical
		if(column != target_column) return false;
		
		// Obtém a direção que o peão deve andar
		int direction; 
		if (color == 'W') direction = -1; // Anda pra cima
		else direction = 1; // Anda pra baixo
		
		// Confere se é a primeira jogada
		boolean isFirstRound = false;
		if( (color == 'W' && row == 6) || (color == 'B' && row == 1) ) isFirstRound = true;
		
		// Movimento de 2 casas
		if( isFirstRound && target_row == row + (2*direction) )
		{
			// Se o caminho estiver vago
			int middle_row = row + direction;
			if ( houses[middle_row][column] == null && houses[target_row][target_column] == null ) return true;
		}
		
		// Movimento de uma casa
		if (target_row == row + direction && houses[target_row][target_column] == null ) return true;
		
		return false;
	}
	
	// Come na diagonal
	private boolean canDiagonalMove(int target_row, int target_column, Piece[][] houses)
	{
		// Restringe o movimento à comer uma peça na diagonal
		if( column == target_column || houses[target_row][target_column] == null ) return false;
		
		// Obtém a direção que o peão deve andar
		int direction; 
		if (color == 'W') direction = -1; // Anda pra cima
		else direction = 1; // Anda pra baixo
		
		// Restringe o movimento do peão de acordo com sua cor
		if (target_row != row + direction) return false;
		
		return true;
	}
	
}