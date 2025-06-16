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
	
	static boolean isPieceSelected = false;
	static int selected_row = -1;
	static int selected_column = -1;
	
	static int origin_row = -1;
	static int origin_column = -1;
	
	static List<int[]> highlighted_path = new ArrayList<>();
	
	public static void main(String[] args)
	{	
		model_api = new ModelAPI();
		view_api = new ViewAPI();
		
		model_api.newGame();
		view_api.openWindow();
		
		userClickHandler();
	}
	
	// Callbacks
	
	private static void userClickHandler()
	{
        view_api.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            	
                int x = e.getX();
                int y = e.getY();
                
                selected_row = y / 64;
                selected_column = x / 64;
                
                if ( !isPieceSelected )
                {
                	if (model_api.isThereAPiece(selected_row, selected_column))
                	{
                		origin_row = selected_row;
                		origin_column = selected_column;
                		view_api.highlightPath(selected_row, selected_column);
                		highlighted_path = model_api.getPossibleMoves(selected_row, selected_column);
                		isPieceSelected = !isPieceSelected;
                	}
                }
                else 
                {
                	if ( isHighlighted(selected_row, selected_column) )
                	{
                		model_api.movePiece(origin_row, origin_column, selected_row, selected_column);
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
        });
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
}