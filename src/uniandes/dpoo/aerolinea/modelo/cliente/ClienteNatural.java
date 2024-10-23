package uniandes.dpoo.aerolinea.modelo.cliente;

public class ClienteNatural extends Cliente {
	private String NATURAL= "Natural";
	private String nombre;
	private String identificador; 
	
	public ClienteNatural(String nombre) {
		super();
		this.nombre=nombre;
	}
	public String getTipoCliente() {
		return NATURAL;
	}
	public String getIdentificador() {
		return identificador;
	}
}
