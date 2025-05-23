package model;

class Pawn extends Piece{

	protected Pawn(char color, int row, int column){ super(color, row, column); }
	
	@Override
	// Move pra frente e come na diagonal
	protected boolean canMove(int target_row, int target_column) 
	{
		if (canVerticalMove(target_row, target_column)) return true;
		if (canDiagonalMove(target_row, target_column)) return true;
		
		return false;
	}
	
	// Movimento reto para frente - 2 casas na 1a rodada, 1 casa nas demais
	private boolean canVerticalMove(int target_row, int target_column)
	{
		// Restringe o movimento à vertical
		if(column != target_column) return false;
		
		// Obtém a direção que o peão deve andar
		int direction; 
		direction = (color == 'W') ? -1 : 1; // -1 (para cima) 1 (para baixo)
		
		// Confere se é a primeira jogada
		boolean isFirstRound = false;
		if( (color == 'W' && row == 6) || (color == 'B' && row == 1) ) isFirstRound = true;
		
		// Movimento de 2 casas
		if( isFirstRound && target_row == row + (2*direction) ) return true;
		
		// Movimento de uma casa
		if (target_row == row + direction) return true;
		
		return false;
	}
	
	// Movimento na diagonal pra frente - 1 casa
	private boolean canDiagonalMove(int target_row, int target_column)
	{	
		int diff_row = Math.abs(row - target_row);
		int diff_column = Math.abs(column - target_column);
		
		// Obtém a direção que o peão deve andar
		int direction; 
		direction = (color == 'W') ? -1 : 1; // -1 (para cima) 1 (para baixo)
		
		if (diff_row == diff_column && target_row == row + direction) return true;
		
		return false;
	}
}