package net.yotvoo.chessboard;

/**
 * Created by jwachowicz on 14.10.2017.
 *
 * Class represents the chess piece Pawn
 * It should calculate possible legal moves and give the answer if move is legal
 */
public class Pawn extends ChessPiece {

    public Pawn(PieceType pieceType, PieceColor pieceColor, ChessBoard chessBoard) {
        super(pieceType, pieceColor, chessBoard);
    }

    public boolean isMoveLegal(){
        return true;
    }

    //TODO zrobić klasę kolekcję ruchów
    //TODO zrobić klasę reprezentującą ruch
    //TODO zastanowić się nad algorytmem ruchu
}
