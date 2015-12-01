package algo3.tp1.tests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import algo3.tp1.modelo.*;

public class CarpetaTest {

	private Carpeta carpeta;
	
	@Before
	public void setup(){
		
		this.carpeta = new Carpeta("Principal");
	}
	
	@Test
	public void testGuardarCorreoIncrementaLaCantidadDeCorreos() {
		
		CorreoNormal correo = new CorreoNormal("yo","usted","hola","mundo");
		CorreoNormal otroCorreo = new CorreoNormal("yo","usted","hola","mundo");
		
		carpeta.guardarCorreo(correo);
		carpeta.guardarCorreo(otroCorreo);
		
		Assert.assertTrue(carpeta.cantidadCorreos()==2);
		
	}

	@Test
	public void testObtenerElAsuntoDelUltimoCorreo(){
		
		CorreoNormal correo1 = new CorreoNormal("yo","usted","hola","mundo");
		CorreoNormal correo2 = new CorreoNormal("yo","usted","hola","mundo");
		CorreoNormal correo3 = new CorreoNormal("yo","usted","hola","mundo");
		CorreoNormal correo4 = new CorreoNormal("yo","usted","PARO DE DOCENTES UADE","Este Lunes");
		
		carpeta.guardarCorreo(correo1);
		carpeta.guardarCorreo(correo2);
		carpeta.guardarCorreo(correo3);
		carpeta.guardarCorreo(correo4);
		
		Assert.assertTrue(carpeta.asuntoUltimoMensajeRecibido()=="PARO DE DOCENTES UADE");
		
	}
	
	@Test
	public void testSoloSeFiltraLosCorreosQueCumplanConElFiltroDeAsunto(){
		
		ArrayList<String> filtros = new ArrayList<String>();
		filtros.add("FIUBA");
		carpeta.agregarFiltrosDeAsunto(filtros);
		CorreoNormal correo1 = new CorreoNormal("yo","usted","hola","mundo");
		CorreoNormal correo2 = new CorreoNormal("yo","usted","PARO DE DOCENTES FIUBA","Este Martes");
		CorreoNormal correo3 = new CorreoNormal("yo","usted","fiuba becas","becas para todos y todas");
		CorreoNormal correo4 = new CorreoNormal("yo","usted","PARO DE DOCENTES UADE","Este Lunes");
		
		carpeta.filtrar(correo1);
		carpeta.filtrar(correo2);
		carpeta.filtrar(correo3);
		carpeta.filtrar(correo4);
		
		Assert.assertEquals(1,carpeta.cantidadCorreos());
	}
	
	@Test
	public void testSoloSeFiltraLosCorreosQueCumplanConElFiltroDeRemitente(){
		
		ArrayList<String> filtros = new ArrayList<String>();
		filtros.add("El ingeniero");
		carpeta.agregarFiltrosDeRemitente(filtros);
		CorreoNormal correo1 = new CorreoNormal("El ingeniero","usted","hola","mundo");
		CorreoNormal correo2 = new CorreoNormal("yo","usted","PARO DE DOCENTES FIUBA","Este Martes");
		CorreoNormal correo3 = new CorreoNormal("El ingeniero","usted","fiuba becas","becas para todos y todas");
		CorreoNormal correo4 = new CorreoNormal("yo","usted","PARO DE DOCENTES UADE","Este Lunes");
		
		carpeta.filtrar(correo1);
		carpeta.filtrar(correo2);
		carpeta.filtrar(correo3);
		carpeta.filtrar(correo4);
		
		Assert.assertEquals(2,carpeta.cantidadCorreos());
	}
	
}
