package algo3.tp1.modelo;

public class Correo {

	private String remitente;
	private String destinatario;
	private String asunto;
	private String mensaje;
	
	public Correo(){
		
	}
	public Correo(String unRemitente, String unDestinatario, String unAsunto, String unMensaje){
		
		this.remitente = unRemitente;
		this.destinatario = unDestinatario;
		this.asunto = unAsunto;
		this.mensaje = unMensaje;
	}
	
	public String getRemitente(){
		
		return this.remitente;
	}
	
	public String getDestinatario(){
		
		return this.destinatario;
	}
	
	public String getAsunto(){
		
		return this.asunto;
	}
	
	public String getMensaje(){
		
		return this.mensaje;
	}
}

