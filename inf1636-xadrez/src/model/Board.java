package model;

class Board {

	private Piece[][] houses;
	private Piece[] captured_whites;
	private Piece[] captured_blacks;
	private int count_captured_whites;
	private int count_captured_blacks;
	
	Board()
	{
		houses = new Piece[8][8];
		captured_whites = new Piece[16];
		captured_blacks = new Piece[16];
		count_captured_whites = 0;
		count_captured_blacks = 0;
		launch();
	}
	
	// Coloca as peças no tabuleiro
	void launch()
	{
		
		// Peões
		for(int column=0; column<8; column++)
		{
			houses[1][column] = new Pawn('B', 1, column);
			houses[6][column] = new Pawn('W', 6, column);
		}
		
		// Torres
		houses[0][0] = new Rook('B', 0, 0);
		houses[0][7] = new Rook('B', 0, 7);
		houses[7][0] = new Rook('W', 7, 0);
		houses[7][7] = new Rook('W', 7, 7);
		
		// Cavalos
		houses[0][1] = new Horse('B', 0, 1);
		houses[0][6] = new Horse('B', 0, 6);
		houses[7][1] = new Horse('W', 7, 1);
		houses[7][6] = new Horse('W', 7, 6);
		
		// Bispos
		houses[0][2] = new Bishop('B', 0, 2);
		houses[0][5] = new Bishop('B', 0, 5);
		houses[7][2] = new Bishop('W', 7, 2);
		houses[7][5] = new Bishop('W', 7, 5);
		
		// Rainhas
		houses[0][3] = new Queen('B', 0, 3);
		houses[7][3] = new Queen('W', 7, 3);
		
		// Reis
		houses[0][4] = new King('B', 0, 4);
		houses[7][4] = new King('B', 7, 4);
		
	}
	
	void print()
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
	
	// Confere se uma peça está dentro do tabuleiro
	private boolean isInside(int row, int column) 
	{
		if (row>=0 && row<8 && column>=0 && column<8) return true;
		else return false;
	}
	
	boolean movePiece(int row, int column, int target_row, int target_column, char turn_color) {
		
		Piece origin = houses[row][column];
		Piece destiny = houses[target_row][target_column];
		
		// Confere se as posições(origem e destino) são válidas. Se não, acaba por aqui
		if (!isInside(row,column) || !isInside(target_row,target_column)) return false;
		
		// Se a peça não estiver no tabuleiro ou a peça não for do usuário, acaba por aqui
		if(origin == null || origin.color != turn_color) return false;
		
		// Se o movimento não for legal, acaba por aqui
		if(!origin.canMove(target_row, target_column, houses)) return false;
		
		// Se a casa de destino estiver ocupada
		if (destiny != null)
		{
			// Se for uma peça da mesma cor
			if(origin.getColor() == destiny.getColor()) return false;
			
			// Se for uma peça da outra cor
			if (destiny.getColor() == 'W')
				captured_whites[count_captured_whites++] = destiny;
			else
				captured_blacks[count_captured_blacks++] = destiny;
		}

		// Efetiva a mudança de posição da peça
		houses[target_row][target_column] = houses[row][column];
		houses[row][column].setPos(target_row, target_column);
		houses[row][column] = null;
		
		return true;
	}
	
} 