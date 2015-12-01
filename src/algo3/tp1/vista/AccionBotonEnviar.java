package algo3.tp1.vista;

import algo3.tp1.modelo.AlgoMail;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AccionBotonEnviar implements EventHandler<ActionEvent> {

	private Stage stage;
	private Text mensajeError;
	private TextField destinatario;
	private TextField asunto;
	private TextField mensaje;
	private AlgoMail algoMail;
	private CreadorDeVentanas creador;
	
	public AccionBotonEnviar(Stage stage, AlgoMail algoMail,TextField destinatario,TextField asunto,TextField mensaje,Text mensajeError){
		
		this.stage = stage;
		this.mensajeError = mensajeError;
		this.destinatario = destinatario;
		this.asunto = asunto;
		this.mensaje = mensaje;
		this.algoMail = algoMail;
		this.creador = new CreadorDeVentanas();
	}
	
	@Override
    public void handle(ActionEvent actionEvent) {

		boolean envioExitoso = algoMail.enviarCorreo(destinatario.getText(), asunto.getText(), mensaje.getText());
		
		if (envioExitoso){
			
			Scene ventana = creador.crearVentanaMensajeEnviado(stage,algoMail);
			stage.setScene(ventana);
			
		}else{
			
			mensajeError.setFill(Color.FIREBRICK);
			mensajeError.setText("MENSAJE NO ENVIADO. USUARIO NO EXISTE");
		}
    }

}
