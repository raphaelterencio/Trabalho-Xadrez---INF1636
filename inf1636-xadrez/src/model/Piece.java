package model;

import java.util.List;
import java.util.ArrayList;

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
    
    protected List<int[]> getPossibleMoves(int row, int column) 
    {
        List<int[]> possibleMoves = new ArrayList<>();

        for (int target_row = 0; target_row < 8; target_row++) {
            for (int target_column = 0; target_column < 8; target_column++) {
            	
                // Ignora a posição atual da peça
                if (target_row == row && target_column == column) continue;

                if (canMove(row, column, target_row, target_column)) {
                    possibleMoves.add(new int[] { target_row, target_column });
                }
            }
        }

        return possibleMoves;
    }
    
}