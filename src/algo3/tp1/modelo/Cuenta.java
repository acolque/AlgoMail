package algo3.tp1.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cuenta implements Receptor {
	
	private String contrasenia;
	private String usuario;
	private ArrayList<Carpeta> carpetas;
	private boolean sesionAbierta;
	private Correo ultimoCorreo;
	private CorreoCopia ultimoCorreoConCopia;
	private GestorDeEnvios miGestorDeEnvios;
	
	public Cuenta(String unUsuario, String unaContrasenia){
		
		this.usuario = unUsuario;
		this.contrasenia = unaContrasenia;
		this.sesionAbierta = false;
		this.ultimoCorreo = null;
		this.ultimoCorreoConCopia = new CorreoCopia();
		this.carpetas = new ArrayList<Carpeta>();
		this.miGestorDeEnvios = new GestorDeEnvios();
		
		this.carpetas.add( new Carpeta("Principal") );
		this.carpetas.add( new Carpeta("Enviados") );
	}
	
	public String getUsuario(){
		
		return this.usuario;
	}
	
	public String getNombreReceptor(){
		
		return this.getUsuario();
	}
	
	public boolean login(String unaContrasenia){
		
		if ( unaContrasenia.trim().equals(this.contrasenia) ){
			this.sesionAbierta = true;
			return true;
		}else{
			return false;
		}
	}
	
	public int mensajesEnCarpeta(String unNombre) throws ErrorSesionNoIniciada, ErrorCarpetaInexistente {
		
		this.verificarSesionAbierta();
		
		Carpeta unaCarpeta = this.obtenerCarpeta(unNombre);
		
		return ( unaCarpeta.cantidadCorreos() );
	}
	
	private boolean verificarSesionAbierta() throws ErrorSesionNoIniciada {
		
		if ( !this.sesionAbierta ){
			throw new ErrorSesionNoIniciada();
		}
		
		return ( this.sesionAbierta );
	}
	
	private Carpeta obtenerCarpeta(String unNombre) throws ErrorCarpetaInexistente {
		
		Iterator<Carpeta> iterador = this.carpetas.iterator();
		boolean carpetaEncontrada = false;
		Carpeta unaCarpeta = null;
		
		while( iterador.hasNext() && !carpetaEncontrada ){
			unaCarpeta = iterador.next();
			if ( unaCarpeta.getNombre() == unNombre ){
				carpetaEncontrada = true;
			}
		}
		
		if ( !carpetaEncontrada ){
			throw new ErrorCarpetaInexistente();
		}
		
		return unaCarpeta; 
	}

	public boolean estaUsuarioLogueado() {
		
		return this.sesionAbierta;
	}

	public void logout() {
		
		this.sesionAbierta = false;
	}

	public int cantidadDeCorreos() throws ErrorSesionNoIniciada {
		
		this.verificarSesionAbierta();
		
		int cantidad = 0;
		
		Iterator<Carpeta> iterador = carpetas.iterator();
		while ( iterador.hasNext() ){
			Carpeta unaCarpeta = iterador.next();
			cantidad = ( cantidad + unaCarpeta.cantidadCorreos() );
		}
		
		return cantidad;
	}

	public void nuevaCarpeta(String unaCarpeta) throws ErrorSesionNoIniciada {
		
		this.verificarSesionAbierta();
		
		this.carpetas.add( new Carpeta(unaCarpeta) );
	}

	public void agregarFiltrosDeRemitenteEnCarpeta(String unaCarpeta, List<String> filtros)
	throws ErrorSesionNoIniciada, ErrorCarpetaInexistente {
		
		this.verificarSesionAbierta();
		
		Carpeta carpeta = this.obtenerCarpeta(unaCarpeta);
		carpeta.agregarFiltrosDeRemitente(filtros);
		
	}

	public boolean enviarCorreo(Receptor cuentaDestinatario, String unAsunto,String unMensaje)
	throws ErrorSesionNoIniciada {
		
		this.verificarSesionAbierta();
		
		boolean envioExitoso = this.miGestorDeEnvios.enviarCorreo(this.usuario, cuentaDestinatario, unAsunto, unMensaje); 
		
		if (envioExitoso){
		
			try {
				Correo correo = new Correo(this.usuario,cuentaDestinatario.getNombreReceptor(),unAsunto,unMensaje);
				this.obtenerCarpeta("Enviados").guardarCorreo(correo);
			} catch(ErrorCarpetaInexistente e) { }
		}
		
		return ( envioExitoso );
	}

	public boolean recibirCorreo(Correo correo) {
		
		boolean correoGuardado = false;
		
		Iterator<Carpeta> iterador = carpetas.iterator();
		while ( iterador.hasNext() && !correoGuardado ){
			Carpeta unaCarpeta = iterador.next();
			correoGuardado = unaCarpeta.filtrar(correo);
		}
		
		if ( correo.getMensaje().matches("Correo copia:(.*)") ){
			this.ultimoCorreoConCopia = (CorreoCopia)correo;
		}
		
		if ( !correoGuardado ){
			try { 
				Carpeta carpeta = this.obtenerCarpeta("Principal");
				carpeta.guardarCorreo(correo);
				correoGuardado = true; }
			catch (ErrorCarpetaInexistente e) { return false; }
		}
		
		this.ultimoCorreo = correo;
		
		return correoGuardado;
	}

	public String asuntoUltimoMensajeRecibidoEnCarpeta(String unaCarpeta) throws ErrorSesionNoIniciada,
	ErrorCarpetaInexistente {
	
		this.verificarSesionAbierta();
		
		Carpeta carpeta = this.obtenerCarpeta(unaCarpeta);
		return ( carpeta.asuntoUltimoMensajeRecibido() );
	}
	
	public String ultimoMensajeRecibido() throws ErrorSesionNoIniciada {
	
		this.verificarSesionAbierta();
		
		if ( this.ultimoCorreo != null ){
		
			String mensaje = ("DE: "+this.ultimoCorreo.getRemitente()+"\n"
					+"ASUNTO: "+this.ultimoCorreo.getAsunto()+"\n"
					+"MENSAJE: "+this.ultimoCorreo.getMensaje());
		
			return mensaje;
		
		}else{
			
			return "No hay mensajes";
		}
	}

	public boolean enviarCorreo(Receptor cuentaDestinatario, GrupoDeCuentas listaCuentas, 
			String unAsunto, String unMensaje) throws ErrorSesionNoIniciada {
		
		this.verificarSesionAbierta();
		
		return ( this.miGestorDeEnvios.enviarCorreo(this.usuario, cuentaDestinatario, listaCuentas, unAsunto, unMensaje) );
		
	}

	public boolean enviarCorreo(Receptor cuentaDestinatario,
			GrupoDeCuentas listaCuentas,
			GrupoDeCuentas listaCuentasConCopiaOculta, String unAsunto,
			String unMensaje) throws ErrorSesionNoIniciada {
		
		this.verificarSesionAbierta();
		
		return ( this.miGestorDeEnvios.enviarCorreo(this.usuario, cuentaDestinatario, listaCuentas,
				listaCuentasConCopiaOculta,unAsunto,unMensaje) );
		
	}

	public ArrayList<String> copiadosUltimoMensajeRecibido() throws ErrorSesionNoIniciada {
		
		this.verificarSesionAbierta();
		
		return ( this.ultimoCorreoConCopia.getDestinatarios() );
	}

	public void nuevoAlias(String unAlias) {
		
		this.usuario = ( this.usuario + ", " + unAlias );
	}

	public void agregarFiltrosDeAsuntoEnCarpeta(String unaCarpeta, List<String> filtros)
	throws ErrorSesionNoIniciada, ErrorCarpetaInexistente {
				
		this.verificarSesionAbierta();
		
		Carpeta carpeta = this.obtenerCarpeta(unaCarpeta);
		carpeta.agregarFiltrosDeAsunto(filtros);
	}
	
	public ArrayList<Correo> obtenerTodosLosMensajesDeCarpeta(String unaCarpeta) 
			throws ErrorCarpetaInexistente, ErrorSesionNoIniciada {
		
		this.verificarSesionAbierta();
		
		Carpeta carpeta = this.obtenerCarpeta(unaCarpeta);
		
		return carpeta.obtenerTodosLosCorreos();
	}

}
