package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas {
	
	private int COSTO_POR_KM_NATURAL= 600;
	private int COSTO_POR_KM_CORPORATIVO= 900;
	private double DESCUENTO_PEQ=0.02; 
	private double DESCUENTO_MEDIANA=0.1;
	private double DESCUENTO_GRANDE=0.2;
	
	@Override
	protected int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
		int distancia= super.calcularDistanciaVuelo(vuelo.getRuta());
		if (cliente.getTipoCliente().equals("Natural")) {
			return (int) (COSTO_POR_KM_NATURAL*distancia);
		}
		if (cliente.getTipoCliente().equals("Corporativo")) {
			return (int)(COSTO_POR_KM_CORPORATIVO*distancia);
		}
		return 0;
	}
	@Override
	protected double calcularPorcentajeDescuento(Cliente cliente) {
		
		if (cliente instanceof ClienteCorporativo) {
			int tamanoEmpresa= ((ClienteCorporativo) cliente).getTamanoEmpresa();
			if (tamanoEmpresa == 1) {
				return DESCUENTO_GRANDE;
			}
			if(tamanoEmpresa==2) {
				return DESCUENTO_MEDIANA;
			}
			if(tamanoEmpresa==3) {
				return DESCUENTO_PEQ;
			}
		}
		return 0;
	}

}
