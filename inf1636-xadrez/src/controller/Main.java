package controller;

import model.ModelAPI;
import view.ViewAPI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class Main
{
	static ModelAPI model_api;
	static ViewAPI view_api;
	
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
		model_api = new ModelAPI();
		view_api = new ViewAPI();
		
		view_api.openWindow();
		view_api.registerObserver();
	
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
        view_api.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            	if (e.getButton() == MouseEvent.BUTTON1) 
            	{
	                int x = e.getX();
	                int y = e.getY();
	                
	                backup_row = selected_row = y / 64;
	                backup_column = selected_column = x / 64;
	                	                
	                if ( !isPieceSelected )
	                {
	                	if (model_api.isThereAPiece(selected_row, selected_column))
	                	{
	                		if ( model_api.getPieceColor(selected_row, selected_column) == round_color )
	                		{
		                		origin_row = selected_row;
		                		origin_column = selected_column;
		                		view_api.highlightPath(selected_row, selected_column);
		                		highlighted_path = model_api.getPossibleMoves(selected_row, selected_column);
		                		isPieceSelected = !isPieceSelected;
	                		}
	                	}
	                }
	                else 
	                {
	                	if ( isHighlighted(selected_row, selected_column) )
	                	{
	                		model_api.movePiece(origin_row, origin_column, selected_row, selected_column);
	                		round_color = (round_color == 'W') ? 'B' : 'W';
	                		afterMoveProcedures();
	                		selected_row = -1; selected_column = -1;
	                		origin_row = -1; origin_column = -1;
	                		view_api.clearHighlightedPath();
	                	}
	                	else
	                	{
	                		selected_row = -1; selected_column = -1;
	                		origin_row = -1; origin_column = -1;
	                		view_api.clearHighlightedPath();
	                	}
	                	isPieceSelected = !isPieceSelected;
	                }
            	}
            }
        });
    }
	
	private static void userRightClickHandler()
	{
	    view_api.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mousePressed(MouseEvent e) {
	            if (e.getButton() == MouseEvent.BUTTON3) 
	            {
	                view_api.saveGameCallback();
	            }
	        }
	    });
	}
	
	private static void pawnPromotionHandler()
	{
		view_api.getMenuItem("Queen").addActionListener(e -> formalizePawnPromotion("Queen"));
		view_api.getMenuItem("Rook").addActionListener(e -> formalizePawnPromotion("Rook"));
		view_api.getMenuItem("Bishop").addActionListener(e -> formalizePawnPromotion("Bishop"));
		view_api.getMenuItem("Horse").addActionListener(e -> formalizePawnPromotion("Horse"));
	}
	
	private static void menuHandler()
	{
		view_api.getButton("NewGame").addActionListener(e -> newGame());
		view_api.getButton("LoadGame").addActionListener(e -> loadGame());
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
    	model_api.isCheckMate(round_color);
    	model_api.isCheck(round_color);
    	model_api.isStaleMate(round_color);
    	model_api.checkPawnPromotion();    	
    } 
    
    private static void formalizePawnPromotion(String piece)
    {
    	model_api.promotePawn(piece, backup_row, backup_column);
    }
    
    private static void newGame()
    {
		model_api.newGame();
		view_api.showBoard();
    }
    
    private static void loadGame()
    {
    	
    }
}