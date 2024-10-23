package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public class CalculadoraTarifasTemporadaAlta extends CalculadoraTarifas {
	
	private int COSTO_POR_KM= 1000;
	
	@Override
	protected double calcularPorcentajeDescuento(Cliente cliente) {
		return 0;
		
	}
	@Override
	protected int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
		int distancia= super.calcularDistanciaVuelo(vuelo.getRuta());
		return (int) distancia*COSTO_POR_KM;
		
	}
}

	
