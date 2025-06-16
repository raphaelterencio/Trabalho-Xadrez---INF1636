package view;

import javax.swing.JFrame;

class WindowFrame extends JFrame
{
	WindowComponent window_component = new WindowComponent();
	
	protected WindowFrame()
	{
		super("Xadrez");
		
		window_component.setUp();
		add(window_component);
		
		setUp();
	}
	
	protected void setUp()
	{
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}