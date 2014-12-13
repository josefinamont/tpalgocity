package algo3.algocity.persistencia;

import algo3.algocity.modelo.mapa.Mapa;

import com.google.gson.Gson;

public class PersistirMapa {

	Gson gson = new Gson();
	String representacionJSON; 
	
	public void serializar(Mapa mapaASerializar){
		
		representacionJSON = gson.toJson(mapaASerializar);
	}
	
	public Mapa deserializar(String objetoSerializado) {
		
		Mapa mapa = gson.fromJson(objetoSerializado,Mapa.class);
			
		 return mapa;
	}
	
	public String obtenerSerializacion() {
		
		return this.representacionJSON;
	}
}
