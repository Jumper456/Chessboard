package net.yotvoo.chessboard;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ChessBoard {
    private ChessField[][] chessArray = new ChessField[8][8];

    //private ChessPiece clickedPiece = null;
    private ChessField clickedField = null;
    private ChessboardButton clickedButton = null;

    /*
    * buttonClicked should be invoked after the chessboard button is cliked.
    * the concept is to click first the piece you want to move and than click the target field
    * gets the button clicked and the corresponding chessbaord field
    * if there is no previous click recorded in clickedField variable, sets this variable
    * if there is a previous click recorded, performs moving the piece to the field clicked as second
    * if the previous click has been made at empty field does nothing
    */
    void buttonClicked(ChessField chessField, ChessboardButton chessboardButton){
        if ((clickedField == null) && (chessField.getPiece() != null)){
            clickedField = chessField;
            clickedButton = chessboardButton;
            System.out.println("first click set");
        }
        else{
            if (clickedField != null){
                System.out.println("second click");
                chessField.setPiece(clickedField.getPiece());
                clickedField.clearPiece();
                printBoardAsString();
                clickedButton.redraw();
                chessboardButton.redraw();
                clickedField = null;
            }

        }


    }

    /*
    * Constructor, initializes fields of the chessArray
    */
    ChessBoard() {
        initializeFields();
    }

    /*
    * prepares the chessArray filling it with the ChessField objects
    * */
    void prepareBoard(){
        setStandardBordOrder();
    }

    private void initializeFields(){
        int row;
        int col;

        for (row = 0; row <= 7; row++) {
            for (col = 0; col <= 7; col++) {
                chessArray[row][col] = new ChessField(this, row, col);
            }
        }
    }

    //sets standard board pieces order on the board, does not clear any existing fields, expects the board to be empty
    private void setStandardBordOrder(){
        chessArray[0][0].setPiece(new ChessPiece(ChessPiece.PieceType.ROOK, ChessPiece.PieceColor.BLACK));
        chessArray[0][1].setPiece(new ChessPiece(ChessPiece.PieceType.KNIGHT, ChessPiece.PieceColor.BLACK));
        chessArray[0][2].setPiece(new ChessPiece(ChessPiece.PieceType.BISHOP, ChessPiece.PieceColor.BLACK));
        chessArray[0][3].setPiece(new ChessPiece(ChessPiece.PieceType.QUEEN, ChessPiece.PieceColor.BLACK));
        chessArray[0][4].setPiece(new ChessPiece(ChessPiece.PieceType.KING, ChessPiece.PieceColor.BLACK));
        chessArray[0][5].setPiece(new ChessPiece(ChessPiece.PieceType.BISHOP, ChessPiece.PieceColor.BLACK));
        chessArray[0][6].setPiece(new ChessPiece(ChessPiece.PieceType.KNIGHT, ChessPiece.PieceColor.BLACK));
        chessArray[0][7].setPiece(new ChessPiece(ChessPiece.PieceType.ROOK, ChessPiece.PieceColor.BLACK));

        chessArray[1][0].setPiece(new ChessPiece(ChessPiece.PieceType.PAWN, ChessPiece.PieceColor.BLACK));
        chessArray[1][1].setPiece(new ChessPiece(ChessPiece.PieceType.PAWN, ChessPiece.PieceColor.BLACK));
        chessArray[1][2].setPiece(new ChessPiece(ChessPiece.PieceType.PAWN, ChessPiece.PieceColor.BLACK));
        chessArray[1][3].setPiece(new ChessPiece(ChessPiece.PieceType.PAWN, ChessPiece.PieceColor.BLACK));
        chessArray[1][4].setPiece(new ChessPiece(ChessPiece.PieceType.PAWN, ChessPiece.PieceColor.BLACK));
        chessArray[1][5].setPiece(new ChessPiece(ChessPiece.PieceType.PAWN, ChessPiece.PieceColor.BLACK));
        chessArray[1][6].setPiece(new ChessPiece(ChessPiece.PieceType.PAWN, ChessPiece.PieceColor.BLACK));
        chessArray[1][7].setPiece(new ChessPiece(ChessPiece.PieceType.PAWN, ChessPiece.PieceColor.BLACK));

        chessArray[6][0].setPiece(new ChessPiece(ChessPiece.PieceType.PAWN, ChessPiece.PieceColor.WHITE));
        chessArray[6][1].setPiece(new ChessPiece(ChessPiece.PieceType.PAWN, ChessPiece.PieceColor.WHITE));
        chessArray[6][2].setPiece(new ChessPiece(ChessPiece.PieceType.PAWN, ChessPiece.PieceColor.WHITE));
        chessArray[6][3].setPiece(new ChessPiece(ChessPiece.PieceType.PAWN, ChessPiece.PieceColor.WHITE));
        chessArray[6][4].setPiece(new ChessPiece(ChessPiece.PieceType.PAWN, ChessPiece.PieceColor.WHITE));
        chessArray[6][5].setPiece(new ChessPiece(ChessPiece.PieceType.PAWN, ChessPiece.PieceColor.WHITE));
        chessArray[6][6].setPiece(new ChessPiece(ChessPiece.PieceType.PAWN, ChessPiece.PieceColor.WHITE));
        chessArray[6][7].setPiece(new ChessPiece(ChessPiece.PieceType.PAWN, ChessPiece.PieceColor.WHITE));

        chessArray[7][0].setPiece(new ChessPiece(ChessPiece.PieceType.ROOK, ChessPiece.PieceColor.WHITE));
        chessArray[7][1].setPiece(new ChessPiece(ChessPiece.PieceType.KNIGHT, ChessPiece.PieceColor.WHITE));
        chessArray[7][2].setPiece(new ChessPiece(ChessPiece.PieceType.BISHOP, ChessPiece.PieceColor.WHITE));
        chessArray[7][3].setPiece(new ChessPiece(ChessPiece.PieceType.QUEEN, ChessPiece.PieceColor.WHITE));
        chessArray[7][4].setPiece(new ChessPiece(ChessPiece.PieceType.KING, ChessPiece.PieceColor.WHITE));
        chessArray[7][5].setPiece(new ChessPiece(ChessPiece.PieceType.BISHOP, ChessPiece.PieceColor.WHITE));
        chessArray[7][6].setPiece(new ChessPiece(ChessPiece.PieceType.KNIGHT, ChessPiece.PieceColor.WHITE));
        chessArray[7][7].setPiece(new ChessPiece(ChessPiece.PieceType.ROOK, ChessPiece.PieceColor.WHITE));

    }

    /**
     * returns the current board (contained in the chessArray) as the string,
     * this string contains the crlf to show separate rows of the board
     * nonunicode letters show the pieces and free fields
     */
    private String boardAsStringLetters(){
        String myString = "";
        int row;
        int col;

        for (row = 0; row <= 7; row++) {
            for (col = 0; col <= 7; col++) {
                if(chessArray[row][col].getPiece() != null) {
                    myString = myString.concat(chessArray[row][col].getPiece().getName());
                    myString = myString.concat(chessArray[row][col].getPiece().getColor());
                    myString = myString.concat(" ");
                }
                else{
                    myString = myString.concat("-- ");
                }
            }
            myString = myString.concat("\r\n");

        }

        return myString;
    }

    /**
     * returns the current board (contained in the chessArray) as the string,
     * this string contains the crlf to show separate rows of the board
     * the unicode chess symbols are used to show the pieces and free fields
     */
    private String boardAsStringSymbols(){
        String myString = "";
        int row;
        int col;

        for (row = 0; row <= 7; row++) {
            for (col = 0; col <= 7; col++) {
                if(chessArray[row][col].getPiece() != null) {
                    myString = myString.concat(chessArray[row][col].getPiece().getSymbol());
                    myString = myString.concat(" ");
                }
                else{
                    myString = myString.concat("\u26CB ");
                }
            }
            myString = myString.concat("\r\n");

        }

        return myString;
    }

    //populates given grid pane with the current fields from chessArray
    void populateGridPane(GridPane gridPane){

        //String buttonText;
        int row;
        int col;

        //fill the palyable fields
        for (row = 0; row <= 7; row++) {
            for (col = 0; col <= 7; col++) {
                gridPane.add(new ChessboardButton(chessArray[row][col]), col + 1, row + 1);
            }

        }

        //Fill the border fields with coordinates
        ChessBorderButton button;
        for (row = 1; row <= 8; row++) {
            button = new ChessBorderButton("" + (9 - row),false);
            gridPane.add( button , 0, row );

            button = new ChessBorderButton("" + (9 - row),false);
            gridPane.add( button, 9, row );
        }

        for (col = 1; col <= 8; col++) {
            button = new ChessBorderButton(columnName(col),true);
            gridPane.add( button, col , 0 );

            button = new ChessBorderButton(columnName(col),true);
            gridPane.add( button, col , 9 );
        }
    }

    private String columnName(int columnNumber){

        String name = "?";
        switch (columnNumber){
            case 1: name = "A";
                break;
            case 2: name = "B";
                break;
            case 3: name = "C";
                break;
            case 4: name = "D";
                break;
            case 5: name = "E";
                break;
            case 6: name = "F";
                break;
            case 7: name = "G";
                break;
            case 8: name = "H";
                break;
        }
        return name;
    }

    //prints the board as the string to the standard out
    void printBoardAsString(){
        System.out.println(boardAsStringSymbols());
    }

    /*
    //move the piece from the starting position to the target position
    public boolean movePiece(int startRow, int startColumn, int targetRow, int targetColum){
        return true;
    }
    */
}
