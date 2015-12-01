package algo3.tp1.vista;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Enter implements EventHandler<KeyEvent> {

    private Button botonLogin;
    
    public Enter(Button botonLogin) {
        
    	this.botonLogin = botonLogin;
    }

    @Override
    public void handle(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {
            Event.fireEvent(botonLogin, new ActionEvent());
        }
    }
}
