package net.yotvoo.chessboard;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;

class ChessboardButton extends ToggleButton implements EventHandler<ActionEvent> {

    private final double fieldSize = 80;

    private ChessField myChessField;

    public ChessField getMyChessField() {
        return myChessField;
    }

    public void setMyChessField(ChessField myChessField) {
        this.myChessField = myChessField;
    }

    //private int boardRow;
    //private int boardColumn;

    ChessboardButton(ChessField chessField) {

        super();

        assert(chessField != null);

        if (chessField != null) {

            myChessField = chessField;

            if (chessField.getPiece() != null) {
                this.setText(chessField.getPiece().getSymbol());
            } else {
                this.setText("");
            }


            this.setMaxSize(fieldSize, fieldSize);
            this.setMinSize(fieldSize, fieldSize);
            this.setOnAction(this);

            //if row+column is even, it should be dark field
            if ((chessField.getRow() + chessField.getColumn()) % 2 == 0) {
                //this.setStyle("-fx-font: 44 arial; -fx-base: #b6e7c9;");
                //Light field
                this.setStyle("-fx-font: 34 arial; -fx-base: #FAEBD7;");
            } else {
                //Dark field
                this.setStyle("-fx-font: 34 arial; -fx-base: #808080;");
            }
        }
        else {
            System.out.println("ChessboardButton.ChessboardButton - no chessField provided");
        }
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Buton event handler: " + event.toString() + " row: " + myChessField.getRow() + " column: "
                + myChessField.getColumn());


    }

   /* public int getRow() {
        return boardRow;
    }

    public int getColumn() {
        return boardColumn;
    }*/

}
