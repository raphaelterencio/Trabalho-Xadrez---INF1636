 package view;

import view.ViewAPI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.imageio.ImageIO;
import model.ModelAPI;

public class Interface extends Canvas {

    private static final int TILE_SIZE = 64;
    private static final int BOARD_SIZE = 8;

    private final ModelAPI api;
    private final HashMap<String, BufferedImage> imageMap;

    private int selectedRow = -1;
    private int selectedCol = -1;
    private List<int[]> movimentosPossiveis = new java.util.ArrayList<>();

    public Interface(ModelAPI api) {
    	this.api = api;
        this.imageMap = loadImages();
        this.api.startGame(); 
        setSize(BOARD_SIZE * TILE_SIZE, BOARD_SIZE * TILE_SIZE);
    }

    private HashMap<String, BufferedImage> loadImages() {
        HashMap<String, BufferedImage> map = new HashMap<>();
        String[] nomes = {"P", "R", "N", "B", "Q", "K"};
        String[] cores = {"Cyan", "Purple"};

        for (String cor : cores) {
            for (String tipo : nomes) {
                String caminho = "/view/img/" + cor + tipo + ".png";
                try {
                    BufferedImage img = ImageIO.read(getClass().getResourceAsStream(caminho));
                    map.put(cor + tipo, img);
                } catch (IOException | IllegalArgumentException e) {
                    System.out.println("Erro ao carregar imagem: " + caminho);
                }
            }
        }

        return map;
    }

    @Override
    public void paint(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                final int currentRow = row;
                final int currentCol = col;

                // Fundo do tabuleiro
                if ((currentRow + currentCol) % 2 == 0)
                    g2d.setColor(Color.LIGHT_GRAY);
                else
                    g2d.setColor(Color.DARK_GRAY);
                g2d.fillRect(currentCol * TILE_SIZE, currentRow * TILE_SIZE, TILE_SIZE, TILE_SIZE);

                // Destaque de movimentos possíveis
                if (movimentosPossiveis.stream().anyMatch(pos -> pos[0] == currentRow && pos[1] == currentCol)) {
                    g2d.setColor(new Color(255, 255, 0, 100));
                    g2d.fillRect(currentCol * TILE_SIZE, currentRow * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }

                // Desenho da peça
                if (api.isThereAPiece(currentRow, currentCol)) {
                    char symbol = api.getPieceSymbol(currentRow, currentCol);
                    char color = api.getPieceColor(currentRow, currentCol);

                    String tipo = switch (symbol) {
                        case 'P' -> "P";
                        case 'R' -> "R";
                        case 'H' -> "N";
                        case 'B' -> "B";
                        case 'Q' -> "Q";
                        case 'K' -> "K";
                        default -> "";
                    };

                    String cor = (color == 'W') ? "Cyan" : "Purple";
                    String chave = cor + tipo;
                    BufferedImage img = imageMap.get(chave);

                    if (img != null)
                        g2d.drawImage(img, currentCol * TILE_SIZE, currentRow * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
                }
            }
        }

        // Realça a casa da peça selecionada
        if (selectedRow != -1 && selectedCol != -1) {
            g2d.setColor(Color.YELLOW);
            g2d.drawRect(selectedCol * TILE_SIZE, selectedRow * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }
    }

    protected void setMovimentos(List<int[]> movimentos) {
        this.movimentosPossiveis = movimentos;
    }

    protected void clearMovimentos() {
        this.movimentosPossiveis.clear();
    }

    protected void setSelecionado(int row, int col) {
        this.selectedRow = row;
        this.selectedCol = col;
    }


    protected void adicionarClickListener(MouseListener listener) {
        addMouseListener(listener);
    }
}