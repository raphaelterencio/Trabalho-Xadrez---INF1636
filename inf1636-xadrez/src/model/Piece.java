package model;

import java.util.List;

abstract class Piece 
{
    private char color;
    private char symbol;
    
    protected Piece(char color) 
    {
        this.color = color;
        this.symbol = '-'; 
    }
    
    protected char getColor() { return color; }
    
    protected void setColor(char color) { this.color = color; }
    
    protected char getSymbol() { return this.symbol; }
    
    protected void setSymbol(char symbol) { this.symbol = symbol; }
    
    protected abstract boolean canMove(int row, int column, int target_row, int target_column);
    
    protected abstract List<int[]> getPath(int row, int column, int target_row, int target_column);
    
    protected abstract Piece clone();
}