package model;

import java.util.List;

abstract class Piece 
{
    protected char color;
    protected char symbol;
    
    protected Piece(char color) 
    {
        this.color = color;
        this.symbol = '-'; 
    }
    
    protected char getColor() { return color; }
    
    protected char getSymbol() { return this.symbol; }
    
    protected abstract boolean canMove(int row, int column, int target_row, int target_column);
    
    protected abstract List<int[]> getPath(int row, int column, int target_row, int target_column);
    
    protected abstract Piece clone();
}