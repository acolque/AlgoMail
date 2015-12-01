package algo3.tp1.vista;

import algo3.tp1.modelo.AlgoMail;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AccionBotonLogout implements EventHandler<ActionEvent> {

	private Stage stage;
	private AlgoMail algoMail;
	private CreadorDeVentanas creador;
	
	public AccionBotonLogout(Stage stage,AlgoMail algoMail){
		
		this.stage = stage;
		this.algoMail = algoMail;
		this.creador = new CreadorDeVentanas();
	}
	
	@Override
    public void handle(ActionEvent actionEvent) {

		algoMail.logout();
		Scene ventana = creador.crearVentanaInicioSesion(stage,algoMail);
        stage.setScene(ventana);
    }

}
