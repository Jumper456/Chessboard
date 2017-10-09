package net.yotvoo.chessboard;

public class ChessPiece {
    private String name = "";
    private String color = "";
    private String symbol = "";
    private PieceType pieceType = null;
    private PieceColor pieceColor = null;

    public enum PieceType {
        ROOK, KNIGHT, BISHOP, QUEEN, KING, PAWN
    }

    public enum PieceColor {
        WHITE, BLACK
    }


    String getSymbol() { return symbol; }

    String getName() {
        return name;
    }

    String getColor() {
        return color;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }

    ChessPiece(PieceType pieceType, PieceColor pieceColor) {
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;

        if (pieceColor == PieceColor.WHITE) {
            color = "W";
            switch (pieceType) {
                case KING:
                    symbol = "\u2654";
                    name = "K";
                    break;
                case QUEEN:
                    symbol = "\u2655";
                    name = "Q";
                    break;
                case ROOK:
                    symbol = "\u2656";
                    name = "R";
                    break;
                case BISHOP:
                    symbol = "\u2657";
                    name = "B";
                    break;
                case KNIGHT:
                    symbol = "\u2658";
                    name = "N";
                    break;
                case PAWN:
                    symbol = "\u2659";
                    name = "P";
                    break;
            }
        }
        else{
            color = "B";
            switch (pieceType) {
                case KING:
                    symbol = "\u265A";
                    name = "K";
                    break;
                case QUEEN:
                    symbol = "\u265B";
                    name = "Q";
                    break;
                case ROOK:
                    symbol = "\u265C";
                    name = "R";
                    break;
                case BISHOP:
                    symbol = "\u265D";
                    name = "B";
                    break;
                case KNIGHT:
                    symbol = "\u265E";
                    name = "N";
                    break;
                case PAWN:
                    symbol = "\u265F";
                    name = "P";
                    break;
            }
        }
    }

    /* Obsolete constructor, left for any case
    public ChessPiece(String name, String color) {
        this.name = name;
        this.color = color;

    }
    */

}
