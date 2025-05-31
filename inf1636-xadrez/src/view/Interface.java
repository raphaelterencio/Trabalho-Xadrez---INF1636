package view;

import model.API;
import model.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class Interface extends JPanel {

    private static final int TILE_SIZE = 64;
    private static final int BOARD_SIZE = 8;
    private API api;
    private HashMap<String, BufferedImage> imageMap;

    private int selectedRow = -1;
    private int selectedCol = -1;

    public Interface() {
        this.api = new API();
        this.api.startGame();
        this.imageMap = loadImages();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = e.getX() / TILE_SIZE;
                int row = e.getY() / TILE_SIZE;

                if (selectedRow == -1) {
                	if (api.isThereAPiece(row, col)) {
                	    selectedRow = row;
                	    selectedCol = col;

                	    movimentosPossiveis.clear();
                	    for (int i = 0; i < BOARD_SIZE; i++) {
                	        for (int j = 0; j < BOARD_SIZE; j++) {
                	            if (api.canMovePiece(row, col, i, j)) {
                	                movimentosPossiveis.add(new int[]{i, j});
                	            }
                	        }
                	    }

                	    repaint();
                	}
                } else {
                    if (api.canMovePiece(selectedRow, selectedCol, row, col)) {
                        api.movePiece(selectedRow, selectedCol, row, col);
                    }
                    movimentosPossiveis.clear();
                    selectedRow = -1;
                    selectedCol = -1;
                    repaint();
                }
            }
        });
    }

    private HashMap<String, BufferedImage> loadImages() {
        HashMap<String, BufferedImage> map = new HashMap<>();

        String[] arquivos = {
            "CyanB", "CyanK", "CyanN", "CyanP", "CyanQ", "CyanR",
            "PurpleB", "PurpleK", "PurpleN", "PurpleP", "PurpleQ", "PurpleR"
        };

        for (String nome : arquivos) {
            String path = "/view/img2/" + nome + ".png";
            try {
                BufferedImage img = ImageIO.read(getClass().getResource(path));
                map.put(nome, img);
            } catch (IOException | IllegalArgumentException e) {
                System.out.println("Erro ao carregar imagem: " + path);
            }
        }

        return map;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if ((row + col) % 2 == 0)
                    g2d.setColor(Color.LIGHT_GRAY);
                else
                    g2d.setColor(Color.DARK_GRAY);
                g2d.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);

                Piece piece = api.getPiece(row, col);
                if (piece != null) {
                	String tipo = mapSymbolToNome(piece.getSymbol());
                	String prefixo = piece.getColor() == 'W' ? "Cyan" : "Purple";
                	BufferedImage img = imageMap.get(prefixo + tipo);
                    if (img != null)
                        g2d.drawImage(img, col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
                }
            }
            
        }

        if (selectedRow != -1) {
            g2d.setColor(Color.YELLOW);
            g2d.drawRect(selectedCol * TILE_SIZE, selectedRow * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }
        g2d.setColor(new Color(0, 255, 0, 120)); // Verde translúcido
        for (int[] pos : movimentosPossiveis) {
            int r = pos[0];
            int c = pos[1];
            g2d.fillRect(c * TILE_SIZE, r * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }
    }

    private String mapSymbolToNome(char symbol) {
        return switch (symbol) {
            case 'P' -> "P";
            case 'T' -> "R"; // Rook
            case 'H' -> "N"; // Knight (cavalo)
            case 'B' -> "B";
            case 'Q' -> "Q";
            case 'K' -> "K";
            default -> "";
        };
    }

    private java.util.List<int[]> movimentosPossiveis = new java.util.ArrayList<>();
}
