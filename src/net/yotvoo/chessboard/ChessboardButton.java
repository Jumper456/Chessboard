package net.yotvoo.chessboard;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;

class ChessboardButton extends ToggleButton implements EventHandler<ActionEvent> {

    final static double FIELD_SIZE_PREF = 80;
    final static double FIELD_SIZE_MAX = 100;
    final static double FIELD_SIZE_MIN = 20;

    private ChessField myChessField;

    public ChessField getMyChessField() {
       return myChessField;
    }

    public void setMyChessField(ChessField myChessField) {
        this.myChessField = myChessField;
    }

    ChessboardButton(ChessField chessField) {

        super();

        assert(chessField != null);

        if (chessField != null) {

            myChessField = chessField;

            redraw();

            //this.setMaxSize(fieldSize, fieldSize);
            this.setPrefSize(FIELD_SIZE_PREF, FIELD_SIZE_PREF);
            this.setMinSize(FIELD_SIZE_MIN, FIELD_SIZE_MIN);
            this.setOnAction(this);

            //if row+column is even, it should be dark field
            if ((chessField.getRow() + chessField.getColumn()) % 2 == 0) {
                //this.setStyle("-fx-font: 44 arial; -fx-base: #b6e7c9;");
                //Light field
                this.setStyle("-fx-font: 34  arial; -fx-base: #FAEBD7;");
                //this.setStyle("-fx-base: #FAEBD7;");

            } else {
                //Dark field
                this.setStyle("-fx-font: 34 arial; -fx-base: #808080;");
                //this.setStyle("-fx-base: #808080;");
            }
        }
        else {
            System.out.println("ChessboardButton.ChessboardButton - no chessField provided");
        }
    }

    void redraw(){
        if (myChessField.getPiece() != null) {
            this.setText(myChessField.getPiece().getSymbol());
        } else {
            this.setText("");
        }

    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Buton event handler: " + event.toString() + " row: " + myChessField.getRow() + " column: "
                + myChessField.getColumn());

        this.myChessField.getChessBoard().chessButtonClicked(myChessField, this);
    }

}
