package net.yotvoo.chessGUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.yotvoo.chessboard.ChessBoard;
import net.yotvoo.chessgame.ChessGame;
import net.yotvoo.chessnet.Client;
import net.yotvoo.chessnet.Message;
import net.yotvoo.chessnet.Server;

public class Main extends Application implements EventHandler<ActionEvent>{

    private static TextArea gameScript;
    private static TextArea chatArea;
    private TextField chatTextInput;

    private ChessBoard chessBoard;
    private ChessboardGUIController guiController;

    private Button newGameButton;
    private Button connectionButton;
    private Button startServerButton;
    private Button chatSendButton;

    private GridPane chessBoardGridPane;
    private ChessGame chessGame;

    private Server netServer;
    private Client netClient;

    /*
    * Connection and Server Dialogs elements
    */
    private TextField textField;

    private Stage connectionDialogStage;
    private Button connectButton;
    private Button connectCancelButton;

    private Stage serverDialogStage;
    private Button initServerButton;
    private Button serverCancelButton;


    public static void main(String[] args) {
        launch(args);
    }

    public static void addGameScriptEntry(String text){
        //gameScript.setText(gameScript.getText().concat(text).concat("\n"));
        gameScript.appendText(text.concat("\n"));
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
        hBoxTop.setSpacing(10);
        hBoxTop.setStyle("-fx-background-color: #FAEBD7;");
        newGameButton = new Button("Nowa gra");
        newGameButton.setTooltip(new Tooltip("Uruchom nową grę - usuwa aktualne ustawienie szachownicy!"));
        newGameButton.setStyle("-fx-font: 16  arial;");
        newGameButton.setOnAction(this);

        connectionButton = new Button("Połączenie");
        connectionButton.setTooltip(new Tooltip("Połącz z serwerem gry i czatem"));
        connectionButton.setStyle("-fx-font: 16  arial;");
        connectionButton.setOnAction(this);


        startServerButton = new Button("Serwer");
        startServerButton.setTooltip(new Tooltip("Uruchom serwer gry i czatu"));
        startServerButton.setStyle("-fx-font: 16  arial;");
        startServerButton.setOnAction(this);



        hBoxTop.getChildren().addAll(newGameButton, connectionButton, startServerButton);
        borderPane.setTop(hBoxTop);

        HBox hBoxBottom = new HBox();
        hBoxBottom.getChildren().addAll(new Label("(c) 2017 Yotvoo"));
        borderPane.setBottom(hBoxBottom);

        VBox vBoxRight = new VBox();
//        vBoxRight.setStyle("-fx-background-color: #A00000;");
        vBoxRight.setPrefWidth(300);
        vBoxRight.setSpacing(10);
        vBoxRight.setPadding(new Insets(5,5,5,5));
/*
        Label labelGameScript = new Label("Zapis partii");
        labelGameScript.setStyle("-fx-font: 16  arial;");
        vBoxRight.getChildren().add(labelGameScript);
*/

        gameScript = new TextArea();
        gameScript.setEditable(false);

        chatArea= new TextArea();
        chatArea.setWrapText(true);
        chatArea.setEditable(false);

        guiController = new ChessboardGUIController(chatArea);

        chatTextInput = new TextField();
        chatTextInput.setPromptText("Wprowadź tekst do wysłania");

        chatSendButton = new Button("Wyślij");
        chatSendButton .setStyle("-fx-font: 16  arial;");
        chatSendButton .setOnAction(this);
        chatSendButton.setDefaultButton(true);


        vBoxRight.getChildren().addAll(gameScript, chatArea,chatTextInput,chatSendButton);
        vBoxRight.setVgrow( gameScript, Priority.ALWAYS );

        borderPane.setRight(vBoxRight);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);

        resetBoard(chessBoardGridPane);

        primaryStage.setTitle("Szachownica");
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();

