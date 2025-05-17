package model;

// Restringe o acesso do usuário a comandos estratégicos
public class API
{
	private static API object = null;
	private Board board;
	private char turn_color;
	
	// Inicializa o jogo
	private API() { board = new Board(); turn_color = 'W'; }
	
	public char getTurnColor() { return turn_color; }
	public void printBoard() { board.print(); }
	
	// "Construtor" que pode ser acessado pelo usuário
	public static API getObject() {
		if (object == null) object = new API();
		return object;
	}
	
	// Cria um novo jogo
	public void restart()
	{
		board = new Board();
		turn_color = 'W';
	}
	
	public boolean movePeca(int row, int column, int target_row, int target_column)
	{
		boolean flag = board.movePiece(row, column, target_row, target_column, turn_color);
		if (flag)
		{
			if (turn_color == 'W') turn_color = 'B';
			else turn_color = 'W';
		}
		return flag;
		
	}
	
}