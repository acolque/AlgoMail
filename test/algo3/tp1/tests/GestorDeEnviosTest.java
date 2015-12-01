package algo3.tp1.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import algo3.tp1.modelo.*;

public class GestorDeEnviosTest {

	private GestorDeEnvios unGestor;
	
	@Before
	public void setup(){
		
		unGestor = new GestorDeEnvios();
	}
	
	@Test
	public void testEnvioConCopiaExitoso() throws ErrorSesionNoIniciada {
		
		Cuenta remitente = new Cuenta("arnldclq3@gmail.com","123456");
		Cuenta destinatario1 = new Cuenta("a@gmail.com","123456");
		Cuenta destinatario2 =  new Cuenta("b@gmail.com","123456");
		Cuenta destinatario3 = new Cuenta("c@gmail.com","123456");
		GrupoDeCuentas destinatarios = new GrupoDeCuentas();
		destinatarios.agregarCuenta(destinatario2);
		destinatarios.agregarCuenta(destinatario3);
		
		boolean recibido = unGestor.enviarCorreo(remitente.getNombreReceptor(), destinatario1, destinatarios, "hola", "mundo");
		
		Assert.assertTrue(recibido);
		
		destinatario3.login("123456");
		
		Assert.assertEquals(1, destinatario3.cantidadDeCorreos());
		
		destinatario3.logout();
	}
	
	@Test
	public void testEnvioConCopiaOculto() throws ErrorSesionNoIniciada {
		
		Cuenta remitente = new Cuenta("arnldclq3@gmail.com","123456");
		Cuenta destinatario1 = new Cuenta("a@gmail.com","123456");
		Cuenta destinatario2 =  new Cuenta("b@gmail.com","123456");
		Cuenta destinatario3 = new Cuenta("c@gmail.com","123456");
		Cuenta destinatario4 = new Cuenta("d@gmail.com","123456");
		GrupoDeCuentas destinatarios = new GrupoDeCuentas();
		GrupoDeCuentas otrosDestinatarios = new GrupoDeCuentas();
		destinatarios.agregarCuenta(destinatario2);
		destinatarios.agregarCuenta(destinatario3);
		otrosDestinatarios.agregarCuenta(destinatario4);
		
		boolean recibido = unGestor.enviarCorreo(remitente.getUsuario(), destinatario1, destinatarios, otrosDestinatarios, "HOLA", "MUNDOD");
		
		Assert.assertTrue(recibido);
		
		destinatario4.login("123456");
		
		Assert.assertEquals(1, destinatario4.cantidadDeCorreos());
		
		destinatario4.logout();
		
	}
	
	@Test
	public void testEnvioNormalExitoso() throws ErrorSesionNoIniciada {
		
		Cuenta remitente = new Cuenta("arnldclq3@gmail.com","123456");
		Cuenta destinatario1 = new Cuenta("a@gmail.com","123456");
		
		boolean recibido = unGestor.enviarCorreo(remitente.getNombreReceptor(), destinatario1, "HOLA", "MUNDO");
		
		Assert.assertTrue(recibido);
		
		destinatario1.login("123456");
		
		Assert.assertEquals(1, destinatario1.cantidadDeCorreos());
		
		destinatario1.logout();
	}
	
	
	
	

}
