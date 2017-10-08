package net.yotvoo.chessboard;

import javafx.scene.layout.GridPane;

public class ChessBoard {
    private ChessField[][] chessArray = new ChessField[8][8];


    /* constructor
     */
    public ChessBoard(ChessField[][] chessArray) {
        this.chessArray = chessArray;

    }

    public ChessBoard() {
    };

    public void prepareBoard(){
        initializeFields();
        setStartingPieces();
    }
    private void initializeFields(){
        int row = 0;
        int col = 0;

        for (row = 0; row <= 7; row++) {
            for (col = 0; col <= 7; col++) {
                chessArray[row][col] = new ChessField();
            }
        };
    }

    private void setStartingPieces(){
        chessArray[0][0].piece = new ChessPiece("R","B");
        chessArray[0][1].piece = new ChessPiece("N","B");
        chessArray[0][2].piece = new ChessPiece("B","B");
        chessArray[0][3].piece = new ChessPiece("Q","B");
        chessArray[0][4].piece = new ChessPiece("K","B");
        chessArray[0][5].piece = new ChessPiece("B","B");
        chessArray[0][6].piece = new ChessPiece("N","B");
        chessArray[0][7].piece = new ChessPiece("R","B");

        chessArray[1][0].piece = new ChessPiece("P","B");
        chessArray[1][1].piece = new ChessPiece("P","B");
        chessArray[1][2].piece = new ChessPiece("P","B");
        chessArray[1][3].piece = new ChessPiece("P","B");
        chessArray[1][4].piece = new ChessPiece("P","B");
        chessArray[1][5].piece = new ChessPiece("P","B");
        chessArray[1][6].piece = new ChessPiece("P","B");
        chessArray[1][7].piece = new ChessPiece("P","B");

        chessArray[7][0].piece = new ChessPiece("R","W");
        chessArray[7][1].piece = new ChessPiece("N","W");
        chessArray[7][2].piece = new ChessPiece("B","W");
        chessArray[7][3].piece = new ChessPiece("Q","W");
        chessArray[7][4].piece = new ChessPiece("K","W");
        chessArray[7][5].piece = new ChessPiece("B","W");
        chessArray[7][6].piece = new ChessPiece("N","W");
        chessArray[7][7].piece = new ChessPiece("R","W");

        chessArray[6][0].piece = new ChessPiece("P","W");
        chessArray[6][1].piece = new ChessPiece("P","W");
        chessArray[6][2].piece = new ChessPiece("P","W");
        chessArray[6][3].piece = new ChessPiece("P","W");
        chessArray[6][4].piece = new ChessPiece("P","W");
        chessArray[6][5].piece = new ChessPiece("P","W");
        chessArray[6][6].piece = new ChessPiece("P","W");
        chessArray[6][7].piece = new ChessPiece("P","W");

    }

    public String boardAsString(){
        String myString = "";
        int row = 0;
        int col = 0;

        for (row = 0; row <= 7; row++) {
            for (col = 0; col <= 7; col++) {
                if(chessArray[row][col].piece != null) {
                    myString = myString.concat(chessArray[row][col].piece.getName());
                    myString = myString.concat(chessArray[row][col].piece.getColor());
                    myString = myString.concat(" ");
                }
                else{
                    myString = myString.concat("__ ");
                }
            }
            myString = myString.concat("\r\n");

        };

        return myString;
    }

    public void populateGridPane(GridPane gridPane){

        String buttonText = "";
        int row = 0;
        int col = 0;

        for (row = 0; row <= 7; row++) {
            for (col = 0; col <= 7; col++) {
                if(chessArray[row][col].piece != null) {
                    buttonText = "";
                    buttonText = buttonText.concat(chessArray[row][col].piece.getName());
                    buttonText = buttonText.concat(chessArray[row][col].piece.getColor());
                    gridPane.add(new ChessboardButton(buttonText,col,row), col, row);
                }
                else{
                    gridPane.add(new ChessboardButton("  ", col, row), col, row);
                }
            }

        };
    }

    public void printBoardAsString(){
        System.out.println(boardAsString());
    }

}
