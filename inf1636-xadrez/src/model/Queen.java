package model;

class Queen extends Piece{

	Queen(char color, int row, int column)
	{ 
		super(color, row, column); 
		symbol = 'Q';
	}
	
	@Override
	// Frente/trás, esquerda/direita e diagonais
	boolean canMove(int target_row, int target_column, Piece[][] houses) 
	{
		// Confere se a posição de destino é válida
		int diff_x = Math.abs(row - target_row);
		int diff_y = Math.abs(column - target_column);
		if (row != target_row && column != target_column && diff_x != diff_y) return false;

		// Auxiliares para percorrer o caminho da peça
		int step_x, step_y;
		
		if (target_row > row) step_x = 1; // Para baixo
		else if (target_row < row) step_x = -1; // Para cima
		else step_x = 0; // Se mantém na mesma linha
		
		if (target_column > column) step_y = 1; // Para direita
		else if (target_column < column) step_y = -1; // Para esquerda
		else step_y = 0; // Se mantém na mesma coluna
		
		// Anda pelas casas conferindo se tem alguma peça
		int current_row = row + step_x; 
		int current_column = column + step_y;
		while(current_row != target_row || current_column != target_column)
		{
			if (houses[current_row][current_column] != null) return false;
			current_row += step_x;
			current_column += step_y;
		}
		
		// Caso não tenha nenhuma peça no caminho
		return true;
	}
	
}