package net.yotvoo.chessboard;

import javafx.scene.layout.GridPane;

public class ChessBoard {
    private ChessField[][] chessArray = new ChessField[8][8];


    /* constructor
     */
    public ChessBoard(ChessField[][] chessArray) {
        this.chessArray = chessArray;

    }

    ChessBoard() {
    }

    void prepareBoard(){
        initializeFields();
        setStandardBordOrder();
    }
    private void initializeFields(){
        int row;
        int col;

        for (row = 0; row <= 7; row++) {
            for (col = 0; col <= 7; col++) {
                chessArray[row][col] = new ChessField();
            }
        }
    }

    //sets standard board pieces order on the board, does not clear any existing fields, expects the board to be empty
    private void setStandardBordOrder(){
        chessArray[0][0].setPiece(new ChessPiece("R", "B"));
        chessArray[0][1].setPiece(new ChessPiece("N", "B"));
        chessArray[0][2].setPiece(new ChessPiece("B", "B"));
        chessArray[0][3].setPiece(new ChessPiece("Q", "B"));
        chessArray[0][4].setPiece(new ChessPiece("K", "B"));
        chessArray[0][5].setPiece(new ChessPiece("B", "B"));
        chessArray[0][6].setPiece(new ChessPiece("N", "B"));
        chessArray[0][7].setPiece(new ChessPiece("R", "B"));

        chessArray[1][0].setPiece(new ChessPiece("P", "B"));
        chessArray[1][1].setPiece(new ChessPiece("P", "B"));
        chessArray[1][2].setPiece(new ChessPiece("P", "B"));
        chessArray[1][3].setPiece(new ChessPiece("P", "B"));
        chessArray[1][4].setPiece(new ChessPiece("P", "B"));
        chessArray[1][5].setPiece(new ChessPiece("P", "B"));
        chessArray[1][6].setPiece(new ChessPiece("P", "B"));
        chessArray[1][7].setPiece(new ChessPiece("P", "B"));

        chessArray[7][0].setPiece(new ChessPiece("R", "W"));
        chessArray[7][1].setPiece(new ChessPiece("N", "W"));
        chessArray[7][2].setPiece(new ChessPiece("B", "W"));
        chessArray[7][3].setPiece(new ChessPiece("Q", "W"));
        chessArray[7][4].setPiece(new ChessPiece("K", "W"));
        chessArray[7][5].setPiece(new ChessPiece("B", "W"));
        chessArray[7][6].setPiece(new ChessPiece("N", "W"));
        chessArray[7][7].setPiece(new ChessPiece("R", "W"));

        chessArray[6][0].setPiece(new ChessPiece("P", "W"));
        chessArray[6][1].setPiece(new ChessPiece("P", "W"));
        chessArray[6][2].setPiece(new ChessPiece("P", "W"));
        chessArray[6][3].setPiece(new ChessPiece("P", "W"));
        chessArray[6][4].setPiece(new ChessPiece("P", "W"));
        chessArray[6][5].setPiece(new ChessPiece("P", "W"));
        chessArray[6][6].setPiece(new ChessPiece("P", "W"));
        chessArray[6][7].setPiece(new ChessPiece("P", "W"));

    }

    /**
     * returns the current board (contained in the chessArray) as the string,
     * this string contains the crlf to show separate rows of the board
     */
    private String boardAsString(){
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

    //populates given grid pane with the current fields from chessArray
    void populateGridPane(GridPane gridPane){

        String buttonText;
        int row;
        int col;

        for (row = 0; row <= 7; row++) {
            for (col = 0; col <= 7; col++) {
                if(chessArray[row][col].getPiece() != null) {
                    buttonText = "";
                    buttonText = buttonText.concat(chessArray[row][col].getPiece().getName());
                    buttonText = buttonText.concat(chessArray[row][col].getPiece().getColor());
                    gridPane.add(new ChessboardButton(buttonText,col,row), col, row);
                }
                else{
                    gridPane.add(new ChessboardButton("  ", col, row), col, row);
                }
            }

        }
    }

    //prints the board as the string to the standard out
    void printBoardAsString(){
        System.out.println(boardAsString());
    }

    //move the piece from the starting position to the target position
    public boolean movePiece(int startRow, int startColumn, int targetRow, int targetColum){
        return true;
    }

}
