package algo3.tp1.vista;

import java.util.ArrayList;
import java.util.Iterator;

import algo3.tp1.modelo.AlgoMail;
import algo3.tp1.modelo.Correo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreadorDeVentanas {

	public CreadorDeVentanas(){
		
	}
	
	public Scene crearVentanaInicioSesion(Stage stage, AlgoMail algoMail){
		
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text tituloPrincipal = new Text("AlgoMail 2015");
        tituloPrincipal.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(tituloPrincipal, 0, 0, 2, 1);
        
        Label usuarioLabel = new Label("Nombre Usuario:");
        grid.add(usuarioLabel, 0, 1);
        
        TextField usuarioTextField = new TextField();
        grid.add(usuarioTextField, 1, 1);
        
        Label contraseniaLabel = new Label("Contraseña:");
        grid.add(contraseniaLabel, 0, 2);
        
        PasswordField contraseniaPassField = new PasswordField();
        grid.add(contraseniaPassField, 1, 2); 

        final Text mensajeError = new Text();
        grid.add(mensajeError, 1, 6);
        
        Button botonLogin = new Button("Login");
        botonLogin.setOnAction(new AccionBotonLogin(stage,algoMail,usuarioTextField,contraseniaPassField,mensajeError));
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(botonLogin);
        grid.add(hbBtn, 1, 4);
        
        contraseniaPassField.setOnKeyPressed(new Enter(botonLogin));
        
        Scene scene = new Scene(grid, 600, 300);
        return scene;
	}
	
	public Scene crearVentanaMenuPrincipal(Stage stage, AlgoMail algoMail){
		
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text tituloPrincipal = new Text("Menu Principal");
        tituloPrincipal.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(tituloPrincipal, 0, 0, 2, 1);
        
        Text usuario = new Text(algoMail.usuarioActual());
        usuario.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        grid.add(usuario, 3, 0, 2, 1);
        
        Button btn1 = new Button("Nuevo Correo");
        btn1.setOnAction(new AccionBotonNuevoCorreo(stage,algoMail));
        grid.add(btn1, 0, 1);
        
        Button btn2 = new Button("Ultimo Mensaje");
        btn2.setOnAction(new AccionBotonUltimoMensaje(stage,algoMail));
        grid.add(btn2, 0, 2);
        
        Button btn3 = new Button("Bandeja Principal");
        btn3.setOnAction(new AccionBotonPrincipal(stage,algoMail));
        grid.add(btn3, 0, 3);
        
        Button btn4 = new Button("Enviados");
        btn4.setOnAction(new AccionBotonEnviados(stage,algoMail));
        grid.add(btn4, 0, 4);
        
        Button btn5 = new Button("Logout");
        btn5.setOnAction(new AccionBotonLogout(stage,algoMail));
        grid.add(btn5, 0, 6);
        
        Scene scene = new Scene(grid, 600, 300);
        return scene;		
	}
	
	public Scene crearVentanaNuevoCorreo(Stage stage,AlgoMail algoMail){
		
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text tituloPrincipal = new Text("Nuevo Correo");
        tituloPrincipal.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(tituloPrincipal, 0, 0, 2, 1);
        
        Text usuario = new Text(algoMail.usuarioActual());
        usuario.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        grid.add(usuario, 3, 0, 2, 1);
        
        Label destinatarioLabel = new Label("Para:");
        grid.add(destinatarioLabel, 0, 1);
        
        TextField destinatarioTextField = new TextField();
        grid.add(destinatarioTextField, 1, 1);
        
        Label asuntoLabel = new Label("Asunto:");
        grid.add(asuntoLabel, 0, 2);
        
        TextField asuntoTextField = new TextField();
        grid.add(asuntoTextField, 1, 2);
        
        Label mensajeLabel = new Label("Mensaje:");
        grid.add(mensajeLabel, 0, 3);
        
        TextField mensajeTextField = new TextField();
        grid.add(mensajeTextField, 1, 3,20,1);
        
        final Text mensajeError = new Text();
        grid.add(mensajeError, 1, 6);
        
        Button btn1 = new Button("Enviar");
        btn1.setOnAction(new AccionBotonEnviar(stage,algoMail,destinatarioTextField,asuntoTextField,mensajeTextField,mensajeError));
        grid.add(btn1, 1, 4);
        
        Button btn2 = new Button("Menu Principal");
        btn2.setOnAction(new AccionBotonMenuPrincipal(stage,algoMail));
        grid.add(btn2, 1, 5);
        
        Scene scene = new Scene(grid, 600, 300);
        return scene;	
	}
	
	public Scene crearVentanaUltimoMensaje(Stage stage,AlgoMail algoMail){
		
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text tituloPrincipal = new Text("Ultimo Mensaje");
        tituloPrincipal.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(tituloPrincipal, 0, 0, 2, 1);
        
        Text usuario = new Text(algoMail.usuarioActual());
        usuario.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        grid.add(usuario, 3, 0, 2, 1);
        
        String ultimoMensaje = algoMail.ultimoMensajeRecibido();
        Label mensajeLabel = new Label(ultimoMensaje);
        grid.add(mensajeLabel,0,1);
        
        Button btn = new Button("Menu Principal");
        btn.setOnAction(new AccionBotonMenuPrincipal(stage,algoMail));
        grid.add(btn, 0, 2);
       
        Scene scene = new Scene(grid, 600, 300);
        return scene;	
	}
	
	public Scene crearVentanaMensajeEnviado(Stage stage,AlgoMail algoMail){
		
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text tituloPrincipal = new Text("Mensaje Enviado!");
        tituloPrincipal.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(tituloPrincipal, 0, 0, 2, 1);
        
        Text usuario = new Text(algoMail.usuarioActual());
        usuario.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        grid.add(usuario, 3, 0, 2, 1);
        
        Button btn1 = new Button("Menu Principal");
        btn1.setOnAction(new AccionBotonMenuPrincipal(stage,algoMail));
        grid.add(btn1, 0, 1);
        
        Button btn2 = new Button("Nuevo Correo");
        btn2.setOnAction(new AccionBotonNuevoCorreo(stage,algoMail));
        grid.add(btn2, 0, 2);
        
        
        Scene scene = new Scene(grid, 600, 300);
        return scene;	
	}
	
	public Scene crearVentanaEnviados(Stage stage,AlgoMail algoMail){
		
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text tituloPrincipal = new Text("Enviados");
        tituloPrincipal.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(tituloPrincipal, 0, 0, 2, 1);
        
        Text usuario = new Text(algoMail.usuarioActual());
        usuario.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        grid.add(usuario, 3, 0, 2, 1);
                
        ArrayList<Correo> mensajes = algoMail.obtenerTodosLosMensajesDeCarpeta("Enviados");
    	
        int col = 0;
    	int fil = 2;
    	
        if ( mensajes.isEmpty() ) {
        	
        	Label noHayMensaje = new Label("No hay mensajes");
        	grid.add(noHayMensaje, col, fil);
        	
        } else {
        	
            Label destinoLabel = new Label("Destinatario");
            grid.add(destinoLabel,col,1);

            Label asuntoLabel = new Label("Asunto");
            grid.add(asuntoLabel,col+1,1);

            Label mensajeLabel = new Label("Mensaje");
            grid.add(mensajeLabel,col+2,1);
            
            Iterator<Correo> iterador = mensajes.iterator();
            while (iterador.hasNext()){
            	
            	Correo correo = iterador.next();
            	Label destino = new Label(correo.getDestinatario());
            	grid.add(destino, col, fil);
            	
            	Label asunto = new Label(correo.getAsunto());
            	grid.add(asunto, col+1, fil);
            	
            	Label mensaje = new Label(correo.getMensaje());
            	grid.add(mensaje, col+2, fil);
            	
            	fil = fil + 1;
            }
            
        }
        
        Button btn = new Button("Menu Principal");
        btn.setOnAction(new AccionBotonMenuPrincipal(stage,algoMail));
        grid.add(btn, 0, fil+1);
       
        Scene scene = new Scene(grid, 600, 300);
        return scene;	
	}
	
	public Scene crearVentanaPrincipal(Stage stage,AlgoMail algoMail){
		
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text tituloPrincipal = new Text("Bandeja Principal");
        tituloPrincipal.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(tituloPrincipal, 0, 0, 2, 1);
        
        Text usuario = new Text(algoMail.usuarioActual());
        usuario.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        grid.add(usuario, 3, 0, 2, 1);
                
        ArrayList<Correo> mensajes = algoMail.obtenerTodosLosMensajesDeCarpeta("Principal");
    	
        int col = 0;
    	int fil = 2;
    	
        if ( mensajes.isEmpty() ) {
        	
        	Label noHayMensaje = new Label("No hay mensajes");
        	grid.add(noHayMensaje, col, fil);
        	
        } else {
        	
            Label destinoLabel = new Label("Destinatario");
            grid.add(destinoLabel,col,1);

            Label asuntoLabel = new Label("Asunto");
            grid.add(asuntoLabel,col+1,1);

            Label mensajeLabel = new Label("Mensaje");
            grid.add(mensajeLabel,col+2,1);
            
            Iterator<Correo> iterador = mensajes.iterator();
            while (iterador.hasNext()){
            	
            	Correo correo = iterador.next();
            	Label destino = new Label(correo.getDestinatario());
            	grid.add(destino, col, fil);
            	
            	Label asunto = new Label(correo.getAsunto());
            	grid.add(asunto, col+1, fil);
            	
            	Label mensaje = new Label(correo.getMensaje());
            	grid.add(mensaje, col+2, fil);
            	
            	fil = fil + 1;
            }
            
        }
        
        Button btn = new Button("Menu Principal");
        btn.setOnAction(new AccionBotonMenuPrincipal(stage,algoMail));
        grid.add(btn, 0, fil+1);
       
        Scene scene = new Scene(grid, 600, 300);
        return scene;	
	}
}
