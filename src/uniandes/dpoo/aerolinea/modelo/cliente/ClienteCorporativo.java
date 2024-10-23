package uniandes.dpoo.aerolinea.modelo.cliente;

import org.json.JSONObject;

/**
 * Esta clase se usa para representar a los clientes de la aerolínea que son empresas
 */
public class ClienteCorporativo extends Cliente
{
    private String CORPORATIVO= "Corporativo";
    private int GRANDE=1;
    private int MEDIANA=2; 
    private int PRQUENA=3; 
    private String nombreEmpresa; 
    private int tamanoEmpresa;
    private String identificador; 
    
    public ClienteCorporativo(String nombreEmpresa, int tamanoEmpresa, int identifiador) {
    	super();
    	this.nombreEmpresa=nombreEmpresa; 
    	this.tamanoEmpresa=tamanoEmpresa; 
    }
    public String getNombreEmpresa() {
    	return nombreEmpresa;
    }
    public int getTamanoEmpresa() {
    	return tamanoEmpresa;
    }
    @Override
    public String getTipoCliente() {
    	return CORPORATIVO; 
    }
    @Override
    public String getIdentificador() {
    	return identificador; 
    }

    /**
     * Crea un nuevo objeto de tipo a partir de un objeto JSON.
     * 
     * El objeto JSON debe tener dos atributos: nombreEmpresa (una cadena) y tamanoEmpresa (un número).
     * @param cliente El objeto JSON que contiene la información
     * @return El nuevo objeto inicializado con la información
     */
    public static ClienteCorporativo cargarDesdeJSON( JSONObject cliente )
    {
        String nombreEmpresa = cliente.getString( "nombreEmpresa" );
        int tam = cliente.getInt( "tamanoEmpresa" );
        return new ClienteCorporativo( nombreEmpresa, tam );
    }

    /**
     * Salva este objeto de tipo ClienteCorporativo dentro de un objeto JSONObject para que ese objeto se almacene en un archivo
     * @return El objeto JSON con toda la información del cliente corporativo
     */
    public JSONObject salvarEnJSON( )
    {
        JSONObject jobject = new JSONObject( );
        jobject.put( "nombreEmpresa", this.nombreEmpresa );
        jobject.put( "tamanoEmpresa", this.tamanoEmpresa );
        jobject.put( "tipo", CORPORATIVO );
        return jobject;
	}
}
