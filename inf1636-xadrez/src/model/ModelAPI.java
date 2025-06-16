package model;

import java.util.List;

import controller.Event;

import java.util.ArrayList;

public class ModelAPI
{
	static Board board;
	private static List<Observer> observers = new ArrayList<>();
	
	public ModelAPI() { }
	
	public void newGame() { board = new Board(); }
	
	// Observer
	
    public void addObserver(Observer obs) { observers.add(obs); }

    public void removeObserver(Observer obs) { observers.remove(obs); }

    private void notifyObservers(Event event) 
    {
        for (Observer obs : observers) 
        {
            obs.update(event);
        }
    }
	
	// Testes
	
	public void testMode() { board.TestSetUp(); }
	
	public void testSetPiece(char type, char color, int row, int column) { board.TestSetPiece(type, color, row, column); }
		
	// Métodos get()
	
	public char getPieceColor(int row, int column) { return board.getPieceColor(row, column); }
	
	public char getPieceSymbol(int row, int column) { return board.getPieceSymbol(row, column); }
	
	public char setGameState(String game_state) { return board.setGameState(game_state); }
	
	public String getGameState() { return board.getGameState(); }
	
	// Peças
	
	public boolean isThereAPiece(int row, int column) { return board.isThereAPiece(row, column); }
	
	public boolean movePiece(int row, int column, int target_row, int target_column)
	{ 
		boolean flag = board.movePiece(row, column, target_row, target_column); 
		
		if (flag)
			notifyObservers(Event.getEvent("PIECE_MOVEMENT"));
		
		return flag;
	}
	
	// Movimentação
	
	public List<int[]> getPossibleMoves(int row, int column) { return board.getPossibleMoves(row, column); }
	
	// Regras
	
	public boolean isCheck(char color)
	{ 
		boolean flag = board.isCheck(color); 

		if (flag)
			notifyObservers(Event.getEvent("CHECK"));
		
		return flag;
		
	}
	
	public boolean isCheckMate(char color) 
	{ 
		boolean flag = board.isCheckMate(color); 
		
		if (flag)
			notifyObservers(Event.getEvent("CHECKMATE"));
		
		return flag;
	}
	
	public boolean isStaleMate(char color) 
	{ 
		boolean flag = board.isStaleMate(color); 
		
		if (flag)
			notifyObservers(Event.getEvent("STALEMATE"));
		
		return flag;
	}
	
	public boolean checkPawnPromotion() 
	{
		boolean flag = board.checkPawnPromotion(); 
		
		if (flag)
			notifyObservers(Event.getEvent("PAWN_PROMOTION"));
		
		return flag;
	}
	
	public void promotePawn(String piece, int row, int column) 
	{ 
		board.promotePawn(piece, row, column); 
		notifyObservers(Event.getEvent("PAWN_PROMOTED"));
	}
	
	// Movimentos especiais
	
	public boolean canCastle(char color, boolean isKingside) { return board.canCastle(color, isKingside); }
	
	public void performCastle(char color, boolean isKingside) { board.performCastle(color, isKingside); }
	
}