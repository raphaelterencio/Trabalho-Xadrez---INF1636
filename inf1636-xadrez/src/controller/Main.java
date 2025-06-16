package controller;

import model.ModelAPI;
import view.ViewAPI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main
{
	static ModelAPI model_api;
	static ViewAPI view_api;
	
	public static void main(String[] args)
	{	
		model_api = new ModelAPI();
		view_api = new ViewAPI();
		
		model_api.newGame();
		view_api.openWindow();
		
		userClickHandler();
	}
	
	private static void userClickHandler()
	{
        view_api.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            	
                int x = e.getX();
                int y = e.getY();
                
                int row = y / 64;
                int column = x / 64;

                if (model_api.isThereAPiece(row, column))
                	view_api.highlightPath(row, column);
                else
                	view_api.clearHighlightedPath();
            }
        });
    }
}