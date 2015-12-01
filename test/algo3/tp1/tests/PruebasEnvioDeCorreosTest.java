package algo3.tp1.tests;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import algo3.tp1.modelo.AlgoMail;

public class PruebasEnvioDeCorreosTest {

	private AlgoMail clienteCorreo;
	
	@Before
	public void setup() {
		clienteCorreo = new AlgoMail();
		clienteCorreo.nuevaCuenta("carlosperez@gmail.com", "carlitos");
		clienteCorreo.nuevaCuenta("juanperez@gmail.com", "juancito");
		clienteCorreo.nuevaCuenta("anaperez@gmail.com", "anita");
		clienteCorreo.nuevaCuenta("jorgeperez@gmail.com", "jorgito");
		clienteCorreo.nuevaCuenta("raulperez@gmail.com", "raulito");
	}

	@Test
	public void testDestinatarioInexistente() {
		clienteCorreo.loginUsuario("carlosperez@gmail.com", "carlitos");
		boolean resultado = clienteCorreo.enviarCorreo("pedro@gmail.com", "Saludo", "Hola Pedro!");
		Assert.assertFalse(resultado);
	}
	
	@Test
	public void testEnvioCorreoExitoso() {
		clienteCorreo.loginUsuario("carlosperez@gmail.com", "carlitos");
		clienteCorreo.enviarCorreo("juanperez@gmail.com", "Saludo", "Hola Juan!!");
		clienteCorreo.logout();
		clienteCorreo.loginUsuario("juanperez@gmail.com", "juancito");
		Assert.assertEquals("Saludo",clienteCorreo.asuntoUltimoMensajeRecibidoEnCarpeta("Principal"));	
	}
	
	@Test
	public void testEnvioAListaDeCorreos() {
		List<String> integrantes = Arrays.asList("juanperez@gmail.com", "anaperez@gmail.com");
		clienteCorreo.nuevaListaCorreos("juanYana", integrantes);

		clienteCorreo.loginUsuario("juanperez@gmail.com", "juancito");
		clienteCorreo.enviarCorreo("juanYana", "Saludo", "Hola a todos!!");
		
		Assert.assertEquals("Saludo",clienteCorreo.asuntoUltimoMensajeRecibidoEnCarpeta("Principal"));		
		clienteCorreo.logout();
		
		clienteCorreo.loginUsuario("anaperez@gmail.com", "anita");
		Assert.assertEquals("Saludo",clienteCorreo.asuntoUltimoMensajeRecibidoEnCarpeta("Principal"));
		
		clienteCorreo.loginUsuario("carlosperez@gmail.com", "carlitos");
		Assert.assertEquals(0, clienteCorreo.cantidadDeCorreos());
	}
	
	@Test
	public void testEnvioCorreoConCopiaYListaDestinatarios() {

		List<String> integrantes = Arrays.asList("juanperez@gmail.com", "anaperez@gmail.com");
		clienteCorreo.nuevaListaCorreos("juanYana", integrantes);

		clienteCorreo.loginUsuario("carlosperez@gmail.com", "carlitos");

		List<String> listaCopia = Arrays.asList("jorgeperez@gmail.com", "juanYana");
		clienteCorreo.enviarCorreo("raulperez@gmail.com",  listaCopia,  "Saludos", "Otros saludos!");
		
		Assert.assertEquals(0, clienteCorreo.cantidadDeCorreos());
		clienteCorreo.logout();
		
		clienteCorreo.loginUsuario("anaperez@gmail.com", "anita");
		Assert.assertEquals(1,clienteCorreo.cantidadDeCorreos());
		clienteCorreo.logout();
		
		clienteCorreo.loginUsuario("raulperez@gmail.com", "raulito");
		Assert.assertEquals(1,clienteCorreo.cantidadDeCorreos());
		clienteCorreo.logout();
	}
	
	@Test
	public void testEnvioCorreoConCopiaOculta() {
		
		clienteCorreo.loginUsuario("carlosperez@gmail.com", "carlitos");
		
		List<String> listaCopia = Arrays.asList("anaperez@gmail.com");
		List<String> listaCopiaOculta = Arrays.asList("raulperez@gmail.com","jorgeperez@gmail.com");
		
		clienteCorreo.enviarCorreo("juanperez@gmail.com", listaCopia, listaCopiaOculta, "Saludos", "Hola!");
		
		clienteCorreo.logout();
		
		clienteCorreo.loginUsuario("raulperez@gmail.com", "raulito");
		Assert.assertEquals(1, clienteCorreo.cantidadDeCorreos());
		Assert.assertFalse(clienteCorreo.copiadosUltimoMensajeRecibidoEnCarpeta("Principal").contains("raulperez@gmail.com"));
		Assert.assertTrue(clienteCorreo.copiadosUltimoMensajeRecibidoEnCarpeta("Principal").contains("anaperez@gmail.com"));
		Assert.assertTrue(clienteCorreo.copiadosUltimoMensajeRecibidoEnCarpeta("Principal").contains("juanperez@gmail.com"));
		
	}
	
	@Test
	public void testEnvioCorreosConAliasYEnCopia() {
		
		clienteCorreo.nuevoAlias("charlie", "carlosperez@gmail.com");
		clienteCorreo.nuevoAlias("anita", "anaperez@gmail.com");
		
		clienteCorreo.loginUsuario("carlosperez@gmail.com", "carlitos");
		
		
		List<String> listaCopia = Arrays.asList("anita");
		
		clienteCorreo.enviarCorreo("charlie", listaCopia, "Saludos", "Saludos con alias!");
		
		
		clienteCorreo.logout();
		
		clienteCorreo.loginUsuario("anaperez@gmail.com", "anita");
		Assert.assertEquals(1, clienteCorreo.cantidadDeCorreos());
		clienteCorreo.logout();
		
		clienteCorreo.loginUsuario("carlosperez@gmail.com", "carlitos");
		Assert.assertEquals(1, clienteCorreo.cantidadDeCorreos());
		clienteCorreo.logout();
		
	}
}
