package net.yotvoo.chessboard;

import net.yotvoo.chessGUI.Main;

/**
 * Created by jwachowicz on 14.10.2017.
 *
 * Class represents the chess piece ChessPiecePawn
 * Calculates possible legal moves and give the answer if move is legal
 */
public class ChessPiecePawn extends ChessPiece {

    public ChessPiecePawn(PieceType pieceType, PieceColor pieceColor, ChessBoard chessBoard) {
        super(pieceType, pieceColor, chessBoard);
    }

    @Override
    public String checkMove(ChessMove move, ChessPiece targetPiece) {

        int srcCol = move.getSourceCol();
        int srcRow = move.getSourceRow();
        int trgCol = move.getTargetCol();
        int trgRow = move.getTargetRow();
        int direction; // for white it is negative -1, for black it is positive +1

        Main.logMsg("ChessPiecePawn move check called with move: srcCol:" + srcCol + " srcRow:" + srcRow
                + " trgCol:" + trgCol + " trgRow:" + trgRow);

        /*
        * 1) kolumna może się zminić tylko jeśli atak na bierkę przeciwnika
        * 2) trzeba uwzględnić bicie w przelocie
        * 3) jeśli pierwszy ruch bierki, to możliwa różnica jeden lub dwa rzędy
        * 4) biały może w górę (rzędy malejące)
        * 5) czarny może w dół (rzędy rosną)
        *
        * */

        if (this.getPieceColor() == PieceColor.WHITE)
            direction =  -1;
        else
            direction =  1;

        //check condition when move is one row forward and column not changed
        //and if there is no collision

        if ((srcCol == trgCol) && (trgRow == (srcRow + direction))){
            if (targetPiece == null) {
                Main.logMsg("OK - ChessPiecePawn simple forward move to empty field is valid move");
                return "OK";
            }
            else{
                Main.logMsg("Wrong - ChessPiecePawn simple forward move to occupied field");
                return "Wrong move - ChessPiecePawn cannot attack forward";
            }
        };

        //check condition when the move is an initial move two rows forward with no column change
        if (!this.isMoved()){
            if ((srcCol == trgCol) && (trgRow == (srcRow + direction * 2))){
                if (targetPiece == null) {
                    Main.logMsg("OK - ChessPiecePawn initial double forward move to empty field is valid move");
                    return "OK";
                }
                else{
                    Main.logMsg("Wrong - ChessPiecePawn initial double forward move to occupied field");
                    return "Wrong move - ChessPiecePawn cannot attack forward";
                }
            };

        };

        // check condition when the move is the attack,
        // so one row forward and column changed to neighbour column
        // amd the target field is occupied
        if (((srcCol == trgCol + 1) || (srcCol == trgCol - 1)) && (trgRow == (srcRow + direction))){
            if (targetPiece != null) {
                Main.logMsg("OK - ChessPiecePawn cross forward move to occupied field is valid move");
                return "OK";
            }
            else{
                Main.logMsg("Wrong - ChessPiecePawn cross forward move to empty field is not valid move");
                return "Wrong move - ChessPiecePawn cannot move cross forward to empty field (not attacking)";
            }
        };

        Main.logMsg("Wrong move, not valid for ChessPiecePawn");
        return "Wrong move, not valid for ChessPiecePawn";

    }

}
