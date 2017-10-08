package net.yotvoo.chessboard;

public class ChessPiece {
    private String name = "";
    private String color = "";

    public String getColor() {
        return color;
    }

    public ChessPiece(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }
}
