package algo3.tp1.modelo;

import java.util.ArrayList;

public class GestorDeEnvios {

	public boolean enviarCorreo(String remitente, Receptor cuentaDestinatario,String unAsunto, String unMensaje) {
		
		String destinatario = cuentaDestinatario.getNombreReceptor();
		CorreoNormal correo = new CorreoNormal(remitente, destinatario, unAsunto, unMensaje);
		
		return ( cuentaDestinatario.recibirCorreo(correo) );
	}

	public boolean enviarCorreo(String remitente, Receptor cuentaDestinatario, 
			GrupoDeCuentas listaCuentas, String unAsunto, String unMensaje) {
		
		this.enviarCorreo(remitente, cuentaDestinatario, unAsunto, unMensaje);
		
		String destinatario = cuentaDestinatario.getNombreReceptor();
		ArrayList<String> destinatarios = listaCuentas.obtenerListaNombresDeLasCuentas();
		
		CorreoCopia correo = new CorreoCopia(remitente, destinatario, destinatarios, unAsunto,
				("Correo copia: "+ unMensaje) );
		
		return ( listaCuentas.recibirCorreo(correo) );
	}

	public boolean enviarCorreo(String remitente, Receptor cuentaDestinatario,
			GrupoDeCuentas listaCuentas,
			GrupoDeCuentas listaCuentasConCopiaOculta, String unAsunto,
			String unMensaje) {
		
		this.enviarCorreo(remitente, cuentaDestinatario, listaCuentas, unAsunto, unMensaje);

		String destinatario = cuentaDestinatario.getNombreReceptor();
		ArrayList<String> destinatarios = listaCuentas.obtenerListaNombresDeLasCuentas();
		
		CorreoCopia correo = new CorreoCopia(remitente, destinatario, destinatarios, unAsunto,
				("Correo copia: " + unMensaje));
		
		return ( listaCuentasConCopiaOculta.recibirCorreo(correo) );
	}

}
