package algo3.tp1.modelo;

import java.util.ArrayList;

public class CorreoCopia extends Correo {

	private ArrayList<String> listaDestinatarios;
	
	public CorreoCopia(String unRemitente, String unDestinatario, ArrayList<String> destinatarios, 
			String unAsunto, String unMensaje) {
		
		super(unRemitente, unDestinatario, unAsunto, unMensaje);
		
		this.listaDestinatarios = destinatarios;
		this.listaDestinatarios.add(unDestinatario);
	}
	
	public CorreoCopia(){
		
		super();
		this.listaDestinatarios = new ArrayList<String>();
	}

	public ArrayList<String> getDestinatarios() {
		
		return this.listaDestinatarios;
	}
	
}
