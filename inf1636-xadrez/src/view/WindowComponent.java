package view;

import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;

class WindowComponent extends JComponent
{
	
	protected WindowComponent() {}
	
	protected void setUp()
	{
		setPreferredSize(new java.awt.Dimension(64 * 8, 64 * 8));
	}
	
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
        		if ( (row+column) % 2 == 0 )
        			g2d.setColor(java.awt.Color.WHITE);
        		else
        			g2d.setColor(java.awt.Color.BLACK);
        		
        		x = column * 64;
        		y = row * 64;
        		
        		g2d.drawRect(x, y, 64, 64);
        		g2d.fillRect(x, y, 64, 64);
        	}
        }
    }
}