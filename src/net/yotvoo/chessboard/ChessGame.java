package net.yotvoo.chessboard;

public class ChessGame {

    private Boolean whiteMove;

    private Integer moveNumber;


    public ChessGame() {
        whiteMove = true;
        moveNumber = 1;
    }

    public boolean isWhiteMove() {
        //for testing reason uncoment returning always true
        //return true;
        return whiteMove;
    }

    public void setWhiteMove(boolean whiteMove) {
        this.whiteMove = whiteMove;
    }

    public Integer getMoveNumber() {
        return moveNumber;
    }

    public void setNextMoveNumber(){
        moveNumber += 1;
    }

    public String prepareMoveRecord(ChessMove move){
        return "";
    }


}
