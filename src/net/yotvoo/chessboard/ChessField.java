package net.yotvoo.chessboard;

class ChessField {

    private ChessPiece piece = null;
    private int column;
    private int row;


    ChessField(int column, int row) {
        this.column = column;
        this.row = row;
    }

    int getColumn() {
        return column;
    }

    int getRow() {
        return row;
    }

    ChessPiece getPiece() {
        return piece;
    }

    void setPiece(ChessPiece piece) {
        this.piece = piece;
    }



}
