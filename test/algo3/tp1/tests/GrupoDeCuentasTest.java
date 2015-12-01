package algo3.tp1.tests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import algo3.tp1.modelo.*;

public class GrupoDeCuentasTest {

	GrupoDeCuentas unGrupo;
	
	@Before
	public void setup(){
		
		unGrupo = new GrupoDeCuentas();
	}
	
	@Test
	public void testAgregarCuentasAlGrupoIncrementaLaCantidadDeCuentas() {
		
		Cuenta cuenta1 = new Cuenta("a@gmail.com","123456");
		Cuenta cuenta2 = new Cuenta("b@gmail.com","123456");
		Cuenta cuenta3 = new Cuenta("c@gmail.com","123456");
		
		unGrupo.agregarCuenta(cuenta1);
		unGrupo.agregarCuenta(cuenta2);
		unGrupo.agregarCuenta(cuenta3);
		
		Assert.assertEquals(3,unGrupo.cantidadDeCuentas());
	}
	
	@Test
	public void testObtenerListaDeSoloNombresDeLosUsuariosAgregados() {
		
		Cuenta cuenta1 = new Cuenta("a@gmail.com","123456");
		Cuenta cuenta2 = new Cuenta("b@gmail.com","123456");
		Cuenta cuenta3 = new Cuenta("c@gmail.com","123456");
		
		unGrupo.agregarCuenta(cuenta1);
		unGrupo.agregarCuenta(cuenta2);
		unGrupo.agregarCuenta(cuenta3);
		ArrayList<String> listaNombres = unGrupo.obtenerListaNombresDeLasCuentas();
		
		Assert.assertTrue(listaNombres.contains("a@gmail.com"));
		Assert.assertTrue(listaNombres.contains("b@gmail.com"));
		Assert.assertTrue(listaNombres.contains("c@gmail.com"));
	}
	
	@Test
	public void testTodasLasCuentasDelGrupoRecibenUnCorreo()  throws ErrorSesionNoIniciada {
		
		Cuenta cuenta1 = new Cuenta("a@gmail.com","123456");
		Cuenta cuenta2 = new Cuenta("b@gmail.com","123456");
		Cuenta cuenta3 = new Cuenta("c@gmail.com","123456");
		Correo correo = new CorreoNormal("yo","usted","hola","mundo");
		
		unGrupo.agregarCuenta(cuenta1);
		unGrupo.agregarCuenta(cuenta2);
		unGrupo.agregarCuenta(cuenta3);
		unGrupo.recibirCorreo(correo);
		
		cuenta1.login("123456");
		
		Assert.assertEquals(1,cuenta1.cantidadDeCorreos());
		
		cuenta1.logout();
		
		cuenta2.login("123456");
		
		Assert.assertEquals(1,cuenta2.cantidadDeCorreos());
		
		cuenta2.logout();
		
		cuenta3.login("123456");
		
		Assert.assertEquals(1,cuenta3.cantidadDeCorreos());
		
		cuenta3.logout();
	}

}
