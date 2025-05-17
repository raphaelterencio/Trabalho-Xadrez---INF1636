package model;

import java.util.Scanner;

class Board {

	private Peca[][] squares;
	
	Board()
	{
		squares = new Peca[8][8];
		launch();
	}
	
	void launch()
	{
		
		// Peões
		for(int j=0; j<8; j++)
		{
			squares[1][j] = new Peao('B', 1, j);
			squares[6][j] = new Peao('W', 6, j);
		}
		
		// Torres
		squares[0][0] = new Torre('B', 0, 0);
		squares[0][7] = new Torre('B', 0, 7);
		squares[7][0] = new Torre('W', 7, 0);
		squares[7][7] = new Torre('W', 7, 7);
		
		// Cavalos
		squares[0][1] = new Cavalo('B', 0, 1);
		squares[0][6] = new Cavalo('B', 0, 6);
		squares[7][1] = new Cavalo('W', 7, 1);
		squares[7][6] = new Cavalo('W', 7, 6);
		
		// Bispos
		squares[0][2] = new Bispo('B', 0, 2);
		squares[0][5] = new Bispo('B', 0, 5);
		squares[7][2] = new Bispo('W', 7, 2);
		squares[7][5] = new Bispo('W', 7, 5);
		
		// Rainhas
		squares[0][3] = new Rainha('B', 0, 3);
		squares[7][3] = new Rainha('W', 7, 3);
		
		// Reis
		squares[0][4] = new Rei('B', 0, 4);
		squares[7][4] = new Rei('B', 7, 4);
		
	}
	
	void print()
	{
		// Imprime a letra das colunas
		System.out.println("  a b c d e f g h");
		
		for(int i=0; i<8; i++) 
		{
			// Imprime o número das linhas
			System.out.print(i + " ");
			
			// Imprime peças/quadrados
			for(int j=0; j<8; j++)
			{
				if (squares[i][j] == null) System.out.print("- ");
				else System.out.print(squares[i][j].getSymbol() + " ");
			}
			
			// Quebra a linha ao final
			System.out.println();
		}
	}
	
	private boolean isInside(int i, int j) 
	{
		if (i>=0 && i<8 && j>=0 && j<8) return true;
		else return false;
	}
	
	private void peaoPromotion(int i, int j, int target_i, int target_j)
	{
		// Se a peça não for um peão, acaba por aqui
		if (!(squares[i][j] instanceof Peao)) return;
		
		// Confere se o peão chegou na última linha
		boolean hasAchieved = false;
		if ( (squares[i][j].color == 'W' && target_i == 0) || (squares[i][j].color == 'B' && target_i == 7) ) hasAchieved = true;
		if(!hasAchieved) return;
		
		Scanner scanner = new Scanner(System.in);
		boolean flag = true;
		Peca substitute = null;
		
		// Pede a nova peça para substituir o peão
		while(flag)
		{
			System.out.print("Parabéns! Seu chegou ao limite vertical do tabuleiro e foi promovido. Você deseja que ele vire um bispo (B), um cavalo (C), uma rainha (Q) ou uma torre (T)? ");
			String new_type = scanner.next().toUpperCase();
			
			switch(new_type) 
			{
				case "B":
					substitute = new Bispo(squares[i][j].getColor(), target_i, target_j);
					flag = false;
					break;
				case "C":
					substitute = new Cavalo(squares[i][j].getColor(), target_i, target_j);	
					flag = false;
					break;
				case "Q":
					substitute = new Rainha(squares[i][j].getColor(), target_i, target_j);	
					flag = false;
					break;
				case "T":
					substitute = new Torre(squares[i][j].getColor(), target_i, target_j);	
					flag = false;
					break;
				default:
					System.out.println("Escolha inválida.");
					break;
			}
		
		}
		
		squares[target_i][target_j] = substitute;
		scanner.close();
	}
	
	boolean movePiece(int i, int j, int target_i, int target_j, char turn_color) {
		
		// Confere se as posições(origem e destino) são válidas. Se não, acaba por aqui
		if (!isInside(i,j) || !isInside(target_i,target_j)) return false;
		
		// Se a peça não estiver no tabuleiro ou não estiver na vez do usuário, acaba por aqui
		if(squares[i][j] == null || squares[i][j].color != turn_color) return false;
		
		// Se o movimento não for legal, acaba por aqui
		if(!squares[i][j].move(target_i, target_j, squares)) return false;
			
		// Se a casa estiver ocupada por uma peça de outra cor, acaba por aqui
		if (squares[target_i][target_j] != null && squares[target_i][target_j].color == squares[i][j].color) return false;
		
		// Efetiva a mudança de posição da peça
		squares[target_i][target_j] = squares[i][j];
		squares[i][j].setPos(target_i, target_j);
		squares[i][j] = null;
		
		// Avalia se o peão foi promovido
		peaoPromotion(i, j, target_i, target_j);
		
		return true;
	}
	
} 