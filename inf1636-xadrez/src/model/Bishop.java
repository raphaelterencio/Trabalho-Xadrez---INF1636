package model;

import java.util.List;
import java.util.ArrayList;

class Bishop extends Piece{

	protected Bishop(char color, int row, int column)
	{ 
		super(color, row, column); 
		symbol = 'B';
	}
	
	@Override
	// Movimento diagonal
	protected boolean canMove(int target_row, int target_column) 
	{
		int diff_row = Math.abs(row - target_row);
		int diff_column = Math.abs(column - target_column);
		
		if (diff_row == diff_column) return true;
		
		return false;
	}
	
	protected List<int[]> getPath(int target_row, int target_column)
	{	
		List<int[]> path = new ArrayList<>();
		
		// Auxiliares para percorrer o caminho da peça
	    int step_row = (target_row > row) ? 1 : -1; // 1 (para baixo) -1 (para cima)
	    int step_column = (target_column > column) ? 1 : -1; // 1 (direta) -1 (esquerda)
		
	    int current_row = row + step_row;
	    int current_column = column + step_column;

	    while (current_row != target_row && current_column != target_column) {
	    	path.add(new int[] {current_row, current_column});
	        current_row += step_row;
	        current_column += step_column;
	    }
		
		return path;
	}
	
} 