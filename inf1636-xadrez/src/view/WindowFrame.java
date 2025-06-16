package view;

import javax.swing.JFrame;

public class WindowFrame extends JFrame
{
	
	protected WindowFrame()
	{
		super("Xadrez");
		setUp();
	}
	
	protected void setUp()
	{
		setSize(64*8, 64*8);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}