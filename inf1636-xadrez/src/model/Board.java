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
		launch();
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
		houses[7][4] = new King('B', 7, 4);
		
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
	
	protected void print()
	{
		// Imprime a letra das colunas
		System.out.println("  a b c d e f g h");
		
		for(int row=0; row<8; row++) 
		{
			// Imprime o número das linhas
			System.out.print(row + " ");
			
			// Imprime peças/quadrados
			for(int column=0; column<8; column++)
			{
				if (houses[row][column] == null) System.out.print("- ");
				else System.out.print(houses[row][column].getSymbol() + " ");
			}
			
			// Quebra a linha ao final
			System.out.println();
		}
		
		// Imprime as peças capturadas
		
		if (count_captured_whites > 0)
		{
			System.out.println();
			System.out.print("Peças brancas capturadas: ");
		    for (int i = 0; i < count_captured_whites; i++) System.out.print(captured_whites[i].getSymbol() + " ");
		}
		
		if (count_captured_blacks > 0)
		{
			System.out.println();
			System.out.print("Peças pretas capturadas: ");
		    for (int i = 0; i < count_captured_blacks; i++) System.out.print(captured_blacks[i].getSymbol() + " ");
		}
		
		// Quebra a linha ao final
		System.out.println();
	}
	
	protected Piece getPiece(int row, int column) 
	{ 
		return houses[row][column]; 
	}
	
	protected void movePiece(int row, int column, int target_row, int target_column)
	{
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
	
	protected boolean isCheck(int color)
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
	
} 