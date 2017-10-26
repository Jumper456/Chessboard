package net.yotvoo.chessgame;

import net.yotvoo.chessboard.ChessMove;
import net.yotvoo.chessnet.Client;
import net.yotvoo.chessnet.Message;

public class ChessGame {

    private Boolean whiteMove;
    private Integer moveNumber;
    private Client netClient;


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

    public void setNetClient( Client client){
        netClient = client;
    }

    public String prepareMoveRecord(ChessMove move){
        return "";
    }

    public void sendMove(ChessMove move){
        //TODO zaimplementować wysłanie ruchu przez sieć
        if (netClient != null){
            netClient.sendMessage(new Message(1, move.toString()));
        }
    };


}
