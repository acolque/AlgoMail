package algo3.tp1.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AdministradorDeCuentas {

	private ArrayList<Cuenta> cuentas;
	private ArrayList<GrupoDeCuentas> gruposDeCuentas; 
	
	public AdministradorDeCuentas(){
		
		this.cuentas = new ArrayList<Cuenta>();
		this.gruposDeCuentas = new ArrayList<GrupoDeCuentas>();
	}
	
	public int cantidadCuentas(){
		
		return ( this.cuentas.size() );	
		
	}
	
	public int cantidadGruposDeCuentas(){
		
		return ( this.gruposDeCuentas.size() );
	}
	
	public boolean nuevaCuenta(String unUsuario, String unaContrasenia){
		
		if ( this.usuarioNoDuplicado(unUsuario) ){
			this.cuentas.add( new Cuenta(unUsuario, unaContrasenia) );
			return true;
		} else {
			return false;
		}
	}
	
	public Cuenta obtenerCuentaDeUsuario(String unUsuario) throws ErrorUsuarioInexistente {
		
		Iterator<Cuenta> iterador = this.cuentas.iterator();
		boolean usuarioEncontrado = false;
		Cuenta unaCuenta = null;
		
		while( iterador.hasNext() && !usuarioEncontrado ){
			unaCuenta = iterador.next();
			if ( unaCuenta.getUsuario().matches("(.*)"+unUsuario+"(.*)")){
				usuarioEncontrado = true;
			}
		}
		
		if ( !usuarioEncontrado ){
			throw new ErrorUsuarioInexistente();
		}
		
		return unaCuenta; 
	}
	
	public Receptor obtenerCuentaDeUsuarioParaEnviarCorreo(String unUsuario) throws ErrorUsuarioInexistente {
		
		Iterator<Cuenta> iteradorCuentas = this.cuentas.iterator();
		Iterator<GrupoDeCuentas> iteradorGruposDeCuentas = this.gruposDeCuentas.iterator();
		boolean usuarioEncontrado = false;
		Receptor unaCuenta = null;
		
		if ( unUsuario.trim().equals("") ){
			
			throw new ErrorUsuarioInexistente();
		}
		
		while( iteradorCuentas.hasNext() && !usuarioEncontrado ){
			unaCuenta = iteradorCuentas.next();
			if ( unaCuenta.getNombreReceptor().matches("(.*)"+unUsuario+"(.*)")){
				usuarioEncontrado = true;
			}
		}
		
		while (iteradorGruposDeCuentas.hasNext() && !usuarioEncontrado){
			unaCuenta = iteradorGruposDeCuentas.next();
			if ( unaCuenta.getNombreReceptor().matches("(.*)"+unUsuario+"(.*)")){
				usuarioEncontrado = true;
			}
		}
		
		if ( !usuarioEncontrado ){
			throw new ErrorUsuarioInexistente();
		}
		
		return unaCuenta; 
	}
	
	private boolean usuarioNoDuplicado(String unUsuario){
		
		Iterator<Cuenta> iterador = this.cuentas.iterator();
		
		while( iterador.hasNext() ){
			Cuenta unaCuenta = (Cuenta)iterador.next();
			if ( unaCuenta.getUsuario() == unUsuario  ){
				return false;
			}
		}
		
		return true;
	}

	public void nuevaListaCorreos(String nombreLista, List<String> integrantes) {
		
		GrupoDeCuentas unGrupoDeCuentas = new GrupoDeCuentas(nombreLista);
		
		Iterator<String> iteradorIntegrantes = integrantes.iterator();
		
		Cuenta unaCuenta = null;
		
		while(iteradorIntegrantes.hasNext()){
			
			String nombreIntegrante = iteradorIntegrantes.next();
			
			unaCuenta = this.obtenerDeCuentas(nombreIntegrante);
			
			if ( unaCuenta != null ){
				unGrupoDeCuentas.agregarCuenta(unaCuenta);
			}
			
		}
		
		this.gruposDeCuentas.add(unGrupoDeCuentas);
		
	}

	public GrupoDeCuentas obtenerGrupoCuentasDeUsuarios(List<String> integrantes) {
		
		GrupoDeCuentas unGrupo = new GrupoDeCuentas();
		
		Iterator<String> iteradorIntegrantes = integrantes.iterator();
		
		Cuenta unaCuenta = null;
		ArrayList<Cuenta> listaCuentas = null;
		
		while(iteradorIntegrantes.hasNext()){
			
			String nombreIntegrante = iteradorIntegrantes.next();
			
			unaCuenta = this.obtenerDeCuentas(nombreIntegrante);
			
			if ( unaCuenta != null ){
				unGrupo.agregarCuenta(unaCuenta);
			}
			
			listaCuentas = this.obtenerDeGruposDeCuentas(nombreIntegrante);
			
			if ( listaCuentas != null ){
				unGrupo.agregarCuentas(listaCuentas);
			}
		
		}
		return unGrupo;
	}


	public void nuevoAlias(String unAlias, String unUsuario) throws ErrorUsuarioInexistente {
		
		Cuenta unaCuenta = (Cuenta)this.obtenerCuentaDeUsuario(unUsuario);
		unaCuenta.nuevoAlias(unAlias);
		
	}
	
	private Cuenta obtenerDeCuentas(String nombreIntegrante){
		
		Iterator<Cuenta> iterador = this.cuentas.iterator();
		boolean usuarioEncontrado = false;
		Cuenta unaCuenta = null;
		
		while( iterador.hasNext() && !usuarioEncontrado ){
			unaCuenta = iterador.next();
			String nombreUsuario = unaCuenta.getUsuario();
			if ( nombreUsuario.matches("(.*)"+nombreIntegrante+"(.*)") ){
				usuarioEncontrado = true;
				return unaCuenta;
			}
		}
		
		return null; 
		
	}
	
	private ArrayList<Cuenta> obtenerDeGruposDeCuentas(String nombreIntegrante) {

		Iterator<GrupoDeCuentas> iterador = this.gruposDeCuentas.iterator();
		boolean usuarioEncontrado = false;
		GrupoDeCuentas cuentas = null;
		
		while (iterador.hasNext() && !usuarioEncontrado){
			cuentas = iterador.next();
			String nombreGrupo = cuentas.getNombreReceptor();
			if ( nombreGrupo == nombreIntegrante ){
				usuarioEncontrado = true;
				return cuentas.obtenerCuentas();
			}
		}
		
		return null;
	}

	
	
	
	
	
}
