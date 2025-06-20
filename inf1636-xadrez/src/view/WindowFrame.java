package view;

import java.awt.CardLayout;
import javax.swing.JFrame;

class WindowFrame extends JFrame
{
	private Menu menu_panel = new Menu();
	private Game game_panel = new Game();
	private CardLayout layout = new CardLayout();
	
	protected WindowFrame()
	{
		super("Xadrez");
		
		getContentPane().setLayout(layout);
		
		menu_panel.setUp();
		game_panel.setUp();
		
		getContentPane().add(menu_panel, "MENU");
		getContentPane().add(game_panel, "GAME");
		
		showPanel("MENU");
		
		setUp();
	}
	
	protected void showPanel(String panel)
	{
		layout.show(getContentPane(), panel);
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
	
	protected Menu getMenuPanel() { return menu_panel; } 
	
	protected Game getGamePanel() { return game_panel; } 
}