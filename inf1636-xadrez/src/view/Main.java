package view;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Jogo de Xadrez - INF1636");
        Interface painel = new Interface();

        frame.add(painel);
        frame.setSize(8 * 64 + 16, 8 * 64 + 39);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
