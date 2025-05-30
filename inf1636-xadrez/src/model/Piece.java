package model;

import java.util.List;
import java.util.ArrayList;

abstract class Piece 
{

	protected char color; // ('B' Black) ou ('W' White)
	protected int row;
	protected int column;
	
	protected Piece(char color, int row, int column)
	{
		this.color = color; 
		this.row = row; 
		this.column = column;
	}
	
	protected char getColor()
	{ 
		return color; 
	}
	
	
	protected int[] getPos()
	{
		return new int[] {row, column};
	}
	
	protected void setPos(int row, int column) 
	{
		this.row = row;
		this.column = column;
	}
	
	protected List<int[]> getPossibleMoves() 
	{
	    List<int[]> possibleMoves = new ArrayList<>();

	    for (int target_row = 0; target_row < 8; target_row++) {
	        for (int target_column = 0; target_column < 8; target_column++) 
	        {
	            // Ignora a posição atual da peça
	            if (target_row == this.row && target_column == this.column) continue;

	            if (canMove(target_row, target_column)) {
	                possibleMoves.add(new int[]{target_row, target_column});
	            }
	        }
	    }

	    return possibleMoves;
	}
	
	protected abstract boolean canMove(int target_row, int target_column);
}