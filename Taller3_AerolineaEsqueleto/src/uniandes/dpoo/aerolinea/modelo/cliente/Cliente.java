package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import uniandes.dpoo.aerolinea.tiquetes.Tiquete;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes;

public abstract class Cliente {
	private List<Tiquete> TiquetesUsados;
	private List<Tiquete> TiquetesSinUsar; 

	public Cliente() {
		TiquetesUsados= new ArrayList<Tiquete>();
		TiquetesSinUsar= new ArrayList<Tiquete>();
	}
	public void agregarTiquete(Tiquete tiquete) {
			TiquetesSinUsar.add(tiquete);
		}
	
	public List<Tiquete> getTiquetesSinUsar(){
		return TiquetesSinUsar;
	}
	
	public List<Tiquete> getTiquetesUsados(){
		return TiquetesUsados;
	}
	
	public int calcularValorTotalTiquetes() {
		int contador= 0;
		for (Tiquete tiquete:TiquetesUsados) {
			contador+= tiquete.getTarifa();
		}
		for (Tiquete tiquete:TiquetesSinUsar) {
			contador+= tiquete.getTarifa();
		}
		return contador;
	}
	public void usarTiquetes(Vuelo vuelo) {
		Collection<Tiquete> tiquetes= vuelo.getTiquetes();
		for (Tiquete tiquete:tiquetes ) {
			if (TiquetesSinUsar.contains(tiquete)) {
				TiquetesSinUsar.remove(tiquete);
			}
			
		TiquetesUsados.add(tiquete);
	}
	}	
	public abstract String getIdentificador();
	public abstract String getTipoCliente();

}
