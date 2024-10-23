package uniandes.dpoo.aerolinea.modelo;

import java.util.Collection;

import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public class Vuelo {
	private String fecha; 
	private Ruta ruta;
	private Avion avion; 
	private Collection<Tiquete> tiquetes; 
	private GeneradorTiquetes generadorTiquetes; 
	
	public Vuelo(Ruta ruta, String fecha, Avion avion) {
		this.fecha=fecha;
		this.ruta=ruta;
		this.avion=avion;
	}
	public Ruta getRuta() {
		return ruta;
	}
	public String getFecha() {
		return fecha;
	}
	public Avion getAvion() {
		return avion;
	}
	public Collection<Tiquete> getTiquetes(){
		return tiquetes;
	}
	public int VenderTiquetes(Cliente cliente, CalculadoraTarifas calculadora, int cantidad)  {
		if (cantidad <= 0) {
			return 0;
		}
		int tiqsVendidos = 0; 
		int tarifa= calculadora.calcularTarifa(this, cliente);
		for (int i=0;i<cantidad;i++) {
			Tiquete nuevo= GeneradorTiquetes.generarTiquete(this, cliente,tarifa); 
			tiquetes.add(nuevo); 
			tiqsVendidos+=1; 
			}
		return tiqsVendidos;
	}
	public boolean equals(Object obj) {
		return true;
	}
}
