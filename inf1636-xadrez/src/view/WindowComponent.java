package view;

import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class WindowComponent extends JComponent
{
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        
    }
}