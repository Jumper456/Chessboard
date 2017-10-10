package net.yotvoo.chessboard;

class ChessField {

    private ChessPiece piece = null;
    private ChessBoard chessBoard;
    private int column;
    private int row;

    void clearPiece(){
        piece = null;
    }

    ChessBoard getChessBoard() {
        return chessBoard;
    }

    ChessField(ChessBoard chessBoard, int column, int row) {
        this.chessBoard = chessBoard;
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
