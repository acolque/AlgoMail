package algo3.tp1.integrador;

import algo3.tp1.vista.*;
import algo3.tp1.modelo.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Integrador extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

    	AlgoMail algoMail = new AlgoMail();
    	algoMail.nuevaCuenta("arnoldclq3@gmail.com","123456");
    	algoMail.nuevaCuenta("fiuba@gmail.com","123456");
    	algoMail.nuevaCuenta("uade@gmail.com","123456");
    	algoMail.nuevaCuenta("sebas@gmail.com","123456");
    	
    	CreadorDeVentanas creadorDeVentanas = new CreadorDeVentanas();
    	Scene ventanaInicioSesion = creadorDeVentanas.crearVentanaInicioSesion(stage,algoMail);
        	
    	stage.setTitle("AlgoMail");

        stage.setScene(ventanaInicioSesion);
        
        stage.show(); 
    	
    }
}