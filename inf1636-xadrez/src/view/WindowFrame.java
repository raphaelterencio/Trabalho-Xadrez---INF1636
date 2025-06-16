package view;

import java.awt.Component;
import javax.swing.JFrame;

class WindowFrame extends JFrame
{
	private MenuComponent menu_component = new MenuComponent();
	private BoardComponent board_component = new BoardComponent();
	
	protected WindowFrame()
	{
		super("Xadrez");
		
		menu_component.setUp();
		board_component.setUp();
		
		showComponent(menu_component);
		
		setUp();
	}
	
	protected void showComponent(Component target_component)
	{
	    getContentPane().removeAll();
	    getContentPane().add(target_component);

	    revalidate();
	    repaint();
	}

	private void setUp()
	{
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	 
	// Métodos get()
	
	protected MenuComponent getMenuComponent() { return menu_component; } 
	
	protected BoardComponent getBoardComponent() { return board_component; } 
}