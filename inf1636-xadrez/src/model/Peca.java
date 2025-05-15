package model;

abstract class Peca 
{

	protected char color; // 'B' ou 'W'
	protected int i;
	protected int j;
	protected char symbol;
	
	Peca(char color, int i, int j)
	{
		this.color = color; 
		this.i = i; 
		this.j = j;
		this.symbol = '-';
	}
	
	char getColor() { return color; }
	char getSymbol() { return symbol; }
	
	void setPos(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	abstract boolean move(int target_i, int target_j, Peca[][] squares);
	
}