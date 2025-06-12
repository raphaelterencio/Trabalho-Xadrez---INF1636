package controller;

import view.Interface;
import view.ViewAPI;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.MouseListener;

public class Main {

    private static int selectedRow = -1;
    private static int selectedCol = -1;
    private static char currentTurn = 'W'; 
    public static void main(String[] args) {
        JFrame frame = new JFrame("Jogo de Xadrez");
        Interface tabuleiro = new Interface();
        ViewAPI api = tabuleiro.getApi();

        tabuleiro.adicionarClickListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = e.getX() / 64;
                int row = e.getY() / 64;

                if (selectedRow == -1) {
                    if (api.isThereAPiece(row, col) && api.getPieceColor(row, col) == currentTurn) {
                        selectedRow = row;
                        selectedCol = col;
                        tabuleiro.setMovimentos(api.getPossibleMoves(row, col));
                        tabuleiro.setSelecionado(row, col);
                        tabuleiro.repaint();
                    }
                } else {
                    if (selectedRow == row && selectedCol == col) {
                        selectedRow = -1;
                        selectedCol = -1;
                        tabuleiro.clearMovimentos();
                        tabuleiro.repaint();
                        return;
                    }

                    if (api.getPossibleMoves(selectedRow, selectedCol).stream().anyMatch(m -> m[0] == row && m[1] == col)) {
                        api.movePiece(selectedRow, selectedCol, row, col);
                        currentTurn = (currentTurn == 'W') ? 'B' : 'W';

                        // alerta de xeque
                        if (api.isCheck(currentTurn)) {
                            JOptionPane.showMessageDialog(null, "Xeque em " + (currentTurn == 'W' ? "brancas" : "pretas") + "!");
                        }
                    }

                    selectedRow = -1;
                    selectedCol = -1;
                    tabuleiro.clearMovimentos();
                    tabuleiro.setSelecionado(-1, -1);
                    tabuleiro.repaint();
                }
            }
        });

        frame.add(tabuleiro);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(8 * 64 + 16, 8 * 64 + 39); 
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
