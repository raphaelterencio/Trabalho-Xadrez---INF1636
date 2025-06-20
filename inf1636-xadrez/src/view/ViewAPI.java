package view;

import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;

import controller.Main;

public class ViewAPI 
{
	static WindowFrame window_frame;
	static Menu menu_panel;
	static Game game_panel;
	
	public ViewAPI() {}

	public static void openWindow()
	{ 
		window_frame = new WindowFrame();
		menu_panel = window_frame.getMenuPanel();
		game_panel = window_frame.getGamePanel();
	}
	
    public static void addMouseListener(MouseListener listener) { game_panel.addMouseListener(listener); }
    
    public static JMenuItem getMenuItem(String item) { return game_panel.getMenuItem(item); }
    
    public static JButton getButton(String button) { return menu_panel.getButton(button); }
    
    // Observer
    
    public static void registerObserver() { Main.addObserver(game_panel); }
    
    // Callbacks
    
    public static void highlightPath(int row, int column) { game_panel.highlightPath(row, column); }
    
    public static void clearHighlightedPath() { game_panel.clearHighlightedPath(); }
    
    // Telas
    
    public static void showMenu() { window_frame.showPanel(menu_panel); }
    
    public static void showBoard() { window_frame.showPanel(game_panel); }
    
}
