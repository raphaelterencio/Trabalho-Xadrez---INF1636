package model;

import java.util.List;

public abstract class Piece 
{

	protected char color; // ('B' Black) ou ('W' White)
	protected int row;
	protected int column;
	protected char symbol; // Identifica o tipo de peça
	
	protected Piece(char color, int row, int column)
	{
		this.color = color; 
		this.row = row; 
		this.column = column;
		this.symbol = '-';
	}
	
	public  char getColor()
	{ 
		return color; 
	}
	
	public  char getSymbol() 
	{ 
		return symbol; 
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
	
	protected abstract boolean canMove(int target_row, int target_column);
	
	protected abstract List<int[]> getPath(int target_row, int target_column);
}