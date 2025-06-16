package controller;

import view.Interface;
import view.ViewAPI;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.MouseListener;
import model.ModelAPI;
public class Main {

    private static int selectedRow = -1;
    private static int selectedCol = -1;
    private static char currentTurn = 'W'; 
    public static void main(String[] args) {
        JFrame frame = new JFrame("Jogo de Xadrez");
        ModelAPI modelApi = new ModelAPI();
        modelApi.startGame();

        Interface interfaceView = new Interface(modelApi); 
        ViewAPI viewApi = new ViewAPI(interfaceView);     

    

        viewApi.adicionarClickListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = e.getX() / 64;
                int row = e.getY() / 64;

                if (selectedRow == -1) {
                    if (modelApi.isThereAPiece(row, col) && modelApi.getPieceColor(row, col) == currentTurn) {
                        selectedRow = row;
                        selectedCol = col;
                        viewApi.setMovimentos(modelApi.getPossibleMoves(row, col));
                        viewApi.setSelecionado(row, col);
                        viewApi.repaint();
                    }
                } else {
                    if (selectedRow == row && selectedCol == col) {
                        selectedRow = -1;
                        selectedCol = -1;
                        viewApi.clearMovimentos();
                        viewApi.repaint();
                        return;
                    }

                    if (modelApi.getPossibleMoves(selectedRow, selectedCol).stream().anyMatch(m -> m[0] == row && m[1] == col)) {
                    	modelApi.movePiece(selectedRow, selectedCol, row, col);
                        currentTurn = (currentTurn == 'W') ? 'B' : 'W';

                        // alerta de xeque
                        if (modelApi.isCheckMate()) {
                            JOptionPane.showMessageDialog(null, "Xeque-mate nas " + (currentTurn == 'W' ? "brancas" : "pretas") + "!");
                        } else if (modelApi.isStalemate()) {
                            JOptionPane.showMessageDialog(null, "Congelamento! As " + (currentTurn == 'W' ? "brancas" : "pretas") + " não têm movimentos válidos.");
                        } else if (modelApi.isCheck()) {
                            JOptionPane.showMessageDialog(null, "Xeque em " + (currentTurn == 'W' ? "brancas" : "pretas") + "!");
                        }


                    }

                    selectedRow = -1;
                    selectedCol = -1;
                    viewApi.clearMovimentos();
                    viewApi.setSelecionado(-1, -1);
                    viewApi.repaint();
                }
            }
        });

        frame.add(viewApi.getCanvas());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(8 * 64 + 16, 8 * 64 + 39); 
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
