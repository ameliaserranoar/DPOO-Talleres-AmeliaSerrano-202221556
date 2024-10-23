package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public abstract class CalculadoraTarifas {
	private double IMPUESTO= 0.28; 

	protected abstract int calcularCostoBase(Vuelo vuelo, Cliente cliente); 
	protected abstract double calcularPorcentajeDescuento(Cliente cliente); 
	
	public int calcularTarifa(Vuelo vuelo, Cliente cliente) {
		int costoBase= calcularCostoBase(vuelo,cliente);
		int impuesto= calcularValorImpuestos(costoBase);
		double descuento= calcularPorcentajeDescuento(cliente);
		if (descuento==0) {
			return costoBase + impuesto; 
		}
		else {
			return (int)((costoBase*(1-descuento))+impuesto); 
		}
	
	}
	public int calcularDistanciaVuelo(Ruta ruta) {
		Aeropuerto destino= ruta.getDestino();
		Aeropuerto origen= ruta.getOrigen();
		int distancia= Aeropuerto.calcularDistancia(destino, origen);
		return (int) distancia; 
	}
	public int calcularValorImpuestos(int costoBase) {
		return (int) (costoBase*IMPUESTO); 
	}
	
	
}
