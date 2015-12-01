package algo3.tp1.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import algo3.tp1.modelo.AlgoMail;

public class PruebasLoginTest {

	private AlgoMail clienteCorreo; 
	
	@Before
	public void setup() { 
		clienteCorreo = new AlgoMail();
		clienteCorreo.nuevaCuenta("carlosperez@gmail.com", "carlitos");
	}

	@Test
	public void testLoginExitoso() {
		Assert.assertTrue(clienteCorreo.loginUsuario("carlosperez@gmail.com", "carlitos"));
	}

	@Test
	public void testLoginFallaSiContraseniaIncorrecta() {
		Assert.assertFalse(clienteCorreo.loginUsuario("carlosperez@gmail.com", "cualquiera"));
	}

	@Test
	public void testLoginFallaSiUsuarioNoExiste() {
		Assert.assertFalse(clienteCorreo.loginUsuario("juanperez@gmail.com", "carlitos"));
	}

	@Test
	public void testCuentaDeCorreoSeCreaVacia() {
		Assert.assertTrue(clienteCorreo.loginUsuario("carlosperez@gmail.com", "carlitos"));
		Assert.assertEquals(0, clienteCorreo.mensajesEnCarpeta("Principal"));
	}

	@Test
	public void testLogoutExitoso() {
		clienteCorreo.loginUsuario("carlosperez@gmail.com", "carlitos");
		Assert.assertTrue(clienteCorreo.estaUsuarioLogueado());
		clienteCorreo.logout();
		Assert.assertFalse(clienteCorreo.estaUsuarioLogueado());
	}
	
	@Test
	public void testNoPuedenExistirDosUsuarioIguales() {
		Assert.assertFalse(clienteCorreo.nuevaCuenta("carlosperez@gmail.com", "carlitos"));
	}
	
	@Test(expected = RuntimeException.class)
	public void testUsuarioNoLogueadoNoPuederAccionar() {
		clienteCorreo.cantidadDeCorreos();
	}
}
