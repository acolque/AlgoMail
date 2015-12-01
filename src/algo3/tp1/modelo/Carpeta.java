package algo3.tp1.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Carpeta {

	private String nombre;
	private ArrayList<Correo> correos;
	private List<String> filtrosDeAsunto;
	private List<String> filtrosDeRemitente;
	
	public Carpeta(String unNombre){
		
		this.nombre = unNombre;
		this.correos = new ArrayList<Correo>();
		this.filtrosDeAsunto = new ArrayList<String>();
		this.filtrosDeRemitente = new ArrayList<String>();
		
	}
	
	public String getNombre(){
		
		return (this.nombre);
	}
	
	public int cantidadCorreos(){
		
		return ( this.correos.size() );
	}

	public void agregarFiltrosDeRemitente(List<String> filtros) {
		
		this.filtrosDeRemitente = filtros;
	}

	public boolean filtrar(Correo correo) {
		
		Iterator<String> iterador;
		
		iterador = this.filtrosDeAsunto.iterator();
		while (iterador.hasNext() ){
			String filtro = iterador.next();
			String asunto = correo.getAsunto();
			if ( asunto.matches("(.*)"+filtro+"(.*)") ){ this.guardarCorreo(correo); return true;}
		}

		iterador = this.filtrosDeRemitente.iterator();
		while (iterador.hasNext() ){
			String filtro = iterador.next();
			String remitente = correo.getRemitente();
			if ( remitente.matches("(.*)"+filtro+"(.*)") ){ this.guardarCorreo(correo); return true;}
		}
		
		return false;
		
	}

	public void guardarCorreo(Correo correo) {
		
		this.correos.add(correo);
	}

	public String asuntoUltimoMensajeRecibido() {
		
		if ( this.correos.size() == 0){
			return "no hay mensajes";
		}else{
			return ( this.correos.get(this.correos.size()-1).getAsunto() );
		}
	}

	public void agregarFiltrosDeAsunto(List<String> filtros) {
		
		this.filtrosDeAsunto = filtros;
		
	}
	
	public ArrayList<Correo> obtenerTodosLosCorreos(){
		
		return this.correos;
	}
}
