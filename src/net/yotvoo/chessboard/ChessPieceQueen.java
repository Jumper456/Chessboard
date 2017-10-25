package net.yotvoo.chessboard;

import net.yotvoo.chessGUI.Main;

public class ChessPieceQueen extends ChessPiece {

    public ChessPieceQueen(PieceType pieceType, PieceColor pieceColor, ChessBoard chessBoard) {
        super(pieceType, pieceColor, chessBoard);
    }

    @Override
    public String checkMove(ChessMove move, ChessPiece targetPiece) {

        int srcCol = move.getSourceCol();
        int srcRow = move.getSourceRow();
        int trgCol = move.getTargetCol();
        int trgRow = move.getTargetRow();
        boolean isBishopsMove;
        boolean isRooksMove;

        Main.logMsg("ChessPieceQueen move check called with move: srcCol:" + srcCol + " srcRow:" + srcRow
                + " trgCol:" + trgCol + " trgRow:" + trgRow);

        /*
        * może się poruszać o dowolną ilość pól
        * albo przy zmianie rzędu i kolumny o tyle samo co do wartości bezwzględnej
        * czyli tak jak goniec
        * oraz tak jak wierza czyli w pionie lub poziomie czyli zmienia się tylko rząd albo kolumna
        *
        * po drodze nie może być figury (bez znaczenia jakiego koloru)
        *
        * */


        //check if it is a Bishop like move
        isBishopsMove = false;
        if (Math.abs(srcCol - trgCol) == Math.abs(srcRow - trgRow)){
            isBishopsMove = true;
            if (checkIfCollidesBishopMove(move)) {
                return "Wrong move - there is a collision on the Queen's way";
            } else {
                return "OK";
            }
        }

        //check if it is a Rook like move
        isRooksMove = false;
        if ((srcCol == trgCol) || (srcRow == trgRow)){
            isRooksMove = true;
            if (checkIfCollidesRookMove(move)) {
                return "Wrong move - there is a collision on the Queen's way";
            } else {
                return "OK";
            }
        }

        if ( !(isBishopsMove || isRooksMove) ) {
            return "Wrong move - Queen can go only on cross line or straight line";
        }

        return "Ooooops this case should not happen...";

    }

    private boolean checkIfCollidesBishopMove(ChessMove move) {

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

            Main.logMsg("Queen (Bishop like) move collision check indexCol: " + indexCol + " indexRow: " + indexRow);
            if (chessBoard.getChessField(indexCol, indexRow).getPiece() != null) {

                Main.logMsg("Collission on the way indexCol: " + indexCol + " indexRow: " + indexRow);
                return true;
            }
            indexCol += incrementCol;
            indexRow += incrementRow;
        }

        return false;
    }

    private boolean checkIfCollidesRookMove(ChessMove move){
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
                        Main.logMsg("Queen (Rook like) move collission right way index " + index);
                        return true;
                    }
                }
            }
            else {
                for (index = srcCol - 1; index > trgCol; --index) {
                    if (chessBoard.getChessField(index , srcRow).getPiece() != null) {
                        Main.logMsg("Queen (Rook like) move collission left way index " + index);
                        return true;
                    }
                }
            }
        }
        else{
            if (srcRow < trgRow){
                for (index = srcRow + 1; index < trgRow; ++index) {
                    if (chessBoard.getChessField(srcCol, index).getPiece() != null) {
                        Main.logMsg("Queen (Rook like) move collission down way index " + index);
                        return true;
                    }
                }
            }
            else {
                for (index = srcRow - 1; index > trgRow; --index) {
                    if (chessBoard.getChessField(srcCol, index).getPiece() != null) {
                        Main.logMsg("Queen (Rook like) move collission up index " + index);
                        return true;
                    }
                }
            }
        }


        return false;
    }


}
