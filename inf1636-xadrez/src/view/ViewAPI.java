package view;

import java.awt.event.MouseListener;
import javax.swing.JMenuItem;
import model.ModelAPI;

public class ViewAPI 
{
	static WindowFrame window_frame;
	static WindowComponent window_component;
	
	static ModelAPI model_api = new ModelAPI();
	
	public ViewAPI() {}

	public void openWindow()
	{ 
		window_frame = new WindowFrame();
		window_component = window_frame.getWindowComponent();
	}
	
    public void addMouseListener(MouseListener listener) { window_component.addMouseListener(listener); }
    
    public JMenuItem getMenuItem(String item) { return window_component.getMenuItem(item); }
    
    // Observer
    
    public void registerObserver() { model_api.addObserver(window_component); }
    
    // Calbacks
    
    public void highlightPath(int row, int column) { window_component.highlightPath(row, column); }
    
    public void clearHighlightedPath() { window_component.clearHighlightedPath(); }
    
    public void saveGameCallback() { window_component.saveGameCallback(); } 
    
}
