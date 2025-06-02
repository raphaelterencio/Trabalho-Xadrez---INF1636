package model;

import java.util.List;

class Board {

	private Piece[][] houses;
	private Piece[] captured_whites;
	private Piece[] captured_blacks;
	private int count_captured_whites;
	private int count_captured_blacks;
	
	protected Board()
	{
		houses = new Piece[8][8];
		captured_whites = new Piece[16];
		captured_blacks = new Piece[16];
		count_captured_whites = 0;
		count_captured_blacks = 0;
	}
	
	// Coloca as peças no tabuleiro
	protected void launch()
	{
		// Bispos
		houses[0][2] = new Bishop('B', 0, 2);
		houses[0][5] = new Bishop('B', 0, 5);
		houses[7][2] = new Bishop('W', 7, 2);
		houses[7][5] = new Bishop('W', 7, 5);
		
		// Cavalos
		houses[0][1] = new Horse('B', 0, 1);
		houses[0][6] = new Horse('B', 0, 6);
		houses[7][1] = new Horse('W', 7, 1);
		houses[7][6] = new Horse('W', 7, 6);
		
		// Reis
		houses[0][4] = new King('B', 0, 4);
		houses[7][4] = new King('W', 7, 4);
		
		// Peões
		for(int column=0; column<8; column++)
		{
			houses[1][column] = new Pawn('B', 1, column);
			houses[6][column] = new Pawn('W', 6, column);
		}
		
		// Rainhas
		houses[0][3] = new Queen('B', 0, 3);
		houses[7][3] = new Queen('W', 7, 3);
		
		// Torres
		houses[0][0] = new Rook('B', 0, 0);
		houses[0][7] = new Rook('B', 0, 7);
		houses[7][0] = new Rook('W', 7, 0);
		houses[7][7] = new Rook('W', 7, 7);
	}

	
	protected Piece getPiece(int row, int column) 
	{ 
		return houses[row][column]; 
	}
	
	protected void movePiece(int row, int column, int target_row, int target_column)
	{
		houses[row][column].setPos(target_row, target_column);
		houses[target_row][target_column] = houses[row][column];
		houses[row][column] = null;
	}
	
	protected void capturePiece(int row, int column)
	{
		if(houses[row][column].getColor() == 'W')
			captured_whites[count_captured_whites++] = houses[row][column];
		else 
			captured_blacks[count_captured_blacks++] = houses[row][column];
	}
	
	protected boolean isThereAPiece(int row, int column)
	{
		if (houses[row][column] != null) return true;
		return false;
	}
	
	protected boolean isCheck(char color)
	{
		// Procura o rei
		int king_row = -1, king_column = -1;
		for(int row=0; row<8; row++)
		{
			for(int column=0; column<8; column++)
			{
				Piece piece = houses[row][column];
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
				Piece piece = houses[row][column];
				if(piece != null && piece.getColor() == opponent_color)
				{
					if (piece.canMove(king_row, king_column))
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
				Piece piece = houses[row][column];
				if(piece != null && piece.getColor() == color)
				{
					// Tenta todos os movimentos possíveis da peça
					List<int[]> possibleMoves = piece.getPossibleMoves();
					for(int[] move : possibleMoves)
					{
						int targetRow = move[0];
						int targetColumn = move[1];
						
						// Salva o estado atual
						Piece originalPiece = houses[targetRow][targetColumn];
						
						// Faz o movimento
						houses[targetRow][targetColumn] = piece;
						houses[row][column] = null;
						piece.setPos(targetRow, targetColumn);
						
						// Verifica se ainda está em xeque
						boolean stillInCheck = isCheck(color);
						
						// Desfaz o movimento
						houses[row][column] = piece;
						houses[targetRow][targetColumn] = originalPiece;
						piece.setPos(row, column);
						
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
				Piece piece = houses[row][column];
				if(piece != null && piece.getColor() == color)
				{
					// Tenta todos os movimentos possíveis da peça
					List<int[]> possibleMoves = piece.getPossibleMoves();
					for(int[] move : possibleMoves)
					{
						int targetRow = move[0];
						int targetColumn = move[1];
						
						// Salva o estado atual
						Piece originalPiece = houses[targetRow][targetColumn];
						
						// Faz o movimento
						houses[targetRow][targetColumn] = piece;
						houses[row][column] = null;
						piece.setPos(targetRow, targetColumn);
						
						// Verifica se o movimento coloca o rei em xeque
						boolean putsInCheck = isCheck(color);
						
						// Desfaz o movimento
						houses[row][column] = piece;
						houses[targetRow][targetColumn] = originalPiece;
						piece.setPos(row, column);
						
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
		Piece king = houses[row][kingCol];
		Piece rook = houses[row][rookCol];
		
		if (!(king instanceof King) || !(rook instanceof Rook) || 
			king.getColor() != color || rook.getColor() != color) {
			return false;
		}
		
		// Verifica se há peças entre o rei e a torre
		int start = isKingside ? kingCol + 1 : kingCol - 1;
		int end = isKingside ? rookCol - 1 : rookCol + 1;
		int step = isKingside ? 1 : -1;
		
		for (int col = start; col != end; col += step) {
			if (houses[row][col] != null) {
				return false;
			}
		}
		
		// Verifica se o rei não passa por casas em xeque
		int targetKingCol = isKingside ? kingCol + 2 : kingCol - 2;
		for (int col = kingCol; col != targetKingCol; col += step) {
			// Salva o estado atual
			Piece originalKing = houses[row][kingCol];
			Piece originalTarget = houses[row][col];
			
			// Move o rei temporariamente
			houses[row][col] = king;
			houses[row][kingCol] = null;
			king.setPos(row, col);
			
			// Verifica se está em xeque
			boolean inCheck = isCheck(color);
			
			// Desfaz o movimento
			houses[row][kingCol] = originalKing;
			houses[row][col] = originalTarget;
			king.setPos(row, kingCol);
			
			if (inCheck) return false;
		}
		
		return true;
	}
	
	// Realiza um roque curto = true ou longo = false
	protected void performCastle(char color, boolean isKingside) {
		int row = (color == 'W') ? 7 : 0;
		int kingCol = 4;
		int rookCol = isKingside ? 7 : 0;
		
		Piece king = houses[row][kingCol];
		Piece rook = houses[row][rookCol];
		
		// Move o rei
		int targetKingCol = isKingside ? kingCol + 2 : kingCol - 2;
		houses[row][targetKingCol] = king;
		houses[row][kingCol] = null;
		king.setPos(row, targetKingCol);
		
		// Move a torre
		int targetRookCol = isKingside ? targetKingCol - 1 : targetKingCol + 1;
		houses[row][targetRookCol] = rook;
		houses[row][rookCol] = null;
		rook.setPos(row, targetRookCol);
	}
	
	protected boolean checkPawnPromotion(int row, int column)
	{
		Piece piece = houses[row][column];
		
		// Confere se a peça é um peão
		if( !(piece instanceof Pawn) ) return false;
		
		// Pega a cor a peça
		char color = piece.getColor();
		
		// Confere se o peão alcançou a última fileira
		if (color == 'W' && row == 0) return true;
		if (color == 'B' && row == 7) return true;
		
		return false;
	}
	
} 