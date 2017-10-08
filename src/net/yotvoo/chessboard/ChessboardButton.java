package net.yotvoo.chessboard;

import javafx.scene.control.Button;

public class ChessboardButton extends Button {

    final double fieldSize = 80;

    public ChessboardButton(String text, int column, int row) {
        super(text);
        this.setMaxSize(fieldSize,fieldSize);
        this.setMinSize(fieldSize, fieldSize);

        //if row+column is even, it should be dark field
        if ((row + column)%2 == 0) {
            //this.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
            //Light field
            this.setStyle("-fx-font: 22 arial; -fx-base: #FAEBD7;");
        }
        else {
            //Dark field
            this.setStyle("-fx-font: 22 arial; -fx-base: #808080;");
        }
    }
}
