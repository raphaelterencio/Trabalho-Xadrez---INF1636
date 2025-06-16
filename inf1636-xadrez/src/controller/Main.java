package controller;

import model.ModelAPI;
import view.ViewAPI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class Main
{	
	static char round_color = 'W';
			
	static boolean isPieceSelected = false;
	static int selected_row = -1;
	static int selected_column = -1;
	
	static int origin_row = -1;
	static int origin_column = -1;
	
	static int backup_row = -1;
	static int backup_column = -1;
	
	static List<int[]> highlighted_path = new ArrayList<>();
	
	public static void main(String[] args)
	{			
		ViewAPI.openWindow();
		ViewAPI.registerObserver();
	
		userLeftClickHandler();
		userRightClickHandler();
		pawnPromotionHandler();
		menuHandler();
	}
	
	// Métodos get()
	
	public static char getRoundColor() { return round_color; }
	
	// Callbacks
	
	private static void userLeftClickHandler()
	{
        ViewAPI.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            	if (e.getButton() == MouseEvent.BUTTON1) 
            	{
	                int x = e.getX();
	                int y = e.getY();
	                
	                backup_row = selected_row = y / 64;
	                backup_column = selected_column = x / 64;
	                	                
	                if ( !isPieceSelected )
	                {
	                	if (ModelAPI.isThereAPiece(selected_row, selected_column))
	                	{
	                		if ( ModelAPI.getPieceColor(selected_row, selected_column) == round_color )
	                		{
		                		origin_row = selected_row;
		                		origin_column = selected_column;
		                		ViewAPI.highlightPath(selected_row, selected_column);
		                		highlighted_path = ModelAPI.getPossibleMoves(selected_row, selected_column);
		                		isPieceSelected = !isPieceSelected;
	                		}
	                	}
	                }
	                else 
	                {
	                	if ( isHighlighted(selected_row, selected_column) )
	                	{
	                		ModelAPI.movePiece(origin_row, origin_column, selected_row, selected_column);
	                		round_color = (round_color == 'W') ? 'B' : 'W';
	                		afterMoveProcedures();
	                		selected_row = -1; selected_column = -1;
	                		origin_row = -1; origin_column = -1;
	                		ViewAPI.clearHighlightedPath();
	                	}
	                	else
	                	{
	                		selected_row = -1; selected_column = -1;
	                		origin_row = -1; origin_column = -1;
	                		ViewAPI.clearHighlightedPath();
	                	}
	                	isPieceSelected = !isPieceSelected;
	                }
            	}
            }
        });
    }
	
	private static void userRightClickHandler()
	{
	    ViewAPI.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mousePressed(MouseEvent e) {
	            if (e.getButton() == MouseEvent.BUTTON3) 
	            {
	                ViewAPI.saveGameCallback();
	            }
	        }
	    });
	}
	
	private static void pawnPromotionHandler()
	{
		ViewAPI.getMenuItem("Queen").addActionListener(e -> formalizePawnPromotion("Queen"));
		ViewAPI.getMenuItem("Rook").addActionListener(e -> formalizePawnPromotion("Rook"));
		ViewAPI.getMenuItem("Bishop").addActionListener(e -> formalizePawnPromotion("Bishop"));
		ViewAPI.getMenuItem("Horse").addActionListener(e -> formalizePawnPromotion("Horse"));
	}
	
	private static void menuHandler()
	{
		ViewAPI.getButton("NewGame").addActionListener(e -> newGame());
		ViewAPI.getButton("LoadGame").addActionListener(e -> loadGame());
	}
	
	// Auxiliares
	
    private static boolean isHighlighted(int row, int column)
    {
        for (int[] pos : highlighted_path) {
            if (pos[0] == row && pos[1] == column) {
                return true;
            }
        }
        
        return false;
    }
    
    private static void afterMoveProcedures() 
    {
    	ModelAPI.isCheckMate(round_color);
    	ModelAPI.isCheck(round_color);
    	ModelAPI.isStaleMate(round_color);
    	ModelAPI.checkPawnPromotion();    	
    } 
    
    private static void formalizePawnPromotion(String piece)
    {
    	ModelAPI.promotePawn(piece, backup_row, backup_column);
    }
    
    private static void newGame()
    {
		ModelAPI.newGame();
		ViewAPI.showBoard();
    }
    
    private static void loadGame()
    {
    	String game_state = ViewAPI.loadGameCallback();
    	
    	if (game_state != null)
    	{
    		ModelAPI.newGame();
    		round_color = ModelAPI.setGameState(game_state);
    		ViewAPI.showBoard();
    	}
    }
}