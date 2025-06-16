package view;

import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import model.ModelAPI;

public class ViewAPI 
{
	static WindowFrame window_frame;
	static MenuComponent menu_component;
	static BoardComponent board_component;
	
	static ModelAPI model_api = new ModelAPI();
	
	public ViewAPI() {}

	public void openWindow()
	{ 
		window_frame = new WindowFrame();
		menu_component = window_frame.getMenuComponent();
		board_component = window_frame.getBoardComponent();
	}
	
    public void addMouseListener(MouseListener listener) { board_component.addMouseListener(listener); }
    
    public JMenuItem getMenuItem(String item) { return board_component.getMenuItem(item); }
    
    public JButton getButton(String button) { return menu_component.getButton(button); }
    
    // Observer
    
    public void registerObserver() { model_api.addObserver(board_component); }
    
    // Callbacks
    
    public void highlightPath(int row, int column) { board_component.highlightPath(row, column); }
    
    public void clearHighlightedPath() { board_component.clearHighlightedPath(); }
    
    public void saveGameCallback() { board_component.saveGameCallback(); } 
    
    // Telas
    
    public void showMenu() { window_frame.showComponent(menu_component); }
    
    public void showBoard() { window_frame.showComponent(board_component); }
    
}
