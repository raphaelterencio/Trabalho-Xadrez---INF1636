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
	
	public ViewAPI() {}

	public static void openWindow()
	{ 
		window_frame = new WindowFrame();
		menu_component = window_frame.getMenuComponent();
		board_component = window_frame.getBoardComponent();
	}
	
    public static void addMouseListener(MouseListener listener) { board_component.addMouseListener(listener); }
    
    public static JMenuItem getMenuItem(String item) { return board_component.getMenuItem(item); }
    
    public static JButton getButton(String button) { return menu_component.getButton(button); }
    
    // Observer
    
    public static void registerObserver() { ModelAPI.addObserver(board_component); }
    
    // Callbacks
    
    public static void highlightPath(int row, int column) { board_component.highlightPath(row, column); }
    
    public static void clearHighlightedPath() { board_component.clearHighlightedPath(); }
    
    public static String loadGameCallback() { return board_component.loadGameCallback(); }
    
    public static void saveGameCallback() { board_component.saveGameCallback(); } 
    
    // Telas
    
    public static void showMenu() { window_frame.showComponent(menu_component); }
    
    public static void showBoard() { window_frame.showComponent(board_component); }
    
}
