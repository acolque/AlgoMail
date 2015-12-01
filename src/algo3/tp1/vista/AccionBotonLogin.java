package algo3.tp1.vista;

import algo3.tp1.modelo.AlgoMail;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AccionBotonLogin implements EventHandler<ActionEvent> {

	private Stage stage;
	private Text mensajeError;
	private TextField usuario;
	private PasswordField contrasenia;
	private AlgoMail algoMail;
	private CreadorDeVentanas creador;
	
	public AccionBotonLogin(Stage stage, AlgoMail algoMail,TextField usuario, PasswordField contrasenia, Text mensajeError){
		
		this.stage = stage;
		this.mensajeError = mensajeError;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.algoMail = algoMail;
		this.creador = new CreadorDeVentanas();
	}
	
	@Override
    public void handle(ActionEvent actionEvent) {

		boolean loginExitoso = algoMail.loginUsuario(usuario.getText(), contrasenia.getText());
		
		if (loginExitoso){
			
			Scene ventana = creador.crearVentanaMenuPrincipal(stage,algoMail);
	        stage.setScene(ventana);
			
		}else{
			
			mensajeError.setFill(Color.FIREBRICK);
			mensajeError.setText("CONTRASEÑA INCORRECTA");
			
		}	
    }
}
