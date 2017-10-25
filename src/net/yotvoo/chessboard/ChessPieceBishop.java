package net.yotvoo.chessboard;

import net.yotvoo.chessGUI.Main;

public class ChessPieceBishop extends ChessPiece {

    public ChessPieceBishop(PieceType pieceType, PieceColor pieceColor, ChessBoard chessBoard) {
        super(pieceType, pieceColor, chessBoard);
    }

    @Override
    public String checkMove(ChessMove move, ChessPiece targetPiece) {

        int srcCol = move.getSourceCol();
        int srcRow = move.getSourceRow();
        int trgCol = move.getTargetCol();
        int trgRow = move.getTargetRow();

        Main.logMsg("ChessPieceBishop move check called with move: srcCol:" + srcCol + " srcRow:" + srcRow
                + " trgCol:" + trgCol + " trgRow:" + trgRow);

        /*
        * może się poruszać o dowolną ilość pól
        * jeśli zarówno rząd jak kolumna zmienia się o taką samą wrtość pól w sensie odległości
        * czyli wartości absolutnej różnicy koordynat x i y odpowiednio
        *
        * po drodze nie może być figury (bez znaczenia jakiego koloru)
        *
        * */

        //First check if the column and row is changed for the same amount

        if (Math.abs(srcCol - trgCol) != Math.abs(srcRow - trgRow)){
            return "Wrong move - ChessPieceBishop can go only on cross line";
        }

        if (checkIfCollides(move)) {
            return "Wrong move - there is a collision on the Bishop's way";
        } else {
            return "OK";
        }
    }

    private boolean checkIfCollides(ChessMove move) {

        int srcCol = move.getSourceCol();
        int srcRow = move.getSourceRow();
        int trgCol = move.getTargetCol();
        int trgRow = move.getTargetRow();

        int incrementCol; //1 or -1 to iterate the row accordingly with col, in good direction
        int incrementRow; //1 or -1 to iterate the row accordingly with row, in good direction

        int indexCol; //index for iterting column
        int indexRow; //index for iterting row

        //set starting values depending on which direction it goes
        //then iterate from starting value to ending value

        if (trgCol > srcCol) {
            incrementCol = 1;
            indexCol = srcCol + 1;
        }
        else {
            incrementCol = -1;
            indexCol = srcCol - 1;
        }

        if (trgRow > srcRow) {
            incrementRow = 1;
            indexRow = srcRow + 1;
        }
        else {
            incrementRow= -1;
            indexRow = srcRow - 1;
        }

        while (indexCol != (trgCol)) {

            Main.logMsg("Bishop move collision check indexCol: " + indexCol + " indexRow: " + indexRow);
            if (chessBoard.getChessField(indexCol, indexRow).getPiece() != null) {

                Main.logMsg("Collission on the way indexCol: " + indexCol + " indexRow: " + indexRow);
                return true;
            }
            indexCol += incrementCol;
            indexRow += incrementRow;
        }



/*
        //it is the Rook code, to be used at Queen code or to be removed

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
*/

        return false;
    }

}
