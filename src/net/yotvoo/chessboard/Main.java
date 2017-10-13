package net.yotvoo.chessboard;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;


public class Main extends Application implements EventHandler<ActionEvent>{

    private ChessBoard chessBoard;
    private Button newGameButton;
    private GridPane chessBoardGridPane;
    private TextArea gameScript;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Chessboard");

        BorderPane borderPane = new BorderPane();

        chessBoardGridPane = new GridPane();
        chessBoardGridPane.setGridLinesVisible(false);
        chessBoardGridPane.setHgap(3);
        chessBoardGridPane.setVgap(3);

        borderPane.setCenter(chessBoardGridPane);


        HBox hBoxTop = new HBox();
        hBoxTop.setPadding(new Insets(5,5,5,5));
        newGameButton = new Button("Nowa gra");
        newGameButton.setOnAction(this);
        hBoxTop.getChildren().addAll(newGameButton);
        borderPane.setTop(hBoxTop);

        HBox hBoxBottom = new HBox();
        hBoxBottom.getChildren().addAll(new Label("(c) 2017 Yotvoo"));
        borderPane.setBottom(hBoxBottom);

        VBox vBoxRight = new VBox();
        vBoxRight.setPrefWidth(200);
        vBoxRight.setSpacing(10);
        vBoxRight.setPadding(new Insets(5,5,5,5));
        vBoxRight.getChildren().add(new Label("Zapis partii"));
        gameScript = new TextArea();
        gameScript.setEditable(false);
        vBoxRight.getChildren().add(gameScript);
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

    public static void main(String[] args) {
        launch(args);
    }
}
