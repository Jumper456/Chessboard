package net.yotvoo.chessboard;

public class ChessPieceKnight extends ChessPiece {

    public ChessPieceKnight(PieceType pieceType, PieceColor pieceColor, ChessBoard chessBoard) {
        super(pieceType, pieceColor, chessBoard);
    }

    @Override
    public String checkMove(ChessMove move, ChessPiece targetPiece) {

        int srcCol = move.getSourceCol();
        int srcRow = move.getSourceRow();
        int trgCol = move.getTargetCol();
        int trgRow = move.getTargetRow();
        int direction; // for white it is negative -1, for black it is positive +1

        Main.logMsg("ChessPieceKnight move check called with move: srcCol:" + srcCol + " srcRow:" + srcRow
                + " trgCol:" + trgCol + " trgRow:" + trgRow);

        /*
        * 1) może skoczyć na pole +/-2 +/-1 bez sprawdzania kolizji po drodze
        *
        * */


        //just check the eight possible combinations, maybe there is smarter way, but that works too
        if ((srcCol == trgCol + 1) && (srcRow == trgRow + 2)) {
            return "OK";
        }
        if ((srcCol == trgCol + 1) && (srcRow == trgRow - 2)) {
            return "OK";
        }
        if ((srcCol == trgCol + 2) && (srcRow == trgRow + 1)) {
            return "OK";
        }
        if ((srcCol == trgCol + 2) && (srcRow == trgRow - 1)) {
            return "OK";
        }
        if ((srcCol == trgCol - 1) && (srcRow == trgRow + 2)) {
            return "OK";
        }
        if ((srcCol == trgCol - 1) && (srcRow == trgRow -2)) {
            return "OK";
        }
        if ((srcCol == trgCol -2) && (srcRow == trgRow + 1)) {
            return "OK";
        }
        if ((srcCol == trgCol -2) && (srcRow == trgRow - 1)) {
            return "OK";
        }

        Main.logMsg("Wrong move - not a Knigh move");
        return "Wrong move - not a Knigh move";
    }
}
