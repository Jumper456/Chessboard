package net.yotvoo.chessboard;

class ChessField {

    private ChessPiece piece = null;
    private ChessBoard chessBoard;
    private int column;
    private int row;
    private String coordinates;

    ChessField(ChessBoard chessBoard, int column, int row, String coordinates) {
        this.chessBoard = chessBoard;
        this.column = column;
        this.row = row;
        this.coordinates = coordinates;
    }

    void clearPiece(){
        piece = null;
    }

    ChessBoard getChessBoard() {
        return chessBoard;
    }

    int getColumn() {
        return column;
    }

    int getRow() {
        return row;
    }

    String getCoordinates(){
        return coordinates;
    }

    ChessPiece getPiece() {
        return piece;
    }

    void setPiece(ChessPiece piece) {
        this.piece = piece;
    }

}
