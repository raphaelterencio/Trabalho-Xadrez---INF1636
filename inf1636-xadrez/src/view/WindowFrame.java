package view;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.util.HashMap;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class WindowFrame extends JFrame
{
    private static final int TILE_SIZE = 64;
    private static final int BOARD_SIZE = 8;
    
    protected HashMap<String, BufferedImage> image_map;
	
	protected WindowFrame()
	{
		super("Xadrez");
		
		setUp();
		loadImages();
	}
	
	protected void setUp()
	{
		setSize(BOARD_SIZE * TILE_SIZE, BOARD_SIZE * TILE_SIZE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
    
    protected void loadImages() 
    {
        String[] nomes = {"P", "R", "H", "B", "Q", "K"};
        String[] cores = {"Cyan", "Purple"};

        for (String cor : cores) 
        {
            for (String tipo : nomes) 
            {
                String caminho = "/view/img/" + cor + tipo + ".png";
                try 
                {
                    BufferedImage img = ImageIO.read(getClass().getResourceAsStream(caminho));
                    image_map.put(cor + tipo, img);
                } 
                catch (IOException | IllegalArgumentException e) { System.out.println("Erro ao carregar imagem: " + caminho); }
            }
        }
    }
    
    
	
}