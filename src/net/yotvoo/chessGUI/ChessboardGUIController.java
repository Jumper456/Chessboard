package net.yotvoo.chessGUI;

import javafx.scene.control.TextArea;
import net.yotvoo.chessnet.ClientGUI;
import net.yotvoo.chessnet.ServerGUI;



public class ChessboardGUIController implements ClientGUI, ServerGUI {

    TextArea chatTextArea;

    public ChessboardGUIController(TextArea textArea) {
        chatTextArea = textArea;
    }

    @Override
    public void append(String textStr) {
        chatTextArea.appendText("\n" + textStr);
    }

    @Override
    public void connectionFailed() {
        chatTextArea.appendText("\nBłąd - Połączenie zostało przerwane lub nie udało się go nawiązać.");
    }

    @Override
    public void appendEvent(String eventStr) {
        chatTextArea.appendText("\n event: " + eventStr);
    }

    @Override
    public void appendRoom(String roomStr) {
        chatTextArea.appendText("\n room: " + roomStr);
    }
}
