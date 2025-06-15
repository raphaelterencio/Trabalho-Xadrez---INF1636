package view;

import model.ModelAPI;
import java.util.HashMap;
import java.util.List;

public class ViewAPI {

    private final Interface tabuleiro;
    private final ModelAPI model;

    public ViewAPI(Interface tabuleiro) {
        this.tabuleiro = tabuleiro;
        this.tabuleiro.setAPI(this);
        this.model = new ModelAPI();
        this.model.startGame();
    }

    public void adicionarClickListener(java.awt.event.MouseListener listener) {
        tabuleiro.adicionarClickListener(listener);
    }

    public void setSelecionado(int row, int col) {
        tabuleiro.setSelecionado(row, col);
    }

    public void setMovimentos(List<int[]> movimentos) {
        tabuleiro.setMovimentos(movimentos);
    }

    public void clearMovimentos() {
        tabuleiro.clearMovimentos();
    }

    public int getSelectedRow() {
        return tabuleiro.getSelectedRow();
    }

    public int getSelectedCol() {
        return tabuleiro.getSelectedCol();
    }

    public List<int[]> getPossibleMoves(int row, int col) {
        return model.getPossibleMoves(row, col);
    }

    public boolean isThereAPiece(int row, int col) {
        return model.isThereAPiece(row, col);
    }

    public void movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        model.movePiece(fromRow, fromCol, toRow, toCol);
    }

    public char getPieceColor(int row, int col) {
        return model.getPieceColor(row, col);
    }

    public char getPieceSymbol(int row, int col) {
        return model.getPieceSymbol(row, col);
    }

    public boolean isCheck(char color) {
        return model.isCheck(color);
    }

    public boolean isCheckMate(char color) {
        return model.isCheckMate(color);
    }

    public boolean isStalemate(char color) {
        return model.isStalemate(color);
    }

    public HashMap<String, java.awt.image.BufferedImage> getImageMap() {
        return tabuleiro.getImageMap();
    }

    public Interface getInterface() {
        return tabuleiro;
    }
}
