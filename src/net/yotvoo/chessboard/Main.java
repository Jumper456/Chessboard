package net.yotvoo.chessboard;

//import com.sun.java.util.jar.pack.Attribute;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;



public class Main extends Application implements EventHandler<ActionEvent>{

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
