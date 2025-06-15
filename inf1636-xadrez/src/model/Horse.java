package model;

import java.util.ArrayList;
import java.util.List;

class Horse extends Piece{

	protected Horse(char color)
	{ 
		super(color);
		this.symbol = 'H';
	}
	
	@Override
	// Movimento em L
	protected boolean canMove(int row, int column, int target_row, int target_column) 
	{
		int diff_row = Math.abs(row - target_row);
		int diff_column = Math.abs(column - target_column);
		if ( (diff_row == 2 && diff_column == 1) || (diff_row == 1 && diff_column == 2) ) return true;

		return false;
	}
	
	@Override
	protected List<int[]> getPath(int row, int column, int target_row, int target_column)
	{
		List<int[]> path = new ArrayList<>();
		
		int diff_row = Math.abs(row - target_row);
		
		int step_row = 0, step_column = 0;
		
		if (diff_row == 2) step_row = (target_row > row) ? 2 : -2; // 2 (para baixo) -2 (para cima)
		else step_column = (target_column > column) ? 2 : -2; // 2 (direta) -2 (esquerda)
		
	    int current_row = row + step_row;
	    int current_column = column + step_column;
	   
		path.add(new int[] {current_row, current_column});
		return path;
	}
}