package algo3.algocity.persistencia;

import algo3.algocity.modelo.catastrofes.Terremoto;

import com.google.gson.Gson;

public class PersistirTerremoto {

	Gson gson = new Gson();
	String representacionJSON; 
	
	public void serializar(Terremoto terremotoASerializar){
		
		representacionJSON = gson.toJson(terremotoASerializar);
	}
	
	public String obtenerSerializacion() {
		
		return this.representacionJSON;
	}
	
	public Terremoto deserializar(String objetoSerializado) {
		
		Terremoto terremoto = gson.fromJson(objetoSerializado,Terremoto.class);
			
		 return terremoto;
	}
}
