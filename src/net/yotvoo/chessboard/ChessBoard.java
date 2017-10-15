package net.yotvoo.chessboard;

import javafx.scene.layout.GridPane;

public class ChessBoard {

    //chessArray represents the chess board itself,
    //be caution for indexing as it is not the same as the chess coordinates
    //first index is row, second is column
    private ChessField[][] chessArray;

    private ChessGame chessGame;

    /*
    * clickedField and clikedButton show which field/button has been clicked as the source field
    * when the next click happens, it means to move the piece from this source
    */
    private ChessField clickedField = null;
    private ChessboardButton clickedButton = null;

    /*
    * Getters and setters here
    */

    /*
    * returns the cloned chessArray
    * */
    public ChessField[][] getChessArrayClone() {
        return chessArray.clone();
    }

    public ChessField getChessField(int column, int row){
        return chessArray[row][column];
    }

    /*
    * chessButtonClicked should be invoked after the chessboard button is cliked.
    * the concept is to click first the field with the piece you want to move and than click the target field
    * gets the button clicked and the corresponding chessbaord field
    * if there is no previous click recorded in clickedField variable, sets this variable
    * if there is a previous click recorded, performs moving the piece to the field clicked as second
    * if the previous click has been made at empty field does nothing
    */
    void chessButtonClicked(ChessField chessField, ChessboardButton chessboardButton){

        if ((clickedField == null) && (chessField.getPiece() == null)){
            System.out.println("First clik on occupied field, then on the field you want to move the piece to");
            return;
        }

        if ((clickedField == null) && (chessField.getPiece() != null)){
            if (((chessField.getPiece().getPieceColor() == ChessPiece.PieceColor.WHITE)
                        && chessGame.isWhiteMove())
                    || ((chessField.getPiece().getPieceColor() == ChessPiece.PieceColor.BLACK)
                        && !chessGame.isWhiteMove())){
                clickedField = chessField;
                clickedButton = chessboardButton;
                System.out.println("OK - first click set on valid field");
            }
            else{
                System.out.println("Wrong - first click on the bad side!");
            }

        }
        else{
            if (checkMoveStatus(chessField).equals("OK")) {
                // move the piece and change the white/black move flag
                movePieceTo(chessField, chessboardButton);
            }
            else {
                System.out.println("Wrong - Illegal move!");
                clickedField = null;
            }
        }
    }

    /*
    *   move the piece and change the white/black move flag
    *   uses the clickedField (previously clicked field) as the source
    */
    String checkMoveStatus(ChessField chessField){

        String answer;
        ChessMove move;

        if (clickedField != null){
            //check if not clicked the same piece twice
            if (clickedField != chessField) {

                move = new ChessMove(clickedField.getColumn(),clickedField.getRow(),
                                        chessField.getColumn(), chessField.getRow());
                //check if clicked empty or occupied field
                if (chessField.getPiece() != null){
                    //check if attacked own or opponents piece
                    if (chessField.getPiece().getPieceColor() == clickedField.getPiece().getPieceColor()) {
                        System.out.println("Wrong - You cannot attack own piece!");
                        return "Wrong - You cannot attack own piece!";
                    }
                    else {
                        System.out.println("OK - second click was move to occupied field");
                        answer = clickedField.getPiece().checkMove(move, chessField.getPiece());
                        System.out.println(answer);
                        return answer;
                    }
                }
                else{
                    System.out.println("OK - second click was move to empty field");
                    answer = clickedField.getPiece().checkMove(move, chessField.getPiece());
                    System.out.println(answer);
                    return answer;
                }
            }
            else {
                System.out.println("Wrong - Illegal move - clicked the same field twice!");
                return "Wrong - Illegal move - clicked the same field twice!";
            }
        }
        else {
            System.out.println("Error - clickedField jest nullem, tak nie powinno się zdarzyć");
            return "Error - clickedField jest nullem, tak nie powinno się zdarzyć";
        }

        //return "Wrong - Unknown reason why the move is illegal, but it is illegal...";
    }

    /*
    *   move the piece and change the white/black move flag
    *   uses the clickedField (previously clicked field) as the source
    */
    void movePieceTo(ChessField targetChessField, ChessboardButton chessboardButton){

        System.out.println("moving piece");
        recordMove(clickedField, targetChessField);

        targetChessField.setPiece(clickedField.getPiece());
        clickedField.getPiece().setMoved();

        if (chessGame.isWhiteMove()) {
            chessGame.setWhiteMove(false);
        }
        else {
            chessGame.setWhiteMove(true);
        }

        clickedField.clearPiece();
        printBoardAsString();
        clickedButton.redraw();
        chessboardButton.redraw();
        clickedField = null;

    }

    void recordMove(ChessField sourceField, ChessField targetField){
        String pieceSymbol = "X";
        String sourceCoords = "Y0";
        String targetCoords = "Z0";
        String record = "record not initialized";

        if (sourceField.getPiece() != null)
            pieceSymbol = sourceField.getPiece().getSymbol();
        else
            pieceSymbol = "???";

        sourceCoords = sourceField.getCoordinates();
        targetCoords = targetField.getCoordinates();

        record = "".concat(pieceSymbol).concat(sourceCoords).concat(targetCoords);

        Main.addGameScriptEntry(record);
    }

