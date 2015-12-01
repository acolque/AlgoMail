package algo3.tp1.modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class GrupoDeCuentas implements Receptor {

	private String nombreLista;
	private ArrayList<Cuenta> cuentas;
	
	public GrupoDeCuentas(){
		
		cuentas = new ArrayList<Cuenta>();
	}
	
	public GrupoDeCuentas(String unNombreLista){
		
		this.nombreLista = unNombreLista;
		cuentas = new ArrayList<Cuenta>();
	}

	public void agregarCuenta(Cuenta unaCuenta){
		
		this.cuentas.add(unaCuenta);
	}
	
	public boolean recibirCorreo(Correo correo){
		
		boolean correoRecibido = false;
		
		Iterator<Cuenta> iterador = this.cuentas.iterator();
		while ( iterador.hasNext()) {
			Cuenta unaCuenta = iterador.next();
			correoRecibido = unaCuenta.recibirCorreo(correo);
		}
		
		return correoRecibido;
	}

	public ArrayList<String> obtenerListaNombresDeLasCuentas() {
	
		ArrayList<String> listaNombres = new ArrayList<String>();
		
		Iterator<Cuenta> iterador = this.cuentas.iterator();
		while (iterador.hasNext()){
			listaNombres.add(iterador.next().getUsuario());
		}
		
		return listaNombres;
	}
	
	public String getNombreReceptor(){
		
		return this.nombreLista;
	}

	public void agregarCuentas(ArrayList<Cuenta> listaCuentas) {
		
		Iterator<Cuenta> iterador = listaCuentas.iterator();
		
		while (iterador.hasNext()){
			this.cuentas.add(iterador.next());
		}
	}
	
	public ArrayList<Cuenta> obtenerCuentas(){
		
		return this.cuentas;
	}
	
	public int cantidadDeCuentas(){
		
		return this.cuentas.size();
	}
}
