package net.yotvoo.chessboard;

public class ChessPieceKing extends ChessPiece {

    public ChessPieceKing(PieceType pieceType, PieceColor pieceColor, ChessBoard chessBoard) {
        super(pieceType, pieceColor, chessBoard);
    }

    @Override
    public String checkMove(ChessMove move, ChessPiece targetPiece) {

        int srcCol = move.getSourceCol();
        int srcRow = move.getSourceRow();
        int trgCol = move.getTargetCol();
        int trgRow = move.getTargetRow();

        Main.logMsg("ChessPieceKing move check called with move: srcCol:" + srcCol + " srcRow:" + srcRow
                + " trgCol:" + trgCol + " trgRow:" + trgRow);

        /*
        * 1) może przejść jedno pole w dowolną stronę, nie ma potrzeby sprawdzać kolizji
        *
        * */


        //just check the eight possible combinations, maybe there is smarter way, but that works too
        if ((Math.abs(srcCol - trgCol) <= 1) && (Math.abs(srcRow - trgRow) <= 1)) {
            return "OK";
        }

        Main.logMsg("Wrong move - not a Knigh move");
        return "Wrong move - not a Knigh move";
    }
}
