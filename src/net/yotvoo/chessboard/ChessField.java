package net.yotvoo.chessboard;

public class ChessField {

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

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    String getCoordinates(){
        return coordinates;
    }

    public ChessPiece getPiece() {
        return piece;
    }

    void setPiece(ChessPiece piece) {
        this.piece = piece;
    }

}
