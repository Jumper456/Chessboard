package net.yotvoo.chessboard;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;


public class Main extends Application implements EventHandler<ActionEvent>{

    private ChessBoard chessBoard;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Chessboard");

        BorderPane borderPane = new BorderPane();

        GridPane chessBoardGridPane = new GridPane();
        borderPane.setCenter(chessBoardGridPane);


        HBox hBoxTop = new HBox();
        hBoxTop.setPadding(new Insets(5,5,5,5));
        hBoxTop.getChildren().addAll(new Button("Nowa gra"));
        borderPane.setTop(hBoxTop);

        HBox hBoxBottom = new HBox();
        hBoxBottom.getChildren().addAll(new Label("(c) 2017 Yotvoo"));
        borderPane.setBottom(hBoxBottom);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);

        try {
            chessBoard = new ChessBoard();
            chessBoard.prepareBoard();
            chessBoard.printBoardAsString();
            chessBoard.populateGridPane(chessBoardGridPane);
        }
        catch (Exception e){
            System.out.println("Coś się zdupiło przy inicjalizacji boarda");
            e.printStackTrace();
        }

        primaryStage.show();
    }

    private void fillPaneWithJustButtons(GridPane layout){
        int row;
        int col;

        for (row = 0; row <= 7; row++) {
            for (col = 0; col <= 7; col++) {
                layout.add(new ChessboardButton(null),col,row);
            }
        }
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Event " + event.getSource().toString());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
