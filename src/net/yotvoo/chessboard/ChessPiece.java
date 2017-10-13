package net.yotvoo.chessboard;

public class ChessPiece {

    //String nameStr is used for plain text version of the chessboard
    private String nameStr = "";
    //String colorStr is used for plain text version of the chessboard
    private String colorStr = "";
    //String symbol is used to contain the unicode chess symbol font
    private String symbol = "";

    //pieceType and pieceColor are used to distinguish what type of piece is it and of what colorStr
    private PieceType pieceType = null;
    private PieceColor pieceColor = null;

    public enum PieceType {
        ROOK, KNIGHT, BISHOP, QUEEN, KING, PAWN
    }

    public enum PieceColor {
        WHITE, BLACK
    }

    String getSymbol() {
        return symbol;
    }

    String getNameStr() {
        return nameStr;
    }

    String getColorStr() {
        return colorStr;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }


    /*
    *  Constructor
    */
    ChessPiece(PieceType pieceType, PieceColor pieceColor) {
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;

        if (pieceColor == PieceColor.WHITE) {
            colorStr = "W";
            switch (pieceType) {
                case KING:
                    symbol = "\u2654";
                    nameStr = "K";
                    break;
                case QUEEN:
                    symbol = "\u2655";
                    nameStr = "Q";
                    break;
                case ROOK:
                    symbol = "\u2656";
                    nameStr = "R";
                    break;
                case BISHOP:
                    symbol = "\u2657";
                    nameStr = "B";
                    break;
                case KNIGHT:
                    symbol = "\u2658";
                    nameStr = "N";
                    break;
                case PAWN:
                    symbol = "\u2659";
                    nameStr = "P";
                    break;
            }
        }
        else{
            colorStr = "B";
            switch (pieceType) {
                case KING:
                    symbol = "\u265A";
                    nameStr = "K";
                    break;
                case QUEEN:
                    symbol = "\u265B";
                    nameStr = "Q";
                    break;
                case ROOK:
                    symbol = "\u265C";
                    nameStr = "R";
                    break;
                case BISHOP:
                    symbol = "\u265D";
                    nameStr = "B";
                    break;
                case KNIGHT:
                    symbol = "\u265E";
                    nameStr = "N";
                    break;
                case PAWN:
                    symbol = "\u265F";
                    nameStr = "P";
                    break;
            }
        }
    }
}
