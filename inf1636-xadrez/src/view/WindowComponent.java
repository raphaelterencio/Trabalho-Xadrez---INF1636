package view;

// JSwing
import javax.swing.JComponent;

// Java 2d
import java.awt.Graphics;
import java.awt.Graphics2D;

// Imagens
import java.util.HashMap;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

// ModelAPI
import model.ModelAPI;

class WindowComponent extends JComponent
{
	private HashMap<String, BufferedImage> image_map = new HashMap<>();
	ModelAPI model_api = new ModelAPI();
	
	protected WindowComponent() {}
	
	protected void setUp()
	{
		setPreferredSize(new java.awt.Dimension(64 * 8, 64 * 8));
		loadImages();
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
        		
        		g2d.fillRect(x, y, 64, 64);
        		
        		char color = model_api.getPieceColor(row, column);
        		char symbol = model_api.getPieceSymbol(row, column);
        		
                if (symbol != '-') {
                	
                	String color_string = colorString(color);
                	String piece_string = pieceString(symbol);
                	
                	String image_name = color_string + piece_string;
                	
                    BufferedImage pieceImage = image_map.get(image_name);
                    g2d.drawImage(pieceImage, x, y, 64, 64, this);
                }
        	}
        }
    }
    
    private void loadImages() 
    {
        String[] colors = {"White", "Black"};
        String[] pieces = {"Bishop", "Horse", "King", "Pawn", "Queen", "King"};

        for (String color : colors) 
        {
            for (String piece : pieces) 
            {
                String path = "/view/images/" + color + piece + ".png";
                try 
                {
                    BufferedImage image = ImageIO.read(getClass().getResourceAsStream(path));
                    image_map.put(color + piece, image);
                } 
                catch (IOException | IllegalArgumentException e)
                {
                    System.out.println("Erro ao carregar imagem: " + path);
                }
            }
        }
    }
    
    private String colorString(char color)
    {
    	switch (color) 
    	{
			case 'W':
				return "White";
			case 'B':
				return "Black";
    	}
		return null;
    }
    
    private String pieceString(char symbol)
    {
    	switch (symbol)
    	{
    		case 'B':
    			return "Bishop";
    		case 'H':
    			return "Horse";
    		case 'K':
    			return "King";
    		case 'P':
    			return "Pawn";
    		case 'Q':
    			return "Queen";
    		case 'R':
    			return "Rook";
    	}
    	return null;
    }
}