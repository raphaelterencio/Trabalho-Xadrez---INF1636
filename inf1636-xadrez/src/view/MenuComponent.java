package view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JComponent;

class MenuComponent extends JComponent
{
	private JButton newGame;
	private JButton loadGame;
	
	protected MenuComponent() {}
	
	protected void setUp()
	{
		setPreferredSize(new Dimension(64 * 8, 64 * 8));
		setLayout(null);

		newGame = new JButton("Novo Jogo");
		loadGame = new JButton("Carregar Jogo");

		int frameWidth = 64 * 8;
		int frameHeight = 64 * 8;

		int buttonWidth = 150;
		int buttonHeight = 40;
		int spacing = 20;

		int totalHeight = 2 * buttonHeight + spacing;

		int startY = (frameHeight - totalHeight) / 2;
		int centerX = (frameWidth - buttonWidth) / 2;

		newGame.setBounds(centerX, startY, buttonWidth, buttonHeight);
		loadGame.setBounds(centerX, startY + buttonHeight + spacing, buttonWidth, buttonHeight);

		add(newGame);
		add(loadGame);
	}
	
	@Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
    }

}