package view;

import java.util.HashMap;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Images
{
	protected HashMap<String, BufferedImage> images;
	
	protected Images()) {}
	
    protected HashMap<String, BufferedImage> loadImages() 
    {
        HashMap<String, BufferedImage> map = new HashMap<>();
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
                    map.put(cor + tipo, img);
                } 
                catch (IOException | IllegalArgumentException e) { System.out.println("Erro ao carregar imagem: " + caminho); }
            }
        }

        return map;
    }
	
}