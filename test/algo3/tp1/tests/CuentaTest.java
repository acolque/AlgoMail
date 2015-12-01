package algo3.tp1.tests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import algo3.tp1.modelo.*;

public class CuentaTest {

	private Cuenta cuenta;
	
	@Before
	public void setup(){
		
		cuenta = new Cuenta("arnoldclq3@gmail.com","123456");
	}
	
	@Test
	public void testAgregarFiltroyFiltradoExitoso() throws ErrorSesionNoIniciada, ErrorCarpetaInexistente {
		
		cuenta.login("123456");
		cuenta.nuevaCarpeta("FACULTAD");
		ArrayList<String> filtros = new ArrayList<String>();
		filtros.add("FIUBA");
		cuenta.agregarFiltrosDeAsuntoEnCarpeta("FACULTAD", filtros);
		CorreoNormal correo1 = new CorreoNormal("yo","usted","hola","mundo");
		CorreoNormal correo2 = new CorreoNormal("yo","usted","PARO DE DOCENTES FIUBA","Este Martes");
		CorreoNormal correo3 = new CorreoNormal("yo","usted","fiuba becas","becas para todos y todas");
		CorreoNormal correo4 = new CorreoNormal("yo","usted","PARO DE DOCENTES UADE","Este Lunes");
		
		cuenta.recibirCorreo(correo1);
		cuenta.recibirCorreo(correo2);
		cuenta.recibirCorreo(correo3);
		cuenta.recibirCorreo(correo4);
		
		Assert.assertEquals(1, cuenta.mensajesEnCarpeta("FACULTAD"));
	}
	
	@Test
	public void testContraseniaCorrectaParaLoguearse(){
		
		boolean logueoExitoso = cuenta.login("123456");
		
		Assert.assertTrue(logueoExitoso);
	}
	
	@Test
	public void testEnvioConCopiaExitoso() throws ErrorSesionNoIniciada {
		
		cuenta.login("123456");
		Cuenta destinatario1 = new Cuenta("facultad@fiuba.com","222222");
		Cuenta destinatario2 = new Cuenta("facultad@uade.com","222222");
		Cuenta destinatario3 = new Cuenta("facultad@aysa.com","222222");
		GrupoDeCuentas destinatarios = new GrupoDeCuentas("favoritos");
		destinatarios.agregarCuenta(destinatario2);
		destinatarios.agregarCuenta(destinatario3);
		
		boolean recibido = cuenta.enviarCorreo(destinatario1, destinatarios, "hola", "mundo");
		
		Assert.assertTrue(recibido);
		
		destinatario2.login("222222");
		
		Assert.assertEquals(1, destinatario2.cantidadDeCorreos());
	}
	
	@Test
	public void testEnvioNormalExitoso() throws ErrorSesionNoIniciada {
		
		cuenta.login("123456");
		Cuenta destinatario = new Cuenta("facultad@fiuba.com","222222");
		
		boolean recibido = cuenta.enviarCorreo(destinatario, "HOLA", "mundo");
		
		Assert.assertTrue(recibido);
		
		destinatario.login("222222");
		
		Assert.assertEquals(1, destinatario.cantidadDeCorreos());
	}
	
	@Test(expected = ErrorCarpetaInexistente.class)
	public void testLanzaExcepcionSiLaCarpetaNoExiste() throws ErrorCarpetaInexistente, ErrorSesionNoIniciada {
		
		cuenta.login("123456");
		
		cuenta.mensajesEnCarpeta("FACULTAD");
	}
	
	@Test(expected = ErrorSesionNoIniciada.class)
	public void testNoSePuedeAccionarSinIniciarSesion() throws ErrorSesionNoIniciada, ErrorCarpetaInexistente {
		
		Cuenta destinatario = new Cuenta("facultad@gmail.com","123456");
		
		cuenta.enviarCorreo(destinatario, "holis", "todopeola");
		
		cuenta.asuntoUltimoMensajeRecibidoEnCarpeta("Principal");
		
		cuenta.mensajesEnCarpeta("Principal");
		
		cuenta.cantidadDeCorreos();
		
		cuenta.nuevaCarpeta("Facultad");
		
		cuenta.copiadosUltimoMensajeRecibido();
		
		cuenta.agregarFiltrosDeAsuntoEnCarpeta("Facultad", new ArrayList<String>() );
		
		cuenta.agregarFiltrosDeRemitenteEnCarpeta("Facultad", new ArrayList<String>() );
	}
	
	@Test
	public void testNuevoAliasAgregaUnApodoAlNombreDeUsuario() {
		
		cuenta.nuevoAlias("ArnoldFacultad");
		
		Assert.assertEquals("arnoldclq3@gmail.com, ArnoldFacultad", cuenta.getUsuario());
	}
	
	@Test
	public void testObtenerElAsuntoDelUltimoMensajeDeDosMensajesRecibidos() 
	throws ErrorSesionNoIniciada, ErrorCarpetaInexistente {
		
		cuenta.login("123456");
		CorreoNormal correo1 = new CorreoNormal("yo","usted","hola","mundo");
		CorreoNormal correo2 = new CorreoNormal("yo","usted","PARO DE DOCENTES FIUBA","Este Martes");
		
		cuenta.recibirCorreo(correo1);
		cuenta.recibirCorreo(correo2);
		String asunto = cuenta.asuntoUltimoMensajeRecibidoEnCarpeta("Principal");
		
		Assert.assertEquals("PARO DE DOCENTES FIUBA",asunto);
	}
	
	@Test
	public void testRecibirDosCorreosIncrementaLaCantidadDeMensajes()
	throws ErrorSesionNoIniciada, ErrorCarpetaInexistente {
		
		cuenta.login("123456");
		CorreoNormal correo1 = new CorreoNormal("yo","usted","hola","mundo");
		CorreoNormal correo2 = new CorreoNormal("yo","usted","PARO DE DOCENTES FIUBA","Este Martes");
		
		Assert.assertEquals(0,cuenta.cantidadDeCorreos());
	
		cuenta.recibirCorreo(correo1);
		
		Assert.assertEquals(1,cuenta.cantidadDeCorreos());
		
		cuenta.recibirCorreo(correo2);
		
		Assert.assertEquals(2,cuenta.cantidadDeCorreos());	
	}
	
	@Test
	public void testSiNoHayNingunFiltroCorreoSeGuardaEnCarpetaPrincipal()
	throws ErrorSesionNoIniciada, ErrorCarpetaInexistente {
		
		cuenta.login("123456");
		CorreoNormal correo1 = new CorreoNormal("yo","usted","hola","mundo");
		
		cuenta.recibirCorreo(correo1);
		
		Assert.assertEquals(1, cuenta.mensajesEnCarpeta("Principal"));
	}
	
	
	
	
	
	
	
	
	
	

}
