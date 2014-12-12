package algo3.algocity.persistencia;

import algo3.algocity.modelo.mapa.Mapa;

import com.google.gson.Gson;

public class PersisitirMapa {

	Gson gson = new Gson();
	String representacionJSON; 
	
	public void serializar(Mapa mapaAserializar){
		
		representacionJSON = gson.toJson(mapaAserializar);
	}
	
	public Mapa deserializar(String objetoSerializado) {
		
		 Mapa mapa = gson.fromJson(objetoSerializado,Mapa.class);
			
		 return mapa;
	}

	
}
