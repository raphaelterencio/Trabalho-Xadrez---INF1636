package model;

import java.util.ArrayList;
import java.util.List;

class Board 
{
	private Piece[][] tiles;
	private Piece[] captured_whites;
	private Piece[] captured_blacks;
	private int count_captured_whites;
	private int count_captured_blacks;
	
	protected Board()
	{
		tiles = new Piece[8][8];
		captured_whites = new Piece[16];
		captured_blacks = new Piece[16];
		count_captured_whites = 0;
		count_captured_blacks = 0;
	}

	protected void capturePiece(int row, int column)
	{
		if(tiles[row][column].getColor() == 'W')
			captured_whites[count_captured_whites++] = tiles[row][column];
		else 
			captured_blacks[count_captured_blacks++] = tiles[row][column];
	}
	
	protected Piece getPiece(int row, int column){ return tiles[row][column]; }
	
	protected Piece[][] getTiles() { return tiles; }
	
	protected void setUp()
	{
		// Bispos
		tiles[0][2] = new Bishop('B');
		tiles[0][5] = new Bishop('B');
		tiles[7][2] = new Bishop('W');
		tiles[7][5] = new Bishop('W');
		
		// Cavalos
		tiles[0][1] = new Horse('B');
		tiles[0][6] = new Horse('B');
		tiles[7][1] = new Horse('W');
		tiles[7][6] = new Horse('W');
		
		// Reis
		tiles[0][4] = new King('B');
		tiles[7][4] = new King('W');
		
		// Peões
		for(int column=0; column<8; column++)
		{
			tiles[1][column] = new Pawn('B');
			tiles[6][column] = new Pawn('W');
		}
		
		// Rainhas
		tiles[0][3] = new Queen('B');
		tiles[7][3] = new Queen('W');
		
		// Torres
		tiles[0][0] = new Rook('B');
		tiles[0][7] = new Rook('B');
		tiles[7][0] = new Rook('W');
		tiles[7][7] = new Rook('W');
	}

	protected boolean movePiece(int row, int column, int target_row, int target_column)
	{
		if ( isValidMove(row, column, target_row, target_column) ) 
		{
			tiles[target_row][target_column] = tiles[row][column];
			tiles[row][column] = null;
			return true;
		}
		
		return false;
	}
	
	protected boolean isValidMove(int row, int column, int target_row, int target_column)
	{
		// Obtém o caminho até a posição desejada
		List<int[]> list = getFixedPath(row, column, target_row, target_column);
		
		// Confere se é possível chegar até a posição desejada
	    for (int[] coords : list) 
	    {
	        if (coords[0] == target_row && coords[1] == target_column) { return true; }
	    }
	    
	    return false;
	}
	
	protected List<int[]> getFixedPath(int row, int column, int target_row, int target_column)
	{
		Piece piece = tiles[row][column]; 
		Piece cmp_piece;
		
		// Obtém o caminho a ser percorrido desconsiderando colisão
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

    protected List<int[]> getPossibleMoves(int row, int column) 
    {
        List<int[]> possibleMoves = new ArrayList<>();
        Piece piece = tiles[row][column];
        boolean alreadyExists;

        for (int target_row = 0; target_row < 8; target_row++) {
            for (int target_column = 0; target_column < 8; target_column++) {
            	
                // Ignora a posição atual da peça
                if (row == target_row && column == target_column) continue;

                // Se for uma posição possível de se chegar
                if (piece.canMove(row, column, target_row, target_column)) 
                {
                	// Percorre o caminho até a posição
                	for (int[] move : getFixedPath(row, column, target_row, target_column)) 
                	{
                	    alreadyExists = false;
                	    
                	    // Confere se a posição já foi selecionada
                	    for (int[] existing : possibleMoves) 
                	    {
                	        if (move[0] == existing[0] && move[1] == existing[1]) { alreadyExists = true; break; }
                	    }
                	    
                	    // Se a posição ainda não foi selecionada, adicionar ela à lista
                	    if (!alreadyExists) { possibleMoves.add(move); }
                	}
                    
                }
            }
        }

        return possibleMoves;
    }
} 