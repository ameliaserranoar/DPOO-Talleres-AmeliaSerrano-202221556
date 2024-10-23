package uniandes.dpoo.aerolinea.persistencia;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import org.json.JSONArray;
import org.json.JSONObject;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Avion;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;

public class PersistenciaAerolineaJson implements IPersistenciaAerolinea {

	@Override
	public void cargarAerolinea(String archivo, Aerolinea aerolinea) throws IOException, InformacionInconsistenteException {
		String jsonCompleto = new String( Files.readAllBytes( new File( archivo ).toPath( ) ) );
	    JSONObject raiz = new JSONObject( jsonCompleto );

	    cargarAviones( aerolinea, raiz.getJSONArray( "aviones" ) );
        cargarRutas( aerolinea, raiz.getJSONArray( "rutas" ) );
        cargarVuelos( aerolinea, raiz.getJSONArray( "vuelos" ) );
		
	}
	
	private void cargarVuelos(Aerolinea aerolinea, JSONArray jVuelos) {
        for (int i = 0; i < jVuelos.length(); i++) {
            JSONObject vueloJson = jVuelos.getJSONObject(i);
            String fecha = vueloJson.getString("fecha");
            Ruta ruta = aerolinea.getRuta(vueloJson.getString("codigoRuta"));
            Avion avion = aerolinea.getAvionNombre(vueloJson.getString("nombreAvion"));
            Vuelo vuelo = new Vuelo(ruta, fecha, avion);
            aerolinea.agregarVuelo(vuelo);
        }
    } 
	
	private void cargarRutas(Aerolinea aerolinea, JSONArray jRutas) {
	       for (int i = 0; i < jRutas.length(); i++) {
	            JSONObject rutaJson = jRutas.getJSONObject(i);
	            String codigoRuta = rutaJson.getString("codigoRuta");
	            String horaSalida = rutaJson.getString("horaSalida");
	            String horaLlegada = rutaJson.getString("horaLlegada");
	            String codigoOrigen = rutaJson.getString("codigoOrigen");
	            String codigoDestino = rutaJson.getString("codigoDestino");
	            Aeropuerto origen = new Aeropuerto(codigoOrigen, "", codigoDestino, 0, 0);  
	            Aeropuerto destino = new Aeropuerto(codigoDestino, "", codigoDestino, 0, 0);  
	            Ruta ruta = new Ruta(destino, origen, horaSalida, horaLlegada, codigoRuta);
	            aerolinea.agregarRuta(ruta);
	       }
		
	}
	private void cargarAviones(Aerolinea aerolinea, JSONArray jAviones) {
	        for (int i = 0; i < jAviones.length(); i++) {
	             JSONObject avionJson = jAviones.getJSONObject(i);
	             String nombre = avionJson.getString("nombre");
	             int capacidad = avionJson.getInt("capacidad");
	             Avion avion = new Avion(nombre, capacidad);
	             aerolinea.agregarAvion(avion);
	           }
	       }
	@Override
    public void salvarAerolinea(String archivo, Aerolinea aerolinea) throws IOException {
        try {
            JSONObject jsonAerolinea = new JSONObject();
            salvarAviones(jsonAerolinea, aerolinea);
            salvarRutas(jsonAerolinea, aerolinea);
            salvarVuelos(jsonAerolinea, aerolinea);
            try (FileWriter file = new FileWriter(archivo)) {
                file.write(jsonAerolinea.toString(4)); 

        } }catch (IOException e) {
        	throw new IOException("Error al escribir el archivo JSON", e);     	
        }
        }
    

    private void salvarAviones(JSONObject jsonAerolinea, Aerolinea aerolinea) {
        JSONArray avionesArray = new JSONArray();
        for (Avion avion : aerolinea.getAviones()) {
            JSONObject jsonAvion = new JSONObject();
            jsonAvion.put("nombre", avion.getNombre());
            jsonAvion.put("capacidad", avion.getCapacidad());
            avionesArray.put(jsonAvion);
        }
        jsonAerolinea.put("aviones", avionesArray);
    }

    private void salvarRutas(JSONObject jsonAerolinea, Aerolinea aerolinea) {
        JSONArray rutasArray = new JSONArray();
        for (Ruta ruta : aerolinea.getRutas()) {
            JSONObject jsonRuta = new JSONObject();
            jsonRuta.put("codigoOrigen", ruta.getOrigen().getCodigo());
            jsonRuta.put("codigoDestino", ruta.getDestino().getCodigo());
            jsonRuta.put("horaSalida", ruta.getHoraSalida());
            jsonRuta.put("horaLlegada", ruta.getHoraLlegada());
            jsonRuta.put("codigoRuta", ruta.getCodigoRuta());
            rutasArray.put(jsonRuta);
        }
        jsonAerolinea.put("rutas", rutasArray);
    }

    private void salvarVuelos(JSONObject jsonAerolinea, Aerolinea aerolinea) {
        JSONArray vuelosArray = new JSONArray();
        for (Vuelo vuelo : aerolinea.getVuelos()) {
            JSONObject jsonVuelo = new JSONObject();
            jsonVuelo.put("codigoRuta", vuelo.getRuta().getCodigoRuta());
            jsonVuelo.put("fecha", vuelo.getFecha());
            jsonVuelo.put("nombreAvion", vuelo.getAvion().getNombre());
            vuelosArray.put(jsonVuelo);
        }
        jsonAerolinea.put("vuelos", vuelosArray);
    }
}

