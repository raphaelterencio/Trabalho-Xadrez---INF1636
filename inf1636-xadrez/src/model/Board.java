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
	
	// Testes
	
	protected void TestSetUp()
	{
		tiles = new Piece[8][8];
	}
	
	protected void TestSetPiece(char type, char color, int row, int column)
	{	
		switch (type)
		{
		case 'B':
			tiles[row][column] = new Bishop(color);
			break;
		case 'H':
			tiles[row][column] = new Horse(color);
			break;
		case 'K':
			tiles[row][column] = new King(color);
			break;
		case 'P':
			tiles[row][column] = new Pawn(color);
			break;
		case 'Q':
			tiles[row][column] = new Queen(color);
			break;
		case 'R':
			tiles[row][column] = new Rook(color);
			break;
		}
	}
	
	// Métodos get()
	
	protected char getPieceColor(int row, int column) { return tiles[row][column].getColor(); }
	
	protected char getPieceSymbol(int row, int column) { return tiles[row][column].getSymbol(); }

	// Peças
	
	protected boolean isThereAPiece(int row, int column) 
	{ 
		if (tiles[row][column] != null) return true;
		return false;
	}

	protected boolean movePiece(int row, int column, int target_row, int target_column)
	{		
		if ( isValidMove(row, column, target_row, target_column) ) 
		{
			Piece piece = tiles[row][column];
			Piece cmp_piece = tiles[target_row][target_column];
			
			// Confere se houve uma captura de peça
			if (cmp_piece != null && piece.getColor() != cmp_piece.getColor())
				capturePiece(target_row, target_column);
				
			tiles[target_row][target_column] = tiles[row][column];
			tiles[row][column] = null;
						
			return true;
		}
		
		return false;
	}
	
	// Captura de peças
	
	private void capturePiece(int row, int column)
	{
		if(tiles[row][column].getColor() == 'W')
			captured_whites[count_captured_whites++] = tiles[row][column].clone();
		else 
			captured_blacks[count_captured_blacks++] = tiles[row][column].clone();
	}
	
	// Movimentação
	
	private boolean isValidMove(int row, int column, int target_row, int target_column)
	{
		// Obtém o caminho até a posição desejada
		List<int[]> list = getFixedPath(row, column, target_row, target_column);
				
		// Confere se é possível chegar até a posição desejada
	    for (int[] coords : list) 
	    {
	        if (coords[0] == target_row && coords[1] == target_column) 
	        	return true;
	    }
	    
	    return false;
	}
	
	private List<int[]> getFixedPath(int row, int column, int target_row, int target_column)
	{
		Piece piece = tiles[row][column]; 
		Piece cmp_piece;
				
		// Obtém o caminho a ser percorrido desconsiderando colisão
		List<int[]> path = piece.getPath(row, column, target_row, target_column);
		List<int[]> fixed_path = new ArrayList<>();
				
		// Percorre o caminho até a posição
		for(int[] coords : path) 
		{	
			cmp_piece = tiles[coords[0]][coords[1]];
			
			// Se não houver uma peça no caminho
			if(cmp_piece == null)
			{
				// Confere se o movimento é um peão tentando andar na diagonal sem comer ninguém
				if( piece instanceof Pawn)
				{
					int row_diff = Math.abs(coords[0] - row);
					int column_diff = Math.abs(coords[1] - column);
					
					if (row_diff == 1 && column_diff == 1)
						continue;
				}
				
				fixed_path.add(new int[] { coords[0], coords[1] });
			}
			
			// Se houver uma peça no caminho
			else 
			{ 
				// Se casa não for o destino final
				if ( coords[0] == target_row && coords[1] == target_column) 
					break;
				
				// Se a casa for o destino final
				else
				{  
					// Se for uma peça de cor diferente
					if ( piece.getColor() != cmp_piece.getColor() ) 
						fixed_path.add(new int[] { coords[0], coords[1] });
					
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
                if (row == target_row && column == target_column) 
                	continue;

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
                	        if (move[0] == existing[0] && move[1] == existing[1]) 
                	        { 
                	        	alreadyExists = true; 
                	        	break; 
                	        }
                	    }
                	    
                	    // Se a posição ainda não foi selecionada, adicionar ela à lista
                	    if (!alreadyExists)
                	    	possibleMoves.add(move);
                	}
                    
                }
            }
        }

        return possibleMoves;
    }

	// Regras
	
	protected boolean isCheck(char color)
	{
		// Procura o rei
		int king_row = -1, king_column = -1;
		for(int row=0; row<8; row++)
		{
			for(int column=0; column<8; column++)
			{
				Piece piece = tiles[row][column];
				if(piece instanceof King && piece.getColor() == color)
				{
					king_row = row;
					king_column = column;
					break;
				}
			}
		}
		// Se o rei não for encontrado
		if (king_row == -1) return false;
		
		// Obtém a cor do oponente
		char opponent_color = (color == 'W') ? 'B' : 'W';
	
		// Verifica se o oponente consegue chegar no rei
		for(int row=0; row<8; row++)
		{
			for(int column=0; column<8; column++)
			{
				Piece piece = tiles[row][column];
				if(piece != null && piece.getColor() == opponent_color)
				{
					if (piece.canMove(row, column, king_row, king_column))
						return true;
				}
					
			}
		}
		
		return false;
	}
	
	protected boolean isCheckMate(char color)
	{
		// Se não estiver em xeque, não pode ser xeque-mate
		if (!isCheck(color)) return false;
		
		// Procura todas as peças da cor
		for(int row=0; row<8; row++)
		{
			for(int column=0; column<8; column++)
			{
				Piece piece = tiles[row][column];
				if(piece != null && piece.getColor() == color)
				{
					// Tenta todos os movimentos possíveis da peça
					List<int[]> possibleMoves = getPossibleMoves(row, column);
					for(int[] move : possibleMoves)
					{
						int targetRow = move[0];
						int targetColumn = move[1];
						
						// Salva o estado atual
						Piece originalPiece = tiles[targetRow][targetColumn];
						
						// Faz o movimento
						tiles[targetRow][targetColumn] = piece;
						tiles[row][column] = null;
						
						// Verifica se ainda está em xeque
						boolean stillInCheck = isCheck(color);
						
						// Desfaz o movimento
						tiles[row][column] = piece;
						tiles[targetRow][targetColumn] = originalPiece;
						
						// Se encontrou um movimento que tira do xeque, não é xeque-mate
						if (!stillInCheck) return false;
					}
				}
			}
		}
		
		
		return true;
	}

	protected boolean isStalemate(char color)
	{
		// Se estiver em xeque, não pode ser congelamento
		if (isCheck(color)) return false;
		
		// Procura todas as peças da cor
		for(int row=0; row<8; row++)
		{
			for(int column=0; column<8; column++)
			{
				Piece piece = tiles[row][column];
				if(piece != null && piece.getColor() == color)
				{
					// Tenta todos os movimentos possíveis da peça
					List<int[]> possibleMoves = getPossibleMoves(row, column);
					for(int[] move : possibleMoves)
					{
						int targetRow = move[0];
						int targetColumn = move[1];
						
						// Salva o estado atual
						Piece originalPiece = tiles[targetRow][targetColumn];
						
						// Faz o movimento
						tiles[targetRow][targetColumn] = piece;
						tiles[row][column] = null;
						
						// Verifica se o movimento coloca o rei em xeque
						boolean putsInCheck = isCheck(color);
						
						// Desfaz o movimento
						tiles[row][column] = piece;
						tiles[targetRow][targetColumn] = originalPiece;
						
						// Se encontrou um movimento legal, não é congelamento
						if (!putsInCheck) return false;
					}
				}
			}
		}
		
		return true;
	}

	protected boolean checkPawnPromotion()
	{
	    for (int row = 0; row < 8; row++) {
	        for (int column = 0; column < 8; column++) 
	        {
				Piece piece = tiles[row][column];
				
				// Confere se a peça é um peão
				if( piece instanceof Pawn )
				{
					// Pega a cor a peça
					char color = piece.getColor();
					
					// Confere se o peão alcançou a última fileira
					if (color == 'W' && row == 0) return true;
					if (color == 'B' && row == 7) return true;
				}
				
				
	        }
	    }
	    
		return false;
	}
	
	// Movimentos especiais
	
	// Verifica se o rei pode realizar um roque
	protected boolean canCastle(char color, boolean isKingside) {
		// Verifica se o rei está em xeque
		if (isCheck(color)) return false;
		
		int row = (color == 'W') ? 7 : 0;
		int kingCol = 4;
		int rookCol = isKingside ? 7 : 0;
		
		// Verifica se o rei e a torre estão nas posições iniciais
		Piece king = tiles[row][kingCol];
		Piece rook = tiles[row][rookCol];
		
		if (!(king instanceof King) || !(rook instanceof Rook) || 
			king.getColor() != color || rook.getColor() != color) {
			return false;
		}
		
		// Verifica se há peças entre o rei e a torre
		int start = isKingside ? kingCol + 1 : kingCol - 1;
		int end = isKingside ? rookCol - 1 : rookCol + 1;
		int step = isKingside ? 1 : -1;
		
		for (int col = start; col != end; col += step) {
			if (tiles[row][col] != null) {
				return false;
			}
		}
		
		// Verifica se o rei não passa por casas em xeque
		int targetKingCol = isKingside ? kingCol + 2 : kingCol - 2;
		for (int col = kingCol; col != targetKingCol; col += step) {
			// Salva o estado atual
			Piece originalKing = tiles[row][kingCol];
			Piece originalTarget = tiles[row][col];
			
			// Move o rei temporariamente
			tiles[row][col] = king;
			tiles[row][kingCol] = null;
			
			// Verifica se está em xeque
			boolean inCheck = isCheck(color);
			
			// Desfaz o movimento
			tiles[row][kingCol] = originalKing;
			tiles[row][col] = originalTarget;
			
			if (inCheck) return false;
		}
		
		return true;
	}
	
	// isKingside -> true = roque curto // false - roque longo
	protected void performCastle(char color, boolean isKingside) {
		int row = (color == 'W') ? 7 : 0;
		int kingCol = 4;
		int rookCol = isKingside ? 7 : 0;
		
		Piece king = tiles[row][kingCol];
		Piece rook = tiles[row][rookCol];
		
		// Move o rei
		int targetKingCol = isKingside ? kingCol + 2 : kingCol - 2;
		tiles[row][targetKingCol] = king;
		tiles[row][kingCol] = null;
		
		// Move a torre
		int targetRookCol = isKingside ? targetKingCol - 1 : targetKingCol + 1;
		tiles[row][targetRookCol] = rook;
		tiles[row][rookCol] = null;
	}

} 