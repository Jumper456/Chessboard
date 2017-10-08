package net.yotvoo.chessboard;

//import com.sun.java.util.jar.pack.Attribute;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;



public class Main extends Application {

    private ChessBoard chessBoard;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Chessboard");

        GridPane layout = new GridPane();

        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);


        //fillPaneWithJustButtons(layout);

        try {
            chessBoard = new ChessBoard();
            chessBoard.prepareBoard();
            chessBoard.printBoardAsString();
            chessBoard.populateGridPane(layout);
        }
        catch (Exception e){
            System.out.println("Coś się zdupiło przy inicjalizacji boarda");
            e.printStackTrace();
        }

        primaryStage.show();
    }

    private void fillPaneWithJustButtons(GridPane layout){
        int row = 0;
        int col = 0;

        for (row = 0; row <= 7; row++) {
            for (col = 0; col <= 7; col++) {
                layout.add(new ChessboardButton("A", col, row), col, row);
            }
        };
    }

    public static void main(String[] args) {
        launch(args);
    }
}
