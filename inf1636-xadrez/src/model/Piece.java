package model;

abstract class Piece 
{

	protected char color; // ('B' Black) ou ('W' White)
	protected int row;
	protected int column;
	protected char symbol; // Identifica o tipo de peça
	
	Piece(char color, int row, int column)
	{
		this.color = color; 
		this.row = row; 
		this.column = column;
		this.symbol = '-';
	}
	
	char getColor() { return color; }
	char getSymbol() { return symbol; }
	
	void setPos(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	abstract boolean canMove(int target_row, int target_column, Piece[][] houses);
	// Nota: conferir se tem alguma peça na casa de destino fica a cargo de Board
	// OBS: menos no movimento vertical do peão
}