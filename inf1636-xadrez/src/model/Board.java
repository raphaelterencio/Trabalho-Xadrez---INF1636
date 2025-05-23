package model;

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