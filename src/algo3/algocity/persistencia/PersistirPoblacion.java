package algo3.algocity.persistencia;

import algo3.algocity.modelo.menu.Poblacion;

import com.google.gson.Gson;

public class PersistirPoblacion {

	Gson gson = new Gson();
	String representacionJSON; 
	
	public void serializar(Poblacion poblacionASerializar){
		
		representacionJSON = gson.toJson(poblacionASerializar);
	}
	
	public String obtenerSerializacion() {
		
		return this.representacionJSON;
	}
	
	public Poblacion deserializar(String objetoSerializado) {
		
		Poblacion poblacion = gson.fromJson(objetoSerializado,Poblacion.class);
			
		 return poblacion;
	}

}