        gameScript.setText("Zapis partii:\n");
        setTextAreaBackgroundStyle(gameScript);
        setTextAreaBackgroundStyle(chatArea);
        setTextAreaBackgroundStyle(chatTextInput);

    }

    private static void setTextAreaBackgroundStyle(Control area){
        Region content = (Region) area.lookup(".content");
        if (content != null) content.setStyle("-fx-background-color: #FAEBD7;");
        area.setStyle("-fx-font: 16  arial; -fx-background-color: #FAEBD7;");
        //gameScript.setStyle("-fx-font: 16  arial; .text-area .content{-fx-background-color: #FAEBD7;}");
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
            gameScript.clear();
            gameScript.setText("Zapis partii:\n");
        }
        catch (Exception e){
            System.out.println("Coś się zdupiło przy inicjalizacji boarda");
            e.printStackTrace();
        }

    }

    @Override
    public void handle(ActionEvent event) {
        //System.out.println("Event " + event.toString());
        if (event.getSource() == newGameButton){
            resetBoard(chessBoardGridPane);
        }
        else if (event.getSource() == connectionButton){
            showConnectionDialog();
        }
        else if (event.getSource() == startServerButton){
            showServerDialog();
        }
        else if (event.getSource() == connectCancelButton){
            //Close the dialog
            connectionDialogStage.close();
        }
        else if (event.getSource() == serverCancelButton){
            //Close the dialog
            serverDialogStage.close();
        }
        else if (event.getSource() == connectButton){

            logMsg("kliknięto klawisz nawiązania połaczenia");

            //TODO nawiązanie połączenia
            netClient = new Client("localhost",1500, "Jarek", guiController);
            netClient.start();

            //Close the dialog
            connectionDialogStage.close();
        }
        else if (event.getSource() == initServerButton){

            logMsg("kliknięto klawisz uruchomienia serwera");

            //TODO uruchomienie servera
            netServer = new Server(1500);//, guiController);
            netServer.start();

            //Close the dialog
            serverDialogStage.close();
        }
        else if (event.getSource() == chatSendButton){
            sendChatMsg();
        }
        else {
            logMsg("Błąd: coś kliknięto ale nie wiem co to było..." + event.toString());
        }
    }

    private void sendChatMsg(){
        logMsg("Komunikat do wysłąnia: " + chatTextInput.getText());
        if ( !chatTextInput.getText().isEmpty() ) {
            //TODO wysyłanie wiadomośi z chatu

            chatArea.appendText("ja: " + chatTextInput.getText() + "\n");
            try {
                netClient.sendMessage(new Message(1, chatTextInput.getText() + "\n"));
            }
            catch(Exception e){
                logMsg(e.toString());
            }
            chatTextInput.clear();
        }

    }


    private void showConnectionDialog(){

        connectionDialogStage = new Stage();
        GridPane pane = new GridPane();


        connectButton = new Button("Połącz");
        connectButton.setOnAction(this);

        connectCancelButton = new Button("Anuluj");
        connectCancelButton.setOnAction(this);


        textField = new TextField();
        textField.setPromptText("Podaj adres servera");
        textField.setText("localhost");
        textField.setPrefWidth(300);

        pane.add(textField, 0,0);
        pane.add(connectButton, 1,0);
        pane.add(connectCancelButton, 2,0);
        pane.setPadding(new Insets(10,10,10,10));
        pane.setHgap(10);

        Scene scene = new Scene(pane);
        connectionDialogStage.setScene(scene);
        connectionDialogStage.initModality(Modality.APPLICATION_MODAL);
        connectionDialogStage.setTitle("Połączenie");
        connectionDialogStage.setResizable(false);
        connectionDialogStage.showAndWait();
    }

    private void showServerDialog(){

        serverDialogStage = new Stage();
        GridPane pane = new GridPane();


        initServerButton = new Button("Uruchom serwer");
        initServerButton .setOnAction(this);

        serverCancelButton = new Button("Anuluj");
        serverCancelButton.setOnAction(this);


        textField = new TextField();
        textField.setPromptText("Podaj numer portu");
        textField.setText("55555");
        textField.setPrefWidth(300);

        pane.add(textField, 0,0);
        pane.add(initServerButton, 1,0);
        pane.add(serverCancelButton, 2,0);
        pane.setPadding(new Insets(10,10,10,10));
        pane.setHgap(10);

        Scene scene = new Scene(pane);
        serverDialogStage.setScene(scene);
        serverDialogStage.initModality(Modality.APPLICATION_MODAL);
        serverDialogStage.setTitle("Serwer");
        serverDialogStage.setResizable(false);
        serverDialogStage.showAndWait();
    }


    public static void logMsg(String message){
        System.out.println(message);
    }


}
