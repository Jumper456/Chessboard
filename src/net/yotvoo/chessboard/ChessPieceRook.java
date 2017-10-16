package net.yotvoo.chessboard;

public class ChessPieceRook extends ChessPiece {

    public ChessPieceRook(PieceType pieceType, PieceColor pieceColor, ChessBoard chessBoard) {
        super(pieceType, pieceColor, chessBoard);
    }

    @Override
    public String checkMove(ChessMove move, ChessPiece targetPiece) {

        int srcCol = move.getSourceCol();
        int srcRow = move.getSourceRow();
        int trgCol = move.getTargetCol();
        int trgRow = move.getTargetRow();

        Main.logMsg("ChessPieceRook move check called with move: srcCol:" + srcCol + " srcRow:" + srcRow
                + " trgCol:" + trgCol + " trgRow:" + trgRow);

        /*
        * 1) może się poruszać o dowolną ilość pól albo przy niezmiennym rzędzie
        * 2) albo przy niezmiennej kolumnie
        * 3) po drodze nie może być figury (bez znaczenia jakiego koloru
        * 4) roszada nie wymaga sprawdzania tu, bo będzie realizowana przez Króla a wieża będzie przesuwana automatycznie
        * 5)
        *
        * */

        //First check if the column is not changed but row is changed or row is changed but column not

        if ((srcCol != trgCol) && (srcRow != trgRow)){
            return "Wrong move - ChessPieceRook can go only straight line";
        }

        if (checkIfCollides(move)) {
            return "Wrong move - there is a collision on the way";
        } else {
            return "OK";
        }
    }

    private boolean checkIfCollides(ChessMove move){
        int srcCol = move.getSourceCol();
        int srcRow = move.getSourceRow();
        int trgCol = move.getTargetCol();
        int trgRow = move.getTargetRow();

        int index; //index for iterting

        //if srcCol != trgCol means the move is horizontal, otherwise is vertical
        if (srcCol != trgCol) {
            if (srcCol < trgCol) {
                for (index = srcCol + 1; index < trgCol; ++index) {
                    if (chessBoard.getChessField(index , srcRow).getPiece() != null) {
                        Main.logMsg("Collission right way index " + index);
                        return true;
                    }
                }
            }
            else {
                for (index = srcCol - 1; index > trgCol; --index) {
                    if (chessBoard.getChessField(index , srcRow).getPiece() != null) {
                        Main.logMsg("Collission left way index " + index);
                        return true;
                    }
                }
            }
        }
        else{
            if (srcRow < trgRow){
                for (index = srcRow + 1; index < trgRow; ++index) {
                    if (chessBoard.getChessField(srcCol, index).getPiece() != null) {
                        Main.logMsg("Collission down way index " + index);
                        return true;
                    }
                }
            }
            else {
                for (index = srcRow - 1; index > trgRow; --index) {
                    if (chessBoard.getChessField(srcCol, index).getPiece() != null) {
                        Main.logMsg("Collission up index " + index);
                        return true;
                    }
                }
            }
        }


        return false;
    }
}