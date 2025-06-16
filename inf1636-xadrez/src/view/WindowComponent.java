package view;

import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class WindowComponent extends JComponent
{
	
	protected WindowComponent() {}
	
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        
        int x, y;
        // Desenha as casas
        for(int row=0; row<8; row++) 
        {
        	for(int column=0; column<8; column++)
        	{
        		x = column * 64;
        		y = row * 64;
        		
        		g2d.drawRect(x, y, row, column);
        	}
        }
    }
}