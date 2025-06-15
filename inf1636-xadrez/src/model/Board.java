package model;

import java.util.ArrayList;
import java.util.List;

class Board 
{
	private Piece[][] houses;
	
	protected Board()
	{
		houses = new Piece[8][8];
	}

	protected Piece getPiece(int row, int column){ return houses[row][column]; }
	
	protected void setUp()
	{
		// Bispos
		houses[0][2] = new Bishop('B');
		houses[0][5] = new Bishop('B');
		houses[7][2] = new Bishop('W');
		houses[7][5] = new Bishop('W');
		
		// Cavalos
		houses[0][1] = new Horse('B');
		houses[0][6] = new Horse('B');
		houses[7][1] = new Horse('W');
		houses[7][6] = new Horse('W');
		
		// Reis
		houses[0][4] = new King('B');
		houses[7][4] = new King('W');
		
		// Peões
		for(int column=0; column<8; column++)
		{
			houses[1][column] = new Pawn('B');
			houses[6][column] = new Pawn('W');
		}
		
		// Rainhas
		houses[0][3] = new Queen('B');
		houses[7][3] = new Queen('W');
		
		// Torres
		houses[0][0] = new Rook('B');
		houses[0][7] = new Rook('B');
		houses[7][0] = new Rook('W');
		houses[7][7] = new Rook('W');
	}

	protected boolean movePiece(int row, int column, int target_row, int target_column)
	{
		if ( isValidMove(row, column, target_row, target_column) ) 
		{
			houses[target_row][target_column] = houses[row][column];
			houses[row][column] = null;
			return true;
		}
		return false;
	}
	
	protected boolean isValidMove(int row, int column, int target_row, int target_column)
	{
		List<int[]> list = getFixedPath(row, column, target_row, target_column);
		
	    for (int[] coords : list) 
	    {
	        if (coords[0] == target_row && coords[1] == target_column) { return true; }
	    }
	    
	    return false;
	}
	
	protected List<int[]> getFixedPath(int row, int column, int target_row, int target_column)
	{
		Piece piece = houses[row][column]; 
		Piece cmp_piece;
		
		List<int[]> path = piece.getPath(row, column, target_row, target_column);
		List<int[]> fixed_path = new ArrayList<>();
		
		// Percorre o caminho até a posição
		for(int[] coords : path) 
		{	
			cmp_piece = getPiece(coords[0], coords[1]);
			
			// Se não houver uma peça no caminho
			if(cmp_piece == null) { fixed_path.add(new int[] { coords[0], coords[1] }); }
			
			// Se houver uma peça no caminho
			else 
			{ 
				// Se casa não for o destino final
				if ( coords[0] == target_row && coords[1] == target_column) { break; }
				
				// Se a casa for o destino final
				else
				{  
					// Se for uma peça de cor diferente
					if ( piece.getColor() != cmp_piece.getColor() ) fixed_path.add(new int[] { coords[0], coords[1] });
					break;
				} 
			}
		}
		
		return fixed_path;
	}
} 