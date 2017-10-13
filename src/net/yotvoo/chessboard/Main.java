package net.yotvoo.chessboard;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class Main extends Application implements EventHandler<ActionEvent>{

    private ChessBoard chessBoard;
    private Button newGameButton;
    private GridPane chessBoardGridPane;
    private ChessGame chessGame;
    private static TextArea gameScript;

    public static void main(String[] args) {
        launch(args);
    }
    public static void addGameScriptEntry(String text){
        gameScript.setText(gameScript.getText().concat(text).concat("\n"));
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Chessboard");

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(5,5,5,5));

        chessBoardGridPane = new GridPane();
        chessBoardGridPane.setGridLinesVisible(false);
        chessBoardGridPane.setPadding(new Insets(5,5,5,5));
//        chessBoardGridPane.setHgap(3);
//        chessBoardGridPane.setVgap(3);

        borderPane.setCenter(chessBoardGridPane);

        HBox hBoxTop = new HBox();
        hBoxTop.setPadding(new Insets(5,5,5,5));
        hBoxTop.setStyle("-fx-background-color: #AAAAAA;");
        newGameButton = new Button("Nowa gra");
        newGameButton.setOnAction(this);
        hBoxTop.getChildren().addAll(newGameButton);
        borderPane.setTop(hBoxTop);

        HBox hBoxBottom = new HBox();
        hBoxBottom.getChildren().addAll(new Label("(c) 2017 Yotvoo"));
        borderPane.setBottom(hBoxBottom);

        VBox vBoxRight = new VBox();
//        vBoxRight.setStyle("-fx-background-color: #A00000;");
        vBoxRight.setPrefWidth(200);
        vBoxRight.setSpacing(10);
        vBoxRight.setPadding(new Insets(5,5,5,5));
        vBoxRight.getChildren().add(new Label("Zapis partii"));

        gameScript = new TextArea();
        gameScript.setEditable(false);


        vBoxRight.getChildren().add(gameScript);
        vBoxRight.setVgrow( gameScript, Priority.ALWAYS );

        borderPane.setRight(vBoxRight);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);

        resetBoard(chessBoardGridPane);

        primaryStage.setTitle("Szachownica");
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    /**
     * Destroy current board and prepare the new one
     */
    private void resetBoard(GridPane pane){
        try {
            chessBoard = new ChessBoard();
            chessBoard.prepareBoard();
            chessBoard.printBoardAsString();
            chessBoard.populateGridPane(pane);
        }
        catch (Exception e){
            System.out.println("Coś się zdupiło przy inicjalizacji boarda");
            e.printStackTrace();
        }

    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Event " + event.toString());
        if (event.getSource() == newGameButton){
            resetBoard(chessBoardGridPane);
        }
    }


}
