package uniandes.dpoo.aerolinea.tiquetes;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public class Tiquete {
	private String codigo; 
	private int tarifa; 
	private boolean usado;
	private Vuelo vuelo; 
	private Cliente comprador;
	

	public Tiquete(String codigo, Vuelo vuelo,Cliente comprador, int tarifa) {
		this.codigo=codigo;
		this.vuelo=vuelo;
		this.comprador=comprador;
		this.tarifa=tarifa;
	}
	public Cliente getCliente() {
		return comprador;
	}
	public Vuelo getVuelo() {
		return vuelo; 
	}
	public String getCodigo(){
		return codigo;
	}
	public int getTarifa() {
		return tarifa;
	}
	public void marcarComoUsado() {
		this.usado=true;
	}
	public boolean esUsado() {
		return usado;
	}
}
