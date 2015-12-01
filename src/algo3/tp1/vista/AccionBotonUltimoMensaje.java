package algo3.tp1.vista;

import algo3.tp1.modelo.AlgoMail;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AccionBotonUltimoMensaje implements EventHandler<ActionEvent> {

	private Stage stage;
	private AlgoMail algoMail;
	private CreadorDeVentanas creador;
	
	public AccionBotonUltimoMensaje(Stage stage,AlgoMail algoMail){
		
		this.stage = stage;
		this.algoMail = algoMail;
		this.creador = new CreadorDeVentanas();
	}
	
	@Override
    public void handle(ActionEvent actionEvent) {

		Scene ventana = creador.crearVentanaUltimoMensaje(stage,algoMail);
        stage.setScene(ventana);
    }

}
