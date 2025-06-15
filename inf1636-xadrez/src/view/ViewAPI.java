package view;

import java.awt.event.MouseListener;
import java.util.List;

public class ViewAPI {
    private final Interface tabuleiro;

    public ViewAPI() {
        this.tabuleiro = new Interface();
    }

    public Interface getCanvas() {
        return tabuleiro;
    }

    public void setMovimentos(List<int[]> movimentos) {
    	tabuleiro.setMovimentos(movimentos);
    }

    public void clearMovimentos() {
    	tabuleiro.clearMovimentos();
    }

    public void setSelecionado(int row, int col) {
    	tabuleiro.setSelecionado(row, col);
    }

    public void adicionarClickListener(MouseListener listener) {
    	tabuleiro.adicionarClickListener(listener);
    }

    public void repaint() {
    	tabuleiro.repaint();
    }
}
