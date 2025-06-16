package controller;

import model.ModelAPI;
import view.ViewAPI;

public class Main
{
	public static void main(String[] args)
	{	
		ModelAPI model_api = new ModelAPI();
		ViewAPI view_api = new ViewAPI();
		
		model_api.newGame();
		view_api.openWindow();
	}
}