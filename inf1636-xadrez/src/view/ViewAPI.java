package view;

import java.awt.event.MouseListener;

public class ViewAPI 
{
	static WindowFrame window_frame;
	static WindowComponent window_component;
	
	public ViewAPI() {}

	public void openWindow()
	{ 
		window_frame = new WindowFrame();
		window_component = window_frame.getWindowComponent();
	}
	
    public void addMouseListener(MouseListener listener) {
        window_component.addMouseListener(listener);
    }
}