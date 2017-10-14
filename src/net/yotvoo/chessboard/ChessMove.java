package net.yotvoo.chessboard;

/**
 * Created by jwachowicz on 14.10.2017.
 *
 * Represents the move of the chess piece from one position to another
 */
public class ChessMove {
    private int sourceCol;
    private int sourceRow;
    private int targetCol;
    private int targetRow;

    public ChessMove(int sourceCol, int sourceRow, int targetCol, int targetRow) {
        this.sourceCol = sourceCol;
        this.sourceRow = sourceRow;
        this.targetCol = targetCol;
        this.targetRow = targetRow;
    }

    public int getSourceCol() {
        return sourceCol;
    }

    public int getSourceRow() {
        return sourceRow;
    }

    public int getTargetCol() {
        return targetCol;
    }

    public int getTargetRow() {
        return targetRow;
    }
}
