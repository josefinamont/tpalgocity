package algo3.algocity.persistencia;

import algo3.algocity.modelo.conectores.Ruta;

import com.google.gson.Gson;

public class PersistirRuta {

	String representacionJSON;
	Gson gson = new Gson();
	
	public PersistirRuta(){
		
		this.representacionJSON = null;
	}
	
	public void serializar(Ruta ruta){
		
		this.representacionJSON = gson.toJson(ruta);
	}
	
	public String obtenerSerializacion() {
		
		return this.representacionJSON;
	}
	
	public Ruta deserializar(String rutaSerializada) {
		
		 Ruta rutaNueva = gson.fromJson(rutaSerializada,Ruta.class);
		
		 return rutaNueva;
	}
	
}
