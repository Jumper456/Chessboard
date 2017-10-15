package net.yotvoo.chessboard;

public class ChessGame {

    private boolean whiteMove = true;

    public ChessGame() {
    }

    public boolean isWhiteMove() {
        //for testing reason uncoment returning always true
        return true;
        //return whiteMove;
    }

    public void setWhiteMove(boolean whiteMove) {
        this.whiteMove = whiteMove;
    }


}
