package model;

import java.util.List;

class Game{
	
	private Board board;
	private Piece[][] tiles;
	private int round;
	private char round_color;
	
	protected Game()
	{
		board = new Board();
		board.setUp();
		tiles = board.getTiles();
		round = 1;
		round_color = 'W';
	}
	
	protected int getRound() { return this.round; }

	protected char getRoundColor() { return this.round_color; }
	
	protected void testMode(char round_color)
	{
		board = new Board();
		tiles = board.getTiles();
		this.round_color = round_color;
	}
	
	protected void setPiece(char type, char color, int row, int column) { board.setPiece(type, color, row, column); } 
	
	protected boolean movePiece(int row, int column, int target_row, int target_column) 
	{ 
		boolean flag = board.movePiece(row, column, target_row, target_column); 
		
		if (flag) 
		{
			round++;
			round_color = (round_color == 'W') ? 'B' : 'W';
		}
		
		return flag;
	}
	
	protected List<int[]> getPossibleMoves(int row, int column){ return board.getPossibleMoves(row, column); }
	
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
					List<int[]> possibleMoves = board.getPossibleMoves(row, column);
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
					List<int[]> possibleMoves = board.getPossibleMoves(row, column);
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
	
	// 
	
	protected char getPieceColor(int row, int column) { return tiles[row][column].getColor(); }
	
	protected boolean isThereAPiece(int row, int column) 
	{ 
		if (tiles[row][column] != null) return true;
		return false;
	}
	
	protected char getPieceSymbol(int row, int column) { return tiles[row][column].getSymbol(); }
}