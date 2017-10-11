package net.yotvoo.chessboard;


import javafx.scene.Node;
import javafx.scene.control.Button;
import net.yotvoo.chessboard.ChessboardButton;

public class ChessBorderButton extends Button {

    public ChessBorderButton(boolean isVertical) {
        styleMe(isVertical);
    }

    public ChessBorderButton(String text, boolean isVertical) {
        super(text);
        styleMe(isVertical);
    }

    public ChessBorderButton(String text, Node graphic, boolean isVertical) {
        super(text, graphic);
        styleMe(isVertical);
    }

    private void styleMe(boolean isVartical){
        if (isVartical) {
            this.setPrefWidth(ChessboardButton.FIELD_SIZE_PREF);
        }
        else{
            this.setPrefHeight(ChessboardButton.FIELD_SIZE_PREF);
        }



    }
}