    /*
    * Constructor, initializes fields of the chessArray
    */
    ChessBoard() {
        chessArray = new ChessField[8][8];
        chessGame = new ChessGame();
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
                chessArray[row][col] = new ChessField(this, col, row, chessCooordinate(row, col));
            }
        }
    }

    private String chessCooordinate(int row, int col){
        return columnName(col+1) + (8-row);
    }

    //sets standard board pieces order on the board, does not clear any existing fields, expects the board to be empty
    private void setStandardBordOrder(){
        chessArray[0][0].setPiece(new ChessPieceRook(ChessPiece.PieceType.ROOK, ChessPiece.PieceColor.BLACK, this));
        chessArray[0][1].setPiece(new ChessPieceKnight(ChessPiece.PieceType.KNIGHT, ChessPiece.PieceColor.BLACK,this));
        chessArray[0][2].setPiece(new ChessPieceBishop(ChessPiece.PieceType.BISHOP, ChessPiece.PieceColor.BLACK,this));
        chessArray[0][3].setPiece(new ChessPiece(ChessPiece.PieceType.QUEEN, ChessPiece.PieceColor.BLACK,this));
        chessArray[0][4].setPiece(new ChessPieceKing(ChessPiece.PieceType.KING, ChessPiece.PieceColor.BLACK,this));
        chessArray[0][5].setPiece(new ChessPieceBishop(ChessPiece.PieceType.BISHOP, ChessPiece.PieceColor.BLACK,this));
        chessArray[0][6].setPiece(new ChessPieceKnight(ChessPiece.PieceType.KNIGHT, ChessPiece.PieceColor.BLACK,this));
        chessArray[0][7].setPiece(new ChessPieceRook(ChessPiece.PieceType.ROOK, ChessPiece.PieceColor.BLACK,this));

        chessArray[1][0].setPiece(new ChessPiecePawn(ChessPiece.PieceType.PAWN, ChessPiece.PieceColor.BLACK,this));
        chessArray[1][1].setPiece(new ChessPiecePawn(ChessPiece.PieceType.PAWN, ChessPiece.PieceColor.BLACK,this));
        chessArray[1][2].setPiece(new ChessPiecePawn(ChessPiece.PieceType.PAWN, ChessPiece.PieceColor.BLACK,this));
        chessArray[1][3].setPiece(new ChessPiecePawn(ChessPiece.PieceType.PAWN, ChessPiece.PieceColor.BLACK,this));
        chessArray[1][4].setPiece(new ChessPiecePawn(ChessPiece.PieceType.PAWN, ChessPiece.PieceColor.BLACK,this));
        chessArray[1][5].setPiece(new ChessPiecePawn(ChessPiece.PieceType.PAWN, ChessPiece.PieceColor.BLACK,this));
        chessArray[1][6].setPiece(new ChessPiecePawn(ChessPiece.PieceType.PAWN, ChessPiece.PieceColor.BLACK,this));
        chessArray[1][7].setPiece(new ChessPiecePawn(ChessPiece.PieceType.PAWN, ChessPiece.PieceColor.BLACK,this));

        chessArray[6][0].setPiece(new ChessPiecePawn(ChessPiece.PieceType.PAWN, ChessPiece.PieceColor.WHITE,this));
        chessArray[6][1].setPiece(new ChessPiecePawn(ChessPiece.PieceType.PAWN, ChessPiece.PieceColor.WHITE,this));
        chessArray[6][2].setPiece(new ChessPiecePawn(ChessPiece.PieceType.PAWN, ChessPiece.PieceColor.WHITE,this));
        chessArray[6][3].setPiece(new ChessPiecePawn(ChessPiece.PieceType.PAWN, ChessPiece.PieceColor.WHITE,this));
        chessArray[6][4].setPiece(new ChessPiecePawn(ChessPiece.PieceType.PAWN, ChessPiece.PieceColor.WHITE,this));
        chessArray[6][5].setPiece(new ChessPiecePawn(ChessPiece.PieceType.PAWN, ChessPiece.PieceColor.WHITE,this));
        chessArray[6][6].setPiece(new ChessPiecePawn(ChessPiece.PieceType.PAWN, ChessPiece.PieceColor.WHITE,this));
        chessArray[6][7].setPiece(new ChessPiecePawn(ChessPiece.PieceType.PAWN, ChessPiece.PieceColor.WHITE,this));

        chessArray[7][0].setPiece(new ChessPieceRook(ChessPiece.PieceType.ROOK, ChessPiece.PieceColor.WHITE,this));
        chessArray[7][1].setPiece(new ChessPieceKnight(ChessPiece.PieceType.KNIGHT, ChessPiece.PieceColor.WHITE,this));
        chessArray[7][2].setPiece(new ChessPieceBishop(ChessPiece.PieceType.BISHOP, ChessPiece.PieceColor.WHITE,this));
        chessArray[7][3].setPiece(new ChessPiece(ChessPiece.PieceType.QUEEN, ChessPiece.PieceColor.WHITE,this));
        chessArray[7][4].setPiece(new ChessPieceKing(ChessPiece.PieceType.KING, ChessPiece.PieceColor.WHITE,this));
        chessArray[7][5].setPiece(new ChessPieceBishop(ChessPiece.PieceType.BISHOP, ChessPiece.PieceColor.WHITE,this));
        chessArray[7][6].setPiece(new ChessPieceKnight(ChessPiece.PieceType.KNIGHT, ChessPiece.PieceColor.WHITE,this));
        chessArray[7][7].setPiece(new ChessPieceRook(ChessPiece.PieceType.ROOK, ChessPiece.PieceColor.WHITE,this));

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
                    myString = myString.concat(chessArray[row][col].getPiece().getNameStr());
                    myString = myString.concat(chessArray[row][col].getPiece().getColorStr());
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
            case 1: name = "a";
                break;
            case 2: name = "b";
                break;
            case 3: name = "c";
                break;
            case 4: name = "d";
                break;
            case 5: name = "e";
                break;
            case 6: name = "e";
                break;
            case 7: name = "f";
                break;
            case 8: name = "g";
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
