package algo3.tp1.tests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import algo3.tp1.modelo.*;

public class AdministradorDeCuentasTest {

	private AdministradorDeCuentas admin;
	
	@Before
	public void setup(){
	
		admin = new AdministradorDeCuentas();
		admin.nuevaCuenta("arnoldclq3@gmail.com", "123456");
	}
		
	@Test
	public void testAgregarUnaCuentaValidaIncrementaLaCantidadDeCuentas(){
		
		admin.nuevaCuenta("arnoldclq3@hotmail.com","1233333");
		
		int cantidadCuentas = admin.cantidadCuentas();
		
		Assert.assertTrue(cantidadCuentas==2);
	}
	
	@Test
	public void testAgregarUnaListaDeCorreosSoloContemplaLasCuentasQueExisten()
	throws ErrorUsuarioInexistente {
		
		admin.nuevaCuenta("a@gmail.com","aaadddd");
		admin.nuevaCuenta("b@gmail.com","aaadddd");
		admin.nuevaCuenta("c@gmail.com","aaadddd");
		ArrayList<String> integrantes = new ArrayList<String>();
		integrantes.add("a@gmail.com");
		integrantes.add("b@gmail.com");
		integrantes.add("c@gmail.com");
		integrantes.add("d@gmail.com");
		admin.nuevaListaCorreos("abcd@gmail.com", integrantes);
		
		GrupoDeCuentas listaCuentas = (GrupoDeCuentas)admin.obtenerCuentaDeUsuarioParaEnviarCorreo("abcd@gmail.com");
		ArrayList<String> integrantesValidos = listaCuentas.obtenerListaNombresDeLasCuentas();
		
		Assert.assertFalse(integrantesValidos.contains("d@gmail.com"));
		
	}
	
	@Test
	public void testNombreUsuarioDuplicado(){
		
		boolean creacionExitosa = admin.nuevaCuenta("arnoldclq3@gmail.com","dadsdada");
		
		Assert.assertFalse(creacionExitosa);
		
	}
	
	@Test(expected = ErrorUsuarioInexistente.class)
	public void testObtenerCuentaLanzaExcepcionSiLaCuentaNoExiste()
	throws ErrorUsuarioInexistente {
		
		admin.obtenerCuentaDeUsuario("franciscoosss@ddadd.com");
		
	}
	
	@Test
	public void testObtenerGrupoDeCuentasDevuelveSoloLasCuentasQueExisten(){
		
		admin.nuevaCuenta("a@gmail.com","aaadddd");
		admin.nuevaCuenta("b@gmail.com","aaadddd");
		admin.nuevaCuenta("c@gmail.com","aaadddd");
		ArrayList<String> integrantes = new ArrayList<String>();
		integrantes.add("a@gmail.com");
		integrantes.add("b@gmail.com");
		integrantes.add("c@gmail.com");
		integrantes.add("d@gmail.com");
		admin.nuevaListaCorreos("abcd@gmail.com", integrantes);
		
		GrupoDeCuentas grupoCuentas = admin.obtenerGrupoCuentasDeUsuarios(integrantes);
		ArrayList<String> integrantesValidos = grupoCuentas.obtenerListaNombresDeLasCuentas();
		
		Assert.assertFalse(integrantesValidos.contains("d@gmail.com"));
		
	}
}
