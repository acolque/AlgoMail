package algo3.tp1.tests;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

import algo3.tp1.modelo.AlgoMail;

public class PruebasClasificacionCorreosTest {

	private AlgoMail clienteCorreo;

	@Before
	public void setup() {
		clienteCorreo = new AlgoMail();
		clienteCorreo.nuevaCuenta("carlosperez@gmail.com", "carlitos");
		clienteCorreo.nuevaCuenta("juanperez@gmail.com", "juancito");
		clienteCorreo.nuevaCuenta("anaperez@gmail.com", "anita");
		clienteCorreo.nuevaCuenta("jorgeperez@gmail.com", "jorgito");
		clienteCorreo.nuevaCuenta("raulperez@gmail.com", "raulito");
		clienteCorreo.nuevaCuenta("ventas@algocar.com.ar", "algocar");
		clienteCorreo.nuevaCuenta("ventas@algophones.com.ar", "algophones");
	}

	@Test
	public void testCalificacionPorRemitente() {
		
		List<String> filtros = Arrays.asList("ventas@algocar.com.ar", "ventas@algophones.com.ar");
		
		clienteCorreo.loginUsuario("juanperez@gmail.com", "juancito");
		clienteCorreo.nuevaCarpeta("Spam");
		clienteCorreo.agregarFiltrosRemitenteEnCarpeta("Spam", filtros);
		clienteCorreo.logout();
		
		clienteCorreo.loginUsuario("ventas@algocar.com.ar", "algocar");
		clienteCorreo.enviarCorreo("juanperez@gmail.com", "0 km oferta", "comprate un auto");
		clienteCorreo.logout();
		
		clienteCorreo.loginUsuario("juanperez@gmail.com", "juancito");
		Assert.assertEquals("0 km oferta", clienteCorreo.asuntoUltimoMensajeRecibidoEnCarpeta("Spam"));
		
	}

	@Test
	public void testCalificacionPorAsunto() {
		
		List<String> filtros = Arrays.asList("facultad", "fiuba");
		
		clienteCorreo.loginUsuario("juanperez@gmail.com", "juancito");
		clienteCorreo.nuevaCarpeta("Facultad");
		clienteCorreo.agregarFiltrosDeAsuntoEnCarpeta("Facultad", filtros);
		clienteCorreo.logout();
		
		clienteCorreo.loginUsuario("jorgeperez@gmail.com", "jorgito");
		clienteCorreo.enviarCorreo("juanperez@gmail.com", "Venis a la facultad?", "Avisame por favor");
		clienteCorreo.logout();
		
		clienteCorreo.loginUsuario("juanperez@gmail.com", "juancito");
		Assert.assertEquals("Venis a la facultad?", clienteCorreo.asuntoUltimoMensajeRecibidoEnCarpeta("Facultad"));
		
	}

}
