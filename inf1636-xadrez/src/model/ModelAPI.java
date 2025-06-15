package model;

import java.util.List;

public class ModelAPI
{
	Game game;
	
	public ModelAPI() {}
	
	public void newGame() { game = new Game(); }
	
	public char getRoundColor() { return game.getRoundColor(); }
	
	public boolean movePiece(int row, int column, int target_row, int target_column){ return game.movePiece(row, column, target_row, target_column); }
	
	public List<int[]> getPossibleMoves(int row, int column){ return game.getPossibleMoves(row, column); }
	
	public boolean isCheck() { return game.isCheck(game.getRoundColor()); } 
	
	public boolean isCheckMate() { return game.isCheckMate(game.getRoundColor()); } 
	
	public boolean checkPawnPromotion() { return game.checkPawnPromotion(); }
	
	protected boolean isStalemate() { return game.isStalemate(getRoundColor()); } 
	
}