package view;

import model.ModelAPI;

import java.util.List;

public class ViewAPI {
    private ModelAPI model;

    public ViewAPI() {
        this.model = new ModelAPI();
        this.model.startGame();
    }
    public void startGame() {
        model.startGame();
    }


    public boolean isThereAPiece(int row, int col) {
        return model.isThereAPiece(row, col);
    }

    public List<int[]> getPossibleMoves(int row, int col) {
        return model.getPossibleMoves(row, col);
    }

    public char getPieceColor(int row, int col) {
        return model.getPieceColor(row, col);
    }

    public void movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        model.movePiece(fromRow, fromCol, toRow, toCol);
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



}
