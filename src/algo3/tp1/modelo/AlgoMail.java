package algo3.tp1.modelo;

import java.util.ArrayList;
import java.util.List;

public class AlgoMail {

	private AdministradorDeCuentas miAdministradorDeCuentas;
	private Cuenta cuentaActual;
	
	public AlgoMail(){
		
		this.miAdministradorDeCuentas = new AdministradorDeCuentas();
		this.cuentaActual = null;
	}
	
	public boolean nuevaCuenta(String unUsuario, String unaContrasenia){
		
		return ( this.miAdministradorDeCuentas.nuevaCuenta(unUsuario,unaContrasenia) );
	}
	
	public boolean loginUsuario(String unUsuario, String unaContrasenia){
		
		try {
			this.cuentaActual = (Cuenta)this.miAdministradorDeCuentas.obtenerCuentaDeUsuario(unUsuario);
		} catch (ErrorUsuarioInexistente e) { return false; };
		
		return ( this.cuentaActual.login(unaContrasenia) );
	}
	
	public int mensajesEnCarpeta(String unaCarpeta){
		
		try { try {
			return ( this.cuentaActual.mensajesEnCarpeta(unaCarpeta) );
		} catch (ErrorSesionNoIniciada e) { return -1; }
		} catch (ErrorCarpetaInexistente e) {return -1; }
	}
	
	public boolean estaUsuarioLogueado(){
		
		return ( this.cuentaActual.estaUsuarioLogueado() );
	}
	
	public void logout(){
		
		this.cuentaActual.logout();
	}
	
	public int cantidadDeCorreos(){
		
		try {
			return ( this.cuentaActual.cantidadDeCorreos() );
		} catch (ErrorSesionNoIniciada e) { return -1; }
	}

	public void nuevaCarpeta(String unaCarpeta) {
		
		try {
			this.cuentaActual.nuevaCarpeta(unaCarpeta);
		} catch (ErrorSesionNoIniciada e) { }
	}

	public void agregarFiltrosRemitenteEnCarpeta(String unaCarpeta, List<String> filtros) {
		
		try { try {
			this.cuentaActual.agregarFiltrosDeRemitenteEnCarpeta(unaCarpeta, filtros);
		} catch (ErrorSesionNoIniciada e) { }
		} catch (ErrorCarpetaInexistente e) { }	
	}

	public boolean enviarCorreo(String unDestinatario, String unAsunto, String unMensaje) {
	
		Receptor cuentaDestinatario = null;
		try {
			cuentaDestinatario = this.miAdministradorDeCuentas.obtenerCuentaDeUsuarioParaEnviarCorreo(unDestinatario);
		} catch (ErrorUsuarioInexistente e) { return false;}
		
		try {
			return ( this.cuentaActual.enviarCorreo(cuentaDestinatario, unAsunto, unMensaje) );
		} catch (ErrorSesionNoIniciada e) { return false;}
	}

	public String asuntoUltimoMensajeRecibidoEnCarpeta(String unaCarpeta) {
		
		try { try {
			return ( this.cuentaActual.asuntoUltimoMensajeRecibidoEnCarpeta(unaCarpeta) );
		} catch (ErrorSesionNoIniciada e) { return ("no hay mensajes"); }
		} catch (ErrorCarpetaInexistente e) { return ("no hay mensajes"); }
	}

	public void nuevaListaCorreos(String nombreLista, List<String> integrantes) {
		
		this.miAdministradorDeCuentas.nuevaListaCorreos(nombreLista, integrantes);
		
	}

	public boolean enviarCorreo(String unDestinatario, List<String> listaCopia, 
			String unAsunto, String unMensaje) {
		
		Receptor cuentaDestinatario;
		try {
			cuentaDestinatario = this.miAdministradorDeCuentas.obtenerCuentaDeUsuarioParaEnviarCorreo(unDestinatario);
		} catch (ErrorUsuarioInexistente e) { return false;}
		
		GrupoDeCuentas listaCuentas = this.miAdministradorDeCuentas.obtenerGrupoCuentasDeUsuarios(listaCopia);
		
		try {
			return ( this.cuentaActual.enviarCorreo(cuentaDestinatario, listaCuentas, unAsunto, unMensaje) );
		} catch (ErrorSesionNoIniciada e) { return false;}
		
	}

	public boolean enviarCorreo(String unDestinatario, List<String> listaCopia,
			List<String> listaCopiaOculta, String unAsunto, String unMensaje) {

		Receptor cuentaDestinatario;
		try {
			cuentaDestinatario = this.miAdministradorDeCuentas.obtenerCuentaDeUsuarioParaEnviarCorreo(unDestinatario);
		} catch (ErrorUsuarioInexistente e) { return false;}
		
		GrupoDeCuentas listaCuentas = this.miAdministradorDeCuentas.obtenerGrupoCuentasDeUsuarios(listaCopia);
		GrupoDeCuentas listaCuentasConCopiaOculta = this.miAdministradorDeCuentas.obtenerGrupoCuentasDeUsuarios(listaCopiaOculta);
		
		try {
			return ( this.cuentaActual.enviarCorreo(cuentaDestinatario, listaCuentas, listaCuentasConCopiaOculta, unAsunto, unMensaje) );
		} catch (ErrorSesionNoIniciada e) { return false;}		
		
	}

	public ArrayList<String> copiadosUltimoMensajeRecibidoEnCarpeta(String unaCarpeta) {
		
		try {
			return ( this.cuentaActual.copiadosUltimoMensajeRecibido() );
		} catch (ErrorSesionNoIniciada e) { return null; }
	}

	public void nuevoAlias(String unAlias, String unUsuario) {
		
		try {
			this.miAdministradorDeCuentas.nuevoAlias(unAlias, unUsuario);
		} catch (ErrorUsuarioInexistente e) { }
	}

	public void agregarFiltrosDeAsuntoEnCarpeta(String unaCarpeta, List<String> filtros) {
		
		try { try {
			this.cuentaActual.agregarFiltrosDeAsuntoEnCarpeta(unaCarpeta, filtros);
		} catch (ErrorSesionNoIniciada e) { }
		} catch (ErrorCarpetaInexistente e) { }	
	}
	
	/** nuevo metodo agregado para mostrar el ultimo mensaje en la interfaz **/
	public String ultimoMensajeRecibido() {
		
		try {
			return ( this.cuentaActual.ultimoMensajeRecibido() );
		} catch (ErrorSesionNoIniciada e) { return ("no hay mensajes"); }
	}
	
	/** nuevo metodo agregado para mostrar todos los mensajes en la interfaz **/
	public ArrayList<Correo> obtenerTodosLosMensajesDeCarpeta(String carpeta){
		
		try { try {
			return ( this.cuentaActual.obtenerTodosLosMensajesDeCarpeta(carpeta) );
		} catch (ErrorSesionNoIniciada e) { return (new ArrayList<Correo>()); }
		} catch (ErrorCarpetaInexistente e) { return (new ArrayList<Correo>()); }
	}
	
	/** nuevo metodo agregado para mostrar nombre de usuario en la interfaz **/
	public String usuarioActual(){
		
		if (this.cuentaActual != null){
			
			return this.cuentaActual.getNombreReceptor();
			
		}else{
			
			return "ninguna cuenta abierta";
		}
	}
}


